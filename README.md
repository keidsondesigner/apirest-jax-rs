# Projeto API REST para Gestão de Veterinários

Este projeto é uma API REST desenvolvida com Java EE, utilizando Servlet, Jersey e JAX-RS. Ele permite gerenciar registros de veterinários através de operações CRUD (Create, Read, Update, Delete).

## Endpoints

### Listar todos os veterinários
**GET:** `http://localhost:8080/apirest/api/veterinarios`

### Obter veterinário por ID
**GET:** `http://localhost:8080/apirest/api/veterinarios/{id}`  
Exemplo: `http://localhost:8080/apirest/api/veterinarios/1`

### Deletar veterinário por ID
**DELETE:** `http://localhost:8080/apirest/api/veterinarios/{id}`  
Exemplo: `http://localhost:8080/apirest/api/veterinarios/7`

### Criar um novo veterinário
**POST:** `http://localhost:8080/apirest/api/veterinarios`

#### Corpo da requisição (JSON):
```json
{
    "nome": "teste 2",
    "crmv": "4536",
    "salario": "2500.0"
}
```

### Atualizar um veterinário existente
**PUT:** `http://localhost:8080/apirest/api/veterinarios/{id}`  
Exemplo: `http://localhost:8080/apirest/api/veterinarios/4`

#### Corpo da requisição (JSON):
```json
{
    "nome": "teste 4 editado",
    "crmv": "4536",
    "salario": "2500.0"
}
```

## Configuração do Projeto

Este projeto utiliza **Maven** para gerenciamento de dependências e build. Abaixo estão as principais dependências e plugins utilizados no projeto.

### Dependências

- **JUnit**: Para testes unitários.
  ```xml
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>test</scope>
  </dependency>
  ```

- **JAX-RS API**: Para suporte RESTful.
  ```xml
  <dependency>
    <groupId>javax.ws.rs</groupId>
    <artifactId>javax.ws.rs-api</artifactId>
    <version>2.1.1</version>
  </dependency>
  ```

- **Resteasy JAX-RS**: Implementação do JAX-RS.
  ```xml
  <dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-jaxrs</artifactId>
    <version>3.15.3.Final</version>
  </dependency>
  ```

- **Resteasy Jackson 2 Provider**: Para serialização e desserialização JSON.
  ```xml
  <dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-jackson2-provider</artifactId>
    <version>3.15.3.Final</version>
  </dependency>
  ```

- **MySQL Connector**: Para integração com banco de dados MySQL.
  ```xml
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.22</version>
  </dependency>
  ```

- **Servlet API**: Para suporte a Servlets.
  ```xml
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
  </dependency>
  ```

- **CDI API**: Para injeção de dependência.
  ```xml
  <dependency>
    <groupId>javax.enterprise</groupId>
    <artifactId>cdi-api</artifactId>
    <version>2.0</version>
    <scope>provided</scope>
  </dependency>
  ```

### Plugins

O projeto utiliza vários plugins Maven para realizar as operações de build, teste e empacotamento.

- **maven-compiler-plugin**: Compila o código Java.
  ```xml
  <plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.0</version>
  </plugin>
  ```

- **maven-war-plugin**: Empacota o projeto como um arquivo WAR.
  ```xml
  <plugin>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.2.2</version>
  </plugin>
  ```

- **maven-surefire-plugin**: Executa testes durante o build.
  ```xml
  <plugin>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.1</version>
  </plugin>
  ```

### Versões de Java

O projeto está configurado para utilizar o Java 1.7 como versão de compilação:
```xml
<properties>
  <maven.compiler.source>1.7</maven.compiler.source>
  <maven.compiler.target>1.7</maven.compiler.target>
</properties>
```

---

## Configuração do Servidor Web (`web.xml`)

Este projeto utiliza um arquivo de configuração `web.xml` para definir o comportamento da aplicação no servidor, configurar o mapeamento de URLs e registrar os recursos RESTful. A seguir estão os principais pontos dessa configuração:

### Mapeamento de URLs

A API REST está configurada para ser acessada sob o caminho `/api/*`. Todos os endpoints RESTful são prefixados com `/api`.

Exemplo:  
- Para listar todos os veterinários, use: `http://localhost:8080/apirest/api/veterinarios`

```xml
<servlet-mapping>
  <servlet-name>resteasy-servlet</servlet-name>
  <url-pattern>/api/*</url-pattern>
</servlet-mapping>
```

### Configuração do Resteasy

O projeto utiliza **Resteasy** como a implementação de JAX-RS para expor os recursos RESTful. Isso é configurado no `web.xml` através de parâmetros de contexto e um `servlet` específico.

- **Prefixo de mapeamento dos endpoints REST:** Definido pelo parâmetro `resteasy.servlet.mapping.prefix` como `/api`. Isso significa que todos os endpoints RESTful estarão sob esse prefixo.

  ```xml
  <context-param>
    <param-name>resteasy.servlet.mapping.prefix</param-name>
    <param-value>/api</param-value>
  </context-param>
  ```

- **Digitalização automática de recursos:** O parâmetro `resteasy.scan` está configurado como `true`, permitindo que o Resteasy escaneie e registre automaticamente as classes de recursos (endpoints).

  ```xml
  <context-param>
    <param-name>resteasy.scan</param-name>
    <param-value>true</param-value>
  </context-param>
  ```

- **Registro de recursos RESTful:** A classe `VeterinarioResource` é explicitamente registrada como um recurso REST através do parâmetro `resteasy.resources`.

  ```xml
  <context-param>
    <param-name>resteasy.resources</param-name>
    <param-value>resource.VeterinarioResource</param-value>
  </context-param>
  ```

### Bootstrap do Resteasy

O listener `ResteasyBootstrap` é utilizado para iniciar o framework Resteasy quando o servidor é carregado. Isso garante que a API REST esteja disponível assim que o servidor iniciar.

```xml
<listener>
  <listener-class>
    org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap
  </listener-class>
</listener>
```

### Servlet do Resteasy

O servlet `HttpServletDispatcher` do Resteasy é responsável por encaminhar todas as solicitações recebidas para os recursos RESTful registrados. Ele está mapeado para o caminho `/api/*`.

```xml
<servlet>
  <servlet-name>resteasy-servlet</servlet-name>
  <servlet-class>org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher</servlet-class>
</servlet>

<servlet-mapping>
  <servlet-name>resteasy-servlet</servlet-name>
  <url-pattern>/api/*</url-pattern>
</servlet-mapping>
```

## Executando o Projeto
Após a configuração no `web.xml`, o servidor de aplicação estará pronto para expor os endpoints REST da API sob o caminho `/api`. Para acessar os recursos, certifique-se de usar a URL correta, que incluirá o prefixo `/api`.

### Preparação do WAR

Após efetuar alterações no código, siga os passos abaixo para gerar o arquivo `.war`:

1. No Maven, vá até `Plugins > war` e execute a tarefa para empacotar o projeto.
2. O arquivo `apirest.war` será gerado na pasta `target`.

### Configuração do Tomcat

Antes de iniciar o servidor, siga estas etapas:

1. Aponte o arquivo `apirest.war` para o servidor Tomcat.
2. Se novas dependências forem adicionadas ao projeto, execute os seguintes comandos Maven:

   - Limpar o projeto:
     ```bash
     mvn clean
     ```

   - Instalar dependências e compilar o projeto:
     ```bash
     mvn install
     ```

### Build e Deploy do Projeto

Para compilar, empacotar e gerar o arquivo `.war`, execute os seguintes comandos no terminal:

1. **Compilar e instalar o projeto:**
   ```bash
   mvn clean install
   ```

2. **Empacotar o projeto:**
   ```bash
   mvn clean package
   ```
