import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Order {
  private String colour;
  private String model;
  private int skuFront;
  private int skuBack;
  
  public Order(String colour, String model) {
    this.colour = colour;
    this.model = model;
    setSKUs();
  }
  
  public int getSKUfront() {
    return this.skuFront;
  }
  
  public int getSKUback() {
    return this.skuBack;
  }
  
  public void setSKUs() {
    String path = System.getProperty("user.dir");
    String translationFile = path + "\\src\\translation.csv";
    BufferedReader br = null;
    String line = "";
    
    try {
      br = new BufferedReader(new FileReader(translationFile));

      while((line = br.readLine()) != null) {
          String[] data = line.split(",");
          if ((data[0].equals(this.colour)) && (data[1].equals(this.model))) {
            this.skuFront = Integer.parseInt(data[2]);
            this.skuBack = Integer.parseInt(data[3]);
          }
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
    }
  }
}
