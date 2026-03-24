package ru.kiss.fool;

import java.util.Scanner;

public class Fool {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final String FIZZ_BUZZ = "FizzBuzz";
    private static final String ERROR = "Ошибка. Начинай снова.";

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(checkNum(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!checkNum(startAt).equals(answer)) {
                System.out.println(ERROR);
                startAt = 0;
            }
            startAt++;
        }
    }

    public static String checkNum(int num) {
        if (num % 5 == 0 & num % 3 == 0) {
            return FIZZ_BUZZ;
        }
        if (num % 3 == 0) {
            return FIZZ;
        }
        if (num % 5 == 0) {
            return BUZZ;
        }
        return String.valueOf(num);
    }

}
