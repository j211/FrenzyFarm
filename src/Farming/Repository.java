package Farming;


import Farming.impl.ShopImpl;

import java.util.Map;
//Интерфейс склад
public interface Repository{
    void buySeedling(String name, int count);
    void sellCrop(String name,Double count);
    Seedling getSeedling(String name,int count);
    void addCrop(Crop cr,double count);
    int getBonus();
    Shop getShop();
    Map<String, Seedling> getSeedling();
    Map<String, Crop> getCrop();
    void setBonus(int bonus);
    void setShop(ShopImpl shop);
}
