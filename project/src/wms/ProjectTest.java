package wms;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class ProjectTest {

  @Test
  public void testOrderSetSkus() {
    try{
      Order m = new Order("Red","SEL");
      m.setSkus();
      assertTrue(m.getSkuFront()==23);
      assertTrue(m.getSkuBack()==24);
    }catch(Exception e){
      fail(e.getMessage());
    }
      
  }
  
  @Test 
  public void testInitialFinalOrders() {
    try{
      WriteOrdersTruck.initialFinalOrders();
    }catch(Exception e){
      fail(e.getMessage());
    }
  }
  
  @Test
  public void testGetPicker(){
    Task task = new Task(new Picker("Bob"));
    assertTrue(task.getPicker().getName().equals("Bob"));
  }
  
  @Test
  public void testWriteOrder(){
    //order number not equals 4
    Order m = new Order("Red","SEL");
    PickingSet ps1 = new PickingSet(1);
    ps1.addOrder(m);
    PickingSet ps2 = new PickingSet(2);
    //order number equals 4
    for(int i=0; i<4; i++){
      ps2.addOrder(m);
    }
    try{
      WriteOrdersTruck.writeorders(ps1);
      WriteOrdersTruck.writeorders(ps2);
    }catch(Exception e){
      fail(e.getMessage());
    }
  }
  
  @Test
  public void testGetSkuBack() {
      Order m = new Order("Red","SEL");
      m.setSkus();
      assertTrue(Integer.class.isInstance(m.getSkuBack()));
  }
  
  @Test
  public void testGetSkuFront() {
      Order m = new Order("Red","SEL");
      m.setSkus();
      assertTrue(Integer.class.isInstance(m.getSkuFront()));
  }
  
  @Test
  public void testWarehouse() {
    try {
      HashMap<String,Integer> storage = new HashMap<>();
      ArrayList<Picker> pickers = new ArrayList<>();
      new Warehouse(storage, pickers);
    } catch (Exception e) {
      fail(e.getMessage());
    }
    
  }
  
  @Test
  public void testReadStorage() {
    try {
      assertTrue(HashMap.class.isInstance(ReadWriteStorage.readstorage()));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  public void testWriteStorage() {
    try {
      ReadWriteStorage.writestorage(new HashMap<String,Integer>());;
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
  
  @Test
  public void testReadCSV(){
    try {
      HashMap<String, String> map = WarehousePicking.readCsv();
      assertEquals(map.get("3"),"A002");
    }catch (Exception e){
      fail(e.getMessage());
    }
    
  }
  
  @Test
  public void testPickingSetAddOrder(){
    Order order = new Order("Red", "SEL");
    PickingSet ps = new PickingSet(1);
    for(int i=0; i<4; i++){
      assertTrue(ps.addOrder(order));
    }
    assertFalse(ps.addOrder(order));
    
  }
  
  @Test
  public void testGetPickingSet(){
    PickingSet ps = new PickingSet(1);
    Order order = new Order("Red", "SEL");
    ps.addOrder(order);
    assertTrue(ps.getPickingSet().get(0)==23);
    assertTrue(ps.getPickingSet().get(1)==24);
  }
  
  @Test
  public void testGetIndex(){
    PickingSet ps = new PickingSet(1);
    assertEquals(1, ps.getIndex());
  }
  
  @Test
  public void testPicker(){
    try {
      Picker picker = new Picker("Alice");
      assertEquals("Alice", picker.getName());
      assertFalse(picker.checkStatus());
    }catch (Exception e){
      fail(e.getMessage());
    }
  }
  
  @Test
  public void testOptimize(){
    List<Integer> skus = new ArrayList<>();
    skus.add(3);
    skus.add(5);
    List<String> optimized = WarehousePicking.optimize(skus);
    assertEquals(optimized.get(0),"A002");
    assertEquals(optimized.get(1),"A010");
  }

}
