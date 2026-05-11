package behavioural.observer;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String symbol, double price);
}

interface Publisher {
    void attachObserver(Observer observer);

    void detachObserver(Observer observer);

    void notifyObservers();
}

class Stock implements Publisher {

    private final List<Observer> observers = new ArrayList<>();
    private String symbol;
    private double price;

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }
}

class PriceDisplay implements Observer {
    @Override
    public void update(String symbol, double price) {
        System.out.println("Price Display updated: " + symbol + " = $" + price);
    }
}

class PriceAlert implements Observer {
    private final double threshold;

    public PriceAlert(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update(String symbol, double price) {
        if (price > threshold) {
            System.out.println("Alert! " + symbol + " exceeded $" + threshold);
        }
    }
}

public class ThisIsWhyObserver {
    static void main() {
        Stock stock = new Stock("A1");

        PriceDisplay priceDisplay = new PriceDisplay();
        PriceAlert priceAlert = new PriceAlert(100);

        stock.attachObserver(priceDisplay);
        stock.attachObserver(priceAlert);

        stock.setPrice(200);
    }
}
