package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de débito de conta")
public class TesteDebitoConta {

    BigDecimal cem = new BigDecimal(100L);
    Conta contaValida;

    @BeforeEach
    void preparar() {
        contaValida = new Conta(1, cem, "Tales");
    }

    @Test
    @DisplayName("Validar valor débito nulo como obrigatório")
    void teste01() {
        try {
            contaValida.debitar(null);
            fail("Valor de débito deveria ser obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor de débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de débito negativo como obrigatório")
    void teste02() {
        try {
            contaValida.debitar(new BigDecimal(-1));
            fail("Valor de débito deveria ser obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor de débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de débito zero como obrigatório")
    void teste03() {
        try {
            contaValida.debitar(BigDecimal.ZERO);
            fail("Valor de débito deveria ser obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor de débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de débito acima do saldo")
    void teste04() {
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            fail("Valor de débito acima do saldo");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Saldo insuficiente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de débito igual ao saldo")
    void teste05() {
        try {
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(), BigDecimal.ZERO, "Saldo deve zerar");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Validar valor de débito abaixo do saldo")
    void teste06() {
        try {
            contaValida.debitar(BigDecimal.TEN);
            var saldoFinal = cem.subtract(BigDecimal.TEN);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve zerar");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }

}
