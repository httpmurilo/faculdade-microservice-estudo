### Projeto de estudo com Angular 8 e Microservices

## Simula um projeto de um site de uma faculdade com os seguintes módulos:

- Portfolio de cursos.
- Portfolio de pós graduação.
- Vendas
- Seletivo
- Portal EAD. API SEPARADA.
- Pagamento - API SEPARADA.
- Portal administrativo.
- API Service Discovery (Eureka).

O usuário sem autenticação pode acessar o site, escolher o curso, nesse momento é criado um novo usuário no sistema, feito isso ele pode se matricular para o seletivo, a partir desse momento é criado um objeto da entidade Aluno.

O seletivo consiste tem 5 perguntas e depois retorna o status da aprovação do aluno.

O usuário que nesse momento, passa a ser aluno, acessará o portal administrativo e pagará a mensalidade.

Ao pagar a mensalidade, será disponibilizado o acesso ao sistema de Portal EAD.

Onde terá o painel de materias do curso, e outras informações.

### Arquitetura :

- API CORE: Spring boot
- API Pagamentos: Spring boot
- API Portal EAD: Spring boot
- Web: Front end em Angular 8
- API Eureka: Descoberta de clients.
