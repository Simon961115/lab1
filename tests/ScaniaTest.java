import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {
Scania scania = new Scania();
final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void gas(){
        scania.gas(1);
        assertEquals(1.08,scania.getCurrentSpeed());
    }

    @Test
    void gasMedFlakUppe() {
        System.setOut(new PrintStream(outContent));
        scania.höjFlaket(10);
        scania.gas(1);
        assertEquals("Släpp ner flaket innan du kör!\n", outContent.toString());
    }

    @Test
    void getflakVinkel() {
        scania.höjFlaket(15);
        assertEquals(15,scania.getflakVinkel());
    }

    @Test
    void höjFlaket() {
        scania.höjFlaket(10);
        assertEquals(10, scania.getflakVinkel());
    }

    @Test
    void sänkFlaket() {
        scania.höjFlaket(20);
        scania.sänkFlaket(10);
        assertEquals(10,scania.getflakVinkel());
    }
    @Test
    void höjFlaketMedFart(){
        System.setOut(new PrintStream(outContent));
        scania.startEngine();
        scania.gas(1);
        scania.höjFlaket(10);
        assertEquals("Otillåtet värde/Stanna bilen\n", outContent.toString());
    }
    @Test
    void sänkFlaketMedFart(){
        System.setOut(new PrintStream(outContent));
        scania.startEngine();
        scania.gas(1);
        scania.sänkFlaket(10);
        assertEquals("Otillåtet värde/Stanna bilen\n", outContent.toString());
    }
}