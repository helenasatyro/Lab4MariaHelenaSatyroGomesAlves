package MrBet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class SistemaMrBet {
    private final HashMap<String, Time> times;
    private final HashSet<Campeonato> campeonatos;
    private final ArrayList<Aposta> apostas;

    public SistemaMrBet() {
        this.times = new HashMap<>();
        this.campeonatos = new HashSet<>();
        this.apostas = new ArrayList<>();
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
        return campeonatos.add(campeonato);
    }

    public void addAoCampeonato(String codigo, String campeonato) {
        if (!times.containsKey(codigo)) throw new NoSuchElementException("TIME NÃO EXISTE!");
        Campeonato camp = buscaCampeonato(campeonato);
        if (camp == null) throw new NoSuchElementException("CAMPEONATO NÃO EXISTE!");
        camp.addTime(times.get(codigo));
    }

    public boolean timeNoCampeonato(String codigo, String campeonato) {
        if (!times.containsKey(codigo)) throw new NoSuchElementException("TIME NÃO EXISTE!");
        Campeonato camp = buscaCampeonato(campeonato);
        if (camp == null) throw new NoSuchElementException("CAMPEONATO NÃO EXISTE!");
        return camp.temTime(times.get(codigo));

    }

    private Campeonato buscaCampeonato(String nome) {
        for (Campeonato camp: campeonatos) {
            if (camp.getNome().equalsIgnoreCase(nome)) return camp;
        }
        return null;
    }


    public String verCampeonatos(String codigoTime) {
        Time time = times.get(codigoTime);
        if (time == null) throw new NoSuchElementException("TIME NÃO EXISTE!");
        return time.exibeCampeonatos();
    }

    public void addAposta(String codigoTime, String nomeCamp, double valor, int colocacao) {
        Time time = times.get(codigoTime);
        Campeonato camp = buscaCampeonato(nomeCamp);
        Aposta aposta = new Aposta(time, camp, valor, colocacao);
        apostas.add(aposta);
    }

    public String verApostas() {
        int i = 0;
        String retorno = "";
        for (Aposta a: apostas) {
            retorno += i + ". " + a;
        }
        return retorno;
    }
}
