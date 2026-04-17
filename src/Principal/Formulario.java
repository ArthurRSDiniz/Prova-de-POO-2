package Principal;

import AlunoIMC.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Formulario extends JFrame {
    // Definição dos Componentes (Variáveis da Classe)
    private JTextField txtID = new JTextField(5);
    private JTextField txtNome = new JTextField(20);
    private JTextField txtEndereco = new JTextField(20);
    private JFormattedTextField txtTelefone, txtCPF, txtTelefoneEmergencia;
    private JComboBox<String> cbSangue, cbRH, cbCurso;
    private JTextField txtContatoEmergencia = new JTextField(15);
    private JTextField txtPeso = new JTextField(5), txtAltura = new JTextField(5);
    private JLabel lblIMCValor = new JLabel("IMC: 0.00");

    // montagem da tela:
    public Formulario() {
        setTitle("Avaliação 01 - Sistema de Matrícula");
        setSize(550, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // GridLayout = linhas, colunas, espaçamentoH, espaçamentoV)
        setLayout(new GridLayout(0, 2, 10, 10));

        try {
            txtTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
            txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
            txtTelefoneEmergencia = new JFormattedTextField(new MaskFormatter("(##) #####-####"));

            cbSangue = new JComboBox<>(new String[]{"A", "B", "AB", "O"});
            cbRH = new JComboBox<>(new String[]{"+", "-"});
            cbCurso = new JComboBox<>(new String[]{"Direito", "Ciência da Computação", "Sistemas De Informação", "Medicina", "Psicologia", "Nutrição"});

            // componentes do Layout
            add(new JLabel(" ID (para Alterar/Remover):")); add(txtID);
            add(new JLabel(" Nome Completo:")); add(txtNome);
            add(new JLabel(" Endereço:")); add(txtEndereco);
            add(new JLabel(" Telefone:")); add(txtTelefone);
            add(new JLabel(" CPF:")); add(txtCPF);
            add(new JLabel(" Tipo Sanguíneo:")); add(cbSangue);
            add(new JLabel(" Fator RH:")); add(cbRH);
            add(new JLabel(" Curso:")); add(cbCurso);
            add(new JLabel(" Contato Emergência:")); add(txtContatoEmergencia);
            add(new JLabel(" Tel. Emergência:")); add(txtTelefoneEmergencia);
            add(new JLabel(" Peso (kg):")); add(txtPeso);
            add(new JLabel(" Altura (ex: 1.75):")); add(txtAltura);
            add(new JLabel(" Resultado:")); add(lblIMCValor);

            // Criação dos Botões
            JButton btnCalcular = new JButton("Calcular IMC");
            JButton btnCadastrar = new JButton("Cadastrar");
            JButton btnAlterar = new JButton("Alterar");
            JButton btnRemover = new JButton("Remover");
            JButton btnListagem = new JButton("Listagem");
            JButton btnRelatorio = new JButton("Relatório");

            add(btnCalcular); add(btnCadastrar);
            add(btnAlterar); add(btnRemover);
            add(btnListagem); add(btnRelatorio);

            // botão de calcular
            btnCalcular.addActionListener(e -> {
                try {
                    double p = Double.parseDouble(txtPeso.getText().replace(",", "."));
                    double a = Double.parseDouble(txtAltura.getText().replace(",", "."));
                    double imc = CalculoIMC.calcular(p, a);
                    lblIMCValor.setText(String.format("IMC: %.2f", imc));
                    JOptionPane.showMessageDialog(this, CalculoIMC.obterMensagem(imc));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: Verifique Peso e Altura.");
                }
            });

            // Botão de cadastrar
            btnCadastrar.addActionListener(e -> {
                try {
                    Aluno al = popularAluno();
                    new AlunoDAO().cadastrar(al);
                    JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                    limparCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
                }
            });

            // botõa de alterar
            btnAlterar.addActionListener(e -> {
                try {
                    Aluno al = popularAluno();
                    new AlunoDAO().alterar(al);
                    JOptionPane.showMessageDialog(this, "Dados alterados com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao alterar: " + ex.getMessage());
                }
            });

            // botao de remover
            btnRemover.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    new AlunoDAO().remover(id);
                    JOptionPane.showMessageDialog(this, "Removido com sucesso!");
                    limparCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: Informe um ID válido.");
                }
            });

            // botão da listagem
            btnListagem.addActionListener(e -> {
                try {
                    List<Aluno> lista = new READ().listar();
                    String[] colunas = {"ID", "Nome", "Curso", "IMC"};
                    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(colunas, 0);
                    for (Aluno a : lista) {
                        modelo.addRow(new Object[]{a.getId(), a.getNome(), a.getCurso(), String.format("%.2f", a.getImc())});
                    }
                    JTable tabela = new JTable(modelo);
                    JDialog dial = new JDialog(this, "Lista de Alunos", true);
                    dial.add(new JScrollPane(tabela));
                    dial.setSize(500, 400);
                    dial.setLocationRelativeTo(this);
                    dial.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
                }
            });

            // botão do telatorio
            btnRelatorio.addActionListener(e -> {
                try {
                    String textoRel = new READ().gerarRelatorio();
                    JTextArea area = new JTextArea(textoRel);
                    area.setEditable(false);
                    JOptionPane.showMessageDialog(this, new JScrollPane(area), "Relatório", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro no relatório: " + ex.getMessage());
                }
            });

        } catch (Exception e) {
            System.out.println("Erro na montagem da tela: " + e.getMessage());
        }
    }

    // aqui eu transformei os campos da tela no objeto aluno
    private Aluno popularAluno() {
        Aluno a = new Aluno();
        if(!txtID.getText().isEmpty()) a.setId(Integer.parseInt(txtID.getText()));
        a.setNome(txtNome.getText());
        a.setEndereco(txtEndereco.getText());
        a.setTelefone(txtTelefone.getText());
        a.setCpf(txtCPF.getText());
        a.setTipoSanguineo(cbSangue.getSelectedItem().toString());
        a.setFatorRH(cbRH.getSelectedItem().toString());
        a.setCurso(cbCurso.getSelectedItem().toString());
        a.setContatoEmergencia(txtContatoEmergencia.getText());
        a.setTelefoneEmergencia(txtTelefoneEmergencia.getText());
        a.setPeso(Double.parseDouble(txtPeso.getText().replace(",", ".")));
        a.setAltura(Double.parseDouble(txtAltura.getText().replace(",", ".")));
        a.setImc(CalculoIMC.calcular(a.getPeso(), a.getAltura()));
        return a;
    }

    private void limparCampos() {
        txtID.setText(""); txtNome.setText(""); txtEndereco.setText("");
        txtPeso.setText(""); txtAltura.setText(""); txtCPF.setText("");
    }
}

