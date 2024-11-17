package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField display;
    private double numeroActual = 0;
    private String operadorActual = "";
    private boolean nuevoNumero = true;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(383, 587);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 230));
        add(panel);

        display = new JTextField();
        display.setBounds(10, 10, 350, 80);
        display.setFont(new Font("Consolas", Font.PLAIN, 32));
        display.setForeground(Color.BLACK);
        display.setBackground(new Color(230, 230, 230));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        panel.add(display);

        String[] buttons = {
            "C", "±", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "−",
            "1", "2", "3", "+",
            "0", ".", "="
        };

        int x = 10, y = 100;
        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton(buttons[i]);
            button.setBounds(x, y, i == 16 ? 170 : 80, 80);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setForeground(Color.BLACK);
            button.setBackground(new Color(250, 250, 250));
            button.setFocusPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(e.getActionCommand());
                }
            });

            panel.add(button);
            x += i == 16 ? 180 : 90;

            if ((i + 1) % 4 == 0 && i != 16) {
                x = 10;
                y += 90;
            }
        }
    }

    private void handleButtonClick(String comando) {
        if ("0123456789.".contains(comando)) {
            if (nuevoNumero) {
                display.setText(comando);
                nuevoNumero = false;
            } else {
                display.setText(display.getText() + comando);
            }
        } else if ("C".equals(comando)) {
            display.setText("0");
            numeroActual = 0;
            operadorActual = "";
            nuevoNumero = true;
        } else if ("±".equals(comando)) {
            double value = Double.parseDouble(display.getText());
            display.setText(String.valueOf(-value));
        } else if ("%".equals(comando)) {
            double value = Double.parseDouble(display.getText());
            display.setText(String.valueOf(value / 100));
        } else if ("=".equals(comando)) {
            calculate(Double.parseDouble(display.getText()));
            operadorActual = "";
            nuevoNumero = true;
        } else {
            if (!operadorActual.isEmpty()) {
                calculate(Double.parseDouble(display.getText()));
            } else {
                numeroActual = Double.parseDouble(display.getText());
            }
            operadorActual = comando;
            nuevoNumero = true;
        }
    }

    private void calculate(double segundoNumero) {
        switch (operadorActual) {
            case "+":
                numeroActual += segundoNumero;
                break;
            case "−":
                numeroActual -= segundoNumero;
                break;
            case "×":
                numeroActual *= segundoNumero;
                break;
            case "÷":
                if (segundoNumero != 0) {
                    numeroActual /= segundoNumero;
                } else {
                    display.setText("Error");
                    operadorActual = "";
                    nuevoNumero = true;
                    return;
                }
                break;
        }
        display.setText(String.valueOf(numeroActual));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Calculadora calculator = new Calculadora();
            calculator.setVisible(true);
        });
    }
}