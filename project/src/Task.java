import java.util.ArrayList;
import java.util.List;

public class Task {
  private PickingSet orderSequencing;
  private Picker picker;
  private WarehousePicking warehousepicking;
  private List<String> optimizedSequencing;
  
  public Task(Picker picker) {
    this.picker = picker;
  }
  
  public Picker getPicker() {
    return this.picker;
  }
  
  public void getoptimizedlocation(int pickingIndex) {
    String location = this.optimizedSequencing.get(pickingIndex-1);
    String output = this.picker.getName() + " pick " + pickingIndex + ", location: " + location;
    System.out.println(output);
    pickFromStroage(location);
    checkReplanishStorage(location);
    
  }
  
  public void updateOrderSequencing(PickingSet pickingset) {
    this.orderSequencing = pickingset;
    this.optimizedSequencing = WarehousePicking.optimize(this.orderSequencing.getPickingSet());
  }
  
  public void toMarshaling() {
    Warehouse.ordersSequencing.add(this.orderSequencing);
    this.orderSequencing = null;
    String output = this.picker.getName() + " to Marshaling";
    System.out.println(output);
  }
  
  public void checkReplanishStorage(String location) {
    int itemNum = Warehouse.storage.get(location);
    System.out.println("location: " + location + ": " + itemNum);
    if (itemNum <= 8) {
      System.out.println(location + " need replanish !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      itemNum += 25;
      Warehouse.storage.put(location, itemNum);
    }
  }
  
  public void pickFromStroage(String location) {
    int itemNum = Warehouse.storage.get(location);
    itemNum--;
    Warehouse.storage.put(location, itemNum);
  }
  
}
