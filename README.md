
# Desafio pitang
 Desafio Técnico Pitang

# Sobre

 Criar aplicação que exponha uma API RESTful de criação de usuários e carros com login.
 
# Tecnologias

- Framework Spring;
- Java 11;
- H2 banco de dados;
- JWT como token;
- Servidor Tomcat;
- MockMvc;
- Ec2 AWS;
- Swagger;

**Justificativas e Defesa Técnica da Solução**

Neste projeto, optamos por utilizar um conjunto de ferramentas e tecnologias que são reconhecidas por sua robustez, confiabilidade e capacidade de atender às necessidades de desenvolvimento de aplicações corporativas. Abaixo, apresentamos uma justificativa detalhada para cada uma das ferramentas escolhidas, destacando suas vantagens e os motivos pelos quais foram selecionadas.



 **Spring Framework**


**Justificativa:** O Spring Framework é amplamente utilizado no desenvolvimento de aplicações Java devido à sua modularidade, extensibilidade e suporte a padrões de design que promovem a criação de software escalável e fácil de manter.

**Defesa Técnica:**
- **Inversão de Controle (IoC):** Facilita o gerenciamento de dependências, resultando em um código mais limpo e modular.
- **Programação Orientada a Aspectos (AOP):** Permite a separação de preocupações transversais, como logging, segurança e gerenciamento de transações, melhorando a modularidade do código.
- **Suporte Abrangente:** Integra-se facilmente com diversos frameworks e bibliotecas, além de oferecer um ecossistema completo com projetos como Spring Boot, Spring Data e Spring Security.
- **Comunidade e Documentação:** Possui excelente documentação e uma comunidade ativa que facilita a resolução de problemas e a troca de conhecimento.




 **H2 Database**


**Justificativa:** O H2 Database é uma solução de banco de dados leve e embutida, ideal para desenvolvimento e testes rápidos devido à sua facilidade de uso e configuração.

**Defesa Técnica:**
- **Modo Em Memória:** Permite execução em memória, ideal para ambientes de desenvolvimento e testes rápidos.
- **Compatibilidade:** Suporta a maioria dos recursos SQL e é compatível com JDBC, facilitando a migração para bancos de dados de produção.
- **Performance:** Alta performance em operações básicas de banco de dados, sem necessidade de instalação ou configuração complexa.





**Amazon EC2**


**Justificativa:** Amazon EC2 oferece uma infraestrutura flexível e escalável, permitindo ajustar os recursos computacionais conforme a demanda do projeto.

**Defesa Técnica:**
- **Escalabilidade:** Permite ajustar rapidamente a capacidade computacional para atender a picos de demanda.
- **Confiabilidade:** Infraestrutura robusta com alta disponibilidade e recuperação rápida em caso de falhas.
- **Integração com AWS:** Integra-se facilmente com outros serviços AWS, proporcionando um ecossistema completo para aplicações na nuvem.
- **Segurança:** Recursos avançados de segurança, incluindo VPC, IAM e grupos de segurança, garantindo a proteção dos dados e da aplicação.

**Swagger**


**Justificativa:** Swagger facilita a documentação e o teste de APIs, oferecendo uma interface amigável e interativa para desenvolvedores e stakeholders.

**Defesa Técnica:**
- **Documentação Automática:** Gera documentação automaticamente a partir do código, garantindo que a documentação esteja sempre atualizada.
- **Interatividade:** Fornece uma interface web onde desenvolvedores podem testar as APIs diretamente, facilitando o desenvolvimento e a depuração.
- **Padrões Abertos:** Adere aos padrões OpenAPI, promovendo interoperabilidade e facilidade na integração com outras ferramentas e serviços.

**JSON Web Token (JWT)**



**Justificativa:** JWT é uma forma eficiente e segura de transmitir informações entre partes, sendo amplamente utilizado para autenticação e autorização em aplicações web.

**Defesa Técnica:**
- **Simplicidade:** Fácil de implementar e usar em arquiteturas RESTful.
- **Segurança:** Permite a assinatura e/ou criptografia dos tokens, garantindo a integridade e autenticidade das informações transmitidas.
- **Desempenho:** Não requer armazenamento de sessão no servidor, reduzindo a carga e melhorando a performance da aplicação.
- **Interoperabilidade:** Amplamente suportado em diversas plataformas e linguagens de programação.

**Testes Unitários**



**Justificativa:** Testes unitários são essenciais para garantir a qualidade e a confiabilidade do código, facilitando a detecção de erros e regressões.

**Defesa Técnica:**
- **Confiabilidade:** Aumentam a confiança no código ao verificar que cada unidade de software funciona conforme esperado.
- **Manutenção:** Facilitam a refatoração e manutenção do código, garantindo que mudanças não introduzam novos bugs.
- **Documentação:** Servem como documentação viva do comportamento esperado das funções e métodos.
- **Automatização:** Integram-se bem com pipelines de CI/CD, permitindo a execução automática e contínua dos testes, melhorando a eficiência do desenvolvimento.
 
# História de usuário
 
- Gerenciamento de Contas:

US.1: Como alguém que ainda não se autenticou, gostaria de registrar dados dos usuários para acessá-los mais tarde.

US.2: Enquanto usuário não autenticado, quero poder ver uma lista dos usuários cadastrados para ter controle sobre eles.

US.3: Como usuário não autenticado, desejo poder atualizar informações de usuários para corrigir erros ou atualizar detalhes.

US.4: Sendo um usuário não autenticado, quero poder eliminar informações de usuários que não são mais necessárias.

US.5: Como usuário registrado, mas não autenticado, quero acessar o sistema usando meu nome de usuário e senha.

- Gerenciamento de Veículos:

US.1: Como usuário não autenticado, desejo poder pesquisar informações sobre um veículo específico para melhor controle sobre o cadastro.

US.2: Sendo um usuário registrado e autenticado, quero catalogar informações sobre meus veículos para referência futura.

US.3: Como usuário registrado e autenticado, desejo ver uma lista detalhada dos meus veículos para melhor controle sobre eles.

US.4: Sendo um usuário registrado e autenticado, desejo poder atualizar informações dos meus veículos para corrigir erros ou atualizar detalhes.

US.5: Como usuário registrado e autenticado, desejo poder remover informações de veículos que não são mais relevantes.

US.6: Sendo um usuário registrado e autenticado, quero poder visualizar minhas informações de login no sistema.

US.7: Como usuário registrado e autenticado, desejo poder pesquisar informações sobre um veículo específico para melhor controle sobre ele.







# Passos para execução

- 1. Para executar o projeto localmente basta clonar o projeto e importar na sua IDE de preferencia, subir a aplicação e acessar localhost na porta 8081(http://localhost:8081) e o Swagger (http://localhost:8081/swagger-ui/index.html). 

 - 2. Para executar os testes unitários é preciso clicar com o botão direito no projeto e acessar o caminho: **src > main > test**.


# Conclusão

Cada uma dessas ferramentas foi escolhida com base em sua capacidade de atender às necessidades específicas do projeto, proporcionando uma solução eficiente, escalável e de fácil manutenção. Esta abordagem técnica visa entregar resultados sólidos e consistentes, alinhados com as melhores práticas de desenvolvimento de software.