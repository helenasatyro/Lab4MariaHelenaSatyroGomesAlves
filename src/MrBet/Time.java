package MrBet;

import java.util.HashSet;
import java.util.Objects;

public class Time {
    private final String id;
    private final String nome;
    private final String mascote;
    private final HashSet<Campeonato> campeonatos;

    public Time(String id, String nome, String mascote) throws IllegalArgumentException, NullPointerException {
        if (id == null || nome == null || mascote == null) {
            throw new NullPointerException("Parâmetros não podem ser nulos");
        }
        if (id.isBlank() || nome.isBlank() || mascote.isBlank()) {
            throw new IllegalArgumentException("Parâmetros não podem estar vazios");
        }
        this.id = id;
        this.nome = nome;
        this.mascote = mascote;
        this.campeonatos = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void addCampeonato(Campeonato camp) {
        campeonatos.add(camp);
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

    public String exibeCampeonatos() {
        String retorno = "Campeonatos do " + nome + ":";
        for (Campeonato camp: campeonatos) {
            retorno += "\n* " + camp;
        }
        return retorno;
    }
}
