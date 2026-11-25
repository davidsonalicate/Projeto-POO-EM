import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Impressora {

    String caminhoXmlSat = "XMLSAT.xml";
    String caminhoXmlCancelamento = "CANC_SAT.xml";

    String assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

    int tipo;
    String modelo;
    String porta;
    int parametro;

    boolean conexaoAberta = false;

    private boolean verificarConexao() {
        if (!conexaoAberta) {
            System.out.println("Conexao fechada. Abra a conexao antes.");
            return false;
        }
        return true;
    }

    private int lerInteiro(Scanner sc, String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Digite um numero entre " + min + " e " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas numeros!");
            }
        }
    }

    public void configurar(String novoTipo, String novoModelo, String novaPorta, String novoParametro) {

        try {
            tipo = Integer.parseInt(novoTipo.trim());
            parametro = Integer.parseInt(novoParametro.trim());

            modelo = novoModelo.trim();
            porta = novaPorta.trim();

            conexaoAberta = false;

            System.out.println("Configuracao realizada!");

        } catch (NumberFormatException e) {
            System.out.println("Tipo e parametro devem ser numeros!");
        }
    }

    public void abrirConexao() {

        if (modelo == null && porta == null) {
            System.out.println("Configure a impressora primeiro!");
            return;
        }

        if (conexaoAberta) {
            System.out.println("A conexao ja esta aberta.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(
                    tipo, modelo, porta, parametro
            );

            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Impressora conectada com sucesso!");
            } else {
                conexaoAberta = false;
                System.out.println("Erro ao conectar. Codigo: " + retorno);
            }

        } catch (UnsatisfiedLinkError | ExceptionInInitializerError e) {
            conexaoAberta = false;
            System.out.println("Nao foi possivel carregar a DLL Elgin.");
            System.out.println("Verifique a pasta da DLL e a arquitetura do Java.");

        } catch (Exception e) {
            conexaoAberta = false;
            System.out.println("Erro na conexao: " + e.getMessage());
        }
    }

    public void fecharConexao() {

        if (!conexaoAberta) {
            System.out.println("A conexao ja esta fechada.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Conexao fechada!");
            } else {
                System.out.println("Erro ao fechar conexao. Codigo: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void imprimirTexto(Scanner sc) {

        if (!verificarConexao()) {
            return;
        }

        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(texto, 0, 0, 0);

            if (retorno >= 0) {
                ImpressoraDLL.INSTANCE.Corte(5);
                System.out.println("Texto enviado para impressao!");
            } else {
                System.out.println("Erro ao imprimir texto: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao imprimir texto: " + e.getMessage());
        }
    }

    public void imprimirQRCode(Scanner sc) {

        if (!verificarConexao()) {
            return;
        }

        System.out.print("Digite o conteudo do QR Code: ");
        String codigo = sc.nextLine();

        if (codigo.isEmpty()) {
            System.out.println("O conteudo nao pode ficar vazio.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(codigo, 5, 4);

            if (retorno == 0) {
                ImpressoraDLL.INSTANCE.Corte(5);
                System.out.println("QR Code enviado com sucesso!");
            } else {
                System.out.println("Erro ao imprimir QR Code: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao imprimir QR Code: " + e.getMessage());
        }
    }

    public void imprimirCodigoBarras() {

        if (!verificarConexao()) {
            return;
        }

        try {

            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(
                    2, "123456789012", 100, 2, 2
            );

            if (retorno == 0) {
                ImpressoraDLL.INSTANCE.Corte(5);
                System.out.println("Codigo de barras enviado!");
            } else {
                System.out.println("Erro ao imprimir codigo de barras: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao imprimir codigo de barras: " + e.getMessage());
        }
    }

    public void imprimirXMLSAT() {

        if (!verificarConexao()) {
            return;
        }

        try {
            String xml = Files.readString(Paths.get(caminhoXmlSat));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, 0);

            if (retorno == 0) {
                ImpressoraDLL.INSTANCE.Corte(5);
                System.out.println("XML SAT enviado com sucesso!");
            } else {
                System.out.println("Erro ao imprimir XML: " + retorno);
            }

        } catch (java.io.IOException e) {
            System.out.println("Arquivo " + caminhoXmlSat + " nao encontrado ou ilegivel: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao imprimir XML SAT: " + e.getMessage());
        }
    }

    public void imprimirXMLCancelamentoSAT() {

        if (!verificarConexao()) {
            return;
        }

        try {
            String xml = Files.readString(Paths.get(caminhoXmlCancelamento));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(xml, assQRCode, 0);

            if (retorno == 0) {
                ImpressoraDLL.INSTANCE.Corte(5);
                System.out.println("XML de cancelamento enviado!");
            } else {
                System.out.println("Erro ao imprimir XML de cancelamento: " + retorno);
            }

        } catch (java.io.IOException e) {
            System.out.println("Arquivo " + caminhoXmlCancelamento + " nao encontrado ou ilegivel: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao imprimir XML de cancelamento: " + e.getMessage());
        }
    }

    public void abrirGaveta() {

        if (!verificarConexao()) {
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1, 5, 10);

            if (retorno == 0) {
                System.out.println("Gaveta aberta!");
            } else {
                System.out.println("Erro ao abrir gaveta: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao abrir gaveta: " + e.getMessage());
        }
    }

    public void sinalSonoro(Scanner sc) {

        if (!verificarConexao()) {
            return;
        }

        int quantidade = lerInteiro(sc, "Quantidade de bips (1 a 63): ", 1, 63);

        try {
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(quantidade, 5, 5);

            if (retorno == 0) {
                System.out.println("Bip!");
            } else {
                System.out.println("Erro no sinal sonoro. Codigo: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro no sinal sonoro: " + e.getMessage());
        }
    }

    public void avancarPapel(Scanner sc) {

        if (!verificarConexao()) {
            return;
        }

        int linhas = lerInteiro(sc, "Quantidade de linhas: ", 1, 255);

        try {
            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);

            if (retorno == 0) {
                System.out.println("Papel avancado!");
            } else {
                System.out.println("Erro ao avancar papel: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Nao foi possivel carregar a DLL Elgin: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Erro ao avancar papel: " + e.getMessage());
        }
    }

    public void statusImpressora(Scanner sc) {

        if (!verificarConexao()) {
            return;
        }

        System.out.println("O que consultar?");
        System.out.println("1 - Gaveta");
        System.out.println("2 - Tampa");
        System.out.println("3 - Papel");
        System.out.println("5 - Geral");

        int param = lerInteiro(sc, "Escolha: ", 1, 5);

        if (param == 4) {
            System.out.println("Opcao invalida!");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(param);

            if (param == 3) {
                if (retorno == 5) {
                    System.out.println("Status do papel: tem papel.");
                } else if (retorno == 6) {
                    System.out.println("Status do papel: papel acabando.");
                } else if (retorno == 7) {
                    System.out.println("Status do papel: sem papel.");
                } else {
                    System.out.println("Status retornado: " + retorno);
                }
            } else {
                System.out.println("Status retornado: " + retorno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar status: " + e.getMessage());
        }
    }
}