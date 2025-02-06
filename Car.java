import java.awt.*;

public abstract class Car implements Movable{
    
    private final int nrDoors; // Number of doors on the car
    final double enginePower; // Engine power of the car
    double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name
    
    public enum Directions {NORTH, EAST, SOUTH, WEST}
    private Directions currentDirection;
    private double x, y; // Position on x-/y-axis
    
    private final boolean transportable; // If car is transportable in a car transport
    private boolean transported; // If car is currently being transported.
    
    
    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentDirection = Directions.NORTH;
        this.transportable = true;
        this.transported = false;
        stopEngine();
    }
    
    public Car(int nrDoors, double enginePower, Color color, String modelName, boolean transportable) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentDirection = Directions.NORTH;
        this.transportable = transportable;
        this.transported = false;
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
    
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color clr){
        color = clr;
    }
    
    public void startEngine(){
        currentSpeed = 0.1;
    }
    
    public void stopEngine(){
        currentSpeed = 0;
    }
    
    abstract double speedFactor();
    
    private void incrementSpeed(double amount){
        this.setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower));
    }
    
    private void decrementSpeed(double amount){
        this.setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }
    
    public void gas(double amount){
        incrementSpeed(Math.clamp(amount,0,1));
    }
    
    public void brake(double amount){
        decrementSpeed(Math.clamp(amount,0,1));
    }
    
    private void setCurrentSpeed(double currentSpeed){
        if(currentSpeed <= 0){this.currentSpeed = 0;}
        else this.currentSpeed = Math.min(currentSpeed, enginePower);
    }
    
    @Override
    public void move() {
        switch (currentDirection) {
            case EAST -> x += currentSpeed; // move east
            case SOUTH -> y -= currentSpeed; // move south
            case WEST -> x -= currentSpeed; // move west
            case NORTH -> y += currentSpeed; // move north
        }
    }
    
    @Override
    public void turnRight() {
        switch (currentDirection){
            case NORTH -> currentDirection = Directions.EAST;
            case EAST -> currentDirection = Directions.SOUTH;
            case SOUTH -> currentDirection = Directions.WEST;
            case WEST -> currentDirection = Directions.NORTH;
        }
    }
    
    @Override
    public void turnLeft() {
        switch (currentDirection){
            case NORTH -> currentDirection = Directions.WEST;
            case EAST -> currentDirection = Directions.NORTH;
            case SOUTH -> currentDirection = Directions.EAST;
            case WEST -> currentDirection = Directions.SOUTH;
        }
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    protected void setPosition(double x, double y){ // Needed for CarTransport, maybe not good?
        this.x = x;
        this.y = y;
    }
    
    public Directions getCurrentDirection(){
        return currentDirection;
    }
    protected void setDirection(Directions dir){
        this.currentDirection = dir;
    }
    
    public boolean getTransportable(){
        return transportable;
    }
    public boolean getTransported(){
        return transported;
    }
    public void setTransported(boolean transported){
        this.transported = transported;
    }
}
