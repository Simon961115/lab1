import java.awt.*;
import java.util.Stack;

public class CarTransport extends Car {
    
    private boolean rampOpen;
    private Stack<Car> cars;
    private final int maxCars = 5;
    private int currentCars;
    
    public CarTransport() {
        super(2,100, Color.blue, "Scania");
        rampOpen = false;
        cars = new Stack<>();
        currentCars = 0;
        transportable = false;
    
    }
    
    @Override
    double speedFactor() {
        if (rampOpen) {     // Returns 0 if ramp is open.
            return 0;
        }
        return enginePower * 0.01;
    }
    
    public void setRampOpen(boolean rampOpen) {
        if (getCurrentSpeed() == 0){
            this.rampOpen = rampOpen;
        } else {
            this.rampOpen = false;
        }
    }
    
    public boolean getRampOpen() {
        return rampOpen;
    }
    
    public void loadCar(Car car) {
        if (rampOpen &&                     // Ramp must be open,
                currentCars < maxCars &&    // must be fewer than maxCars already loaded,
                car.transportable &&        // car must be transportable and be within 1 units distance to be loaded.
                1 >= Math.sqrt(Math.pow(this.getX() - car.getX(), 2) + Math.pow(this.getY() - car.getY(), 2))) {
            
            car.setPosition(this.getX(), this.getY());
            cars.push(car);
            currentCars++;
        }
    }
    
    // Unloads a car 1 unit behind transport facing the opposite direction.
    public void unloadCar() {
        if (rampOpen && currentCars > 0) {
            Car car = cars.pop();
            Directions dir = this.getCurrentDirection();
            switch (dir) {
                case NORTH -> {
                    car.setPosition(this.getX(), this.getY() - 1);
                    car.setDirection(Directions.SOUTH);
                }
                case SOUTH -> {
                    car.setPosition(this.getX(), this.getY() + 1);
                    car.setDirection(Directions.NORTH);
                }
                case EAST -> {
                    car.setPosition(this.getX() - 1, this.getY());
                    car.setDirection(Directions.WEST);
                }
                case WEST -> {
                    car.setPosition(this.getX() + 1, this.getY());
                    car.setDirection(Directions.EAST);
                }
            }
            
            currentCars--;
        }
    }
    
    @Override
    public void move() {
        super.move();
        for (Car car : cars) {
            car.setPosition(this.getX(), this.getY());
        }
    }
}
