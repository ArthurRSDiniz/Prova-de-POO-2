package Principal;
import javax.swing.SwingUtilities;
// SwingUtilities abrir janela sem risco de travar
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            // SwingUtilities abrir janela sem risco de travar
            @Override
            public void run() {
                // Cria o objeto da sua tela
                Formulario tela = new Formulario();

                // Faz o objeto aparecer
                tela.setVisible(true);
            }
        });
    }
}