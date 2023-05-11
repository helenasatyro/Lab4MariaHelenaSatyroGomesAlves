package tests;

import MrBet.SistemaMrBet;
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

    @Test
    void addTimeDuplicado() {
        assertEquals("TIME JÁ EXISTE!", mrBetBase.incluiTime("250_PB", "time", "mascote"));
    }

    @Test
    void recuperaTime() {
        assertEquals("[250_PB] Nacional de Patos / Canário",mrBetBase.getTime("250_PB").toString());
    }
    @Test
    void recuperaTimeInexistente() {
            assertNull(mrBetBase.getTime("2750_PB"));
    }

    @Test
    void testaVerCampeonatosDoTime() {
        mrBetBase.addCampeonato( "Copa do Nordeste 2023", 20);
        mrBetBase.addCampeonato("Brasileirão série A 2023", 1);
        mrBetBase.addAoCampeonato("250_PB", "Copa do Nordeste 2023");
        mrBetBase.addAoCampeonato("250_PB", "Brasileirão série A 2023");
        assertEquals("""
                Campeonatos do Nacional de Patos:
                * Copa do Nordeste 2023 - 1/20
                * Brasileirão série A 2023 - 1/1""",mrBetBase.verCampeonatos("250_PB"));
    }

    @Test
    void testaApostar() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        assertEquals("APOSTA REGISTRADA!", mrBetBase.addAposta("252_PB", "Brasileirão série A 2023", 10, 2));    }
    @Test
    void testaApostarTimeNaoExiste() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        assertEquals("O TIME NÃO EXISTE!", mrBetBase.addAposta("256_PB", "Brasileirão série A 2023", 0, 2));
    }
    @Test
    void testaApostarCampNaoExiste() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        assertEquals("O CAMPEONATO NÃO EXISTE!", mrBetBase.addAposta("252_PB", "Brasileirão 2023", 10, 2));
    }
    @Test
    void testaApostarSemValor() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        assertEquals("VALOR DA APOSTA DEVE SER MAIOR QUE ZERO", mrBetBase.addAposta("252_PB", "Brasileirão série A 2023", 0, 2));
    }
    @Test
    void testaApostarPosicaoExcedente() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 1);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        assertEquals("APOSTA NÃO REGISTRADA!", mrBetBase.addAposta("252_PB","Brasileirão série A 2023", 3.0, 2 ));
    }
    @Test
    void testaApostarPosicaoInferior() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        assertEquals("APOSTA NÃO REGISTRADA!", mrBetBase.addAposta("252_PB","Brasileirão série A 2023", 3.0, 0 ));
    }
    @Test
    void testaApostarPosicaoLimite() {
        mrBetBase.addCampeonato("Brasileirão série A 2023", 1);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        assertEquals("APOSTA REGISTRADA!", mrBetBase.addAposta("252_PB","Brasileirão série A 2023", 3.0, 1 ));
    }

    @Test
    void testaStatusApostas() {
        mrBetBase.addCampeonato("Campeonato Paraibano 2023", 14);
        mrBetBase.addCampeonato("Nordestão 2023", 20);
        mrBetBase.addAposta("250_PB", "Campeonato Paraibano 2023", 50.0, 2 );
        mrBetBase.addAposta("252_PB", "Nordestão 2023", 250.0, 1 );

        assertEquals(
                """
                        Apostas:

                        1. [250_PB] Nacional de Patos / Canário
                        Campeonato Paraibano 2023
                        2/14
                        R$ 50.00

                        2. [252_PB] Sport Lagoa Seca / Carneiro
                        Nordestão 2023
                        1/20
                        R$ 250.00
                        """, mrBetBase.verApostas());
    }

    @Test
    void testaBonusNinguemEmCampNinguemEmAposta() {
        mrBetBase.addCampeonato("Campeonato Paraibano 2023", 14);
        mrBetBase.addCampeonato("Nordestão 2023", 20);
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        assertEquals("""
                Participação mais frequente em campeonatos (0)
                [250_PB] Nacional de Patos / Canário
                [002_RJ] Clube de Regatas do Flamengo / Urubu
                [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião
                [252_PB] Sport Lagoa Seca / Carneiro

                Ainda não participou de campeonato
                [250_PB] Nacional de Patos / Canário
                [002_RJ] Clube de Regatas do Flamengo / Urubu
                [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião
                [252_PB] Sport Lagoa Seca / Carneiro

                Popularidade em apostas""", mrBetBase.historico());
    }
    @Test
    void testaBonusUmEmCampUmEmAposta() {
        mrBetBase.addCampeonato("Campeonato Paraibano 2023", 14);
        mrBetBase.addCampeonato("Nordestão 2023", 20);
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        mrBetBase.addAposta("250_PB", "Campeonato Paraibano 2023", 50.0, 2 );
        mrBetBase.addAposta("250_PB", "Nordestão 2023", 250.0, 1 );
        mrBetBase.addAposta("250_PB", "Nordestão 2023", 250.0, 1 );
        assertEquals("""
                Participação mais frequente em campeonatos (1)
                [252_PB] Sport Lagoa Seca / Carneiro

                Ainda não participou de campeonato
                [250_PB] Nacional de Patos / Canário
                [002_RJ] Clube de Regatas do Flamengo / Urubu
                [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião

                Popularidade em apostas
                Nacional de Patos / 2""", mrBetBase.historico(), "Deve contar duas instancias de aposta no nacional de patos e registrar que o Sport Lagoa Seca participou do maximo de campeonatos (1)");
    }

    @Test
    void testaBonusEmpateEmCamp() {
        mrBetBase.addCampeonato("Campeonato Paraibano 2023", 14);
        mrBetBase.addCampeonato("Nordestão 2023", 20);
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        mrBetBase.addAoCampeonato("250_PB", "Nordestão 2023");
        assertEquals("""
                Participação mais frequente em campeonatos (1)
                [250_PB] Nacional de Patos / Canário
                [252_PB] Sport Lagoa Seca / Carneiro

                Ainda não participou de campeonato
                [002_RJ] Clube de Regatas do Flamengo / Urubu
                [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião

                Popularidade em apostas""", mrBetBase.historico());
    }
    @Test
    void testaBonusVariasApostas() {
        mrBetBase.addCampeonato("Campeonato Paraibano 2023", 14);
        mrBetBase.addCampeonato("Nordestão 2023", 20);
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAposta("250_PB", "Campeonato Paraibano 2023", 50.0, 2 );
        mrBetBase.addAposta("250_PB", "Nordestão 2023", 250.0, 1 );
        mrBetBase.addAposta("250_PB", "Nordestão 2023", 250.0, 1 );
        mrBetBase.addAposta("252_PB", "Campeonato Paraibano 2023", 50.0, 1 );

        assertEquals("""
                Participação mais frequente em campeonatos (0)
                [250_PB] Nacional de Patos / Canário
                [002_RJ] Clube de Regatas do Flamengo / Urubu
                [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião
                [252_PB] Sport Lagoa Seca / Carneiro
                                
                Ainda não participou de campeonato
                [250_PB] Nacional de Patos / Canário
                [002_RJ] Clube de Regatas do Flamengo / Urubu
                [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião
                [252_PB] Sport Lagoa Seca / Carneiro
                                
                Popularidade em apostas
                Nacional de Patos / 2
                Sport Lagoa Seca / 1""", mrBetBase.historico());
    }
    @Test
    void testaTodosEmCamp() {
        mrBetBase.addCampeonato("Campeonato Paraibano 2023", 14);
        mrBetBase.addCampeonato("Nordestão 2023", 20);
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        mrBetBase.addAoCampeonato("105_PB", "Brasileirão série A 2023");
        mrBetBase.addAoCampeonato("250_PB", "Brasileirão série A 2023");
        mrBetBase.addAoCampeonato("002_RJ", "Nordestão 2023");
        mrBetBase.addAoCampeonato("250_PB", "Nordestão 2023");
        assertEquals("""
                Participação mais frequente em campeonatos (2)
                [250_PB] Nacional de Patos / Canário

                Ainda não participou de campeonato

                Popularidade em apostas""", mrBetBase.historico());
    }
    @Test
    void testaTodosEmCampEmpatados() {
        mrBetBase.addCampeonato("Campeonato Paraibano 2023", 14);
        mrBetBase.addCampeonato("Nordestão 2023", 20);
        mrBetBase.addCampeonato("Brasileirão série A 2023", 10);
        mrBetBase.addAoCampeonato("252_PB", "Brasileirão série A 2023");
        mrBetBase.addAoCampeonato("105_PB", "Brasileirão série A 2023");
        mrBetBase.addAoCampeonato("002_RJ", "Nordestão 2023");
        mrBetBase.addAoCampeonato("250_PB", "Nordestão 2023");
        assertEquals("""
                Participação mais frequente em campeonatos (1)
                [250_PB] Nacional de Patos / Canário
                [002_RJ] Clube de Regatas do Flamengo / Urubu
                [105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião
                [252_PB] Sport Lagoa Seca / Carneiro

                Ainda não participou de campeonato

                Popularidade em apostas""", mrBetBase.historico());
    }
}