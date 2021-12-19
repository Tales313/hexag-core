package conta.sistema.casouso.imp;

import conta.sistema.casouso.porta.PortaTransferencia;
import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.servico.Transferencia;
import conta.sistema.porta.ContaRepositorio;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static conta.sistema.dominio.modelo.Erro.obrigatorio;
import static conta.sistema.dominio.modelo.Erro.inexistente;
import static conta.sistema.dominio.modelo.Erro.mesmaConta;

@Named
public class PortaTransferenciaImp implements PortaTransferencia {

    private ContaRepositorio contaRepositorio;
    private Transferencia transferencia;

    // TODO ver se esse construtor vazio deve ficar aqui mesmo
    // pois o compilador estava reclamando do @Named com a classe sem construtor vazio
    public PortaTransferenciaImp() {}

    public PortaTransferenciaImp(ContaRepositorio contaRepositorio, Transferencia transferencia) {
        this.contaRepositorio = contaRepositorio;
        this.transferencia = transferencia;
    }

    @Override
    public Conta getConta(Integer numero) {
        return contaRepositorio.get(numero);
    }

    @Override
    @Transactional
    public void transferir(Integer numeroContaDebito, Integer numeroContaCredito, BigDecimal valor) {

        if(isNull(numeroContaDebito))
            obrigatorio("Número conta débito");

        if(isNull(numeroContaCredito))
            obrigatorio("Número conta crédito");

        if(isNull(valor))
            obrigatorio("Valor");

        var contaDebito = getConta(numeroContaDebito);
        var contaCredito = getConta(numeroContaCredito);

        if(isNull(contaDebito))
            inexistente("Conta débito");

        if(isNull(contaCredito))
            inexistente("Conta crédito");

        if(contaCredito.getNumero().equals(contaDebito.getNumero()))
            mesmaConta();

        transferencia.transferencia(valor, contaDebito, contaCredito);
        contaRepositorio.alterar(contaDebito);
        contaRepositorio.alterar(contaCredito);
    }

}
