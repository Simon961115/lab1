import java.awt.*;

public class Scania extends Car{
    private int loadAngle;
    
    public Scania(){
        super(2,100, Color.gray, "Scania");
        transportable = false;
        loadAngle = 0;
    }
    
    @Override
    double speedFactor() {
        if (loadAngle == 0){    // No speedFactor unless loadAngle is 0.
            return enginePower * 0.01;
        } else {
            return 0;
        }
    }
    
    public int getLoadAngle() {
        return loadAngle;
    }
    public void increaseLoadAngle(int amount){
        if (this.currentSpeed == 0){  // Cant increase loadAngle if truck is in motion.
            loadAngle = Math.min(loadAngle + amount, 70); // Max angle is 70 degrees.
        }
    }
    
    public void decreaseLoadAngle(int amount){
        loadAngle = Math.max(loadAngle - amount, 0); // Min angle is 0 degrees.
    }
}
