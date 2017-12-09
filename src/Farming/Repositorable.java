package Farming;

import java.util.Map;
//Интерфейс склад
interface Repositorable{
    void buySeedling(String name, int count);
    void sellCrop(String name,Double count);
    Seedling getSeedling(String name,int count);
    void addCrop(Crop cr,double count);
    int getBonus();
    Shopable getShop();
    Map<String, Seedling> getSeedling();
    Map<String, Crop> getCrop();
    void setBonus(int bonus);
    void setShop(Shop shop);
}
