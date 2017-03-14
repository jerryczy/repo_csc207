import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Order {
  private String colour;
  private String model;
  private int skuFront;
  private int skuBack;
  
  /**
   * Create a Order.
   * @param colour  the colour asked by this order.
   * @param model   the model asked by this order.
   */
  public Order(String colour, String model) {
    this.colour = colour;
    this.model = model;
    setSkus();
  }
  
  public int getSkuFront() {
    return this.skuFront;
  }
  
  public int getSkuBack() {
    return this.skuBack;
  }
  
  /**
   * Find the set the sku numbers according to traslation.csv file.
   */
  public void setSkus() {
    String path = System.getProperty("user.dir");
    String translationFile = path + "/translation.csv";
    BufferedReader br = null;
    String line = "";
    
    try {
      br = new BufferedReader(new FileReader(translationFile));

      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");
        if ((data[0].equals(this.colour)) && (data[1].equals(this.model))) {
          this.skuFront = Integer.parseInt(data[2]);
          this.skuBack = Integer.parseInt(data[3]);
        }
      }
    } catch (FileNotFoundException except) {
      except.printStackTrace();
    } catch (IOException except) {
      except.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException except) {
          except.printStackTrace();
        }
      }
    }
  }
}
