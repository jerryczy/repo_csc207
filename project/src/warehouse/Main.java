package warehouse;


import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            File eventInput = new File(System.getProperty("user.dir")+"/40orders.txt");
            BufferedReader br = new BufferedReader(new FileReader(eventInput));
            String line;

            line = br.readLine();

            while(line!=null){
                String[] splited = line.split(" ");
                System.out.println("Task name is: "+ splited[0]);
                if(splited[0].equals("Order")){
                    //construct an order object
                }else if(splited[0].equals("Picker")){
                    //task is to be assigned to a picker
                }else if(splited[0].equals("Sequencer")){
                    //task is to be assigned to a sequencer
                }else if(splited[0].equals("Loader")){
                    //task is to be assigned to a loader
                }
                line = br.readLine();
            }

            // some testing code to optimize algo..
            ArrayList<String> skus = new ArrayList<>();
            skus.add("1");
            skus.add("2");
            List returned = WarehousePicking.optimize(skus);
            for(int i=0; i<returned.size();i++){
                System.out.println(returned.get(i));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
