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

// Como mostrado no curso, essa classe vai dar erro se for executada com versões acima da 2019.1 do Intellij.
// Eu tive que baixar e usar o Intellij 2019.1 para essa classe ser executada sem erro.