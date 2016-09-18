# Valeska
Projeto IP2
Gerenciamento Financeiro 
- Quem criará novos médicos e novos recepcionistas será uma classe administradora
- Quem cria novas consultas e cadastra novos clientes é o recepcionista
- Recepcionista tem acesso a todos os clientes e consultas cadastradas no sistema
- Quem garante que os horários das consultas sejam compatíveis é o recepcionista
- Médicos só tem acesso as consultas que foram marcadas para ele
- Clientes não devem usar o sistema
- Teremos tres tipos de usuário do sistema, Médicos, Recepcionistas e Administrador
- Uma consulta tem o atributo "realizada" que começa como falso, após o atendimento, colocamos ela como true para que ela possa ser removida do sistema, ou pelo menos removida da lista de consultas do médico
