# Projeto NeoStore com JAX-RS

## Visão Geral
Este projeto, intitulado NeoStore, utiliza Quarkus e JAX-RS para implementar uma API RESTful dedicada ao gerenciamento de fornecedores. As funcionalidades chave incluem listagem, busca, inserção, edição e exclusão de fornecedores.

## Java 17

## Configuração e Execução
1. Clone o repositório do projeto.
2. Configure o ambiente de desenvolvimento com Java e Quarkus.
3. Inicie o projeto com o comando:
   ```shell script
   ./mvnw compile quarkus:dev

## Funcionalidades e Validações
1. **Validação de CNPJ**: Garantindo que apenas CNPJs válidos sejam cadastrados.
2. **Validação de E-mail**: Certificando que os e-mails fornecidos sejam válidos.
3. **Listagem Paginada de Fornecedores**: Opção de visualizar fornecedores em lotes de 5 em 5.
4. **Serviço de Importação de Fornecedores via JSON**: Permitindo a inserção de fornecedores através de arquivos JSON. 
5. **Importante:** Não foi utilizado Spring Boot para esta aplicação.

## Tecnologias Utilizadas
- **Quarkus**: Escolhido para o servidor, visando otimização no tempo de execução.
- **JAX-RS**: Especificação Java para APIs RESTful.
- **JPA/Hibernate**: Implementação/ORM.
- **CDI**: Injeção de dependência.
- **JUnit**: Testes Unitários

## Estrutura do Projeto
O projeto está organizado nos seguintes pacotes:
- `org.neomind.Controller`: Contém `FornecedorController`.
- `org.neomind.Entity`: Define a entidade `Fornecedor`.
- `org.neomind.Service`: Contém `FornecedorService` com a lógica de negócios.
- `org.neomind.Repository`: Gerencia a persistência dos dados.

## Endpoints da API

### FornecedorController
#### Listar Fornecedores
- **GET** `/fornecedores`

#### Listar Fornecedores Paginados
- **GET** `/fornecedores/{startIndex}-{endIndex}`

#### Buscar Fornecedor por ID
- **GET** `/fornecedores/buscar/fornecedor/{id}`

#### Inserir Fornecedor
- **POST** `/fornecedores/inserir/fornecedor`
  - **Body** (Exemplo):
    ```json
    [
      {
        "nome": "Nome do Fornecedor",
        "description": "Descrição do Fornecedor",
        "cnpj": "CNPJ do Fornecedor",
        "email": "Email do Fornecedor"
      }
    ]
    ```
  - **Nota**: Sempre em lista para permitir a inserção de um ou mais fornecedores simultaneamente.

#### Editar Fornecedor
- **PUT** `/fornecedores/editar/fornecedor/{id}`
  - **Body** (Exemplo):
    ```json
    {
      "nome": "Nome do Fornecedor",
      "description": "Descrição do Fornecedor",
      "cnpj": "CNPJ do Fornecedor",
      "email": "Email do Fornecedor"
    }
    ```

#### Excluir Fornecedor
- **DELETE** `/fornecedores/delete/fornecedor/{id}`
  - **Body**: Não aplicável. A exclusão é realizada com base no ID fornecido na URL.


## Testes Unitários
Realizei testes unitários para validar as seguintes funcionalidades:
- **Validação de CNPJ**: Certificar de que apenas CNPJs válidos são aceitos. O método primeiro remove todos os caracteres não numéricos do CNPJ. Em seguida, verifica se o CNPJ possui exatamente 14 dígitos. Depois, calcula os dígitos verificadores de acordo com as regras da Receita Federal e verifica se eles correspondem aos últimos dois dígitos do CNPJ fornecido. Se eles correspondem, o CNPJ é considerado válido.


- **Validação de E-mail**: Verificar a validade dos e-mails fornecidos. Certifica de que apenas os e-mails que contenham '@' e '.' serão aceitos como e-mails válidos.

## Diferenciais
Incluir os seguintes aspectos pode diferenciar ainda mais seu projeto:
- **Uso de JPA/Hibernate**: Para uma abstração mais eficiente e gerenciamento de banco de dados.
- **CDI (Injeção de Dependências Contextuais)**: Melhorar a modularidade e a gestão de dependências do seu projeto.


---

Este README fornece uma visão geral do projeto NeoStore, incluindo detalhes sobre suas funcionalidades, endpoints da API, instruções para configuração
