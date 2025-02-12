import java.awt.*;

public class Volvo240 extends Vehicle {

    public final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, Color.black, 100, "Volvo240");
    }

    @Override //Accelarations varibel specifik för Volvo
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
