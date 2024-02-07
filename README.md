# Título del Proyecto

SUPERMAINTENANCE (Una prueba técnica con Spring Boot)

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

### Entorno de integración

Pasos para correr la aplicación en un entorno de integración.


## Checklist de Tareas

- [x] Proyecto creado en Start.Spring.io con las dependencias necesarias.
- [ ] Entorno/s de desarrollo configurado (Java, Maven).

