package Farming;

abstract class ShopDecorator implements Shopable{
    Shopable component;

    public ShopDecorator(Shopable component) {
        this.component = component;
    }
    public void sell(String name, int count){
        component.getAssortment().forEach((k,v)-> System.out.println(v.name+"  " + v.count + "  " + v.price + "  " + v.plods_weight ));
        component.sell(name,count);
    };
}
