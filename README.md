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
 

## Autores
Luis Fernando da Silva

## Licença
Todos as ferramentas são de códigos open-source.

## Status do Projeto
Projeto concluido.