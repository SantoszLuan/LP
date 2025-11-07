package org.example.calculomedia;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class App extends JFrame {

    // Interface para criar os objetos Registravel
    private interface CriadorObjeto {
        Registravel criar(String[] campos) throws Exception;
    }

    private JTabbedPane tabbedPane;

    // Construtor da janela
    public App() {
        super("Exercicios LP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();



        // Monalisa
        tabbedPane.addTab("Monalisa", criarPainel("Salvar Personagem",
                new String[]{"Nome", "Idade", "Sexo"},
                campos -> new Personagem(campos[0], Integer.parseInt(campos[1]), campos[2])
        ));

        // Prédio
        tabbedPane.addTab("Prédio", criarPainel("Salvar Prédio",
                new String[]{"Cor", "Altura", "Andares"},
                campos -> new Predio(campos[0], Double.parseDouble(campos[1]), Integer.parseInt(campos[2]))
        ));

        // Café
        tabbedPane.addTab("Café", criarPainel("Salvar Café",
                new String[]{"Tamanho", "Posição", "Cor"},
                campos -> new Cafe(campos[0], campos[1], campos[2])
        ));

        // Elefante
        tabbedPane.addTab("Elefante", criarPainel("Salvar Elefante",
                new String[]{"Nome", "Peso", "Tamanho"},
                campos -> new Elefante(campos[0], Double.parseDouble(campos[1]), Double.parseDouble(campos[2]))
        ));

        // Música
        tabbedPane.addTab("Música", criarPainel("Salvar Música",
                new String[]{"Nome", "Estado", "Motivação"},
                campos -> new Musica(campos[0], campos[1], campos[2])
        ));

        tabbedPane.addTab("Cálculo de Média", criarPainelMedia());

        add(tabbedPane);
        setVisible(true);
    }

    // Integra o JavaFX dentro do swing
    private JPanel criarPainelMedia() {
        final JFXPanel fxPanel = new JFXPanel();


        Platform.runLater(() -> {
            try {

                Parent mediaContent = MediaCalculo.createContent();


                Scene scene = new Scene(mediaContent);
                fxPanel.setScene(scene);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao carregar conteúdo JavaFX. Verifique as dependências do JavaFX: " + e.getMessage(),
                        "Erro de Inicialização",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        JPanel container = new JPanel(new BorderLayout());
        container.add(fxPanel, BorderLayout.CENTER);
        return container;
    }


    // Cria abas de registro
    private JPanel criarPainel(String botaoTexto, String[] labels, CriadorObjeto criador) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i] + ":"));
            fields[i] = new JTextField(20);
            panel.add(fields[i]);
        }

        JButton salvarButton = new JButton(botaoTexto);

        salvarButton.addActionListener(e -> {
            try {
                String[] valores = new String[fields.length];
                for (int i = 0; i < fields.length; i++) valores[i] = fields[i].getText();

                // Cria o objeto correspondente usando a classe CriadorObjeto (que chama Monalisa, Predio, etc.)
                Registravel obj = criador.criar(valores);

                salvarEmCSV(obj);

                JOptionPane.showMessageDialog(this, botaoTexto + " com sucesso!");

                for (JTextField field : fields) {
                    field.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro de formato numérico. Verifique campos numéricos.",
                        "Erro de Entrada",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Organiza o painel
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(panel, BorderLayout.CENTER);
        container.add(salvarButton, BorderLayout.SOUTH);
        return container;
    }

    // Salvar no csv
    private void salvarEmCSV(Registravel obj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.csv", true))) {
            writer.write(obj.toString());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no arquivo CSV: " + e.getMessage(), "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(App::new);
    }
}