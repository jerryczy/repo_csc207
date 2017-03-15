import java.util.List;

public class Task {
  private PickingSet orderSequencing;
  private Picker picker;
  private List<String> optimizedSequencing;
  
  public Task(Picker picker) {
    this.picker = picker;
  }
  
  public Picker getPicker() {
    return this.picker;
  }
  
  /**
   * Print the PickingSet number and item location as well as check the storage of that item.
   * @param pickingIndex    a PickingSet number.
   * @param replenisher     The name of a replenisher.
   */
  public void getoptimizedlocation(int pickingIndex, String replenisher) {
    String location = this.optimizedSequencing.get(pickingIndex - 1);
    String output = this.picker.getName() + " pick " + pickingIndex + ", location: " + location;
    System.out.println(output);
    pickFromStroage(location);
    checkReplanishStorage(location, replenisher);
    
  }
  
  public void updateOrderSequencing(PickingSet pickingset) {
    this.orderSequencing = pickingset;
    this.optimizedSequencing = WarehousePicking.optimize(this.orderSequencing.getPickingSet());
  }
  
  /**
   * Move a complete items of orderSet to marshal.
   */
  public void toMarshaling() {
    Warehouse.ordersSequencing.add(this.orderSequencing);
    this.orderSequencing = null;
    String output = this.picker.getName() + " to Marshaling";
    System.out.println(output);
  }
  
  private void checkReplanishStorage(String location, String replenisher) {
    int itemNum = Warehouse.storage.get(location);
    System.out.println("location: " + location + ": " + itemNum);
    if (itemNum <= 8) {
      System.out.println(location + " need replanish !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      itemNum += 25;
      Warehouse.storage.put(location, itemNum);
      System.out.println(replenisher + " replenished item at " + location);
    }
  }
  
  private void pickFromStroage(String location) {
    int itemNum = Warehouse.storage.get(location);
    itemNum--;
    Warehouse.storage.put(location, itemNum);
  }
  
}
