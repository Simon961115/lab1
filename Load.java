import java.util.LinkedList;

public class Load {
    private final int maxSize;
    private LinkedList<Car> cars;
    
    public Load(int maxSize) {
        this.maxSize = maxSize;
        cars = new LinkedList<>();
    }
    
    public void loadCar(Car car) {
        if (cars.size() < maxSize && !car.getTransported()) {
            car.setTransported(true);
            cars.add(car);
        }
    }
    
    // If we want to make a general load method that works for all LinkedLists
//    public void load(LinkedList<Object> objects, Object obj) {
//        if (objects.size() < maxSize) {
//            objects.add(obj);
//        }
//    }
    
    public Car unloadCar(int index) {
        try {
            Car car = cars.remove(index);
            car.setTransported(false);
            return car;
        } catch (Exception e) {
            System.out.println("Index out of bounds");
            return null;
        }
    }
    
    public int getCarsSize() {
        return cars.size();
    }
    public LinkedList<Car> getCars() {
        return cars;
    }
    
}
