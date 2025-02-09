import java.awt.*;
import java.util.Stack;

public class CarTransport extends Car {

    private boolean rampOpen;
    private final Load loadedCar;

    public CarTransport() {
        super(2,100, Color.blue, "Scania", false);
        rampOpen = false;
        loadedCar = new Load(5);
    }

    @Override
    public double speedFactor() {
        if (rampOpen) {     // Returns 0 if ramp is open.
            return 0;
        }
        return getEnginePower() * 0.01;
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
        if (rampOpen &&// Ramp must be open,
                car.getTransportable() &&  // car must be transportable and be within 1 units distance to be loaded.
                1 >= Math.sqrt(Math.pow(this.getX() - car.getX(), 2) + Math.pow(this.getY() - car.getY(), 2))) {

            loadedCar.loadCar(car);
            car.setPosition(this.getX(), this.getY());

        }
    }

    // Unloads a car 1 unit behind transport facing the opposite direction.
    public void unloadCar() {
        if (rampOpen) {
            Car car = loadedCar.unloadCar(loadedCar.getLoadedCars().size()-1);
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

        }
    }

    public int getCurrentCars(){
        return loadedCar.getLoadedCars().size();
    }

    @Override
    public void move() {
        super.move();
        for (Car car : loadedCar.getLoadedCars()) { //Updates loaded cars position while being transported
            car.setPosition(this.getX(), this.getY());
        }
    }
}