package br.com.apsoo.view;

import br.com.apsoo.dao.ClienteDAO;
import br.com.apsoo.model.Cliente;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ClienteView extends JFrame {

    private JTextField txtCpf, txtNome, txtRua, txtNumero, txtBairro, txtCidade, txtFone, txtEmail, txtDataNasc;
    private JComboBox<String> cbUf;
    private JButton btnConfirmar, btnCancelar;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public ClienteView() {
        setTitle("Cadastrar Cliente");
        setSize(420, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com margens externas (padding) para a janela "respirar"
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        setContentPane(mainPanel);

        // Formulário estilo Grid de 11 linhas e 2 colunas com espaçamento entre elementos
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 8, 8));

        // Instancia os componentes
        txtCpf = new JTextField();
        txtNome = new JTextField();
        txtRua = new JTextField();
        txtNumero = new JTextField();
        txtBairro = new JTextField();
        txtCidade = new JTextField();
        cbUf = new JComboBox<>(new String[]{
            "MS", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", 
            "MA", "MG", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", 
            "RO", "RR", "RS", "SC", "SE", "SP", "TO"
        });
        txtFone = new JTextField();
        txtEmail = new JTextField();
        txtDataNasc = new JTextField();

        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");

        // Adiciona os rótulos e campos na ordem original
        formPanel.add(new JLabel("CPF*:")); formPanel.add(txtCpf);
        formPanel.add(new JLabel("Nome*:")); formPanel.add(txtNome);
        formPanel.add(new JLabel("Rua:")); formPanel.add(txtRua);
        formPanel.add(new JLabel("Nº:")); formPanel.add(txtNumero);
        formPanel.add(new JLabel("Bairro:")); formPanel.add(txtBairro);
        formPanel.add(new JLabel("Cidade:")); formPanel.add(txtCidade);
        formPanel.add(new JLabel("UF:")); formPanel.add(cbUf);
        formPanel.add(new JLabel("Fone*:")); formPanel.add(txtFone);
        formPanel.add(new JLabel("Email*:")); formPanel.add(txtEmail);
        formPanel.add(new JLabel("Data Nasc. (dd/MM/yyyy)*:")); formPanel.add(txtDataNasc);

        // Adiciona os botões no final da grid
        formPanel.add(btnCancelar);
        formPanel.add(btnConfirmar);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Ações dos botões
        btnConfirmar.addActionListener(e -> executarCadastro());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void executarCadastro() {
        try {
            String cpf = txtCpf.getText().trim();
            String nome = txtNome.getText().trim();
            String fone = txtFone.getText().trim();
            String email = txtEmail.getText().trim();
            String dataStr = txtDataNasc.getText().trim();

            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "CPF não preenchido.");
                txtCpf.requestFocus();
                return;
            }

            if (cpf.length() < 11) {
                JOptionPane.showMessageDialog(this, "CPF inválido.");
                txtCpf.requestFocus();
                return;
            }

            if (nome.isEmpty() || fone.isEmpty() || email.isEmpty() || dataStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios (*).");
                return;
            }

            if (clienteDAO.buscarPorCpf(cpf) != null) {
                JOptionPane.showMessageDialog(this, "Cliente já cadastrado.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNasc = LocalDate.parse(dataStr, formatter);

            Cliente cliente = new Cliente(
                null, cpf, nome, txtRua.getText(), txtNumero.getText(),
                txtBairro.getText(), txtCidade.getText(), (String) cbUf.getSelectedItem(),
                fone, email, dataNasc
            );

            if (!cliente.isMaiorDeIdade()) {
                JOptionPane.showMessageDialog(this, "O cliente deve ser maior de idade (18 anos ou mais).");
                return;
            }

            clienteDAO.incluir(cliente);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso.");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na operação: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteView().setVisible(true));
    }
}