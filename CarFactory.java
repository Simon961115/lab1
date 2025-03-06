import java.util.Random;

public class CarFactory {
    
    private static final Random rand = new Random();
    
    public static Volvo240 createVolvo () {
        return new Volvo240();
    }
    
    public static Saab95 createSaab () {
        return new Saab95();
    }
    
    public static Scania createScania () {
        return new Scania();
    }
    
    public static Vehicle createRandomCar () {
        
        return switch (rand.nextInt(0, 3)) {
            case 0 -> new Volvo240();
            case 1 -> new Saab95();
            case 2 -> new Scania();
            default -> new Volvo240();
        };
        
    }
    
    
}