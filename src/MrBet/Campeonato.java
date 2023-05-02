package MrBet;

import java.util.Objects;

public class Campeonato {
    private final Time[] times;
    private final String nome;
    private int proxIndice;

    public Campeonato(String nome, int maxTimes) {
        this.times = new Time[maxTimes];
        this.nome = nome;
        this.proxIndice = 0;
    }

    public void addTime(Time time) {
        if (cheio()) throw new IllegalStateException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
        times[proxIndice] = time;
        proxIndice++;
        time.addCampeonato(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato that = (Campeonato) o;
        return nome.equalsIgnoreCase(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    public boolean cheio() {
        return times[times.length -1] != null;
    }

    public boolean temTime(Time time) {
        for (int i = 0; i < times.length; i++) {
            if (times[i] != null && times[i] == time ) return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " - " + proxIndice + "/" + times.length;
    }
    public int max() {
        return times.length;
    }

    public int getQuantTimes() {
        return proxIndice;
    }
}
