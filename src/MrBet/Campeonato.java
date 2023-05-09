package MrBet;

import java.util.Objects;

/**
 * Classe que modela um campeonato. O campeonato deve ter um nome e um número máximo de times, além da sua coleção de times.
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Campeonato {
    private final Time[] times;
    private final String nome;
    private int proxIndice;

    /**
     * Construtor de campeonato. Inicializa a coleção de times e o contador de índices.
     * @param nome do campeonato
     * @param maxTimes número máximo de times aceitos no campeonato.
     */
    public Campeonato(String nome, int maxTimes) {
        this.times = new Time[maxTimes];
        this.nome = nome;
        this.proxIndice = 0;
    }

    /**
     * Adiciona um time ao campeonato, e ao mesmo tempo se adiciona à coleção de campeonatos do time.
     * @param time objeto Time a ser adicionado
     * @throws IllegalArgumentException se o campeonato estiver cheio
     */
    public void addTime(Time time) throws IllegalArgumentException {
        if (cheio()) throw new IllegalStateException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
        times[proxIndice] = time;
        proxIndice++;
        time.addCampeonato(this);
    }

    /**
     * Sobrescreve o método equals de object. Dois campeonatos são iguais se seus nomes são iguais (case insensitive)
     * @param o objeto sendo comparado
     * @return true se objetos forem iguais, false se não forem
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato that = (Campeonato) o;
        return nome.equalsIgnoreCase(that.nome);
    }

    /**
     * Gera um hashcode para o campeonato baseado no seu nome (case insensitive)
     * @return hashcode do campeonato
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    /**
     * Verifica se um determinado time está presente no campeonato
     * @param time objeto Time senod buscado
     * @return true se time estiver no campeonato, false se não estiver
     */
    public boolean temTime(Time time) {
        for (int i = 0; i < times.length; i++) {
            if (times[i] != null && times[i] == time ) return true;
        }
        return false;
    }

    /**
     * Getter do nome do campeonato
     * @return o nome do campeonato
     */
    public String getNome() {
        return nome;
    }

    /**
     * Formata string representando o campeonato
     * @return string no formato "Nome do campeonato - participantesAtuais/maximoParticipantes"
     */
    @Override
    public String toString() {
        return nome + " - " + proxIndice + "/" + times.length;
    }

    /**
     * Informa o número máximo de times permitidos no campeonato.
     * @return o máximo de times permitidos no campeonato (length do array de times)
     */
    public int max() {
        return times.length;
    }

    /**
     * Informa a quantidade atual de times
     * @return int a quantidade de times cadastrados no campeonato
     */
    public int getQuantTimes() {
        return proxIndice;
    }
    private boolean cheio() {
        return times[times.length -1] != null;
    }
}
