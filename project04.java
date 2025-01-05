package gr.aueb.cf.projects;


import java.util.*;

public class project04 {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        initializeBoard(board);
        playGame(board);
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], '-');
        }
    }

    public static void playGame(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';
        while (true) {
            printBoard(board);
            System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board[row][col] = currentPlayer;
            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}