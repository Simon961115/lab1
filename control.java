public class control {
    public static void main(String[] args){
    Saab95 saab = new Saab95();
    Volvo240 volvo = new Volvo240();
    System.out.println(saab.getNrDoors());
    System.out.println(volvo.getEnginePower());

    saab.startEngine();
    saab.setCurrentSpeed(12);
    saab.move();
    System.out.println(saab.getX() + ", " + saab.getY());
    saab.setCurrentSpeed(10);
    saab.turnLeft();
    saab.move();
    System.out.println(saab.getX() + ", " + saab.getY());


    }
}
