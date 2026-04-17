package AlunoIMC;

public class CalculoIMC {
    public static double calcular(double peso, double altura) {
        return peso / (altura * altura);
    }

    public static String obterMensagem(double imc) {
        if (imc < 18.5) return "Você está abaixo do peso ideal";
        if (imc <= 25) return "Peso Ideal";
        return "Você está acima do peso ideal!";
    }
}
