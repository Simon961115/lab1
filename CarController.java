import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarController extends JPanel{

    private int screenWidth = 700;
    private int screenHeight = 560;

    private int carPicX = 100;
    private int carPicY = 60;

    //Timer
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());

    //?
    private static final int X = 800;

    //private World world; // De bilar som controllern styr
    private CarPanel panel;

    //Knappgrejer
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





    public CarController(CarPanel panel){
        this.panel = panel;
        this.initComponents("Controller");
        timer.start();
    }


    private void initComponents(String title) {
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
                panel.gas(gasAmount);

            }
        });

        // Adds functionality for the break button.
        brakeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               panel.brake(gasAmount);
           }
        });
        
        // Adds functionality to the turbo buttons.
        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setTurbo(true);
            }
        });
        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setTurbo(false);
            }
        });
        
        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setLiftAngle(70);
            }
        });
        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                panel.setLiftAngle(-70);
            }
        });
        
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.stopEngine();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.startEngine();
            }
        });

    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (panel == null) return;
            for (Vehicle car : panel.cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                // Checks if car is out of bounds, if so, car is flipped,
                if (    x > screenWidth - carPicX ||
                        y > screenHeight - carPicY ||
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
                panel.updateCarPos(x, y,i);
                // repaint() calls the paintComponent method of the panel

            }
            panel.update();
        }
    }

}