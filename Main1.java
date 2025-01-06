import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }
}

// Menu Class
class Menu {
    private CustomerManager customerManager;
    private ProductManager productManager;
    private OrderManager orderManager;

    public Menu() {
        customerManager = new CustomerManager();
        productManager = new ProductManager();
        orderManager = new OrderManager(customerManager, productManager);
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Amazon Menu ===");
            System.out.println("1. Manage Customers");
            System.out.println("2. Manage Products");
            System.out.println("3. Manage Orders");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerManager.manageCustomers(scanner);
                    break;
                case 2:
                    productManager.manageProducts(scanner);
                    break;
                case 3:
                    orderManager.manageOrders(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Customer Manager
class CustomerManager {
    private Map<Integer, Customer> customerMap = new HashMap<>();
    private TreeSet<Customer> sortedCustomers = new TreeSet<>(new CustomerLoyaltyComparator());

    public void manageCustomers(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Customer Management ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Loyalty Points: ");
                    int points = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = new Customer(id, name, points);
                    customerMap.put(id, customer);
                    sortedCustomers.add(customer);
                    System.out.println("Customer added successfully.");
                    break;
                case 2:
                    System.out.println("Sorted Customers by Loyalty Points:");
                    sortedCustomers.forEach(System.out::println);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public Customer getCustomerById(int id) {
        return customerMap.get(id);
    }
}

// Product Manager
class ProductManager {
    private Map<Integer, Product> productMap = new HashMap<>();
    private TreeSet<Product> sortedProducts = new TreeSet<>(new ProductPriceComparator());

    public void manageProducts(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Product Management ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    Product product = new Product(id, name, price);
                    productMap.put(id, product);
                    sortedProducts.add(product);
                    System.out.println("Product added successfully.");
                    break;
                case 2:
                    System.out.println("Sorted Products by Price:");
                    sortedProducts.forEach(System.out::println);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public Product getProductById(int id) {
        return productMap.get(id);
    }
}

// Order Manager
class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private CustomerManager customerManager;
    private ProductManager productManager;

    public OrderManager(CustomerManager customerManager, ProductManager productManager) {
        this.customerManager = customerManager;
        this.productManager = productManager;
    }

    public void manageOrders(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. Place Order");
            System.out.println("2. View Orders");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = customerManager.getCustomerById(customerId);
                    if (customer == null) {
                        System.out.println("Customer not found!");
                        break;
                    }
                    List<Product> products = new ArrayList<>();
                    while (true) {
                        System.out.print("Enter Product ID (or -1 to finish): ");
                        int productId = scanner.nextInt();
                        scanner.nextLine();
                        if (productId == -1) break;
                        Product product = productManager.getProductById(productId);
                        if (product != null) {
                            products.add(product);
                        } else {
                            System.out.println("Product not found!");
                        }
                    }
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Delivery Date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    Date deliveryDate = java.sql.Date.valueOf(dateInput);
                    orders.add(new Order(orderId, customer, products, deliveryDate));
                    System.out.println("Order placed successfully.");
                    break;
                case 2:
                    System.out.println("Orders:");
                    orders.forEach(System.out::println);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Customer Class
class Customer {
    private int id;
    private String name;
    private int loyaltyPoints;

    public Customer(int id, String name, int loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Customer[ID=" + id + ", Name=" + name + ", LoyaltyPoints=" + loyaltyPoints + "]";
    }
}

// Product Class
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product[ID=" + id + ", Name=" + name + ", Price=" + price + "]";
    }
}

// Order Class
class Order {
    private int id;
    private Customer customer;
    private List<Product> products;
    private Date deliveryDate;

    public Order(int id, Customer customer, List<Product> products, Date deliveryDate) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Order[ID=" + id + ", Customer=" + customer.getName() +
                ", Products=" + products + ", DeliveryDate=" + deliveryDate + "]";
    }
}

// Comparator for Customer Loyalty Points
class CustomerLoyaltyComparator implements Comparator<Customer> {
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c2.getLoyaltyPoints(), c1.getLoyaltyPoints());
    }
}