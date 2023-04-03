// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario

data class ConteudoEducacional(
    var nome: String,
    val duracao: Int = 60,
    var descricao: String = "",
    var categoria: String = "",
    var nivel: Nivel = Nivel.BASICO
)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    var descricao: String = ""
    var dificuldade: Nivel = Nivel.BASICO

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }

    fun removerMatricula(usuario: Usuario) {
        inscritos.remove(usuario)
    }

    fun calculaDuracaoTotal(): Int {
        return conteudos.sumOf { it.duracao }
    }

    fun calculaDificuldadeMedia(): Nivel {
        val dificuldades = conteudos.map { it.nivel }
        val total = dificuldades.size
        val dificuldadeMedia = dificuldades.sumBy { it.ordinal } / total
        return Nivel.values()[dificuldadeMedia]
    }
}

fun main() {
    val usuario1 = Usuario()
    val usuario2 = Usuario()

    val conteudo1 = ConteudoEducacional(
        "Kotlin para iniciantes",
        120,
        "Curso introdutório sobre a linguagem Kotlin.",
        "Programação",
        Nivel.BASICO
    )
    val conteudo2 = ConteudoEducacional(
        "Kotlin avançado",
        180,
        "Curso avançado sobre a linguagem Kotlin.",
        "Programação",
        Nivel.DIFICIL
    )
    val conteudo3 = ConteudoEducacional(
        "Matemática financeira",
        90,
        "Curso sobre cálculos financeiros.",
        "Finanças",
        Nivel.INTERMEDIARIO
    )

    val formacao1 = Formacao("Desenvolvedor Kotlin", listOf(conteudo1, conteudo2))
    formacao1.descricao = "Formação para desenvolvedores Kotlin"
    formacao1.dificuldade = Nivel.INTERMEDIARIO

    val formacao2 = Formacao("Finanças pessoais", listOf(conteudo3))
    formacao2.descricao = "Formação sobre finanças pessoais"
    formacao2.dificuldade = Nivel.INTERMEDIARIO

    formacao1.matricular(usuario1)
    formacao1.matricular(usuario2)

    println("Duração total da formação '${formacao1.nome}': ${formacao1.calculaDuracaoTotal()} minutos")
    println("Dificuldade média da formação '${formacao1.nome}': ${formacao1.calculaDificuldadeMedia()}")
    println("Inscritos na formação '${formacao1.nome}': ${formacao1.inscritos}")

    formacao1.removerMatricula(usuario1)

    println("Inscritos na formação '${formacao1.nome}' após a remoção do usuário 1: ${formacao1.inscritos}")
}
