package Farming;

class Seedling extends Product{
    int ripeness;
    double plods_weight;

    Seedling(String name,Unit unit, double count,int price,  int ripeness,double plods_weight) {
        super(name,unit,count,price);
        this.ripeness = ripeness;
        this.plods_weight = plods_weight;
    }
    void setCount(double count){
        this.count = count;
    }

    @Override
    void getWater() {
        System.out.println("Поливаем");
        System.out.println("Просмативаем, помог полив или нет");
    }
}