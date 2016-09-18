# Valeska
Projeto IP2
Gerenciamento Clínica Médica
- Quem criará novos médicos e novos recepcionistas será uma classe administradora
- Quem cria novas consultas e cadastra novos clientes é o recepcionista
- Recepcionista tem acesso a todos os clientes e consultas cadastradas no sistema
- Quem garante que os horários das consultas sejam compatíveis é o recepcionista
- Médicos só tem acesso as consultas que foram marcadas para ele
- Clientes não devem usar o sistema
- Teremos tres tipos de usuário do sistema, Médicos, Recepcionistas e Administrador
- Uma consulta tem o atributo "realizada" que começa como falso, após o atendimento, colocamos ela como true para que ela possa ser removida do sistema, ou pelo menos removida da lista de consultas do médico

O Cliente deve:
- informar suas informações para as classe que nessecitarem

Uma Consulta deve:
- informar horário, cliente, médico, se já foi realizada e as anotações do médico

Um Médico deve:
- Poder ver quais são as consultas que ele realizará no dia
- Ver todas as suas consultas
- Adicionar à sua lista de consultas as consultas marcadas pelo recepcionista
- Informar seus dados pessoais
- Realizar consultas

Um Recepconista pode:
- Criar novas Consultas
- Cadastrar novos clientes
- Desmarcar consultas
- Marcar consultas para o médico
- Informar seus dados pessoais

