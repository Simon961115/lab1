import java.util.List;
import java.util.Stack;

import static java.lang.Math.abs;


public class CarTransport extends Car {
    private double trimFactor;
    private boolean rampDown;
    private List<Car> load;

    public CarTransport() {
        super(2, java.awt.Color.black,540,"Transport");
        trimFactor = 0.2;
        rampDown = false;
        load = new Stack<Car>();

    }

    public void lowerRamp () {
        if (getCurrentSpeed() == 0 ) {rampDown = true;}
    }

    public void raiseRamp() {
        if (getCurrentSpeed() == 0 ) {rampDown = false;}

    }

    public boolean isRampDown () {
        return rampDown;
    }

    public void unload (Car car) {
        if (getCurrentSpeed() == 0 && isRampDown()) {
            load.remove(car);
            car.setPos(getX(),getY());

        }
    }

    public void loadCar (Car car) {
        if (car instanceof CarTransport) {
            throw new IllegalStateException("Kan ej lasta en biltranport på en biltransport!");
        }
        else if (carIsInRange(car) && isRampDown()){
            load.add(car);
        }

    }

    public boolean carIsInRange (Car car) {
        return abs(getX()-car.getX()) < 5 && abs(getY()-car.getY()) < 5;

    }

    public double speedFactor() {return getEnginePower() * 0.01 * trimFactor; }

}
