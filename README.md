# Blog API

Esta é uma API de blog que permite criar, ler, atualizar e excluir posts, autores e comentários entre outras funcionalidades.

## Tecnologias Utilizadas

- JAVA ✔️
- Maven ✔️
- MySQL ✔️
- Spring Framework ✔️

## Dependencias do Maven

Spring Data JPA - > Utilizando o JPA para fazer a ponte entre o banco de dados e a aplicação -> ORM

Spring Web -> É uma aplicação WEB, Utilizado para receber requisições, devolver uma resposta ...

LomBok -> Utilizado para evitar muitas linhas de código através de annotations.

Spring Validation -> Dependência utilizada para validação dos atributos 

MySQL Connector -> Utilizado para se connectar ao Banco de Dados MySQL

Devtools -> Utilizado para fazer o Live reload para melhor experiência de desenvolvimento

## Versionamento
- JAVA 17
- Spring 3.1.4
- MySQL 8.0

## Instalação
- Certifique-se de ter o Java 17 instalado em sua máquina.
- Sua IDE de preferência e de preferência o Banco de dados MySQL
- Postman ou Insomnia

1. Clonar repositorio:

```
git clone https://github.com/dsaoDev/blogAPI.git
```

2. Entrar na pasta do projeto:

```
cd BlogAPI
```

3. Inicializar o Projeto

```
./mvnw spring-boot:run
```
4. Após inicializar o Projeto estará disponivel  http://localhost:8080 por padrão, mas você pode alterar a porta no arquivo application.properties utilizando a propriedade server.port = Numero da porta
   
## Endpoints

### Posts
- POST /posts : Cria uma novo post
- POST /posts/idPost/comentarios : Adiciona um comentario a um Post existente
- GET /posts : Traz uma pagina com todos os posts
- GET /posts/idPost : Acha um post pelo id
- DELETE /posts/idPost : Deleta um Post pelo id
- PUT /posts/idPost : Atualiza um post
- GET/posts/idAutor/autores : Traz uma pagina com todos os posts pertencentes ao id do Autor
   
### Autores
- POST/autores : cria um novo Autor
- GET/autores/idAutor : Acha um autor pelo id
- GET/autores : Traz uma pagina de autores
- PUT/autores/idAutor : Atualiza um autor
- DELETE/autores/idAutor : Deleta um Autor pelo id

### Comentarios
- GET/comentarios/idPost : Traz uma pagina de comentarios referente ao id do Post
- GET/comentarios : Traz uma pagina com todos comentarios
- PUT/comentarios/idComentario : Atualiza um comentario
- DELETE/comentarios/idComentario : Deleta um comentario

### Schema 
![schema](https://github.com/dsaoDev/blogAPI/assets/129787872/0e328d72-cada-45e4-b78a-ab7559928e4d)