<h1 align="center"> Entrega 1 </h1>


<h2> 1. Média.java </h2>
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

<h2> 2. Música.java </h2>
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
<h3> Monalisa.java </h3>
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

<h3> Café.java </h3>

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

<h3> Elefante.java </h3>
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




<h2> Main.java </h2>
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

<h2> Registrável.java </h2>
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

<h2> Main.java </h2>
<pre><code class="language-java">
  import javax.swing.*;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private static final String DB_URL = "jdbc:sqlite:MusicaDB.db";

    public Main() {
        super("Musica - Waiting for the End");

        try {

            // Cria o painel de abas
            JTabbedPane tabbedPane = new JTabbedPane();

            // Define os campos para cada classe
            String[] camposVoz = {"Tom", "Revolta", "Melhoria (%)"};
            String[] camposRitmo = {"Ouvir", "Palavras", "Vazio"};
            String[] camposFogo = {"Sensação", "Fim", "Recomeçar"};

            // Adiciona as abas utilizando o PainelCRUD
            tabbedPane.addTab("Voz", new PainelCRUD("Voz", camposVoz));
            tabbedPane.addTab("Ritmo", new PainelCRUD("Ritmo", camposRitmo));
            tabbedPane.addTab("Fogo", new PainelCRUD("Fogo", camposFogo));

            add(tabbedPane, BorderLayout.CENTER);

        } catch (Exception e) {
            //Mensagem de erro
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o Painel: " + e.getMessage(), "Erro Fatal", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }

        // Configurações da Janela Principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Inicializa o banco de dados antes de iniciar a GUI
        criarTabelas();

        // Gera os CSVs iniciais ao carregar o sistema.
        try {
            exportarParaCSV("Vozes", "Voz.csv");
            exportarParaCSV("Ritmos", "Ritmo.csv");
            exportarParaCSV("Fogos", "Fogo.csv");
        } catch (SQLException | java.io.IOException e) {
            System.err.println("Aviso: Falha ao gerar CSV inicial. Arquivos serão criados após a primeira alteração.");
        }

        SwingUtilities.invokeLater(Main::new);
    }


    // Conexão com SQLite
    private static Connection conectar() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC SQLite não encontrado.");
        }
        return DriverManager.getConnection(DB_URL);
    }

    //Cria as tabelas
    public static void criarTabelas() {
        String sqlVoz = "CREATE TABLE IF NOT EXISTS Vozes (id INTEGER PRIMARY KEY, tom TEXT, revolta TEXT, melhoria INTEGER);";
        String sqlRitmo = "CREATE TABLE IF NOT EXISTS Ritmos (id INTEGER PRIMARY KEY, ouvir TEXT, palavras TEXT, vazio TEXT);";
        String sqlFogo = "CREATE TABLE IF NOT EXISTS Fogos (id INTEGER PRIMARY KEY, sensacao TEXT, fim TEXT, recomecar TEXT);";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlVoz);
            stmt.execute(sqlRitmo);
            stmt.execute(sqlFogo);
        } catch (SQLException e) {
            //Mensagem de erro se não conseguir criar as tabelas
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    // CRUD - Voz
    public static void inserirVoz(Voz voz) {
        String sql = "INSERT INTO Vozes(tom, revolta, melhoria) VALUES(?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, voz.getTom()); pstmt.setString(2, voz.getRevolta()); pstmt.setInt(3, voz.getMelhoria());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) { if (rs.next()) { voz.setId(rs.getInt(1)); } }

            // Exporta os dados para o csv
            exportarParaCSV("Vozes", "Voz.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao inserir Voz ou exportar CSV: " + e.getMessage()); }
    }
    public static List<Voz> listarTodasVozes() {
        String sql = "SELECT id, tom, revolta, melhoria FROM Vozes";
        List<Voz> vozes = new ArrayList<>();
        try (Connection conn = conectar(); Statement stmt  = conn.createStatement(); ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) { vozes.add(new Voz(rs.getInt("id"), rs.getString("tom"), rs.getString("revolta"), rs.getInt("melhoria"))); }
        } catch (SQLException e) { System.err.println("Erro ao listar Vozes: " + e.getMessage()); }
        return vozes;
    }
    public static void atualizarVoz(Voz voz) {
        String sql = "UPDATE Vozes SET tom = ?, revolta = ?, melhoria = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, voz.getTom()); pstmt.setString(2, voz.getRevolta()); pstmt.setInt(3, voz.getMelhoria()); pstmt.setInt(4, voz.getId());
            pstmt.executeUpdate();

            exportarParaCSV("Vozes", "Voz.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao atualizar Voz ou exportar CSV: " + e.getMessage()); }
    }
    public static void deletarVoz(int id) {
        String sql = "DELETE FROM Vozes WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); pstmt.executeUpdate();

            exportarParaCSV("Vozes", "Voz.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao deletar Voz ou exportar CSV: " + e.getMessage()); }
    }

    // CRUD - Ritmo
    //PreparedStatement - Envia comandos para o banco com segurança
    //setString() - coloca um valor no lugar de um ?
    //executeUpdate() - Faz o insert no banco
    //getGeneratedKeys() - Pega o ID gerado pelo banco
    public static void inserirRitmo(Ritmo ritmo) {
        String sql = "INSERT INTO Ritmos(ouvir, palavras, vazio) VALUES(?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) //"Starta" o pstmt
        {
            pstmt.setString(1, ritmo.getOuvir()); pstmt.setString(2, ritmo.getPalavras()); pstmt.setString(3, ritmo.getVazio());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) { if (rs.next()) { ritmo.setId(rs.getInt(1)); } }

            exportarParaCSV("Ritmos", "Ritmo.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao inserir Ritmo ou exportar CSV: " + e.getMessage()); }
    }
    public static List<Ritmo> listarTodosRitmos() {
        String sql = "SELECT id, ouvir, palavras, vazio FROM Ritmos";
        List<Ritmo> ritmos = new ArrayList<>();
        try (Connection conn = conectar(); Statement stmt  = conn.createStatement(); ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) { ritmos.add(new Ritmo(rs.getInt("id"), rs.getString("ouvir"), rs.getString("palavras"), rs.getString("vazio"))); }
        } catch (SQLException e) { System.err.println("Erro ao listar Ritmos: " + e.getMessage()); }
        return ritmos;
    }
    public static void atualizarRitmo(Ritmo ritmo) {
        String sql = "UPDATE Ritmos SET ouvir = ?, palavras = ?, vazio = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ritmo.getOuvir()); pstmt.setString(2, ritmo.getPalavras()); pstmt.setString(3, ritmo.getVazio()); pstmt.setInt(4, ritmo.getId());
            pstmt.executeUpdate();

            exportarParaCSV("Ritmos", "Ritmo.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao atualizar Ritmo ou exportar CSV: " + e.getMessage()); }
    }
    public static void deletarRitmo(int id) {
        String sql = "DELETE FROM Ritmos WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); pstmt.executeUpdate();

            exportarParaCSV("Ritmos", "Ritmo.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao deletar Ritmo ou exportar CSV: " + e.getMessage()); }
    }

    // CRUD - Fogo
    public static void inserirFogo(Fogo fogo) {
        String sql = "INSERT INTO Fogos(sensacao, fim, recomecar) VALUES(?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, fogo.getSensacao()); pstmt.setString(2, fogo.getFim()); pstmt.setString(3, fogo.getRecomecar());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) { if (rs.next()) { fogo.setId(rs.getInt(1)); } }

            exportarParaCSV("Fogos", "Fogo.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao inserir Fogo ou exportar CSV: " + e.getMessage()); }
    }
    public static List<Fogo> listarTodosFogos() {
        String sql = "SELECT id, sensacao, fim, recomecar FROM Fogos";
        List<Fogo> fogos = new ArrayList<>();
        try (Connection conn = conectar(); Statement stmt  = conn.createStatement(); ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) { fogos.add(new Fogo(rs.getInt("id"), rs.getString("sensacao"), rs.getString("fim"), rs.getString("recomecar"))); }
        } catch (SQLException e) { System.err.println("Erro ao listar Fogos: " + e.getMessage()); }
        return fogos;
    }
    public static void atualizarFogo(Fogo fogo) {
        String sql = "UPDATE Fogos SET sensacao = ?, fim = ?, recomecar = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fogo.getSensacao()); pstmt.setString(2, fogo.getFim()); pstmt.setString(3, fogo.getRecomecar()); pstmt.setInt(4, fogo.getId());
            pstmt.executeUpdate();

            exportarParaCSV("Fogos", "Fogo.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao atualizar Fogo ou exportar CSV: " + e.getMessage()); }
    }
    public static void deletarFogo(int id) {
        String sql = "DELETE FROM Fogos WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); pstmt.executeUpdate();

            exportarParaCSV("Fogos", "Fogo.csv");

        } catch (SQLException | java.io.IOException e) { System.err.println("Erro ao deletar Fogo ou exportar CSV: " + e.getMessage()); }
    }


    public static void exportarParaCSV(String tableName, String filePath) throws SQLException, java.io.IOException {
        String sql = "SELECT * FROM " + tableName;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             java.io.FileWriter writer = new java.io.FileWriter(filePath)) {

            // Escreve os nomes das colunas
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                writer.append(metaData.getColumnName(i));
                if (i < columnCount) {
                    writer.append(",");
                }
            }
            writer.append("\n");

            // Escreve os dados
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);

                    // Adiciona aspas se o valor contiver vírgula ou aspas
                    if (value != null && (value.contains(",") || value.contains("\""))) {
                        value = "\"" + value.replace("\"", "\"\"") + "\"";
                    }
                    writer.append(value != null ? value : "");
                    if (i < columnCount) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }

            writer.flush();

        } catch (SQLException e) {
            throw new SQLException("Erro de SQL durante a exportação: " + e.getMessage());
        } catch (java.io.IOException e) {
            throw new java.io.IOException("Erro de I/O ao escrever o arquivo CSV: " + e.getMessage());
        }
    }
}
</code></pre>

<h2> PainelCRUD.java </h2>
<pre><code class="language-java">
 import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PainelCRUD extends JPanel {

    // --- CORES DO TEMA (Dark Moderno) ---
    private static final Color BACKGROUND_COLOR = new Color(30, 30, 30); // Fundo Principal
    private static final Color TEXT_COLOR = Color.WHITE; // Texto Geral

    // Cores VIVAS (Neon/Glow) para Destaques
    private static final Color ADD_COLOR = new Color(0, 255, 100);    // Verde Neon para Adicionar
    private static final Color UPDATE_COLOR = new Color(255, 165, 0); // Laranja para Atualizar
    private static final Color REFRESH_COLOR = new Color(0, 191, 255); // Azul Claro para Recarregar
    private static final Color DELETE_COLOR = new Color(200, 50, 50);  // Vermelho para Remover (Ação de Risco)
    private static final Color DEFAULT_BUTTON_COLOR = new Color(50, 50, 50); // Cinza Escuro para Default

    // Cores para Campos e Tabela
    private static final Color FIELD_BG_COLOR = new Color(45, 45, 45);
    private static final Color TABLE_BG_COLOR = new Color(35, 35, 35);
    private static final Color HEADER_BG_COLOR = new Color(50, 50, 50);
    private static final Color SELECTION_COLOR = new Color(0, 123, 255, 100);

    private final DefaultTableModel tableModel;
    private final JTable dataTable;
    private final String[] fieldNames;
    private final Class<?> dataClass;
    private final JTextField[] inputFields;
    private final Method loadMethod;
    private final Method insertMethod;
    private final Method updateMethod;
    private final Method deleteMethod;


    public PainelCRUD(String className, String[] fieldNames) throws Exception {
        this.fieldNames = fieldNames;
        this.dataClass = Class.forName(className);

        // --- ESTILO: Fundo do Painel Principal ---
        setBackground(BACKGROUND_COLOR);

        // Configurar o Modelo da Tabela
        String[] columnNames = new String[fieldNames.length + 2];
        columnNames[0] = "ID";
        System.arraycopy(fieldNames, 0, columnNames, 1, fieldNames.length);
        columnNames[fieldNames.length + 1] = "Método";

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        dataTable = new JTable(tableModel);

        // --- ESTILO: Tabela ---
        dataTable.setBackground(TABLE_BG_COLOR);
        dataTable.setForeground(TEXT_COLOR);
        dataTable.setGridColor(new Color(60, 60, 60));
        dataTable.setSelectionBackground(SELECTION_COLOR);
        dataTable.setSelectionForeground(Color.WHITE);
        dataTable.setRowHeight(25);

        JTableHeader header = dataTable.getTableHeader();
        header.setBackground(HEADER_BG_COLOR);
        header.setForeground(TEXT_COLOR);
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setBorder(null);


        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setPreferredSize(new Dimension(700, 250));
        scrollPane.getViewport().setBackground(TABLE_BG_COLOR);
        scrollPane.setBorder(BorderFactory.createLineBorder(HEADER_BG_COLOR, 1));

        dataTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && dataTable.getSelectedRow() != -1) {
                preencherCamposComSelecao();
            }
        });

        // Configurar os Campos de Entrada
        JPanel inputPanel = new JPanel(new GridBagLayout());
        // --- ESTILO: Fundo do Painel de Entrada ---
        inputPanel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        inputFields = new JTextField[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            inputFields[i] = new JTextField(20);

            // --- ESTILO: Label (Texto) ---
            JLabel label = new JLabel(fieldNames[i] + ":");
            label.setForeground(TEXT_COLOR);
            label.setFont(new Font("Arial", Font.PLAIN, 14));

            // --- ESTILO: Campo de Texto ---
            inputFields[i].setBackground(FIELD_BG_COLOR);
            inputFields[i].setForeground(TEXT_COLOR);
            inputFields[i].setCaretColor(TEXT_COLOR);
            inputFields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));

            gbc.gridx = 0; gbc.gridy = i; inputPanel.add(label, gbc);
            gbc.gridx = 1; gbc.gridy = i; inputPanel.add(inputFields[i], gbc);
        }

        // Configurar os Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        // --- APLICAÇÃO DOS ESTILOS VIVAS/NEON ---
        JButton addButton = createStyledButton("Adicionar", ADD_COLOR);
        JButton updateButton = createStyledButton("Atualizar", UPDATE_COLOR);
        JButton deleteButton = createStyledButton("Remover", DELETE_COLOR);
        JButton refreshButton = createStyledButton("Recarregar", REFRESH_COLOR);
        JButton clearButton = createStyledButton("Limpar Campos", DEFAULT_BUTTON_COLOR);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(clearButton);

        // Layout Principal
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Tradutor que garante que o nome da função seja montado corretamente a cada vez que troca de aba.
        String pluralizedName;
        String allOrTodas;

        if (className.equals("Voz")) {
            pluralizedName = "Vozes";
            allOrTodas = "Todas";
        } else if (className.equals("Ritmo")) {
            pluralizedName = "Ritmos";
            allOrTodas = "Todos";
        } else {
            pluralizedName = "Fogos";
            allOrTodas = "Todos";
        }

        //Listar : listarTodasVozes, listarTodosRitmos, listarTodosFogos
        loadMethod = Main.class.getMethod("listar" + allOrTodas + pluralizedName);

        insertMethod = Main.class.getMethod("inserir" + className, dataClass);
        updateMethod = Main.class.getMethod("atualizar" + className, dataClass);
        deleteMethod = Main.class.getMethod("deletar" + className, int.class);

        // Ações dos Botões
        addButton.addActionListener(this::adicionarRegistro);
        updateButton.addActionListener(this::atualizarRegistro);
        deleteButton.addActionListener(this::deletarRegistro);
        refreshButton.addActionListener(e -> carregarRegistros());
        clearButton.addActionListener(e -> limparCampos());

        carregarRegistros();
    }

    /**
     * Cria um botão com efeito de destaque (glow sutil) no tema escuro.
     */
    private JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text);

        // Definindo as cores do botão
        Color currentBg = baseColor;

        // Se a cor base for um tom escuro padrão, usamos texto branco
        // Se for uma cor de destaque (neon), usamos o preto para alto contraste
        Color currentFg = (baseColor == DELETE_COLOR || baseColor == DEFAULT_BUTTON_COLOR) ? Color.WHITE : Color.BLACK;

        button.setBackground(currentBg);
        button.setForeground(currentFg);

        // Estilo de fonte e padding
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);

        // Efeito de Mouse Over (Glow Sutil)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Ao passar o mouse, a cor fica mais clara, simulando um "glow"
                Color hoverBg = currentBg.brighter().brighter();
                button.setBackground(hoverBg);

                // Para botões neon, o texto volta para preto no hover se não for preto
                if (baseColor != DELETE_COLOR && baseColor != DEFAULT_BUTTON_COLOR) {
                    button.setForeground(Color.BLACK);
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(currentBg);
                button.setForeground(currentFg);
            }
        });

        return button;
    }

    // Métodos de Ação (Mantidos os originais)

    private void adicionarRegistro(ActionEvent e) {
        try {
            Object[] simpleArgs = new Object[inputFields.length];
            Class<?>[] simpleTypes = new Class<?>[inputFields.length];

            for (int i = 0; i < inputFields.length; i++) {
                String text = inputFields[i].getText();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (fieldNames[i].toLowerCase().contains("melhoria")) {
                    simpleArgs[i] = Integer.parseInt(text);
                    simpleTypes[i] = int.class;
                } else {
                    simpleArgs[i] = text;
                    simpleTypes[i] = String.class;
                }
            }

            // Construtor sem ID
            Constructor<?> constructor = dataClass.getConstructor(simpleTypes);
            Object novoObjeto = constructor.newInstance(simpleArgs);

            insertMethod.invoke(null, novoObjeto);
            JOptionPane.showMessageDialog(this, dataClass.getSimpleName() + " adicionado com sucesso!");
            limparCampos();
            carregarRegistros();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Campo numérico inválido. Verifique Melhoria (%).", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (InvocationTargetException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar. Erro interno: " + ex.getTargetException().getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar registro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarRegistro(ActionEvent e) {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);

            Object[] args = new Object[inputFields.length + 1];
            Class<?>[] paramTypes = new Class<?>[inputFields.length + 1];

            args[0] = id;
            paramTypes[0] = int.class;

            for (int i = 0; i < inputFields.length; i++) {
                String text = inputFields[i].getText();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos para atualização.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (fieldNames[i].toLowerCase().contains("melhoria")) {
                    args[i + 1] = Integer.parseInt(text);
                    paramTypes[i + 1] = int.class;
                } else {
                    args[i + 1] = text;
                    paramTypes[i + 1] = String.class;
                }
            }

            // Construtor com ID
            Constructor<?> constructor = dataClass.getConstructor(paramTypes);
            Object objetoAtualizado = constructor.newInstance(args);

            updateMethod.invoke(null, objetoAtualizado);
            JOptionPane.showMessageDialog(this, "Registro ID " + id + " atualizado com sucesso!");
            limparCampos();
            carregarRegistros();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Melhoria (%) deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (InvocationTargetException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar. Erro interno: " + ex.getTargetException().getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarRegistro(ActionEvent e) {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o registro ID " + id + "?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                deleteMethod.invoke(null, id);
                JOptionPane.showMessageDialog(this, "Registro ID " + id + " removido com sucesso!");
                limparCampos();
                carregarRegistros();
            } catch (InvocationTargetException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover. Erro interno: " + ex.getTargetException().getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarRegistros() {
        tableModel.setRowCount(0);
        try {
            List<?> registros = (List<?>) loadMethod.invoke(null);

            for (Object obj : registros) {
                Object[] rowData = new Object[fieldNames.length + 2];

                Method getIdMethod = obj.getClass().getMethod("getId");
                rowData[0] = getIdMethod.invoke(obj);

                for (int i = 0; i < fieldNames.length; i++) {
                    String fieldName = fieldNames[i].split(" ")[0].toLowerCase();
                    Method getter = obj.getClass().getMethod("get" + capitalize(fieldName));
                    rowData[i + 1] = getter.invoke(obj);
                }

                Method extraMethod;
                String className = obj.getClass().getSimpleName();

                if (className.equals("Voz")) {
                    extraMethod = obj.getClass().getMethod("ouvirTom");
                } else if (className.equals("Ritmo")) {
                    extraMethod = obj.getClass().getMethod("parecerFirme");
                } else { // Fogo
                    extraMethod = obj.getClass().getMethod("acharSensacao");
                }
                rowData[fieldNames.length + 1] = extraMethod.invoke(obj);

                tableModel.addRow(rowData);
            }

        } catch (Exception ex) {
            System.err.println("Erro ao carregar registros: " + ex.getMessage());
        }
    }

    private void preencherCamposComSelecao() {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow != -1) {
            for (int i = 0; i < inputFields.length; i++) {
                inputFields[i].setText(String.valueOf(tableModel.getValueAt(selectedRow, i + 1)));
            }
        }
    }

    private void limparCampos() {
        for (JTextField field : inputFields) {
            field.setText("");
        }
        dataTable.clearSelection();
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (str.contains("(")) {
            str = str.substring(0, str.indexOf("(")).trim();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
</code></pre>

<h2> Voz.java </h2>
<pre><code class="language-java">
public class Voz {
    private int id;
    private String tom;
    private String revolta;
    private int melhoria;

    public Voz(int id, String tom, String revolta, int melhoria) {
        this.id = id;
        this.tom = tom;
        this.revolta = revolta;
        this.melhoria = melhoria;
    }

    public Voz(String tom, String revolta, int melhoria) {
        this(0, tom, revolta, melhoria);
    }

    public String ouvirTom() {
        return "Tom: " + this.tom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTom() { return tom; }
    public void setTom(String tom) { this.tom = tom; }
    public String getRevolta() { return revolta; }
    public void setRevolta(String revolta) { this.revolta = revolta; }
    public int getMelhoria() { return melhoria; }
    public void setMelhoria(int melhoria) { this.melhoria = melhoria; }
}
</code></pre>

<h2> Ritmo.java </h2>
<pre><code class="language-java">
public class Ritmo {
    private int id;
    private String ouvir;
    private String palavras;
    private String vazio;

    public Ritmo(int id, String ouvir, String palavras, String vazio) {
        this.id = id;
        this.ouvir = ouvir;
        this.palavras = palavras;
        this.vazio = vazio;
    }
    public Ritmo(String ouvir, String palavras, String vazio) {
        this(0, ouvir, palavras, vazio);
    }

    public boolean parecerFirme() {
        return this.palavras != null && this.palavras.length() > 5;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOuvir() { return ouvir; }
    public void setOuvir(String ouvir) { this.ouvir = ouvir; }
    public String getPalavras() { return palavras; }
    public void setPalavras(String palavras) { this.palavras = palavras; }
    public String getVazio() { return vazio; }
    public void setVazio(String vazio) { this.vazio = vazio; }
}
</code></pre>

<h2> Fogo.java </h2>
<pre><code class="language-java">
public class Fogo {
    private int id;
    private String sensacao;
    private String fim;
    private String recomecar;

    //Construtor (Lê dados do SQLite)
    public Fogo(int id, String sensacao, String fim, String recomecar) {
        this.id = id;
        this.sensacao = sensacao;
        this.fim = fim;
        this.recomecar = recomecar;
    }

    //Construtor simplificado (Usado para criar um novo item quando clicado em "Adicionar")
    public Fogo(String sensacao, String fim, String recomecar) {
        this(0, sensacao, fim, recomecar);
    }


    public String acharSensacao() {
        return "Sensação: " + this.sensacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getSensacao() { return sensacao; }
    public void setSensacao(String sensacao) { this.sensacao = sensacao; }
    public String getFim() { return fim; }
    public void setFim(String fim) { this.fim = fim; }
    public String getRecomecar() { return recomecar; }
    public void setRecomecar(String recomecar) { this.recomecar = recomecar; }
}
</code></pre>

<h2> JanelaVoz.java </h2>
<pre><code class="language-java">
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaVoz extends JFrame {

    private JTextField tomField;
    private JTextField revoltaField;
    private JTextField melhoriaField;
    private JTable vozTable;
    private DefaultTableModel tableModel;

    public JanelaVoz() {
        super("CRUD - Voz");

        tomField = new JTextField(15);
        revoltaField = new JTextField(15);
        melhoriaField = new JTextField(15);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(new JLabel("Tom:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(tomField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(new JLabel("Revolta:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(revoltaField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(new JLabel("Melhoria (%):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; inputPanel.add(melhoriaField, gbc);

        String[] columnNames = {"ID", "Tom", "Revolta", "Melhoria (%)", "Método"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        vozTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(vozTable);
        scrollPane.setPreferredSize(new Dimension(550, 200));

        vozTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && vozTable.getSelectedRow() != -1) {
                preencherCamposComSelecao();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar ");
        JButton deleteButton = new JButton("Remover");
        JButton clearButton = new JButton("Limpar Campos");

        buttonPanel.add(addButton); buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton); buttonPanel.add(clearButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this::adicionarVoz);
        updateButton.addActionListener(this::atualizarVoz);
        deleteButton.addActionListener(this::deletarVoz);
        clearButton.addActionListener(e -> limparCampos());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        carregarRegistros();
    }

    private void adicionarVoz(ActionEvent e) {
        if (tomField.getText().isEmpty() || melhoriaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha Tom e Melhoria.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int melhoria = Integer.parseInt(melhoriaField.getText());
            Voz novaVoz = new Voz(tomField.getText(), revoltaField.getText(), melhoria);
            Main.inserirVoz(novaVoz);
            limparCampos();
            carregarRegistros();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Melhoria deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarVoz(ActionEvent e) {
        int selectedRow = vozTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int melhoria = Integer.parseInt(melhoriaField.getText());
            Voz vozAtualizada = new Voz(id, tomField.getText(), revoltaField.getText(), melhoria);
            Main.atualizarVoz(vozAtualizada);
            JOptionPane.showMessageDialog(this, "Registro ID " + id + " atualizado com sucesso!");
            limparCampos();
            carregarRegistros();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Melhoria deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarVoz(ActionEvent e) {
        int selectedRow = vozTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o registro ID " + id + "?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Main.deletarVoz(id);
                JOptionPane.showMessageDialog(this, "Registro ID " + id + " removido com sucesso!");
                limparCampos();
                carregarRegistros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarRegistros() {
        tableModel.setRowCount(0);
        List<Voz> vozes = Main.listarTodasVozes();
        for (Voz v : vozes) {
            tableModel.addRow(new Object[]{
                    v.getId(),
                    v.getTom(),
                    v.getRevolta(),
                    v.getMelhoria(),
                    v.ouvirTom()
            });
        }
    }

    private void preencherCamposComSelecao() {
        int selectedRow = vozTable.getSelectedRow();
        if (selectedRow != -1) {
            tomField.setText((String) tableModel.getValueAt(selectedRow, 1));
            revoltaField.setText((String) tableModel.getValueAt(selectedRow, 2));
            melhoriaField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 3)));
        }
    }

    private void limparCampos() {
        tomField.setText("");
        revoltaField.setText("");
        melhoriaField.setText("");
        vozTable.clearSelection();
    }
}
</code></pre>

<h2> JanelaRitmo.java </h2>
<pre><code class="language-java">
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaRitmo extends JFrame {

    private JTextField ouvirField;
    private JTextField palavrasField;
    private JTextField vazioField;
    private JTable ritmoTable;
    private DefaultTableModel tableModel;

    public JanelaRitmo() {
        super("CRUD - Ritmo");

        ouvirField = new JTextField(15);
        palavrasField = new JTextField(15);
        vazioField = new JTextField(15);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(new JLabel("Ouvir:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(ouvirField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(new JLabel("Palavras:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(palavrasField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(new JLabel("Vazio:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; inputPanel.add(vazioField, gbc);

        String[] columnNames = {"ID", "Ouvir", "Palavras", "Vazio", "Firme?"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        ritmoTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ritmoTable);
        scrollPane.setPreferredSize(new Dimension(550, 200));

        ritmoTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && ritmoTable.getSelectedRow() != -1) {
                preencherCamposComSelecao();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Remover");
        JButton clearButton = new JButton("Limpar Campos");

        buttonPanel.add(addButton); buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton); buttonPanel.add(clearButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this::adicionarRitmo);
        updateButton.addActionListener(this::atualizarRitmo);
        deleteButton.addActionListener(this::deletarRitmo);
        clearButton.addActionListener(e -> limparCampos());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        carregarRegistros();
    }

    private void adicionarRitmo(ActionEvent e) {
        if (ouvirField.getText().isEmpty() || palavrasField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha Percepção e Palavras.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Ritmo novoRitmo = new Ritmo(ouvirField.getText(), palavrasField.getText(), vazioField.getText());
            Main.inserirRitmo(novoRitmo);
            limparCampos();
            carregarRegistros();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarRitmo(ActionEvent e) {
        int selectedRow = ritmoTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Ritmo ritmoAtualizado = new Ritmo(id, ouvirField.getText(), palavrasField.getText(), vazioField.getText());
            Main.atualizarRitmo(ritmoAtualizado);
            JOptionPane.showMessageDialog(this, "Registro ID " + id + " atualizado com sucesso!");
            limparCampos();
            carregarRegistros();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarRitmo(ActionEvent e) {
        int selectedRow = ritmoTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o registro ID " + id + "?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Main.deletarRitmo(id);
                JOptionPane.showMessageDialog(this, "Registro ID " + id + " removido com sucesso!");
                limparCampos();
                carregarRegistros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarRegistros() {
        tableModel.setRowCount(0);
        List<Ritmo> ritmos = Main.listarTodosRitmos();
        for (Ritmo r : ritmos) {
            tableModel.addRow(new Object[]{
                    r.getId(),
                    r.getOuvir(),
                    r.getPalavras(),
                    r.getVazio(),
                    r.parecerFirme()
            });
        }
    }

    private void preencherCamposComSelecao() {
        int selectedRow = ritmoTable.getSelectedRow();
        if (selectedRow != -1) {
            ouvirField.setText((String) tableModel.getValueAt(selectedRow, 1));
            palavrasField.setText((String) tableModel.getValueAt(selectedRow, 2));
            vazioField.setText((String) tableModel.getValueAt(selectedRow, 3));
        }
    }

    private void limparCampos() {
        ouvirField.setText("");
        palavrasField.setText("");
        vazioField.setText("");
        ritmoTable.clearSelection();
    }
}
</code></pre>

<h2> JanelaFogo.java </h2>
<pre><code class="language-java">
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaFogo extends JFrame {

    private JTextField sensacaoField;
    private JTextField fimField;
    private JTextField recomecarField;
    private JTable fogoTable;
    private DefaultTableModel tableModel;

    public JanelaFogo() {
        super("CRUD - Fogo");

        sensacaoField = new JTextField(15);
        fimField = new JTextField(15);
        recomecarField = new JTextField(15);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(new JLabel("Sensação:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(sensacaoField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(new JLabel("Fim:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(fimField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(new JLabel("Recomeçar:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; inputPanel.add(recomecarField, gbc);

        String[] columnNames = {"ID", "Sensação", "Fim", "Recomeçar", "Método"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        fogoTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(fogoTable);
        scrollPane.setPreferredSize(new Dimension(550, 200));

        fogoTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && fogoTable.getSelectedRow() != -1) {
                preencherCamposComSelecao();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Remover");
        JButton clearButton = new JButton("Limpar Campos");

        buttonPanel.add(addButton); buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton); buttonPanel.add(clearButton);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this::adicionarFogo);
        updateButton.addActionListener(this::atualizarFogo);
        deleteButton.addActionListener(this::deletarFogo);
        clearButton.addActionListener(e -> limparCampos());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        carregarRegistros();
    }

    private void adicionarFogo(ActionEvent e) {
        if (sensacaoField.getText().isEmpty() || fimField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha Sensação e Fim.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Fogo novoFogo = new Fogo(sensacaoField.getText(), fimField.getText(), recomecarField.getText());
            Main.inserirFogo(novoFogo);
            limparCampos();
            carregarRegistros();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarFogo(ActionEvent e) {
        int selectedRow = fogoTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Fogo fogoAtualizado = new Fogo(id, sensacaoField.getText(), fimField.getText(), recomecarField.getText());
            Main.atualizarFogo(fogoAtualizado);
            JOptionPane.showMessageDialog(this, "Registro ID " + id + " atualizado com sucesso!");
            limparCampos();
            carregarRegistros();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarFogo(ActionEvent e) {
        int selectedRow = fogoTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o registro ID " + id + "?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Main.deletarFogo(id);
                JOptionPane.showMessageDialog(this, "Registro ID " + id + " removido com sucesso!");
                limparCampos();
                carregarRegistros();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarRegistros() {
        tableModel.setRowCount(0);
        List<Fogo> fogos = Main.listarTodosFogos();
        for (Fogo f : fogos) {
            tableModel.addRow(new Object[]{
                    f.getId(),
                    f.getSensacao(),
                    f.getFim(),
                    f.getRecomecar(),
                    f.acharSensacao()
            });
        }
    }

    private void preencherCamposComSelecao() {
        int selectedRow = fogoTable.getSelectedRow();
        if (selectedRow != -1) {
            sensacaoField.setText((String) tableModel.getValueAt(selectedRow, 1));
            fimField.setText((String) tableModel.getValueAt(selectedRow, 2));
            recomecarField.setText((String) tableModel.getValueAt(selectedRow, 3));
        }
    }

    private void limparCampos() {
        sensacaoField.setText("");
        fimField.setText("");
        recomecarField.setText("");
        fogoTable.clearSelection();
    }
}
}
</code></pre>






