
public class control {

    public static void main(String[] args) {
        new control().program();

    }
    
    public void program() {
        Car car = new Volvo240();
        car.startEngine();
        System.out.println(car.getCurrentSpeed());

        for(int i = 0; i < 5;i++) {
            car.incrementSpeed(0.2);
            System.out.println(car.getCurrentSpeed());
        }





        
    }


}
