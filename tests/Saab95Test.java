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
    void getCurrentSpeed() {
        saab.stopEngine();
        assertEquals(0.0, saab.getCurrentSpeed());
    }

    @Test
    void setCurrentSpeed() {
        saab.setCurrentSpeed(10);
        assertEquals(10, saab.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.red, saab.getColor());
    }

    @Test
    void setColor() {
        saab.setColor(Color.black);
        assertEquals(Color.black, saab.getColor());
    }

    @Test
    void startEngine() {
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        saab.stopEngine();
        assertEquals(0.0, saab.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        //TODO
    }

    @Test
    void decrementSpeed() {
        //TODO
    }

    @Test
    void move() {
        saab.startEngine();
        saab.move();
        assertEquals(0.1, saab.getY());
        assertEquals(0.0, saab.getX());
    }

    @Test
    void turnLeft() {
        saab.startEngine();
        saab.setCurrentSpeed(10);
        saab.turnLeft();
        saab.move();
        assertEquals(-10, saab.getX());
        assertEquals(0, saab.getY());
    }

    @Test
    void turnRight() {
        saab.startEngine();
        saab.setCurrentSpeed(10);
        saab.turnRight();
        saab.move();
        assertEquals(10, saab.getX());
        assertEquals(0, saab.getY());
    }


    @Test
    void getX() {
        assertEquals(0.0, saab.getX());
    }

    @Test
    void getY() {
        assertEquals(0.0, saab.getY());
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
    }

    @Test
    void brake() {
    }
}