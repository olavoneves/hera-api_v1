# 🧠 Hera API

**Hera Platform (Sistema de Automação e Engajamento para Teleconsultas)**  
Desenvolvido pela **Hera Corporation**, este projeto tem como objetivo automatizar e otimizar a jornada de teleconsulta dos pacientes do **IMREA**, reduzindo a carga operacional dos colaboradores e garantindo eficiência, segurança e engajamento em todo o processo.

---

## 🎯 Objetivo do Projeto

O **Hera API** é um backend em **Java + Quarkus** responsável por gerenciar os dados dos pacientes, médicos, consultas e usuários do sistema **Hera Platform**.  
Ele automatiza etapas da jornada de teleconsulta através de integração com o **n8n**, utilizando canais como **WhatsApp, E-mail e SMS** para comunicação inteligente com os pacientes.

O sistema tem como metas:
- Automatizar o envio de vídeos tutoriais e lembretes de teleconsultas.
- Atualizar automaticamente o status da jornada do paciente.
- Engajar os pacientes através de gamificação e notificações automáticas.
- Oferecer um portal administrativo para controle completo de usuários, médicos, pacientes e consultas.

---

## ⚙️ Funcionalidades Principais

### 🔐 Portal do Administrador
- **CRUD de Usuário:** criação, consulta, atualização e exclusão de contas.  
- **CRUD de Paciente:** gerenciamento completo de informações pessoais e clínicas.  
- **CRUD de Médico:** controle de dados de profissionais de saúde (especialidade, disponibilidade, etc).  
- **CRUD de Consulta:** criação e manutenção de agendamentos médicos.

### 🤖 Automação Inteligente
- Envio automático de vídeos tutoriais para novos pacientes.  
- Envio programado de lembretes via WhatsApp, e-mail ou SMS.  
- Atualização automática do status de teleconsulta.  
- Sistema de **gamificação** para reforçar o engajamento do paciente.  

---

## 🌐 URI Pública da API

A API está hospedada e acessível publicamente em:  
👉 **https://hera-api.onrender.com/hera-api/**

---

## 📚 Tabela de Endpoints

| Endpoint | Método | Descrição | Códigos de Resposta |
|-----------|---------|------------|----------------------|
| `/usuarios` | POST | Cria um novo usuário | 201, 400, 500 |
| `/usuarios/{id}` | PUT | Atualiza os dados de um usuário | 201, 400, 500 |
| `/usuarios/{id}` | DELETE | Exclui um usuário existente | 204, 404, 500 |
| `/usuarios/{id}` | GET | Retorna um usuário específico | 200, 404, 500 |
| `/usuarios` | GET | Lista todos os usuários cadastrados | 200, 404, 500 |
| `/login` | POST | Verifica se o usuário existe no banco | 200, 400, 500 |
| `/pacientes` | POST | Cria um novo paciente | 201, 400, 500 |
| `/pacientes/{id}` | PUT | Atualiza os dados de um paciente | 201, 400, 500 |
| `/pacientes/{id}` | DELETE | Exclui um paciente existente | 204, 404, 500 |
| `/pacientes/{id}` | GET | Retorna um paciente específico | 200, 404, 500 |
| `/pacientes` | GET | Lista todos os pacientes cadastrados | 200, 404, 500 |
| `/medicos` | POST | Cria um novo médico | 201, 400, 500 |
| `/medicos/{id}` | PUT | Atualiza os dados de um médico | 201, 400, 500 |
| `/medicos/{id}` | DELETE | Exclui um médico existente | 204, 404, 500 |
| `/medicos/{id}` | GET | Retorna um médico específico | 200, 404, 500 |
| `/medicos` | GET | Lista todos os médicos cadastrados | 200, 404, 500 |
| `/consultas` | POST | Cria uma nova consulta | 201, 400, 500 |
| `/consultas/{id}` | PUT | Atualiza uma consulta existente | 201, 400, 500 |
| `/consultas/{id}` | DELETE | Exclui uma consulta existente | 204, 404, 500 |
| `/consultas/{id}` | GET | Retorna uma consulta específica | 200, 404, 500 |
| `/consultas` | GET | Lista todas as consultas cadastradas | 200, 404, 500 |

---

## 🧩 Tecnologias Utilizadas

- **Java 17**
- **Quarkus**
- **Oracle SQL**
- **Render** (Deploy)
- **n8n** (Automação)
- **Maven** (Gerenciamento de dependências)

---

## 🗄️ Estrutura do Projeto

```
  src/
├── main/
│ ├── java/br/com/fiap/
│ │ ├── bo/
│ │ ├── dao/
│ │ ├── resource/
│ │ ├── to/
│ └── resources/
└── test/
```

---

## 🚀 Como Executar Localmente

```bash
# Clone o repositório
git clone https://github.com/olavoneves/hera-api_v1.git

# Acesse a pasta do projeto
cd hera-api

# Compile o projeto
./mvnw clean install

# Execute a aplicação
./mvnw quarkus:dev
```

A aplicação ficará disponível em:  ``` http://localhost:8080/hera-api ```

---

## 🧾 Licença

Este projeto foi desenvolvido para fins acadêmicos e está sob uso educacional.
© 2025 Hera Corporation. Todos os direitos reservados.

---

## 📞 Contato

Em caso de dúvidas ou contribuições, entre em contato com os desenvolvedores:
`📧 olavo9neves@gmail.com `
