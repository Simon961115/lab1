import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PanelImage {
    
    private Point carPoint;
    private BufferedImage image;
    
    public PanelImage(Point pos, String fileName) {
        this.carPoint = pos;
        try {
            this.image = ImageIO.read(CarView.class.getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public BufferedImage getImage() { return image; }
    public Point getPoint() { return carPoint; }
    public int getX() { return carPoint.x; }
    public int getY() { return carPoint.y; }
    public void setPoint(int x, int y) {
        carPoint = new Point(x, y);
    }
}
