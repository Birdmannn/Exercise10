import textio.TextIO;

import java.util.*;


public class Concordance {
    private static TreeMap<String, TreeSet<Integer>> index;
    private static int lineNum = 1;                                                        //for the first line


    public static void main(String[] args) {
        index = new TreeMap<>();

        System.out.println("   This program let's the user select both an input and an output file.");
        System.out.println("   It creates a concordance for the input file and outputs the result to the output file.");
        System.out.println("   Basically prints each word in the input file with the line number in which they were found.");
        System.out.println();
        System.out.println("Press the Return key if you wish to continue...");
        TextIO.getln();

        String word;
        try {
            boolean read = TextIO.readUserSelectedFile();
            if(!read) {
                System.out.println("No file was selected.");
                return;
            }
            word = readNextWord();
            while(word != null) {
                word = word.toLowerCase();
                if(word.length() > 2 && !word.equals("the"))
                    addReference(word,lineNum);

                word = readNextWord();
            }

            printToOutput();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        System.out.println("End of Program.");
    }

    static void addReference(String key, int lineNum) {
        TreeSet<Integer> references = index.get(key);

        if(references == null) {
            TreeSet<Integer> firstRef = new TreeSet<>();
            firstRef.add(lineNum);
            index.put(key,firstRef);
        }
        else {
            references.add(lineNum);
        }
    }

    static void printToOutput() {
        boolean check = TextIO.writeUserSelectedFile();
        if(!check)
            return;
        TextIO.putln("Concordance in the file:");
        TextIO.putln();
        for(Map.Entry<String, TreeSet<Integer>> entry : index.entrySet()) {
            String key = entry.getKey();
            TreeSet<Integer> lineSet = entry.getValue();
            int firstLine = lineSet.first();

            TextIO.put(key + ": " + firstLine);

            for(int line : lineSet.tailSet(firstLine + 1))
                TextIO.put(", " + line);

            TextIO.putln();
        }
    }

    static String readNextWord() {
        char ch = TextIO.peek();
        while(ch != TextIO.EOF && !Character.isLetter(ch)) {
            if(ch == '\n')
                lineNum++;
            TextIO.getAnyChar();
            ch = TextIO.peek();
        }
        if(ch == TextIO.EOF)
            return null;

        String word ="";
        while(true) {
            word += TextIO.getAnyChar();
            ch = TextIO.peek();
            if(ch == '\'') {
                TextIO.getAnyChar();
                ch = TextIO.peek();
                if(Character.isLetter(ch)) {
                    word += "'" + TextIO.getAnyChar();
                    ch = TextIO.peek();
                }
                else
                    break;
            }
            if(!Character.isLetter(ch)) {
                break;
            }
            // If we haven't broken out of the loop, next char is a letter.
        }
        return word;
    }
}
