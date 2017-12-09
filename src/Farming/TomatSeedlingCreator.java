package Farming;

class TomatSeedlingCreator extends SeedlingCreator{
    @Override
    TomatSeedling creatSeedling(String name,Unit unit, double count,int price,  int ripeness,double plods_weight){
        return new TomatSeedling(name,unit,count, price,ripeness, plods_weight);
    }
}
