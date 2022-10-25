import java.util.*;
import java.util.stream.*;
public class StreamAPITest {

    private static class ScoreInfo {
        String firstName;
        String lastName;
        int score;
        ScoreInfo(String lName, String fName, int s) {
            firstName = fName;
            lastName = lName;
            score = s;
        }
    }


    private final static ScoreInfo[] scoreData = new ScoreInfo[] {
            new ScoreInfo("Smith", "John", 70),
            new ScoreInfo("Doe", "Mary", 85),
            new ScoreInfo("Page", "Alice", 82),
            new ScoreInfo("Cooper", "Jill", 97),
            new ScoreInfo("Flintstone", "Fred", 66),
            new ScoreInfo("Rubble", "Barney", 80),
            new ScoreInfo("Smith", "Judy", 48),
            new ScoreInfo("Dean", "James", 90),
            new ScoreInfo("Russ", "Joe", 55),
            new ScoreInfo("Wolfe", "Bill", 73),
            new ScoreInfo("Dart", "Mary", 54),
            new ScoreInfo("Rogers", "Chris", 78),
            new ScoreInfo("Toole", "Pat", 51),
            new ScoreInfo("Khan", "Omar", 93),
            new ScoreInfo("Smith", "Ann", 95)
    };

    public static void main(String[] args) {

        System.out.println("Certain tests in an array of scoreData of people, using the Stream API.");
        System.out.println();

        long count = Arrays.stream(scoreData)
                .count();

        int sum = Arrays.stream(scoreData)
                .mapToInt(s -> s.score)
                .sum();

        double average = (double) (sum / count);

        long no_of_A_Students = Arrays.stream(scoreData)
                .filter(s -> (s.score >= 90))
                .count();

        List<String> not_pass_students;
        not_pass_students = Arrays.stream(scoreData)
                .filter(s -> (s.score < 70))
                .map(s -> s.firstName + " " + s.lastName)
                .collect(Collectors.toList());

        System.out.printf("   1. Number of students:%40d\n", count);
        System.out.printf("   2. Average score of students:%40f\n", average);
        System.out.printf("   3. Number of students who got an 'A':%24d\n", no_of_A_Students);
        System.out.println("   4. Names of students whose score was less than 70:");

        for (String name : not_pass_students)
            System.out.println("          " + name);

        System.out.println("\n   5. Names and scores of students ordered by last name:");
        Arrays.stream(scoreData).parallel()
                .map(scoreData -> {
                    String str = scoreData.lastName + " " + scoreData.firstName;
                    return String.format("%20s%40d", str, scoreData.score);

                })
                .sorted()
                .forEachOrdered(System.out::println);

        System.out.println("\n   6. Names and scores of students ordered by score:");
        Arrays.stream(scoreData).parallel()
                .sorted(Comparator.comparingInt(s -> s.score))
                .map(s -> {
                    String str = s.lastName + " " + s.firstName;
                    return String.format("%20s%40d", str, s.score);
                })
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(System.out::println);
    }
}
