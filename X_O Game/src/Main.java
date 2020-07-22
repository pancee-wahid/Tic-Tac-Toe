import java.util.Scanner;

public class Main {

    final public static Scanner scanner = new Scanner(System.in);
    public static char[][] box = new char[3][3];
    public static boolean finished = false;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[i][j] = ' ';
            }
        }
        printField();

        //Turns
        while (!finished) {
            System.out.println("X's turn");
            nextMove('X');
            checkBox();
            if (!finished) {
                System.out.println("O's turn");
                nextMove('O');
                checkBox();
            }
        }
    }

    public static void printField(){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void nextMove(char turn) {
        String a, b;
        do {
            System.out.println("Enter the coordinates: ");
            a = scanner.next();
            b = scanner.next();
        } while (!validMove(a,b));
        box[3-Integer.parseInt(b)][Integer.parseInt(a)-1] = turn;
        printField();
    }

    public static boolean validMove(String a, String b) {
        if (!isNumeric(a) || !isNumeric(b)) {
            System.out.println("You should enter numbers!");
            return false;
        } else if (Integer.parseInt(a) < 1 || Integer.parseInt(a) > 3
                || Integer.parseInt(b) < 1 || Integer.parseInt(b) > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (box[3-Integer.parseInt(b)][Integer.parseInt(a)-1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void checkBox() {
        if (checkInRow('X')) {
            System.out.println("X wins");
            finished = true;
        } else if (checkInRow('O')) {
            System.out.println("O wins");
            finished = true;
        } else if (emptyCell()) {
            finished = false;
        } else {
            System.out.println("Draw");
            finished = true;
        }
    }

    public static boolean checkInRow(char letter) {
        //Check Row
        for (int i = 0; i < 3; i++) {
            int j = 0;
            if (box[i][j] == letter && box[i][j + 1]  == letter && box[i][j+2] == letter) {
                return true;
            }
        }
        //Check Column
        for (int j = 0; j < 3; j++) {
            int i = 0;
            if (box[i][j] == letter && box[i + 1][j]  == letter && box[i + 2][j] == letter) {
                return true;
            }
        }
        //Check Diagonal
        if (box[0][0] == letter && box[1][1] == letter && box[2][2] == letter) {
            return true;
        } else if (box[0][2] == letter && box[1][1] == letter && box[2][0] == letter) {
            return true;
        }
        return false;
    }

    public static boolean emptyCell() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (box[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }
}