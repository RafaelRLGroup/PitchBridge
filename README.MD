# 🚀 PitchBridge API

![Java Version](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-brightgreen?style=for-the-badge&logo=spring-boot)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)
![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg?style=for-the-badge)

> **Transformando sonhos em realidade, uma contribuição por vez.**
> O PitchBridge é uma plataforma de financiamento coletivo (crowdfunding) construída para conectar sonhadores e investidores de forma transparente e robusta.

---

## 🥤 O Primeiro Sonho: "A Coquinha Gelada do bigode"
O projeto nasceu da necessidade real de organizar as vaquinhas da manutenção, evoluindo para uma API profissional capaz de gerenciar metas, percentuais de arrecadação e doações anônimas.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21** & **Spring Boot 3**
* **Spring Security**: Criptografia de senhas com **BCrypt**.
* **Spring Data JPA**: Persistência de dados simplificada.
* **PostgreSQL**: Banco de dados relacional de alta performance.
* **Jakarta Validation**: Blindagem contra dados inválidos.
* **Lombok**: Código limpo e produtivo.
* **Jackson**: Conversão elegante de Java para JSON.

---

## 🏗️ Arquitetura e Diferenciais Técnicos

* **Segurança de Dados**: Implementação de Hashing com **BCrypt**, garantindo que as senhas nunca sejam armazenadas em texto puro.
* **Categorização por Enums**: Organização de sonhos por categorias (Saúde, Educação, Tecnologia, Lazer, etc).
* **Padrão DTO (Data Transfer Object)**: Separação total entre as entidades do banco e a camada de apresentação.
* **Global Exception Handler**: Tratamento de erros centralizado com respostas amigáveis.
* **JPA Auditing**: Registro automático de data e hora (`createdAt`) em todos os sonhos e doações com `@EnableJpaAuditing`.
---

## 📊 Endpoints Principais

### 🌟 Sonhos (Dreams)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/dreams` | Lista todos os sonhos com métricas (%, total doado). |
| `POST` | `/dreams` | Cria um novo sonho vinculado a um usuário e categoria. |
| `GET` | `/dreams/category/{CAT}` | Filtra sonhos por categoria (Ex: LAZER, SAUDE). |
| `GET` | `/dreams/search?title=coca` | Busca dinâmica por título (IgnoreCase). |
| `GET` | `/dreams/stats` | **Dashboard:** Total de sonhos, doações e montante global. |

### 💸 Contribuições (Contributions)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/contributions` | Realiza uma doação vinculada a um sonho. |
| `GET` | `/contributions/ranking` | **Ranking:** Maiores doadores da plataforma por valor total. |
| `GET` | `/contributions/dream/{id}` | Mural de doações de um sonho específico. |

---

## 🧪 Guia de Testes (Exemplos de Body)

### 1. Criar Usuário (POST `/users`)
> As senhas enviadas são automaticamente criptografadas antes de chegarem ao banco.
```json
{
  "name": "Rosquineovaldo",
  "email": "rosros@email.com",
  "password": "senhaSegura123"
}
```

### 2. Criar um Sonho (POST `/dreams`)

```json
{
  "title": "Banda de Rock do Adalbelto",
  "description": "Comprar instrumentos para animar o escritório.",
  "goalValue": 15000.00,
  "category": "LAZER",
  "creator": { "id": 5 }
}
```

### 3. Realizar Doação (POST `/contributions`)

```json
{
  "amount": 150.00,
  "anonymous": false,
  "dream": { "id": 1 },
  "donor": { "id": 2 }
}
```

---

## 🚀 Como Executar

1. Clone o repositório.
2. Configure o banco PostgreSQL no `application.properties`.
3. Certifique-se de que a coluna `created_at` está presente ou use `ddl-auto=update`.
4. Execute o comando:
   ```bash
   ./mvnw clean compile spring-boot:run
   ```
5. Acesse a API em `http://localhost:8080`.

---

---

## 🤝 Conecte-se Comigo

O **PitchBridge** é um projeto em evolução constante. Se você gostou da arquitetura ou da ideia:

* 🌟 **Dê uma estrela:** Isso ajuda muito na visibilidade do projeto!
* 💬 **Feedback:** Tem alguma sugestão técnica ou de negócio? Abra uma [Issue](link-para-issues).

**Estou em busca da minha próxima oportunidade no mundo Dev!** Se você é recrutador ou desenvolvedor e quer trocar uma ideia sobre Java, Spring ou o futuro do financiamento coletivo, me chame no LinkedIn:

[ ![LinkedIn Badge](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white) ](https://www.linkedin.com/in/rafael-simi%C3%A3o-pereira-b67108191/)

## 👤 Autor
**Rafael** / [@RafaelRLGroup](https://github.com/RafaelRLGroup)
*"Codando até o 'zoio' fechar e o sorvete de pistache acabar."* 🍦🚀
