package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

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



}
