public class Workshop<T extends Vehicle> {

    private double x, y;
    private final Container loadedCar;

    public Workshop(int maxCarSpots){
        if (maxCarSpots <= 0) {
            throw new IllegalArgumentException("Antalet parkeringsplatser måste vara större än 0.");
        }
        loadedCar = new Container(maxCarSpots);
        this.x = 0; //Temporära koordinater för garaget, kan tillföras som parameter om så behövs.
        this.y = 0;
    }

    public void parkCar(T car){
        if(loadedCar.getLoadedItem().size() < loadedCar.getMaxItems() && car.getTransportable() && //Bil ska vara nära ingång
                1 >= Math.sqrt(Math.pow(this.x - car.getX(), 2) + Math.pow(this.y - car.getY(), 2))){
            loadedCar.loadItem(car); //Använder Loads, loadCard metod.
            car.setTransported(true);
        }
    }
    public T removeCar(T car) {
        for (int i = 0; i < loadedCar.getLoadedItem().size(); i++) {
            if (car.equals(loadedCar.getLoadedItem().get(i))) {
                T tmpCar = (T) loadedCar.unloadItem(i);
                car.setTransported(false);
                car.setDirection(Vehicle.Directions.NORTH);
                car.setPosition(this.x + 1, this.y + 1);
                carModelRemoved(car);
                return tmpCar;
            }
        }
        System.out.println("Billen finns inte på verkstaden!");
        return null;
    }

    public void listParkedCars() {
        if (loadedCar.getLoadedItem().isEmpty()) {
            System.out.println("Inga bilar är parkerade.");
            return;
        }
        System.out.println("Parkerade bilar:");
        for (int i = 0; i < loadedCar.getLoadedItem().size(); i++) {
            Vehicle vehicle = (Vehicle) loadedCar.getLoadedItem().get(i);
            System.out.println(i + ": " + vehicle.getModelName());
        }
    }
    private void carModelRemoved(T car){
        System.out.println(car.getModelName() + " Har körts ut ur garaget!");
    }
}
