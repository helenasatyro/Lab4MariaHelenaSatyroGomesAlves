package tests;

import MrBet.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
     private Time timeBase;
     private Campeonato campBase;

    @BeforeEach
    void setUp() {
        timeBase = new Time("220_PR", "Time Time", "Besouro");
        campBase = new Campeonato("Camp paraibano", 2);
    }

    @Test
    void testaConstrutorParamNulos() {
        try {
            Time time = new Time(null, null, null);
            fail("Deve lançar exceção");
        } catch (NullPointerException e) {
            assertEquals("Parâmetros não podem ser nulos", e.getMessage());
        }
    }

    @Test
    void testaConstrutorParamVazios() {
        try {
            Time time = new Time("", "", "");
            fail("Deve lançar exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Parâmetros não podem estar vazios", e.getMessage());
        }
    }

    @Test
    void testaEquals() {
        Time time1 = new Time("220_PR", "Time", "Mascote");
        Time time2 = new Time("330_PB", "Time", "Mascote");
        assertEquals(timeBase, time1);
        assertNotEquals(timeBase, time2);
        assertNotEquals(time1, time2);
    }


    @Test
    void testaExibeCampeonatos() {
        timeBase.addCampeonato(campBase);
        // quantidade de participantes é alterada em Campeonato
        assertEquals("Campeonatos do Time Time:\n* Camp paraibano - 0/2",timeBase.exibeCampeonatos());
        timeBase.addCampeonato(campBase);
        assertEquals("Campeonatos do Time Time:\n* Camp paraibano - 0/2",timeBase.exibeCampeonatos(), "Não deve adicionar novamente");
        Campeonato camp = new Campeonato("Campeonato", 2);
        timeBase.addCampeonato(camp);
        assertEquals("Campeonatos do Time Time:\n* Camp paraibano - 0/2\n* Campeonato - 0/2",timeBase.exibeCampeonatos(), "Deve adicionar normalmente.");
    }
}