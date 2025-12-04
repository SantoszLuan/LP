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