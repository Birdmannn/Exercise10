import textio.TextIO;

import java.util.TreeSet;

public class MathSetOperations {

    private static class SyntaxError extends Exception {
        SyntaxError(String message) {
            super(message);
        }
    }
    private static boolean check;

    public static void main(String[] args) {

        TreeSet<Integer> set1, set2, set3;
        System.out.println("This program emulates a mathematical set operation calculator.");
        System.out.println("Type in set operations with the following syntax:");
        System.out.println("   1. There should no more than two sets.");
        System.out.println("   2. Each set should be enclosed in block parenthesis, and each element separated by commas.");
        System.out.println("   3. The two sets must be separated by either one of these operators: '+' for union, '*' for intersection, and '-' for difference.");
        System.out.println("   4. Not abiding by the rules above will cause an error in the syntax.");
        System.out.println("\nYou may type in your line of input.");


        System.out.println("                   Input                                                                                Output   ");
        System.out.println("--------------------------------------------                                     ------------------------------------------------");

        boolean goo;
        do {
            try {
                set1 = createSet();
                set3 = new TreeSet<>(set1);
                TextIO.skipBlanks();
                char ch = TextIO.getChar();
                set2 = createSet();
                System.out.printf("       " + set3 + " " + ch + " " + set2 + "%82s\n", doOp(set1,set2,ch));
                goo = true;

            }
            catch (SyntaxError | NumberFormatException | NullPointerException e) {
                System.out.printf("%100s continue?\n", e.getMessage());
                goo = false;
                check = TextIO.getlnBoolean();
            }
        } while (goo || check);
    }

    static TreeSet<Integer> createSet() throws SyntaxError {
        TreeSet<Integer> temp = new TreeSet<>();
        TextIO.skipBlanks();

        if(TextIO.peek() == '[' || TextIO.peek() == '\n') {
            TextIO.getChar();
            TextIO.skipBlanks();
            //Then we should start reading numbers
            if(TextIO.peek() == ']') {
                //the set is empty
                TextIO.getChar();
                return temp;
            }
            if(!Character.isDigit(TextIO.peek()))
                throw new SyntaxError("Error found in syntax.");
            while(true) {
                TextIO.skipBlanks();
                if(Character.isDigit(TextIO.peek())) {
                    temp.add(TextIO.getInt());
                    TextIO.skipBlanks();
                    if(TextIO.peek() == ',')
                        TextIO.getChar();
                    else if(TextIO.peek() == ']') {
                        TextIO.getChar();
                        break;
                    }
                    else
                        throw new SyntaxError("Error found in Syntax.");
                }
                else {
                    throw new SyntaxError("Error found in Syntax. Next Syntax should be an integer.");
                }
            }
        }
        else
            throw new SyntaxError("Error found in syntax.");

        return temp;
    }

    static TreeSet<Integer> doOp(TreeSet<Integer> A, TreeSet<Integer> B, char op) throws SyntaxError {
        switch (op) {
            case '+' -> {
                A.addAll(B);
                return A;
            }
            case '*' -> {
                A.retainAll(B);
                return A;
            }
            case '-' -> {
                A.removeAll(B);
                return A;
            }
            default -> throw new SyntaxError("Illegal operand '" + op + "' found.");
        }
    }
}
