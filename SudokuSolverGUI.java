import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuSolverGUI extends JFrame {

    private JTextField[][] cells = new JTextField[9][9];
    private JButton solveButton, clearButton;

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sudoku grid panel
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize 9x9 text fields
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("SansSerif", Font.BOLD, 18));
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));

                // Different background for 3x3 blocks
                if (((i / 3) + (j / 3)) % 2 == 0) {
                    cells[i][j].setBackground(new Color(230, 230, 250));
                }

                gridPanel.add(cells[i][j]);
            }
        }

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve Sudoku");
        clearButton = new JButton("Clear Board");

        solveButton.addActionListener(e -> solveSudoku());
        clearButton.addActionListener(e -> clearBoard());

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        // Add panels to frame
        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText("");
            }
        }
    }

    private void solveSudoku() {
        int[][] board = new int[9][9];

        // Read numbers from text fields
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = cells[i][j].getText().trim();
                if (!text.isEmpty()) {
                    try {
                        int num = Integer.parseInt(text);
                        if (num < 1 || num > 9) {
                            JOptionPane.showMessageDialog(this, "Invalid number at (" + (i+1) + "," + (j+1) + ")");
                            return;
                        }
                        board[i][j] = num;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input at (" + (i+1) + "," + (j+1) + ")");
                        return;
                    }
                } else {
                    board[i][j] = 0;
                }
            }
        }

        // Use your SudokuSolver logic
        if (SudokuSolver.solveSudoku(board)) {
            // Display solution
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    cells[i][j].setText(String.valueOf(board[i][j]));
                }
            }
            JOptionPane.showMessageDialog(this, "Sudoku solved successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuSolverGUI().setVisible(true));
    }
}
