package warehouse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuewang on 2017-03-12.
 */
public class WarehousePicking {
  
  private static HashMap<String, String[]> readCsv() {
    File traversalInput = new File(System.getProperty("user.dir") + "/traversal_table.csv");
    HashMap<String, String[]> map = new HashMap<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(traversalInput));
      String line = br.readLine();
      while (line != null) {
        String[] splited = line.split(",");
        map.put(splited[4], splited);
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

  public static List<String> optimize(List<String> skus) {
    ArrayList<String[]> fromCsv = new ArrayList<>();
    HashMap<String, String[]> map = readCsv();
    List<String> result = new ArrayList<>();
    for (int i = 0; i < skus.size(); i++) {
      fromCsv.add(map.get(skus.get(i)));
    }
    for (int i = 0; i < fromCsv.size();i++) {
      result.add(Arrays.toString(fromCsv.get(i)));
    }
    return result;
  }
}
