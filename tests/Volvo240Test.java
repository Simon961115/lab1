import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {
    Volvo240 volvo = new Volvo240();

    @Test
    void getNrDoors() {
        assertEquals(2, volvo.getNrDoors());
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
    }

    @Test
    void decrementSpeed() {
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
        volvo.move();
        assertEquals(0.1, volvo.getY());
        assertEquals(0.0, volvo.getX());
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
    }

    @Test
    void brake() {
    }
}