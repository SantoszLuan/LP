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