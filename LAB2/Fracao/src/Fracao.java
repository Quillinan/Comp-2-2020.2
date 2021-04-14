/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */
public class Fracao
{

    private final int numerador;
    private final int denominador;
    private double valorNumerico;
    private boolean sinal;

    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */

    public Fracao(int numerador, int denominador)
    {
        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!!");
        }
        if(numerador <= 0 && denominador < 0) {
            this.sinal = true;
        }
        if(numerador >= 0 && denominador >= 0) {
            this.sinal = true;
        }
        this.numerador = Math.abs(numerador);
        this.denominador = Math.abs(denominador);
        this.valorNumerico = (double) numerador / denominador;
    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal()
    {
        return sinal;
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador()
    {
        return numerador;
    }

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o numerador
     */
    public int getDenominador()
    {
        return denominador;
    }

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar 0.33333333
     */
    public double getValorNumerico()
    {
        return valorNumerico;
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */

    public Fracao getFracaoGeratriz()
    {
        int mdc = AritmeticaUtils.mdc(this.numerador, this.denominador);
        if(mdc == 1) return this;
        return new Fracao(this.numerador/mdc, this.denominador/mdc);
    }

    @Override
    public String toString()
    {
        int positivo = this.getSinal()? 1: -1;
        if (this.getNumerador() == 0){
            return String.format("%d", 0);
        }
        if (this.getDenominador() == 1){
            return String.format("%d", positivo * this.getNumerador());
        }
        return String.format("%d/%d", positivo * this.getNumerador(), this.getDenominador());
    }

    @Override
    public boolean equals(Object object)
    {
        if(this == object) return true;
        if(!(object instanceof Fracao)) return false;
        Fracao fracao = (Fracao) object;
            return fracao.getFracaoGeratriz().getNumerador() == getFracaoGeratriz().getNumerador()
                    && fracao.getFracaoGeratriz().getDenominador() == getFracaoGeratriz().getDenominador()
                    && fracao.getFracaoGeratriz().getSinal() == getFracaoGeratriz().getSinal();

    }
}
