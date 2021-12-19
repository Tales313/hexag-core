package teste.unidade.servico;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.dominio.servico.Transferencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de transferência")
public class TesteTransferencia {

    BigDecimal cem = new BigDecimal(100);
    BigDecimal vinte = new BigDecimal(20);
    Transferencia transferencia;
    Conta contaDebito;
    Conta contaCredito;

    @BeforeEach
    void preparar() {
        contaDebito = new Conta(1, cem, "Tales");
        contaCredito = new Conta(2, cem, "Bob");
        transferencia = new Transferencia();
    }

    @Test
    @DisplayName("Validar valor nulo como obrigatório")
    void teste01() {
        try {
            transferencia.transferencia(null, contaDebito, contaCredito);
            fail("Valor de transferência obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor da transferência é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar conta débito como obrigatório")
    void teste02() {
        try {
            transferencia.transferencia(vinte, null, contaCredito);
            fail("Conta de débito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar conta crédito como obrigatório")
    void teste03() {
        try {
            transferencia.transferencia(vinte, contaDebito, null);
            fail("Conta de crédito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Transferir 20 reais")
    void teste04() {
        var saldoFinalContaDebito = cem.subtract(vinte);
        var saldoFinalContaCredito = cem.add(vinte);
        try {
            transferencia.transferencia(vinte, contaDebito, contaCredito);
            assertEquals(contaDebito.getSaldo(), saldoFinalContaDebito, "Saldo da conta débito deve bater.");
            assertEquals(contaCredito.getSaldo(), saldoFinalContaCredito, "Saldo da conta crédito deve bater.");
        } catch (NegocioException e) {
            fail("Deve transferir com sucesso.");
        }
    }

}
