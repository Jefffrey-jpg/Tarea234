package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Yape {
    private static final int MAX_DIGITS = 6;
    private static StringBuilder inputDigits = new StringBuilder();
    private static ArrayList<String> botones = new ArrayList<>();
    private static JLabel puntosLabel;
    private static int intentosRestantes = 3;
    private static JPanel panelBotones;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Yape clave: 123456");
        frame.setSize(400, 687);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 400, 650);
        frame.add(layeredPane);

        JPanel panelFondo = new JPanel();
        panelFondo.setBounds(0, 0, 400, 650);
        panelFondo.setBackground(new Color(91, 27, 103));
        panelFondo.setLayout(null);
        layeredPane.add(panelFondo, Integer.valueOf(0));

        JLabel qrCode = new JLabel();
        try {
            Image qrImage = ImageIO.read(new File("ui\\img\\qr.png"));
            ImageIcon icon = new ImageIcon(qrImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            qrCode.setIcon(icon);
        } catch (IOException e) {
            qrCode.setText("QR CODE");
        }
        qrCode.setBounds(100, 40, 200, 200);
        panelFondo.add(qrCode);

        JLabel textoClave = new JLabel("Ingresa tu clave", SwingConstants.CENTER);
        textoClave.setBounds(50, 250, 300, 30);
        textoClave.setForeground(Color.WHITE);
        textoClave.setFont(new Font("Arial", Font.BOLD, 20));
        panelFondo.add(textoClave);

        puntosLabel = new JLabel("");
        puntosLabel.setBounds(50, 280, 300, 30);
        puntosLabel.setForeground(Color.WHITE);
        puntosLabel.setFont(new Font("Arial", Font.BOLD, 30));
        puntosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelFondo.add(puntosLabel);

        panelBotones = new JPanel();
        panelBotones.setBounds(80, 320, 240, 240);
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setLayout(new GridLayout(4, 3, 10, 10));
        panelFondo.add(panelBotones);

        String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        Collections.addAll(botones, numeros);

        agregarBotones();

        JButton borrarBoton = new JButton("←");
        borrarBoton.setFont(new Font("Arial", Font.BOLD, 20));
        borrarBoton.setBackground(new Color(230, 230, 230));
        borrarBoton.setFocusPainted(false);
        borrarBoton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        borrarBoton.setPreferredSize(new Dimension(60, 60));
        borrarBoton.setBounds(245, 505, 75, 55);

        borrarBoton.addActionListener(e -> borrar());

        layeredPane.add(borrarBoton, Integer.valueOf(1));

        JLabel textoOlvidaste = new JLabel("¿OLVIDASTE TU CLAVE?", SwingConstants.CENTER);
        textoOlvidaste.setBounds(50, 610, 300, 30);
        textoOlvidaste.setForeground(new Color(0, 200, 200));
        textoOlvidaste.setFont(new Font("Arial", Font.BOLD, 14));
        panelFondo.add(textoOlvidaste);

        frame.setVisible(true);
    }

    private static void agregarBotones() {
        panelBotones.removeAll();

        Collections.shuffle(botones);

        for (int i = 0; i < botones.size(); i++) {
            String botonTexto = botones.get(i);
            JButton boton = new JButton(botonTexto);
            boton.setFont(new Font("Arial", Font.BOLD, 20));
            boton.setBackground(new Color(230, 230, 230));
            boton.setFocusPainted(false);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            boton.setPreferredSize(new Dimension(60, 60));

            boton.addActionListener(e -> presionarBoton(boton.getText()));

            panelBotones.add(boton);
        }
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    private static void presionarBoton(String texto) {
        if (inputDigits.length() < MAX_DIGITS) {
            inputDigits.append(texto);
            actualizarPuntos();
        }

        if (inputDigits.length() == MAX_DIGITS) {
            verificarClave();
        }
    }

    private static void borrar() {
        if (inputDigits.length() > 0) {
            inputDigits.deleteCharAt(inputDigits.length() - 1);
            actualizarPuntos();
        }
    }

    private static void actualizarPuntos() {
        StringBuilder puntos = new StringBuilder();
        for (int i = 0; i < inputDigits.length(); i++) {
            puntos.append("•");
        }
        puntosLabel.setText(puntos.toString());
    }

    private static void verificarClave() {
        String clave = inputDigits.toString();
        if (clave.equals("123456")) {
            JOptionPane.showMessageDialog(null, "Acceso exitoso.");
        } else {
            intentosRestantes--;
            if (intentosRestantes == 0) {
                JOptionPane.showMessageDialog(null, "Tu cuenta ha sido bloqueada debido a 3 intentos fallidos.");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "Error al iniciar sesión: Clave incorrecta. Intentos restantes: " + intentosRestantes);
                reiniciarPantalla();
            }
        }
    }

    private static void reiniciarPantalla() {
        inputDigits.setLength(0);
        actualizarPuntos();

        agregarBotones();
    }
}
