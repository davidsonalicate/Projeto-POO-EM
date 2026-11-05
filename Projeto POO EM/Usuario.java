
public class Usuario {

    String nome;
    String senha;

    public void cadastrar(String novoNome, String novaSenha) {
        nome = novoNome;
        senha = novaSenha;
    }

    public int verificarLogin(String nomeDigitado, String senhaDigitada) {

        if (nome != null && senha != null &&
                nome.equals(nomeDigitado) && senha.equals(senhaDigitada)) {
            return 1;
        } else {
            return 0;
        }
    }
}
