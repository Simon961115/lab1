import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    Saab95 saab = new Saab95(); // Tests all general car methods with a Saab95
    
    @Test
    void getCurrentSpeed() {
        saab.stopEngine();
        assertEquals(0.0, saab.getCurrentSpeed());
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
    void move() {
        saab.startEngine();
        saab.move();
        assertEquals(0.1, saab.getY());
        assertEquals(0.0, saab.getX());
    }
    
    @Test
    void turnLeft() {
        saab.startEngine();
        saab.turnLeft();
        saab.move();
        assertEquals(-0.1, saab.getX());
        assertEquals(0, saab.getY());
    }
    
    @Test
    void turnRight() {
        saab.startEngine();
        saab.turnRight();
        saab.move();
        assertEquals(0.1, saab.getX());
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
    
    @Test
    void setPosition() {
        saab.setPosition(1,1);
        assertEquals(1.0, saab.getX());
        assertEquals(1.0, saab.getY());
    }
    
    @Test
    void getSetDirection() {
        assertEquals(Car.Directions.NORTH, saab.getCurrentDirection());
        saab.setDirection(Car.Directions.EAST);
        assertEquals(Car.Directions.EAST, saab.getCurrentDirection());
    }
    
    @Test
    void getTransportable() {
        assertTrue(saab.getTransportable());
    }
    
    @Test
    void getSetTransported() {
        assertFalse(saab.getTransported());
        saab.setTransported(true);
        assertTrue(saab.getTransported());
        saab.setTransported(false);
        assertFalse(saab.getTransported());
    }
    
}
