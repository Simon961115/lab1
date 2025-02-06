import java.awt.*;

public abstract class Car implements Movable {
    public enum Directions{NORTH, EAST, WEST, SOUTH}

    private Directions currentDirection;
    private double x, y;
    //private CarTransport transport;

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    private final boolean transportable; // If car is transportable in a car transport
    private boolean transported; // If car is currently being transported.

    public Car (int nrDoors, Color color,double enginePower, String modelName) {
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

    private void setCurrentSpeed(double currentSpeed){
        if(currentSpeed <= 0){this.currentSpeed = 0;}
        else if(currentSpeed >= this.enginePower){
            this.currentSpeed = this.enginePower;
        }
        else this.currentSpeed = currentSpeed;
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

    private void incrementSpeed(double amount){ //Används endast av gas
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower()));
    }

    private void decrementSpeed(double amount){ //Används endast av brake
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }

    public void gas(double amount){ //Ger fart, inom värdet 0-1 så att gas inte bromsar
        if(amount <= 0) {
            incrementSpeed(0);
        }
        else if (amount >= 1){
            incrementSpeed(1);
        }
        else incrementSpeed(amount);
    }

    public void brake(double amount){ //Saktar ner farten, håller sig inom 0-1.
        if(amount <= 0) {
            decrementSpeed(0);
        }
        else if (amount >= 1){
            decrementSpeed(1);
        }
        else decrementSpeed(amount);

    }

    public abstract double speedFactor(); //Abstract baseras på specifik modellklass

    @Override
    public void move() { //Flyttar bilens x,y koordinater baserat på riktning och fart
        switch (currentDirection){
            case NORTH -> y+= currentSpeed;
            case EAST -> x+= currentSpeed;
            case WEST -> x-= currentSpeed;
            case SOUTH -> y-= currentSpeed;
        }

    }

    @Override
    public void turnLeft() { //svänger 90 grader baserat på riktning
        switch (currentDirection){
            case NORTH -> currentDirection = Directions.WEST;
            case EAST -> currentDirection = Directions.NORTH;
            case WEST -> currentDirection = Directions.SOUTH;
            case SOUTH -> currentDirection = Directions.EAST;
        }

    }

    @Override
    public void turnRight() { //svänger 90 grader baserat på riktning
        switch (currentDirection){
            case NORTH -> currentDirection = Directions.EAST;
            case EAST -> currentDirection = Directions.SOUTH;
            case WEST -> currentDirection = Directions.NORTH;
            case SOUTH -> currentDirection = Directions.WEST;

        }

    }

    public double getX() { //Skickar ut x koordinat
        return x;
    }

    public double getY() { //Skickar ut y koordinat
        return y;
    }

    public void setPos(double x,double y) {
        this.x = x;
        this.y = y;
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