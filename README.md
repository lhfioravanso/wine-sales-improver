
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
- Redis

## Requisitos

Possuir Docker instalado na máquina ou uma instância do redis. 

A instância do redis deve estar acessível em 127.0.0.1 na porta 6379, ou os dados de conexão devem ser alterados no arquivo 'application.properties'.

Para criar a instância do redis no docker, rode o seguinte comando: 
   * docker run -d -p 6379:6379 -i -t redis:3.2.5-alpine 
   
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

Após subir a aplicação, a documentação da api ficará disponivel em:
- http://localhost:8080/swagger-ui.html

E os endpoints para resolução dos problemas ficaram disponiveis da seguinte forma:

* 1: http://localhost:8080/api/v1/customer/highest-total-purchases
* 2: http://localhost:8080/api/v1/customer/highest/{year}
* 3: http://localhost:8080/api/v1/customer/fidelity
* 4: http://localhost:8080/api/v1/customer/recommendation/{customerId}

## Observação:

Para melhorar a performance foi implementado cache nas requests, sempre que uma mesma request for chamada pela primeira vez será adicionada no cache e a partir das proximas chamadas, buscará diretamente do cache.

O cache é limpo a cada 1 hora, configurável no arquivo 'application.properties'.
  
  

### Débitos técnicos:

Ficou pendente por questões de tempo, adicionar uma maior cobertura de testes unitarios e realizar tratamento de exceções.
