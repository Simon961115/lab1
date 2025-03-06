import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    // measures of the car.jpgs
    private int carPicX = 100;
    private int carPicY = 60;



    static String frameName = "Car simulator";

    CarPanel panel;
    CarView view;

    public static void main(String[] args){

        CarPanel panel = new CarPanel();

        CarController controller = new CarController(panel);

        CarView view = new CarView("Car simulator", controller);

        panel.addObserver(view);
        
   }



}
