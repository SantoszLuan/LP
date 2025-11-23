<h1 align="center"> Entrega 1 </h1>


<h2> 1. Média </h2>
<pre><code class="language-java">
package org.example.calculomedia;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class MediaCalculo extends Application {

    private final DecimalFormat df = new DecimalFormat("0.00"); //Garante que os números tenham duas casas decimais

    //"Main" do JavaFX
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculo de Média");

        GridPane grid = new GridPane(); //Cria o layout
        grid.setHgap(10); //Espaço Horizontal
        grid.setVgap(10); //Espaço Vertical
        grid.setPadding(new Insets(20, 20, 20, 20)); //Espaçamento interno

        Label lblP1 = new Label("P1: ");
        TextField tfP1 = new TextField();


        Label lblE1 = new Label("E1: ");
        TextField tfE1 = new TextField();


        Label lblE2 = new Label("E2: ");
        TextField tfE2 = new TextField();


        Label lblX = new Label("X: ");
        TextField tfX = new TextField();


        Label lblSUB = new Label("SUB: ");
        TextField tfSUB = new TextField();


        Label lblAPI = new Label("API: ");
        TextField tfAPI = new TextField();


        Label lblResult = new Label("Média:");
        TextField tfResult = new TextField();
        tfResult.setEditable(false); //O usuario não pode editar o campo

        Button btnCalc = new Button("Calcular");
        btnCalc.setOnAction(e -> {
            //Lê os valores
            try {
                double p1 = parseDouble(tfP1.getText());
                double e1 = parseDouble(tfE1.getText());
                double e2 = parseDouble(tfE2.getText());
                double x = parseDouble(tfX.getText());
                double sub = parseDouble(tfSUB.getText());
                double api = parseDouble(tfAPI.getText());

                double media = calcularMedia(p1, e1, e2, x, sub, api);
                tfResult.setText(df.format(media));
            } catch (NumberFormatException ex) {
                //Se der inválido
                showError("Entrada inválida! ", "Use apenas números válidos (ex: 7.5).");
            }
        });

        //Limpa os campos
        Button btnClear = new Button("Limpar");
        btnClear.setOnAction(e -> {
            tfP1.clear(); tfE1.clear(); tfE2.clear(); tfX.clear(); tfSUB.clear(); tfAPI.clear(); tfResult.clear();
        });

        //Adiciona colunas e linhas ao grid
        grid.add(lblP1, 0, 0); grid.add(tfP1, 1, 0);
        grid.add(lblE1, 0, 1); grid.add(tfE1, 1, 1);
        grid.add(lblE2, 0, 2); grid.add(tfE2, 1, 2);
        grid.add(lblX, 0, 3); grid.add(tfX, 1, 3);
        grid.add(lblSUB, 0, 4); grid.add(tfSUB, 1, 4);
        grid.add(lblAPI, 0, 5); grid.add(tfAPI, 1, 5);
        grid.add(btnCalc, 0, 6); grid.add(btnClear, 1, 6);
        grid.add(lblResult, 0, 7); grid.add(tfResult, 1, 7);

        Scene scene = new Scene(grid, 420, 360);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double parseDouble(String s) {
        if (s == null || s.trim().isEmpty()) return 0.0;
        return Double.parseDouble(s.trim());
    }

    private double calcularMedia(double p1, double e1, double e2, double x, double sub, double api) {
        double base = p1 * 0.5 + e1 * 0.2 + e2 * 0.3 + x + sub * 0.15;
        double part1 = base * 0.5;
        double bonusFactor = (base > 5.9) ? 1.0 : 0.0;
        double part2 = bonusFactor * api * 0.5;
        return part1 + part2;
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

</code></pre>

<h2> 2. Música </h2>
<pre><code class="language-java">
package org.example.calculomedia;

public class Musica implements Registravel {
    private String nome;
    private String estado;
    private String motivacao;

    public Musica(String nome, String estado, String motivacao) {
        this.nome = nome;
        this.estado = estado;
        this.motivacao = motivacao;
    }

    @Override
    public String toString() {
        return "Musica," + nome + "," + estado + "," + motivacao;
    }
}

</code></pre>


<h2> 3. Imagem — Vik Muniz </h2>
<h3> Monalisa </h3>
<pre><code class="language-java">
      package org.example.calculomedia;

public class Monalisa implements Registravel {
    private String nome;
    private int idade;
    private String sexo;

    public Monalisa(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Monalisa," + nome + "," + idade + "," + sexo;
    }
}
</code></pre>

<h3> Café </h3>

<pre><code class="language-java">
      package org.example.calculomedia;

public class Cafe implements Registravel {
    private String tamanho;
    private String posicao;
    private String cor;

    public Cafe(String tamanho, String posicao, String cor) {
        this.tamanho = tamanho;
        this.posicao = posicao;
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Cafe," + tamanho + "," + posicao + "," + cor;
    }
}
</code></pre>



<h2> 4. Imagem — Doodle </h2>

<h3> Elefante </h3>
<pre><code class="language-java">
 package org.example.calculomedia;

public class Elefante implements Registravel {
    private String nome;
    private double peso;
    private double tamanho;

    public Elefante(String nome, double peso, double tamanho) {
        this.nome = nome;
        this.peso = peso;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Elefante," + nome + "," + peso + "," + tamanho;
    }
}
</code></pre>

## 5. Vídeo
### [Cena Final - Clube da Luta](https://www.youtube.com/watch?v=vh6SDvDhDN4)

<pre><code class="language-java">
package org.example.calculomedia;

public class Predio implements Registravel {
    private String cor;
    private double altura;
    private int andares;

    public Predio(String cor, double altura, int andares) {
        this.cor = cor;
        this.altura = altura;
        this.andares = andares;
    }

    @Override
    public String toString() {
        return "Predio," + cor + "," + altura + "," + andares;
    }
}

</code></pre>




<h2> Main </h2>
<pre><code class="language-java">
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
</code></pre>

<h2> Registrável </h2>
<pre><code class="language-java">
package org.example.calculomedia;

public class Registrável {
// Define que qualquer classe que a implemente precisa saber se transformar em texto (toString)
// Assim, podemos salvar qualquer objeto em um arquivo sem saber sua classe específica. Como se fosse um "crachá"
public interface Registravel {
    String toString();
}
}

</code></pre>

<h1 align="center"> Entrega 2 </h1>

<h2> Main </h2>
<pre><code class="language-java">
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
</code></pre>

<h2> Musica </h2>
<pre><code class="language-java">
package org.example.calculomedia;

public class Musica {
    private String nome;
    private String artista;
    private int duracaoEmSegundos;

    public Musica(String nome, String artista, int duracaoEmSegundos) {
        this.nome = nome;
        this.artista = artista;
        this.duracaoEmSegundos = duracaoEmSegundos; // CORRIGIDO
    }

    public String getNome() { return nome; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public int getDuracaoEmSegundos() { return duracaoEmSegundos; }
    public void setDuracaoEmSegundos(int duracaoEmSegundos) { this.duracaoEmSegundos = duracaoEmSegundos; }

    @Override
    public String toString() {
        return "Musica{Nome: " + nome + ", Artista: " + artista + ", Duração: " + duracaoEmSegundos + "s}";
    }
}
</code></pre>

<h2> Album </h2>
<pre><code class="language-java">
 package org.example.calculomedia;

public class Album {
    private String titulo;
    private int anoLancamento;
    private int numeroDeFaixas;

    public Album(String titulo, int anoLancamento, int numeroDeFaixas) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.numeroDeFaixas = numeroDeFaixas;
    }

    public String getTitulo() { return titulo; }
    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public int getNumeroDeFaixas() { return numeroDeFaixas; }
    public void setNumeroDeFaixas(int numeroDeFaixas) { this.numeroDeFaixas = numeroDeFaixas; }

    @Override
    public String toString() {
        return "Album{Título: " + titulo + ", Lançamento: " + anoLancamento + ", Faixas: " + numeroDeFaixas + "}";
    }
}
</code></pre>

<h2> Artista </h2>
<pre><code class="language-java">
 package org.example.calculomedia;

public class Artista {
    
    private String nomeCompleto; 
    private String nacionalidade;
    private int anoInicioCarreira;

  
    public Artista(String nomeCompleto, String nacionalidade, int anoInicioCarreira) {
        this.nomeCompleto = nomeCompleto;
        this.nacionalidade = nacionalidade;
        this.anoInicioCarreira = anoInicioCarreira;
    }

   
    public String getNomeCompleto() { return nomeCompleto; }
    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public int getAnoInicioCarreira() { return anoInicioCarreira; }
    public void setAnoInicioCarreira(int anoInicioCarreira) { this.anoInicioCarreira = anoInicioCarreira; }

    @Override
    public String toString() {
        return "Artista{Nome: " + nomeCompleto + ", País: " + nacionalidade + ", Início: " + anoInicioCarreira + "}";
    }
}
</code></pre>

<h2> Musica - Service </h2>
<pre><code class="language-java">
package org.example.calculomedia.service;

import org.example.calculomedia.dao.DAO;
import org.example.calculomedia.Musica;
import java.util.ArrayList;
import java.util.List;

public class MusicaService implements DAO<Musica> {
    private static final List<Musica> REPOSITORIO_MUSICAS = new ArrayList<>();

    @Override
    public void salvar(Musica musica) {
        REPOSITORIO_MUSICAS.add(musica);
    }

    @Override
    public Musica buscarPorId(String nomeMusica) {
        return REPOSITORIO_MUSICAS.stream()
                .filter(m -> m.getNome().equals(nomeMusica))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Musica musicaAtualizada) {
        Musica musicaExistente = buscarPorId(musicaAtualizada.getNome());
        if (musicaExistente != null) {
            musicaExistente.setArtista(musicaAtualizada.getArtista());
            musicaExistente.setDuracaoEmSegundos(musicaAtualizada.getDuracaoEmSegundos());
        }
    }

    @Override
    public void deletar(Musica musica) {
        REPOSITORIO_MUSICAS.removeIf(m -> m.getNome().equals(musica.getNome()));
    }

    @Override
    public List<Musica> buscarTodos() {
        return new ArrayList<>(REPOSITORIO_MUSICAS);
    }
}
</code></pre>

<h2> Album - Service </h2>
<pre><code class="language-java">
 package org.example.calculomedia.service;

import org.example.calculomedia.dao.DAO;
import org.example.calculomedia.Album;
import java.util.ArrayList;
import java.util.List;

public class AlbumService implements DAO<Album> {
    private static final List<Album> REPOSITORIO_ALBUNS = new ArrayList<>();

    @Override
    public void salvar(Album album) {
        REPOSITORIO_ALBUNS.add(album);
    }

    @Override
    public Album buscarPorId(String tituloAlbum) {
        return REPOSITORIO_ALBUNS.stream()
                .filter(a -> a.getTitulo().equals(tituloAlbum))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Album albumAtualizado) {
        Album albumExistente = buscarPorId(albumAtualizado.getTitulo());
        if (albumExistente != null) {
            albumExistente.setAnoLancamento(albumAtualizado.getAnoLancamento());
            albumExistente.setNumeroDeFaixas(albumAtualizado.getNumeroDeFaixas());
        }
    }

    @Override
    public void deletar(Album album) {
        REPOSITORIO_ALBUNS.removeIf(a -> a.getTitulo().equals(album.getTitulo()));
    }

    @Override
    public List<Album> buscarTodos() {
        return new ArrayList<>(REPOSITORIO_ALBUNS);
    }
}
</code></pre>

<h2> Artista - Service </h2>
<pre><code class="language-java">
 package org.example.calculomedia;

public class Artista {

    private String nomeCompleto;
    private String nacionalidade;
    private int anoInicioCarreira;


    public Artista(String nomeCompleto, String nacionalidade, int anoInicioCarreira) {
        this.nomeCompleto = nomeCompleto;
        this.nacionalidade = nacionalidade;
        this.anoInicioCarreira = anoInicioCarreira;
    }


    public String getNomeCompleto() { return nomeCompleto; }
    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public int getAnoInicioCarreira() { return anoInicioCarreira; }
    public void setAnoInicioCarreira(int anoInicioCarreira) { this.anoInicioCarreira = anoInicioCarreira; }

    @Override
    public String toString() {
        return "Artista{Nome: " + nomeCompleto + ", País: " + nacionalidade + ", Início: " + anoInicioCarreira + "}";
    }
}
</code></pre>

<h2> Princiapl GUI </h2>
<pre><code class="language-java">
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
</code></pre>

<h2> Musica - GUI </h2>
<pre><code class="language-java">
 package org.example.calculomedia.gui;

import org.example.calculomedia.Musica;
import org.example.calculomedia.service.MusicaService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class MusicaGUI extends JPanel {
    private MusicaService musicaService = new MusicaService();
    private JTable musicTable;
    private DefaultTableModel tableModel;
    private JTextField txtNome, txtArtista, txtDuracao;

    public MusicaGUI() {
        setLayout(new BorderLayout(10, 10));

        
        String[] colunas = {"Nome", "Artista", "Duração"};
        tableModel = new DefaultTableModel(colunas, 0);
        musicTable = new JTable(tableModel);
        carregarMusicasNaTabela();

      
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        txtNome = new JTextField(20);
        txtArtista = new JTextField(20);
        txtDuracao = new JTextField(20);

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);
        formPanel.add(new JLabel("Artista:"));
        formPanel.add(txtArtista);
        formPanel.add(new JLabel("Duração (Segundos):"));
        formPanel.add(txtDuracao);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnBuscar = new JButton("Recarregar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnDeletar);

        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

     al
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(musicTable), BorderLayout.CENTER);

   
        btnSalvar.addActionListener(e -> salvarMusica());
        btnBuscar.addActionListener(e -> carregarMusicasNaTabela());
        btnAtualizar.addActionListener(e -> atualizarMusica());
        btnDeletar.addActionListener(e -> deletarMusica());


        musicTable.getSelectionModel().addListSelectionListener(e -> preencherCampos());
    }

    private void carregarMusicasNaTabela() {
        tableModel.setRowCount(0);
        List<Musica> musicas = musicaService.buscarTodos();
        for (Musica m : musicas) {
            tableModel.addRow(new Object[]{m.getNome(), m.getArtista(), m.getDuracaoEmSegundos()});
        }
    }

    private void salvarMusica() {
        try {
            String nome = txtNome.getText();
            String artista = txtArtista.getText();
            int duracao = Integer.parseInt(txtDuracao.getText());

            if (musicaService.buscarPorId(nome) != null) {
                JOptionPane.showMessageDialog(this, "Música já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Musica novaMusica = new Musica(nome, artista, duracao);
            musicaService.salvar(novaMusica);
            carregarMusicasNaTabela();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duração deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarMusica() {
        try {
            String nome = txtNome.getText();
            String artista = txtArtista.getText();
            int duracao = Integer.parseInt(txtDuracao.getText());

            Musica musicaAtualizada = new Musica(nome, artista, duracao);
            musicaService.atualizar(musicaAtualizada);

            carregarMusicasNaTabela();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duração deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarMusica() {
        String nome = txtNome.getText();
        if (musicaService.buscarPorId(nome) == null) {
            JOptionPane.showMessageDialog(this, "Música não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Musica musicaParaDeletar = new Musica(nome, "", 0);
        musicaService.deletar(musicaParaDeletar);

        carregarMusicasNaTabela();
        limparCampos();
    }

    private void preencherCampos() {
        int row = musicTable.getSelectedRow();
        if (row >= 0) {
            txtNome.setText(tableModel.getValueAt(row, 0).toString());
            txtArtista.setText(tableModel.getValueAt(row, 1).toString());
            txtDuracao.setText(tableModel.getValueAt(row, 2).toString());
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtArtista.setText("");
        txtDuracao.setText("");
    }
}
</code></pre>

<h2> Album - GUI </h2>
<pre><code class="language-java">
 package org.example.calculomedia.gui;

import org.example.calculomedia.Album;
import org.example.calculomedia.service.AlbumService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlbumGUI extends JPanel {
    private AlbumService albumService = new AlbumService();
    private JTable albumTable;
    private DefaultTableModel tableModel;
    private JTextField txtTitulo, txtAnoLancamento, txtNumeroFaixas;

    public AlbumGUI() {
        setLayout(new BorderLayout(10, 10));

        
        String[] colunas = {"Título", "Ano de Lançamento", "Nº Faixas"};
        tableModel = new DefaultTableModel(colunas, 0);
        albumTable = new JTable(tableModel);
        carregarAlbumsNaTabela();

    
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        txtTitulo = new JTextField(20);
        txtAnoLancamento = new JTextField(20);
        txtNumeroFaixas = new JTextField(20);

        formPanel.add(new JLabel("Título:"));
        formPanel.add(txtTitulo);
        formPanel.add(new JLabel("Ano de Lançamento:"));
        formPanel.add(txtAnoLancamento);
        formPanel.add(new JLabel("Número de Faixas:"));
        formPanel.add(txtNumeroFaixas);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnBuscar = new JButton("Recarregar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnDeletar);

        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(albumTable), BorderLayout.CENTER);
        
        btnSalvar.addActionListener(e -> salvarAlbum());
        btnBuscar.addActionListener(e -> carregarAlbumsNaTabela());
        btnAtualizar.addActionListener(e -> atualizarAlbum());
        btnDeletar.addActionListener(e -> deletarAlbum());
        
        albumTable.getSelectionModel().addListSelectionListener(e -> preencherCampos());
    }

    private void carregarAlbumsNaTabela() {
        tableModel.setRowCount(0);
        List<Album> albums = albumService.buscarTodos();

        for (Album a : albums) {
            tableModel.addRow(new Object[]{a.getTitulo(), a.getAnoLancamento(), a.getNumeroDeFaixas()});
        }
    }

    private void salvarAlbum() {
        try {
            String titulo = txtTitulo.getText();
            int anoLancamento = Integer.parseInt(txtAnoLancamento.getText());
            int numeroFaixas = Integer.parseInt(txtNumeroFaixas.getText());

            if (albumService.buscarPorId(titulo) != null) {
                JOptionPane.showMessageDialog(this, "Álbum com este título já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Album novoAlbum = new Album(titulo, anoLancamento, numeroFaixas);
            albumService.salvar(novoAlbum);
            carregarAlbumsNaTabela();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e número de faixas devem ser números inteiros!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarAlbum() {
        try {
            String titulo = txtTitulo.getText();
            int anoLancamento = Integer.parseInt(txtAnoLancamento.getText());
            int numeroFaixas = Integer.parseInt(txtNumeroFaixas.getText());

            Album albumAtualizado = new Album(titulo, anoLancamento, numeroFaixas);
            albumService.atualizar(albumAtualizado);

            carregarAlbumsNaTabela();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e número de faixas devem ser números inteiros!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarAlbum() {
        String titulo = txtTitulo.getText();
        if (albumService.buscarPorId(titulo) == null) {
            JOptionPane.showMessageDialog(this, "Álbum não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Album albumParaDeletar = new Album(titulo, 0, 0); // Apenas o título (ID) é necessário
        albumService.deletar(albumParaDeletar);

        carregarAlbumsNaTabela();
        limparCampos();
    }

    private void preencherCampos() {
        int row = albumTable.getSelectedRow();
        if (row >= 0) {
            txtTitulo.setText(tableModel.getValueAt(row, 0).toString());
            txtAnoLancamento.setText(tableModel.getValueAt(row, 1).toString());
            txtNumeroFaixas.setText(tableModel.getValueAt(row, 2).toString());
        }
    }

    private void limparCampos() {
        txtTitulo.setText("");
        txtAnoLancamento.setText("");
        txtNumeroFaixas.setText("");
    }
}
</code></pre>

<h2> Artista - GUI </h2>
<pre><code class="language-java">
package org.example.calculomedia.gui;

import org.example.calculomedia.Artista;
import org.example.calculomedia.service.ArtistaService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ArtistaGUI extends JPanel {
    private ArtistaService artistaService = new ArtistaService();
    private JTable artistaTable;
    private DefaultTableModel tableModel;
    private JTextField txtNomeCompleto, txtNacionalidade, txtAnoInicio;

    public ArtistaGUI() {
        setLayout(new BorderLayout(10, 10));
        
        String[] colunas = {"Nome Completo", "Nacionalidade", "Início Carreira"};
        tableModel = new DefaultTableModel(colunas, 0);
        artistaTable = new JTable(tableModel);
        carregarArtistasNaTabela();
        
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        txtNomeCompleto = new JTextField(20);
        txtNacionalidade = new JTextField(20);
        txtAnoInicio = new JTextField(20);

        formPanel.add(new JLabel("Nome Completo:"));
        formPanel.add(txtNomeCompleto);
        formPanel.add(new JLabel("Nacionalidade:"));
        formPanel.add(txtNacionalidade);
        formPanel.add(new JLabel("Ano de início de carreira:"));
        formPanel.add(txtAnoInicio);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnBuscar = new JButton("Recarregar ");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar ");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnDeletar);

        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(artistaTable), BorderLayout.CENTER);
        
        btnSalvar.addActionListener(e -> salvarArtista());
        btnBuscar.addActionListener(e -> carregarArtistasNaTabela());
        btnAtualizar.addActionListener(e -> atualizarArtista());
        btnDeletar.addActionListener(e -> deletarArtista());
        
        artistaTable.getSelectionModel().addListSelectionListener(e -> preencherCampos());
    }

    private void carregarArtistasNaTabela() {
        tableModel.setRowCount(0);
        List<Artista> artistas = artistaService.buscarTodos();

        for (Artista a : artistas) {
            tableModel.addRow(new Object[]{a.getNomeCompleto(), a.getNacionalidade(), a.getAnoInicioCarreira()});
        }
    }

    private void salvarArtista() {
        try {
            String nome = txtNomeCompleto.getText();
            String nacionalidade = txtNacionalidade.getText();
            int anoInicio = Integer.parseInt(txtAnoInicio.getText());

            if (artistaService.buscarPorId(nome) != null) {
                JOptionPane.showMessageDialog(this, "Artista com este nome já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Artista novoArtista = new Artista(nome, nacionalidade, anoInicio);
            artistaService.salvar(novoArtista);
            carregarArtistasNaTabela();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ano deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarArtista() {
        try {
            String nome = txtNomeCompleto.getText();
            String nacionalidade = txtNacionalidade.getText();
            int anoInicio = Integer.parseInt(txtAnoInicio.getText());

            Artista artistaAtualizado = new Artista(nome, nacionalidade, anoInicio);
            artistaService.atualizar(artistaAtualizado);

            carregarArtistasNaTabela();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ano deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarArtista() {
        String nome = txtNomeCompleto.getText();
        if (artistaService.buscarPorId(nome) == null) {
            JOptionPane.showMessageDialog(this, "Artista não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Artista artistaParaDeletar = new Artista(nome, "", 0); // Apenas o nome (ID) é necessário
        artistaService.deletar(artistaParaDeletar);

        carregarArtistasNaTabela();
        limparCampos();
    }

    private void preencherCampos() {
        int row = artistaTable.getSelectedRow();
        if (row >= 0) {
            txtNomeCompleto.setText(tableModel.getValueAt(row, 0).toString());
            txtNacionalidade.setText(tableModel.getValueAt(row, 1).toString());
            txtAnoInicio.setText(tableModel.getValueAt(row, 2).toString());
        }
    }

    private void limparCampos() {
        txtNomeCompleto.setText("");
        txtNacionalidade.setText("");
        txtAnoInicio.setText("");
    }
}
</code></pre>

<h2> DAO </h2>
<pre><code class="language-java">
 package org.example.calculomedia.dao;

import java.util.List;

public interface DAO<T> {
    void salvar(T objeto);
    T buscarPorId(String id);
    void atualizar(T objeto);
    void deletar(T objeto);
    List<T> buscarTodos();
}
</code></pre>




