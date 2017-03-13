package warehouse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuewang on 2017-03-12.
 */
public class WarehousePicking {
  /**
   * Convert the traversal_table.csv file into a HashMap.
   * @return a HashMap where the key is a String represent the sku number,
   *  and the value is the position.
   */
  private static HashMap<String, String> readCsv() {
    File traversalInput = new File(System.getProperty("user.dir") + "/traversal_table.csv");
    HashMap<String, String> map = new HashMap<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(traversalInput));
      String line = br.readLine();
      while (line != null) {
        String[] splited = line.split(",");
        String location = splited[0] + splited[1] + splited[2] + splited[3];
        map.put(splited[4], location);
        line = br.readLine();
      }
      br.close();
    } catch (FileNotFoundException except) {
      except.printStackTrace();
    } catch (IOException except) {
      except.printStackTrace();
    }
    return map;
  }
  
  /**
   * Optimize the path for pickers based on orders.
   * @param skus    A list of sku number to retrieve.
   * @return A list of locations.
   */
  public static List<String> optimize(List<Integer> skus) {
    ArrayList<String> fromCsv = new ArrayList<>();
    HashMap<String, String> map = readCsv();
    List<String> result = new ArrayList<>();
    for (int i = 0; i < skus.size(); i++) {
      fromCsv.add(map.get(skus.get(i).toString()));
    }
    for (int i = 0; i < fromCsv.size();i++) {
      result.add(fromCsv.get(i));
    }
    return result;
  }
}
