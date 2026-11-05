
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcao;

        System.out.println("\n======================================");
        System.out.println("          MENU DA IMPRESSORA");
        System.out.println("======================================");

        System.out.println("1 - Configurar conexao");
        System.out.println("2 - Abrir conexao");
        System.out.println("3 - Fechar conexao");
        System.out.println("4 - Imprimir texto");
        System.out.println("5 - Imprimir QR Code");
        System.out.println("6 - Imprimir codigo de barras");
        System.out.println("7 - Imprimir XML SAT");
        System.out.println("8 - Imprimir XML de cancelamento SAT");
        System.out.println("9 - Abrir gaveta");
        System.out.println("10 - Sinal sonoro (bip)");
        System.out.println("--------------------------------------");
        System.out.println("11 - Avancar papel");
        System.out.println("12 - Status da impressora");
        System.out.println("======================================");

        System.out.print("Escolha uma opcao: ");
        opcao = sc.nextInt();

        switch (opcao) {

            case 1:
                System.out.println("Configuracao da conexao");
                System.out.println("Digite o tipo de conexao:");
                break;

            case 2:
                System.out.println("Comando DLL: AbreConexaoImpressora");
                break;

            case 3:
                System.out.println("Comando DLL: FechaConexaoImpressora");
                break;

            case 4:
                System.out.println("Comando DLL: ImpressaoTexto");
                break;

            case 5:
                System.out.println("Comando DLL: ImpressaoQRCode");
                break;

            case 6:
                System.out.println("Comando DLL: ImpressaoCodigoBarras");
                break;

            case 7:
                System.out.println("Comando DLL: ImprimeXMLSAT");
                break;

            case 8:
                System.out.println("Comando DLL: ImprimeXMLCancelamentoSAT");
                break;

            case 9:
                System.out.println("Comando DLL: AbreGaveta");
                break;

            case 10:
                System.out.println("Comando DLL: SinalSonoro");
                break;

            case 11:
                System.out.println("Comando DLL: AvancaPapel");
                break;

            case 12:
                System.out.println("Comando DLL: StatusImpressora");
                break;

            default:
                System.out.println("Opcao invalida!");
                break;
        }

        sc.close();
    }
}
