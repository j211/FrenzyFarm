package Farming;

public class Crop extends Product{

    public Crop(String name, Unit unit, double count, int price) {
        super(name, unit, count, price);
    }

    @Override
    void getWater() {}
}
