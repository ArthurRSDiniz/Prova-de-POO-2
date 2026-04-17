package AlunoIMC;
import SQL.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void cadastrar(Aluno a) throws Exception {

        String sql = "INSERT INTO alunos (nome, endereco, telefone, cpf, tipo_sanguineo, fator_rh, curso, contato_emergencia," +
                     " telefone_emergencia, peso, altura, imc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            preencherStatement(ps, a);
            ps.execute();
        }
    }

    public void alterar(Aluno a) throws Exception {

        String sql = "UPDATE alunos SET nome=?, endereco=?, telefone=?, cpf=?, tipo_sanguineo=?, fator_rh=?, curso=?," +
                     " contato_emergencia=?, telefone_emergencia=?, peso=?, altura=?, imc=? WHERE id=?";

        try (Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            preencherStatement(ps, a);
            ps.setInt(13, a.getId());
            ps.executeUpdate();
        }
    }

    public void remover(int id) throws Exception {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    private void preencherStatement(PreparedStatement ps, Aluno a) throws SQLException {
        ps.setString(1, a.getNome());
        ps.setString(2, a.getEndereco());
        ps.setString(3, a.getTelefone());
        ps.setString(4, a.getCpf());
        ps.setString(5, a.getTipoSanguineo());
        ps.setString(6, a.getFatorRH());
        ps.setString(7, a.getCurso());
        ps.setString(8, a.getContatoEmergencia());
        ps.setString(9, a.getTelefoneEmergencia());
        ps.setDouble(10, a.getPeso());
        ps.setDouble(11, a.getAltura());
        ps.setDouble(12, a.getImc());
    }
}

// DAO = Data Access object ou objeto de acesso a dados
// é um padrão de projeto (design pattern) amplamente utilizado na engenharia de software
// finalidade: separar as regras de negócio das regras de acesso ao banco de dados.



