import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class StreamAPI {
        public static void main(String[] args) {
            //Remove all vowels from a given String
            String str = "HelloWorld";  //H, e,l, l, w hllw
            String removeVolwels = str.chars()
                    .filter(ch -> !"aeiouAEIOU".contains(String.valueOf((char) ch)))
                    .mapToObj(ch -> String.valueOf((char) ch))   //"h","l"
                    .collect(Collectors.joining());
            System.out.println(removeVolwels);


//        convert a list of string to uppercase
            List<String> nameList = Arrays.asList("Ram", "shyam", "herry", "sachin");
            List<String> nameListUpperCase = nameList.stream()
                    .map(name -> name.toUpperCase())
                    .toList();
            System.out.println(nameListUpperCase);
//        count the frequency of words in a given string
            String st = "java python java html html html"; //java, python, java, html
            //grouopingBy, counting
            String[] split = st.split(" ");
            Map<String, Long> frequencyOfEachWord = Arrays.stream(split)
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
            System.out.println(frequencyOfEachWord);


//        occurance of each charcter
            String s = "welcome to my coding cure";
            Map<Character, Long> occouranceOfEachChar = s.chars()
                    .mapToObj(ch -> (char) ch)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            System.out.println(occouranceOfEachChar);

//        length of each word in a string
            String str1 = "welcome to my coding cure welcome to Java";
            String[] wordArray = str1.split(" ");
//            LinkedHashMap<String, Integer> legthofEachWord = Arrays.stream(wordArray)
//                    .collect(Collectors.toMap(word -> word, String::length, (e1, e2) -> e1))
//                    .entrySet()
//                    .stream()
//                    .sorted(Map.Entry.comparingByKey())
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//            System.out.println(legthofEachWord);


//        Find the first non-repeating character in a string
            String str3 = "cbadbdcf";
            Map.Entry<Character, Long> firstNonrepeatingCharacter = str3.chars()
                    .mapToObj(ch -> (char) ch)
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() > 1)
                    .findFirst().get();
            System.out.println(firstNonrepeatingCharacter);

            int[] freq = new int[256]; // ASCII size
//        Find the first repeating character in a string
//        write a program to find the second highest element
            int[] x = {1, 2, 5, 6,6, 3, 8,8,8,7,7};
            Integer secondHighest = Arrays.stream(x)
                    .boxed()
                    .sorted(Comparator.reverseOrder()) //8,7,6,5,2,1
                    .distinct()
                    .skip(1)
                    .findFirst().get();
            System.out.println(secondHighest);


//        Longest String in an Array of Strings
            String str4 = "welcome to my coding cure abcdedfhgjkhffg";
            String[] longestArraySplit = str4.split(" ");
            Optional<String> longestString = Arrays.stream(longestArraySplit)
                    .max(Comparator.comparingInt(String::length));
            System.out.println(longestString.get());


//        calculate sum
            int num = 12345; //1, 2, 3, 4, 5
            Integer sumOfNum = String.valueOf(num).chars()
                            .map(ch -> ch - '0')
                                    .sum();
            System.out.println(sumOfNum);


//        Find Total no of count of particular word from a list
            String searchingWord="python";
            List<String> list = Arrays.asList("java python java html", "java java css html", "hadoop spark java");
            long count = list.stream()
                    .flatMap(word -> Arrays.stream(word.split(" ")))
                    .filter(word -> word.equalsIgnoreCase(searchingWord))
                    .count();
            System.out.println(count);


        }

}
