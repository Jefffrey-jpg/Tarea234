package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AceHomeCenter {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ace Home Center");
        frame.setSize(750, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setBackground(new Color(156, 24, 24));

        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(new Color(156, 24, 24));
        upperPanel.setPreferredSize(new Dimension(700, 50));
        upperPanel.setLayout(new BorderLayout());
        frame.add(upperPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(156, 24, 24));
        bottomPanel.setPreferredSize(new Dimension(700, 50));
        bottomPanel.setLayout(new BorderLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);

        JPanel timePanel = new JPanel();
        timePanel.setOpaque(false);
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timePanel.add(timeLabel);

        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timePanel.add(dateLabel);

        JLabel registrarLabel = new JLabel("Registrar001");
        registrarLabel.setFont(new Font("Arial", Font.BOLD, 20));
        registrarLabel.setForeground(Color.WHITE);
        timePanel.add(registrarLabel);

        bottomPanel.add(timePanel, BorderLayout.WEST);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                Date date = new Date();
                timeLabel.setText(timeFormat.format(date));
                timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
                timeLabel.setForeground(Color.WHITE);;
                dateLabel.setText(dateFormat.format(date));
                dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
                dateLabel.setForeground(Color.WHITE);;
            }
        });
        timer.start();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.setPreferredSize(new Dimension(400, 400));
        leftPanel.setBackground(new Color(156, 24, 24));

        JPanel upperLeftPanel = new JPanel();
        upperLeftPanel.setBackground(new Color(156, 24, 24));
        upperLeftPanel.setPreferredSize(new Dimension(400, 200));

        ImageIcon originalImageIcon = new ImageIcon("ui\\img\\maestro.png");
        Image originalImage = originalImageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledImageIcon);
        upperLeftPanel.add(imageLabel);
        leftPanel.add(upperLeftPanel);

        JPanel lowerLeftPanel = new JPanel();
        lowerLeftPanel.setBackground(new Color(156, 24, 24));
        lowerLeftPanel.setPreferredSize(new Dimension(400, 200));
        lowerLeftPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JLabel idLabel = new JLabel("ID del Operador:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 15));
        idLabel.setForeground(Color.WHITE);
        JTextField idField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField(20);

        idLabel.setBorder(BorderFactory.createEmptyBorder(0, 12, 5, 50));
        idField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 12, 5, 50));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 50));

        idField.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (getLength() + str.length() <= 6) {
                    super.insertString(offs, str, a);
                }
            }
        });

        passwordField.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (getLength() + str.length() <= 4) {
                    super.insertString(offs, str, a);
                }
            }
        });

        lowerLeftPanel.add(idLabel);
        lowerLeftPanel.add(idField);
        lowerLeftPanel.add(passwordLabel);
        lowerLeftPanel.add(passwordField);

        leftPanel.add(lowerLeftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 4, 5, 5));
        rightPanel.setBackground(new Color(156, 24, 24));
        rightPanel.setPreferredSize(new Dimension(400, 300));

        String[] buttonLabels = {
            "1", "2", "3", "Cancelar",
            "4", "5", "6", "Borrar",
            "7", "8", "9", "Atrás",
            "", "0", "Entrar", ""
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(50, 50));
            button.setFocusPainted(false);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);
            button.setBackground(Color.WHITE);
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = button.getText();
                    if (command.matches("[0-9]")) {
                        if (idField.getText().length() < 6) {
                            idField.setText(idField.getText() + command);
                        } else if (passwordField.getPassword().length < 4) {
                            char[] password = passwordField.getPassword();
                            passwordField.setText(new String(password) + command);
                        }
                    } else if (command.equals("Borrar")) {
                        if (passwordField.getPassword().length > 0) {
                            char[] password = passwordField.getPassword();
                            passwordField.setText(new String(password, 0, password.length - 1));
                        } else if (idField.getText().length() > 0) {
                            idField.setText(idField.getText().substring(0, idField.getText().length() - 1));
                        }
                    } else if (command.equals("Cancelar")) {
                        idField.setText("");
                        passwordField.setText("");
                    } else if (command.equals("Atrás")) {
                        int option = JOptionPane.showConfirmDialog(frame, "¿Estás seguro que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            frame.dispose();
                        }
                    } else if (command.equals("Entrar")) {
                        String id = idField.getText();
                        String password = new String(passwordField.getPassword());

                        if ("123456".equals(id) && "1234".equals(password)) {
                            JOptionPane.showMessageDialog(frame, "Acceso exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            rightPanel.add(button);
        }

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
