# Servicio Creación y consulta de usuarios

servicio que cuenta con 2 endpoints (/sign-up, /sign-up) para creación y consulta de usuarios usando Jwt par crear tokens y H2 para persistencia.






## Requisitos previos

Antes de iniciar, asegúrate de tener los siguientes elementos instalados:

- **JDK 11** o compatible  
  Puedes descargarlo desde [(Oracle JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) o [(OpenJDK)](https://openjdk.java.net/).
- **Gradle 7.4**  
  Descárgalo en: https://gradle.org/releases/. Alternativamente, el wrapper de Gradle (`gradlew`) incluido en el proyecto manejará esto automáticamente.
- **H2** Como base de datos, esto se configura en las dependencias y las properties del proyecto

---

## Configuración inicial

Sigue los siguientes pasos para importar y ejecutar el proyecto en tu entorno local:

### 1. Clonar el proyecto
Clona este repositorio en tu máquina local con el siguiente comando:

```bash
git clone https://github.com/diegosegura448/GlobalLogic.git
```

### 2. Importar el proyecto en tu editor

#### **Con IntelliJ IDEA (recomendado):**
1. Abre IntelliJ IDEA.
2. Ve a `File -> Open` y selecciona la carpeta del proyecto clonado.
3. IntelliJ detectará automáticamente el archivo `build.gradle` e importará la configuración del proyecto.

#### **Con Eclipse:**
1. Asegúrate de tener instalado el plugin **Buildship** para Gradle.
2. Ve a `File -> Import -> Gradle -> Existing Gradle Project`.
3. Selecciona la carpeta del proyecto y haz clic en `Finish`.

---

### 4. Construir y ejecutar el proyecto

#### **Con Gradle Wrapper (recomendado):**
Utiliza los scripts `gradlew` (Linux/macOS) o `gradlew.bat` (Windows) incluidos en el proyecto para compilar y ejecutar el proyecto, incluso sin instalar Gradle de forma global.

1. **Compilar el proyecto:**

   En Linux/macOS:

   ```bash
   ./gradlew build
   ```

   En Windows:

   ```bash
   gradlew.bat build
   ```

2. **Ejecutar la aplicación:**

   En Linux/macOS:

   ```bash
   ./gradlew bootRun
   ```

   En Windows:

   ```bash
   gradlew.bat bootRun
   ```

#### **Con un IDE (IntelliJ/Eclipse):**
1. Compila el proyecto desde tu IDE (Gradle debería configurarse automáticamente).
2. Ejecuta la clase principal `UserServicesApplication`.

   - En IntelliJ: Haz clic derecho en `UserServicesApplication` y selecciona `Run`.
   - En Eclipse: Haz clic derecho en la misma clase, selecciona `Run As -> Java Application`.

---

### 5. Probar el servicio

Cuando la aplicación esté corriendo, puedes probar los endpoints REST utilizando Postman, Swagger u otros clientes HTTP.

#### **1. URL base:**

   ```
   http://localhost:8085/
   ```

#### **2. Endpoints principales:**

- **Crear usuario**:  
  `POST /auth/sign-up`: el apyload esta mas abajo en payloads de ejemplo.

- **Crear usuario**:  
  `POST /auth/login`: para logear al usuario creado, el apyload esta mas abajo en payloads de ejemplo.
  

#### **3. Opciones de pruebas con Postman u OpenAPI (Swagger):**
   Puedes consumir los servicios con herramientas como [Postman](https://www.postman.com/) 

---
### Tecnologías utilizadas

- **Spring Boot**: Framework base para la creación de la aplicación.
- **Spring Data JPA**: Acceso a la base de datos.
- **Gradle**: Sistema de construcción.
- **JWT**: Para autenticación basada en tokens.
- **H2**: Como base de datos para persistir las entidades

---


## payloads de ejemplo

```javascript
//creación
{
    "name":"jaime cosio",
    "email":"jaimec@dominio.com",
    "password":"2adffSiu8",
    "phones":[
        {
            "number": 123456,
            "cityCode": 12,
            "countryCode":57
        },
        {
            "number": 12345678,
            "cityCode": 13,
            "countryCode":55
        }
    ]
}

//login
{
    "email":"jaimec@dominio.com",
    "password":"2adffSiru8"
}

```
## Respuesta
```javascript
//creación
{
    "id": "9cb24d1a-cc90-4232-9303-dc5c5763abdc",
    "name": "jaime cosio",
    "email": "jaimec@dominio.com",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJpc3N1ZXIiLCJpYXQiOjE3MzY1MzU4NDAsImVtYWlsIjoiamFpbWVjQGRvbWluaW8uY29tIn0.R3Xr4pzu77AgfadMtyJVFFH7EO4XCS-eyhQTCILbvrQ",
    "created": "2025-01-10T19:04:00.764+00:00",
    "lastLogin": "2025-01-10T19:04:00.733+00:00",
    "phones": [
        {
            "number": 123456,
            "cityCode": 12,
            "countryCode": 57
        },
        {
            "number": 12345678,
            "cityCode": 13,
            "countryCode": 55
        }
    ],
    "active": true
}
//login
{
    "id": "fa2f5e91-c007-4e1e-879e-4dac3f395e3f",
    "created": "2025-01-10T19:28:27.494+00:00",
    "lastLogin": "2025-01-10T19:28:27.462+00:00",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJpc3N1ZXIiLCJpYXQiOjE3MzY1MzczMDksImVtYWlsIjoiamFpbWVjQGRvbWluaW8uY29tIn0.iBVC5rldLdNhDH2VHUg1NYm1e7SUUbBzFR7ZhrdOYXM",
    "name": "jaime cosio",
    "password": "$2a$10$zjl46rikYk/zUy9XaVltSO8lzf.A8Jk2t/kKvNCpV1daENk73JAXi",
    "phones": [
        {
            "number": 123456,
            "cityCode": 12,
            "countryCode": 57
        },
        {
            "number": 12345678,
            "cityCode": 13,
            "countryCode": 55
        }
    ],
    "active": true
}
```
