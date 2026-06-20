import java.util.*;

class Product {
    int productId;
    String productName;
    String category;

    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}

public class EcommerceSearchFunction {

    public static Product linearSearch(Product[] products, String target) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(target)) {
                return products[i];
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String target) {

        int left = 0;
        int right = products.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            int compare =
                    products[mid].productName.compareToIgnoreCase(target);

            if (compare == 0) {
                return products[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter product name(Laptop/Phone/Shoes/Watch) : ");
        String search=sc.next();

        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Phone", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Watch", "Accessories")
        };

        System.out.println("Linear Search:");

        Product result1 = linearSearch(products, search);

        if (result1 != null) {
            System.out.println("Product ID: " + result1.productId);
            System.out.println("Product Name: " + result1.productName);
            System.out.println("Category: " + result1.category);
        } else {
            System.out.println("Product not found");
        }

        Arrays.sort(products,
                Comparator.comparing(p -> p.productName));

        System.out.println("\nBinary Search:");

        Product result2 = binarySearch(products, search);

        if (result2 != null) {
            System.out.println("Product ID: " + result2.productId);
            System.out.println("Product Name: " + result2.productName);
            System.out.println("Category: " + result2.category);
        } else {
            System.out.println("Product not found");
        }
        sc.close();
    }
}