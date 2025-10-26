package rmb.application.auth

interface TokenGeneratorInterface {
    fun generate(userId: Int): String
}
