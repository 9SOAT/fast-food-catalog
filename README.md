# Catalog Microservice

O **Fast Food Catalog Microservice** é responsável por gerenciar o catálogo de produtos do ecossistema Fast-Food. Ele fornece endpoints para consulta de cardápio, detalhes de produtos e busca personalizada, além de manter o registro de categorias e preços.

- **Listar todos os produtos**  
- **Buscar produto por ID**  
- **Filtrar produtos por categoria, nome e faixa de preço**  
- **Cadastrar novo produto**  
- **Atualizar dados de um produto**  
- **Excluir produto (soft delete)**  
- **Gerenciar categorias de produtos**
---

## Tecnologias  utilizadas

- **Java 21**  
- **Spring Boot 3.4**  
- **Maven 3.8+**  
- **Lombok**  
- **Docker & Docker Compose**  
- **GitHub Actions** (CI/CD)  
- **MapStruct** (para mapeamento DTO ↔ entidade)  
- **Swagger / OpenAPI** (documentação de API)  

---

## Como rodar localmente

### Pré-requisitos

- Java 21 instalado  
- Maven 3.8+ instalado  
- Docker & Docker Compose instalados  

### Docker Compose

- Ter o Docker e Docker Compose instalados

```shell
  docker compose -p fast-food-catalog up -d
```

### Rodar a aplicação

No terminal, execute:

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação ficará disponível em:  
```
http://localhost:8080
```


## 📖 API Endpoints

| Método | Rota                                    | Descrição                          |
| ------ | --------------------------------------- | ---------------------------------- |
| GET    | `/catalog/products`                     | Listar produtos (com paginação)    |
| GET    | `/catalog/products/{id}`                | Obter detalhes de um produto       |
| GET    | `/catalog/products/search`              | Buscar produtos (query params)     |
| POST   | `/catalog/products`                     | Criar novo produto                 |
| PUT    | `/catalog/products/{id}`                | Atualizar produto                  |
| DELETE | `/catalog/products/{id}`                | Deletar produto (soft delete)      |
| GET    | `/catalog/categories`                   | Listar categorias                  |
| POST   | `/catalog/categories`                   | Criar nova categoria               |

### Query params em `/catalog/products/search`

- `name` (string) — busca por parte do nome  
- `category` (string) — filtro por categoria  
- `minPrice` (decimal) — preço mínimo  
- `maxPrice` (decimal) — preço máximo  
- `page` (int) — número da página (default `0`)  
- `size` (int) — itens por página (default `10`)  

---

## Testes

- **Unitários**: JUnit 5 + Mockito  
- **Integração**: Spring Boot Test (WebEnvironment.RANDOM_PORT)  
- Para rodar todos:
  ```bash
  mvn test
  ```

---

O **GitHub Actions** está configurado para executar, a cada push:

1. Análise estática de código  
2. Testes unitários (JUnit + Mockito)  
3. Verificação de cobertura e qualidade via SonarQube  
