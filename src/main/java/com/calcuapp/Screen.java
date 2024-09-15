package com.calcuapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The main screen of the calculator application.
 */
public final class Screen extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final String[] BUTTON_LABELS = {
            "C", "^", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", ""
    };

    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 500;
    private static final int DISPLAY_FONT_SIZE = 37;
    private static final int BUTTON_FONT_SIZE = 18;

    private static final Color BG_COLOR = new Color(0, 20, 0);
    private static final Color FG_COLOR = new Color(0, 255, 0);
    private static final Color BUTTON_COLOR = new Color(0, 40, 0);
    private static final Color OPERATOR_COLOR = new Color(0, 80, 0);

    private static final Font MONO_FONT = new Font("Consolas", Font.PLAIN, BUTTON_FONT_SIZE);
    private static final Font DISPLAY_FONT = new Font("Consolas", Font.BOLD, DISPLAY_FONT_SIZE);

    private static final String CREDITS_TEXT = "This was made by Dane Quintano, Dharmveer Sandhu, JC Paglinawan, and Jansen Moral for CS0053....";

    private final JTextField display;

    private double firstOperand;
    private double secondOperand;
    private char operator;

    private Timer marqueeTimer;
    private int marqueePosition = 0;

    public Screen() {
        super("C4LCUL4TOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BG_COLOR);
        topPanel.add(Box.createVerticalStrut(30), BorderLayout.NORTH);
        display = createDisplay();
        topPanel.add(display, BorderLayout.CENTER);
        topPanel.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);
        setVisible(true);

        initMarqueeTimer();
    }

    private JTextField createDisplay() {
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(DISPLAY_FONT);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(BG_COLOR);
        textField.setForeground(FG_COLOR);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textField.setCaretColor(FG_COLOR);
        return textField;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(BG_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        int gridY = 0;
        for (int i = 0; i < BUTTON_LABELS.length; i++) {
            String label = BUTTON_LABELS[i];
            gbc.gridx = i % 4;
            gbc.gridy = gridY;

            if (label.isEmpty()) {
                JButton equalButton = createButton("=");
                gbc.gridwidth = 2;
                panel.add(equalButton, gbc);
                gbc.gridwidth = 1; 
            } else {
                panel.add(createButton(label), gbc);
            }

            if (gbc.gridx == 3) {
                gridY++; 
            }
        }

        return panel;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(MONO_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(FG_COLOR);

        if (label.matches("[0-9.]")) {
            button.setBackground(BUTTON_COLOR);
        } else {
            button.setBackground(OPERATOR_COLOR);
        }

        button.addActionListener(createButtonListener(label));
        return button;
    }

    private ActionListener createButtonListener(String label) {
        return e -> handleButtonClick(label);
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "C":
                clearCalculator();
                break;
            case "^":
                setOperator('^');
                break;
            case "%":
                setOperator('%');
                break;
            case "=":
                calculateResult();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(label.charAt(0));
                break;
            default:
                appendToDisplay(label);
        }
    }

    private void clearCalculator() {
        display.setText("");
        firstOperand = 0;
        secondOperand = 0;
        operator = '\0';
        stopMarquee();
    }

    private void setOperator(char newOperator) {
        try {
            firstOperand = Double.parseDouble(display.getText());
            operator = newOperator;
            display.setText("");
        } catch (NumberFormatException e) {
            displaySyntaxError();
        }
    }

    private void appendToDisplay(String text) {
        if (text.equals(".") && display.getText().contains(".")) {
            return;
        }
        display.setText(display.getText() + text);
        checkEasterEgg();
    }

    private void checkEasterEgg() {
        if (display.getText().equals("69696969")) {
            startMarquee();
        }
    }

    private void calculateResult() {
        try {
            secondOperand = Double.parseDouble(display.getText());
            display.setText(performCalculation());
        } catch (NumberFormatException e) {
            displaySyntaxError();
        }
    }

    private String performCalculation() {
        switch (operator) {
            case '+':
                return String.valueOf(firstOperand + secondOperand);
            case '-':
                return String.valueOf(firstOperand - secondOperand);
            case '*':
                return String.valueOf(firstOperand * secondOperand);
            case '/':
                if (secondOperand == 0) return displayDivisionError();
                return String.valueOf(firstOperand / secondOperand);
            case '^':
                return String.valueOf(Math.pow(firstOperand, secondOperand));
            case '%':
                if (secondOperand == 0) return displayDivisionError();
                return String.valueOf(firstOperand % secondOperand);
            default:
                return display.getText();
        }
    }

    private void displaySyntaxError() {
        display.setText("SYNT4X 3RROR!");
    }

    private String displayDivisionError() {
        return "DIVI$ION 3RROR!";
    }

    private void initMarqueeTimer() {
        marqueeTimer = new Timer(200, e -> updateMarquee());
    }

    private void startMarquee() {
        marqueePosition = 11; // Start at the 11th character
        marqueeTimer.start();
    }

    private void stopMarquee() {
        marqueeTimer.stop();
    }

    private void updateMarquee() {
        marqueePosition++;
        if (marqueePosition >= CREDITS_TEXT.length()) {
            marqueePosition = 0;
        }
        display.setText(CREDITS_TEXT.substring(marqueePosition) + CREDITS_TEXT.substring(0, marqueePosition));
    }
}