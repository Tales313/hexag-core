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
        }
    }

}
