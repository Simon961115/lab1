import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayList;

// This panel represents the animated part of the view with the car images.

public class CarView extends JFrame {
    private static final int X = 700;
    private static final int Y = 700;

    CarController controller;
    DrawPanel drawPanel;



    ArrayList<PanelImage> cars = new ArrayList<>();
    
    PanelImage volvoWorkshop = new PanelImage(new Point(300,300), "pics/VolvoBrand.jpg");
    PanelImage volvoImage = new PanelImage(new Point(),"pics/Volvo240.jpg" );   // Corresponds to
    PanelImage saabImage = new PanelImage(new Point(), "pics/Saab95.jpg");      // cars added in
    PanelImage scaniaImage = new PanelImage(new Point(), "pics/Scania.jpg");    // CarController main
    
    
    
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);
    


    public CarView(String framename, CarController controller,DrawPanel drawpanel) {
        this.controller = controller;
        this.drawPanel = drawpanel;

        initComponents(framename);


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
