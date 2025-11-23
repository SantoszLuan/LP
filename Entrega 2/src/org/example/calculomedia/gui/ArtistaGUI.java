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