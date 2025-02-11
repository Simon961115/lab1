public class control {
    public static void main(String[] args){
    Saab95 saab = new Saab95();
    Saab95 saab2 = new Saab95();
    Saab95 saab3 = new Saab95();
    Volvo240 volvo = new Volvo240();
    Volvo240 volvo2 = new Volvo240();
    Volvo240 volvo3 = new Volvo240();
        /*
        Garage<Volvo240> volvoGarage = new Garage<>(2);
        volvoGarage.parkCar(volvo3);
        //volvoGarage.parkCar(saab); Går ej!
        volvoGarage.listParkedCars();

*/
        Workshop<Saab95> saabWorkshop = new Workshop<>(2);
        saabWorkshop.parkCar(saab); // OK
        //saabGarage.parkCar(volvo); Går ej
        saabWorkshop.listParkedCars();
        saabWorkshop.removeCar(saab);
        saabWorkshop.removeCar(saab2);

        Workshop<Vehicle> verkstad = new Workshop<>(5);
        verkstad.parkCar(volvo);
        verkstad.parkCar(saab3);
        verkstad.parkCar(volvo2);
        verkstad.parkCar(saab);
        verkstad.listParkedCars();
        Vehicle nej = verkstad.removeCar(volvo2);
        Vehicle okej = verkstad.removeCar(saab);
        System.out.println(nej);
        System.out.println(okej);
        verkstad.removeCar(saab);
        System.out.println("aaa");
//    saab.startEngine();
//    saab.setCurrentSpeed(12);
//    saab.move();
//    System.out.println(saab.getX() + ", " + saab.getY());
//    saab.setCurrentSpeed(10);
//    saab.turnLeft();
//    saab.move();
//    System.out.println(saab.getX() + ", " + saab.getY());
//
//
   }
}
