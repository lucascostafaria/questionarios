# Sistema de questionários
Se refere a um sistema que possibilita ao usuário cadastrar e responder questionários. 

# Regras:
 - Esta aplicação deve ter autenticação;
 - Usuários autenticados podem cadastrar questionários;
 - Um questionário pode possuir N perguntas;
 - Uma pergunta pode ser de marcar (N opções) ou aberta;
 - Qualquer usuário pode responder a um questionário utilizando seu email;
 - Um questionário pode ser respondido uma única vez para cada email.

# Como executar a aplicação
mvn clean install
mvn jetty:run

# Como usar a aplicação
O sistema tem como pagina inicial uma tela que da boas vindas ao usuário e solicita que o mesmo digite seu email. Ao digitar e pressionar ok o sistema exibe os questionários já cadastrados. Para responder um questionário clique no link “Responder” na linha do questionário que deseja responder.
Será exibida uma tela com o questionário escolhido. Basta preencher as respostas e clicar em “Responder”
Cadastro de questionário – Para cadastrar um questionário é necessário estar cadastrado como usuário do sistema. Clique na aba “Área restrita”.
A aplicação possui um usuário Administrador com email: admin@admin.com e senha: 1234
Ao tentar cadastrar um questionário, será necessário fazer login no sistema. Após efetuar o login, basta clicar na aba Cadastro de questionário, digitar um nome para o questionário e clicar em “Cadastrar”. Em seguida, preencha as questões e alternativas (caso seja uma questão fechada) e clique em “Visualizar antes de salvar”. Confira o questionário e caso esteja tudo correto, clique em “Salvar”, caso contrário clique em “Voltar”.
Caso queira pesquisar um questionário, informe o nome e clique em “Pesquisar”. Após realizar a pesquisa também é oferecida as opçãos de “Excluir” e “Editar” o questionário. 
Cadastro de Usuário – Para cadastrar um usuário, clique na aba Cadastro de Usuário. Preencha os campos email e senha e clique em “Cadastrar”.
Caso queira pesquisar um usuário, utilize o mesmo campo email como filtro e clique em “Pesquisar”.
Relatório de respostas – Para visualizar as respostas de um questionário clique na aba “Respostas de questionário”, digite o email do usuário que respondeu esse questionário e clique em “Ok”.

# Versões
Maven Version – 3.0.5
Java Version – 1.7.x




