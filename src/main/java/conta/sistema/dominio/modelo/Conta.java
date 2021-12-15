package conta.sistema.dominio.modelo;

import java.math.BigDecimal;

public class Conta {

    private Integer numero;
    private BigDecimal saldo;
    private String correntista;

    public Conta() {
        numero = 0;
        saldo = BigDecimal.ZERO;
        correntista = "não informado";
    }

    public Conta(Integer numero, BigDecimal saldo, String correntista) {
        this.numero = numero;
        this.saldo = saldo;
        this.correntista = correntista;
    }

    public void creditar(BigDecimal credito) throws NegocioException {

    }

    public void debitar(BigDecimal debito) throws NegocioException {

    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", saldo=" + saldo +
                ", correntista='" + correntista + '\'' +
                '}';
    }
}
