import java.awt.*;

public class Scania extends Car{

    public final static double trimFactor = 0.2;
    private double flakVinkel = 0;
    private final static double maxVinkel = 70.0;

    public Scania(){
        super(2, Color.black, 540, "Scania");
    }

    @Override
    public double speedFactor() {return getEnginePower() * 0.01 * trimFactor; }

    @Override
    public void gas(double amount){ //Overridar gas funktionen från Car klassen och kollar så att falket är nere
        if(flakVinkel == 0) {
            super.gas(amount);
        } else { System.out.println("Släpp ner flaket innan du kör!");}
        //throw new IllegalStateException("Släpp ner flaket innan du kör!");
    }
    public double getflakVinkel(){
        return flakVinkel;
    }
    public void höjFlaket(double grader){ //Höjer flaket gradvis baserat på inputvärde, kastar fel om värdet negativt
        if(grader < 0 || getCurrentSpeed() > 0.1){ //
            System.out.println("Otillåtet värde/Stanna bilen");
        }
        flakVinkel = Math.min(flakVinkel + grader, maxVinkel);
    }
    public void sänkFlaket(double grader){ //Sänker flaket gradvis baserat på inputvärde, kastar fel om värdet negativt
        if(grader < 0 || getCurrentSpeed() > 0.1){
            System.out.println("Otillåtet värde/Stanna bilen");//throw new IllegalArgumentException("Otillåtet värde/Stanna bilen");
        }
        flakVinkel = Math.max(flakVinkel - grader, 0);
    }
}
