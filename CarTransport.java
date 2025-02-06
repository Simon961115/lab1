import java.awt.*;
import java.util.Stack;

public class CarTransport extends Car {
    
    private boolean rampOpen;
    private Stack<Car> cars;
    private final int maxCars = 5;
    private int currentCars;
    
    public CarTransport() {
        super(2,100, Color.blue, "Scania",false);
        rampOpen = false;
        cars = new Stack<>();
        currentCars = 0;
    }
    
    @Override
    double speedFactor() {
        if (rampOpen) {     // Returns 0 if ramp is open.
            return 0;
        }
        return enginePower * 0.01;
    }
    
    public void setRampOpen(boolean rampOpen) {
        if (getCurrentSpeed() == 0){ // Speed must be 0 to alter rampOpen.
            this.rampOpen = rampOpen;
        }
    }
    
    public boolean getRampOpen() {
        return rampOpen;
    }
    
    public void loadCar(Car car) {
        if (rampOpen &&                     // To be loaded: the ramp must be open,
                currentCars < maxCars &&    // must be fewer than maxCars already loaded,
                car.getTransportable() &&   // car must be transportable,
                !car.getTransported() &&     // not currently being transported and be within 1 units of distance from transport.
                1 >= Math.sqrt(Math.pow(this.getX() - car.getX(), 2) + Math.pow(this.getY() - car.getY(), 2))) {
            
            car.setPosition(this.getX(), this.getY());
            car.setTransported(true);
            cars.push(car);
            currentCars++;
        }
    }
    
    // Unloads a car 1 unit behind transport facing the opposite direction.
    public void unloadCar() {
        if (rampOpen && currentCars > 0) { // Ramp must be open, and there must be a car loaded.
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
            car.setTransported(false);
            currentCars--;
        }
    }
    
    public int getCurrentCars() {
        return currentCars;
    }
    
    @Override
    public void move() {
        super.move();
        for (Car car : cars) {
            car.setPosition(this.getX(), this.getY());
        }
    }
}
