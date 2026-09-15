# 📌 Sistema de Cadastro de Clientes - Caso de Uso APSOO

Projeto desenvolvido para a disciplina de **Análise e Projeto de Sistemas Orientados a Objetos (APSOO)**, cobrindo a especificação e implementação do caso de uso **Cadastrar Cliente**.

---

## 📋 Especificação do Caso de Uso

* **Nome do Caso de Uso:** Cadastrar Cliente
* **Ator:** Funcionário
* **Pré-Condição:** Funcionário autenticado no sistema
* **Pós-Condição:** Cliente cadastrado no banco de dados

### ⚙️ Regras de Negócio & Validações
1. **Maioridade:** O cliente deve ter 18 anos ou mais no momento do cadastro.
2. **Campos Obrigatórios:** CPF, Nome, Telefone, Email e Data de Nascimento.
3. **Unicidade:** Não é permitido o cadastro de dois clientes com o mesmo CPF.

### 🔄 Fluxos
* **Fluxo Normal:**
  1. O sistema exibe as unidades federativas.
  2. O funcionário informa o CPF.
  3. O funcionário informa o Nome.
  4. O funcionário informa Rua, Número, Bairro, Cidade e UF.
  5. O funcionário informa Telefone, E-mail e Data de Nascimento.
  6. O funcionário clica em **Confirmar**.
  7. O sistema valida os dados e exibe a mensagem: *"Cliente cadastrado com sucesso"*.
* **Fluxos Alternativos:**
  * `2a. Cliente já cadastrado`: Exibe *"Cliente já cadastrado"* e encerra o fluxo.
  * `2b. CPF inválido`: Exibe *"CPF inválido"* e solicita nova digitação.
  * `2c. CPF não preenchido`: Exibe *"CPF não preenchido"* e solicita preenchimento.

---

## 🏗️ Arquitetura (3 Camadas)

```text
sql/
└── bd.sql
src/
└── br/
    └── com/
        └── apsoo/
            ├── model/
            │   └── Cliente.java
            ├── dao/
            │   ├── Conexao.java
            │   └── ClienteDAO.java
            └── view/
                └── ClienteView.java
```

---

## 🚀 Como Executar

1. Importe o script `sql/bd.sql` no seu SGBD MySQL.
2. Configure usuário/senha no arquivo `br.com.apsoo.dao.Conexao`.
3. Certifique-se de adicionar o driver JDBC (`mysql-connector-j.jar`) no Classpath.
4. Execute a classe principal `br.com.apsoo.view.ClienteView`.
