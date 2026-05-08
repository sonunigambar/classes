package com.services.investmentservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.investmentservices.entity.Investment;
import com.services.investmentservices.repo.InverstmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jmx.ParentAwareNamingStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Investmentservice {

    @Autowired
    DownStreamService downStreamService;

    @Autowired
    InverstmentRepo repo;
    @Autowired
    private ParentAwareNamingStrategy objectNamingStrategy;

    @Autowired
    KafkaTemplate<String, Investment> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity<String> createInvestment(String userId, Double amount) {
        String status ="INIT";
        Investment investment = new Investment();
        try {
            //step-1
            String walletResponse = downStreamService.callWalletServiceDeductAmountWebClient(userId, amount);
            if (walletResponse != null && !"SUCCESS".equals(walletResponse)) {
                return ResponseEntity.status(408).body(walletResponse);
            }
            status = "WALLET_DEBIT_DONE";
            //save to investment db step-2
            investment.setAmount(amount);
            investment.setStatus("CREATED");
            investment.setUserId(userId);
            investment = repo.save(investment);

            status = "INVESTMENT_SAVED";

            //step-3
            String txResponse = downStreamService.callTransctionService();
            if (txResponse != null && !"SUCCESS".equals(txResponse)) {
                return ResponseEntity.badRequest().body("Transcription Failed");
            }
            status = "TX_DONE";
        }catch (Exception e){
            switch (status){
                case "WALLET_DEBIT_DONE":
                    downStreamService.compentiateTxWalletServiceAddAmount(userId, amount);
                    break;
                case "INVESTMENT_SAVED":
                    //rollback the db
                    investment.setStatus("FAILED");
                    repo.save(investment);
                    //rollback the investemnetservice
                    downStreamService.compentiateTxWalletServiceAddAmount(userId, amount);
                    break;
                default:
                    System.out.println("Nothing to initiate the compentitate transaction");

            }
        }

        return ResponseEntity.ok(status);
    }

    public ResponseEntity<String> createInvestmentKafka(String userId, Double amount) {
//        create investmet objectNamingStrategysave to db
//                initially status init
//
//                //send mesg to investment topic

        Investment investment = new Investment();
        investment.setStatus("INIT");
        investment.setUserId(userId);
        investment.setAmount(amount);
        investment = repo.save(investment);
        //send mesg to investment topic
        kafkaTemplate.send("investment_topic",userId, investment);
//        return the message;
        return ResponseEntity.ok("Investment Initiated");
    }

    @KafkaListener(topics = "investment_success", groupId = "tx_grp_id")
    public String investment_sccFul(String msg) throws JsonProcessingException {
        Investment investment = objectMapper.readValue(msg, Investment.class);
        repo.save(investment);
        System.out.println("msg: "+msg);
        return msg;
    }

    @KafkaListener(topics = "invesment_failure", groupId = "tx_fail_grp_id")
    public String investment_fail(String msg) throws JsonProcessingException {
        Investment investment = objectMapper.readValue(msg, Investment.class);
        repo.save(investment);
        System.out.println("msg: "+msg);
        return msg;
    }


}
