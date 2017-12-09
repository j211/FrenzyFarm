package Farming;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

import static Farming.Farm.getClasses;

public class Console {
    private Scanner in = new Scanner(System.in);
    private String ansver=" ";
    void readConsole() throws Exception {
            Farm farm = new Farm();
            farm.repository = new Repository();
            farm.garden = new Garden(farm.repository);
            farm.repository.setBonus(100);
            farm.repository.setShop(new Shop());
            farm.repository.getShop().fileRead(farm.repository.getShop());
            Class[] list = getClasses("Farming");
            System.out.println("Для вас доступен данный тип рассады:");
            for (Class l:list){
                if (l.isAnnotationPresent(Product_Type.class))
                 System.out.println(l.getSimpleName());
            }
            System.out.println("У вас имеется " + farm.repository.getBonus() + " бонусов.");
            System.out.println("На них вы можете купить рассаду для выращивания.");
            System.out.println("Для этого введите наименование растения и желаемое количество для приобретения из представленных позиций:");
            System.out.println("Наим.   "+"кол "+"цена"+" вес_урожая,кг");
            farm.repository.getShop().getAssortment().forEach((k,v)-> System.out.println(v.name+"  " + v.count + "  " + v.price + "  " + v.plods_weight ));
            System.out.println("Например Tomat 3");
            farm.repository.buySeedling(in.next(),Integer.parseInt(in.next()));
            System.out.println("В магазине осталось:");
            System.out.println("Наим.   "+"кол "+"цена"+" вес_урожая,кг");
            farm.repository.getShop().getAssortment().forEach((k,v)-> System.out.println(v.name+"  " + v.count + "  " + v.price + "  " + v.plods_weight ));
            System.out.println("У вас осталось " + farm.repository.getBonus() + " бонусов.");
            System.out.println("Купленная рассада для посадки:");
            System.out.println("Наим.   "+"кол "+"цена"+" вес_урожая,кг");
            farm.repository.getSeedling().forEach((k,v)-> System.out.println(v.name+"  " + v.count + "  " + v.price + "  " + v.plods_weight ));
            System.out.println("Введите что и в каком количестве вы желаете посадить, например: Tomat 3 ");
            farm.garden.Plant(in.next(),Integer.parseInt(in.next()));
            System.out.println("Ваши посадки на огороде:");
            System.out.println("Наим.   "+"кол "+"цена"+" урожай,кг"+" зрелость");
            //farm.garden.garden.forEach((k,v)-> System.out.println(v.name+"  " + v.count + "  " + v.price + "  " + v.plods_weight + " " + v.ripeness ));
            farm.garden.printGarden();
            System.out.println("Начинаем выращивать? Введите - yes");
            in.nextLine();
            if (in.nextLine().equals("yes")) {
                farm.garden.grow();
                System.out.println("Плоды созрели, можно собирать на склад? Введите - yes ");
                if (in.nextLine().equals("yes")) {
                    farm.garden.clearGarden();
                    System.out.println("На складе:");
                    System.out.println("Наим. "+"кол,кг "+"цена за 1 кг");
                    farm.repository.getCrop().forEach((k, v) -> System.out.println(v.name + "  " + v.count + "  " + v.price + "  "));
                    System.out.println("Продать урожай? Введите, что необходимо продать, например: Tomat 2 , количество в кг");
                    farm.repository.sellCrop(in.next(),Double.parseDouble(in.next()));
                    System.out.println("На складе:");
                    System.out.println("Наим. "+"кол,кг "+"цена за 1 кг");
                    farm.repository.getCrop().forEach((k,v)-> System.out.println(v.name+"  " + v.count + "  " + v.price + "  "   ));
                    System.out.println("Ваши бонусы после продажи:");
                    System.out.println(farm.repository.getBonus());
                }

            }
    }

}
