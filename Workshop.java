public class Workshop <T extends Car> {
    private Load load;
    private int maxCars;
    
    public Workshop(int maxCars) {
        load = new Load(maxCars);
    }
    
    public void loadCar(T car) {
            load.loadCar(car);
    }
    
    public T unloadCar(int index) {
        return (T) load.unloadCar(index);
    }
    
    public T unloadCar() {
        return (T) load.unloadCar(0);
    }
}
