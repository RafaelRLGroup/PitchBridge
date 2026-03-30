# 🚀 PitchBridge API

> **Transformando sonhos em realidade, uma contribuição por vez.**
> O PitchBridge é uma plataforma de financiamento coletivo (crowdfunding) construída para conectar sonhadores e investidores de forma transparente e robusta.

---

## 🥤 O Primeiro Sonho: "A Coquinha Gelada do Adalbelto"
O projeto nasceu da necessidade real de organizar as vaquinhas do escritório, evoluindo para uma API profissional capaz de gerenciar metas, percentuais de arrecadação e doações anônimas.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21** & **Spring Boot 3**
* **Spring Data JPA**: Persistência de dados simplificada.
* **PostgreSQL**: Banco de dados relacional de alta performance.
* **Jakarta Validation**: Blindagem contra dados inválidos.
* **Lombok**: Código limpo e produtivo.
* **Jackson**: Conversão elegante de Java para JSON.

---

## 🏗️ Arquitetura e Diferenciais Técnicos

* **Padrão DTO (Data Transfer Object)**: Separação total entre as entidades do banco e a camada de apresentação, garantindo segurança e performance.
* **Global Exception Handler**: Tratamento de erros centralizado com respostas amigáveis e padronizadas.
* **JPA Auditing**: Registro automático de data e hora (`createdAt`) em todos os sonhos e doações.
* **Inteligência de Dados**: Uso de Projections e Queries JPQL customizadas para cálculos de rankings e estatísticas em tempo real.
* **Privacidade (LGPD)**: Suporte a doações anônimas, onde o nome do doador é mascarado na API.

---

## 📊 Endpoints Principais

### 🌟 Sonhos (Dreams)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/dreams` | Lista todos os sonhos com métricas (%, total doado). |
| `POST` | `/dreams` | Cria um novo sonho vinculado a um usuário. |
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
```json
{
  "name": "Adalbelto Carlos",
  "email": "adalbelto@email.com",
  "password": "senha123456"
}
### 2. Criar um Sonho (POST /dreams)
```json
{
  "title": "Chácara do seu Zé",
  "description": "Um refúgio para apreciar uma coquinha gelada.",
  "goalValue": 50000.00,
  "creator": { "id": 1 }
}
### 3. Realizar Doação (POST /contributions)
```json
{
  "amount": 150.00,
  "anonymous": false,
  "dream": { "id": 1 },
  "donor": { "id": 2 }
}

---

## 🚀 Como Executar

1.  Clone o repositório.
2.  Configure o banco PostgreSQL no `application.properties`.
3.  Execute o comando:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  Acesse a API em `http://localhost:8080`.

---

## 👤 Autor
**Rafael S / @RafaelRLGroup** *"Codando até o 'zoio' fechar e o sorvete de pistache acabar."* 🍦🚀
