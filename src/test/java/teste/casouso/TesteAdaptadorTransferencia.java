package teste.casouso;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Caso de Uso - Serviço de Transferência")
@ContextConfiguration(classes = Build1.class)
@ExtendWith(SpringExtension.class)
public class TesteAdaptadorTransferencia {

    @DisplayName("Pesquisa de conta com número nulo")
    @Test
    void teste01() {
        System.out.println("TUDO CERTO!");
    }

}
