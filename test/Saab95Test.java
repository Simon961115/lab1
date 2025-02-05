import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    Saab95 saab = new Saab95();
    @Test
    void getNrDoors() {
        int nDoors;
        nDoors = saab.getNrDoors();
        assertEquals(2, nDoors);
    }
    
    @Test
    void getEnginePower() {
        assertEquals(125, saab.getEnginePower());
    }
    
    @Test
    void getColor() {
        assertEquals(Color.red, saab.getColor());
    }
    
    @Test
    void setTurboOn() {
        saab.setTurboOn();
        assertTrue(saab.turboOn);
    }
    
    @Test
    void setTurboOff() {
        saab.setTurboOff();
        assertFalse(saab.turboOn);
    }
    
    @Test
    void speedFactor() {
        saab.setTurboOff();
        assertEquals(1.25, saab.speedFactor());
        saab.setTurboOn();
        assertEquals(1.625, saab.speedFactor());
    }
    
    @Test
    void gas() {
        saab.gas(1);
        assertEquals(1.25, saab.getCurrentSpeed());
        saab.gas(2);
        assertEquals(2.5, saab.getCurrentSpeed());
        saab.gas(-1);
        assertEquals(2.5, saab.getCurrentSpeed());
    }
    
    @Test
    void brake() {
        saab.gas(1);
        saab.gas(1);
        saab.brake(0);
        assertEquals(2.5, saab.getCurrentSpeed());
        saab.brake(2);
        assertEquals(1.25, saab.getCurrentSpeed());
        saab.brake(-1);
        assertEquals(1.25, saab.getCurrentSpeed());
    }
}