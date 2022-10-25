import java.util.Set;
import java.util.TreeMap;
import textio.TextIO;

public class PhoneDirectory {

    public static void main(String[] args) {
        TreeMap<String,String> directory = new TreeMap<>();

        System.out.println("Enter names and numbers line by line for this experiment.");
        String name, num;

        for(int i = 0; i < 5; i++) {
            name = TextIO.getWord();
            num = TextIO.getWord();
            directory.put(name,num);
        }
        System.out.println();
        System.out.println("Your current Phone Directory:\n");

        Set<String> keys = directory.keySet();
        for(String key : keys) {
            System.out.println("   " + key + "         " + directory.get(key));
        }
    }
}
