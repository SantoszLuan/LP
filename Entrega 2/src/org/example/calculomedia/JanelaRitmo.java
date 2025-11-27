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