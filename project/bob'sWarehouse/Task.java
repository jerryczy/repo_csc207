import java.util.ArrayList;

public class Task {
  private ArrayList<Integer> orderSequencing;
  private Picker picker;
  
  public Task(Picker picker, PickingSet pickingset) {
    this.picker = picker;
    this.orderSequencing = pickingset.getPickingSet();
  }
  
  public ArrayList<Integer> getPickingSet () {
    return this.orderSequencing;
  }
  
  public ArrayList optimize(ArrayList<Integer> skus) {
    return this.orderSequencing;
  }
  
  public void sequence() {
    
  }
  
  public void load() {
    
  }
  
  public void checkReplanishStorage(Integer sku) {
    int itemNum = Warehouse.storage.get(sku);
    if (itemNum <= 5) {
      itemNum += 25;
      Warehouse.storage.put(sku, itemNum);
    }
  }
  
}
