package teste;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        "teste.unidade.dominio.modelo",
        "teste.unidade.dominio.servico",
        "teste.casouso"
})
public class SuiteCore {
}
