package com.example.allentang.password;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author Allen Tang
 */
public class PasswordGeneratorTest {
    @Test
    public void createPass() throws Exception {
        assertEquals(PasswordGenerator.createPass(5,1,2).length(), 5);
        assertEquals(PasswordGenerator.createPass(11,1,2).length(), 11);
        assertEquals(PasswordGenerator.createPass(7,1,2).length(), 7);

        String pass = PasswordGenerator.createPass(5,1,2);
        assertEquals(1, intersection(pass.toCharArray(), PasswordGenerator.POSSIBLE_NUMBERS.toCharArray()).length);
        pass = PasswordGenerator.createPass(5,0,2);
        assertEquals(0, intersection(pass.toCharArray(), PasswordGenerator.POSSIBLE_NUMBERS.toCharArray()).length);

        pass = PasswordGenerator.createPass(5,1,2);
        assertEquals(2, intersection(pass.toCharArray(), PasswordGenerator.POSSIBLE_SYMBOLS.toCharArray()).length);
        pass = PasswordGenerator.createPass(5,1,0);
        assertEquals(0, intersection(pass.toCharArray(), PasswordGenerator.POSSIBLE_SYMBOLS.toCharArray()).length);
    }

    /**
     * Hashset intersection function for testing purposes
     * @param passString password
     * @param genSet numbers/symbols it needs to contain
     * @return intersection of the 2, or number of something it contains
     */
    public char[] intersection(char[] passString, char[] genSet) {
        HashSet<Character> set1 = new HashSet<Character>();
        for(char i: passString){
            set1.add(i);
        }

        HashSet<Character> set2 = new HashSet<Character>();
        for(char i: genSet){
            if(set1.contains(i)){
                set2.add(i);
            }
        }

        char[] result = new char[set2.size()];
        char i=0;
        for(char n: set2){
            result[i++] = n;
        }

        return result;
    }

}