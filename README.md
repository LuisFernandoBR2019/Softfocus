## Nome
ProAgro SoftFocus

## Descrição
É um software web desenvolvido na linguagem de programação Java utilizando as ferramentas Spring Boot, PostgreSQL e Bootstrap. 
É utilizado para manipular comunicações de perda causados por eventos climaticos.

## Instalação
Para a funcionalidade da aplicação, é necassário ter as seguintes ferramentas/frameworks instaladas:
-PostgreSQL versão 9.5 ou superior.
-Spring Tool Suite versão 4.0 ou superior (ou qualquer IDE alternativa de desenvolvimento com spring boot).
-Java Development Kit 11.

## Uso
Passo a passo para a utilização da aplicação:
1° passo : Instale as ferramentas mencionadas no tópico acima.
2° passo : Execute o script localizado na pasta documentos dentro do projeto (proagro-ddl.sql). Obs.: Utilizo via prompt de comando o seguinte comando (psql -U postgres -f (diretório do script))).
3° passo : Execute a aplicação e acesse a url : localhost:8082/
4° passo (opcional) : Caso queira executar o teste unitário do CRUD, basta executar a classe ComunicacaoPerdaTest.java com a aplicação em execução.
5° passo (opcional) : Caso queira realizar a consulta da documentação da API, basta acessar a url : http://localhost:8082/swagger-ui.html com a aplicação em execução.
6° passo (opcional) : Caso queira gerar o relatório, basta pressionar o botão Gerar Relatório, será feito uma cópia de segurança dentro da pasta Documentos/ProAgro/Relatórios e irá fazer o download de uma outra cópia via front-end.
 
## Validações
Validações encontradas no front-end.
1° validação CPF : Possui validação de CPF, onde só se pode fazer a alteração e criação da comunicação de perda com um valor de CPF válido.
2° validação e-mail : Possui validação no e-mail, só é possivel a criação ou alteraão da comunicação de perda com um valor de e-mail valido, ex: nando_srs@hotmail.com.
3° validação de latitude e longitude : Possui validação de latitude e longitude, só é realizada a requisição para a API informando um valor número float válido.
4° validação de campos em brancos : Possui validação de campo em branco em todos os inputs do formulário.

## Autores
Luis Fernando da Silva

## Licença
Todos as ferramentas são de códigos open-source.

## Status do Projeto
Projeto concluido.