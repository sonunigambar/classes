import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> list = List.of("sonu","monu");
        List x = list.stream()
                .map(t-> t.toUpperCase())
                .toList();
        System.out.println(x);
    }
}




