# 🖨️ Sistema de Controle de Impressora Elgin

---

## 📘 Sobre o Projeto

Este projeto foi desenvolvido pelos alunos Beatriz, Davidson e Gustavo, com o objetivo de estabelecer uma comunicação entre o computador e uma impressora térmica **Elgin i8/i9**, utilizando a biblioteca **E1_Impressora01.dll**, acessada em Java por meio do JNA.

O sistema inicia com o cadastro de um novo usuário e, em seguida, solicita o login utilizando os dados cadastrados. Caso o usuário erre a senha, são permitidas até **três tentativas** de acesso.

Após a liberação do acesso, é apresentado um menu no console com opções para configurar e controlar a impressora, realizar impressões de textos, QR Codes, códigos de barras e XMLs, além de executar funções como abrir a gaveta, emitir bipes, avançar o papel e consultar o status da impressora.

O projeto faz parte da disciplina de **Programação Orientada a Objetos**, ministrada pelo professor Richard.

---

## 🧰 Tecnologias Utilizadas

- **Java 11 ou superior** (o código usa `Files.readString`, disponível a partir do Java 11)
- **IntelliJ IDEA**
- **JNA 5.15.0** (comunicação com a DLL)
- **DLL E1_Impressora01** (SDK da Elgin)
- **Impressora Elgin i8 ou i9**

---

## 📁 Estrutura do Projeto

```
/src                          → Código-fonte
   Main.java                  → Inicia o programa: cadastro, login e menu principal
   Usuario.java               → Guarda nome e senha e verifica o login
   Impressora.java            → Configura a conexão e executa cada operação da impressora
   ImpressoraDLL.java         → Interface JNA que mapeia as funções da DLL Elgin
   XMLSAT.xml                 → XML de venda SAT usado na opção 7
   CANC_SAT.xml               → XML de cancelamento SAT usado na opção 8
/Bibliotecas E1 Impressora    → DLLs da Elgin (x64 e x86)
/libs Java                    → jna-5.15.0.jar
```

---

## ⚙️ Instalação e Execução

### 1. Baixar o projeto
1. Acesse o repositório no GitHub.
2. Clique em **Code → Download ZIP**.
3. Extraia o arquivo.

### 2. Abrir no IntelliJ IDEA
1. **File → Open** e escolha a pasta extraída.
2. Em **File → Project Structure → Libraries**, confirme que o `jna-5.15.0.jar` está adicionado.
3. Confirme que a DLL da Elgin está acessível e que a arquitetura do Java (x64 ou x86) é a mesma da DLL.
4. Deixe os arquivos `XMLSAT.xml` e `CANC_SAT.xml` na pasta de execução do projeto.
5. Conecte e ligue a impressora.
6. Abra `Main.java` e clique em ▶️ **Run**.

---

## 🔐 Login

O sistema não possui um usuário ou senha predefinidos. Por isso, sempre que o programa é iniciado, é solicitado que o usuário realize um cadastro, **criando seu próprio nome de usuário e senha.**

Após o cadastro, o sistema solicita novamente essas informações para realizar o login. Os dados digitados devem ser iguais aos cadastrados anteriormente.

São permitidas até **três tentativas** de acesso. Caso o usuário erre as informações nas três tentativas, o sistema apresenta uma mensagem informando que o limite foi atingido e encerra a execução do programa.

**Observação: Os dados do cadastro ficam armazenados apenas na memória durante a execução do programa. Portanto, quando o sistema é encerrado, essas informações são perdidas.**
---

## 📋 Funcionalidades do Menu

| Opção | O que faz |
|---|---|
| 1 | Configura a conexão: tipo, modelo, porta e parâmetro (tipo e parâmetro devem ser numéricos) |
| 2 | Abre a conexão com a impressora (exige configuração prévia) |
| 3 | Fecha a conexão com a impressora |
| 4 | Imprime um texto digitado pelo usuário e corta o papel |
| 5 | Imprime um QR Code com o conteúdo digitado (não aceita vazio) e corta o papel |
| 6 | Imprime um código de barras fixo (`123456789012`) e corta o papel |
| 7 | Lê o arquivo `XMLSAT.xml` e imprime o cupom SAT |
| 8 | Lê o arquivo `CANC_SAT.xml` e imprime o cupom de cancelamento SAT, usando a assinatura do QR Code definida no código |
| 9 | Abre a gaveta de dinheiro |
| 10 | Emite sinal sonoro (bip); o usuário informa de 1 a 63 bips |
| 11 | Avança o papel; o usuário informa de 1 a 255 linhas |
| 12 | Consulta o status: 1 = gaveta, 2 = tampa, 3 = papel (tem papel, acabando ou sem papel), 5 = geral |
| 13 | Sai do sistema (fecha a conexão antes, se estiver aberta) |

As opções de impressão, gaveta, bip, avanço de papel e status só funcionam com a conexão aberta. Caso contrário, o sistema avisa "Conexao fechada. Abra a conexao antes.".

---

## 🧪 Testes Realizados

*(Ajuste esta lista com o que vocês realmente testaram na impressora.)*

| Teste | Resultado esperado |
|---|---|
| Login com senha ou usuário errado 3 vezes | Sistema encerra |
| Login correto | Acesso permitido e menu exibido |
| Letra no lugar de número no menu | Mensagem "Digite apenas numeros!" |
| Opção fora do menu | Mensagem "Opcao invalida!" |
| Usar uma impressão sem abrir a conexão | Aviso para abrir a conexão antes |
| Abrir a conexão sem configurar | Aviso para configurar a impressora primeiro |
| Impressão de texto, QR Code e código de barras | Impressão seguida de corte do papel |
| Impressão dos XMLs SAT e de cancelamento | Cupom impresso; se o arquivo não existir, mensagem de erro |
| Bip e avanço de papel | Valores fora do limite são recusados e pedidos de novo |
| Gaveta | Gaveta abre |
| Status do papel | Informa se tem papel, está acabando ou acabou |
| DLL não encontrada | Mensagem pedindo para verificar a pasta da DLL e a arquitetura do Java |

---

## 📚 Documentação Adicional

- Manual da impressora Elgin
- Documentação do SDK E1_Impressora

---

## 👥 Autores

- Beatriz Rodrigues
- Davidson Alex Camargo
- Gustavo Constantino
