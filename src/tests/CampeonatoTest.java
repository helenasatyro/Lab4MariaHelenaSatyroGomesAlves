package tests;

import MrBet.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampeonatoTest {

    private Campeonato campBase;
    private Time timeBase;

    @BeforeEach
    void setUp() {
        campBase = new Campeonato("Camp Base", 2);
        timeBase = new Time("220_PR", "Time Time", "Besouro");
    }

    @Test
    void testaAddTimeSemRestricao() {
        campBase.addTime(timeBase);
        assertTrue(campBase.temTime(timeBase));
    }
    @Test
    void testaAddTimeCampCheio() {
        Time time1 = new Time("220_PR", "Time", "Mascote");
        Time time2 = new Time("330_PB", "Time", "Mascote");
        campBase.addTime(time1);
        campBase.addTime(time2);
        try {
            campBase.addTime(timeBase);
            fail();
        } catch ( IllegalStateException e) {
            assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", e.getMessage());
        }
    }
}