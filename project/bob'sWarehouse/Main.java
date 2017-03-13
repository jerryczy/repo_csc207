import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Main {
  
  public static void main(String[] args) {
    // Read initstorage.csv and initialize the storage
    HashMap<Integer, Integer> storage = new HashMap<Integer, Integer>();
    
    String translationFile = "src\\initstorage.csv";
    BufferedReader brr = null;
    String line = "";
    
    try {
      brr = new BufferedReader(new FileReader(translationFile));

      while((line = brr.readLine()) != null) {
          String[] data = line.split(",");
          storage.put(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (brr != null) {
        try {
            brr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
    }
    
    String sequencer = "sue";
    String loader = "max";
    String replenisher = "Joe";
    Warehouse warehouse = new Warehouse(sequencer, loader, replenisher, storage);
    
    // Read input file and do the corresponding task
    try (
        InputStream fis = new FileInputStream("src\\16orders.txt");
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
    ) {
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            String[] command = line.split(" ");
            
            if (command[0].toLowerCase().equals("order")){
              Order order = new Order(command[2], command[1]);
              
            }
            else if (command[0].toLowerCase().equals("picker")) {
              
            }
        }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    
    
    
  }
  
  
}
