import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    
    // measures of the car.jpgs
    private int carPicX = 100;
    private int carPicY = 60;
    
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    Workshop<Volvo240> volvoWorkshop = new Workshop<>(5,300,300);
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        cc.cars.get(1).setPosition(100,0); // Står att offseten ska vara på y-led men satt den i x.
        cc.cars.get(2).setPosition(200,0);
        cc.cars.get(0).setPosition(200,0);
        
        
        
        

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                
                // Checks if car is out of bounds, if so, car is flipped,
                if (    x > frame.drawPanel.getWidth() - carPicX ||
                        y > frame.drawPanel.getHeight() - carPicY ||
                        x < 0 || y < 0) {
                    car.turnLeft();
                    car.turnLeft();
                    car.move();
                    x = (int) Math.round(car.getX());
                    y = (int) Math.round(car.getY());
                } else if ( car.getClass() == Volvo240.class && !car.getTransported() &&
                            volvoWorkshop.getCurrentCarsParked() < volvoWorkshop.getMaxCarSpots() &&
                            volvoWorkshop.getX() - carPicX <= car.getX() && volvoWorkshop.getX() + 101 >= car.getX() &&
                            volvoWorkshop.getY() - carPicY <= car.getY() && volvoWorkshop.getY() + 96 >= car.getY()) {
                    car.stopEngine();
                    car.setPosition(volvoWorkshop.getX(),
                            volvoWorkshop.getY() - 10 * (1 + volvoWorkshop.getCurrentCarsParked()));
                    volvoWorkshop.parkCar((Volvo240) car);
                    
                }
                
                int i = cars.indexOf(car);
                frame.drawPanel.moveit(x, y, i);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
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
}
