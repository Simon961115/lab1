import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class World {

    // measures of the car.jpgs
    private int carPicX = 100;
    private int carPicY = 60;

    //Timer
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());

    static String frameName = "Car simulator";

    CarPanel panel;
    CarView view;

    public static void main(String[] args){

        World world = new World(); // Skapar en instans av sig själv

        world.panel = new CarPanel();// Skapar en modell


        world.view = new CarView(frameName,world);


        world.timer.start();



   }

   void gas (int amount) {
        panel.gas(amount);

   }

    void brake (int amount) {
        panel.brake(amount);

    }

    void setTurbo (boolean turbo) {
        panel.setTurbo(turbo);

    }
    void setLiftAngle (int angle) {
        panel.setLiftAngle(angle);
    }

   void stopEngine () {
        panel.stopEngine();
   }

   void startEngine() {
        panel.startEngine();
   }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : panel.cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                // Checks if car is out of bounds, if so, car is flipped,
                if (    x > 700 - carPicX ||
                        y > 560 - carPicY ||
                        x < 0 || y < 0) {
                    car.turnLeft();
                    car.turnLeft();
                    car.move();
                    x = (int) Math.round(car.getX());
                    y = (int) Math.round(car.getY());
                } else if ( car.getClass() == Volvo240.class && !car.getTransported() &&
                        panel.volvoWorkshop.getCurrentCarsParked() < panel.volvoWorkshop.getMaxCarSpots() &&
                        panel.volvoWorkshop.getX() - carPicX <= car.getX() && panel.volvoWorkshop.getX() + 101 >= car.getX() &&
                        panel.volvoWorkshop.getY() - carPicY <= car.getY() && panel.volvoWorkshop.getY() + 96 >= car.getY()) {
                    car.stopEngine();
                    car.setPosition(panel.volvoWorkshop.getX(),
                            panel.volvoWorkshop.getY() - 10 * (1 + panel.volvoWorkshop.getCurrentCarsParked()));
                    panel.volvoWorkshop.parkCar((Volvo240) car);

                }

                int i = panel.cars.indexOf(car);
                view.drawPanel.moveit(x, y,i);
                // repaint() calls the paintComponent method of the panel
                view.drawPanel.repaint();
            }
        }
    }
}
