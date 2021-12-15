package conta.sistema.dominio.modelo;

// Resposavel por centralizar todas as mensagens de erro de negocio do sistema.
public class Erro {

    // erros genéricos
    public static void obrigatorio(String nome) {
        throw new NegocioException(nome + " é obrigatório.");
    }
    public static void inexistente(String nome) {
        throw new NegocioException(nome + " é inexistente.");
    }

    // erros especificos
    public static void saldoInsuficiente(String nome) {
        throw new NegocioException("Saldo insuficiente.");
    }
    public static void mesmaConta(String nome) {
        throw new NegocioException("Conta débito e crédito devem ser diferentes.");
    }

}
