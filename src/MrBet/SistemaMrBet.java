package MrBet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Classe que modela um sistema de gerenciamento de apostas, times e campeonatos.
 * @author Maria Helena Sátyro Gomes Alves
 */
public class SistemaMrBet {
    private final HashMap<String, Time> times;
    private final HashSet<Campeonato> campeonatos;
    private final ArrayList<Aposta> apostas;

    /**
     * Construtor sem parâmetros, inicializa as coleções de times (HashMap), campeonatos(HashSet) e apostas(ArrayList).
     */
    public SistemaMrBet() {
        this.times = new HashMap<>();
        this.campeonatos = new HashSet<>();
        this.apostas = new ArrayList<>();
    }

    /**
     * Método que cria e adiciona um time ao HashMap de times. Só inclui o time uma vez, identificado pelo código.
     *
     * @param codigo identificador único do time, composto por três dígitos, um underscore e o estado do time (000_AA).
     * @param nome do time
     * @param mascote do time
     * @return String indicando se a operação teve sucesso ou não.
     */
    public String incluiTime(String codigo, String nome, String mascote) {
        if (times.containsKey(codigo)) return "TIME JÁ EXISTE!";

        Time time = new Time(codigo, nome, mascote);
        times.put(codigo, time);
        return "INCLUSÃO REALIZADA!";
    }

    /**
     *  Retorna um time a partir de seu código identificador, ou null se não existir.
     *
     * @param codigo id do time.
     * @return o Time buscado ou null se não existir.
     */
    public Time getTime(String codigo) {
        if (times.containsKey(codigo)) {
            return times.get(codigo);
        }
        return null;
    }

    /**
     * Cria e adiciona um campeonato à coleção de campeonatos. Só adiciona uma vez.
     *
     * @param nome do campeonato
     * @param participantes número máximo
     * @return String informando se a operação teve sucesso.
     */
    public String addCampeonato(String nome, int participantes) {
        Campeonato campeonato = new Campeonato(nome, participantes);
        if(campeonatos.add(campeonato)) {
            return "CAMPEONATO ADICIONADO!";
        }
        return "CAMPEONATO JÁ EXISTE!";
    }

    /**
     * Adiciona um time a um campeonato, referenciados pelo código do time e nome do campeonato (case insensitive).
     *
     * @param codigo de identificação do time
     * @param campeonato nome, case insensitive
     * @return String informando o sucesso ou motivo de falha da operação:
     *          "Time não existe", "Campeonato não existe", "Todos os times do campeonato já foram incluídos".
     */
    public String addAoCampeonato(String codigo, String campeonato) {
        if (!times.containsKey(codigo)) return "O TIME NÃO EXISTE!";
        Campeonato camp = buscaCampeonato(campeonato);
        if (camp == null) return "O CAMPEONATO NÃO EXISTE!";
        try {
            if (!timeNoCampeonato(codigo, campeonato)) {
                camp.addTime(times.get(codigo));
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "TIME INCLUÍDO NO CAMPEONATO!";
    }

    /**
     * Checa se um time está em um campeonato.
     * @param codigo de identificação do time
     * @param campeonato nome, case insensitive
     * @return true se o time estiver no campeonato, falso caso não esteja
     * @throws NoSuchElementException caso o time ou campeonato não existam
     */
    public boolean timeNoCampeonato(String codigo, String campeonato) throws NoSuchElementException {
        if (!times.containsKey(codigo)) throw new NoSuchElementException("O TIME NÃO EXISTE!");
        Campeonato camp = buscaCampeonato(campeonato);
        if (camp == null) throw new NoSuchElementException("O CAMPEONATO NÃO EXISTE!");
        return camp.temTime(times.get(codigo));

    }

    /**
     * Busca e recupera um campeonato a partir de seu nome (case insensitive)
     * @param nome do campeonato
     * @return o campeonato ou null se não existir.
     */
    public Campeonato buscaCampeonato(String nome) {
        Campeonato c = new Campeonato(nome, 10);
        for (Campeonato camp: campeonatos) {
            if (camp.equals(c)) return camp;
        }
        return null;
    }

    /**
     *
     * @param codigoTime código de identificação do time
     * @return String exibindo os campeonatos de cada time no formato:
     * "Campeonatos do NomeDoTime:
     *  * Nome Do Camp - numParticipantesAtual/maxParticipantes"
     *  ou string informando que o time não existe.
     *
     */
    public String verCampeonatos(String codigoTime) {
        Time time = times.get(codigoTime);
        if (time == null) return "TIME NÃO EXISTE!";
        return time.exibeCampeonatos();
    }

    /**
     * Adiciona uma aposta à coleção de apostas e informa se a operação teve sucesso.
     * Uma aposta deve incluir o código do time e nome do campeonato, um valor acima de zero e uma posição válida (menor que o máximo de participantes)
     * @param codigoTime código id do time em que se quer apostar
     * @param nomeCamp em que se quer apostar
     * @param valor maior que zero, em double que será apostado
     * @param colocacao maior que zero, menor que o número máximo de participantes do campeonato
     * @return string informando o sucesso ou motivo de falha da operação
     */
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

    /**
     * Exibe uma string formatada com as apostas do usuário no formato:
     * """                    Apostas:
     *
     *                        1. [Codigo_Time] nome time / mascote
     *                        nome do campeonato
     *                        posicaoApostada/maximoPosicao
     *                        R$ val.or"""
     * @return String formatada com as apostas do usuário.
     */
    public String verApostas() {
        int i = 0;
        String retorno = "Apostas:\n";
        for (Aposta a: apostas) {
            retorno += "\n"+ (i+1) + ". " + a + "\n";
            i++;
        }
        return retorno;
    }

}
