package org.example.calculomedia.gui;

import javax.swing.*;
import java.awt.*;

public class PrincipalGUI extends JFrame {

    public PrincipalGUI() {
        super("Gerenciamento Completo de Músicas, Álbuns e Artistas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();


        tabbedPane.addTab("Músicas", new MusicaGUI());


        tabbedPane.addTab("Álbuns", new AlbumGUI());


        tabbedPane.addTab("Artistas", new ArtistaGUI()); // <-- NOVA ABA AQUI

        add(tabbedPane, BorderLayout.CENTER);
        setSize(800, 550); // Aumentei o tamanho para 3 abas
        setLocationRelativeTo(null);
    }
}