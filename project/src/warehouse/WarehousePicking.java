package warehouse;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuewang on 2017-03-12.
 */
public class WarehousePicking {

     private static HashMap readCSV(){
        File traversalInput = new File(System.getProperty("user.dir")+"/traversal_table.csv");
        HashMap<String, String[]> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(traversalInput));
            String line = br.readLine();
            while(line!=null){
                String[] splited = line.split(",");
                map.put(splited[4], splited);
                line = br.readLine();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         return map;
    }

    public static List optimize(List skus) {
        ArrayList<String[]> fromCSV = new ArrayList<>();
        HashMap<String, String[]> map = readCSV();
        List<String> result = new ArrayList<>();
        for(int i=0; i<skus.size(); i++){
            fromCSV.add(map.get(skus.get(i)));
        }
        for(int i=0; i<fromCSV.size();i++){
            result.add(Arrays.toString(fromCSV.get(i)));
        }
        return result;
    }
}
