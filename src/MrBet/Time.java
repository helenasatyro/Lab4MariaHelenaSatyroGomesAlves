package MrBet;

import java.util.HashSet;
import java.util.Objects;

/**
 * Classe que modela um time.
 * O time deve ter um código identificador único no formato 000_AA, contendo um número de identificação e a sigla em duas letras do seu estado.
 * Deve também ter um nome, um mascote, e contém uma coleção de Campeonatos únicos.
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Time {
    private final String id;
    private final String nome;
    private final String mascote;
    private final HashSet<Campeonato> campeonatos;

    /**
     * Construtor de time, recee seu código identificador, nome, e mascote, todos não nulos e não vazios.
     * @param id do time, no formato 000_AA, em que AA é a sigla de seu estado
     * @param nome do time
     * @param mascote do time
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
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

    /**
     * Adiciona um campeonato à coleção de campeonatos
     * @param camp objeto Campeonato que será adicionado apenas uma vez à coleção
     */
    public void addCampeonato(Campeonato camp) {
        campeonatos.add(camp);
    }

    /**
     * Formata a string qu erepresenta o time
     * @return string no formato [000_AA] Nome do Time / Mascote
     */
    public String toString() {
        return "[" + id + "] " + nome + " / " + mascote;
    }

    /**
     * Sobrescreve o método equals de objectr. Dois times são iguais se tem códigos identificadores iguais.
     * @param o objeto sendo comparado
     * @return true se objetos forem iguais, false se não forem
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return id.equals(time.id);
    }

    /**
     * Gera um hashcode a partir do código ID
     * @return hashcode do time
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Formata string exibindo os campeonatos de um time.
     * @return string no formato """
     *          Campeonatos do Nome do Time:
     *          * campeonato 0/0"""
     */
    public String exibeCampeonatos() {
        String retorno = "Campeonatos do " + nome + ":";
        for (Campeonato camp: campeonatos) {
            retorno += "\n* " + camp;
        }
        return retorno;
    }
}
