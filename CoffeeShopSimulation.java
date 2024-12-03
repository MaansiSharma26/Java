import java.util.LinkedList;
import java.util.Queue;

class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

class CoffeeShop {
    private final Queue<String> counter = new LinkedList<>();
    private final int capacity = 3;

    public synchronized void produce(String coffee, String baristaName) throws InterruptedException {
        while (counter.size() == capacity) {
            System.out.println(baristaName + " is waiting, counter is full.");
            wait();
        }
        counter.add(coffee);
        System.out.println(baristaName + " prepared " + coffee + " (Counter: " + counter.size() + " coffee(s))");
        notifyAll();
    }

    public synchronized String consume(String customerName) throws InterruptedException, CounterEmptyException {
        while (counter.isEmpty()) {
            System.out.println(customerName + " is waiting, counter is empty.");
            wait();
        }
        String coffee = counter.poll();
        System.out.println(customerName + " picked up " + coffee + " (Counter: " + counter.size() + " coffee(s))");
        notifyAll();
        return coffee;
    }

    public synchronized String review(String reviewerName) throws InterruptedException, CounterEmptyException {
        while (counter.isEmpty()) {
            System.out.println(reviewerName + " is waiting, counter is empty.");
            wait();
        }
        String coffee = counter.peek(); // Reviewer just samples without removing
        System.out.println(reviewerName + " reviewed " + coffee + " (Counter: " + counter.size() + " coffee(s))");
        return coffee;
    }
}

class Barista implements Runnable {
    private final CoffeeShop shop;
    private final String baristaName;
    private final int coffeeCount;

    public Barista(CoffeeShop shop, String baristaName, int coffeeCount) {
        this.shop = shop;
        this.baristaName = baristaName;
        this.coffeeCount = coffeeCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= coffeeCount; i++) {
                shop.produce("Coffee " + i, baristaName);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Customer implements Runnable {
    private final CoffeeShop shop;
    private final String customerName;
    private final int coffeeCount;

    public Customer(CoffeeShop shop, String customerName, int coffeeCount) {
        this.shop = shop;
        this.customerName = customerName;
        this.coffeeCount = coffeeCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeeCount; i++) {
                shop.consume(customerName);
            }
        } catch (InterruptedException | CounterEmptyException e) {
            System.out.println(customerName + " encountered an issue: " + e.getMessage());
        }
    }
}

class Reviewer implements Runnable {
    private final CoffeeShop shop;
    private final String reviewerName;

    public Reviewer(CoffeeShop shop, String reviewerName) {
        this.shop = shop;
        this.reviewerName = reviewerName;
    }

    @Override
    public void run() {
        try {
            shop.review(reviewerName);
        } catch (InterruptedException | CounterEmptyException e) {
            System.out.println(reviewerName + " encountered an issue: " + e.getMessage());
        }
    }
}

public class CoffeeShopSimulation {
    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();

        Thread barista1 = new Thread(new Barista(shop, "Barista 1", 2));
        Thread barista2 = new Thread(new Barista(shop, "Barista 2", 3));

        Thread customer1 = new Thread(new Customer(shop, "Customer 1", 1));
        Thread customer2 = new Thread(new Customer(shop, "Customer 2", 2));
        Thread customer3 = new Thread(new Customer(shop, "Customer 3", 1));

        Thread reviewer = new Thread(new Reviewer(shop, "Reviewer"));

        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();
        customer3.start();
        reviewer.start();
    }
}