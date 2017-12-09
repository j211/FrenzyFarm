package Farming;

abstract class Product {
    String name;
    Unit unit;
    double count;
    int price;

    public Product(String name,Unit unit,/* String unit,*/ double count, int price) {
        this.name = name;
        this.unit = unit;
        this.count = count;
        this.price = price;
    }
    final void checkFreshness(){
        watchProduct();
        getWater();
        doUtilization();
    }
    void watchProduct(){
        System.out.println("Отбираем испорченные");
    }
    abstract  void getWater();
    void doUtilization(){
        System.out.println("Утилизировали");
    }
}
