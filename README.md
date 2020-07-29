No projeto foi utilizado WatchService para monitorar a pasta %Homepath%/data/in para caso seja inserido algum arquivo, caso não haja essa pasta no computador, a Aplicação cria o caminho.

Para executar a aplicação basta executar a Classe main em uma ideia importando o projeto como projeto gradle. Logo após a execução as pastas in, out e logs serão criadas e enquanto a aplicação 
estiver rodando estará monitorando. Para gerar um relatório na pasta out, basta inserir um arquivo (ou mais)
na pasta in então após 5 segundos será gerado um relatório com a seguinte estrutra

Quantidade de clientesçQuantidade de vendedoresçId da maior vendaçPior vendedor 

E conforme forem adicionados arquivos será recalculado nesse mesmo relatório.

O desenvolvimento foi todo em Java utilizando gradle.

