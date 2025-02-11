import java.util.LinkedList;

public class Container<T> {
    private final int maxItems;
    private final LinkedList<T> loadedItem;

    public Container(int maxItems){
        this.maxItems = maxItems;
        loadedItem = new LinkedList<>();
    }

    public void loadItem(T loadItem){
        if(maxItems > loadedItem.size()) {
            loadedItem.addLast(loadItem);

        }
    }

    public T unloadItem(int index){
            T tmpItem = loadedItem.remove(index);
            return tmpItem;
    }

    public LinkedList<T> getLoadedItem(){
        return this.loadedItem;

    }

    public int getMaxItems(){
        return this.maxItems;
    }
}
