package Farming;

public class Seedling extends Product{
    public int ripeness;
    public double plods_weight;

    public Seedling(String name,Unit unit, double count,int price,  int ripeness,double plods_weight) {
        super(name,unit,count,price);
        this.ripeness = ripeness;
        this.plods_weight = plods_weight;
    }
    public void setCount(double count){
        this.count = count;
    }

    @Override
    public void getWater() {
        System.out.println("Поливаем");
        System.out.println("Просмативаем, помог полив или нет");
    }
}