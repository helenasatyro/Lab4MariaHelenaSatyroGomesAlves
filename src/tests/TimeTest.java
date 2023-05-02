package tests;

import MrBet.*;
import org.junit.jupiter.api.BeforeEach;

class TimeTest {

    private Time time1;
    private Time time2;
    private Time time3;
    private Time time4;
    @BeforeEach
    void setUp() {
        time1 = new Time("250_PB", "Nacional de Patos", "Canário");
        time2 = new Time("252_PB", "Sport Lagoa Seca", "Carneiro");
        time3 = new Time("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
        time4 = new Time("105_PB", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");
    }
}