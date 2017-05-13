package com.example.allentang.password;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to generate the password based on the user inputs
 * @author Allen Tang
 */

public class PasswordGenerator {

    public final static String POSSIBLE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public final static String POSSIBLE_NUMBERS = "0123456789";
    public final static String POSSIBLE_SYMBOLS = "!@#$%^&*()-=+_,.?:'][{};|";
    
    /**
     * Generates the password from set synbols, letters, and numbers based on user specified quantity
     * @param length length of the total password
     * @param numbers number of numbers in the password
     * @param symbols number of special symbols in the password
     * @return the fully generated password
     */
    public static String createPass(int length, int numbers, int symbols){
        String passConcat = "";
        

        for (int i = 0; i < numbers; i++){
            passConcat += POSSIBLE_NUMBERS.toCharArray()[(int)(Math.random()*POSSIBLE_NUMBERS.length())];
        }
        for (int i = 0; i < symbols; i++){
            passConcat += POSSIBLE_SYMBOLS.toCharArray()[(int)(Math.random()*POSSIBLE_SYMBOLS.length())];
        }
        for (int i = 0; i < (length-numbers-symbols); i++){
            passConcat += POSSIBLE_LETTERS.toCharArray()[(int)(Math.random()*POSSIBLE_LETTERS.length())];
        }

        //since the pass is not mixed up, we slice it up here
        List<Character> passChars = new ArrayList<>();
        for (char passChar : passConcat.toCharArray()){
            passChars.add(passChar);
        }
        StringBuilder scrambledPass = new StringBuilder(length);
        while (passChars.size() != 0){
            scrambledPass.append(passChars.remove((int)(Math.random()*passChars.size())));
        }
        return scrambledPass.toString();
    }
}
