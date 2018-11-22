[![Build Status](https://travis-ci.com/OpenEnade/API.svg?branch=master)](https://travis-ci.com/OpenEnade/API)
[![codecov](https://codecov.io/gh/OpenEnade/API/branch/master/graph/badge.svg)](https://codecov.io/gh/OpenEnade/API)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/OpenEnade/API.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/OpenEnade/API/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/OpenEnade/API.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/OpenEnade/API/context:java)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6354121789314e09b557cc42ffce6f3c)](https://app.codacy.com/app/paulofelipe.feitosa/API?utm_source=github.com&utm_medium=referral&utm_content=OpenEnade/API&utm_campaign=Badge_Grade_Dashboard)
[![codebeat badge](https://codebeat.co/badges/fbbfbbc6-9cde-4933-a5a6-de452663c60c)](https://codebeat.co/projects/github-com-openenade-api-master)
[![CodeFactor](https://www.codefactor.io/repository/github/openenade/api/badge)](https://www.codefactor.io/repository/github/openenade/api)
[![BCH compliance](https://bettercodehub.com/edge/badge/OpenEnade/API?branch=master)](https://bettercodehub.com/)
# OpenEnade API
Aplicação responsável por recuperar, inserir, atualizar e deletar dados do ENADE em uma instância de Banco de Dados.

Esta API foi desenvolvida para dar suporte ao [OpenEnade-ClientApp](https://github.com/OpenEnade/OpenEnade-ClientApp) para que seja possível ranquear e comparar cursos de diferentes IES.

## Rodando o servidor
### Sem stub data
1.  `mvn install`
2.  `mvn spring-boot:run`

## Com stub data
1.  `mvn install`
2.  `mvn spring-boot:run -Dspring-boot.run.arguments=--stub-data`

Pronto, agora basta acessar por exemplo: [http://localhost:8080/api/regioes](http://localhost:8080/api/regioes)

Para acessar a documentacão da API [http://localhost:8080/api/swagger-ui.html#/](http://localhost:8080/api/swagger-ui.html#/).
