package warehouse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Task {
  private ArrayList<Integer> orderSequencing;
  private Picker picker;
  
  public Task(Picker picker, PickingSet pickingSet) {
    this.picker = picker;
    this.orderSequencing = pickingSet.getPickingSet();
  }
  
  public ArrayList<Integer> getPickingSet() {
    return this.orderSequencing;
  }

  public void pick() {
    for (int i = 0; i < orderSequencing.size(); i++) {
      this.pickItem(orderSequencing.get(i));
    }
  }

  private void pickItem(Integer integer) {
    
  }

  public void sequence(String sequencer) {
    System.out.println(sequencer + " sequence the next order.");
  }

  public void load(String loader) {
    System.out.println("Loader " + loader + " loading");
  }
  
  public void outputFile() {
    String header = "Zone, Aisle, Rack, Level, Amount";
    HashMap<Integer, Integer> storage = Warehouse.storage;  // storage <sku, quantity>
    HashMap<String, String> traversal = WarehousePicking.map;   // map <sku, location>
    try {
      FileWriter finalOutput = new FileWriter("final.csv");
      finalOutput.append(header.toString() + "\n");
      for (int i = 0; i < storage.size(); i++) {
        String location = traversal.get(((Integer) i).toString());
        for (int j = 0; j < (location.length()); j++) {
          finalOutput.append(location.charAt(j) + ",");
        }
        finalOutput.append(storage.get((Integer) i).toString() + "\n");
      }
      finalOutput.close();
      System.out.println("final.csv generated");
    } catch (IOException except) {
      except.printStackTrace();
    }
  }

  public void checkReplanishStorage(Integer sku) {
    int itemNum = Warehouse.storage.get(sku);
    if (itemNum <= 5) {
      itemNum += 25;
      Warehouse.storage.put(sku, itemNum);
    }
  }
}
