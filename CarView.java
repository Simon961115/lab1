import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

// This panel represents the animated part of the view with the car images.

public class CarView extends JFrame implements Observer {
    private static final int X = 800;
    private static final int Y = 800;

    //World world; // Världen som view representerar

    //CarPanel panel;
    CarController controller;
    DrawPanel drawPanel;



    ArrayList<PanelImage> cars = new ArrayList<>();



    public CarView(String framename,CarController controller) {

        //this.panel = panel;
        this.controller = controller;
        drawPanel = new DrawPanel();
        initComponents(framename);
        
    }

    @Override
    public void moveIt(int x, int y, int i) {
        drawPanel.moveit(x,y,i);
    }

    public void repaint() {
        drawPanel.repaint();
    }
    
    @Override
    public void addCar(String image) {
        drawPanel.addCar(image);
    }
    
    @Override
    public void removeCar(){
        drawPanel.cars.remove(drawPanel.cars.size()-1);
    }

    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);
        this.add(controller);

        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
