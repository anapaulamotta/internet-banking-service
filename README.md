  # Internet Banking Service

  Projeto de um serviço de Internet Banking que permite a realização de transações bancárias e cadastro de clientes.

  ## Tecnologias utilizadas

  - Java 11
  - Spring Boot 2.6.7
  - H2 Database
  - Hibernate
  - Lombok

  ## Configuração 
  ### execução
  Para executar o projeto, é necessário ter o Java 11 e o Maven instalados.
  
  ### banco de dados
  O projeto utiliza o banco de dados H2 para armazenar os dados. O H2 é um banco de dados em memória, o que significa que os dados serão perdidos ao reiniciar o servidor.

Para acessar o console do H2 e visualizar os dados:

- Inicie o aplicativo Spring Boot.
- Abra um navegador da web e acesse http://localhost:8080/h2-console.
- No campo "JDBC URL", insira jdbc:h2:mem:testdb.
- No campo "User Name", insira sa.
- No campo "Password", insira password.
Clique em "Connect".

## Endpoints disponíveis
 ##### pode ser acessado via swagger em: http://localhost:8080/swagger-ui.html 

 #### Cliente
   Insere um novo cliente. Recebe um objeto JSON no corpo da requisição contendo os dados do cliente a ser inserido.
  ```
  POST /client
  {
  "name": "João Silva",
  "isExclusive": true,
  "accountNumber": "123456",
  "birthDate": "1990-01-01",
  "balance": 1000.0
  }
  ```
   Retorna uma lista de todos os clientes cadastrados.
  ```
  Get /client
  ```
 #### Transaction
   Realiza uma transação de depósito ou saque. O parâmetro {transactionType} deve ser substituído por "withdraw" para saque ou "deposit" para depósito. Recebe um objeto JSON no corpo da requisição contendo os dados da transação.
  ```
  PUT /transaction/{transactionType}
  {
  "accountNumber": "123456",
  "amount": 500.0
  }
  ```
   Retorna uma lista de todas as transações realizadas em uma determinada data. O parâmetro {date} deve ser substituído por uma data no formato "yyyy-MM-dd".
  ```
  GET /transaction?date=2023-06-09
  ```
  


