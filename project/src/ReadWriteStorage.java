import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

public class ReadWriteStorage {
  
  public static HashMap<String, Integer> readstorage(){
    HashMap<String, Integer> storage = new HashMap<String, Integer>();
    
    String translationFile = "src/initial.csv";
    BufferedReader brr = null;
    String line = "";
    
    try {
      brr = new BufferedReader(new FileReader(translationFile));

      while((line = brr.readLine()) != null) {
          String[] data = line.split(",");
          String location = data[0] + data[1] + data[2]+ data[3];
          storage.put(location, Integer.parseInt(data[4]));
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
    
    return storage;
  }
  
  
  public static void writestorage(HashMap<String, Integer> storage){
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new File("final.csv"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
    
    for (Entry<String, Integer> entry : storage.entrySet())
    {
//        System.out.println(entry.getKey() + "/" + entry.getValue());
        String location = entry.getKey();
        int amount = entry.getValue();
        sb.append(location.charAt(0));
        sb.append(",");
        sb.append(location.charAt(1));
        sb.append(",");
        sb.append(location.charAt(2));
        sb.append(",");
        sb.append(location.charAt(3));
        sb.append(",");
        sb.append(Integer.toString(amount));
        sb.append("\n");
    }

    pw.write(sb.toString());
    pw.close();
    System.out.println("done!");
  }
  
  
}
