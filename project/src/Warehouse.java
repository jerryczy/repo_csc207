import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {
  protected static ArrayList<PickingSet> ordersOnHold;
  private String sequencer;
  private String loader;
  private String replenisher;
  protected static ArrayList<PickingSet> ordersInTruck;
  protected static ArrayList<PickingSet> ordersSequencing;
  protected static ArrayList<PickingSet> ordersLoading;
  private ArrayList<Picker> pickersList;
  protected static HashMap<String, Integer> storage;
  private int pickingsetIndex;
  private PickingSet pickset;
  private ArrayList<Task> taskList;
  
  public Warehouse(String sequencer, String loader, String replenisher, HashMap<String, Integer> storage, ArrayList<Picker> pickers) {
    this.sequencer = sequencer;
    this.loader = loader;
    this.replenisher = replenisher;
    this.pickersList = pickers;
    ordersOnHold = new ArrayList<PickingSet>();
    ordersInTruck = new ArrayList<PickingSet>();
    for (int i=0;i<10;i++){
      ordersInTruck.add(i, null);
    }
    ordersSequencing = new ArrayList<PickingSet>();
    ordersLoading = new ArrayList<PickingSet>();
    this.storage = storage;
    this.pickingsetIndex = 0;
    this.pickset = new PickingSet(this.pickingsetIndex);
    taskList = new ArrayList<Task>();
    for (Picker picker: pickers){
      Task task = new Task(picker);
      this.taskList.add(task);
    }
  }
  
  public Task getTask(String name){
    for (Task task: this.taskList){
      if (task.getPicker().getName().equals(name)){
        return task;
      }
    }
    return null;
  }
  
  public void addOrder(Order order) {    
    this.pickset.addOrder(order);
    
    if (this.pickset.isFull()) {
      
      this.ordersOnHold.add(pickset);
      this.pickingsetIndex ++;
      this.pickset = new PickingSet(this.pickingsetIndex);
    }
  }
  
  public void updatePickerStatus(String name, boolean status) {
    for (Picker picker: this.pickersList) {
      if (picker.getName().equals(name)) {
        picker.setStatus(status);
      }
    }
  }
  
  public boolean checkPickerSatus(String name) {
    for (Picker picker: this.pickersList) {
      if (picker.getName().equals(name)) {
        return picker.checkStatus();
      }
    }
    return false;
  }
  
  public Picker getPicker(String name) {
    for (Picker picker: this.pickersList) {
      if (picker.getName().equals(name)) {
        return picker;
      }
    }
    return null;
  }
  
  public PickingSet getOrderOnHold() {
    if (ordersOnHold.size() > 0) {
      PickingSet orders = ordersOnHold.get(0);
      ordersOnHold.remove(0);
      return orders;
    } else {
      return null;
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
  
  public void load() {
    PickingSet pickingset = ordersLoading.get(0);
    ordersInTruck.set(pickingset.getIndex(), pickingset);
    ordersLoading.remove(0);
    System.out.println("loading order#: "+ pickingset.getIndex());
    for (int sku: pickingset.getPickingSet()){
      System.out.println(sku);
    }
  }
  
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
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public boolean deliver() {
    for (PickingSet pickingset: ordersInTruck){
      if (pickingset == null){
        return false;
      }
    }
    
    System.out.println("In delivering following 40 orders");
    for(PickingSet pickingset: ordersInTruck){
      System.out.println("order: "+pickingset.getIndex());
      for (int sku: pickingset.getPickingSet()){
        System.out.println(sku);
      }
    }
    
    ReadWriteStorage.writestorage(storage);
    
    return true;
    
    
  }
  
  
  
  
}
