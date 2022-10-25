import textio.TextIO;
public class Test {

    public static void main(String[] args) {
        HashMapString strMap = new HashMapString();
        int i = 50;
        int j = 1000;

        System.out.println("This program will test the HashMapString class.");
        strMap.put("One", "January");
        strMap.put("Two", "February");
        strMap.put("Three", "March");
        strMap.put("Four", "April");

        strMap.remove("Three");
        System.out.println(strMap.get("One"));
        System.out.println(strMap.get("Three"));
        System.out.println(strMap.size());

        System.out.printf("Your first is: %50d%n", i);
        System.out.printf("What do you mean by my first score is %10d", j);



    }
}
