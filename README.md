# Banco XYZ - Arquitectura Backend for Frontend (BFF)

## Descripción

Este proyecto implementa una arquitectura basada en el patrón **Backend for Frontend (BFF)** para el sistema bancario simulado **Banco XYZ**.

El objetivo es disponer de diferentes interfaces backend especializadas según el canal utilizado por el cliente:

- **BFF Web:** orientado a clientes que utilizan un navegador web, entregando información completa de las cuentas y sus movimientos.
- **BFF Mobile:** orientado a dispositivos móviles, entregando información reducida y los últimos movimientos para optimizar la comunicación.
- **BFF ATM:** orientado a cajeros automáticos, proporcionando operaciones esenciales como consulta de saldo y retiros.

Los distintos BFF no acceden directamente a la base de datos. Las operaciones de negocio y el acceso a los datos son centralizados mediante el servicio **Backend Core**.

Además, el proyecto incorpora un proceso **Spring Batch** encargado de procesar los datos provenientes de archivos CSV y poblar la base de datos utilizada por los microservicios.

---

## Arquitectura

```text
                        ┌─────────────────────┐
                        │   Archivos CSV      │
                        │ bank_legacy_data    │
                        └──────────┬──────────┘
                                   │
                                   ▼
                        ┌─────────────────────┐
                        │    Spring Batch     │
                        │       :8080         │
                        └──────────┬──────────┘
                                   │
                                   ▼
                        ┌─────────────────────┐
                        │        MySQL        │
                        │    banco_xyz        │
                        └──────────┬──────────┘
                                   │
                                   ▼
                        ┌─────────────────────┐
                        │    Backend Core     │
                        │       :8081         │
                        └──────────┬──────────┘
                                   │
                    ┌──────────────┼──────────────┐
                    │              │              │
                    ▼              ▼              ▼
             ┌────────────┐ ┌────────────┐ ┌────────────┐
             │  BFF Web   │ │BFF Mobile  │ │  BFF ATM   │
             │    :8082   │ │    :8083   │ │    :8084   │
             └────────────┘ └────────────┘ └────────────┘
                    │              │              │
                    ▼              ▼              ▼
                Cliente Web   Cliente Mobile   Cajero ATM
```

### Flujo de comunicación

Los clientes se comunican exclusivamente con el BFF correspondiente.

```text
Cliente
   │
   ▼
BFF específico
   │
   │ HTTP/REST + OpenFeign
   ▼
Backend Core
   │
   ▼
MySQL
```

De esta manera, cada BFF puede adaptar la información entregada al tipo de cliente sin duplicar las reglas de negocio ni acceder directamente a la base de datos.

---

## Estructura del proyecto

Todos los microservicios se encuentran dentro del mismo repositorio:

```text
banco-xyz-bff/
│
├── docker-compose.yaml
│
├── banco-batch/
│   ├── src/
│   ├── data/
│   │   ├── semana_1/
│   │   ├── semana_2/
│   │   └── semana_3/
│   └── pom.xml
│
├── backend-core/
│   ├── src/
│   └── pom.xml
│
├── bff-web/
│   ├── src/
│   └── pom.xml
│
├── bff-mobile/
│   ├── src/
│   └── pom.xml
│
└── bff-atm/
    ├── src/
    └── pom.xml
```

---

## Microservicios

| Servicio | Puerto | Función |
|---|---:|---|
| Banco Batch | `8080` | Procesamiento y carga de datos |
| Backend Core | `8081` | Lógica de negocio y acceso a datos |
| BFF Web | `8082` | Backend especializado para clientes Web |
| BFF Mobile | `8083` | Backend especializado para clientes Mobile |
| BFF ATM | `8084` | Backend especializado para cajeros automáticos |
| MySQL | `3307` | Persistencia de datos |

---

# Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Batch
- Spring Cloud OpenFeign
- MySQL
- Maven
- Docker / Docker Compose
- REST API

---

# Requisitos previos

Antes de ejecutar el proyecto se requiere tener instalado:

- Java 21
- Docker
- Docker Compose
- Maven (opcional, ya que los proyectos incluyen Maven Wrapper)

---

# Puesta en marcha

El orden de ejecución es importante.

## 1. Levantar la base de datos

Desde la raíz del proyecto ejecutar:

```bash
docker-compose up -d
```

Esto inicia el contenedor de MySQL utilizado por la aplicación.

La base de datos utilizada es:

```text
banco_xyz
```

---

## 2. Levantar Banco Batch

Ingresar al directorio:

```bash
cd banco-batch
```

Ejecutar:

```bash
mvnw spring-boot:run
```

En Windows también se puede utilizar:

```bash
mvnw.cmd spring-boot:run
```

El servicio estará disponible en:

```text
http://localhost:8080
```

### Procesar los datos

Una vez iniciado Banco Batch, se puede ejecutar el procesamiento mediante:

```text
http://localhost:8080/api/batch/procesar
```

También se encuentra disponible el procesamiento de estados anuales:

```text
http://localhost:8080/api/batch/estados-anuales
```

El procesamiento utiliza los archivos CSV ubicados dentro de:

```text
banco-batch/data/
```

y permite poblar las tablas utilizadas posteriormente por Backend Core.

---

# 3. Levantar Backend Core

Ingresar al directorio:

```bash
cd backend-core
```

Ejecutar:

```bash
mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

Backend Core estará disponible en:

```text
http://localhost:8081
```

**Backend Core debe estar ejecutándose para utilizar cualquiera de los BFF.**

---

# 4. Levantar BFF Web

Ingresar a:

```bash
cd bff-web
```

Ejecutar:

```bash
mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

Disponible en:

```text
http://localhost:8082
```

---

# 5. Levantar BFF Mobile

Ingresar a:

```bash
cd bff-mobile
```

Ejecutar:

```bash
mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

Disponible en:

```text
http://localhost:8083
```

---

# 6. Levantar BFF ATM

Ingresar a:

```bash
cd bff-atm
```

Ejecutar:

```bash
mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

Disponible en:

```text
http://localhost:8084
```

---

# Endpoints Backend Core

Backend Core concentra el acceso a la información y las operaciones principales.

## Consultar cuenta

```http
GET http://localhost:8081/api/cuentas/101
```

Respuesta:

```json
{
    "cuentaId": 101,
    "saldo": 5400.00
}
```

El número `101` corresponde al identificador de la cuenta consultada y puede reemplazarse por otra cuenta existente.

---

## Consultar transacciones de una cuenta

```http
GET http://localhost:8081/api/transacciones/cuenta/101
```

Respuesta:

```json
[
    {
        "cuentaId": 101,
        "fecha": "2024-02-16",
        "id": 75,
        "monto": 100.00,
        "tipo": "compra"
    }
]
```

La respuesta completa contiene las transacciones asociadas a la cuenta.

---

## Realizar retiro

```http
POST http://localhost:8081/api/cuentas/101/retiros
```

Body:

```json
{
    "monto": "2500"
}
```

Respuesta:

```json
{
    "cuentaId": 101,
    "saldo": 7900.00
}
```

El retiro es procesado por Backend Core, que actualiza el saldo de la cuenta y registra la operación correspondiente.

---

# BFF Web

El BFF Web adapta la información de Backend Core para un cliente web que requiere mayor cantidad de información.

## Detalle de cuenta

```http
GET http://localhost:8082/api/web/cuentas/101
```

Respuesta:

```json
{
    "cuentaId": 101,
    "resumen": {
        "cuentaId": 101,
        "resumen": {
            "saldo": 12900.00,
            "totalDepositos": 38000.00,
            "totalMovimientos": 43,
            "totalRetiros": 25100.00
        }
    },
    "movimientos": [
        {
            "fecha": "2024-02-16",
            "id": 75,
            "monto": 100.00,
            "tipo": "compra"
        }
    ]
}
```

Este endpoint entrega información completa de la cuenta, incluyendo:

- Identificador de cuenta.
- Saldo.
- Total de depósitos.
- Total de retiros.
- Total de movimientos.
- Detalle de movimientos.

---

## Resumen de cuenta

```http
GET http://localhost:8082/api/web/cuentas/101/resumen
```

Respuesta:

```json
{
    "cuentaId": 101,
    "resumen": {
        "saldo": 12900.00,
        "totalDepositos": 38000.00,
        "totalMovimientos": 43,
        "totalRetiros": 25100.00
    }
}
```

Este endpoint permite obtener únicamente la información resumida requerida por una interfaz web.

---

# BFF Mobile

El BFF Mobile está diseñado para entregar una respuesta más liviana, reduciendo la cantidad de información transferida al dispositivo.

## Consultar cuenta

```http
GET http://localhost:8083/api/mobile/cuentas/101
```

Respuesta:

```json
{
    "cuentaId": 101,
    "saldo": 5400.00,
    "ultimosMovimientos": [
        {
            "fecha": "2026-09-07",
            "monto": 2500.00,
            "tipo": "retiro"
        }
    ]
}
```

El endpoint entrega:

- Identificador de cuenta.
- Saldo.
- Últimos 5 movimientos.

Los movimientos se ordenan desde el más reciente al más antiguo.

Esta adaptación permite reducir el volumen de información entregada a clientes móviles.

---

# BFF ATM

El BFF ATM está diseñado para operaciones simples y críticas de un cajero automático.

Las operaciones implementadas son:

- Consulta de saldo.
- Retiro de dinero.

## Consulta de saldo

```http
GET http://localhost:8084/api/atm/cuentas/101
```

Respuesta:

```json
{
    "cuentaId": 101,
    "saldo": 7900.00
}
```

---

## Realizar retiro

```http
POST http://localhost:8084/api/atm/cuentas/101/retiros
```

Body:

```json
{
    "monto": 2500
}
```

Respuesta:

```json
{
    "cuentaId": 101,
    "estado": "APROBADO",
    "montoRetirado": 2500,
    "saldo": 5400.00
}
```

El BFF ATM recibe la solicitud y la deriva hacia Backend Core mediante **OpenFeign**.

Backend Core se encarga de:

1. Validar que la cuenta exista.
2. Validar que el monto sea válido.
3. Verificar el saldo disponible.
4. Actualizar el saldo.
5. Registrar la transacción.
6. Retornar el nuevo saldo.

El BFF ATM adapta posteriormente la respuesta al formato requerido por el canal ATM.

---

# Comunicación entre servicios

Los BFF utilizan **Spring Cloud OpenFeign** para comunicarse con Backend Core.

Por ejemplo:

```text
BFF ATM
   │
   │ OpenFeign
   ▼
Backend Core
   │
   ├── CuentaService
   │
   └── TransaccionService
          │
          ▼
        MySQL
```

Los BFF no poseen acceso directo a la base de datos.

Esto permite mantener separadas las responsabilidades:

- **Batch:** procesamiento de datos.
- **Backend Core:** reglas de negocio y persistencia.
- **BFF:** adaptación de información según el canal.

---

# Persistencia

La aplicación utiliza MySQL mediante Docker Compose.

Las principales entidades utilizadas por Backend Core son:

```text
cuentas
transacciones
```

El proceso Batch es responsable de poblar la información inicial utilizada por estos servicios.

---

# Consideraciones sobre los datos

Los datos utilizados corresponden a información bancaria simulada proveniente de archivos CSV.

Para la implementación de los BFF se utilizan principalmente las relaciones entre:

```text
cuentas
    │
    └── transacciones
```

Los datos de intereses provenientes del archivo `intereses.csv` presentan inconsistencias en la relación entre cuentas y clientes, por lo que no se utilizan para las funcionalidades implementadas en los BFF.

Además, los archivos de datos no proporcionan un saldo bancario inicial explícito. Para efectos de esta implementación, el saldo utilizado por `cuentas.saldo` corresponde al movimiento neto calculado durante el procesamiento Batch, permitiendo simular las operaciones de consulta y retiro requeridas por la actividad.

---

# Arquitectura BFF implementada

La solución permite adaptar la información según las necesidades de cada canal:

| Característica | Web | Mobile | ATM |
|---|---|---|---|
| Consulta de saldo | ✓ | ✓ | ✓ |
| Resumen de movimientos | ✓ | - | - |
| Movimientos completos | ✓ | - | - |
| Últimos movimientos | - | ✓ | - |
| Retiro | - | - | ✓ |
| Respuesta optimizada | ✓ | ✓ | ✓ |
| Acceso directo a BD | ✗ | ✗ | ✗ |
| Comunicación con Core | ✓ | ✓ | ✓ |

---

# Seguridad

La implementación actual se encuentra enfocada en la construcción y validación funcional del patrón **Backend for Frontend**.

Como mejora futura se contempla incorporar mecanismos de:

- Autenticación.
- Autorización basada en roles/permisos.
- Tokens de acceso.
- Seguridad específica para cada canal.
- Protección de las operaciones críticas del BFF ATM.

Estas funcionalidades pueden incorporarse posteriormente mediante Spring Security y mecanismos de autenticación basados en tokens.

---

# Resultado

La solución implementa tres BFF independientes, cada uno especializado según las necesidades del cliente:

```text
                    Backend Core
                         │
          ┌──────────────┼──────────────┐
          │              │              │
          ▼              ▼              ▼
       BFF Web       BFF Mobile       BFF ATM
          │              │              │
          ▼              ▼              ▼
       Web UI        Aplicación       Cajero
                      Mobile
```

De esta forma se aplica el patrón **Backend for Frontend**, evitando que todos los clientes dependan de una única interfaz backend y permitiendo que cada canal reciba únicamente la información y operaciones que necesita.