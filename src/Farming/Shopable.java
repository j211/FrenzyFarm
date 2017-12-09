package Farming;

import java.io.FileNotFoundException;
import java.util.Map;

interface Shopable{
    void sell(String name, int count);
    void addAssortment(String type_product,String name,Unit unit, double count,int price, int ripeness,double plods_weight);
    void fileRead(Shopable shop) throws FileNotFoundException;
    Map<String, Seedling> getAssortment();
}
