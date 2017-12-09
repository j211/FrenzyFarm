package Farming;

class PotatoSeedlingCreator extends SeedlingCreator{

    @Override
    PotatoSeedling creatSeedling(String name, Unit unit, double count, int price, int ripeness, double plods_weight) {
        return new PotatoSeedling(name,unit,count, price,ripeness, plods_weight);
    }
}
