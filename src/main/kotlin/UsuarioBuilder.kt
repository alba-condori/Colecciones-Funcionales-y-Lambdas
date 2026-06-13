/**
 * Ejercicio 5: It y Scope Functions (run, apply, also, let)
 *
 * Implementa los métodos de esta clase para que pasen todos los tests
 * del archivo Ejercicio5ItScopeFunctionsTest.kt
 *
 * IMPORTANTE: No modifiques la firma de los métodos, solo implementa su lógica.
 * IMPORTANTE: Debes usar las scope functions indicadas en cada sección.
 */

data class Usuario(
    var id: Int = 0,
    var nombre: String = "",
    var email: String = "",
    var activo: Boolean = false,
    var roles: MutableList<String> = mutableListOf(),
    var configuracion: ConfiguracionUsuario = ConfiguracionUsuario(),
)

data class ConfiguracionUsuario(
    var tema: String = "claro",
    var idioma: String = "es",
    var notificaciones: Boolean = true,
    var nivelPrivacidad: Int = 1,
)

data class Validacion(
    val campo: String,
    val valido: Boolean,
    val mensaje: String,
)

class UsuarioBuilder {
    // Parte A: Uso del parámetro implícito 'it'

    fun procesarNumeros(numeros: List<Int>): List<Int> {
        return numeros.filter { it % 2 == 0 }
                      .map { it * 10 }
    }

    fun validarUsuarios(usuarios: List<Usuario>): List<List<Validacion>> {
        return usuarios.map {
            listOf(
                Validacion("nombre", it.nombre.isNotEmpty(), "El nombre no debe estar vacío"),
                Validacion("email", it.email.contains("@"), "El email debe contener @"),
                Validacion("roles", it.roles.isNotEmpty(), "Debe tener al menos un rol")
            )
        }
    }

    fun procesarTextos(textos: List<String>): List<String> {
        return textos.map { it.trim() } // Modifica cada texto con 'it'
            .map { it.lowercase() }
            .filter { it.isNotEmpty() }   // Filtra los que no estén vacíos

    }

    // Parte B: Función run

    fun calcularNivelAcceso(usuario: Usuario): Int {
        return usuario.run {
            var puntos = 0
            if (activo == true) {
                puntos += 10
            }
            for (role in roles) {
                puntos += 5
            }
            if (email.contains("@empresa.com")) {
                puntos += 5
            }
            puntos
        }
    }

    fun crearUsuarioConTipo(tipo: String): Usuario {
        return Usuario().run {

            if (tipo == "ADMIN") {
                roles.add("ADMIN")
                configuracion.nivelPrivacidad = 3 //configuracion esta en el data class usuario
                configuracion.notificaciones = true
            }
            else if (tipo == "USER") {
                roles.add("USER")
                configuracion.nivelPrivacidad = 1
                configuracion.notificaciones = false
            }
            this
        }
    }

    // Parte C: Función apply

    fun crearUsuarioCompleto(
        nombre: String,
        email: String,
        roles: List<String>,
    ): Usuario {
        return Usuario().apply {
            this.nombre = nombre
            this.email = email
            this.activo = true
            this.roles = roles.toMutableList()
            this.configuracion = ConfiguracionUsuario()
        }
        TODO(
            """
            Implementar usando 'apply':
            - Crear usuario y configurar todas sus propiedades
            - Establecer activo = true
            - Asignar roles
            - Crear configuración por defecto
        """,
        )
    }

    fun actualizarUsuario(
        usuario: Usuario,
        actualizacion: Usuario.() -> Unit,
    ): Usuario {
        return Usuario().apply { actualizacion() }
        TODO("Implementar: Usar 'apply' para aplicar la función de actualización al usuario")
    }

    // Parte D: Función also

    fun crearUsuarioConLog(
        nombre: String,
        email: String,
        onLog: (String) -> Unit,
    ): Usuario {
        TODO(
            """
            Implementar usando 'also' para logging:
            - Crear usuario
            - Loggear "Usuario creado: [nombre]"
            - Asignar email y loggear "Email asignado: [email]"
            - Activar usuario y loggear "Usuario activado"
        """,
        )
    }

    fun crearYValidar(
        nombre: String,
        email: String,
    ): Pair<Usuario, Boolean> {
        TODO(
            """
            Implementar usando 'also' para validación:
            - Crear usuario
            - Validar que nombre no esté vacío y email contenga '@'
            - Retornar par (usuario, esValido)
        """,
        )
    }

    // Parte E: Función let

    fun procesarEmailOpcional(email: String?): String {
        TODO(
            """
            Implementar usando 'let':
            - Si email no es null: "Usuario con email: [email]"
            - Si email es null: "Usuario sin email"
        """,
        )
    }

    fun generarMensajesBienvenida(usuarios: List<Usuario>): List<String> {
        TODO(
            """
            Implementar usando 'let':
            - Solo procesar usuarios activos con email no vacío
            - Generar mensaje "Bienvenido/a [nombre] ([email])"
        """,
        )
    }

    // Parte F: Combinación de Scope Functions

    fun procesarUsuarioComplejo(datosBase: Map<String, String>): Usuario? {
        TODO(
            """
            Implementar combinando scope functions:
            1. Verificar que existan 'nombre' y 'email' (si no, retornar null)
            2. Crear usuario con 'run'
            3. Configurar propiedades con 'apply'
            4. Si departamento es "IT", usar 'also' para configuración especial (tema oscuro, rol IT_USER)
            5. Retornar usuario configurado
        """,
        )
    }

    fun procesarLoteUsuarios(usuarios: List<Usuario>): List<Usuario> {
        TODO(
            """
            Implementar pipeline con scope functions:
            1. Activar todos los usuarios (apply)
            2. Asignar rol USER si no tienen roles (also)
            3. Configurar notificaciones = true (apply)
            4. Si nombre es "Admin", agregar rol ADMIN y nivelPrivacidad = 3 (run)
        """,
        )
    }

    fun parsearYCrearUsuario(datosRaw: String): Usuario? {
        TODO(
            """
            Implementar parsing completo:
            1. Parsear formato "clave:valor|clave:valor|..."
            2. Crear usuario con los datos parseados
            3. Usar scope functions apropiadas para cada transformación
            4. Retornar null si el formato es inválido
        """,
        )
    }
}

