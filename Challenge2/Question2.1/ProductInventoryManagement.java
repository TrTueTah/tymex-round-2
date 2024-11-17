import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductInventoryManagement {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 999.99, 5));
        products.add(new Product("Smartphone", 499.99, 10));
        products.add(new Product("Tablet", 299.99, 0));
        products.add(new Product("Smartwatch", 199.99, 3));

        DecimalFormat df = new DecimalFormat("#.##");
        double totalInventoryValue = totalInventoryValue(products);
        String formattedNumber = df.format(totalInventoryValue);
        System.out.println("Total inventory value: " + formattedNumber);

        System.out.println("Most Expensive product is " + getMostExpensiveProduct(products));

        System.out.println("Is 'Headphone' in stock?: " + isInStock(products, "Headphone"));

        System.out.println("Products sorted by price (ascending): ");
        products = sortProducts(products, "Price", true);
        for(Product product : products) {
            System.out.println(product.getName() + ": price " + product.getPrice() + ", " + product.getQuantity());
        }

        System.out.println("Products sorted by quantity (descending): ");
        products = sortProducts(products, "Quantity", false);
        for(Product product : products) {
            System.out.println(product.getName() + ": price " + product.getPrice() + ", " + product.getQuantity());
        }
    }

    public static double totalInventoryValue(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    public static String getMostExpensiveProduct(List<Product> products) {
        Product mostExpensiveProduct = products.get(0);
        for (Product product : products) {
            if(product.getPrice() > mostExpensiveProduct.getPrice()) {
                mostExpensiveProduct = product;
            }
        }
        return mostExpensiveProduct.getName();
    }

    public static boolean isInStock(List<Product> products, String productName) {
        for(Product product : products) {
            if(product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
    public static List<Product> sortProducts(List<Product> products, String option, boolean ascending) {
        if(option.equals("Price")) {
            products.sort((p, q) -> ascending ? Double.compare(p.getPrice(), q.getPrice()) : Double.compare(q.getPrice(), p.getPrice()));
        } else {
            products.sort((p, q) -> ascending ? Double.compare(p.getQuantity(), q.getQuantity()) : Double.compare(q.getQuantity(), p.getQuantity()));
        }
        return products;
    }
}
