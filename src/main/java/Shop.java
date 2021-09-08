import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static final int TIME_DELIVERY_CAR = 5000;
    private static final int TIME_BUY_CAR = 2000;
    private String name;
    private int counterSale = 0;
    private int index = 0;
    private List<Car> carList = new ArrayList<>();

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCounterSale() {
        return counterSale;
    }

    public synchronized void productonCar() {
        try {
            carList.add(new Car(Thread.currentThread().getName()));
            System.out.println("Поступил в продажу автомобиль " + carList.get(index).getName());
            index++;
            Thread.sleep(TIME_DELIVERY_CAR);
            notify();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " пришел в автосалон");
            while (carList.isEmpty()) {
                System.out.println("В автосалоне нет в наличии автомобилей");
                wait();
            }
            Thread.sleep(TIME_BUY_CAR);
            System.out.println(Thread.currentThread().getName() + " приобрел автомабиль " + carList.remove(0).getName());
            counterSale++;
            return carList.remove(0);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
