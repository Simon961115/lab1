import java.awt.*;

public class VehicleTransport extends Vehicle {

    private boolean rampOpen;
    private final Container<Vehicle> loadedCar;

    public VehicleTransport() {
        super(2,Color.blue,100 , "Scania", false);
        this.rampOpen = false;
        this.loadedCar = new Container<>(5);
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

    public void loadCar(Vehicle vehicle) {
        if (rampOpen &&// Ramp must be open,
                vehicle.getTransportable() &&  // car must be transportable and be within 1 units distance to be loaded.
                1 >= Math.sqrt(Math.pow(this.getX() - vehicle.getX(), 2) + Math.pow(this.getY() - vehicle.getY(), 2))) {
            loadedCar.loadItem(vehicle);
            vehicle.setTransported(true);
            vehicle.setPosition(this.getX(), this.getY());

        }
    }

    // Unloads a car 1 unit behind transport facing the opposite direction.
    public void unloadCar() {
        if (rampOpen) {
            Vehicle vehicle = (Vehicle) loadedCar.unloadItem(loadedCar.getLoadedItem().size()-1);
            Directions dir = this.getCurrentDirection();
            switch (dir) {
                case NORTH -> {
                    vehicle.setPosition(this.getX(), this.getY() - 1);
                    vehicle.setDirection(Directions.SOUTH);
                }
                case SOUTH -> {
                    vehicle.setPosition(this.getX(), this.getY() + 1);
                    vehicle.setDirection(Directions.NORTH);
                }
                case EAST -> {
                    vehicle.setPosition(this.getX() - 1, this.getY());
                    vehicle.setDirection(Directions.WEST);
                }
                case WEST -> {
                    vehicle.setPosition(this.getX() + 1, this.getY());
                    vehicle.setDirection(Directions.EAST);
                }
            }
            vehicle.setTransported(false);

        }
    }

    public int getCurrentCars(){
        return loadedCar.getLoadedItem().size();
    }

    @Override
    public void move() {
        super.move();
        for (int i = 0; i < loadedCar.getLoadedItem().size() ; i++) {
            Vehicle vehicle = (Vehicle) loadedCar.getLoadedItem().get(i);
            vehicle.setPosition(this.getX(), this.getY());
            //Updates loaded cars position while being transported
            vehicle.setPosition(this.getX(), this.getY());
        }
    }
}