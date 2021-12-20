package teste.casouso;

import conta.sistema.casouso.porta.PortaTransferencia;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Caso de Uso - Serviço de Transferência")
@ContextConfiguration(classes = Build1.class)
@ExtendWith(SpringExtension.class)
public class TesteAdaptadorTransferencia {

    Integer contaCredito = 10;
    Integer contaDebito = 20;
    Integer contaInexistente = 30;
    BigDecimal cem = new BigDecimal(100);
    BigDecimal cinquenta = new BigDecimal(50);

    @Inject
    PortaTransferencia porta;

    @DisplayName("Pesquisa de conta com número nulo")
    @Test
    void teste01() {
        try {
            var conta = porta.getConta(null);
            assertNull(conta, "A conta deve ser nula");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula");
        }
    }

    @DisplayName("Pesquisa de conta inexistente")
    @Test
    void teste02() {
        try {
            var conta = porta.getConta(contaInexistente);
            assertNull(conta, "A conta deve ser nula");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula");
        }
    }

    @DisplayName("Pesquisa de conta existente")
    @Test
    void teste03() {
        try {
            var conta = porta.getConta(contaCredito);
            assertNotNull(conta, "A conta não deve ser nula");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta existente");
        }
    }

    @DisplayName("Validar conta débito como obrigatório")
    @Test
    void teste04() {
        try {
            porta.transferir(null, contaCredito, cinquenta);
            fail("Deve validar conta débito como obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Número conta débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Validar conta crédito como obrigatório")
    @Test
    void teste05() {
        try {
            porta.transferir(contaDebito, null, cinquenta);
            fail("Deve validar conta crédito como obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Número conta crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Validar valor nulo como obrigatório")
    @Test
    void teste06() {
        try {
            porta.transferir(contaDebito, contaCredito, null);
            fail("Deve validar valor nulo como obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Validar conta débito inexistente")
    @Test
    void teste07() {
        try {
            porta.transferir(contaInexistente, contaCredito, cinquenta);
            fail("Conta débito deve ser inexistente");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Validar conta crédito inexistente")
    @Test
    void teste08() {
        try {
            porta.transferir(contaDebito, contaInexistente, cinquenta);
            fail("Conta crédito deve ser inexistente");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Validar mesma conta")
    @Test
    void teste09() {
        try {
            porta.transferir(contaDebito, contaDebito, cinquenta);
            fail("Não pode transferir para a mesma conta");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito e crédito devem ser diferentes.");
            System.out.println(e.getMessage());
        }
    }

    @DisplayName("Validar transferencia de 50 reais")
    @Test
    void teste10() {
        try {
            porta.transferir(contaDebito, contaCredito, cinquenta);
            var debito = porta.getConta(contaDebito);
            var credito = porta.getConta(contaCredito);
            assertEquals(cem.subtract(cinquenta), debito.getSaldo(), "Saldo de débito deve bater");
            assertEquals(cem.add(cinquenta), credito.getSaldo(), "Saldo de crédito deve bater");
        } catch (NegocioException e) {
            fail("Deve fazer a transferencia com sucesso - " + e.getMessage());
        }
    }

}
