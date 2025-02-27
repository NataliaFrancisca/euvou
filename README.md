# 🎟️🚶🏽‍♀️‍➡️ EUVOU - api de gerenciamento de eventos e ingressos.

> **euvou** é um projeto pessoal para estudo, onde com ele consegui aprender sobre Spring Boot, MySQL, tratamento de exceções e etc. Sei que é um projeto que pode ter melhorias e pretendo evoluir ele conforme vou aprendendo novas coisas.

## O que essa API permite:

### Cliente (`/client`):
- [X] Criar Cliente
- [X] Listar Clientes
- [X] Busca por Cliente (`cpf`) 
- [X] Editar Cliente
- [X] Deletar Cliente

### Organizador (`/organizer`)
- [X] Criar Organizador
- [X] Listar Organizadores
- [X] Buscar por Organizador (`id`)
- [X] Editar Organizador
- [X] Deletar Organizador   

### Evento (`/event`)
- [X] Criar Evento
- [X] Listar Eventos
- [X] Buscar por Eventos (search)
- [X] Buscar por Evento (`id`)
- [X] Editar Eventos
- [X] Deletar Evento

### Ingressos (`/tickets`)
- [X] Criar Ingressos para Evento
- [X] Listar Ingressos de todos os Eventos
- [X] Buscar por Ingressos (`eventId`)
- [X] Editar disponibilidade dos Ingressos (`eventId` & `manage-access?access=true`)
- [X] Editar Ingressos (`eventId` & `body`)
- [X] Deletar Ingressos do Evento

### Ingressos (`/ticket`)
- [X] Criar Ingresso
- [X] Acessar Ingresso (`cpf`)

