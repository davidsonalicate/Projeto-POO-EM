import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Usuario usuario = new Usuario();
        Impressora impressora = new Impressora();

        String nome;
        String senha;

        int contador = 0;
        int acesso = 0;

        System.out.println("\n******** CADASTRO ********");

        System.out.print("Crie seu nome de usuario: ");
        nome = sc.nextLine();

        System.out.print("Crie sua senha: ");
        senha = sc.nextLine();

        usuario.cadastrar(nome, senha);

        System.out.println("\nCadastro realizado com sucesso!");

        while (contador < 3) {

            System.out.println("\n******** LOGIN ********");

            System.out.print("Digite seu nome: ");
            nome = sc.nextLine();

            System.out.print("Digite sua senha: ");
            senha = sc.nextLine();

            if (usuario.verificarLogin(nome, senha) == 1) {
                System.out.println("\nAcesso permitido. Bem-vindo, " + nome + "!");
                acesso = 1;
                break;
            } else {
                contador++;
                System.out.println("\nUsuario ou senha incorretos.");
                System.out.println("Tentativas restantes: " + (3 - contador));
            }
        }

        if (acesso == 0) {
            System.out.println("\nVoce errou 3 vezes. Sistema encerrado.");
            sc.close();
            return;
        }

        int opcao = 0;

        while (opcao != 13) {

            System.out.println("\n========== MENU DA IMPRESSORA ==========");
            System.out.println("1 - Configurar conexao");
            System.out.println("2 - Abrir conexao");
            System.out.println("3 - Fechar conexao");
            System.out.println("4 - Imprimir texto");
            System.out.println("5 - Imprimir QR Code");
            System.out.println("6 - Imprimir codigo de barras");
            System.out.println("7 - Imprimir XML SAT");
            System.out.println("8 - Imprimir XML de cancelamento SAT");
            System.out.println("9 - Abrir gaveta");
            System.out.println("10 - Sinal sonoro");
            System.out.println("11 - Avancar papel");
            System.out.println("12 - Consultar status");
            System.out.println("13 - Sair");
            System.out.println("========================================");

            System.out.print("Escolha uma opcao: ");

            try {

                opcao = Integer.parseInt(sc.nextLine().trim());

                switch (opcao) {

                    case 1:
                        System.out.print("Tipo de conexao: ");
                        String tipo = sc.nextLine();

                        System.out.print("Modelo da impressora: ");
                        String modelo = sc.nextLine();

                        System.out.print("Porta ou conexao: ");
                        String porta = sc.nextLine();

                        System.out.print("Parametro: ");
                        String parametro = sc.nextLine();

                        impressora.configurar(tipo, modelo, porta, parametro);
                        break;

                    case 2:
                        impressora.abrirConexao();
                        break;

                    case 3:
                        impressora.fecharConexao();
                        break;

                    case 4:
                        impressora.imprimirTexto(sc);
                        break;

                    case 5:
                        impressora.imprimirQRCode(sc);
                        break;

                    case 6:
                        impressora.imprimirCodigoBarras(sc);
                        break;

                    case 7:
                        impressora.imprimirXMLSAT();
                        break;

                    case 8:

                        impressora.imprimirXMLCancelamentoSAT(sc);
                        break;

                    case 9:
                        impressora.abrirGaveta();
                        break;

                    case 10:
                        impressora.sinalSonoro();
                        break;

                    case 11:
                        impressora.avancarPapel(sc);
                        break;

                    case 12:
                        impressora.statusImpressora();
                        break;

                    case 13:
                        System.out.println("Saindo do sistema...");
                        break;

                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas numeros!");
            }
        }

        if (impressora.conexaoAberta == 1) {
            impressora.fecharConexao();
        }

        sc.close();
    }
}