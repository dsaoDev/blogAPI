# Blog API [![license](https://img.shields.io/github/license/DAVFoundation/captain-n3m0.svg?style=flat-square)](https://github.com/dsaoDev/blogAPI/blob/main/LICENSE)

Esta é uma API de Blog que foi um Desafio tecnico passado pelo Treina Recife que permite criar, ler, atualizar e excluir posts, autores e comentários entre outras diversas funcionalidades.
## Tecnologias Utilizadas

- JAVA ✔️
- Maven ✔️
- MySQL ✔️
- Spring Framework ✔️

## Dependencias do Maven

- Spring Data JPA -> Utilizando o JPA para fazer a ponte entre o banco de dados e a aplicação
- Spring Web -> É uma aplicação WEB, Utilizado para receber requisições, devolver uma resposta
- Lombok -> Utilizado para evitar muitas linhas de código através de annotations
- Spring Validation -> Dependência utilizada para validação dos atributos 
- MySQL Connector -> Utilizado para se connectar ao Banco de Dados MySQL
- Devtools -> Utilizado para fazer o Live reload para melhor experiência de desenvolvimento
- Swagger -> Utilizado para documentar a API
- Spring Cloud OpenFeign -> Utilizado para fazer chamada a API Externa

## Versionamento
- JAVA 17
- Spring 3.1.4
- MySQL 8.0

## Como executar o Projeto

#### Pré-requisitos: Java 17, sua IDE de preferência e o Banco de dados MySQL

1. Clonar repositorio

```
git clone https://github.com/dsaoDev/blogAPI.git
```

2. Entrar na pasta do projeto

```
cd BlogAPI
```
3. Criar um schema no seu banco de dados chamado blogAPI
```
create schema blogAPI
```
4. Abrir o projeto com sua IDE de preferência ou Inicializar o Projeto via CMD/Terminal

```
./mvnw spring-boot:run
```
## Como consumir os ENDPOINTS
- Após rodar, o Projeto estará disponivel para testes em http://localhost:8080/swagger-ui.html ou se preferir importe a coleção .json que está na RAIZ do projeto e o consuma utilizando POSTMAN ou INSOMNIA



## Swagger Preview

### Post
![POST ENDPOINTS](https://github.com/dsaoDev/blogAPI/assets/129787872/3b2955c4-0888-4f04-b56d-91badca21785)

### Autor
![AUTOR ENDPOINTS V2](https://github.com/dsaoDev/blogAPI/assets/129787872/31640094-e257-455e-905a-69c485563eca)

### Comentario
![COMENTARIO ENDPOINTS](https://github.com/dsaoDev/blogAPI/assets/129787872/51861b33-facb-43a1-b279-cf55b039ede9)


## Schema 
![schema](https://github.com/dsaoDev/blogAPI/assets/129787872/ea7559f2-e3ad-40ae-84b5-68f6ef90c635)

# Autor
Davi Silva Alves de Oliveira (dsao)

https://www.linkedin.com/in/davi-silva-b91211271/
