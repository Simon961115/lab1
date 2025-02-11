import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {
    Scania scania = new Scania();
    
    @BeforeEach
    public void setUp() {
        scania.increaseLoadAngle(30);
    }
    
    @Test
    void getNrDoors() {
        assertEquals(2, scania.getNrDoors());
    }
    
    @Test
    void getEnginePower() {
        assertEquals(100, scania.getEnginePower());
    }
    
    @Test
    void getColor() {
        assertEquals(Color.gray, scania.getColor());
    }
    
    @Test
    void getLoadAngle() {
        assertEquals(30, scania.getLoadAngle());
    }
    
    
    @Test
    void increaseLoadAngle() {
        scania.increaseLoadAngle(10);
        assertEquals(40, scania.getLoadAngle());
        scania.increaseLoadAngle(100);
        assertEquals(70, scania.getLoadAngle());
    }
    
    @Test
    void decreaseLoadAngle() {
        scania.decreaseLoadAngle(10);
        assertEquals(20, scania.getLoadAngle());
        scania.decreaseLoadAngle(100);
        assertEquals(0, scania.getLoadAngle());
    }
    
    @Test
    void gas(){
        scania.gas(1);
        assertEquals(0, scania.getCurrentSpeed());
        scania.decreaseLoadAngle(30);
        scania.gas(1);
        assertEquals(1, scania.getCurrentSpeed());
    }
    
    @Test
    void brake(){
        scania.decreaseLoadAngle(30);
        scania.gas(1);
        assertEquals(1, scania.getCurrentSpeed());
        scania.brake(0.5);
        assertEquals(0.5, scania.getCurrentSpeed());
        scania.brake(10);
        assertEquals(0, scania.getCurrentSpeed());
    }
    
    @Test
    void increaseAngleInMotion() {
        scania.decreaseLoadAngle(30);
        scania.gas(1);
        scania.increaseLoadAngle(10);
        assertEquals(0, scania.getLoadAngle());
    }
}
