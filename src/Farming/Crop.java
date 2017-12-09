package Farming;

class Crop extends Product{

    Crop(String name, Unit unit, double count, int price) {
        super(name, unit, count, price);
    }

    @Override
    void getWater() {}
}
