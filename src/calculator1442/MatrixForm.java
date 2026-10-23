package calculator1442;
import javax.swing.*;
import java.awt.*;

public class MatrixForm extends JFrame {

    private final JSpinner rowsSpinner =
            new JSpinner(new SpinnerNumberModel(3, 1, 20, 1));

    private final JSpinner colsSpinner =
            new JSpinner(new SpinnerNumberModel(3, 1, 20, 1));

    private final JPanel matrixPanel = new JPanel();

    public MatrixForm() {
        setTitle("Matrix Calculator");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel settings = new JPanel(new FlowLayout());

        settings.add(new JLabel("Rows:"));
        settings.add(rowsSpinner);
        settings.add(new JLabel("Columns:"));
        settings.add(colsSpinner);

        JButton generateButton = new JButton("Generate Matrix");
        generateButton.addActionListener(e -> generateMatrix());

        settings.add(generateButton);

        setLayout(new BorderLayout(10, 10));
        add(settings, BorderLayout.NORTH);
        add(new JScrollPane(matrixPanel), BorderLayout.CENTER);

        generateMatrix();
    }

    private void generateMatrix() {
        int rows = (Integer) rowsSpinner.getValue();
        int cols = (Integer) colsSpinner.getValue();

        matrixPanel.removeAll();
        matrixPanel.setLayout(new GridLayout(rows, cols, 5, 5));

        for (int i = 0; i < rows * cols; i++) {
            JTextField cell = new JTextField("0", 5);
            cell.setHorizontalAlignment(JTextField.CENTER);
            matrixPanel.add(cell);
        }

        matrixPanel.revalidate();
        matrixPanel.repaint();
    }
}

