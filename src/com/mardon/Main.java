package com.mardon;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner cin = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        // write your code here
        char[][] field = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        playGame(field);
    }

    public static void playGame(char[][] field) {

        do {
            // human move
            doHumanMove(field, 'X');
            drawField(field);

            if(checkDraw(field)){
                System.out.println("Game ended in a draw.\nNobody won.");
                break;
            }
            // checkwinHuman
            if (checkWin(field, 'X')) {
                System.out.println("HUMAN WINS.\nGame is over.");
                break;
            }

            // ai move
            doBotMove(field, 'O');
            drawField(field);
            // checkwinAI
            if (checkWin(field, 'O')) {
                System.out.println("Bot Alex wins.\nGame is over.");
                break;
            }
        } while (true);
    }

    public static boolean checkWinningMove(char[][] field, char ourSign, int[] coords) {
        for (int i = 0; i < 3; i++) {
            int counter = 0, x = -1, y = -1;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != '_') {
                    if (field[i][j] != ourSign) {
                        counter++;
                    }
                } else {
                    x = j;
                    y = i;
                }
            }
            if (counter == 2 && x != -1) {
                coords[0] = y;
                coords[1] = x;
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            int counter = 0, x = -1, y = -1;
            for (int j = 0; j < 3; j++) {
                if (field[j][i] != '_') {
                    if (field[j][i] != ourSign) {
                        counter++;
                    }
                } else {
                    x = i;
                    y = j;
                }
            }
            if (counter == 2 && x != -1) {
                coords[0] = y;
                coords[1] = x;
                return true;
            }
        }
        int x = -1, y = -1, counter = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][i] != '_') {
                if (field[i][i] != ourSign) {
                    counter++;
                }
            } else {
                x = i;
                y = i;
            }
            if (counter == 2 && x != -1) {
                coords[0] = y;
                coords[1] = x;
                return true;
            }
        }
        counter = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][i] != '_') {
                if (field[i][2 - i] != ourSign) {
                    counter++;
                }
            } else {
                x = i;
                y = i;
            }
            if (counter == 2 && x != -1) {
                coords[0] = y;
                coords[1] = x;
                return true;
            }
        }

        return false;
    }

    public static void doBotMove(char[][] field, char sign) {
        int[] coords = {-1, -1};
        if (checkWinningMove(field, sign, coords)) {
            field[coords[0]][coords[1]] = sign;
        } else {
            System.out.println("Processing Bot Alex's move...");
            int aiX = rand.nextInt(3);
            int aiY = rand.nextInt(3);
            while (field[aiY][aiX] != '_') {
                aiX = rand.nextInt(3);
                aiY = rand.nextInt(3);
            }
            field[aiY][aiX] = sign;
        }
        System.out.println("Alex has made his move.");
    }

    public static void doHumanMove(char[][] field, char sign) {
        System.out.println("Your move, human.");
        int humanX = cin.nextInt();
        int humanY = cin.nextInt();
        while (!(humanX < 4 && humanY < 4 && humanX > 0 && humanY > 0 && field[humanY - 1][humanX - 1] == '_')) {
            humanX = cin.nextInt();
            humanY = cin.nextInt();
        }
        field[humanY - 1][humanX - 1] = sign;
    }

    public static boolean checkWin(char[][] field, char piece) {
        // vertical
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][2] == piece) {
                return true;
            }
        }
        // horizontal
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[2][i] == piece) {
                return true;
            }
        }
        //diagonal
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[2][2] == piece) {
            return true;
        }
        return field[2][0] == field[1][1] && field[1][1] == field[0][2] && field[0][2] == piece;
    }

    public static void drawField(char[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static boolean checkDraw(char[][] field){
        int counter = 0;
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(field[i][j] == '_'){
                    return false;
                }
            }
        }
        return true;
    }
}
