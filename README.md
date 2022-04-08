<p align="center"> <img src="./assets/imagens-readme/logo.jpeg" alt="Sas" class="center" width=300/> </p>

O projeto a ser desenvolvido vai ser um sistema para buscar novas receitas através da criação de novas
segmentações de clientes para a empresa parceira [SPC](https://www.spcbrasil.org.br/).

<h2 align="center">🚧  Sistema 🚀 Em construção...  🚧</h2>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/API-5-SEMESTRE/back-end?color=%2304D361">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/API-5-SEMESTRE/back-end">

  <a href="https://github.com/tgmarinho/README-ecoleta/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/API-5-SEMESTRE/back-end">
  </a>
    
   <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">
 
   <a href="https://github.com/API-5-SEMESTRE/back-end/stargazers">
    <img alt="Stargazers" src="https://img.shields.io/github/stars/API-5-SEMESTRE/back-end?style=social">
  </a>
</p>

<h2>Tópicos 🏁</h2>

<p>
 <a href="#projeto-">Projeto</a> •
 <a href="#desafio-">Desafio</a> • 
 <a href="#entregas-">Entregas</a> •
 <a href="#Rodando-o-Sistema-">Rodar o sistema</a> •
 <a href="#tecnologias-">Tecnologias</a> •
 <a href="#licença-">Licença</a> • 
</p>

<h2>Projeto 🖥</h2>

Projeto realizado em parceria com a SPC que é organização de gestão e inteligência de dados que entrega soluções de crédito, cobrança, antifraude, marketing e certificação digital juntamente com a Faculdade de Tecnologia de São José dos Campos Professor Jessen Vidal.

<h2>Desafio 📈</h2>

Desafio proposto pela SPC - "Temos um novo desafio na área de vendas que é buscar novas receitas através da criação de novas segmentações de clientes. Para isso precisaremos da criação de novos modelos de dados e indicadores que suportarão as estratégias de vendas (contato com clientes e prospects observando as suas particularidades relacionadas a segmentos, verticais de atuação, consumo e potencial de consumo)."

<h2>Entregas 💎</h2>

O planejamento e o progresso durante as Sprints do projeto poderá ser visto em [Entregas](https://github.com/API-5-SEMESTRE/back-end/wiki/Entregas).

<h2>Rodando o Sistema 🚀</h2>

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java 11](https://www.java.com/pt-BR/) e [Maven](https://maven.apache.org/). 
Além disto é bom ter um editor para trabalhar com o código como [VSCode](https://code.visualstudio.com/) ou [IntelliJ](https://www.jetbrains.com/pt-br/idea/).

### 🎲 Rodando o Back-End

```bash
# Clone este repositório
$ git clone https://github.com/API-5-SEMESTRE/back-end

# Descompactar o arquivo chamado "wallet_DB202203301935.zip" em alguma pasta da sua preferência. O caminho da pasta aonde foi descompactado o Wallet vai ser usado mais a frente.

# Abrir o arquivo "application.properties" no caminho "src/main/resources/"

# Dentro do arquivo "application.properties", aonde estiver escrito "[URL]" apagar e colocar "jdbc:oracle:thin:@DB202203301935_medium?TNS_ADMIN=" + caminho da pasta aonde o Wallet foi descompactado

# Exemplo de como deve ficar: jdbc:oracle:thin:@DB202203301935_medium?TNS_ADMIN=/Users/nome/Downloads/wallet

# Onde estiver "[USER]" e "[PASSORD]" deve colocar o usuário e a senha

# Salvar o arquivo "application.properties"

# Vá na pasta descompactada da Wallet e abra o arquivo "ojdbc.properties"

# Comente a segunda linha (adicione "#" no começo dela)

# Vai ficar assim: # oracle.net.wallet_location=(SOURCE=(METHOD=FILE)(METHOD_DATA=(DIRECTORY=${TNS_ADMIN})))

# Descomente as 4 últimas linhas (retire o "#" delas)

# Vai ficar assim:
javax.net.ssl.trustStore=${TNS_ADMIN}/truststore.jks
javax.net.ssl.trustStorePassword=<password_from_console>
javax.net.ssl.keyStore=${TNS_ADMIN}/keystore.jks
javax.net.ssl.keyStorePassword=<password_from_console>

# Nessas 4 linhas, no final delas, aonde estiver escrito "<password_from_console>", apague e coloque a mesma senha do Banco de Dados usada no application.properties

# Vai ficar assim:
javax.net.ssl.trustStore=${TNS_ADMIN}/truststore.jks
javax.net.ssl.trustStorePassword=senha_teste
javax.net.ssl.keyStore=${TNS_ADMIN}/keystore.jks
javax.net.ssl.keyStorePassword=senha_teste

# Salvar o arquivo "ojdbc.properties"

# Rodar o arquivo "ApiBackApplicartion.java" no seu editor de código ou IDE

# O Back-end está rodando e o Banco de Dados na Oracle Cloud está conectado
```

<h2>Tecnologias 🛠</h2>

As seguintes ferramentas foram usadas na construção do projeto:

<a href="https://www.java.com/pt-BR/">
  <img alt="image" src="https://img.shields.io/badge/Java_11-%23696969?style=for-the-badge&logo=Java">
</a>
<a href="https://www.oracle.com/br/cloud/">
  <img alt="image" src="https://img.shields.io/badge/Oracle_Cloud-%23696969?style=for-the-badge&logo=Oracle">
</a>
<a href="https://git-scm.com/">
  <img alt="image" src="https://img.shields.io/badge/Git-%23696969?style=for-the-badge&logo=Git">
</a>
<a href="https://maven.apache.org/">
  <img alt="image" src="https://img.shields.io/badge/Maven-%23696969?style=for-the-badge&logo=Apache Maven">
</a>
<a href="https://spring.io/projects/spring-boot">
  <img alt="image" src="https://img.shields.io/badge/Spring_Boot-%23696969?style=for-the-badge&logo=Spring">
</a>

<h2>Licença 📝</h2>

Este projeto esta sobe a licença [MIT](./LICENSE).
