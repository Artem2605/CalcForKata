package com.testMe;

import java.util.Scanner;

public class Main {

    static class MyException extends Exception {
        public MyException(String str) {
            super(str);
        }
    }

    public static void main(String[] args) throws MyException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws MyException {
        //записываем в массив арабские числа в соответствии с индексами
        String[] countsArab = new String[101];
        for(int i = 0; i < countsArab.length; i++)
            countsArab[i] = Integer.toString(i);

        //записываем в массив римские числа в соответствии с индексами
        String[] countsRome = new String[] {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C",};

        String[] s = input.split(" ");

        if(s.length > 3) throw new MyException("Inappropriate input format - two operands and one operator (+, -, /, *)");
        else if(s.length < 3) throw new MyException("String is not a mathematical operation");

        //проверяем являются ли числа римскими
        byte modeRim = 0;
        for(String el: countsRome) {
            if(s[0].equals(el))
                modeRim++;
            if(s[2].equals(el))
                modeRim++;
        }

        if(modeRim == 1) throw new MyException("Different number systems are used simultaneously");

        byte numFirst = 0, numSec = 0, result = 0;

        //определяем систему счисления и записывваем числа в переменные
        if(modeRim == 0) {
            for (int i = 0; i < countsArab.length; i++) {
                if(s[0].equals(countsArab[i]))
                    numFirst = (byte)i;
                if(s[2].equals(countsArab[i]))
                    numSec = (byte)i;
            }
        } else if(modeRim == 2) {
            for (int i = 0; i < countsRome.length; i++) {
                if(s[0].equals(countsRome[i]))
                    numFirst = (byte)i;
                if(s[2].equals(countsRome[i]))
                    numSec = (byte)i;
            }
        }

        if((numFirst < 1 || numFirst > 10) || (numSec < 1 || numSec > 10)) throw new MyException("Incorrect numbers entered");

        //производим операцию
        if(s[1].equals("+")) result = (byte)(numFirst + numSec);
        else if(s[1].equals("-")) result = (byte)(numFirst - numSec);
        else if(s[1].equals("*")) result = (byte)(numFirst * numSec);
        else if(s[1].equals("/")) result = (byte)(numFirst / numSec);

        if(result < 1 && modeRim == 2) throw new MyException("There are no negative numbers in the Roman system");

        if(modeRim == 2) return countsRome[result];
        else return Byte.toString(result);
    }
}
