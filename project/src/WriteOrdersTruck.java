import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteOrdersTruck {
  
  /**
   * Produce a output file called orders.csv which contains the items in the truck.
   */
  public static void initialFinalOrders() {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new File("orders.csv"));
    } catch (FileNotFoundException except) {
      except.printStackTrace();
    }
    pw.close();
  }
  
  /**
   * Add information of each order into the order.csv file.
   * @param pickingset  A set of four orders.
   */
  public static void writeorders(PickingSet pickingset) throws IOException {
    PrintWriter pw = null;
    ArrayList<Integer> orders = pickingset.getPickingSet();
    int index = pickingset.getIndex();
    
    try {
      pw = new PrintWriter(new FileWriter("orders.csv", true));
    } catch (FileNotFoundException except) {
      except.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
    
    sb.append("PICKING REQUEST " + index + "\n");
    
    for (int i = 0; i < 4; i++) {
      int front = 2 * i;
      int back = front + 1;
      sb.append("F" + orders.get(front) + "," + "R" + orders.get(back) + "\n");
    }

    pw.write(sb.toString());
    pw.close();
    System.out.println("done!");
  }
}
