package Farming;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
//класс Ферма
public class Farm {
    Garden garden;
    Repository repository;

    public static void main(String[] args) throws Exception {
        Console console = new Console();
        console.readConsole();
    }
}
//класс Огород
class Garden{
    Repository repository;
    Map<String,Seedling> garden  = new HashMap<>();
    Garden(Repository repository){this.repository=repository;}
    void Plant(String name, int count){
            Seedling s = repository.getSeedling(name,count);
            s.setCount(count);
            garden.put(name,s);
    }
    //метод вырастить
    void grow(){
        for (Map.Entry<String,Seedling> map : garden.entrySet()){
            String k = map.getKey();
            Seedling v = map.getValue();
            v.ripeness ++;
        }
    }
    //метод собрать урожай
    void clearGarden() {
        for (Map.Entry<String, Seedling> map : garden.entrySet()) {
            String k = map.getKey();
            Seedling v = map.getValue();
            if (v.ripeness == 1) {
                Crop c = new Crop(v.name,  "kg", v.plods_weight*v.count, (int)(v.plods_weight*v.count*v.price*3));
                repository.addCrop(c,v.plods_weight*v.count);
                garden.remove(k);
            }
        }
    }

}
//класс Рассада потомок от Продукции
class Seedling extends Product{
    int ripeness;
    double plods_weight;

     Seedling(String name,String unit, double count,int price,  int ripeness,double plods_weight) {
        super(name,unit,count,price);
        this.ripeness = ripeness;
        this.plods_weight = plods_weight;
    }
    void setCount(double count){
        this.count = count;
    }
}
//Класс урожай
class Crop extends Product{

     Crop(String name, String unit, double count, int price) {
        super(name, unit, count, price);
    }
}
//Класс склад
class Repository{
    int bonus;
    Shop shop;
    Garden g;
    Map<String,Seedling> seedling = new HashMap<>();
    Map<String,Crop> crop = new HashMap<>();
    //добавить рассаду на склад
    void addSeedling(String name,String unit,double count,int price,int ripeness,double plods_weight){
        Seedling s = new Seedling(name,unit,count,price,ripeness,plods_weight);
        s.name=name;
        s.count=count;
        s.unit=unit;
        s.price=price;
        seedling.put(name,s);
    }
    //купить рассаду
    void buySeedling(String name, int count){
        shop.sell(name, count);
        this.bonus-=shop.assortment.get(name).price*count;
        if (seedling.get(name)!=null)
            seedling.get(name).count+=count;
        else {
            Seedling s = new Seedling(shop.assortment.get(name).name,shop.assortment.get(name).unit,shop.assortment.get(name).count,shop.assortment.get(name).price, shop.assortment.get(name).ripeness,shop.assortment.get(name).plods_weight);
            seedling.put(name, s);
            seedling.get(name).count=count;
        }
    }
    //продать урожай
    void sellCrop(String name,Double count){
        crop.get(name).count-=count;
        this.bonus += (int)(crop.get(name).price*count);
    }
    //получить рассаду
    Seedling getSeedling(String name,int count){
        seedling.get(name).count-=count;
        Seedling s = new Seedling(seedling.get(name).name,seedling.get(name).unit,seedling.get(name).count,seedling.get(name).price, seedling.get(name).ripeness,seedling.get(name).plods_weight);
        return s;
    }
    //добавить урожай
    void addCrop(Crop cr,double count){
        if (crop.get(cr.name)!= null) {
            crop.get(cr.name).count+=count;
        }
        else
            crop.put(cr.name,cr);
    }

}
//класс Продукция  - можно продавать
abstract class Product {
    String name;
    String unit;
    double count;
    int price;

    public Product(String name, String unit, double count, int price) {
        this.name = name;
        this.unit = unit;
        this.count = count;
        this.price = price;
    }
}
//класс Магазин
class Shop{
    Map<String,Seedling> assortment = new HashMap<>();
    //метод продать
    public void sell(String name, int count) {
        assortment.get(name).count-=count;
    }
    //добавить наименования товара
    void addAssortment(String name,String unit, double count,int price,  int ripeness,double plods_weight){
        Seedling s=new Seedling(name,unit,count,price,ripeness,plods_weight);
        assortment.put(name,s);
    }
    //чтение из файла списка товаров
    void fileRead(Shop shop) throws FileNotFoundException {
        try {
            FileReader f = new FileReader("Shop.txt");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                shop.addAssortment(scanner.next(),scanner.next(),Double.parseDouble(scanner.next()),Integer.parseInt(scanner.next()),Integer.parseInt(scanner.next()),Double.parseDouble(scanner.next()));
            }
            scanner.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Требуемый файл не найден или не существует.");
        }
    }
}
