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
