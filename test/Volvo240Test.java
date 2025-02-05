import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {
    Volvo240 volvo = new Volvo240();

    @Test
    void getNrDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(100, volvo.getEnginePower());
    }
    
    @Test
    void getColor() {
        assertEquals(Color.black, volvo.getColor());
    }
    

    @Test
    void speedFactor() {
        assertEquals(1.25, volvo.speedFactor());
    }

    @Test
    void gas() {
        volvo.gas(0);
        assertEquals(0, volvo.getCurrentSpeed());
        volvo.gas(2);
        assertEquals(1.25, volvo.getCurrentSpeed());
        volvo.gas(-2);
        assertEquals(1.25, volvo.getCurrentSpeed());

    }

    @Test
    void brake() {
        volvo.gas(1);
        volvo.gas(1);
        volvo.gas(1);
        volvo.brake(-2);
        assertEquals(3.75, volvo.getCurrentSpeed());
        volvo.brake(2);
        assertEquals(2.5, volvo.getCurrentSpeed());
        volvo.brake(0.5);
        assertEquals(1.875, volvo.getCurrentSpeed());
    }
}