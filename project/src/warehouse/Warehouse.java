package warehouse;

import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {
  protected static ArrayList<PickingSet> ordersOnHold;
  private String sequencer;
  private String loader;
  private String replenisher;
  protected static ArrayList<PickingSet> ordersInTruck;
  private ArrayList<Picker> pickersList;
  protected static HashMap<Integer, Integer> storage;
  private int pickingsetIndex;
  private PickingSet pickset;
  
  public Warehouse(String sequencer, String loader, String replenisher,
      HashMap<Integer, Integer> storage) {
    this.sequencer = sequencer;
    this.loader = loader;
    this.replenisher = replenisher;
    this.pickersList = new ArrayList<Picker>();
    Warehouse.ordersOnHold = new ArrayList<PickingSet>();
    Warehouse.ordersInTruck = new ArrayList<PickingSet>();
    Warehouse.storage = storage;
    this.pickingsetIndex = 0;
    this.pickset = new PickingSet(this.pickingsetIndex);
  }
  
  public void addOrder(Order order) {    
    this.pickset.addOrder(order);
    
    if (this.pickset.isFull()) {
      Warehouse.ordersOnHold.add(pickset);
      this.pickingsetIndex ++;
      this.pickset = new PickingSet(this.pickingsetIndex);
    }
  }
  
  public Picker assignPicker() {
    for (Picker picker: this.pickersList) {
      if (picker.checkStatus()) {
        return picker;
      }
    }
    return null;
  }
  
  public Picker addPicker(String name) {
    Picker picker = new Picker(name);
    return picker;
  }
  
  public void deliver() {
    System.out.println("In deliver");
  }
}