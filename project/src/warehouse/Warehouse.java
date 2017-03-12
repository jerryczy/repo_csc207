package warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Warehouse {
  
  private static String sequencer;
  private static String loader;
  private static String replenisher;
  private static List<Picker> pickerlist;
  private static ArrayList<List<Integer>> ordersOnHold;
  private static ArrayList<List<Integer>> ordersInTruck;
  private static HashMap<String, Integer> storage;
  
  
  public Warehouse(String sequencer, String loader, String replenisher, List<Picker> pickerlist) {
    Warehouse.sequencer = sequencer;
    Warehouse.loader = loader;
    Warehouse.replenisher = replenisher;
    Warehouse.pickerlist = pickerlist;
    Warehouse.ordersOnHold = new ArrayList<List<Integer>>();
    Warehouse.ordersInTruck = new ArrayList<List<Integer>>();
    Warehouse.storage = new HashMap<String, Integer>(96, (float) 1.1);
  }
  
  public void addPickingSet(List<Integer> pickingSet) {
    ordersOnHold.add(pickingSet);
  }
  
  public void assignPicker() {
    for (int i = 0; i < pickerlist.size(); i++) {
      if (pickerlist.get(i).isReady()){
        Task task = new Task(pickerlist.get(i));
      }
    }
  }
  
  public void deliver() {
    
  }
}
