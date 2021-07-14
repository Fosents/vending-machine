package utils;

import enums.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    Scanner input;

    public UserInput() {
        this.input = new Scanner(System.in);
    }

    final String SEPARATOR = "----------";
    final String INVALID_INPUT = "INVALID INPUT! PLEASE SELECT AGAIN";

    /**
     * Get int from console
     * @return int number
     */
    public String getInput(Input type) {
        String select = null;
        boolean correct = false;
        do {
            try {
                if (type == Input.INT) {
                    int collected = input.nextInt();
                    select = String.valueOf(collected);
                } else if (type == Input.DOUBLE) {
                    double collected = input.nextDouble();
                    select = String.valueOf(collected);
                } else if (type == Input.STRING) {
                    select = input.next();
                }
                correct = true;
            } catch (InputMismatchException ignored) {
                printState(INVALID_INPUT);
            }
            input.nextLine();
        } while (!correct);
        return select;
    }

    public int getInt() {
        return Integer.parseInt(this.getInput(Input.INT));
    }

    public double getDouble() {
        return Double.parseDouble(this.getInput(Input.DOUBLE));
    }

    public String getString() {
        return this.getInput(Input.STRING);
    }



    public void printState(String state) {
        System.out.println(SEPARATOR);
        System.out.println(state);
        System.out.println(SEPARATOR);
    }
}
