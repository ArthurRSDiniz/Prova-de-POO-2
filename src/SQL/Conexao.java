package SQL;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/alunos",
                "root",
                "SUA_SENHA"
        );
    }
}
