import java.awt.*;

public abstract class Car implements Movable {
    private enum Directions{NORTH, EAST, WEST, SOUTH}

    private Directions currentDirection;
    private double x, y;

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    public Car (int nDoors, Color color,double enginePower, String modelName) {
        this.nrDoors = nDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.currentDirection = Directions.NORTH;
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed){
        this.currentSpeed = currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine () {
        this.currentSpeed = 0;
    }

    public void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower()));
    }

    public void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }

    public abstract double speedFactor();

    @Override
    public void move() {
        switch (currentDirection){
            case NORTH -> y+= currentSpeed;
            case EAST -> x+= currentSpeed;
            case WEST -> x-= currentSpeed;
            case SOUTH -> y-= currentSpeed;
        }

    }

    @Override
    public void turnLeft() {
        switch (currentDirection){
            case NORTH -> currentDirection = Directions.WEST;
            case EAST -> currentDirection = Directions.NORTH;
            case WEST -> currentDirection = Directions.SOUTH;
            case SOUTH -> currentDirection = Directions.EAST;
        }

    }

    @Override
    public void turnRight() {
        switch (currentDirection){
            case NORTH -> currentDirection = Directions.EAST;
            case EAST -> currentDirection = Directions.SOUTH;
            case WEST -> currentDirection = Directions.NORTH;


        }

    }
}