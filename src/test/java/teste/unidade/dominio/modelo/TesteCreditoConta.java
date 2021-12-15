package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de crédito de conta")
public class TesteCreditoConta {

    BigDecimal cem = new BigDecimal(100L);
    Conta contaValida;

    @BeforeEach
    void preparar() {
        contaValida = new Conta(1, cem, "Tales");
    }

    @Test
    @DisplayName("Validar valor de crédito nulo como obrigatório")
    void teste01() {
        try {
            contaValida.creditar(null);
            fail("Valor de crédito deveria ser obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor de crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de crédito negativo como obrigatório")
    void teste02() {
        try {
            contaValida.creditar(new BigDecimal(-1));
            fail("Valor de crédito deveria ser obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor de crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de crédito zero como obrigatório")
    void teste03() {
        try {
            contaValida.creditar(new BigDecimal(0));
            fail("Valor de crédito deveria ser obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor de crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de crédito positivo")
    void teste04() {
        try {
            contaValida.creditar(BigDecimal.ONE);
            var saldoFinal = cem.add(BigDecimal.ONE);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve bater");
        } catch (NegocioException e) {
            fail("Deve creditar com sucesso - " + e.getMessage());
        }
    }

}
