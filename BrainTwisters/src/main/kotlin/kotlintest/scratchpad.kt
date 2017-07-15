//@file:JvmName("KotlinApp")
package kotlintest

fun main(args: Array<String>) {

    println("Hello World!")

    val url: String = url(domain = "my.domain.de")
    println("url = $url")
}

fun url(protocol: String = "https", domain: String, port: Int = 443): String {
    return "$protocol://$domain:$port"
}
