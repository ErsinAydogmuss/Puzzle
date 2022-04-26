import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Scanner;

public class puzzle {
    public static String puzzleWords;
    public static ArrayList<Character> letters = new ArrayList<>();
    public static String word1;
    public static String word2;
    public static String word3;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 1st input value:   ");
        word1 = input.nextLine();
        if (word1.length() < 2 || word1.length() > 6) throw new Exception("Non-Conforming Word");
        System.out.print("Enter 2nd input value:   ");
        word2 = input.nextLine();
        if (word2.length() < 2 || word2.length() > 6) throw new Exception("Non-Conforming Word");
        System.out.print("Enter Output value:      ");
        word3 = input.nextLine();
        if (word3.length() < 2 || word3.length() > 6) throw new Exception("Non-Conforming Word");

        puzzleWords = word1 + "+" + word2 + "=" + word3;

        String[] letters1 = word1.split("");
        String[] letters2 = word2.split("");
        String[] letters3 = word3.split("");

        System.out.println("searching...found!");
        System.out.println();

        ArrayList<Character> allLetters = new ArrayList<>();

        for (String s : letters1) { allLetters.add(s.charAt(0)); }
        for (String s : letters2) { allLetters.add(s.charAt(0)); }
        for (String s : letters3) { allLetters.add(s.charAt(0)); }

        for (Character allLetter : allLetters) {
            if (!letters.contains(allLetter)) letters.add(allLetter);
        }

        if (letters.size() > 10) System.out.println("Error");

        ArrayList<String> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) numbers.add(Integer.toString(i));

        ArrayList<String> temporaryArray = new ArrayList<>();

        puzzleSolve(temporaryArray, numbers, letters.size());
    }

    public static void puzzleSolve(ArrayList<String> temporaryArray,ArrayList<String> numbers,int numberOfLetters) {
        for (int i = 0; i < numbers.size(); i++) {
            String e = numbers.get(i);
            temporaryArray.add(e);
            numbers.remove(e);

            if (numberOfLetters == 1) solutionCheck(temporaryArray);
            else puzzleSolve(temporaryArray, numbers, numberOfLetters - 1);

            temporaryArray.remove(e);
            numbers.add(i, e);
        }
    }

    public static void solutionCheck(ArrayList<String> temporaryArray) {
        String stringResult = puzzleWords;
        int index = 0;
        int sum = 0;
        int result;

        for (Character letter : letters) {
            stringResult = stringResult.replaceAll(letter.toString(), temporaryArray.get(index));
            index++;
            if (index >= temporaryArray.size()) index = 0;
        }

        String[] stringPart = stringResult.replaceAll(" ", "").split("[+=]");

        for (int i = 0; i < stringPart.length - 1; i++) { sum += Integer.parseInt(stringPart[i]); }

        result = Integer.parseInt(stringPart[stringPart.length - 1]);

        if (sum == result) {
            System.out.println(word1 + ":   " + stringPart[0]);
            System.out.println(word2 + ":   " + stringPart[1]);
            System.out.println(word3 + ":   " + stringPart[2]);
        }
    }
}