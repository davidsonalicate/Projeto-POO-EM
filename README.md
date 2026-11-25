# 🖨️ Sistema de Controle de Impressora Elgin

## 📘 Sobre o Projeto

Este projeto foi desenvolvido pelos alunos **Beatriz, Davidson e Gustavo**, com o objetivo de estabelecer a comunicação entre o computador e uma impressora térmica **Elgin i8/i9**, utilizando a biblioteca `E1_Impressora01.dll`, acessada por meio da linguagem Java com o auxílio da biblioteca JNA.

O sistema começa com o cadastro de um novo usuário e, em seguida, solicita o login utilizando os dados cadastrados. Caso o usuário erre a senha, serão permitidas até **3 tentativas de acesso**.

Após a autenticação, é apresentado um menu no console com opções para configurar e controlar a impressora, realizar impressões de textos, QR Codes, códigos de barras e arquivos XML, além de executar funções como abrir a gaveta, emitir bipes, avançar o papel e consultar o status da impressora.

O projeto foi desenvolvido para a disciplina de **Programação Orientada a Objetos**, ministrada pelo professor Richard.

---

## 🧰 Tecnologias Utilizadas

- **Java 27 ou superior**
- **IntelliJ IDEA**
- **JNA 5.15.0** – Comunicação entre Java e DLL.
- **E1_Impressora01.dll** – SDK da Elgin.
- **Impressora térmica Elgin i8/i9.**

---

## 📁 Estrutura do Projeto

```text
/
├── src/
│   ├── Main.java                  # Menu principal do sistema
│   ├── Menu.java                  # Cadastro, login e inicialização
│   ├── Usuario.java               # Cadastro e validação do usuário
│   ├── Impressora.java            # Operações e controle da impressora
│   ├── ImpressoraDLL.java         # Interface JNA para comunicação com a DLL
│   ├── XMLSAT.xml                 # XML de venda SAT
│   └── CANC_SAT.xml               # XML de cancelamento SAT
│
├── Bibliotecas E1 Impressora/
│   ├── x64/                       # Bibliotecas para 64 bits
│   └── x86/                       # Bibliotecas para 32 bits
│
└── libs Java/
    └── jna-5.15.0.jar             # Biblioteca JNA
```

---

## ⚙️ Instalação e Execução

### 1. Baixar o projeto

1. Acesse o repositório no GitHub.
2. Clique em **Code → Download ZIP**.
3. Extraia os arquivos em uma pasta.

### 2. Abrir no IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Acesse **File → Open** e selecione a pasta do projeto.
3. Entre em **File → Project Structure → Libraries** e adicione o arquivo `jna-5.15.0.jar`.
4. Verifique se a DLL da Elgin está acessível.
5. Confira se a arquitetura do Java (x64 ou x86) corresponde à arquitetura da DLL.
6. Certifique-se de que os arquivos `XMLSAT.xml` e `CANC_SAT.xml` estejam na pasta de execução do projeto.
7. Conecte e ligue a impressora.
8. Abra o arquivo `Main.java` e clique em **Run (▶)**.

---

## 🔐 Sistema de Cadastro e Login

O sistema não possui usuário ou senha predefinidos. Ao iniciar o programa, o usuário deverá realizar um cadastro, criando seu próprio nome de usuário e senha.

Após o cadastro, será solicitado que informe novamente os dados para realizar o login.

**Regras de autenticação:**

- O usuário deve informar os mesmos dados utilizados no cadastro.
- São permitidas até **3 tentativas de login**.
- Caso erre as informações nas três tentativas, o sistema será encerrado.
- Se os dados estiverem corretos, o acesso ao menu principal será liberado.

> **Observação:** Os dados do cadastro ficam armazenados apenas na memória durante a execução do programa. Portanto, ao encerrar o sistema, as informações são perdidas.

---

## 📋 Funcionalidades do Menu

| Opção | Funcionalidade | Descrição |
|:---:|---|---|
| 1 | Configurar impressora | Configura o tipo de conexão, modelo, porta e parâmetro. O tipo e o parâmetro devem ser numéricos. |
| 2 | Abrir conexão | Estabelece a conexão com a impressora, após a configuração. |
| 3 | Fechar conexão | Encerra a conexão com a impressora. |
| 4 | Imprimir texto | Imprime um texto digitado pelo usuário e realiza o corte do papel. |
| 5 | Imprimir QR Code | Gera e imprime um QR Code com o conteúdo digitado. Não aceita conteúdo vazio. |
| 6 | Código de barras | Imprime o código de barras fixo `123456789012` e realiza o corte do papel. |
| 7 | Imprimir XML SAT | Lê o arquivo `XMLSAT.xml` e imprime o cupom SAT. |
| 8 | Cancelamento SAT | Lê o arquivo `CANC_SAT.xml` e imprime o cupom de cancelamento SAT, utilizando a assinatura do QR Code definida no código. |
| 9 | Abrir gaveta | Envia o comando para abrir a gaveta de dinheiro. |
| 10 | Emitir bip | Emite um sinal sonoro. O usuário pode informar de 1 a 63 bipes. |
| 11 | Avançar papel | Avança o papel de 1 a 255 linhas, conforme informado pelo usuário. |
| 12 | Consultar status | Consulta o status da gaveta, tampa, papel ou estado geral da impressora. |
| 13 | Sair | Encerra o sistema e fecha a conexão, caso esteja aberta. |

### 📊 Consulta de Status

A opção 12 permite consultar diferentes informações da impressora:

| Código | Status consultado |
|:---:|---|
| 1 | Gaveta |
| 2 | Tampa |
| 3 | Papel (com papel, papel acabando ou sem papel) |
| 5 | Status geral |

### ⚠️ Requisitos para utilização

As operações de impressão, abertura da gaveta, emissão de bipes, avanço de papel e consulta de status exigem que a conexão com a impressora esteja aberta.

Caso contrário, o sistema apresenta a mensagem:

```text
Conexao fechada. Abra a conexao antes.
```

Também é necessário configurar a impressora antes de tentar abrir a conexão.

---

## 🧪 Testes Realizados

| Teste | Resultado esperado |
|---|---|
| Informar senha ou usuário incorreto 3 vezes | Sistema encerra a execução. |
| Realizar login corretamente | Acesso permitido e menu exibido. |
| Digitar letras no lugar de números no menu | Mensagem: `Digite apenas numeros!` |
| Selecionar uma opção inexistente | Mensagem: `Opcao invalida!` |
| Tentar imprimir sem abrir a conexão | Sistema solicita a abertura da conexão. |
| Abrir conexão sem configurar a impressora | Sistema solicita a configuração prévia. |
| Imprimir texto | Texto impresso e papel cortado. |
| Imprimir QR Code | QR Code impresso e papel cortado. |
| Imprimir código de barras | Código impresso e papel cortado. |
| Imprimir XML SAT | Cupom SAT impresso. |
| Imprimir XML de cancelamento | Cupom de cancelamento impresso. |
| Arquivo XML inexistente | Sistema apresenta mensagem de erro. |
| Informar quantidade inválida de bipes | Valor recusado e solicitado novamente. |
| Informar quantidade inválida de linhas | Valor recusado e solicitado novamente. |
| Abrir gaveta | Gaveta acionada. |
| Consultar status do papel | Informa se há papel, se está acabando ou se acabou. |
| DLL não encontrada | Sistema solicita a verificação da DLL e da arquitetura do Java. |

---

## 📚 Documentação Adicional

- Manual da impressora térmica Elgin.
- Documentação do SDK E1_Impressora.

---

## 👥 Autores

- **Beatriz Souza**
- **Davidson Alex Camargo dos Santos Alicate**
- **Gustavo Moreira**

---

## 🎓 Informações Acadêmicas

**Disciplina:** Programação Orientada a Objetos  
**Professor:** Richard  
**Projeto:** Sistema de Controle de Impressora Elgin

---
