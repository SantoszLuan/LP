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