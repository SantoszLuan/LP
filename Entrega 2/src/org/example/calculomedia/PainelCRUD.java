import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PainelCRUD extends JPanel {

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
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setPreferredSize(new Dimension(700, 250));

        dataTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && dataTable.getSelectedRow() != -1) {
                preencherCamposComSelecao();
            }
        });

        // Configurar os Campos de Entrada
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        inputFields = new JTextField[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            inputFields[i] = new JTextField(20);
            gbc.gridx = 0; gbc.gridy = i; inputPanel.add(new JLabel(fieldNames[i] + ":"), gbc);
            gbc.gridx = 1; gbc.gridy = i; inputPanel.add(inputFields[i], gbc);
        }

        // Configurar os Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Remover");
        JButton refreshButton = new JButton("Recarregar");
        JButton clearButton = new JButton("Limpar Campos");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(clearButton);

        // 4. Layout Principal
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
        } else { // Fogo
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

    // Métodos de Ação

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
            // Mensagem de erro ao iniciar
            System.err.println("Erro ao carregar registros: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o Painel: " + ex.getMessage(), "Erro Fatal", JOptionPane.ERROR_MESSAGE);
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