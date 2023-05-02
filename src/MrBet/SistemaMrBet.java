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

    public String incluiTime(String codigo, String nome, String mascote) {
        if (times.containsKey(codigo)) return "TIME JÁ EXISTE!";

        Time time = new Time(codigo, nome, mascote);
        times.put(time.getId(), time);
        return "INCLUSÃO REALIZADA!";
    }

    public Time getTime(String codigo) {
        if (times.containsKey(codigo)) {
            return times.get(codigo);
        }
        return null;
    }

    public String addCampeonato(String nome, int participantes) {
        Campeonato campeonato = new Campeonato(nome, participantes);
        if(campeonatos.add(campeonato)) {
            return "CAMPEONATO ADICIONADO!";
        }
        return "CAMPEONATO JÁ EXISTE!";
    }

    public String addAoCampeonato(String codigo, String campeonato) {
        if (!times.containsKey(codigo)) return "O TIME NÃO EXISTE!";
        Campeonato camp = buscaCampeonato(campeonato);
        if (camp == null) return "O CAMPEONATO NÃO EXISTE!";
        try {
            if (!timeNoCampeonato(codigo, campeonato))
            camp.addTime(times.get(codigo));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "TIME INCLUÍDO NO CAMPEONATO!";
    }

    public boolean timeNoCampeonato(String codigo, String campeonato) {
        if (!times.containsKey(codigo)) throw new NoSuchElementException("O TIME NÃO EXISTE!");
        Campeonato camp = buscaCampeonato(campeonato);
        if (camp == null) throw new NoSuchElementException("O CAMPEONATO NÃO EXISTE!");
        if (camp.temTime(times.get(codigo))) return true;
        return false;

    }

    public Campeonato buscaCampeonato(String nome) {
        Campeonato c = new Campeonato(nome, 000);
        for (Campeonato camp: campeonatos) {
            if (camp.equals(c)) return camp;
        }
        return null;
    }


    public String verCampeonatos(String codigoTime) {
        Time time = times.get(codigoTime);
        if (time == null) return "TIME NÃO EXISTE!";
        return time.exibeCampeonatos();
    }

    public String addAposta(String codigoTime, String nomeCamp, double valor, int colocacao) {
        Time time = getTime(codigoTime);
        Campeonato camp = buscaCampeonato(nomeCamp);
        Aposta aposta;
        try {
            aposta = new Aposta(time, camp, valor, colocacao);
        } catch (Exception e) {
            return e.getMessage();
        }
        apostas.add(aposta);
        return "APOSTA REGISTRADA!";
    }

    public String verApostas() {
        int i = 0;
        String retorno = "";
        for (Aposta a: apostas) {
            retorno += i + ". " + a;
        }
        return retorno;
    }

aaaaaaaaaaaaa testarb outras coisaaaas
}
