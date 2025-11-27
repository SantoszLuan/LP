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