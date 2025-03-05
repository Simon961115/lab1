import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class DrawPanel extends JPanel {
    ArrayList<PanelImage> cars = new ArrayList<>();

    PanelImage volvoWorkshop = new PanelImage(new Point(300,300), "pics/VolvoBrand.jpg");
    PanelImage volvoImage = new PanelImage(new Point(),"pics/Volvo240.jpg" );   // Corresponds to
    PanelImage saabImage = new PanelImage(new Point(), "pics/Saab95.jpg");      // cars added in
    PanelImage scaniaImage = new PanelImage(new Point(), "pics/Scania.jpg");    // CarController

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);



    void moveit(int x, int y, int i){
        cars.get(i).setPoint(x, y);
    }

    public DrawPanel() {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(800, 560));
        this.setBackground(Color.green);
        cars.add(volvoImage);
        cars.add(saabImage);
        cars.add(scaniaImage);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // see javadoc for more info on the parameters
        g.drawImage(volvoWorkshop.getImage(), volvoWorkshop.getX(), volvoWorkshop.getY(), null);
        for (PanelImage car : cars) {
            g.drawImage(car.getImage(), car.getX(), car.getY(), null);
        }

    }

}
