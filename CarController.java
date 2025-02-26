import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarController extends JFrame{
    private static final int X = 700;
    private static final int Y = 700;


    // measures of the car.jpgs
    private int carPicX = 100;
    private int carPicY = 60;

    String framename = "din mamma";

    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());

    // The controller member
    CarPanel carP;

    CarView drawPanel = new CarView(X, Y-240);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarController(String framename){
        this.carP = new CarPanel();
        initComponents(framename);
    }




    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController("CarSim 1.0");

        cc.timer.start();

    }



    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : carP.cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                // Checks if car is out of bounds, if so, car is flipped,
                if (    x > 700 - carPicX ||
                        y > 460 - carPicY ||
                        x < 0 || y < 0) {
                    car.turnLeft();
                    car.turnLeft();
                    car.move();
                    x = (int) Math.round(car.getX());
                    y = (int) Math.round(car.getY());
                } else if ( car.getClass() == Volvo240.class && !car.getTransported() &&
                        carP.volvoWorkshop.getCurrentCarsParked() < carP.volvoWorkshop.getMaxCarSpots() &&
                        carP.volvoWorkshop.getX() - carPicX <= car.getX() && carP.volvoWorkshop.getX() + 101 >= car.getX() &&
                        carP.volvoWorkshop.getY() - carPicY <= car.getY() && carP.volvoWorkshop.getY() + 96 >= car.getY()) {
                    car.stopEngine();
                    car.setPosition(carP.volvoWorkshop.getX(),
                            carP.volvoWorkshop.getY() - 10 * (1 + carP.volvoWorkshop.getCurrentCarsParked()));
                    carP.volvoWorkshop.parkCar((Volvo240) car);

                }

                int i = carP.cars.indexOf(car);
                drawPanel.moveit(x, y, i);
                // repaint() calls the paintComponent method of the panel
                drawPanel.repaint();
            }
        }
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carP.gas(gasAmount);
            }
        });

        // Adds functionality for the break button.
        brakeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               carP.brake(gasAmount);
           }
        });
        
        // Adds functionality to the turbo buttons.
        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carP.setTurbo(true);
            }
        });
        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carP.setTurbo(false);
            }
        });
        
        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carP.setLiftAngle(70);
            }
        });
        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carP.setLiftAngle(-70);
            }
        });
        
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carP.stopEngine();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carP.startEngine();
            }
        });
        
        // Make the frame pack all it's components by respecting the sizes if possible.
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