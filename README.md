# Votação

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Gradle
- MapStruct
- JUnit 5
- H2 para Teste
- YugaByte para Dev

### Instalação

#### 1. Clonar o Repositorio

```bash
git clone https://github.com/yandeara/db-desafio.git
cd db-desafio
```

#### 2. Configurar o Banco de Dados

Caso queira usar Docker para levantar um container do YugaByte:

```
docker-compose up -d
```

Caso prefira utilizar um banco de dados que está ja instalado, so trocar as informações do Banco no application.properties

```
spring.datasource.url=[URL]
spring.datasource.username=[USERNAME]
spring.datasource.password=[PASSWORD]
spring.datasource.driver-class-name=[DRIVER]
```
#### 3. Buildar com o Gradle

Para buildar o projeto pode se usar um gradle local

```
gradle build
```

ou utilizar o Wrapper 

```
gradlew build 
```

#### 4. Rodar a aplicação
```
gradle bootRun
```

#### 5. Testar a aplicação

O projeto ja tem um Swagger configurado, que fica disponível por padrão no seguinte link:
```
http://[host]:[porta]/swagger-ui/index.html
```





