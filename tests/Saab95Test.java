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

//    @Test
//    void setCurrentSpeed() {
//        saab.gas(10);
//        assertEquals(10, saab.getCurrentSpeed());
//        saab.setCurrentSpeed(130);
//        assertEquals(125, saab.getCurrentSpeed());
//        saab.setCurrentSpeed(-10);
//        assertEquals(0, saab.getCurrentSpeed());
//    }

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

//    @Test
//    void incrementSpeed() { // speedfactor = 1.25 currentspeed = 10 amount = 0.5
//        saab.setTurboOff();
//        saab.setCurrentSpeed(10);
//        saab.incrementSpeed(0.5);
//        assertEquals(10.625, saab.getCurrentSpeed());
//        saab.setCurrentSpeed(10);
//        saab.incrementSpeed(1);
//        assertEquals(11.25, saab.getCurrentSpeed());
//    }

//    @Test
//    void decrementSpeed() {
//        saab.setCurrentSpeed(10);
//        saab.decrementSpeed(0.5);
//        assertEquals(9.375, saab.getCurrentSpeed());
//    }

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
        saab.gas(1);
        saab.turnLeft();
        saab.move();
        assertEquals(-1.35, saab.getX());
        assertEquals(0, saab.getY());
    }

    @Test
    void turnRight() {
        saab.gas(1);
        saab.turnRight();
        saab.move();
        assertEquals(1.25, saab.getX());
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
        assertTrue(saab.isTurboOn());
    }

    @Test
    void setTurboOff() {
        saab.setTurboOff();
        assertFalse(saab.isTurboOn());
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
        saab.gas(-1);
        assertEquals(0, saab.getCurrentSpeed());
        saab.gas(1);
        assertEquals(1.25, saab.getCurrentSpeed());
        saab.gas(2);
        assertEquals(2.5, saab.getCurrentSpeed());
    }

    @Test
    void brake() {
        saab.gas(1);
        saab.brake(0);
        assertEquals(1.25, saab.getCurrentSpeed());
        saab.gas(1);
        saab.brake(2);
        assertEquals(1.25, saab.getCurrentSpeed());
        saab.gas(1);
        saab.brake(0.5);
        assertEquals(1.875, saab.getCurrentSpeed());
    }
}