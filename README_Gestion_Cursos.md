# 🧾 Sistema de Gestión de Cursos (Spring Boot + Thymeleaf)

## 📘 Descripción General
Proyecto completo con **Spring Boot 3.5.7**, **Thymeleaf**, y **MySQL**, dividido en:
1. **apimatricula** → Backend REST.
2. **frontmatricula** → Frontend MVC.

---

## ⚙️ Tecnologías
- Java 17
- Spring Boot 3.5.7
- Thymeleaf
- MySQL
- Maven
- Lombok
- HikariCP

---

## 🧱 Estructura
```
📦 jbc5laboratorio4
 ├── 📁 apimatricula
 └── 📁 frontmatricula
```
Cada módulo puede ejecutarse de forma independiente.

---

## 🧩 Diagrama de Arquitectura

```text
┌─────────────────────────────────────────────────────────────┐
│                         👩‍💻 USUARIO                         │
│                  Navegador Web / Cliente HTTP               │
└─────────────────────────────────────────────────────────────┘
                              │
                              │  HTTP (Puerto 8082)
                              ▼
┌─────────────────────────────────────────────────────────────┐
│ 🎨 FRONTEND – Spring Boot + Thymeleaf                       │
│-------------------------------------------------------------│
│ • Templates: mantenimientoCursos.html / nuevoCurso.html     │
│ • Controlador: CursoController (@Controller)                │
│ • Servicio: CursoServiceImpl                                │
│ • Repositorio: CursoRepositoryImpl (RestTemplate)           │
│-------------------------------------------------------------│
│  🔗 Comunicación: Consume API REST del backend (JSON)        │
│  🔸 Puerto: 8082                                             │
└─────────────────────────────────────────────────────────────┘
                              │
                              │  HTTP JSON (Puerto 8081)
                              ▼
┌─────────────────────────────────────────────────────────────┐
│ ⚙️ BACKEND – Spring Boot API REST                           │
│-------------------------------------------------------------│
│ • Controlador: CursoRestController (@RestController)         │
│ • Servicio: CursoServiceImpl (Reglas de negocio CRUD)        │
│ • Repositorio: CursoRepository (Spring Data JPA)             │
│-------------------------------------------------------------│
│  🔗 Comunicación: expone API REST consumida por el frontend  │
│  🔸 Puerto: 8081                                             │
└─────────────────────────────────────────────────────────────┘
                              │
                              │  JDBC (MySQL Driver)
                              ▼
┌─────────────────────────────────────────────────────────────┐
│ 🗄️ BASE DE DATOS – MySQL                                    │
│-------------------------------------------------------------│
│ • Tabla: curso                                               │
│ • Campos: idcurso, nomcurso, fechainicio,                   │
│            alumnosmin, alumnosact, estado                   │
│-------------------------------------------------------------│
│  🔸 Puerto: 3306                                             │
└─────────────────────────────────────────────────────────────┘
```

## 👁️ Visuales del Proyecto

![Vista del Frontend](Documents/Image1.png)

![Edición de Curso](Documents/Image2.png)

---

## 🚀 Ejecución
### 1️⃣ Backend
```bash
cd apimatricula
mvn clean spring-boot:run -DskipTests
```
👉 [http://localhost:8081/api/v1/cursos](http://localhost:8081/api/v1/cursos)

### 2️⃣ Frontend
```bash
cd frontmatricula
mvn clean spring-boot:run -DskipTests
```
👉 [http://localhost:8082/cursos](http://localhost:8082/cursos)

---

## 🧾 Flujo de Datos
1. El usuario accede a `/cursos`.
2. El front consulta el backend vía RestTemplate.
3. El backend responde con JSON (lista de cursos).
4. Thymeleaf renderiza la vista con la data.
5. Los formularios permiten crear, editar o eliminar cursos.

---

## 🎨 Interfaz
- Botón principal: verde institucional.
- Fondo blanco, sombras suaves.
- Tipografía “Inter” moderna.
- Totalmente responsivo.

---

## 🧠 Créditos
**Autor:** Styp C.  
**Institución:** CIBERTEC  
**Versión:** 2025.11  
**Estado:** ✅ Funcional
