# back-SEJAVA-PLATFORM

El repositorio en cuestión es un proyecto Java que utiliza Spring Boot como su principal tecnología para construir una aplicación web. A continuación, se detalla la información solicitada:

## Tecnologías Utilizadas:
- **Java**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Spring Boot**: Marco de trabajo para la creación de aplicaciones Java con mínima configuración.
- **Spring Data JPA**: Para la persistencia de datos y acceso a la base de datos.
- **MySQL**: Sistema de gestión de bases de datos para almacenar los datos de la aplicación.
- **Lombok**: Biblioteca de Java que se utiliza para minimizar el código repetitivo.
- **Spring Security**: Para la implementación de seguridad en la aplicación.
- **JWT (Java Web Token)**: Utilizado para la autenticación y transmisión segura de información.

## Métodos de Seguridad:
- **Autenticación JWT**: Se utiliza JWT para manejar la autenticación de los usuarios. Esto permite que los servicios sean seguros y que solo los usuarios autenticados puedan acceder a ciertas rutas o funcionalidades.

## Estructura del Proyecto:
El proyecto sigue una estructura típica de una aplicación Spring Boot, organizada en paquetes que separan las entidades, repositorios, servicios, y controladores:

- **entity**: Contiene las clases de entidad que representan las tablas de la base de datos.
- **repository**: Contiene las interfaces de repositorio para el acceso a datos.
- **service**: Contiene las implementaciones de la lógica de negocio.
- **controller**: Contiene los controladores que manejan las solicitudes HTTP.

## Conexión a Base de Datos:
La conexión a la base de datos se configura en el archivo `application.properties` de Spring Boot, donde se especifican el nombre de la base de datos, el usuario, la contraseña, y otros parámetros de conexión relevantes para MySQL.

## Archivo SQL:
El archivo `sqlInitial.sql` contiene las instrucciones SQL para crear la base de datos, las tablas, y para insertar datos iniciales. Las tablas creadas incluyen `plan`, `persona`, `clases`, `cursos`, `examen`, `pregunta`, `respuestas`, `resultado_examen`, y `respuestas_alumno`, junto con las relaciones entre ellas.

## Ejemplo de Código para Conexión a Base de Datos:
Aunque el archivo específico de configuración no está directamente visible en los fragmentos de código proporcionados, la configuración de Spring Data JPA en `application.properties` típicamente se vería así:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/SSJAVA
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

## Ejemplo de Código para Crear una Entidad:
```java
@Entity
public class Persona {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nombres;
private String apellidos;
// Otros campos y métodos
}
```
## Ejemplo de Código para un Repositorio:
```java
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
```
## Ejemplo de Código para un Servicio:
```java
@Service
public class PersonaServiceImpl implements PersonaService {
private final PersonaRepository personaRepository;

    // Constructor, métodos de servicio
}
```
## Ejemplo de Código para un Controlador:
```java
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
private final PersonaService personaService;

    // Constructor, endpoints
}
```
La seguridad de tokens y JWT (JSON Web Tokens) en el código base de Java proporcionado se maneja principalmente a través de las clases `JwtUtil` y `JwtFilter`, junto con la configuración de seguridad definida en `SecurityConfig`.

## JwtUtil
La clase `JwtUtil` es responsable de la creación y validación de los JWT. Utiliza la biblioteca `com.auth0.jwt.JWT` para estas operaciones.

### Creación de JWT
El método `create` genera un nuevo token JWT con un sujeto (el email del usuario), un emisor, una fecha de emisión, una fecha de expiración (15 días después de la emisión) y lo firma con un algoritmo HMAC256 usando una clave secreta.

```java
public String create(String email) {
    return JWT.create()
            .withSubject(email)
            .withIssuer("javaSS")
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
            .sign(ALGORTIHM);
}
Validación de JWT
```
## El método isValid verifica si un token JWT es válido, comprobando su firma y estructura.

```java
public boolean isValid(String jwt) {
    try {
        JWT.require(ALGORTIHM)
                .build()
                .verify(jwt);
        return true;
    } catch (JWTVerificationException e) {
        return false;
    }
}
```
## Extracción de nombre de usuario
El método getUserName extrae el nombre de usuario (email en este caso) del sujeto del token JWT.

```java
public String getUserName(String jwt) {
    return JWT.require(ALGORTIHM)
            .build()
            .verify(jwt)
            .getSubject();
}
```
## JwtFilter
La clase JwtFilter extiende OncePerRequestFilter y se encarga de interceptar las peticiones HTTP para realizar la autenticación basada en JWT.

## Filtrado de peticiones
En el método doFilterInternal, primero verifica si la petición contiene un encabezado de autorización con un token JWT. Si es así, valida el token usando JwtUtil.isValid. Si el token es válido, extrae el nombre de usuario, carga los detalles del usuario mediante UserDetailsService, y establece la autenticación en el contexto de seguridad de Spring.

```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authHeader != null && !authHeader.isEmpty() && authHeader.startsWith("Bearer")) {
        String jwt = authHeader.split(" ")[1].trim();

        if (this.jwtUtil.isValid(jwt)) {
            String username = this.jwtUtil.getUserName(jwt);
            User user = (User) this.userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), null, user.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
    filterChain.doFilter(request, response);
}
```
## SecurityConfig
La configuración de seguridad (SecurityConfig) utiliza HttpSecurity para definir las reglas de seguridad de la aplicación.

## Configuración de seguridad
Se define una política de sesión sin estado, se configuran las rutas que requieren autenticación y los roles necesarios para cada una, se deshabilita CSRF para ciertas rutas, y se agrega JwtFilter antes del filtro de autenticación básica para interceptar las peticiones HTTP.

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("A", "E", "P")
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);

    return http.build();
}
```
#### En resumen, la seguridad de tokens y JWT en esta aplicación Java se gestiona a través de la generación, validación y autenticación de tokens JWT, asegurando que solo los usuarios autenticados y autorizados puedan acceder a ciertas rutas y funcionalidades de la aplicación.

#### Este resumen proporciona una visión general del proyecto, incluyendo las tecnologías utilizadas, métodos de seguridad, estructura del proyecto, y detalles sobre la conexión a la base de datos y el archivo SQL inicial.# back-clasificador
