package tests;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import MrBet.*;
import org.junit.jupiter.api.Test;

class ApostaTest {
    private Time time1;
    private Time time2;
    private Campeonato camp1;

    @BeforeEach
    void setUp() {
        time1 = new Time("250_PB", "time um", "mascote um");
        time2 = new Time("252_PB", "time dois", "mascote dois");
        camp1 = new Campeonato("campeonato um", 10);
    }
   @Test
    void testaConstrutorNormal() {
       Aposta aposta = new Aposta(time1, camp1, 50, 5);
       assertEquals(
               """
                       [250_PB] time um / mascote um
                       campeonato um
                       5/10
                       R$ 50.00""",aposta.toString());
   }
    @Test
    void testaConstrutorTimeNulo() {
        try {
            Aposta aposta = new Aposta(null, camp1, 50, 5);
            fail();
        } catch (NullPointerException e) {
            assertEquals("O TIME NÃO EXISTE!" ,e.getMessage());
        }
    }
    @Test
    void testaConstrutorCampNulo() {
        try {
            Aposta aposta = new Aposta(time2, null, 50, 5);
            fail();
        } catch (NullPointerException e) {
            assertEquals("O CAMPEONATO NÃO EXISTE!" ,e.getMessage());
        }
    }
    @Test
    void testaConstrutorColocacaoExcedente() {
        try {
            Aposta aposta = new Aposta(time2, camp1, 50, 11);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("APOSTA NÃO REGISTRADA!" ,e.getMessage());
        }
    }
    @Test
    void testaConstrutorColocacaoInferior() {
        try {
            Aposta aposta = new Aposta(time2, camp1, 50, 0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("APOSTA NÃO REGISTRADA!" ,e.getMessage());
        }
    }
    @Test
    void testaConstrutorColocacaoLimite() {
        Aposta aposta = new Aposta(time1, camp1, 50, 10);
        assertEquals(
                """
                        [250_PB] time um / mascote um
                        campeonato um
                        10/10
                        R$ 50.00""",aposta.toString());
    }
}