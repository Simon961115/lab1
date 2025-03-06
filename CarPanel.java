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
    
    Workshop<Volvo240> volvoWorkshop = new Workshop<>(5, 300, 300);
    
    private int carPicX = 100;
    private int carPicY = 60;
    
    //Timer
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());
    
    
    public CarPanel() {
        cars.add(new Volvo240());
        cars.add(new Saab95());
        cars.add(new Scania());
        cars.get(1).setPosition(100, 0); // Står att offseten ska vara på y-led men satt den i x.
        cars.get(2).setPosition(200, 0);
        cars.get(0).setPosition(0, 0);
        timer.start();
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
            observer.moveIt(x, y, i);
        }
    }
    
    void update() {
        for (Observer observer : observers) {
            observer.repaint();
        }
    }
    
    void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    void addCar() {
        if (cars.size() < 10) {
            Vehicle newCar = CarFactory.createRandomCar();
            cars.add(newCar);
            newCar.setPosition(50 * (cars.size() - 1), 0);
            for (Observer observer : observers) {
                if (newCar instanceof Volvo240) {
                    observer.addCar("pics/Volvo240.jpg");
                } else if (newCar instanceof Saab95) {
                    observer.addCar("pics/Saab95.jpg");
                } else {
                    observer.addCar("pics/Scania.jpg");
                }
                
            }
        }
    }
    
    void removeCar() {
        if (cars.size() > 0) {
            cars.remove(cars.size() - 1);
            for (Observer observer : observers) {
                observer.removeCar();
            }
        }
    }
    
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                
                // Checks if car is out of bounds, if so, car is flipped,
                if (x > 700 - carPicX ||
                        y > 560 - carPicY ||
                        x < 0 || y < 0) {
                    car.turnLeft();
                    car.turnLeft();
                    car.move();
                    x = (int) Math.round(car.getX());
                    y = (int) Math.round(car.getY());
                } else if (car.getClass() == Volvo240.class && !car.getTransported() &&
                        volvoWorkshop.getCurrentCarsParked() < volvoWorkshop.getMaxCarSpots() &&
                        volvoWorkshop.getX() - carPicX <= car.getX() && volvoWorkshop.getX() + 101 >= car.getX() &&
                        volvoWorkshop.getY() - carPicY <= car.getY() && volvoWorkshop.getY() + 96 >= car.getY()) {
                    car.stopEngine();
                    car.setPosition(volvoWorkshop.getX(),
                            volvoWorkshop.getY() - 10 * (1 + volvoWorkshop.getCurrentCarsParked()));
                    volvoWorkshop.parkCar((Volvo240) car);
                    
                }
                
                int i = cars.indexOf(car);
                updateCarPos(x, y, i);
                // repaint() calls the paintComponent method of the panel
                
            }
            update();
        }
        
        
    }
}
