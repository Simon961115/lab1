import java.awt.*;
import java.util.LinkedList;

public class CarTransport extends Car {
    
    private boolean rampOpen;
    private final int maxCars = 5;
    private Load load;
    private LinkedList<Car> cars;
    
    public CarTransport() {
        super(2,100, Color.blue, "Scania",false);
        rampOpen = false;
        load = new Load(5);
        cars = new LinkedList<>();
        
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
        if (rampOpen && car.getTransportable() &&
                1 >= Math.sqrt(Math.pow(this.getX() - car.getX(), 2) + Math.pow(this.getY() - car.getY(), 2))) {
            load.loadCar(car);
            car.setPosition(this.getX(), this.getY());
        }
    }
    
//    public void loadCar(Car car) {
//        if (rampOpen &&                     // To be loaded: the ramp must be open,
//                currentCars < maxCars &&    // must be fewer than maxCars already loaded,
//                car.getTransportable() &&   // car must be transportable,
//                !car.getTransported() &&     // not currently being transported and be within 1 units of distance from transport.
//                1 >= Math.sqrt(Math.pow(this.getX() - car.getX(), 2) + Math.pow(this.getY() - car.getY(), 2))) {
//
//            car.setPosition(this.getX(), this.getY());
//            car.setTransported(true);
//            cars.push(car);
//            currentCars++;
//        }
//    }
    
    // Unloads a car 1 unit behind transport facing the opposite direction.
    public void unloadCar() {
        if (rampOpen) {
            Car car = load.unloadCar(load.getCarsSize()-1);
            if (car != null) {
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
            }
        }
    }
    
//    public int getCurrentCars() {
//        return load.getCarsSize();
//    }
    public int getCurrentCars() {
        return load.getCarsSize();
    }
    
    @Override
    public void move() {
        super.move();
        for (Car car : load.getCars()) {
            car.setPosition(this.getX(), this.getY());
        }
    }
}
