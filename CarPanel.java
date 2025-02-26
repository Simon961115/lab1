import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarPanel {

    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    Workshop<Volvo240> volvoWorkshop = new Workshop<>(5,300,300);
    //methods:

    public CarPanel() {
        cars.add(new Volvo240());
        cars.add(new Saab95());
        cars.add(new Scania());
        cars.get(1).setPosition(100,0); // Står att offseten ska vara på y-led men satt den i x.
        cars.get(2).setPosition(200,0);
        cars.get(0).setPosition(200,0);

        // Start the timer


    }



    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */


    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }
    
    // Calls the break method for each car once
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }
    
    // Turns on/off turbo for every Saab car
    void setTurbo(boolean turbo) {
        for (Vehicle car : cars) {
            if (car.getClass() == Saab95.class) {
                if (turbo) {
                    ((Saab95) car).setTurboOn();
                } else {
                    ((Saab95) car).setTurboOff();
                }
            }
        }
    }
    
    // Lifts/lowers bed on all Scania cars
    void setLiftAngle(int angle) {
        for (Vehicle car : cars) {
            if (car.getClass() == Scania.class) {
                if (angle > 0) {
                    ((Scania) car).increaseLoadAngle(angle);
                } else {
                    ((Scania) car).decreaseLoadAngle(-angle);
                }
            }
        }
    }
    
    // Stops all cars
    void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }
    
    // Starts all cars
    void startEngine() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }
}
