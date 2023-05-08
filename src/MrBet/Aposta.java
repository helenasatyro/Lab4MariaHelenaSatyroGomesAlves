package MrBet;

import java.text.DecimalFormat;

public class Aposta {
    private Time time;
    private Campeonato campeonato;
    private double valor;
    private int colocacao;

    public Aposta(Time time, Campeonato campeonato, double valor, int colocacao) {
        if (time == null) throw new NullPointerException("O TIME NÃO EXISTE!");
        this.time = time;
        if (campeonato == null) throw new NullPointerException("O CAMPEONATO NÃO EXISTE!");
        this.campeonato = campeonato;
        if (valor <= 0) throw new IllegalArgumentException("VALOR DA APOSTA DEVE SER MAIOR QUE ZERO");
        this.valor = valor;
        if (colocacao <= 0 || !(colocacao <= campeonato.max())) throw new IllegalArgumentException("APOSTA NÃO REGISTRADA!");
        this.colocacao = colocacao;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return time + "\n" + campeonato.getNome() + "\n" + colocacao + "/" + campeonato.max() + "\nR$ " + df.format(valor);
    }
}
