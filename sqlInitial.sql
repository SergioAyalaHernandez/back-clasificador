CREATE DATABASE SSJAVA;
USE SSJAVA;

-- Tabla plan
CREATE TABLE plan (
    id_plan INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    tipo_plan VARCHAR(20) NOT NULL
);

-- Tabla persona
CREATE TABLE persona (
    id_persona INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombres VARCHAR(65) NOT NULL,
    apellidos VARCHAR(65) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(75) NOT NULL,
    celular VARCHAR(20) NOT NULL,
    plan_FK INT NOT NULL,
    tipo_usuario varchar(1) NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (plan_FK) REFERENCES plan(id_plan)
);

-- Tabla clases
CREATE TABLE clases (
    id_clase INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    profesor VARCHAR(25) NOT NULL,
    contenido VARCHAR(255) NOT NULL,
    link_clase VARCHAR(255) NOT NULL,
    json_ejemplo TEXT,
    ejemplos_codigo TEXT
);

-- Tabla cursos
CREATE TABLE cursos (
    id_cursos INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    id_clase INT NOT NULL UNIQUE, -- Relación 1N1 con clases
    id_plan INT NOT NULL UNIQUE,
    FOREIGN KEY (id_clase) REFERENCES clases(id_clase),
    FOREIGN KEY (id_plan) REFERENCES plan(id_plan)
);


-- Tabla examen
CREATE TABLE examen (
    id_examen INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    contenido VARCHAR(200) NOT NULL,
    id_curso INT NOT NULL UNIQUE, -- Relación 1N1 con cursos
    FOREIGN KEY (id_curso) REFERENCES cursos(id_cursos)
);

CREATE TABLE pregunta (
    id_pregunta INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    id_examen INT NOT NULL,
    FOREIGN KEY (id_examen) REFERENCES examen(id_examen)
);

CREATE TABLE respuestas (
    id_respuesta INT AUTO_INCREMENT PRIMARY KEY,
    id_pregunta INT NOT NULL,
    respuesta VARCHAR(255) NOT NULL,
    es_correcta BOOLEAN NOT NULL,
    FOREIGN KEY (id_pregunta) REFERENCES pregunta(id_pregunta)
);



-- Tabla resultado_examen
CREATE TABLE resultado_examen (
    id_resultado INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_persona INT NOT NULL,
    id_examen INT NOT NULL,
    resultado float,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    FOREIGN KEY (id_examen) REFERENCES examen(id_examen)
);

CREATE TABLE respuestas_alumno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_respuesta INT NOT NULL,
    id_pregunta INT NOT NULL,
    id_examen INT NOT NULL,
    id_resultado_examen INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_respuesta) REFERENCES respuestas(id_respuesta),
    FOREIGN KEY (id_pregunta) REFERENCES pregunta(id_pregunta),
    FOREIGN KEY (id_examen) REFERENCES examen(id_examen),
    FOREIGN KEY (id_resultado_examen) REFERENCES resultado_examen(id_resultado)
);


INSERT INTO plan (tipo_plan) VALUES
('basic'),
('medium'),
('advance');

-- Insertar datos en la tabla clases
INSERT INTO clases (profesor, contenido, link_clase, json_ejemplo, ejemplos_codigo)
VALUES
('Sergio Ayala', 'Introducción a JPA', './assets/fondClases3.png ', 'https://www.youtube.com/embed/ppDv4N5A31E?si=GCCLJmmxFhJgGle6', 'import javax.persistence.EntityManager;\nimport javax.persistence.EntityManagerFactory;\nimport javax.persistence.Persistence;\n\npublic class JPAExample {\n    public static void main(String[] args) {\n        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_NAME");\n        EntityManager em = emf.createEntityManager();\n\n        // Código de ejemplo de JPA\n        try {\n            em.getTransaction().begin();\n\n            // Aquí va el código de persistencia y consultas\n\n            em.getTransaction().commit();\n        } catch (Exception e) {\n            e.printStackTrace();\n            em.getTransaction().rollback();\n        } finally {\n            em.close();\n            emf.close();\n        }\n    }\n}'
),
('Santiago Pereira', 'Configuración de Spring Data JPA', './assets/fondClases.png', 'https://www.youtube.com/embed/f8ZfymWhtxA?si=nI02LKdLvPasgqyb": "Example configuration for Spring Data JPA"}', '@Configuration\n@EnableJpaRepositories(basePackages = "com.example.repositories")\n@EnableTransactionManagement\npublic class SpringDataJPAConfig {\n\n    @Bean\n    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {\n        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();\n        em.setDataSource(dataSource());\n        em.setPackagesToScan("com.example.models");\n\n        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();\n        em.setJpaVendorAdapter(vendorAdapter);\n\n        return em;\n    }\n\n    // Otros métodos de configuración\n}'
),
('Santiago Pereira', 'Manejo de Excepciones en Java', './assets/fondClases.JPEG', 'https://www.youtube.com/embed/8rokLhiMT1M?si=xu0U3JF_oIKG9B1j', 'public class JavaExceptionHandling {\n\n    public static void main(String[] args) {\n        try {\n            // Código que puede lanzar excepciones\n            int resultado = 10 / 0; // Esto provocará una excepción de división por cero\n        } catch (Exception e) {\n            System.err.println("Error: " + e.getMessage());\n        } finally {\n            // Código a ejecutar siempre\n            System.out.println("Cierre del programa.");\n        }\n    }\n}'
);


INSERT INTO cursos (nombre, descripcion, id_clase, id_plan) VALUES
('jpaJava', 'Curso sobre JPA en Java', 1, 1),
('microserviciosRestJava', 'Curso sobre microservicios REST en Java', 2, 2),
('javaSE', 'Curso sobre Java SE', 3, 3);


INSERT INTO examen (titulo, contenido, id_curso) VALUES
('Preguntas JPA', 'Este exámen contendrá información vista en nuestro curso de jpa', 1),
('Preguntas Microservicios', 'Este exámen contendrá información vista en nuestro curso de Microservicios', 2),
('Preguntas Java SE', 'Este exámen contendrá información vista en nuestro curso de Java SE', 3);


INSERT INTO pregunta (descripcion,  id_examen) VALUES
('¿Qué significa JPA?', 1),
('¿Qué anotación se usa para mapear una clase Java a una tabla en una base de datos?',  1),
('¿Qué anotación se utiliza para definir una columna como clave primaria?',  1),
('¿Cuál es el propósito de la anotación @Entity?', 1),
('¿Qué anotación se utiliza para una relación de uno a muchos?', 1),
('¿Qué anotación se usa para especificar una columna como única?', 1),
('¿Qué significa la anotación @Table?', 1),
('¿Cómo se especifica una estrategia de generación de claves primarias?', 1),
('¿Qué es el EntityManager en JPA?', 1),
('¿Cuál es la diferencia entre persist y merge?',1);

-- Preguntas de Microservices REST en Java
INSERT INTO pregunta (descripcion,  id_examen) VALUES
('¿Qué es un microservicio?', 2),
('¿Qué biblioteca se usa comúnmente en Java para crear APIs REST?', 2),
('¿Qué es Spring Boot?', 2),
('¿Qué anotación se utiliza para definir un controlador REST en Spring?', 2),
('¿Qué anotación se usa para mapear una URL a un método de controlador?', 2),
('¿Qué significa el principio de separación de preocupaciones en microservicios?', 2),
('¿Qué es un FeignClient?', 2),
('¿Qué herramienta se usa para documentar APIs REST?', 2),
('¿Qué es el balanceo de carga?', 2),
('¿Qué es un gateway en microservicios?', 2);

-- Preguntas de Java SE
INSERT INTO pregunta (descripcion, id_examen) VALUES
('¿Qué significa SE en Java SE?', 3),
('¿Qué es la JVM?',3),
('¿Qué es un Object en Java?', 3),
('¿Qué palabra clave se usa para heredar una clase en Java?', 3),
('¿Qué es una interfaz en Java?', 3),
('¿Qué es la sobrecarga de métodos?',  3),
('¿Qué es la encapsulación?',  3),
('¿Qué es una excepción en Java?', 3),
('¿Cómo se define un bucle for en Java?', 3),
('¿Qué es el recolector de basura en Java?', 3);

-- Respuestas para Preguntas de JPA
INSERT INTO respuestas (id_pregunta, respuesta, es_correcta) VALUES
(1, 'Java Persistence API', true),
(1, 'Java Programming Application', false),
(1, 'Java Programming Annotations', false),
(1, 'Java Persistence Annotations', false),
(2, '@Entity', false),
(2, '@Table', false),
(2, '@PersistenceContext', false),
(2, '@MappedSuperclass', true),
(3, '@Id', true),
(3, '@PrimaryKey', false),
(3, '@Key', false),
(3, '@KeyColumn', false),
(4, 'Define que la clase es una entidad persistente', true),
(4, 'Define una tabla en la base de datos', false),
(4, 'Define una relación uno a muchos', false),
(4, 'Define una relación muchos a uno', false),
(5, '@OneToMany', true),
(5, '@OneToOne', false),
(5, '@ManyToOne', false),
(5, '@ManyToMany', false),
(6, '@Column(unique = true)', true),
(6, '@Unique', false),
(6, '@UniqueConstraint', false),
(6, '@Index', false),
(7, 'Define la tabla a la que está mapeada la entidad', false),
(7, 'Define una columna en la tabla', false),
(7, 'Define una clave foránea', false),
(7, 'Define una relación entre entidades', true),
(8, 'Usando la anotación @GeneratedValue', true),
(8, 'Usando la anotación @SequenceGenerator', false),
(8, 'Usando la anotación @Identity', false),
(8, 'Usando la anotación @TableGenerator', false),
(9, 'Es una interfaz que gestiona las entidades en JPA', false),
(9, 'Es una clase que gestiona las entidades en JPA', false),
(9, 'Es una anotación en JPA', false),
(9, 'Es un método para persistir entidades en la base de datos', true),
(10, 'Persist guarda una entidad nueva en la base de datos', true),
(10, 'Merge guarda una entidad nueva en la base de datos', false),
(10, 'Merge actualiza una entidad existente en la base de datos', true),
(10, 'Persist actualiza una entidad existente en la base de datos', false);

-- Respuestas para Preguntas de Microservices REST en Java
INSERT INTO respuestas (id_pregunta, respuesta, es_correcta) VALUES
(11, 'Es una arquitectura de desarrollo de software', true),
(11, 'Es una biblioteca en Java', false),
(11, 'Es un entorno de desarrollo integrado', false),
(11, 'Es una base de datos', false),
(12, 'Jersey', false),
(12, 'Spring MVC', false),
(12, 'Spring Boot', true),
(12, 'Hibernate', false),
(13, 'Es un framework para el desarrollo de aplicaciones Java', true),
(13, 'Es un framework para el desarrollo de aplicaciones Python', false),
(13, 'Es una biblioteca para el desarrollo de APIs REST', false),
(13, 'Es una base de datos', false),
(14, '@RestController', false),
(14, '@Service', false),
(14, '@Controller', false),
(14, '@RequestMapping', true),
(15, '@GetMapping', false),
(15, '@PostMapping', false),
(15, '@RequestMapping', true),
(15, '@RequestMethod', false),
(16, 'Consiste en separar las funcionalidades de un sistema en partes independientes', true),
(16, 'Consiste en agrupar todas las funcionalidades en un solo lugar', false),
(16, 'No tiene relevancia en el desarrollo de microservicios', false),
(16, 'Significa dividir el código en pequeñas partes pero no necesariamente independientes', false),
(17, 'Es un cliente HTTP en Java', false),
(17, 'Es un cliente de bases de datos en Java', false),
(17, 'Es un cliente para servicios REST', true),
(17, 'Es un cliente de correo electrónico', false),
(18, 'Swagger', true),
(18, 'Postman', false),
(18, 'Insomnia', false),
(18, 'SOAPUI', false),
(19, 'Es la distribución de la carga de trabajo entre varios servidores', true),
(19, 'Es la distribución de la carga de trabajo entre varios microservicios', false),
(19, 'Es la optimización de la carga de trabajo en un solo servidor', false),
(19, 'Es la optimización de la carga de trabajo entre diferentes capas de una aplicación', false),
(20, 'Es un punto de entrada único para acceder a múltiples microservicios', true),
(20, 'Es un servidor que almacena los microservicios', false),
(20, 'Es una base de datos para microservicios', false),
(20, 'Es un cliente HTTP', false);

-- Respuestas para Preguntas de Java SE
INSERT INTO respuestas (id_pregunta, respuesta, es_correcta) VALUES
(21, 'Standard Edition', true),
(21, 'Service Edition', false),
(21, 'Sergio ElMejor', false),
(21, 'Santiago ElMejor', false),
(22, 'Java Virtual Machine', true),
(22, 'Java Virtual Memory', false),
(22, 'Java Virtual Method', false),
(22, 'Java Virtual Modifier', false),
(23, 'Es una instancia de una clase en Java', true),
(23, 'Es una variable en Java', false),
(23, 'Es un método en Java', false),
(23, 'Es una interfaz en Java', false),
(24, 'extends', true),
(24, 'implements', false),
(24, 'inherit', false),
(24, 'extend', false),
(25, 'Es una interfaz en Java', true),
(25, 'Es una clase en Java', false),
(25, 'Es un método en Java', false),
(25, 'Es una variable en Java', false),
(26, 'Es tener varios métodos con el mismo nombre pero diferentes parámetros', true),
(26, 'Es tener varios métodos con el mismo nombre y parámetros', false),
(26, 'Es tener un método con múltiples parámetros', false),
(26, 'Es tener un método con diferentes nombres', false),
(27, 'Es ocultar la implementación de un objeto y mostrar solo la interfaz', true),
(27, 'Es mostrar la implementación de un objeto y ocultar la interfaz', false),
(27, 'Es combinar la interfaz y la implementación de un objeto', false),
(27, 'Es crear objetos sin interfaz', false),
(28, 'Es un error o condición inesperada durante la ejecución del programa', true),
(28, 'Es un tipo de dato en Java', false),
(28, 'Es una variable en Java', false),
(28, 'Es una excepción controlada en Java', false),
(29, 'for (int i = 0; i < limite; i++) { }', true),
(29, 'for (int i = a; i <= limite; i++) { }', false),
(29, 'for (int i = 0; i is true limite; i++) { }', false),
(29, 'for (int i = 1; i <> limite; i++) { }', false),
(30, 'Es un componente de Java que gestiona la memoria de objetos no utilizados', true),
(30, 'Es un componente de Java que gestiona la memoria de objetos utilizados', false),
(30, 'Es un componente de Java que gestiona la memoria de programas externos', false),
(30, 'Es un componente de Java que gestiona la memoria de la JVM', false);


