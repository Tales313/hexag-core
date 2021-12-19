package teste.casouso;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        // objetos de sistema
        {"conta.sistema",
        // adaptadores falsos
         "conta.adaptador"
})
public class Build1 {
    // Build 1: Adaptador Teste -> Sistema <- Adaptadores Mock
}
