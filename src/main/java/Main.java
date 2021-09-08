import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static Random random = new Random();
    public static final String CAR_SHOWROOM = "Car Showroom";
    public static final List<String> LIST_NAME_CLIENT = Arrays.asList("Андрей", "Eвгений", "Саша", "Петр", "Владимир");
    public static final List<String> LIST_TITLE_CAR = Arrays.asList("KIA", "RENO", "OPEL", "NISSAN", "HYUNDAI", "LADA");
    public static final int NUMBER_SALE_MAX = 10;
    public static final int TIME_SLEEP = 2000;

    public static void main(String[] args) {
        ThreadGroup mainGroup = new ThreadGroup("mainGroup");
        Shop shop = new Shop(CAR_SHOWROOM);
        while (shop.getCounterSale() != NUMBER_SALE_MAX) {
            new Thread(mainGroup, shop::sellCar, LIST_NAME_CLIENT.get(random.nextInt(LIST_NAME_CLIENT.size()))).start();
            new Thread(mainGroup, shop::productonCar, LIST_TITLE_CAR.get(random.nextInt(LIST_TITLE_CAR.size()))).start();
            try {
                Thread.sleep(TIME_SLEEP);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            System.out.println("Число продаж автомобилей: " + shop.getCounterSale());
        }
        mainGroup.interrupt();
    }
}
