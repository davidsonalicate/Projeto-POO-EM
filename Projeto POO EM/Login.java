
import java.util.Scanner;

public class Login {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String name1 = "Admin";
        String name2;

        String senha1 = "1234";
        String senha2;

        int contador = 0;

        while (contador < 3) {

            System.out.println("\n*************");
            System.out.println("**** Login ****");
            System.out.println("*************\n");

            System.out.println("Digite o nome de usuario:\n");
            name2 = sc.nextLine();

            if (name1.equals(name2)) {

                System.out.println("\n*********************");
                System.out.println("** Nome correto **");
                System.out.println("*********************");

                System.out.println("\nDigite a sua senha:\n");
                senha2 = sc.nextLine();

                if (senha1.equals(senha2)) {

                    System.out.println("***********************************");
                    System.out.println("Acesso permitido. Bem-vindo, " + name1 + "!");
                    System.out.println("***********************************");

                    break;

                } else {

                    System.out.println("\n** Senha incorreta. **\n");
                    contador++;
                }

            } else {

                System.out.println("\n** Nome incorreto. **\n");
                contador++;
            }
        }

        if (contador == 3) {

            System.out.println("\n** Voce errou 3 vezes. **\n");

        }

        sc.close();
    }
}
