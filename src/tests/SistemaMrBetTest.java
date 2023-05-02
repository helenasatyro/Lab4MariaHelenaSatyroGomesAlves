package tests;

import MrBet.Campeonato;
import MrBet.SistemaMrBet;
import MrBet.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaMrBetTest {
    private SistemaMrBet mrBetBase;

    @BeforeEach
    void setUp() {
        mrBetBase = new SistemaMrBet();
        mrBetBase.incluiTime("250_PB", "Nacional de Patos", "Canário");
        mrBetBase.incluiTime("252_PB", "Sport Lagoa Seca", "Carneiro");
        mrBetBase.incluiTime("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
        mrBetBase.incluiTime("105_PB", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");


    }

    @Test
    void cadastraCampSemRestricao() {
        assertEquals("CAMPEONATO ADICIONADO!", mrBetBase.addCampeonato("Brasileirão série A 2023", 20));
    }

    @Test
    void cadastraCampRepetido() {
        assertEquals("CAMPEONATO ADICIONADO!", mrBetBase.addCampeonato("Brasileirão série A 2023", 20));
        assertEquals("CAMPEONATO JÁ EXISTE!", mrBetBase.addCampeonato("Brasileirão série A 2023", 25));
    }

    @Test
    void cadastraTimeCamp() {
        assertEquals("CAMPEONATO ADICIONADO!", mrBetBase.addCampeonato("Brasileirão série A 2023", 20));
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBetBase.addAoCampeonato("250_PB", "Brasileirão série A 2023") );
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023") );
    }

    @Test
    void testaBuscarCampeonato() {
        assertEquals("CAMPEONATO ADICIONADO!", mrBetBase.addCampeonato("Brasileirão série A 2023", 20));
        assertEquals("CAMPEONATO JÁ EXISTE!", mrBetBase.addCampeonato("brasileirão série a 2023", 20));
        assertEquals("CAMPEONATO ADICIONADO!", mrBetBase.addCampeonato("Brasileirão série B 2023", 20));

        assertNotNull(mrBetBase.buscaCampeonato("brasileirão série a 2023"));
        assertNotNull(mrBetBase.buscaCampeonato("brasileirão série B 2023"));
        assertNotNull(mrBetBase.buscaCampeonato("Brasileirão série B 2023"));
        assertNull(mrBetBase.buscaCampeonato("colombiano série B 2023"));
    }


    @Test
    void cadastraTimeCampJaIncluido() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 20);
        mrBetBase.addAoCampeonato("250_PB", "Brasileirão série A 2023");
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");

        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023") );
        assertEquals(2,mrBetBase.buscaCampeonato("Brasileirão série A 2023").getQuantTimes());
        assertEquals(2,mrBetBase.buscaCampeonato("Brasileirão série A 2023").getQuantTimes());
    }

    @Test
    void cadastraTimeNaoExisteCamp() {
        assertEquals("CAMPEONATO ADICIONADO!", mrBetBase.addCampeonato("Brasileirão série A 2023", 20));
        assertEquals("O TIME NÃO EXISTE!", mrBetBase.addAoCampeonato("005_PB", "Brasileirão série A 2023") );
    }
    @Test
    void cadastraTimeCampNaoExiste() {
        assertEquals("CAMPEONATO ADICIONADO!", mrBetBase.addCampeonato("Brasileirão série A 2023", 20));
        assertEquals("O CAMPEONATO NÃO EXISTE!", mrBetBase.addAoCampeonato("252_PB", "Brasileirão série D 2023") );
    }

    @Test
    void cadastraTimeCampCheio() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 1);
        assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023") );
        assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", mrBetBase.addAoCampeonato("250_PB", "Brasileirão série A 2023") );

    }

    @Test
    void verificaTimeNoCampeonato() {
        mrBetBase.addCampeonato( "Copa do Nordeste 2023", 20);
        mrBetBase.addAoCampeonato("250_PB", "Copa do Nordeste 2023");
        assertTrue(mrBetBase.timeNoCampeonato("250_PB", "Copa do Nordeste 2023"));
        assertFalse(mrBetBase.timeNoCampeonato("252_PB", "Copa do Nordeste 2023"));
    }
    @Test
    void verificaTimeNoCampeonatoNaoExiste() {
        try {
            mrBetBase.timeNoCampeonato("252_PB", "Brasileirão série D 2023");
            fail("Deve lançar exceção.");
        } catch (Exception e) {
            assertEquals("O CAMPEONATO NÃO EXISTE!", e.getMessage());
        }
    }
    @Test
    void verificaTimeNaoExisteNoCampeonato() {
        mrBetBase.addCampeonato( "Copa do Nordeste 2023", 20);
        try {
            mrBetBase.timeNoCampeonato("005_PB", "Copa do Nordeste 2023");
            fail("Deve lançar exceção.");
        } catch (Exception e) {
            assertEquals("O TIME NÃO EXISTE!", e.getMessage());
        }
    }
}