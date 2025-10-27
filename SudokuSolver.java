
public class SudokuSolver{

    // Function to print the Sudoku grid
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0)
                System.out.println("----------+-----------+-----------");

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0)
                    System.out.print(" | ");
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to check if placing a number is valid
    public static boolean isValid(int[][] board, int row, int col, int num) {

        // Check the row
        for (int x = 0; x < 9; x++)
            if (board[row][x] == num)
                return false;

        // Check the column
        for (int x = 0; x < 9; x++)
            if (board[x][col] == num)
                return false;

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num)
                    return false;
            }
        }

        return true;
    }

    // Function that uses backtracking to solve Sudoku
    public static boolean solveSudoku(int[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Find an empty cell (represented by 0)
                if (board[row][col] == 0) {

                    // Try placing numbers 1–9
                    for (int num = 1; num <= 9; num++) {

                        // Check if valid
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            // Recursively continue
                            if (solveSudoku(board))
                                return true;

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false; // No valid number found
                }
            }
        }

        return true; // Solved
    }

    // Main function
    public static void main(String[] args) {

        int[][] board = {
            {7, 8, 0, 4, 0, 0, 1, 2, 0},
            {6, 0, 0, 0, 7, 5, 0, 0, 9},
            {0, 0, 0, 6, 0, 1, 0, 7, 8},
            {0, 0, 7, 0, 4, 0, 2, 6, 0},
            {0, 0, 1, 0, 5, 0, 9, 3, 0},
            {9, 0, 4, 0, 6, 0, 0, 0, 5},
            {0, 7, 0, 3, 0, 0, 0, 1, 2},
            {1, 2, 0, 0, 0, 7, 4, 0, 0},
            {0, 4, 9, 2, 0, 6, 0, 0, 7}
        };

        System.out.println("Original Sudoku:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists!");
        }
    }
}
