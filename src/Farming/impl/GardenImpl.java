package Farming.impl;

import Farming.*;

import java.util.HashMap;
import java.util.Map;

public class GardenImpl implements Garden {
    Repository repository;
    private Map<String, Seedling> garden  = new HashMap<>();
    public GardenImpl(Repository repository){this.repository=repository;}
    @Override
    public void Plant(String name, int count){
        Seedling s = repository.getSeedling(name,count);
        s.setCount(count);
        garden.put(name,s);
    }
    //метод вырастить
    @Override
    public void grow(){
        for (Map.Entry<String,Seedling> map : garden.entrySet()){
            String k = map.getKey();
            Seedling v = map.getValue();
            v.ripeness ++;
        }
    }
    //метод собрать урожай
    @Override
    public void clearGarden() {
        for (Map.Entry<String, Seedling> map : garden.entrySet()) {
            String k = map.getKey();
            Seedling v = map.getValue();
            if (v.ripeness == 1) {
                Crop c = new Crop(v.name,  Unit.KG, v.plods_weight*v.count, (int)(v.plods_weight*v.count*v.price*3));
                repository.addCrop(c,v.plods_weight*v.count);
                garden.remove(k);
            }
        }
    }

    @Override
    public void printGarden() {
        garden.forEach((k,v)-> System.out.println(v.name+"  " + v.count + "  " + v.price + "  " + v.plods_weight + " " + v.ripeness ));
    }
}