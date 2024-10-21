GET:          http://localhost:8080/apirest/api/veterinarios
GET porID:    http://localhost:8080/apirest/api/veterinarios/1
DELETE:       http://localhost:8080/apirest/api/veterinarios/7

POST:         http://localhost:8080/apirest/api/veterinarios
{
    "nome": "teste 2",
    "crmv": "4536",
    "salario": "2500.0"
}

PUT:         http://localhost:8080/apirest/api/veterinarios/4
{
    "nome": "teste 4 editado",
    "crmv": "4536",
    "salario": "2500.0"
}


após efetuar alguma alteraçõa, executar o "war" em maven > plugins > war
apontar o arquivo "apirest.war" 
criado dentro da pastar "target" para o servidor Tomcat
antes de executar o servidor

Após adicionar uma dependica executar o "clean" e "install"

> build do projeto
mvn clean install

>empacotar o projeto e gerar o arquivo .war
mvn clean package
