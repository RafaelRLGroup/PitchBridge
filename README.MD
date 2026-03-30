# 🚀 PitchBridge API

> **Transformando sonhos em realidade, uma contribuição por vez.** > O PitchBridge é uma plataforma de financiamento coletivo (crowdfunding) construída para conectar sonhadores e investidores de forma transparente e robusta.

---

## 🥤 O Primeiro Sonho: "A Coquinha Gelada do Adalbelto"
O projeto nasceu da necessidade real de organizar as vaquinhas do escritório, evoluindo para uma API profissional capaz de gerenciar metas, percentuais de arrecadação e doações anônimas.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17** & **Spring Boot 3**
* **Spring Data JPA**: Persistência de dados simplificada.
* **PostgreSQL**: Banco de dados relacional de alta performance.
* **Jakarta Validation**: Blindagem contra dados inválidos.
* **Lombok**: Código limpo e produtivo.
* **Jackson**: Conversão elegante de Java para JSON.

---

## 🏗️ Arquitetura e Diferenciais Técnicos

O projeto segue os padrões de mercado mais rigorosos:

* **Padrão DTO (Data Transfer Object)**: Separação total entre as entidades do banco e o que é exibido no Front-end, garantindo segurança e evitando loops infinitos.
* **Global Exception Handler**: Tratamento de erros centralizado que devolve respostas amigáveis e padronizadas.
* **JPA Auditing**: Registro automático de data e hora (`createdAt`) em todos os sonhos e doações.
* **Lógica de Negócio Transacional**: Uso de `@Transactional` para garantir que o saldo do sonho só seja atualizado se a doação for salva com sucesso.
* **Privacidade (LGPD)**: Suporte a doações anônimas, onde o nome do doador é mascarado na camada de apresentação.

---

## 📊 Endpoints Principais

### 🌟 Sonhos (Dreams)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/dreams` | Lista todos os sonhos com métricas (%, total doado). |
| `POST` | `/dreams` | Cria um novo sonho vinculado a um usuário. |
| `GET` | `/dreams/search?title=coca` | Busca dinâmica por título (IgnoreCase). |
| `GET` | `/dreams/trending` | Top 5 sonhos mais próximos da meta. |

### 💸 Contribuições (Contributions)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/contributions` | Realiza uma doação (Mínimo R$ 1,00). |
| `GET` | `/contributions/dream/{id}` | Mural de doações de um sonho específico. |

---

## 🧪 Guia de Testes (Exemplos de Body)

Copie e cole os JSONs abaixo no seu cliente HTTP (Postman/Insomnia) para testar as funcionalidades:

### 1. Criar Usuário (POST `/users`)
> **Nota:** A senha deve ter no mínimo 6 caracteres.
```json
{
  "name": "Adalbelto Carlos",
  "email": "adalbelto@email.com",
  "password": "senha123456"
}

{
  "title": "Chácara do seu Zé",
  "description": "Um refúgio para os fins de semana com muita coquinha gelada.",
  "goalValue": 50000.00,
  "creator": {
    "id": 1
  }
}

{
  "amount": 150.00,
  "anonymous": false,
  "dream": {
    "id": 1
  },
  "donor": {
    "id": 2
  }
}

---

---

### 🧐 Por que isso é a "Tábua de Salvação"?

1.  **Estrutura de Objeto:** Repare no `creator: { id: 1 }`. Muita gente esquece que, no Spring, quando a gente recebe um relacionamento, o JSON precisa dessa "chavinha" extra. No README, isso fica claro como o dia!
2.  **Agilidade:** Agora você só dá um `Ctrl+C` no README e um `Ctrl+V` no Postman.
3.  **Documentação Viva:** Se a gente mudar alguma regra (tipo o valor mínimo de doação), a gente atualiza aí e pronto.

---

### ⚠️ Sobre o erro do compilador (Print anterior)
O seu print mostrou que o Spring não achou o `UserRankingDTO`. Antes de testar esses bodies novos, certifique-se de que você rodou o comando:

```bash
./mvnw clean compile

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
**Rafael / @RafaelRLGroup** *"Codando até o 'zoio' fechar e o sorvete de pistache acabar."* 🍦🚀
