/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator1442;

import java.awt.*;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darag
 */
public class NewJFrame extends javax.swing.JFrame {
 
 DecimalFormat format = new DecimalFormat("0.###############");
 int counter = 0;
 int NumberofMarks=0;
private String st; 

private double no1 = 0.0; 
 
private double no2 = 0.0; 
 
private double total = 0.0; 
 private double totel = 0.0;
private Double Memory ; 
  private void makeResponsive() {

    // امسح أي Layout قديم
    getContentPane().removeAll();
    getContentPane().setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    /* ================= Calculator Panel ================= */

    JPanel calculatorPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbcCalc = new GridBagConstraints();
    gbcCalc.insets = new Insets(3, 3, 3, 3);
    gbcCalc.fill = GridBagConstraints.BOTH;
    gbcCalc.weightx = 1.0;
    gbcCalc.weighty = 1.0;

    // inputField (صف كامل)
    gbcCalc.gridx = 0;
    gbcCalc.gridy = 0;
    gbcCalc.gridwidth = 7;
    calculatorPanel.add(inputField, gbcCalc);

    // 🔴 مهم جدًا: رجّعها
    gbcCalc.gridwidth = 1;

    JButton[][] buttons = {
        {seven, eight, nine, div, clear, back, forward},
        {four, five, six, mul, sine, cose, tane},
        {one, two, three, minus, sqr, ex, pi},
        {zero, comma, equal, plus, pow, parentesesLeft, parentesesRight},
        {modulo, log, or, and, no, log10, exp},
        {hextodeci, tohex, decitobin, bitodec, hextobi, bitohexa, jButton1},
        {answer, sinh, cosh, tanh, asin, acos, atan},
        {fact, decitooct, bitooct, octodec, octtobi}
    };

    for (int row = 0; row < buttons.length; row++) {
        for (int col = 0; col < buttons[row].length; col++) {
            gbcCalc.gridx = col;
            gbcCalc.gridy = row + 1;
            calculatorPanel.add(buttons[row][col], gbcCalc);
        }
    }

    JScrollPane calcScroll = new JScrollPane(calculatorPanel);
    calcScroll.setBorder(null);

    /* ================= History Panel ================= */

    JPanel historyPanel = new JPanel(new BorderLayout(4, 4));
    historyPanel.setBorder(BorderFactory.createTitledBorder("History"));
    historyPanel.add(jScrollPane1, BorderLayout.CENTER);
    historyPanel.setMinimumSize(new Dimension(180, 140));

    /* ================= Stats Panel ================= */

    JPanel statsPanel = new JPanel(new GridBagLayout());
    statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
    GridBagConstraints gbcStats = new GridBagConstraints();
    gbcStats.insets = new Insets(3, 3, 3, 3);
    gbcStats.fill = GridBagConstraints.BOTH;
    gbcStats.weightx = 1.0;

    gbcStats.gridx = 0; gbcStats.gridy = 0;
    statsPanel.add(new JLabel("Count:"), gbcStats);

    gbcStats.gridx = 1;
    statsPanel.add(counto, gbcStats);

    gbcStats.gridx = 2;
    statsPanel.add(new JLabel("Sum:"), gbcStats);

    gbcStats.gridx = 3;
    statsPanel.add(sum, gbcStats);

    gbcStats.gridx = 0; gbcStats.gridy = 1;
    statsPanel.add(addomean, gbcStats);

    gbcStats.gridx = 1;
    statsPanel.add(reset, gbcStats);

    gbcStats.gridx = 2;
    statsPanel.add(calmean, gbcStats);

    gbcStats.gridx = 3;
    statsPanel.add(devia, gbcStats);

    gbcStats.gridx = 0; gbcStats.gridy = 2;
    gbcStats.gridwidth = 4;
    gbcStats.weighty = 1.0;
    statsPanel.add(jScrollPane2, gbcStats);

    



    /* ================= Add Panels to Main Window ================= */

    // Calculator takes the upper part of the window.
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 0.72;
    gbc.fill = GridBagConstraints.BOTH;
    getContentPane().add(calcScroll, gbc);

    // History and statistics are displayed side by side underneath.
    JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 8, 8));
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));
    bottomPanel.add(historyPanel);
    bottomPanel.add(statsPanel);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 0.28;
    gbc.fill = GridBagConstraints.BOTH;
    getContentPane().add(bottomPanel, gbc);

    setMinimumSize(new Dimension(800, 600));
    setSize(1100, 800);
    revalidate();
    repaint();
}
private void createMenuBar() {
    // Use the menu bar already created by initComponents().
    // This preserves the original View, Edit, and Help menus.
    JMenuBar menuBar = jMenuBar1;
    if (menuBar == null) {
        menuBar = new JMenuBar();
        jMenuBar1 = menuBar;
    }

    // Ensure the original menus are present, without adding duplicates.
    if (view != null && view.getParent() != menuBar) menuBar.add(view);
    if (edit != null && edit.getParent() != menuBar) menuBar.add(edit);
    if (help != null && help.getParent() != menuBar) menuBar.add(help);

    // Avoid duplicate custom menus if this method is called again.
    for (int i = menuBar.getMenuCount() - 1; i >= 0; i--) {
        JMenu existing = menuBar.getMenu(i);
        if (existing != null && ("Equation".equals(existing.getText()) ||
                "Matrix".equals(existing.getText()) ||
                "Calculus".equals(existing.getText()))) {
            menuBar.remove(i);
        }
    }

    JMenu equationMenu = new JMenu("Equation");
    JMenuItem equationItem = new JMenuItem("Equation Solver");
    equationItem.addActionListener(e -> {
        EquationForm form = new EquationForm();
        form.setVisible(true);
    });
    equationMenu.add(equationItem);

    JMenu matrixMenu = new JMenu("Matrix");
    JMenuItem matrixItem = new JMenuItem("Matrix Calculator");
    matrixItem.addActionListener(e -> {
        MatrixForm form = new MatrixForm();
        form.setVisible(true);
    });
    matrixMenu.add(matrixItem);

    menuBar.add(equationMenu);
    menuBar.add(matrixMenu);
    setJMenuBar(menuBar);
}

private void addCalculusMenu() {
    JMenuBar bar = getJMenuBar();
    if (bar == null) {
        bar = (jMenuBar1 != null) ? jMenuBar1 : new JMenuBar();
        setJMenuBar(bar);
    }

    // Do not add Calculus more than once.
    for (int i = 0; i < bar.getMenuCount(); i++) {
        JMenu menu = bar.getMenu(i);
        if (menu != null && "Calculus".equals(menu.getText())) return;
    }

    JMenu calculusMenu = new JMenu("Calculus");
    JMenuItem derivativeItem = new JMenuItem("Differentiation");
    JMenuItem integralItem = new JMenuItem("Integration");

    derivativeItem.addActionListener(e ->
        JOptionPane.showMessageDialog(this, "Differentiation selected")
    );
    integralItem.addActionListener(e ->
        JOptionPane.showMessageDialog(this, "Integration selected")
    );

    calculusMenu.add(derivativeItem);
    calculusMenu.add(integralItem);
    bar.add(calculusMenu);
    bar.revalidate();
    bar.repaint();
}

 private void Calculation(int n,double in[])
{
       	double sum=0;  
	for(int i=0;i<n;i++) 
	{
		sum=sum+in[i];
	}
	double mean=sum/n;
       	System.out.println("Mean :"+mean);
	sum=0;
	for(int i=0;i<n;i++) 
	{
		sum+=Math.pow((in[i]-mean),2);
	}
	mean=sum/(n-1);
	double deviation=Math.sqrt(mean);
	System.out.println("standard deviation :"+deviation);
}

private int factorial(int n){    
  if (n == 0)    
    return 1;    
  else    
    return(n * factorial(n-1));    
 }


private int convertoctaltodecimal(int octal)
{
    int decimalNumber=0, i=0;
    while(octal!=0){
        decimalNumber +=(octal%10)*Math.pow(8,i);
        ++i;
        octal/=10;
    }
    return decimalNumber;
}
 private double calculate(String str) {
 
        return new Object() {
            int pos = -1, ch;
 
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
 
            boolean eat(int charToEat) {
                while (ch == ' ') {
                    nextChar();
                }
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
 
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }
 
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm();
                    }
                    else if (eat('-')) {
                        x -= parseTerm();
                    }
                    else {
                        return x;
                    }
                }
            }
 
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('×')) {
                        x *= parseFactor();
                    }
                    else if (eat('÷')) {
                        x /= parseFactor();
                    }
                    else if (eat('%')) {
                        x %= parseFactor();
                    }
                    else {
                        return x;
                    }
                }
            }
 
            double parseFactor() {
                if (eat('+')) {
                    return parseFactor();
                }
                if (eat('-')) {
                    return -parseFactor();
                }
                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                }
                else if (eat('e')) {
                    x = Math.E;
                }
                else if (eat('π')) {
                    x = Math.PI;
                }
                else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        nextChar();
                    }
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                }
                else if (ch >= 'a' && ch <= 'z' || ch == '√') {
                    while (ch >= 'a' && ch <= 'z' || ch == '√') {
                        nextChar();
                    }
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "√":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                }
                else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                if (eat('^')) {
                    x = Math.pow(x, parseFactor());
                }
                return x;
 
            }
 
        }.parse();
    }

public static int hextodecimal(String hex)
{int decimalvalue=0;
for(int i=0; i<hex.length();i++){
char hexchar=hex.charAt(i);
decimalvalue=decimalvalue*16+hexchartodecimal(hexchar);}
return decimalvalue;}
    public static int hexchartodecimal(char ch){
    if(ch>='A'&&ch<='F')
        return 10+ch-'A';
    else if (ch>='a'&&ch<='f')
        return 10+ch-'a';
    else
        return ch-'0';
    }
    public void sin_isClicked(){
     
     String   l=inputField.getText();
        double no1=Double.parseDouble( inputField.getText());
double x=Math.toRadians(no1);
double y=Math.round(Math.sin(x)*1000)/1000.0;
String z= Double.toString(y);
inputField.setText("sin("+l+") ="+z);
        
 
 
 
    }
 
    // ?? ???? ???? ?? ??????????? × ????? ??? ??? ?????? cos ???? ????? ?????? cos ??? ????? ??? ????
    public void cos_isClicked(){
 
     String   l=inputField.getText();
        double no1=Double.parseDouble(inputField.getText());
 
 
double x=Math.toRadians(no1);
double y=Math.round(Math.cos(x)*1000)/1000.0;
String z= Double.toString(y);
inputField.setText("cos("+l+") ="+z);
    }
 
    // ?? ???? ???? ?? ??????????? × ????? ??? ??? ?????? tan ???? ????? ?????? tan ??? ????? ??? ????
    public void tan_isClicked(){
       
        
     String   l=inputField.getText();
        double no1;
         no1 = Double.parseDouble(inputField.getText());
double x=Math.toRadians(no1);
double y=Math.sin(x);
double z=Math.cos(x);
double r=y/z;
if(r!=0){
    String f=Double.toString(r);
    inputField.setText("tan("+l+") ="+f);
}
else{
JOptionPane.showMessageDialog(null,"infinty");
 
 
    }   
 
     
        
    }
    public void sqrt_isClicked(){
        autoAddOrRemove("symbol");
        inputField.setText(inputField.getText() + "v");
    }
 
    // ?? ???? ???? ?? ??????????? × ???? ??? ??? ?????? e ???? ????? e ??? ????? ??? ????
    public void exponential_isClicked(){
        autoAddOrRemove("symbol");
        inputField.setText(inputField.getText() + "e");
    }
 
    // ?? ???? ???? ?? ??????????? × ???? ??? ??? ?????? p ???? ????? p ??? ????? ??? ????
    public void pi_isClicked(){
        autoAddOrRemove("symbol");
        inputField.setText(inputField.getText() + "π");
    }
      public void parentesesLeft_isClicked(){
        autoAddOrRemove("symbol");
        inputField.setText(inputField.getText() + "(");
    }
       public void parentesesRight_isClicked(){
        autoAddOrRemove("symbol");
        inputField.setText(inputField.getText() + ")");
    }
 
    // ??? ????? ??? ?? ?????? ( ?? ??????? ) ???? ????? ???? ?? ???? ???? ?? ????? 0 ????? ?? ??? ??? ???? ???? ??????
    public void comma_isClicked(){
        String str = inputField.getText();
        if (inputField.getText().isEmpty()) {
            inputField.setText("0.");
        }
        else {
            int lastPointIndex = str.lastIndexOf(".");
            int lastPlusIndex = str.lastIndexOf("+");
            int lastMinusIndex = str.lastIndexOf("-");
            int lastMultipleIndex = str.lastIndexOf("×");
            int lastDivideIndex = str.lastIndexOf("÷");
            int lastModuloIndex = str.lastIndexOf("%");
 
            if (lastPointIndex <= lastPlusIndex ||
                lastPointIndex <= lastMinusIndex ||
                lastPointIndex <= lastMultipleIndex ||
                lastPointIndex <= lastDivideIndex ||
                lastPointIndex <= lastModuloIndex ) {
                    autoAddOrRemove("point");
                    inputField.setText(inputField.getText() + ".");
            }
        }
    }
    public void back_isClicked(){
        String temp = inputField.getText();
        if (temp.equals("Error") || temp.equals("cannot divide by 0")) {
            inputField.setText("");
        }
        else if (!temp.isEmpty()) {
            temp = inputField.getText().substring(0, inputField.getText().length() - 1);
            if (temp.length() >= 2) {
                switch (temp.substring(temp.length() - 2)) {
                    case "co":
                    case "si":
                    case "ta":
                        temp = temp.substring(0, temp.length() - 2);
                        break;
                }
            }
            inputField.setText(temp);
        }
    }
    public  void autoAddOrRemove(String button) {
 
       
        if (!inputField.getText().isEmpty()) {
            Character lastCharacter = inputField.getText().charAt(inputField.getText().length() - 1);
 
            switch (button) {
 
               
                case "symbol":
                    switch (lastCharacter) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case 'e':
                        case 'p':
                            inputField.setText(inputField.getText() + "×");
                            break;
                        case '.':
                            inputField.setText(inputField.getText() + "0×");
                            break;
                    }
                    break;
 
                // ???? ????? ????? × ???? ,p ?? e ?? lastCharacter ??? ?? ????? ??? ??? ? ??? ????? ?????? ?? ??????
                // ????? ?? 0 ?? ???? ?? ???? ???? ??? lastCharacter ? ??? ?? ????? ??? ??? ? ??? ??? ??? ???? ?? ??????
                case "number":
                    switch (lastCharacter) {
                        case 'e':
                        case 'p':
                            inputField.setText(inputField.getText() + "×");
                            break;
                        case '0':
                            String str = inputField.getText();
                            if ( str.equals("0") ||
                                 str.endsWith("+0") ||
                                 str.endsWith("-0") ||
                                 str.endsWith("×0") ||
                                 str.endsWith("÷0") ||
                                 str.endsWith("%0") ||
                                 str.endsWith("^0") ||
                                 str.endsWith("v0") ||
                                 str.endsWith("(0") ||
                                 str.endsWith("cos0") ||
                                 str.endsWith("sin0") ||
                                 str.endsWith("tan0") )
                                inputField.setText(inputField.getText().substring(0, inputField.getText().length()-1));
                        break;
                    }
                break;
 
                // ?? ??? ??????? ??????? ?? ???? ???? ???? lastCharacter ??? ?? ????? ??? ???? ? ??? ????? ?????? ?? ??????
                // ???? ??????? ?? ?????? ???????? ??? ???? ?? ?????? ???? ??? ?? ??? ???? ??????? ??? ????
                case "operand":
                    switch (lastCharacter) {
                        case '+':
                        case '-':
                        case '×':
                        case '÷':
                        case '%':
                        case '.':
                        inputField.setText(inputField.getText().substring(0, inputField.getText().length()-1));
                        break;
                    }
                break;
 
                // ?? ??? ?????????? ??????? lastCharacter ??? ?? ????? ??? ?????? ? ??? ????? ?????? ?? ??????
                // ???? ????? 0 ?? 0× ?????, ?? ??? ?????? ????? ??? ???? ?????? ???? ???
                case "point":
                    switch (lastCharacter) {
                        case '+':
                        case '-':
                        case '×':
                        case '÷':
                        case '%':
                        case '(':
                        case 'v':
                        case 'p':
                        case 's':
                        case 'n':
                        case '^':
                            inputField.setText(inputField.getText() + "0");
                            break;
                        case ')':
                            inputField.setText(inputField.getText() + "×0");
                            break;
                        case '.':
                            inputField.setText(inputField.getText().substring(0, inputField.getText().length()-1));
                            break;
                    }
                break;
 
            }
        }
    }
 
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        makeResponsive();
        createMenuBar();
        addCalculusMenu();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
 
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        inputField = new javax.swing.JTextField();
        one = new javax.swing.JButton();
        two = new javax.swing.JButton();
        three = new javax.swing.JButton();
        four = new javax.swing.JButton();
        five = new javax.swing.JButton();
        six = new javax.swing.JButton();
        seven = new javax.swing.JButton();
        eight = new javax.swing.JButton();
        nine = new javax.swing.JButton();
        zero = new javax.swing.JButton();
        plus = new javax.swing.JButton();
        minus = new javax.swing.JButton();
        mul = new javax.swing.JButton();
        div = new javax.swing.JButton();
        equal = new javax.swing.JButton();
        pow = new javax.swing.JButton();
        sine = new javax.swing.JButton();
        cose = new javax.swing.JButton();
        tane = new javax.swing.JButton();
        sqr = new javax.swing.JButton();
        comma = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        back = new javax.swing.JButton();
        forward = new javax.swing.JButton();
        or = new javax.swing.JButton();
        log = new javax.swing.JButton();
        and = new javax.swing.JButton();
        no = new javax.swing.JButton();
        parentesesLeft = new javax.swing.JButton();
        parentesesRight = new javax.swing.JButton();
        modulo = new javax.swing.JButton();
        ex = new javax.swing.JButton();
        pi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyText = new javax.swing.JTextArea();
        plns = new javax.swing.JButton();
        log10 = new javax.swing.JButton();
        exp = new javax.swing.JButton();
        hextodeci = new javax.swing.JButton();
        tohex = new javax.swing.JButton();
        decitobin = new javax.swing.JButton();
        bitodec = new javax.swing.JButton();
        hextobi = new javax.swing.JButton();
        bitohexa = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        answer = new javax.swing.JButton();
        sinh = new javax.swing.JButton();
        cosh = new javax.swing.JButton();
        tanh = new javax.swing.JButton();
        asin = new javax.swing.JButton();
        acos = new javax.swing.JButton();
        atan = new javax.swing.JButton();
        fact = new javax.swing.JButton();
        decitooct = new javax.swing.JButton();
        bitooct = new javax.swing.JButton();
        octodec = new javax.swing.JButton();
        octtobi = new javax.swing.JButton();
        calmean = new javax.swing.JButton();
        counto = new javax.swing.JTextField();
        sum = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        addomean = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        devia = new javax.swing.JButton();
      
        jMenuBar1 = new javax.swing.JMenuBar();
        view = new javax.swing.JMenu();
        history = new javax.swing.JCheckBoxMenuItem();
        exit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        copy = new javax.swing.JMenuItem();
        paste = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        copyHistory = new javax.swing.JMenuItem();
        clearHistory = new javax.swing.JMenuItem();
        printHistory = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        jButton3.setText("jButton3");

        jToggleButton1.setText("jToggleButton1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem4.setText("jMenuItem4");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu4.setText("jMenu4");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });
        getContentPane().add(inputField, new java.awt.GridBagConstraints());

        one.setText("1");
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }
        });
        getContentPane().add(one, new java.awt.GridBagConstraints());

        two.setText("2");
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }
        });
        getContentPane().add(two, new java.awt.GridBagConstraints());

        three.setText("3");
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }
        });
        getContentPane().add(three, new java.awt.GridBagConstraints());

        four.setText("4");
        four.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourActionPerformed(evt);
            }
        });
        getContentPane().add(four, new java.awt.GridBagConstraints());

        five.setText("5");
        five.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiveActionPerformed(evt);
            }
        });
        getContentPane().add(five, new java.awt.GridBagConstraints());

        six.setText("6");
        six.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixActionPerformed(evt);
            }
        });
        getContentPane().add(six, new java.awt.GridBagConstraints());

        seven.setText("7");
        seven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sevenActionPerformed(evt);
            }
        });
        getContentPane().add(seven, new java.awt.GridBagConstraints());

        eight.setText("8");
        eight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightActionPerformed(evt);
            }
        });
        getContentPane().add(eight, new java.awt.GridBagConstraints());

        nine.setText("9");
        nine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nineActionPerformed(evt);
            }
        });
        getContentPane().add(nine, new java.awt.GridBagConstraints());

        zero.setText("0");
        zero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zeroActionPerformed(evt);
            }
        });
        getContentPane().add(zero, new java.awt.GridBagConstraints());

        plus.setText("+");
        plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusActionPerformed(evt);
            }
        });
        getContentPane().add(plus, new java.awt.GridBagConstraints());

        minus.setText("-");
        minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusActionPerformed(evt);
            }
        });
        getContentPane().add(minus, new java.awt.GridBagConstraints());

        mul.setText("*");
        mul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mulActionPerformed(evt);
            }
        });
        getContentPane().add(mul, new java.awt.GridBagConstraints());

        div.setText("/");
        div.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divActionPerformed(evt);
            }
        });
        getContentPane().add(div, new java.awt.GridBagConstraints());

        equal.setText("=");
        equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equalActionPerformed(evt);
            }
        });
        getContentPane().add(equal, new java.awt.GridBagConstraints());

        pow.setText("p");
        pow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powActionPerformed(evt);
            }
        });
        getContentPane().add(pow, new java.awt.GridBagConstraints());

        sine.setText("sin");
        sine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sineActionPerformed(evt);
            }
        });
        getContentPane().add(sine, new java.awt.GridBagConstraints());

        cose.setText("cos");
        cose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coseActionPerformed(evt);
            }
        });
        getContentPane().add(cose, new java.awt.GridBagConstraints());

        tane.setText("tan");
        tane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taneActionPerformed(evt);
            }
        });
        getContentPane().add(tane, new java.awt.GridBagConstraints());

        sqr.setText("√");
        sqr.setPreferredSize(new java.awt.Dimension(46, 23));
        sqr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqrActionPerformed(evt);
            }
        });
        getContentPane().add(sqr, new java.awt.GridBagConstraints());

        comma.setText(".");
        comma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commaActionPerformed(evt);
            }
        });
        getContentPane().add(comma, new java.awt.GridBagConstraints());

        clear.setText("CE");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        getContentPane().add(clear, new java.awt.GridBagConstraints());

        back.setText("<-");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new java.awt.GridBagConstraints());

        forward.setText("->");
        getContentPane().add(forward, new java.awt.GridBagConstraints());

        or.setText("|");
        or.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orActionPerformed(evt);
            }
        });
        getContentPane().add(or, new java.awt.GridBagConstraints());

        log.setText("log");
        log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logActionPerformed(evt);
            }
        });
        getContentPane().add(log, new java.awt.GridBagConstraints());

        and.setText("&");
        and.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andActionPerformed(evt);
            }
        });
        getContentPane().add(and, new java.awt.GridBagConstraints());

        no.setText("~");
        no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noActionPerformed(evt);
            }
        });
        getContentPane().add(no, new java.awt.GridBagConstraints());

        parentesesLeft.setText("(");
        parentesesLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentesesLeftActionPerformed(evt);
            }
        });
        getContentPane().add(parentesesLeft, new java.awt.GridBagConstraints());

        parentesesRight.setText(")");
        parentesesRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentesesRightActionPerformed(evt);
            }
        });
        getContentPane().add(parentesesRight, new java.awt.GridBagConstraints());

        modulo.setText("%");
        modulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduloActionPerformed(evt);
            }
        });
        getContentPane().add(modulo, new java.awt.GridBagConstraints());

        ex.setText("e");
        ex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exActionPerformed(evt);
            }
        });
        getContentPane().add(ex, new java.awt.GridBagConstraints());

        pi.setText("b");
        pi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piActionPerformed(evt);
            }
        });
        getContentPane().add(pi, new java.awt.GridBagConstraints());

        historyText.setColumns(20);
        historyText.setRows(5);
        jScrollPane1.setViewportView(historyText);

        getContentPane().add(jScrollPane1, new java.awt.GridBagConstraints());

        plns.setText("+-");
        getContentPane().add(plns, new java.awt.GridBagConstraints());

        log10.setText("Log10");
        log10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log10ActionPerformed(evt);
            }
        });
        getContentPane().add(log10, new java.awt.GridBagConstraints());

        exp.setText("E");
        exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expActionPerformed(evt);
            }
        });
        getContentPane().add(exp, new java.awt.GridBagConstraints());

        hextodeci.setText("hextodeci");
        hextodeci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hextodeciActionPerformed(evt);
            }
        });
        getContentPane().add(hextodeci, new java.awt.GridBagConstraints());

        tohex.setText("tohex");
        tohex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tohexActionPerformed(evt);
            }
        });
        getContentPane().add(tohex, new java.awt.GridBagConstraints());

        decitobin.setText("decitobin");
        decitobin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decitobinActionPerformed(evt);
            }
        });
        getContentPane().add(decitobin, new java.awt.GridBagConstraints());

        bitodec.setText("bitodec");
        bitodec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bitodecActionPerformed(evt);
            }
        });
        getContentPane().add(bitodec, new java.awt.GridBagConstraints());

        hextobi.setText("hextobi");
        hextobi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hextobiActionPerformed(evt);
            }
        });
        getContentPane().add(hextobi, new java.awt.GridBagConstraints());

        bitohexa.setText("bitohexa");
        bitohexa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bitohexaActionPerformed(evt);
            }
        });
        getContentPane().add(bitohexa, new java.awt.GridBagConstraints());

        jButton1.setText("save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new java.awt.GridBagConstraints());

        answer.setText("Ans");
        answer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerActionPerformed(evt);
            }
        });
        getContentPane().add(answer, new java.awt.GridBagConstraints());

        sinh.setText("sinh");
        sinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinhActionPerformed(evt);
            }
        });
        getContentPane().add(sinh, new java.awt.GridBagConstraints());

        cosh.setText("cosh");
        cosh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coshActionPerformed(evt);
            }
        });
        getContentPane().add(cosh, new java.awt.GridBagConstraints());

        tanh.setText("tanh");
        tanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanhActionPerformed(evt);
            }
        });
        getContentPane().add(tanh, new java.awt.GridBagConstraints());

        asin.setText("asin");
        asin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asinActionPerformed(evt);
            }
        });
        getContentPane().add(asin, new java.awt.GridBagConstraints());

        acos.setText("acos");
        acos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acosActionPerformed(evt);
            }
        });
        getContentPane().add(acos, new java.awt.GridBagConstraints());

        atan.setText("atan");
        atan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atanActionPerformed(evt);
            }
        });
        getContentPane().add(atan, new java.awt.GridBagConstraints());

        fact.setText("fact");
        fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factActionPerformed(evt);
            }
        });
        getContentPane().add(fact, new java.awt.GridBagConstraints());

        decitooct.setText("decitooct");
        decitooct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decitooctActionPerformed(evt);
            }
        });
        getContentPane().add(decitooct, new java.awt.GridBagConstraints());

        bitooct.setText("bitooct");
        bitooct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bitooctActionPerformed(evt);
            }
        });
        getContentPane().add(bitooct, new java.awt.GridBagConstraints());

        octodec.setText("octodec");
        octodec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                octodecActionPerformed(evt);
            }
        });
        getContentPane().add(octodec, new java.awt.GridBagConstraints());

        octtobi.setText("octtobi");
        octtobi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                octtobiActionPerformed(evt);
            }
        });
        getContentPane().add(octtobi, new java.awt.GridBagConstraints());

        calmean.setText("calmean");
        calmean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calmeanActionPerformed(evt);
            }
        });
        getContentPane().add(calmean, new java.awt.GridBagConstraints());

        counto.setText("0");
        getContentPane().add(counto, new java.awt.GridBagConstraints());

        sum.setText("0");
        getContentPane().add(sum, new java.awt.GridBagConstraints());

        jScrollPane2.setViewportView(jList1);

        getContentPane().add(jScrollPane2, new java.awt.GridBagConstraints());

        addomean.setText("addomean");
        addomean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addomeanActionPerformed(evt);
            }
        });
        getContentPane().add(addomean, new java.awt.GridBagConstraints());

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        getContentPane().add(reset, new java.awt.GridBagConstraints());

        devia.setText("devia");
        devia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deviaActionPerformed(evt);
            }
        });
        getContentPane().add(devia, new java.awt.GridBagConstraints());



        view.setText("View");

        history.setSelected(true);
        history.setText("History");
        history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyActionPerformed(evt);
            }
        });
        view.add(history);

        exit.setText("exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        view.add(exit);

        jMenuBar1.add(view);

        edit.setText("Edit");

        copy.setText("copy");
        copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyActionPerformed(evt);
            }
        });
        edit.add(copy);

        paste.setText("paste");
        paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteActionPerformed(evt);
            }
        });
        edit.add(paste);
        edit.add(jSeparator1);

        copyHistory.setText("copyHistory");
        copyHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyHistoryActionPerformed(evt);
            }
        });
        edit.add(copyHistory);

        clearHistory.setText("clearHistory");
        clearHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearHistoryActionPerformed(evt);
            }
        });
        edit.add(clearHistory);

        printHistory.setText("printHistory");
        printHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printHistoryActionPerformed(evt);
            }
        });
        edit.add(printHistory);

        jMenuBar1.add(edit);

        help.setText("Help");

        jMenuItem11.setText("keyboardShortcuts");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        help.add(jMenuItem11);

        jMenuItem12.setText("about");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        help.add(jMenuItem12);

        jMenuBar1.add(help);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyActionPerformed
if(history.isSelected())
                this.setSize(1200, 1400);
            else
                this.setSize(1200, 1200);
        // TODO add your handling code here:
    }//GEN-LAST:event_historyActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
 String temp = inputField.getText();
        if (temp.equals("Error") || temp.equals("cannot divide by 0")) {
            inputField.setText("");
        }
        else if (!temp.isEmpty()) {
            temp = inputField.getText().substring(0, inputField.getText().length() - 1);
            if (temp.length() >= 2) {
                switch (temp.substring(temp.length() - 2)) {
                    case "co":
                    case "si":
                    case "ta":
                        temp = temp.substring(0, temp.length() - 2);
                        break;
                }
            }
            inputField.setText(temp);}
    }//GEN-LAST:event_backActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
    inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionPerformed

    private void orActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orActionPerformed

        no1=Double.parseDouble( inputField.getText( ));
st=or.getText();
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_orActionPerformed

    private void noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noActionPerformed

        no1=Double.parseDouble(inputField.getText( ));
 
 
        if(no1==1.0){
           inputField.setText("0.0");
                String historyNewText = historyText.getText();
    historyText.setText(historyNewText +"~"+ no1+"="+"0.0"+"\n");
        }
        else if (no1==0.0){
           inputField.setText("1.0");
                  String historyNewText = historyText.getText();
    historyText.setText(historyNewText +"~"+ no1+"="+"1.0"+"\n");
        }
        else {
             inputField.setText("error");
                   String historyNewText = historyText.getText();
    historyText.setText(historyNewText + "error"+"\n");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_noActionPerformed

    private void andActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andActionPerformed
        no1=Double.parseDouble( inputField.getText( ));
st=and.getText();
inputField.setText("");
    }//GEN-LAST:event_andActionPerformed

    private void copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyActionPerformed

     inputField.selectAll();
            inputField.copy();
            inputField.setCaretPosition(inputField.getText().length());
        // TODO add your handling code here:
    }//GEN-LAST:event_copyActionPerformed

    private void pasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteActionPerformed
inputField.paste();
        // TODO add your handling code here:
    }//GEN-LAST:event_pasteActionPerformed

    private void copyHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyHistoryActionPerformed

            historyText.selectAll();
            historyText.copy();
            inputField.setCaretPosition(inputField.getText().length());
        // TODO add your handling code here:
    }//GEN-LAST:event_copyHistoryActionPerformed

    private void clearHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearHistoryActionPerformed
 historyText.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_clearHistoryActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed

       String str = "<html>"
                        + "<ul>"
                        + "<li>Press <b>v</b> to add <b>v</b>.</li>"
                        + "<li>Press <b>p</b> to add <b>p</b>.</li>"
                        + "<li>Press <b>c</b> to add <b>cos</b>.</li>"
                        + "<li>Press <b>s</b> to add <b>sin</b>.</li>"
                        + "<li>Press <b>t</b> to add <b>tan</b>.</li>"
                        + "<li>Press <b>=</b> or <b>Enter</b> to get the result</li>"
                        + "<li>Press <b>BackSpace</b> to clear last character entered.</li>"
                        + "<li>Press <b>Delete</b> to clear all characters entered.</li>"
                        + "</ul>"
                        + "<html>";
 
            JOptionPane.showMessageDialog(null, str, "Keyboard Shortcuts", JOptionPane.PLAIN_MESSAGE);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

      String str = "<html>"
                        + "<big>Advanced Scientific Calculator</big><br><br>"
                        + "<p>Prepared by <b>Mhamad Harmush- abdelrahman darrage - ahmad manaa - muhamad Hashim</b><br><br>"
                        + "If you have any comments, ideas.. just let know<br><br>"
                        + "email:   abdelrahmanmuhamad2016@gmail.com<br>"
                        + " facebook: ????????? ???? ?????  @<br><br>"
                        + "<u>Note</u><br>"
                        + "I used JDK 8 to compile the source code.<br><br><br>"
                        + "<p><i>from  harmash.com - All Rights Reserved</i></p>"
                        + "<html>";
 
            JOptionPane.showMessageDialog(null, str, "About", JOptionPane.PLAIN_MESSAGE);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusActionPerformed
no1=Double.parseDouble( inputField.getText( ));
st=plus.getText();
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_plusActionPerformed

    private void minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusActionPerformed
 no1=Double.parseDouble( inputField.getText( ));
st=minus.getText();
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_minusActionPerformed

    private void mulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mulActionPerformed
 no1=Double.parseDouble( inputField.getText( ));
st=mul.getText();
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_mulActionPerformed

    private void divActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divActionPerformed
 no1=Double.parseDouble( inputField.getText( ));
st=div.getText();
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_divActionPerformed

    private void powActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powActionPerformed
 no1=Double.parseDouble( inputField.getText( ));
st=pow.getText();
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_powActionPerformed

    private void equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalActionPerformed

            String historyNewText = historyText.getText();
           
  
       

        no2=Double.parseDouble(inputField.getText());
  char type = st.charAt(0);
switch(type){
    case '+':
     total=no1+no2;
    inputField.setText(Double.toString(total));
     historyText.setText(historyNewText + no1+"+"+no2+"="+total+"\n");
break;
     case '-':
total=no1-no2;
    inputField.setText(Double.toString(total));
    historyText.setText(historyNewText + no1+"-"+no2+"="+total+"\n");
break;
      case '*':
    total=no1*no2;
    inputField.setText(Double.toString(total));
    historyText.setText(historyNewText + no1+"*"+no2+"="+total+"\n");
break;
    case '/':
    total=no1/no2;
    inputField.setText(Double.toString(total));
    historyText.setText(historyNewText + no1+"/"+no2+"="+total+"\n");
break;
case 'p':
    total=Math.pow(no1,no2);
    inputField.setText(Double.toString(total)); 
    historyText.setText(historyNewText + no1+"power"+no2+"="+total+"\n");
        break;
    case'|':
        if(no1==0 &&no2==0){
     inputField.setText("0.0");
    historyText.setText(historyNewText + no1+"|"+no2+"="+"0.0"+"\n");
}
else if (no1==0 && no2==1){
     inputField.setText("1.0");
    historyText.setText(historyNewText + no1+"|"+no2+"="+"1.0"+"\n");
}
else if (no1==1 && no2==1){
     inputField.setText("1.0");
    historyText.setText(historyNewText + no1+"|"+no2+"="+"1.0"+"\n");
}
else if (no1==1 && no2==0){
     inputField.setText("1.0");
         
    historyText.setText(historyNewText + no1+"|"+no2+"="+"1.0"+"\n");
}
else{
      inputField.setText("error");
          
    historyText.setText(historyNewText + "error"+"\n");
}
   break;
   case'&':
       if(no1==0 &&no2==0){
     inputField.setText("0.0");
    
    historyText.setText(historyNewText + no1+"|"+no2+"="+"0.0"+"\n");
}
else if (no1==0 && no2==1){
     inputField.setText("0.0");
         
    historyText.setText(historyNewText + no1+"|"+no2+"="+"0.0"+"\n");
}
else if (no1==1 && no2==1){
     inputField.setText("1.0");
        
    historyText.setText(historyNewText + no1+"|"+no2+"="+"1.0"+"\n");
}
else if (no1==1 && no2==0){
     inputField.setText("0.0");
          
    historyText.setText(historyNewText + no1+"|"+no2+"="+"0.0"+"\n");
}
else{
      inputField.setText("error");
      
    historyText.setText(historyNewText + "error"+"\n");
}
       case'%':
            total=no1%no2;
    inputField.setText(Double.toString(total));
    historyText.setText(historyNewText + no1+"%"+no2+"="+total+"\n");
        // T
   
        
        
} 
        // TODO add your handling code here:
    }//GEN-LAST:event_equalActionPerformed

    private void zeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zeroActionPerformed
String s1= inputField.getText()+"0";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_zeroActionPerformed

    private void oneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneActionPerformed
String s1= inputField.getText()+"1";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_oneActionPerformed

    private void twoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoActionPerformed
String s1= inputField.getText()+"2";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_twoActionPerformed

    private void threeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeActionPerformed
String s1= inputField.getText()+"3";
inputField.setText(s1);
    }//GEN-LAST:event_threeActionPerformed

    private void fourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourActionPerformed
String s1= inputField.getText()+"4";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_fourActionPerformed

    private void fiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveActionPerformed
String s1= inputField.getText()+"5";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_fiveActionPerformed

    private void sixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixActionPerformed
String s1= inputField.getText()+"6";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_sixActionPerformed

    private void sevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sevenActionPerformed
String s1= inputField.getText()+"7";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_sevenActionPerformed

    private void eightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightActionPerformed
String s1= inputField.getText()+"8";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_eightActionPerformed

    private void nineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineActionPerformed
String s1= inputField.getText()+"9";
inputField.setText(s1);
        // TODO add your handling code here:
    }//GEN-LAST:event_nineActionPerformed

    private void sineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sineActionPerformed
sin_isClicked();
String historyNewText = historyText.getText();
    historyText.setText(historyNewText + inputField.getText()+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_sineActionPerformed

    private void coseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coseActionPerformed
cos_isClicked();
String historyNewText = historyText.getText();
    historyText.setText(historyNewText + inputField.getText()+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_coseActionPerformed

    private void taneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taneActionPerformed
  tan_isClicked();
  String historyNewText = historyText.getText();
    historyText.setText(historyNewText + inputField.getText()+"\n");
    }//GEN-LAST:event_taneActionPerformed

    private void commaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commaActionPerformed
comma_isClicked();
        // TODO add your handling code here:
    }//GEN-LAST:event_commaActionPerformed

    private void parentesesLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentesesLeftActionPerformed
parentesesLeft_isClicked();
        // TODO add your handling code here:
    }//GEN-LAST:event_parentesesLeftActionPerformed

    private void parentesesRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentesesRightActionPerformed
  if (inputField.getText().matches(".*[^ns√(]$")) {
            int leftParentesesCounter = 0, rightParentesesCounter = 0;
            for(char c: inputField.getText().toCharArray()) {
                if( c == '(' )
                    leftParentesesCounter++;
                else if( c == ')' )
                    rightParentesesCounter++;
            }
            if (leftParentesesCounter > rightParentesesCounter)
                inputField.setText(inputField.getText() + ")");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_parentesesRightActionPerformed

    private void exActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exActionPerformed
  String historyNewText = historyText.getText();
    total=Math.E;
    inputField.setText(Double.toString(total)); 
    historyText.setText(historyNewText + "ex ="+total+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_exActionPerformed

    private void piActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piActionPerformed
 String historyNewText = historyText.getText();
        inputField.setText(Double.toString(Math.PI)); 
historyText.setText(historyNewText + "PI ="+Math.PI+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_piActionPerformed

    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputFieldActionPerformed

    private void sqrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sqrActionPerformed
String historyNewText = historyText.getText();
no1=Double.parseDouble( inputField.getText( ));
          total= Math.sqrt(no1);
            inputField.setText(Double.toString(total)); 
       historyText.setText(historyNewText + "√ "+no1+"="+total+"\n");
// TODO add your handling code here:
    }//GEN-LAST:event_sqrActionPerformed

    private void expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expActionPerformed
no1=Double.parseDouble( inputField.getText( ));
 String historyNewText = historyText.getText();
    total=Math.exp(no1);
    inputField.setText(Double.toString(total)); 
       historyText.setText(historyNewText + "exp "+no1+"="+total+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_expActionPerformed

    private void log10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log10ActionPerformed
no1=Double.parseDouble( inputField.getText( ));
        String historyNewText = historyText.getText();
        total=Math.log10(no1);
        inputField.setText(Double.toString(total)); 
        historyText.setText(historyNewText + "log "+no1+"="+total+"\n");        // TODO add your handling code here:
    }//GEN-LAST:event_log10ActionPerformed

    private void logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logActionPerformed
no1=Double.parseDouble( inputField.getText( ));
        String historyNewText = historyText.getText();
        total=Math.log(no1);
        inputField.setText(Double.toString(total)); 
        historyText.setText(historyNewText + "log "+no1+"="+total+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_logActionPerformed

    private void hextodeciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hextodeciActionPerformed

      String no3=inputField.getText(); 
      total=hextodecimal(no3);
  String historyNewText = historyText.getText();
inputField.setText(Double.toString(total)); 
historyText.setText(historyNewText + no3+"in deci="+total+"\n");
// TODO add your handling code here:
    }//GEN-LAST:event_hextodeciActionPerformed

    private void tohexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tohexActionPerformed
no1=Integer.parseInt( inputField.getText( ));
String x= Integer.toHexString((int) no1);
inputField.setText(x);
  String historyNewText = historyText.getText();
  historyText.setText(historyNewText + no1+"in hexa="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_tohexActionPerformed

    private void decitobinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decitobinActionPerformed
no1 = Integer.parseInt(inputField.getText());
String x=Integer.toBinaryString((int) no1);
inputField.setText(x);
  String historyNewText = historyText.getText();
  historyText.setText(historyNewText + no1+"in bi="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_decitobinActionPerformed

    private void bitodecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitodecActionPerformed
String binaryString=inputField.getText();
no1=Integer.parseInt(binaryString,2);
String x=Double.toString(no1);
inputField.setText(x);
 String historyNewText = historyText.getText();
  historyText.setText(historyNewText + no1+"in dec="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_bitodecActionPerformed

    private void hextobiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hextobiActionPerformed
  
      String no3=inputField.getText(); 
      totel=hextodecimal(no3.toUpperCase());
      String x = Integer.toBinaryString((int) totel);
  String historyNewText = historyText.getText();
inputField.setText(x); 
historyText.setText(historyNewText + no3+"\t in binary="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_hextobiActionPerformed

    private void bitohexaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitohexaActionPerformed
    String binaryString=inputField.getText();
no1=Integer.parseInt(binaryString,2);
String x= Integer.toHexString((int) no1);
String y = x.toUpperCase();
inputField.setText(y);
 String historyNewText = historyText.getText();
  historyText.setText(historyNewText + binaryString+"  in hexadecimal="+y+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_bitohexaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
Memory = Double.parseDouble(inputField.getText());
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void answerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerActionPerformed
inputField.setText(Double.toString(Memory));
        // TODO add your handling code here:
    }//GEN-LAST:event_answerActionPerformed

    private void sinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinhActionPerformed
no1=Double.parseDouble( inputField.getText( ));
double x=Math.toRadians(no1);
double y=Math.sinh(x);
inputField.setText(Double.toString(y));
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "sinh "+no1+" ="+y+"\n");


        // TODO add your handling code here:
    }//GEN-LAST:event_sinhActionPerformed

    private void coshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coshActionPerformed
  no1=Double.parseDouble( inputField.getText( ));
double x=Math.toRadians(no1);
double y=Math.cosh(x);
inputField.setText(Double.toString(y));
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "cosh "+no1+" ="+y+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_coshActionPerformed

    private void tanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanhActionPerformed
no1=Double.parseDouble( inputField.getText( ));
double x=Math.toRadians(no1);
double y=Math.sinh(x);
double z=Math.cosh(x);
double r=y/z;
if(r!=0){
    String f=Double.toString(r);
    inputField.setText("tanh("+no1+") ="+f);
}
else{
JOptionPane.showMessageDialog(null,"infinty");
 
 
    }   
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "tanh "+no1+" ="+y+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_tanhActionPerformed

    private void asinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asinActionPerformed
no1=Double.parseDouble( inputField.getText( ));

double y=Math.asin(no1);
double x=Math.toDegrees(y);

inputField.setText(Double.toString(x));
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "asin "+no1+" ="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_asinActionPerformed

    private void acosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acosActionPerformed
no1=Double.parseDouble( inputField.getText( ));

double y=Math.acos(no1);
double x=Math.toDegrees(y);

inputField.setText(Double.toString(x));
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "acos "+no1+" ="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_acosActionPerformed

    private void atanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atanActionPerformed
no1=Double.parseDouble( inputField.getText( ));

double y=Math.atan(no1);
double x=Math.toDegrees(y);

inputField.setText(Double.toString(x));
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "atan "+no1+" ="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_atanActionPerformed

    private void factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factActionPerformed
int no4=Integer.parseInt( inputField.getText( ));
int totol=factorial(no4);

inputField.setText("="+totol);
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "fact "+no4+" ="+totol+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_factActionPerformed

    private void moduloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduloActionPerformed
no1=Double.parseDouble( inputField.getText( ));
st=modulo.getText();
inputField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_moduloActionPerformed

    private void decitooctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decitooctActionPerformed
 int no6=Integer.parseInt( inputField.getText( ));
        String octalString = Integer.toOctalString(no6);
    inputField.setText(octalString);
 
    /* Method 2: 
     * Writing your own logic: Here we will write
     * our own logic for decimal to octal conversion
     */
 
    // For storing remainder
    int rem;
 
    // For storing result
    String str=""; 
 
    // Digits in Octal number system
    char dig[]={'0','1','2','3','4','5','6','7'};
 
    while(no6>0)
    {
       rem=no6%8; 
       str=dig[rem]+str; 
       no6=no6/8;
    }
   
    String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "Method 1: Decimal to octal: " + octalString+"\n"+"Method 2: Decimal to octal: "+str+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_decitooctActionPerformed

    private void bitooctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitooctActionPerformed
String binaryString=inputField.getText();
int no6=Integer.parseInt(binaryString,2);
 String octalString = Integer.toOctalString(no6);
    inputField.setText(octalString);
    // For storing remainder
    int rem;
 
    // For storing result
    String str=""; 
 
    // Digits in Octal number system
    char dig[]={'0','1','2','3','4','5','6','7'};
 
    while(no6>0)
    {
       rem=no6%8; 
       str=dig[rem]+str; 
       no6=no6/8;
    }
   
    String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "Method 1: Decimal to octal: " + octalString+"\n"+"Method 2: Decimal to octal: "+str+"\n");
      
    }//GEN-LAST:event_bitooctActionPerformed

    private void octodecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_octodecActionPerformed
     int no6=Integer.parseInt( inputField.getText( ));
     int decimal =convertoctaltodecimal(no6);
     String decimalString = Integer.toString(decimal);
     inputField.setText(decimalString);
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "dec of  "+no6+" ="+decimalString+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_octodecActionPerformed

    private void octtobiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_octtobiActionPerformed
 int no6=Integer.parseInt( inputField.getText( ));
      int decimal =convertoctaltodecimal(no6);
      String x=Integer.toBinaryString((int) decimal);
       inputField.setText(x);
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "dec of  "+no6+" ="+x+"\n");
        // TODO add your handling code here:
    }//GEN-LAST:event_octtobiActionPerformed

    private void calmeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calmeanActionPerformed
double Average;
String Answer;
Average=counter/NumberofMarks;
Answer=String.format("%.1f",Average);
  inputField.setText(Answer);
String historyNewText = historyText.getText();
  historyText.setText(historyNewText + "Average ="+Answer+"\n");
   
  // TODO add your handling code here:
    }//GEN-LAST:event_calmeanActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
int c=JOptionPane.showConfirmDialog(null,"ALL System Down","System Down",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
if(c== JOptionPane.YES_OPTION){
    System.exit(0);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated
DefaultListModel Number= new DefaultListModel();
    private void addomeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addomeanActionPerformed
      int num1= Integer.parseInt(sum.getText());
      Number.addElement(num1);
      jList1.setModel(Number);
      counter=counter+num1;
     NumberofMarks=NumberofMarks+1;
     int count=jList1.getModel().getSize();
     counto.setText(Integer.toString(count));
     
//add your handling code here:
    }//GEN-LAST:event_addomeanActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
     Number.clear();
     jList1.setModel(Number);
     counter = 0;
  NumberofMarks=0;
        // TODO add your handling code here:
    }//GEN-LAST:event_resetActionPerformed

    private void deviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deviaActionPerformed
int n=Integer.parseInt(counto.getText());
 double num1=Double.parseDouble(sum.getText());
double Average;
String Answer;
Average=counter/NumberofMarks;
  int count=jList1.getModel().getSize();
 NumberofMarks=0;
double m=0;
double[]input= new double[n];
double sum=0,mean,moon;
for(int i=0;i<count;i++){
    input[i]=num1;
    m=m+input[i];
     NumberofMarks=NumberofMarks+1;
}
mean=m/NumberofMarks;
m=0;
for(int i=0;i<count;i++){
      input[i]=num1;
    m+=(input[i]-mean)*(input[i]-mean);
}
  moon=m/count;
  double deviation=Math.sqrt(moon);
  inputField.setText(Double.toString(deviation));
// TODO add your handling code here:
    }//GEN-LAST:event_deviaActionPerformed


    private void printHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printHistoryActionPerformed

     try {
         historyText.print();
// TODO add your handling code here:
     } catch (PrinterException ex) {
         Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_printHistoryActionPerformed

    /**
     * @param args the command line arguments
     */
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton acos;
    private javax.swing.JButton addomean;
    private javax.swing.JButton and;
    private javax.swing.JButton answer;
    private javax.swing.JButton asin;
    private javax.swing.JButton atan;

    private javax.swing.JButton back;
    private javax.swing.JButton bitodec;
    private javax.swing.JButton bitohexa;
    private javax.swing.JButton bitooct;
  

    private javax.swing.JButton calmean;
    private javax.swing.JButton clear;
    private javax.swing.JMenuItem clearHistory;
    private javax.swing.JButton comma;
    private javax.swing.JMenuItem copy;
    private javax.swing.JMenuItem copyHistory;
    private javax.swing.JButton cose;
    private javax.swing.JButton cosh;
    private javax.swing.JTextField counto;
    private javax.swing.JButton decitobin;
    private javax.swing.JButton decitooct;
    private javax.swing.JButton devia;
    private javax.swing.JButton div;
    private javax.swing.JMenu edit;
    private javax.swing.JButton eight;
    private javax.swing.JButton equal;
    private javax.swing.JButton ex;
    private javax.swing.JMenuItem exit;
    private javax.swing.JButton exp;
    private javax.swing.JButton fact;
    private javax.swing.JButton five;
    private javax.swing.JButton forward;
    private javax.swing.JButton four;
    private javax.swing.JMenu help;
    private javax.swing.JButton hextobi;
    private javax.swing.JButton hextodeci;
    private javax.swing.JCheckBoxMenuItem history;
    private javax.swing.JTextArea historyText;
    private javax.swing.JTextField inputField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton log;
    private javax.swing.JButton log10;
    private javax.swing.JButton minus;
    private javax.swing.JButton modulo;
    private javax.swing.JButton mul;
    private javax.swing.JButton nine;
    private javax.swing.JButton no;
    private javax.swing.JButton octodec;
    private javax.swing.JButton octtobi;
    private javax.swing.JButton one;
    private javax.swing.JButton or;
    private javax.swing.JButton parentesesLeft;
    private javax.swing.JButton parentesesRight;
    private javax.swing.JMenuItem paste;
    private javax.swing.JButton pi;
    private javax.swing.JButton plns;
    private javax.swing.JButton plus;
    private javax.swing.JButton pow;
    private javax.swing.JMenuItem printHistory;
    private javax.swing.JButton reset;
    private javax.swing.JButton seven;
    private javax.swing.JButton sine;
    private javax.swing.JButton sinh;
    private javax.swing.JButton six;
    private javax.swing.JButton sqr;
    private javax.swing.JTextField sum;
    private javax.swing.JButton tane;
    private javax.swing.JButton tanh;
    private javax.swing.JButton three;
    private javax.swing.JButton tohex;
    private javax.swing.JButton two;
    private javax.swing.JMenu view;
    private javax.swing.JButton zero;
    // End of variables declaration//GEN-END:variables
}

