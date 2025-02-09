import java.awt.*;

import java.util.LinkedList;

public class Load {
    private final int maxCars;
    private final LinkedList<Car> loadedCars;

    public Load(int maxCars){
        this.maxCars = maxCars;
        loadedCars = new LinkedList<>();
    }

    public void loadCar(Car loadCar){
        if(maxCars > loadedCars.size()) {
            loadCar.setTransported(true);
            loadedCars.addLast(loadCar);
        }
    }

    public Car unloadCar(int index){
            Car tmpCar = loadedCars.remove(index);
            tmpCar.setTransported(false);
            return tmpCar;
    }

    public LinkedList<Car> getLoadedCars(){
        return this.loadedCars;

    }

    public int getMaxCars(){
        return this.maxCars;
    }
}
