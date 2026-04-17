package AlunoIMC;

import SQL.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class READ {

    // Busca todos os alunos para preencher a JTable
    public List<Aluno> listar() throws Exception {
        String sql = "SELECT * FROM alunos";
        List<Aluno> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setEndereco(rs.getString("endereco"));
                a.setTelefone(rs.getString("telefone"));
                a.setCpf(rs.getString("cpf"));
                a.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                a.setFatorRH(rs.getString("fator_rh"));
                a.setCurso(rs.getString("curso"));
                a.setContatoEmergencia(rs.getString("contato_emergencia"));
                a.setTelefoneEmergencia(rs.getString("telefone_emergencia"));
                a.setPeso(rs.getDouble("peso"));
                a.setAltura(rs.getDouble("altura"));
                a.setImc(rs.getDouble("imc"));
                lista.add(a);
            }
        }
        return lista;
    }

    // Gerador do texto do relatório estatístico
    public String gerarRelatorio() throws Exception {
        StringBuilder rel = new StringBuilder();
        rel.append("---------- RELATÓRIO ESTATÍSTICO ----------\n\n");

        try (Connection conn = Conexao.conectar()) {

            // Média IMC
            try (PreparedStatement ps = conn.prepareStatement("SELECT AVG(imc) as media FROM alunos");
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rel.append(String.format("Média de IMC Geral: %.2f\n", rs.getDouble("media")));
                }
            }

            // Média de Peso
            try (PreparedStatement ps = conn.prepareStatement("SELECT AVG(peso) as media_p FROM alunos");
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rel.append(String.format("Média de Peso Geral: %.2f kg\n", rs.getDouble("media_p")));
                }
            }

            // Maior Peso
            String sqlMax = "SELECT nome, peso, tipo_sanguineo, fator_rh FROM alunos ORDER BY peso DESC LIMIT 1";
            try (PreparedStatement ps = conn.prepareStatement(sqlMax);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rel.append("\n> MAIOR PESO:\n");
                    rel.append("  Nome: ").append(rs.getString("nome")).append("\n");
                    rel.append("  Peso: ").append(rs.getDouble("peso")).append(" kg\n");
                    rel.append("  Sangue: ").append(rs.getString("tipo_sanguineo"))
                            .append(rs.getString("fator_rh")).append("\n");
                }
            }

            // Menor Altura
            String sqlMin = "SELECT nome, altura FROM alunos ORDER BY altura ASC LIMIT 1";
            try (PreparedStatement ps = conn.prepareStatement(sqlMin);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rel.append("\n> MENOR ALTURA:\n");
                    rel.append("  Nome: ").append(rs.getString("nome")).append("\n");
                    rel.append("  Altura: ").append(rs.getDouble("altura")).append(" m\n");
                }
            }
        }
        return rel.toString();
    }
}
