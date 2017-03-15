import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {
  protected static ArrayList<PickingSet> ordersOnHold;
  protected static ArrayList<PickingSet> ordersInTruck;
  protected static ArrayList<PickingSet> ordersSequencing;
  protected static ArrayList<PickingSet> ordersLoading;
  private ArrayList<Picker> pickersList;
  protected static HashMap<String, Integer> storage;
  private int pickingsetIndex;
  private PickingSet pickset;
  private ArrayList<Task> taskList;
  
  /**
   * Create a warehouse.
   * @param storage     A hashMap that contains all all the item in the warehouse.
   * @param pickers     A list of pickers this warehouse currently hired.
   */
  public Warehouse(HashMap<String, Integer> storage, ArrayList<Picker> pickers) {
    this.pickersList = pickers;
    ordersOnHold = new ArrayList<PickingSet>();
    ordersInTruck = new ArrayList<PickingSet>();
    for (int i = 0;i < 10; i++) {
      ordersInTruck.add(i, null);
    }
    ordersSequencing = new ArrayList<PickingSet>();
    ordersLoading = new ArrayList<PickingSet>();
    Warehouse.storage = storage;
    this.pickingsetIndex = 0;
    this.pickset = new PickingSet(this.pickingsetIndex);
    taskList = new ArrayList<Task>();
    for (Picker picker: pickers) {
      Task task = new Task(picker);
      this.taskList.add(task);
    }
  }
  
  /**
   * @param name    The name if a picker.
   * @return    Return the task a specific picker having.
   */
  public Task getTask(String name) {
    for (Task task: this.taskList) {
      if (task.getPicker().getName().equals(name)) {
        return task;
      }
    }
    return null;
  }
  
  /**
   * Add a order to the warehouse system.
   * @param order    A new order.
   */
  public void addOrder(Order order) {    
    this.pickset.addOrder(order);
    
    if (this.pickset.isFull()) {
      
      Warehouse.ordersOnHold.add(pickset);
      this.pickingsetIndex ++;
      this.pickset = new PickingSet(this.pickingsetIndex);
    }
  }
  
  /**
   * Update the Pickers status to a desire one.
   * @param name    The name of this picker.
   * @param status  A pickers status, use true if the picker is ready, otherwise false.
   */
  public void updatePickerStatus(String name, boolean status) {
    for (Picker picker: this.pickersList) {
      if (picker.getName().equals(name)) {
        picker.setStatus(status);
      }
    }
  }
  
  /**
   * Check a pickers current status
   * @param name    The name of this picker.
   * @return    Return true if this Picker is ready, ie has no works to do.
   */
  public boolean checkPickerSatus(String name) {
    for (Picker picker: this.pickersList) {
      if (picker.getName().equals(name)) {
        return picker.checkStatus();
      }
    }
    return false;
  }
  
  /**
   * Return the desire Picker object.
   * @param name    A picker's name.
   * @return    A picker object corresponding to the name.
   */
  public Picker getPicker(String name) {
    for (Picker picker: this.pickersList) {
      if (picker.getName().equals(name)) {
        return picker;
      }
    }
    return null;
  }
  
  /**
   * Return the next order and deleted from the ArrayList ordersOnHold.
   * @return    Return a order if there is any in the ordersOnHold. Otherwise return null.
   */
  public PickingSet getOrderOnHold() {
    if (ordersOnHold.size() > 0) {
      PickingSet orders = ordersOnHold.get(0);
      ordersOnHold.remove(0);
      return orders;
    } else {
      return null;
    }
  }
  
  /**
   * Find a ready picker.
   * @return    Return a Picker object if there is one picker ready, return null otherwise.
   */
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
  
  /**
   * Load a pickingSet of orders on to the truck.
   * @param loader  The loader's name.
   */
  public void load(String loader) {
    PickingSet pickingset = ordersLoading.get(0);
    ordersInTruck.set(pickingset.getIndex(), pickingset);
    ordersLoading.remove(0);
    System.out.println(loader + "loading order#: " + pickingset.getIndex());
    for (int sku: pickingset.getPickingSet()) {
      System.out.println(sku);
    }
  }
  
  /**
   * Sequence a pickingSet of orders.
   * @param sequencer  The sequencer's name.
   */
  public void sequence(String sequencer) {
    
    System.out.println("Sequencer " + sequencer + " sequences");
    PickingSet pickingset = ordersSequencing.get(0);
    ordersLoading.add(pickingset);
    ordersSequencing.remove(0);
    for (int sku: pickingset.getPickingSet()) {
      System.out.println("sku: " + sku);
    }
    
    try {
      WriteOrdersTruck.writeorders(pickingset);
    } catch (IOException except) {
      except.printStackTrace();
    }
  }
  
  /**
   * Deliver the truck. Truck deliver if and only if the truck is full.
   * @return Return true if the truck is delivered.
   */
  public boolean deliver() {
    for (PickingSet pickingset: ordersInTruck) {
      if (pickingset == null) {
        return false;
      }
    }
    
    System.out.println("In delivering following 40 orders");
    for (PickingSet pickingset: ordersInTruck) {
      System.out.println("order: " + pickingset.getIndex());
      for (int sku: pickingset.getPickingSet()) {
        System.out.println(sku);
      }
    }
    
    ReadWriteStorage.writestorage(storage);
    
    return true;
    
    
  }
  
  
  
  
}
