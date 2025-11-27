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