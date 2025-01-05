package gr.aueb.cf.projects;

public class project05 {
    private static final int ROWS = 30;
    private static final int COLUMNS = 12;
    private static boolean[][] theater = new boolean[ROWS][COLUMNS];

    public static void main(String[] args) {
        book('C', 2);
        cancel('C', 2);
    }

    public static void book(char column, int row) {
        int colIndex = column - 'A';
        if (isValidPosition(row, colIndex) && !theater[row][colIndex]) {
            theater[row][colIndex] = true;
            System.out.println("Seat " + column + row + " booked successfully.");
        } else {
            System.out.println("Seat " + column + row + " is already booked or invalid.");
        }
    }

    public static void cancel(char column, int row) {
        int colIndex = column - 'A';
        if (isValidPosition(row, colIndex) && theater[row][colIndex]) {
            theater[row][colIndex] = false;
            System.out.println("Booking for seat " + column + row + " cancelled.");
        } else {
            System.out.println("Seat " + column + row + " is not booked or invalid.");
        }
    }

    private static boolean isValidPosition(int row, int colIndex) {
        return row >= 0 && row < ROWS && colIndex >= 0 && colIndex < COLUMNS;
    }
}
