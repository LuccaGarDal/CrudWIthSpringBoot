# CRUD com Spring Boot

API REST desenvolvida com Java e Spring Boot para gerenciamento de produtos, com integração a banco de dados usando Spring Data JPA.

## Tecnologias
- Java
- Spring Boot
- Spring Data JPA
- Banco de dados relacional
- Postman

## Funcionalidades
- Criar produto
- Listar todos os produtos
- Buscar produto por ID
- Atualizar produto
- Deletar produto

## Endpoints

| Método | Endpoint          | Descrição                  |
|------|-------------------|----------------------------|
| POST | /products         | Criar um produto           |
| GET  | /products         | Listar todos os produtos   |
| GET  | /products/{id}    | Buscar produto por ID      |
| PUT  | /products/{id}    | Atualizar um produto       |
| DELETE | /products/{id}  | Deletar um produto         |

## Como executar o projeto

### Pré-requisitos
- Java 17+
- Maven
- Banco de dados configurado

### Passos
```bash
git clone https://github.com/LuccaGarDal/CrudWIthSpringBoot.git
cd CrudWIthSpringBoot
mvn spring-boot:run

---

## Testes

Os endpoints foram testados utilizando o Postman, simulando requisições HTTP para validação das funcionalidades da API.
