package warehouse;

import java.util.ArrayList;

public class PickingSet {
  private ArrayList<Integer> order;
  private int index;
  
  public PickingSet(int index) {
    this.order = new ArrayList<Integer>();
    this.index = index;
  }
  
  public boolean addOrder(Order newOrder) {
    if (!isFull()) {
      int frontSku = newOrder.getSKUfront();
      int backSku = newOrder.getSKUback();
      this.order.add(frontSku);
      this.order.add(backSku);
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isFull() {
    if (this.order.size() >= 8) {
      return true;
    }
    return false;
  }
  
  public ArrayList<Integer> getPickingSet() {
    return this.order;
  }
  
  public int getIndex() {
    return this.index;
  }
}
