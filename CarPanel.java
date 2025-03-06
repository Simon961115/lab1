import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
Klass som innehåller lista med bilar, volvoverkstad samt alla operationer du kan göra på bilar.


 */

public class CarPanel {

    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    ArrayList<Observer> observers = new ArrayList<>();



    Workshop<Volvo240> volvoWorkshop = new Workshop<>(5,300,300);


    public CarPanel() {

        for (int i = 0; i < 3;i++) {
            cars.add(CarFactory.createRandomCar());
        }

        cars.get(1).setPosition(100,0); // Står att offseten ska vara på y-led men satt den i x.
        cars.get(2).setPosition(200,0);
        cars.get(0).setPosition(200,0);
    }

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

    void updateCarPos(int x, int y, int i) {

        for (Observer observer : observers) {
            observer.moveIt(x, y,i);
        }
    }

    void update() {
        for (Observer observer : observers) {
            observer.repaint();
        }
    }

    void addObserver (Observer observer) {
        observers.add(observer);
    }



}
