import java.util.Objects;

public class Campeonato {
    private final Time[] times;
    private final String nome;

    public Campeonato(String nome, int maxTimes) {
        this.times = new Time[maxTimes];
        this.nome = nome;
    }

    public boolean addTime(Time time) {
        return false;
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
}
