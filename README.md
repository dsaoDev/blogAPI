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
- Spring Security -> Proteger os endpoints da API  baseando-se nas Roles dos Usuarios

## Versionamento
- JAVA 17
- Spring 3.1.4
- MySQL 8.0

## Como executar o Projeto
Pré-requisitos: Java 17+ sua IDE de preferência e um Banco de dados de preferência o MySQL

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
- Como o sistema está protegido com Spring security você tem duas maneiras de testar a aplicação
1. Mudar o Endpoint de Registro de usuarios para Salvar um Usuario com Role de ADMIN
  
2. Criar um usuario com Role de ADMIN direto no banco de dados

- Apos seguir um dos passos acima  o Projeto estará disponivel http://localhost:8080/swagger-ui.html ou se preferir Importe a collection do Postman na raiz do Projeto e o Consuma utilizando Postman ou Insomnia



## Swagger Preview

### Post
![POSTNEWSWAGGER](https://github.com/dsaoDev/blogAPI/assets/129787872/54287757-526c-433e-aa7d-d6bdb8226bd9)

### Autor
![AUTORNEWSWAGGER](https://github.com/dsaoDev/blogAPI/assets/129787872/c82ef895-51c0-4d27-acf6-dcadb72716ea)

### Comentario
![COMENTARIOSWAGGERNEW](https://github.com/dsaoDev/blogAPI/assets/129787872/b5d26404-8b28-49b7-8992-c707a9e8ba3e)

### Usuario
![USUARIOSWAGGER](https://github.com/dsaoDev/blogAPI/assets/129787872/a3af2105-5a83-4cda-ab5d-fe7511700195)


## Schema 
![schema new](https://github.com/dsaoDev/blogAPI/assets/129787872/2d77f6c3-02f8-4174-b0b0-dcd742ba72e1)


# Autor
Davi Silva Alves de Oliveira (dsao)

https://www.linkedin.com/in/davi-silva-b91211271/
