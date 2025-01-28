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
    void getCurrentSpeed() {
        volvo.stopEngine();
        assertEquals(0.0, volvo.getCurrentSpeed());
    }

    @Test
    void setCurrentSpeed() {
            volvo.setCurrentSpeed(10);
            assertEquals(10, volvo.getCurrentSpeed());
            volvo.setCurrentSpeed(101);
            assertEquals(100, volvo.getCurrentSpeed());
            volvo.setCurrentSpeed(-10);
            assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.black, volvo.getColor());
    }

    @Test
    void setColor() {
        volvo.setColor(Color.blue);
        assertEquals(Color.blue, volvo.getColor());
    }

    @Test
    void startEngine() {
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        volvo.stopEngine();
        assertEquals(0.0,volvo.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        volvo.setCurrentSpeed(10);
        volvo.incrementSpeed(0.5);
        assertEquals(10.625, volvo.getCurrentSpeed());
        volvo.setCurrentSpeed(10);
        volvo.incrementSpeed(1);
        assertEquals(11.25, volvo.getCurrentSpeed());
    }

    @Test
    void decrementSpeed() {
        volvo.setCurrentSpeed(10);
        volvo.decrementSpeed(0.5);
        assertEquals(9.375, volvo.getCurrentSpeed());
    }

    @Test
    void move() {
        volvo.startEngine();
        volvo.move();
        assertEquals(0.1, volvo.getY());
        assertEquals(0.0, volvo.getX());
    }

    @Test
    void turnLeft() {
        volvo.startEngine();
        volvo.setCurrentSpeed(10);
        volvo.turnLeft();
        volvo.move();
        assertEquals(-10, volvo.getX());
        assertEquals(0, volvo.getY());
    }
    @Test
    void turnRight() {
        volvo.startEngine();
        volvo.setCurrentSpeed(10);
        volvo.turnRight();
        volvo.move();
        assertEquals(10, volvo.getX());
        assertEquals(0, volvo.getY());
    }

    @Test
    void getX() {
        assertEquals(0.0, volvo.getX());
    }

    @Test
    void getY() {
        assertEquals(0.0, volvo.getY());
    }

    @Test
    void speedFactor() {
        assertEquals(1.25, volvo.speedFactor());
    }

    @Test
    void gas() {
        volvo.setCurrentSpeed(10);
        volvo.gas(0);
        assertEquals(10, volvo.getCurrentSpeed());
        volvo.setCurrentSpeed(10);
        volvo.gas(2);
        assertEquals(11.25, volvo.getCurrentSpeed());
        volvo.setCurrentSpeed(10);
        volvo.gas(0.5);
        assertEquals(10.625, volvo.getCurrentSpeed());

    }

    @Test
    void brake() {
        volvo.setCurrentSpeed(10);
        volvo.brake(0);
        assertEquals(10, volvo.getCurrentSpeed());
        volvo.setCurrentSpeed(10);
        volvo.brake(2);
        assertEquals(8.75, volvo.getCurrentSpeed());
        volvo.setCurrentSpeed(10);
        volvo.brake(0.5);
        assertEquals(9.375, volvo.getCurrentSpeed());
    }
}