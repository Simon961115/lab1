import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
class WorkshopTest {

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    Volvo240 volvo = new Volvo240();
    Volvo240 volvo1 = new Volvo240();
    Saab95 saab = new Saab95();
    Saab95 saab1 = new Saab95();


    @Test
    void parkCar() {
        System.setOut(new PrintStream(outContent));
        Workshop<Vehicle> verkstad = new Workshop<>(5);
        verkstad.parkCar(volvo);
        verkstad.parkCar(saab);
        verkstad.listParkedCars();
        assertEquals("Parkerade bilar:\n" + "0: Volvo240\n" + "1: Saab95\n", outContent.toString());

    }

    @Test
    void removeCar() {
        Workshop<Vehicle> verkstad = new Workshop<>(10);
        verkstad.parkCar(saab);
        verkstad.parkCar(volvo);
        verkstad.parkCar(volvo1);
        assertEquals(volvo, verkstad.removeCar(volvo));
        verkstad.listParkedCars();


    }

    @Test
    void listParkedCars() {
        System.setOut(new PrintStream(outContent));
        Workshop<Vehicle> volvoWorkshop = new Workshop<>(5);
        volvoWorkshop.parkCar(volvo);
        volvoWorkshop.listParkedCars();
        assertEquals("Parkerade bilar:\n" + "0: Volvo240\n", outContent.toString());
    }
}