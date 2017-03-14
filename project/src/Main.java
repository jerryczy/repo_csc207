import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
  
  public static void main(String[] args) {
    String sequencer = "sue";
    String loader = "max";
    String replenisher = "Joe";
    ArrayList<Picker> pickers = new ArrayList<Picker>();
    Picker picker = new Picker("Bob");
    pickers.add(picker);
    picker = new Picker("Alice");
    pickers.add(picker);
    Task task = null;
    
    // Read initstorage.csv and initialize the storage
    HashMap<String, Integer> storage = ReadWriteStorage.readstorage();
    
    // Initial Final.csv
    WriteOrdersTruck.initialFinalOrders();

    Warehouse warehouse = new Warehouse(sequencer, loader, replenisher, storage, pickers);
    
    // Read input file and do the corresponding task
    try {
      File eventInput = new File(System.getProperty("user.dir")+"/src/40orders.txt");
      BufferedReader br = new BufferedReader(new FileReader(eventInput));

      String line = br.readLine();
      int linenum = 0;

      while(line!=null){
          String[] splited = line.split(" ");
          
          if(splited[0].equals("Order")){
            
            //construct an order object
            Order order = new Order(splited[2], splited[1]);
            warehouse.addOrder(order);
              
          }else if(splited[0].equals("Picker")){
            //task is to be assigned to a picker
            String name = splited[1];
            if (splited[2].equals("ready")) {
              warehouse.updatePickerStatus(name, true);
            }
            else if (splited[2].equals("pick")) {
              int pickingIndex = Integer.parseInt(splited[3]);
              
              if (warehouse.checkPickerSatus(name)) {
                PickingSet pickingset = warehouse.getOrderOnHold();
                if (pickingset != null){
                  warehouse.updatePickerStatus(name, false);
                  task = warehouse.getTask(name);
                  task.updateOrderSequencing(pickingset);
                }
              }
              task = warehouse.getTask(name);
              task.getoptimizedlocation(pickingIndex);
            }
            else if (splited[3].equals("Marshaling")) {
              task = warehouse.getTask(name);
              task.toMarshaling();
              warehouse.updatePickerStatus(name, true);
            }
          }else if(splited[0].equals("Sequencer")){
            //task is to be assigned to a sequencer
            warehouse.sequence(sequencer);
          }else if(splited[0].equals("Loader")){
            //task is to be assigned to a loader
            warehouse.load();
            System.out.println("orders in Truck: "+warehouse.ordersInTruck.size());
            warehouse.deliver();
          }
          line = br.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
