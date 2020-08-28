
# API desenvolvida para resolver o desafio técnico abaixo:

Velasquez possui uma loja de vinhos e, ao longo dos anos, guardou dados de seus
clientes e um histórico de compras. Velasquez quer personalizar o atendimento e
contratou você para desenvolver um software que:

* Liste os clientes ordenados pelo maior valor total em compras.
* Mostre o cliente com maior compra única no último ano (2016).
* Liste os clientes mais fiéis.
* Recomende um vinho para um determinado cliente a partir do histórico de compras.

Para criar esse software o neto do Velasquez (o Velasquinho) disponibilizou uma
API com cadastro de clientes
(http://www.mocky.io/v2/598b16291100004705515ec5) e histórico de compras
(http://www.mocky.io/v2/598b16861100004905515ec7).


## Stack utilizada

- Java
- Spring
- Maven
- Swagger
- JUnit
- Mockito

## Rodar a aplicação

Clone o projeto e navegue até a pasta root do mesmo por um terminal.

Então, execute os comandos na sequencia abaixo para compilar e rodar os testes unitarios da aplicação:

- mvn clean
- mvn package

Caso deseje rodar apenas os testes:
- mvn test

Navegue até o diretório onde foi gerado o arquivo .jar (diretorio /target) e rode o comando abaixo para subir a aplicação:
- java -jar wine-sales-improver-0.0.1-SNAPSHOT.jar

## Acessar a API

Após a aplicação, a api ficará disponivel em:
- http://localhost:8080/api/v1/customer

Para visualizar a documentação da api:
- http://localhost:8080/swagger-ui.html