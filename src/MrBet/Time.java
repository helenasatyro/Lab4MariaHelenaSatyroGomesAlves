package MrBet;

import java.util.Objects;

public class Time {
    private final String id;
    private final String nome;
    private final String mascote;

    public Time(String id, String nome, String mascote) {
        this.id = id;
        this.nome = nome;
        this.mascote = mascote;
    }

    public String getId() {
        return id;
    }
    public String toString() {
        return "[" + id + "] " + nome + " / " + mascote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return id.equals(time.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
