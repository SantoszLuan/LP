package org.example.calculomedia;


public class CriadorObjeto {
package org.example;


public class CriadorObjeto {
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Classe principal — cria a interface gráfica e gerencia os cadastros
public class App extends JFrame {

    private JTabbedPane tabbedPane;

    public App() {
        super("Registro de Objetos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Personagem", criarPainel("Salvar Personagem",
                new String[]{"Nome", "Idade", "Sexo"},
                campos -> new Personagem(campos[0], Integer.parseInt(campos[1]), campos[2])
        ));

        tabbedPane.addTab("Prédio", criarPainel("Salvar Prédio",
                new String[]{"Cor", "Altura", "Andares"},
                campos -> new Predio(campos[0], Double.parseDouble(campos[1]), Integer.parseInt(campos[2]))
        ));

        tabbedPane.addTab("Café", criarPainel("Salvar Café",
                new String[]{"Tamanho", "Posição", "Cor"},
                campos -> new Cafe(campos[0], campos[1], campos[2])
        ));

        tabbedPane.addTab("Elefante", criarPainel("Salvar Elefante",
                new String[]{"Nome", "Peso", "Tamanho"},
                campos -> new Elefante(campos[0], Double.parseDouble(campos[1]), Double.parseDouble(campos[2]))
        ));

        tabbedPane.addTab("Música", criarPainel("Salvar Música",
                new String[]{"Nome", "Estado", "Motivação"},
                campos -> new Musica(campos[0], campos[1], campos[2])
        ));

        add(tabbedPane);
        setVisible(true);
    }

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

                Registravel obj = criador.criar(valores);
                salvarEmCSV(obj);

                JOptionPane.showMessageDialog(this, botaoTexto + " com sucesso!");
                for (JTextField field : fields) field.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);
        container.add(salvarButton, BorderLayout.SOUTH);
        return container;
    }

    private void salvarEmCSV(Registravel obj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.csv", true))) {
            writer.write(obj.toString());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no arquivo CSV.", "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}

}

}
