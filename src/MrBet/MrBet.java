package MrBet;

import java.util.HashMap;

public class MrBet {
    private final HashMap<String, Time> times;
    private final HashMap<String, Campeonato> campeonatos;

    public MrBet() {
        this.times = new HashMap<>();
        this.campeonatos = new HashMap<>();
    }

    public boolean incluiTime(String codigo, String nome, String mascote) {
        if (times.containsKey(codigo)) return false;

        Time time = new Time(codigo, nome, mascote);
        times.put(time.getId(), time);
        return true;
    }

    public Time getTime(String codigo) {
        if (times.containsKey(codigo)) {
            return times.get(codigo);
        }
        return null;
    }

    public boolean addCampeonato(String nome, int participantes) {
        Campeonato campeonato = new Campeonato(nome, participantes);
        if (campeonatos.containsKey(nome.toLowerCase())) {
            return false;
        }
        campeonatos.put(nome.toLowerCase(), campeonato);
        return true;
    }
}
