import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map.Entry;

public class WriteOrdersTruck {
  public static void initialFinalOrders(){
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new File("src\\orders.csv"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    pw.close();
  }
  
  public static void writeorders(PickingSet pickingset) throws IOException {
    PrintWriter pw = null;
    ArrayList<Integer> orders = pickingset.getPickingSet();
    int index = pickingset.getIndex();
    
    try {
      pw = new PrintWriter(new FileWriter("src\\orders.csv", true));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
    
    sb.append("PICKING REQUEST " + index + "\n");
    
    for (int i=0; i<4; i++){
      int j = 2*i;
      int k = j+1;
      sb.append("F" + orders.get(j) + "," + "R" + orders.get(k) + "\n");
    }

    pw.write(sb.toString());
    pw.close();
    System.out.println("done!");
  }
}
