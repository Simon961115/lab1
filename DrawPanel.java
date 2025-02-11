import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    //BufferedImage volvoImage;
    //BufferedImage saabImage;
    //BufferedImage scaniaImage;
    // To keep track of a single car's position
    ArrayList<PanelImage> cars = new ArrayList<>();
    
    PanelImage volvoWorkshop = new PanelImage(new Point(300,300), "pics/VolvoBrand.jpg");
    PanelImage volvoImage = new PanelImage(new Point(),"pics/Volvo240.jpg" );   // Corresponds to
    PanelImage saabImage = new PanelImage(new Point(), "pics/Saab95.jpg");      // cars added in
    PanelImage scaniaImage = new PanelImage(new Point(), "pics/Scania.jpg");    // CarController main
    
    
    
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);
    

    // TODO: Make this general for all cars
    void moveit(int x, int y, int i){
        cars.get(i).setPoint(x, y);
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        cars.add(volvoImage);
        cars.add(saabImage);
        cars.add(scaniaImage);
        // Print an error message in case file is not found with a try/catch block
//        try {
//            // You can remove the "pics" part if running outside of IntelliJ and
//            // everything is in the same main folder.
//            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));
//
//            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
//            // if you are starting in IntelliJ.
//            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
//            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
//            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
//            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
//
//        } catch (IOException ex)
//        {
//            ex.printStackTrace();
//        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // see javadoc for more info on the parameters
        g.drawImage(volvoWorkshop.getImage(), volvoWorkshop.getX(), volvoWorkshop.getY(), null);
        for (PanelImage car : cars) {
            g.drawImage(car.getImage(), car.getX(), car.getY(), null);
        }
        
    }
}
