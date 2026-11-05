
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Impressora {

    int tipo;
    String modelo;
    String porta;
    int parametro;

    int conexaoAberta = 0;

    public void configurar(String novoTipo, String novoModelo, String novaPorta, String novoParametro) {

        try {
            tipo = Integer.parseInt(novoTipo);
            parametro = Integer.parseInt(novoParametro);

            modelo = novoModelo;
            porta = novaPorta;

            conexaoAberta = 0;

            System.out.println("Configuracao realizada!");

        } catch (NumberFormatException e) {
            System.out.println("Tipo e parametro devem ser numeros!");
        }
    }

    public void abrirConexao() {

        if (modelo == null || porta == null) {
            System.out.println("Configure a impressora primeiro!");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(
                    tipo, modelo, porta, parametro
            );

            if (retorno == 0) {
                conexaoAberta = 1;
                System.out.println("Impressora conectada com sucesso!");
            } else {
                conexaoAberta = 0;
                System.out.println("Erro ao conectar. Codigo: " + retorno);
            }

        } catch (UnsatisfiedLinkError | ExceptionInInitializerError e) {
            conexaoAberta = 0;
            System.out.println("Nao foi possivel carregar a DLL Elgin.");
            System.out.println("Verifique a pasta da DLL e a arquitetura do Java.");

        } catch (Exception e) {
            conexaoAberta = 0;
            System.out.println("Erro na conexao: " + e.getMessage());
        }
    }

    public void fecharConexao() {

        if (conexaoAberta == 1) {

            try {
                int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

                if (retorno == 0) {
                    conexaoAberta = 0;
                    System.out.println("Conexao fechada!");
                } else {
                    System.out.println("Erro ao fechar conexao. Codigo: " + retorno);
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } else {
            System.out.println("A conexao ja esta fechada.");
        }
    }

    public void imprimirTexto(Scanner sc) {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(texto, 0, 0, 0);

        if (retorno >= 0) {
            System.out.println("Texto enviado para impressao!");
        } else {
            System.out.println("Erro ao imprimir texto: " + retorno);
        }
    }

    public void imprimirQRCode(Scanner sc) {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        System.out.print("Digite o conteudo do QR Code: ");
        String codigo = sc.nextLine();

        int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(codigo, 5, 0);

        if (retorno == 0) {
            System.out.println("QR Code enviado com sucesso!");
        } else {
            System.out.println("Erro ao imprimir QR Code: " + retorno);
        }
    }

    public void imprimirCodigoBarras(Scanner sc) {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        System.out.print("Digite o codigo de barras: ");
        String codigo = sc.nextLine();

        int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(
                0, codigo, 100, 2, 0
        );

        if (retorno == 0) {
            System.out.println("Codigo de barras enviado!");
        } else {
            System.out.println("Erro ao imprimir codigo: " + retorno);
        }
    }

    public void imprimirXMLSAT(Scanner sc) {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        System.out.print("Digite o caminho do XML SAT: ");
        String arquivo = sc.nextLine();

        try {
            String xml = Files.readString(Paths.get(arquivo));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, parametro);

            if (retorno == 0) {
                System.out.println("XML SAT enviado com sucesso!");
            } else {
                System.out.println("Erro ao imprimir XML: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao abrir o arquivo XML: " + e.getMessage());
        }
    }

    public void imprimirXMLCancelamentoSAT(Scanner sc) {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        System.out.print("Caminho do XML de cancelamento: ");
        String arquivo = sc.nextLine();

        System.out.print("Assinatura do QR Code: ");
        String assinatura = sc.nextLine();

        try {
            String xml = Files.readString(Paths.get(arquivo));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(
                    xml, assinatura, parametro
            );

            if (retorno == 0) {
                System.out.println("XML de cancelamento enviado!");
            } else {
                System.out.println("Erro ao imprimir XML: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao abrir o arquivo XML: " + e.getMessage());
        }
    }

    public void abrirGaveta() {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(0, 50, 50);

        if (retorno == 0) {
            System.out.println("Gaveta aberta!");
        } else {
            System.out.println("Erro ao abrir gaveta: " + retorno);
        }
    }

    public void sinalSonoro() {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(1, 0, 100);

        if (retorno == 0) {
            System.out.println("Sinal sonoro executado!");
        } else {
            System.out.println("Erro no sinal sonoro: " + retorno);
        }
    }

    public void avancarPapel(Scanner sc) {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        System.out.print("Quantidade de linhas: ");

        try {
            int linhas = Integer.parseInt(sc.nextLine());

            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);

            if (retorno == 0) {
                System.out.println("Papel avancado!");
            } else {
                System.out.println("Erro ao avancar papel: " + retorno);
            }

        } catch (NumberFormatException e) {
            System.out.println("Digite apenas numeros!");
        }
    }

    public void statusImpressora() {

        if (conexaoAberta != 1) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return;
        }

        int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(0);

        System.out.println("Status retornado: " + retorno);
    }
}
