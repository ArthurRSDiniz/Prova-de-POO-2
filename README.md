# 📚 Sistema de Matrícula e Cálculo de IMC

Projeto desenvolvido para a **Avaliação 01** da disciplina de Programação Orientada a Objetos. O sistema permite o cadastro completo de alunos, vinculando dados pessoais, acadêmicos e de saúde.

## 🚀 Funcionalidades
- **CRUD Completo:** Cadastrar, Listar, Alterar e Remover alunos do banco de dados.
- **Cálculo de IMC:** Processamento automático do Índice de Massa Corporal com classificação.
- **Relatório Estatístico:** Geração de dados dinâmicos como média de IMC, maior peso e menor altura.
- **Interface Gráfica:** Desenvolvida em Java Swing com máscaras de campo (CPF e Telefone).

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java 17+
- **Interface:** Java Swing (JFrame, JTable, JDialog)
- **Banco de Dados:** MySQL 8.0
- **Padrão de Projeto:** DAO (Data Access Object) e MVC (Model-View-Controller)

## 📁 Estrutura do Projeto
- `AlunoIMC`: Classes de modelo, cálculos e acesso a dados (DAO/READ).
- `SQL`: Classe de conexão com o banco de dados.
- `Principal`: Ponto de entrada (Main) e Interface Gráfica (Formulário).

## 📝 Como rodar o projeto
1. Clone o repositório.
2. Certifique-se de ter o driver JDBC do MySQL (`mysql-connector-j`) instalado no seu projeto.
3. Configure a classe `Conexao.java` com seu usuário e senha do MySQL.
4. Execute o script SQL para criar a tabela `alunos`.
