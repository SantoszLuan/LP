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