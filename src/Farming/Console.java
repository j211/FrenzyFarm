package Farming;
import Farming.impl.GardenImpl;
import Farming.impl.RepositoryImpl;
import Farming.impl.ShopImpl;

import java.util.Scanner;

import static Farming.Farm.*;

public class Console {
    private Scanner in = new Scanner(System.in);
    private String name;
    private String s;
    private int count;
    private Double countD;
    int checkInt(String s) {
        while (true) {
            try {
                return Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                System.out.println("Ошибка при вводе количества рассады, введите корректное число для дальнейшей работы");
                s=in.next();
            }
        }
    }
    Double checkDouble(String s) {
        while (true) {
            try {
                return Double.parseDouble(s);
            }
            catch (NumberFormatException ex) {
                System.out.println("Ошибка при вводе количества рассады, введите корректное число для дальнейшей работы");
                s=in.next();
            }
        }
    }
    void readConsole() throws Exception {
            Farm farm = new Farm();
            farm.repository = new RepositoryImpl();
            farm.garden = new GardenImpl(farm.repository);
            farm.repository.setBonus(100);
            farm.repository.setShop(new ShopImpl());
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
            name = "";
            count=0;
            while (!name.equals("exit")){
                name=in.next();
                if (!name.equals("exit")){
                s = in.next();
                    if (farm.repository.getShop().getAssortment().get(name) != null){
                        count = checkInt(s);
                        if (count!=0 && farm.repository.getShop().getAssortment().get(name).count >= count && farm.repository.getBonus()>=count*farm.repository.getShop().getAssortment().get(name).price) {
                            farm.repository.buySeedling(name, count);
                            System.out.println("В магазине осталось:");
                            System.out.println("Наим.   " + "кол " + "цена" + " вес_урожая,кг");
                            farm.repository.getShop().getAssortment().forEach((k, v) -> System.out.println(v.name + "  " + v.count + "  " + v.price + "  " + v.plods_weight));
                            System.out.println("У вас осталось " + farm.repository.getBonus() + " бонусов.");
                            System.out.println("Купленная рассада для посадки:");
                            System.out.println("Наим.   " + "кол " + "цена" + " вес_урожая,кг");
                            farm.repository.getSeedling().forEach((k, v) -> System.out.println(v.name + "  " + v.count + "  " + v.price + "  " + v.plods_weight));
                            System.out.println("Введите что и в каком количестве вы желаете посадить, например: Tomat 3 ");
                            name = "";
                            count=0;
                            while (!name.equals("exit")) {
                                name = in.next();
                                if (!name.equals("exit")) {
                                    s = in.next();
                                    if (farm.repository.getSeedling().get(name) != null) {
                                        count = checkInt(s);
                                        if (count != 0 && farm.repository.getSeedling().get(name).count >= count) {
                                            farm.garden.Plant(name, count);
                                            System.out.println("Ваши посадки на огороде:");
                                            System.out.println("Наим.   " + "кол " + "цена" + " урожай,кг" + " зрелость");
                                            farm.garden.printGarden();
                                            System.out.println("Начинаем выращивать? Введите - yes");
                                            in.nextLine();
                                            if (in.nextLine().equals("yes")) {
                                                farm.garden.grow();
                                                System.out.println("Плоды созрели, можно собирать на склад? Введите - yes ");
                                                if (in.nextLine().equals("yes")) {
                                                    farm.garden.clearGarden();
                                                    System.out.println("На складе:");
                                                    System.out.println("Наим. " + "кол,кг " + "цена за 1 кг");
                                                    farm.repository.getCrop().forEach((k, v) -> System.out.println(v.name + "  " + v.count + "  " + v.price + "  "));
                                                    System.out.println("Продать урожай? Введите, что необходимо продать, например: Tomat 1.1 , количество в кг");
                                                    name = "";
                                                    countD=0.;
                                                    while (!name.equals("exit")) {
                                                        name = in.next();
                                                        if (!name.equals("exit")) {
                                                            s = in.next();
                                                            if (farm.repository.getCrop().get(name) != null) {
                                                                countD = checkDouble(s);
                                                                if (countD != 0 && farm.repository.getCrop().get(name).count >= countD) {
                                                                    farm.repository.sellCrop(name, countD);
                                                                    System.out.println("На складе:");
                                                                    System.out.println("Наим. " + "кол,кг " + "цена за 1 кг");
                                                                    farm.repository.getCrop().forEach((k, v) -> System.out.println(v.name + "  " + v.count + "  " + v.price + "  "));
                                                                    System.out.println("Ваши бонусы после продажи:");
                                                                    System.out.println(farm.repository.getBonus());
                                                                    System.out.println("Для выхода введите - exit");
                                                                } else {
                                                                    System.out.println("Вы ввели не верное количество продукта, повторите ввод корректно, количество в кг, например: Tomat 1.1 или введите - exit");
                                                                }
                                                            } else {
                                                                System.out.println("Вы ввели несуществующий вид продукта,повторите ввод корректно, например: Tomat 1.1 или введите - exit");
                                                            }
                                                        }
                                                    }
                                                } else {System.out.println("Сбор урожая преостановлен. Для выхода введите - exit");}
                                            } else {System.out.println("Выращивание преостановленно.Для выхода введите - exit");}
                                        } else {System.out.println("Вы ввели не верное количество рассады, повторите ввод корректно, например: Tomat 3 или введите - exit");
                                        }
                                    } else{System.out.println("Вы ввели несуществующий вид продукта,повторите ввод корректно, например: Tomat 3 или введите - exit");}
                                }
                            }
                        }
                        else{
                            System.out.println("Вы ввели не верное количество рассады, повторите ввод корректно, например: Tomat 3 или введите - exit");
                        }
                    } else {
                        System.out.println("Вы ввели несуществующий вид продукта,повторите ввод корректно, например: Tomat 3 или введите - exit");
                       }
                }
            }
    }

}
