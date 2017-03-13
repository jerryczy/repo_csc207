package warehouse;

import java.util.ArrayList;

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
    System.out.println("Sequencer " + sequencer + " sequenceing.");
  }

  public void load(String loader) {
    System.out.println("Loader " + loader + " loading.");
  }

  public void checkReplanishStorage(Integer sku) {
    int itemNum = Warehouse.storage.get(sku);
    if (itemNum <= 5) {
      itemNum += 25;
      Warehouse.storage.put(sku, itemNum);
    }
  }
  
}
