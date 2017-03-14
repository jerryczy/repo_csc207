
public class Picker {
  private String name;
  private boolean status;
  
  public Picker(String name) {
    this.name = name;
    setStatus(false);
  }
  
  public boolean checkStatus() {
    return status;
  }
  
  public void setStatus(boolean status) {
    this.status = status;
  }
  
  public String getName() {
    return this.name;
  }
}
