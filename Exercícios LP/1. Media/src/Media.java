import java.util.Scanner;

public class Media {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Entradas
        System.out.print("Digite a nota P1: ");
        double P1 = sc.nextDouble();

        System.out.print("Digite a nota E1: ");
        double E1 = sc.nextDouble();

        System.out.print("Digite a nota E2: ");
        double E2 = sc.nextDouble();

        System.out.print("Digite o valor de X: ");
        double X = sc.nextDouble();

        System.out.print("Digite a nota SUB: ");
        double SUB = sc.nextDouble();

        System.out.print("Digite a nota API: ");
        double API = sc.nextDouble();


        //Calculo

        double base = (P1 * 0.5) + (E1 * 0.2) + (E2 * 0.3) + X + (SUB * 0.15);
        double divisor = base - 5.9;
        double fator;

        if (divisor == 0) {
            fator = 0;
        } else {
            fator = Math.max(divisor, 0) / divisor;
        }

        double media = (base * 0.5) + (fator * API * 0.5);

        // precisa desse código '%.2f%n' para reconhecer no printf
        System.out.printf("A média calculada é: %.2f%n", media);
    }
}
