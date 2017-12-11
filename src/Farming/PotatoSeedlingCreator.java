package Farming;

public class PotatoSeedlingCreator extends SeedlingCreator{

    @Override
    public PotatoSeedling creatSeedling(String name, Unit unit, double count, int price, int ripeness, double plods_weight) {
        return new PotatoSeedling(name,unit,count, price,ripeness, plods_weight);
    }
}
