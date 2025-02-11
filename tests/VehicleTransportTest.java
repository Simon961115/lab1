import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class VehicleTransportTest {
    VehicleTransport transport = new VehicleTransport();
    Saab95 saab1= new Saab95();
    Saab95 saab2= new Saab95();
    Saab95 saab3= new Saab95();
    Saab95 saab4= new Saab95();
    Saab95 saab5= new Saab95();
    Saab95 saab6= new Saab95();
    Volvo240 volvo1 = new Volvo240();
    Scania scania1 = new Scania();

    @Test
    void carTransportConstructor() {
        assertEquals(2, transport.getNrDoors());
        assertEquals(100, transport.getEnginePower());
        assertEquals(Color.blue, transport.getColor());
    }

    @Test
    void speedFactor() {
        assertEquals(1, transport.speedFactor());
        transport.setRampOpen(true);
        assertEquals(0, transport.speedFactor());
    }

    @Test
    void getSetRampOpen() {
        assertFalse(transport.getRampOpen());
        transport.setRampOpen(true);
        assertTrue(transport.getRampOpen());
        transport.setRampOpen(false);
        assertFalse(transport.getRampOpen());
    }

    @Test
    void loadCarToCurrentCars() {
        assertEquals(0, transport.getCurrentCars());
        assertFalse(saab1.getTransported());
        transport.setRampOpen(true);
        assertTrue(transport.getRampOpen());
        transport.loadCar(saab1);
        assertEquals(1, transport.getCurrentCars());
        assertTrue(saab1.getTransported());
    }

    @Test
    void loadCarOverMaxCars() {
        assertEquals(0, transport.getCurrentCars());
        transport.setRampOpen(true);
        transport.loadCar(saab1);
        transport.loadCar(saab2);
        transport.loadCar(saab3);
        transport.loadCar(saab4);
        transport.loadCar(saab5);
        assertEquals(5, transport.getCurrentCars());
        transport.loadCar(saab6);
        assertEquals(5, transport.getCurrentCars());
    }

    @Test
    void loadCarConditions() {
        assertEquals(0, transport.getCurrentCars());
        transport.loadCar(saab1);
        assertEquals(0, transport.getCurrentCars());
        transport.setRampOpen(true);
        transport.loadCar(saab1);
        assertEquals(1, transport.getCurrentCars());
        transport.loadCar(saab1);
        assertEquals(2, transport.getCurrentCars());
        transport.loadCar(scania1);
        assertEquals(2, transport.getCurrentCars());
        transport.loadCar(volvo1);
        assertEquals(3, transport.getCurrentCars());
        transport.loadCar(transport);
        assertEquals(3, transport.getCurrentCars());
        saab2.setPosition(3,3);
        transport.loadCar(saab2);
        assertEquals(3, transport.getCurrentCars());
    }

    @Test
    void unloadOrderAndDirection() {    // Starts facing north, unloads in opposite direction.
        transport.setRampOpen(true);
        transport.loadCar(saab1);
        transport.loadCar(saab2);
        transport.loadCar(saab3);
        transport.loadCar(saab4);
        transport.unloadCar();
        assertEquals(Vehicle.Directions.SOUTH, saab4.getCurrentDirection());
        transport.turnLeft();
        transport.unloadCar();
        assertEquals(Vehicle.Directions.EAST, saab3.getCurrentDirection());
        transport.turnLeft();
        transport.unloadCar();
        assertEquals(Vehicle.Directions.NORTH, saab2.getCurrentDirection());
        transport.turnLeft();
        transport.unloadCar();
        assertEquals(Vehicle.Directions.WEST, saab1.getCurrentDirection());
    }

    @Test
    void unloadCarRampClosed() {
        transport.setRampOpen(true);
        transport.loadCar(saab1);
        transport.loadCar(saab2);
        assertEquals(2, transport.getCurrentCars());
        transport.setRampOpen(false);
        transport.unloadCar();
        assertEquals(2, transport.getCurrentCars());
    }

    @Test
    void loadedCarsSharePosition() {
        transport.setRampOpen(true);
        transport.loadCar(saab1);
        transport.loadCar(saab2);
        transport.setRampOpen(false);
        assertEquals(0, transport.getX());
        assertEquals(0, transport.getY());
        assertEquals(0, saab1.getX());
        assertEquals(0, saab1.getY());
        transport.gas(1);
        transport.move();
        transport.move();
        assertEquals(2, transport.getY());
        assertEquals(2, saab1.getY());
        assertEquals(2, saab2.getY());
        transport.brake(1);
        transport.setRampOpen(true);
        transport.unloadCar();
        assertEquals(1, saab2.getY()); // -1 due to unload.
        transport.setRampOpen(false);
        transport.gas(1);
        transport.move();
        transport.move();
        assertEquals(4, transport.getY());
        assertEquals(4, saab1.getY());
        assertEquals(1, saab2.getY());
        transport.turnRight();
        transport.move();
        transport.move();
        assertEquals(2, transport.getX());
        assertEquals(2, saab1.getX());
        assertEquals(0, saab2.getX());
    }

    @Test
    void openRampWhileMoving() {
        assertFalse(transport.getRampOpen());
        transport.gas(1);
        transport.setRampOpen(true);
        assertFalse(transport.getRampOpen());
    }

    @Test
    void moveWithRampOpen() {
        transport.setRampOpen(true);
        transport.gas(1);
        transport.move();
        assertEquals(0,transport.getCurrentSpeed());
        assertEquals(0,transport.getY());
    }
}