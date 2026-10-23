
package calculator1442;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.Locale;

public class EquationForm extends JFrame {

    private final JComboBox<Integer> degreeBox =
            new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});

    private final JComboBox<Integer> variablesBox =
            new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

    private final JPanel coefficientsPanel =
            new JPanel(new GridLayout(0, 2, 8, 8));

    private final JTextArea resultArea = new JTextArea(6, 40);

    private final List<Term> terms = new ArrayList<>();
    private final List<JTextField> coefficientFields = new ArrayList<>();

    private static final double EPS = 1e-9;

    private static class Term {
        int[] powers;
        JLabel label;
        JTextField field;

        Term(int[] powers) {
            this.powers = powers;
        }

        int totalDegree() {
            int sum = 0;
            for (int p : powers) {
                sum += p;
            }
            return sum;
        }
    }

    public EquationForm() {
        setTitle("Equation Solver");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 600);
        setMinimumSize(new Dimension(550, 400));
        setLocationRelativeTo(null);

        JPanel settings = new JPanel(new GridLayout(2, 2, 8, 8));
        settings.setBorder(
                BorderFactory.createTitledBorder("Equation Settings"));

        settings.add(new JLabel("Maximum total degree:"));
        settings.add(degreeBox);
        settings.add(new JLabel("Number of variables:"));
        settings.add(variablesBox);

        JButton generateButton = new JButton("Generate Fields");
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear Coefficients");

        generateButton.addActionListener(e -> generateFields());
        solveButton.addActionListener(e -> solveEquation());
        clearButton.addActionListener(e -> clearCoefficients());

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(generateButton);
        buttons.add(solveButton);
        buttons.add(clearButton);

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JPanel resultPanel = new JPanel(new BorderLayout(5, 5));
        resultPanel.setBorder(
                BorderFactory.createTitledBorder("Result"));
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout(5, 5));
        center.add(new JScrollPane(coefficientsPanel), BorderLayout.CENTER);
        center.add(resultPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout(8, 8));
        add(settings, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        generateFields();
    }

    private void generateFields() {
        int degree = (Integer) degreeBox.getSelectedItem();
        int variableCount = (Integer) variablesBox.getSelectedItem();

        terms.clear();
        coefficientFields.clear();
        coefficientsPanel.removeAll();

        generateTerms(new int[variableCount], 0, degree);

        // Display higher-degree terms first.
        terms.sort(Comparator
                .comparingInt(Term::totalDegree).reversed()
                .thenComparing((a, b) -> comparePowers(b.powers, a.powers)));

        for (Term term : terms) {
            term.label = new JLabel(termName(term.powers) + " coefficient:");
            term.field = new JTextField("0", 12);

            coefficientsPanel.add(term.label);
            coefficientsPanel.add(term.field);
            coefficientFields.add(term.field);
        }

        coefficientsPanel.revalidate();
        coefficientsPanel.repaint();

        resultArea.setText(
                "Enter the coefficients, then click Solve.\n"
                + "The equation is interpreted as a polynomial equal to zero.\n"
                + "Generated terms: " + terms.size());
    }

    private int comparePowers(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }
        return 0;
    }

    // Generate every monomial whose total degree is <= the chosen degree.
    private void generateTerms(int[] powers, int index, int remaining) {
        if (index == powers.length) {
            terms.add(new Term(powers.clone()));
            return;
        }

        for (int p = remaining; p >= 0; p--) {
            powers[index] = p;
            generateTerms(powers, index + 1, remaining - p);
        }
    }

    private String variableName(int index) {
        return "x" + (index + 1);
    }

    private String termName(int[] powers) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < powers.length; i++) {
            int p = powers[i];

            if (p == 0) {
                continue;
            }

            if (sb.length() > 0) {
                sb.append("*");
            }

            sb.append(variableName(i));

            if (p > 1) {
                sb.append("^").append(p);
            }
        }

        return sb.length() == 0 ? "Constant" : sb.toString();
    }

    private double[] readCoefficients() {
        double[] values = new double[terms.size()];

        for (int i = 0; i < coefficientFields.size(); i++) {
            String input = coefficientFields.get(i).getText().trim();

            if (input.isEmpty()) {
                throw new IllegalArgumentException(
                        "A coefficient field is empty.");
            }

            try {
                values[i] = Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(
                        "Invalid coefficient: " + input);
            }

            if (!Double.isFinite(values[i])) {
                throw new IllegalArgumentException(
                        "Coefficients must be finite numbers.");
            }
        }

        return values;
    }

    private void solveEquation() {
        try {
            double[] coefficients = readCoefficients();
            int variableCount = (Integer) variablesBox.getSelectedItem();

            if (allZero(coefficients)) {
                resultArea.setText(
                        "Every coefficient is zero.\n"
                        + "The equation is 0 = 0, so every variable "
                        + "assignment satisfies it.");
                return;
            }

            String equation = formatEquation(coefficients);

            if (variableCount == 1) {
                List<Double> roots = findRealRoots(coefficients);

                StringBuilder out = new StringBuilder();
                out.append("Equation:\n")
                   .append(equation).append(" = 0\n\n");

                if (roots.isEmpty()) {
                    out.append("No real roots were detected in the search "
                            + "interval [-100, 100].\n")
                       .append("This numerical search is not a proof that "
                            + "no other real roots exist.");
                } else {
                    out.append("Approximate real roots:\n");

                    for (double root : roots) {
                        out.append("x1 ≈ ")
                           .append(String.format(Locale.US, "%.10g", root))
                           .append('\n');
                    }

                    out.append("\nRoots are approximate; the numerical "
                            + "search may miss some roots.");
                }

                resultArea.setText(out.toString());
            } else {
                solveMultivariable(coefficients, equation);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(),
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean allZero(double[] coefficients) {
        for (double c : coefficients) {
            if (Math.abs(c) > EPS) {
                return false;
            }
        }
        return true;
    }

    private String formatEquation(double[] coefficients) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (int i = 0; i < terms.size(); i++) {
            double c = coefficients[i];

            if (Math.abs(c) < EPS) {
                continue;
            }

            if (!first) {
                sb.append(c >= 0 ? " + " : " - ");
            } else if (c < 0) {
                sb.append("-");
            }

            sb.append(String.format(Locale.US, "%.6g", Math.abs(c)));

            String name = termName(terms.get(i).powers);
            if (!name.equals("Constant")) {
                sb.append("*").append(name);
            }

            first = false;
        }

        return first ? "0" : sb.toString();
    }

    private void solveMultivariable(
            double[] coefficients, String equation) {

        int variableCount = (Integer) variablesBox.getSelectedItem();
        int lastVariable = variableCount - 1;

        // A single equation generally cannot determine multiple
        // variables uniquely. Handle the linear case explicitly.
        boolean isLinear = true;
        double lastCoefficient = 0.0;

        for (int i = 0; i < terms.size(); i++) {
            if (Math.abs(coefficients[i]) < EPS) {
                continue;
            }

            Term term = terms.get(i);

            if (term.totalDegree() > 1) {
                isLinear = false;
                break;
            }

            boolean onlyLastVariable =
                    term.powers[lastVariable] == 1;

            for (int j = 0; j < variableCount; j++) {
                if (j != lastVariable && term.powers[j] != 0) {
                    onlyLastVariable = false;
                }
            }

            if (onlyLastVariable) {
                lastCoefficient += coefficients[i];
            }
        }

        StringBuilder out = new StringBuilder();
        out.append("Equation:\n")
           .append(equation).append(" = 0\n\n");

        if (isLinear && Math.abs(lastCoefficient) > EPS) {
            out.append("Explicit solution for ")
               .append(variableName(lastVariable)).append(":\n")
               .append(variableName(lastVariable)).append(" = ");

            // x_last = -(sum of all other terms) / coefficient
            StringBuilder numerator = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < terms.size(); i++) {
                double c = coefficients[i];
                Term term = terms.get(i);

                if (Math.abs(c) < EPS) {
                    continue;
                }

                boolean isLast = term.powers[lastVariable] == 1;
                for (int j = 0; j < variableCount; j++) {
                    if (j != lastVariable && term.powers[j] != 0) {
                        isLast = false;
                    }
                }

                if (isLast) {
                    continue;
                }

                if (!first) {
                    numerator.append(c >= 0 ? " + " : " - ");
                } else if (c < 0) {
                    numerator.append("-");
                }

                numerator.append(
                        String.format(Locale.US, "%.6g", Math.abs(c)));

                String name = termName(term.powers);
                if (!name.equals("Constant")) {
                    numerator.append("*").append(name);
                }

                first = false;
            }

            if (first) {
                numerator.append("0");
            }

            out.append("-(").append(numerator).append(") / ")
               .append(String.format(Locale.US, "%.6g", lastCoefficient))
               .append("\n\n")
               .append("The other variables are free parameters; "
                       + "this is not a unique numerical solution.");
        } else {
            out.append("A unique numerical solution cannot generally be "
                    + "determined from one equation with multiple variables.\n\n")
               .append("For nonlinear equations, provide a system of "
                       + "equations or specify values for the other "
                       + "variables before solving.");
        }

        resultArea.setText(out.toString());
    }

    // Numerical real-root search using sign changes and bisection.
    private List<Double> findRealRoots(double[] coefficients) {
        int maxPower = 0;

        for (Term term : terms) {
            maxPower = Math.max(maxPower, term.powers[0]);
        }

        double[] polynomial = new double[maxPower + 1];

        for (int i = 0; i < terms.size(); i++) {
            polynomial[terms.get(i).powers[0]] += coefficients[i];
        }

        while (maxPower > 0
                && Math.abs(polynomial[maxPower]) < EPS) {
            maxPower--;
        }

        List<Double> roots = new ArrayList<>();

        if (maxPower == 0) {
            return roots;
        }

        double left = -100.0;
        double step = 0.05;
        double fLeft = evaluate(polynomial, left);

        for (double right = left + step; right <= 100.0; right += step) {
            double fRight = evaluate(polynomial, right);

            if (Double.isFinite(fLeft) && Math.abs(fLeft) < 1e-7) {
                addRoot(roots, left);
            }

            if (Double.isFinite(fLeft) && Double.isFinite(fRight)
                    && ((fLeft < 0 && fRight > 0)
                    || (fLeft > 0 && fRight < 0))) {
                addRoot(roots, bisect(polynomial, left, right));
            }

            left = right;
            fLeft = fRight;
        }

        if (Math.abs(evaluate(polynomial, 100.0)) < 1e-7) {
            addRoot(roots, 100.0);
        }

        roots.sort(Double::compare);
        return roots;
    }

    private double evaluate(double[] coefficients, double x) {
        double value = 0.0;

        for (int i = coefficients.length - 1; i >= 0; i--) {
            value = value * x + coefficients[i];
        }

        return value;
    }

    private double bisect(double[] coefficients, double left, double right) {
        double fLeft = evaluate(coefficients, left);

        for (int i = 0; i < 100; i++) {
            double mid = (left + right) / 2.0;
            double fMid = evaluate(coefficients, mid);

            if (Math.abs(fMid) < 1e-12
                    || Math.abs(right - left) < 1e-12) {
                return mid;
            }

            if ((fLeft < 0 && fMid > 0)
                    || (fLeft > 0 && fMid < 0)) {
                right = mid;
            } else {
                left = mid;
                fLeft = fMid;
            }
        }

        return (left + right) / 2.0;
    }

    private void addRoot(List<Double> roots, double root) {
        for (double existing : roots) {
            if (Math.abs(existing - root) < 1e-5) {
                return;
            }
        }

        roots.add(root);
    }

    private void clearCoefficients() {
        for (JTextField field : coefficientFields) {
            field.setText("0");
        }

        resultArea.setText("Coefficients cleared.");
    }
}

