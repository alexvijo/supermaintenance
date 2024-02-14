# SUPERMAINTENANCE (Una prueba técnica con Spring Boot)

## Prerrequisitos
Las versiones de las herramientas que necesitas para instalar el software y cómo instalarlas:

- Java: 11 (java-11-openjdk-amd64) 
- Maven: Apache Maven 3.6.3


## Instrucciones para correr la aplicación
### Entorno local
Para correr la aplicación en un entorno local, sigue estos pasos:

1. Compila el proyecto y descarga las dependencias necesarias con 
```bash
mvn clean install
```
2. Corre la aplicación con
```bash
mvn spring-boot:run
```
La aplicación ahora debería estar corriendo en `localhost:8080`, a menos que hayas configurado un puerto diferente en tu archivo `application.properties`.

Alternativamente, si estás usando Visual Studio Code, puedes correr la aplicación usando el plugin Spring Boot Dashboard.

### Correr las pruebas
Para correr las pruebas de la aplicación, abre una terminal en el directorio raíz del proyecto y ejecuta el siguiente comando:
```bash
mvn test
```

### Correr Docker
Para correr la aplicación sobre docker, sigue estos pasos:
1. Compila el proyecto .jar
```bash
mvn clean package
```
2. Ejecuta docker-compose
```bash
docker-compose build
docker-compose up supermaintenance
```

### Uso de la API
Para consumir los diferentes endpoints se debe: 
1. Crear un usuario a través de 'api/signup' con 'username' y 'password'
2. Autenticarte en 'api/signin' con las mismas credenciales para obtener Jwt   
(Opcionalmente se puede hacer uso de la colección y entorno para Postman
ubicados en el directorio '/postman/')

## Documentación de API con swagger
[Abrir swagger en navegador](http://localhost:8080/swagger-ui.html)


