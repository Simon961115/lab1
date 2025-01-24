
public class control {

    public static void main(String[] args) {
        new control().program();

    }
    
    public void program() {
        Car car = new Volvo240();
        car.startEngine();

        System.out.println(car.speedFactor());

        car = new Saab95();

        System.out.println(car.speedFactor());





        
    }


}
