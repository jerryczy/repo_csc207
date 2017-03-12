package warehouse;

public class Picker {
  
  private String name;
  private String status;
  
  public Picker(String name) {
    this.name = name;
    this.status = "not ready";
  }

  public boolean isReady() {
    if (status == "ready") {
      return true;
    } else {
      return false;
    }
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public String getName() {
    return this.name;
  }
}
