package Farming;

import java.util.HashMap;
import java.util.Map;
//класс склад
class Repository implements Repositorable{
    private int bonus;
    Shopable shop;
    Gardenable g;
    Map<String,Seedling> seedling = new HashMap<>();
    Map<String,Crop> crop = new HashMap<>();
    //купить рассаду
    public void buySeedling(String name, int count){
            shop.sell(name, count);
            this.bonus -= shop.getAssortment().get(name).price * count;
            if (seedling.get(name) != null)
                seedling.get(name).count += count;
            else {
                Seedling s = new Seedling(shop.getAssortment().get(name).name, shop.getAssortment().get(name).unit, shop.getAssortment().get(name).count, shop.getAssortment().get(name).price, shop.getAssortment().get(name).ripeness, shop.getAssortment().get(name).plods_weight);
                seedling.put(name, s);
                seedling.get(name).count = count;
            }
    }
    //продать урожай
    public void sellCrop(String name,Double count){
        crop.get(name).count-=count;
        this.bonus += (int)(crop.get(name).price*count);
    }
    //получить рассаду
    public Seedling getSeedling(String name,int count){
        seedling.get(name).count-=count;
        Seedling s = new Seedling(seedling.get(name).name,seedling.get(name).unit,seedling.get(name).count,seedling.get(name).price, seedling.get(name).ripeness,seedling.get(name).plods_weight);
        return s;
    }
    //добавить урожай
    public void addCrop(Crop cr,double count){
        if (crop.get(cr.name)!= null) {
            crop.get(cr.name).count+=count;
        }
        else
            crop.put(cr.name,cr);
    }

    public int getBonus() {
        return bonus;
    }

    public Shopable getShop() {
        return shop;
    }

    public Map<String, Seedling> getSeedling() {
        return seedling;
    }

    public Map<String, Crop> getCrop() {
        return crop;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}

