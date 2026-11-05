
import java.util.Scanner;

public class Login {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String name1;
        String name2;

        String senha1;
        String senha2;

        int contador = 0;


        System.out.println("\n*************");
        System.out.println("**** Cadastro ****");
        System.out.println("*************\n");

        System.out.println("Crie seu nome de usuario:");
        name1 = sc.nextLine();

        System.out.println("\nCrie sua senha:");
        senha1 = sc.nextLine();

        System.out.println("\nCadastro realizado com sucesso!");


        while (contador < 3) {

            System.out.println("\n*************");
            System.out.println("**** Login ****");
            System.out.println("*************\n");

            System.out.println("Digite o nome de usuario:\n");
            name2 = sc.nextLine();

            if (name1.equals(name2)) {

                System.out.println("\n** Nome correto **\n");

                System.out.println("Digite a sua senha:\n");
                senha2 = sc.nextLine();

                if (senha1.equals(senha2)) {

                    System.out.println("\n***********************************");
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
            sc.close();
            return;
        }


        sc.close();
    }
}
