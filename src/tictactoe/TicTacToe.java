package tictactoe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.server.ExportException;

public class TicTacToe {

    char[][] ticTacToe;
    int size;
    public TicTacToe(int size) {
        this.ticTacToe = new char[size][size];
        this.size = size;
    }

    /**
     *
     * @param i
     * @param j
     * @param c: X or O
     */
    public boolean addToken(int i, int j, char c) {
        if (ticTacToe[i][j] == 'X' || ticTacToe[i][j] == 'O') {
            return false;
        }
        ticTacToe[i][j] = c;
        return true;
    }

    /**
     *
     */
    private void aiMove() {
        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j ++) {
                if (ticTacToe[i][j] != 'X' && ticTacToe[i][j] != 'O') {
                    ticTacToe[i][j] = 'O';// ai is always O
                    return;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j ++) {
                if (ticTacToe[i][j] != 'X' && ticTacToe[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println();
        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j ++) {
                char c = ticTacToe[i][j] == 'X' || ticTacToe[i][j] == 'O' ? ticTacToe[i][j] : '-';
                System.out.print(c);
                if (j != size - 1) {
                    System.out.print('|');
                }
            }
            System.out.println();
        }
    }

    private int[] readFromTerminal() {
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            String loc = reader.readLine();
            System.out.println(loc);
            String[] idxStr = loc.split(" ");
            int[] idx = new int[2];
            idx[0] = Integer.parseInt(idxStr[0]);
            idx[1] = Integer.parseInt(idxStr[1]);
            return idx;
        } catch (Exception e) {
            System.out.println("error");
        }
        return null;
    }

    private boolean isInvalidMove(int[] idx) {
        int i = idx[0];
        int j = idx[1];
        return idx == null || idx.length != 2 || j < 0 || j >= size || i >= size || ticTacToe[i][j] == 'X' || ticTacToe[i][j] == 'O';
    }

    public void playGame() {
        boolean exitGame = false;
        while (!exitGame) {
            System.out.println("Please add token:");
            int[] token = readFromTerminal();
            if (isInvalidMove(token)) {
                System.out.println("Error input.");
                continue;
            }
            addToken(token[0], token[1], 'X');
            aiMove();
            printBoard();
        }
    }

    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe(3);
        ttt.playGame();
    }

}
