package Farming.impl;

import Farming.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShopImpl implements Shop {
    Map<String, Seedling> assortment = new HashMap<>();
    //метод продать
    @Override
    public void sell(String name, int count) {
        assortment.get(name).count-=count;
    }
    //добавить наименования товара
    @Override
    public void addAssortment(String type_product, String name, Unit unit, double count, int price, int ripeness, double plods_weight){
        SeedlingCreator creator;
        Seedling s;
        switch (type_product) {
            case "Tomat": creator  = new TomatSeedlingCreator();
                s = creator.creatSeedling(name,unit,count,price,ripeness,plods_weight);
                break;
            case "Potato": creator = new PotatoSeedlingCreator();
                s = creator.creatSeedling(name,unit,count,price,ripeness,plods_weight);
                break;
            default: s = new Seedling(name,unit,count,price,ripeness,plods_weight);
                break;

        }
        assortment.put(name,s);
    }
    //чтение из файла списка товаров
    @Override
    public void fileRead(Shop shop) throws FileNotFoundException {
        try {
            FileReader f = new FileReader("Shop.txt");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                shop.addAssortment(scanner.next(),scanner.next(),Unit.valueOf(scanner.next()),Double.parseDouble(scanner.next()),Integer.parseInt(scanner.next()),Integer.parseInt(scanner.next()),Double.parseDouble(scanner.next()));
            }
            scanner.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Требуемый файл не найден или не существует.");
        }
    }
    @Override
    public Map<String, Seedling> getAssortment() {
        return assortment;
    }

}
