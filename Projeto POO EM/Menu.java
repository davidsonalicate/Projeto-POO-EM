public class Menu {
    switch (opcao) {
        case 1:

            printf("\n== Cadastro de Aluno ==\n");

            float soma;
            float media;
            int i;


            if (alunos < 50) {

                printf("Digite o nome do aluno: ");
                scanf("%s", nomes[alunos]);

                printf("Digite a matricula: ");
                scanf("%i", &matriculas[alunos]);

                for (i = 0; i < 3; i++) {
                    printf("Digite a nota %i: ", i + 1);
                    scanf("%i", &notas[alunos][i]);

                    soma = notas[alunos][i];
                }



                if(soma >= 6){
                    printf("\n**Aprovado**\n");
                }

                if(soma <= 5.9){
                    printf("\n**Reprovado**\n");
                }



                printf("\n A soma total das notas e: %i\n", soma);

                alunos++;
                printf("\nAluno cadastrado com sucesso! Total de alunos: %d\n", alunos);

            } else {
                printf("\nLimite maximo de 50 alunos atingido.\n");
            }
            break;

        case 2:
            printf("\nMostrando alunos cadastrados:\n");
            break;
        case 3:
            printf("Voce escolheu Sair.\n");
            break;

        default:
            printf("Opcao invalida.\n");
            break;
    }

	return 0;
}

}

