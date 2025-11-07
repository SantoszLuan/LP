


<h2> 1. Média</h2>
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

<h2>🎵 2. Música</h2>
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


<h2>🖼️ 3. Imagem — Vik Muniz</h2>
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



<h2>🎨 4. Imagem — Doodle</h2>

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

<h2>🎥 5. Vídeo</h2>

<a href="https://www.youtube.com/watch?v=vh6SDvDhDN4"> <h3> Cena Final - Clube da Luta </h3> </a>


