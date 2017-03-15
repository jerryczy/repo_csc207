import java.util.ArrayList;

public class PickingSet {
  private ArrayList<Integer> order;
  private int index;
  
  public PickingSet(int index) {
    this.order = new ArrayList<Integer>();
    this.index = index;
  }
  
  /**
   * Add a order to the PickingSet.
   * @param newOrder    A new order.
   * @return    Return true of this pickingSet is not full otherwise, return false.
   */
  public boolean addOrder(Order newOrder) {
    if (!isFull()) {
      int frontSku = newOrder.getSkuFront();
      int backSku = newOrder.getSkuBack();
      this.order.add(frontSku);
      this.order.add(backSku);
      System.out.println("Order Received, frontSKU: " + frontSku + ", backSKU: " + backSku);
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Check if this pickingSet if full. A full pickingSet would contains 4 orders
   *  or 8 fascias (sku numbers).
   * @return return true if there are 8 number of items in this pickingset.
   */
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
