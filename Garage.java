import java.util.List;
import java.util.LinkedList;
public class Garage<T extends Car> {

    private double x, y;
    private final Load loadedCar;

    public Garage(int maxCarSpots){
        if (maxCarSpots <= 0) {
            throw new IllegalArgumentException("Antalet parkeringsplatser måste vara större än 0.");
        }
        loadedCar = new Load(maxCarSpots);
        this.x = 0; //Temporära koordinater för garaget, kan tillföras som parameter om så behövs.
        this.y = 0;
    }

    public void parkCar(T car){
        if(loadedCar.getLoadedCars().size() < loadedCar.getMaxCars() && car.getTransportable() &&
                1 >= Math.sqrt(Math.pow(this.x - car.getX(), 2) + Math.pow(this.y - car.getY(), 2))){
            loadedCar.loadCar(car);
            car.setTransported(true);
        }
    }
    public T removeCar(T car) {
        for (int i = 0; i < loadedCar.getLoadedCars().size(); i++) {
            if (car.equals(loadedCar.getLoadedCars().get(i))) {
                T tmpCar = (T) loadedCar.unloadCar(i);
                car.setTransported(false);
                car.setDirection(Car.Directions.NORTH);
                car.setPosition(this.x + 1, this.y + 1);
                carModelRemoved(car);
                return tmpCar;
            }
        }
        System.out.println("Billen finns inte på verkstaden!");
        return null;
    }

    public void listParkedCars() {
        if (loadedCar.getLoadedCars().isEmpty()) {
            System.out.println("Inga bilar är parkerade.");
            return;
        }
        System.out.println("Parkerade bilar:");
        for (int i = 0; i < loadedCar.getLoadedCars().size(); i++) {
            System.out.println(i + ": " + loadedCar.getLoadedCars().get(i).getModelName());
        }
    }
    private void carModelRemoved(T car){
        System.out.println(car.getModelName() + " Har körts ut ur garaget!");
    }
}
