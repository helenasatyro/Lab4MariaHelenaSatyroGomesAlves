package MrBet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Classe que modela um formato de apostas em campeonatos.
 * Uma aposta tem um time, uma colocação, um valor e um campeonato.
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Aposta {
    private final Time time;
    private final Campeonato campeonato;
    private final double valor;
    private final int colocacao;

    /**
     * Construtor de Aposta, parâmetros não podem ser nulos ou vazios
     * @param time em que se quer apostar
     * @param campeonato em que se quer apostar
     * @param valor da aposta, deve ser maior que zero
     * @param colocacao em que se quer apostar, deve ser maior que zero e emenor que o máximo de participantes do campeonato
     * @throws NullPointerException se o time ou campeonato não existirem
     * @throws IllegalArgumentException se o valor da aposta for menor ou igual a zero, ou a colocação for inválida (menor que 1 ou maior que o máximo de participantes do campeonato)
     */
    public Aposta(Time time, Campeonato campeonato, double valor, int colocacao) throws NullPointerException, IllegalArgumentException {
        if (time == null) throw new NullPointerException("O TIME NÃO EXISTE!");
        this.time = time;
        if (campeonato == null) throw new NullPointerException("O CAMPEONATO NÃO EXISTE!");
        this.campeonato = campeonato;
        if (valor <= 0) throw new IllegalArgumentException("VALOR DA APOSTA DEVE SER MAIOR QUE ZERO");
        this.valor = valor;
        if (colocacao <= 0 || !(colocacao <= campeonato.max())) throw new IllegalArgumentException("APOSTA NÃO REGISTRADA!");
        this.colocacao = colocacao;
    }

    /**
     * Formata uma string exibindo a aposta.
     * @return String no formato """
     *          [cod_time] Nome do Time / mascote
     *          nome do campeonato
     *          posicaoApostada/maxPosicoes
     *          R$ val.or
     *          """
     */
    @Override
    public String toString() {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
        return time + "\n" + campeonato.getNome() + "\n" + colocacao + "/" + campeonato.max() + "\nR$ " + df.format(valor);
    }
}
