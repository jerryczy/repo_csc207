import java.util.ArrayList;
import java.util.List;

public class PickingSet {
  private ArrayList<Integer> order;
  private int index;
  
  public PickingSet(int index) {
    this.order = new ArrayList<Integer>();
    this.index = index;
  }
  
  public boolean addOrder(Order newOrder) {
    if (!isFull()) {
      int frontSKU = newOrder.getSKUfront();
      int backSKU = newOrder.getSKUback();
      this.order.add(frontSKU);
      this.order.add(backSKU);
      System.out.println("Order Received, frontSKU: "+frontSKU+", backSKU: "+backSKU);
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
