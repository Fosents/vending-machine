package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    Scanner input;

    public UserInput() {
        this.input = new Scanner(System.in);
    }

    final String SEPARATOR = "----------";
    final String INVALID_INPUT = "INVALID INPUT! PLEASE SELECT AGAIN";

    public int getInt() {
        int select = 0;
        boolean correct = false;
        do {
            try {
                select = input.nextInt();
                correct = true;
            } catch (InputMismatchException ignored) {
                printState(INVALID_INPUT);
            }
            input.nextLine();
        } while (!correct);
        return select;
    }

    public void printState(String str) {
        System.out.println(SEPARATOR);
        System.out.println(str);
        System.out.println(SEPARATOR);
    }
}
