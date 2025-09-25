import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// =======================================================================
// Classes do Usuário
// =======================================================================

// Classe Personagem
class Personagem {
    private String nome;
    private int idade;
    private String sexo;

    public Personagem(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getSexo() { return sexo; }

    @Override
    public String toString() {
        return "Personagem," + nome + "," + idade + "," + sexo;
    }
}

// Classe Predio
class Predio {
    private String cor;
    private double altura;
    private int andares;

    public Predio(String cor, double altura, int andares) {
        this.cor = cor;
        this.altura = altura;
        this.andares = andares;
    }

    public String getCor() { return cor; }
    public double getAltura() { return altura; }
    public int getAndares() { return andares; }

    @Override
    public String toString() {
        return "Predio," + cor + "," + altura + "," + andares;
    }
}

// Classe Cafe
class Cafe {
    private String tamanho;
    private String posicao;
    private String cor;

    public Cafe(String tamanho, String posicao, String cor) {
        this.tamanho = tamanho;
        this.posicao = posicao;
        this.cor = cor;
    }

    public String getTamanho() { return tamanho; }
    public String getPosicao() { return posicao; }
    public String getCor() { return cor; }

    @Override
    public String toString() {
        return "Cafe," + tamanho + "," + posicao + "," + cor;
    }
}

// Classe Elefante
class Elefante {
    private String nome;
    private double peso;
    private double tamanho;

    public Elefante(String nome, double peso, double tamanho) {
        this.nome = nome;
        this.peso = peso;
        this.tamanho = tamanho;
    }

    public String getNome() { return nome; }
    public double getPeso() { return peso; }
    public double getTamanho() { return tamanho; }

    @Override
    public String toString() {
        return "Elefante," + nome + "," + peso + "," + tamanho;
    }
}

// Classe Musica
class Musica {
    private String nome;
    private String estado;
    private String motivacao;

    public Musica(String nome, String estado, String motivacao) {
        this.nome = nome;
        this.estado = estado;
        this.motivacao = motivacao;
    }

    public String getNome() { return nome; }
    public String getEstado() { return estado; }
    public String getMotivacao() { return motivacao; }

    @Override
    public String toString() {
        return "Musica," + nome + "," + estado + "," + motivacao;
    }
}

// =======================================================================
// Interface Gráfica Unificada
// =======================================================================

public class App extends JFrame {

    private JTabbedPane tabbedPane;

    public App() {
        super("Registro de Objetos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Personagem", criarPainelPersonagem());
        tabbedPane.addTab("Prédio", criarPainelPredio());
        tabbedPane.addTab("Café", criarPainelCafe());
        tabbedPane.addTab("Elefante", criarPainelElefante());
        tabbedPane.addTab("Música", criarPainelMusica());

        this.add(tabbedPane);
        this.setVisible(true);
    }

    private JPanel criarPainelPersonagem() {
        JTextField nomeField = new JTextField(20);
        JTextField idadeField = new JTextField(20);
        JTextField sexoField = new JTextField(20);
        JButton salvarButton = new JButton("Salvar Personagem");

        salvarButton.addActionListener(e -> {
            try {
                Personagem p = new Personagem(nomeField.getText(), Integer.parseInt(idadeField.getText()), sexoField.getText());
                salvarEmCSV(p);
                JOptionPane.showMessageDialog(this, "Personagem salvo com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "A idade deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return criarPainelComCampos(salvarButton, new JLabel("Nome:"), nomeField, new JLabel("Idade:"), idadeField, new JLabel("Sexo:"), sexoField);
    }

    private JPanel criarPainelPredio() {
        JTextField corField = new JTextField(20);
        JTextField alturaField = new JTextField(20);
        JTextField andaresField = new JTextField(20);
        JButton salvarButton = new JButton("Salvar Prédio");

        salvarButton.addActionListener(e -> {
            try {
                Predio p = new Predio(corField.getText(), Double.parseDouble(alturaField.getText()), Integer.parseInt(andaresField.getText()));
                salvarEmCSV(p);
                JOptionPane.showMessageDialog(this, "Prédio salvo com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique os tipos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return criarPainelComCampos(salvarButton, new JLabel("Cor:"), corField, new JLabel("Altura:"), alturaField, new JLabel("Andares:"), andaresField);
    }

    private JPanel criarPainelCafe() {
        JTextField tamanhoField = new JTextField(20);
        JTextField posicaoField = new JTextField(20);
        JTextField corField = new JTextField(20);
        JButton salvarButton = new JButton("Salvar Café");

        salvarButton.addActionListener(e -> {
            Cafe c = new Cafe(tamanhoField.getText(), posicaoField.getText(), corField.getText());
            salvarEmCSV(c);
            JOptionPane.showMessageDialog(this, "Café salvo com sucesso!");
        });

        return criarPainelComCampos(salvarButton, new JLabel("Tamanho:"), tamanhoField, new JLabel("Posição:"), posicaoField, new JLabel("Cor:"), corField);
    }

    private JPanel criarPainelElefante() {
        JTextField nomeField = new JTextField(20);
        JTextField pesoField = new JTextField(20);
        JTextField tamanhoField = new JTextField(20);
        JButton salvarButton = new JButton("Salvar Elefante");

        salvarButton.addActionListener(e -> {
            try {
                Elefante eObj = new Elefante(nomeField.getText(), Double.parseDouble(pesoField.getText()), Double.parseDouble(tamanhoField.getText()));
                salvarEmCSV(eObj);
                JOptionPane.showMessageDialog(this, "Elefante salvo com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Peso e tamanho devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        return criarPainelComCampos(salvarButton, new JLabel("Nome:"), nomeField, new JLabel("Peso:"), pesoField, new JLabel("Tamanho:"), tamanhoField);
    }

    private JPanel criarPainelMusica() {
        JTextField nomeField = new JTextField(20);
        JTextField estadoField = new JTextField(20);
        JTextField motivacaoField = new JTextField(20);
        JButton salvarButton = new JButton("Salvar Música");

        salvarButton.addActionListener(e -> {
            Musica m = new Musica(nomeField.getText(), estadoField.getText(), motivacaoField.getText());
            salvarEmCSV(m);
            JOptionPane.showMessageDialog(this, "Música salva com sucesso!");
        });

        return criarPainelComCampos(salvarButton, new JLabel("Nome:"), nomeField, new JLabel("Estado:"), estadoField, new JLabel("Motivação:"), motivacaoField);
    }

    private JPanel criarPainelComCampos(JButton salvarButton, Component... componentes) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        for (Component comp : componentes) {
            panel.add(comp);
        }
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        container.add(salvarButton, BorderLayout.SOUTH);
        return container;
    }

    // =======================================================================
    // Salvar em um Único Arquivo CSV
    // =======================================================================

    private void salvarEmCSV(Object obj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.csv", true))) {
            if (obj instanceof Personagem) {
                writer.write(obj.toString());
            } else if (obj instanceof Predio) {
                writer.write(obj.toString());
            } else if (obj instanceof Cafe) {
                writer.write(obj.toString());
            } else if (obj instanceof Elefante) {
                writer.write(obj.toString());
            } else if (obj instanceof Musica) {
                writer.write(obj.toString());
            }
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no arquivo CSV.", "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}