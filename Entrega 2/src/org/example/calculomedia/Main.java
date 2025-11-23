package org.example.calculomedia;

import org.example.calculomedia.gui.PrincipalGUI; // Chama a nova GUI
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando...");

        SwingUtilities.invokeLater(() -> {
            new PrincipalGUI().setVisible(true);
        });
    }
}