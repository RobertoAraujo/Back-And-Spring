# Política de Segurança

## Versões Suportadas

Esta seção descreve quais versões do projeto estão atualmente recebendo atualizações de segurança. As versões suportadas são aquelas nas quais garantimos a aplicação de patches de segurança.

| Versão  | Suporte           |
| ------- | ----------------- |
| 5.1.x   | :white_check_mark: |
| 5.0.x   | :x:                |
| 4.0.x   | :white_check_mark: |
| < 4.0   | :x:                |

## Relatando Vulnerabilidades

Para garantir a segurança contínua do projeto, incentivamos os usuários a reportarem quaisquer vulnerabilidades descobertas. Por favor, siga os passos abaixo para relatar uma vulnerabilidade:

1. **Onde Relatar:** Envie um e-mail detalhado para `endriosrobert@gmail.com`, incluindo todas as informações relevantes sobre a vulnerabilidade.
   
2. **Confirmação de Recebimento:** Você receberá uma confirmação de recebimento dentro de 48 horas.
   
3. **Atualizações:** Forneceremos atualizações sobre o status da vulnerabilidade relatada a cada 7 dias, até que uma solução seja implementada ou a questão seja fechada.

4. **Processo de Análise:** Após a análise inicial, se a vulnerabilidade for aceita, ela será categorizada e priorizada para correção. Se a vulnerabilidade for declinada, um relatório explicando os motivos será enviado ao repórter.

5. **Divulgação:** Após a correção, uma atualização será lançada, e a vulnerabilidade será divulgada publicamente, creditando o descobridor, a menos que seja solicitado anonimato.

## Práticas de Segurança na Aplicação

Nossa aplicação Spring Boot segue rigorosos padrões de segurança para garantir a proteção de dados e a integridade do sistema:

### 1. Autenticação e Autorização

- **Autenticação:** Utilizamos o Spring Security para autenticar usuários que acessam os endpoints protegidos.
- **Autorização:** O acesso é controlado com base em `roles`, como `ADMIN` e `USER`, garantindo que apenas usuários autorizados possam executar determinadas operações.

### 2. Validação de Dados

- **Validação de Entrada:** Todas as entradas de dados são validadas usando anotações de validação do Spring, reduzindo o risco de injeções e outros tipos de ataques.

### 3. Documentação e Transparência

- **Documentação da API:** A documentação da API está disponível via Swagger em `http://localhost:8080/api/swagger-ui.html`, permitindo que desenvolvedores e usuários entendam claramente as funcionalidades e as medidas de segurança implementadas.

## Contribuição Segura

Contribuições para o projeto são incentivadas, mas devem seguir as melhores práticas de segurança. Antes de submeter qualquer mudança, por favor:

1. **Fork o Repositório:** Faça um fork do repositório oficial.
2. **Criar uma Branch:** Crie uma nova branch para sua funcionalidade ou correção (`git checkout -b feature/nova-feature`).
3. **Comitar Mudanças:** Faça o commit das suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. **Push para a Branch:** Envie sua branch para o repositório (`git push origin feature/nova-feature`).
5. **Abrir um Pull Request:** Submeta um pull request para revisão e inclusão no projeto principal.
