import java.util.ArrayList;

public class ProductService {
    private final ArrayList<Product> products = new ArrayList<>();
    private int nextId = 1; // ID incremental

    public ProductService() {
        addProduct("Laptop", 1200.0, 5);
        addProduct("Smartphone", 800.0, 10);
    }

    public void addProduct(String name, double price, int stock) {
        Product p = new Product(nextId++, name, price, stock);
        products.add(p);
        System.out.println("✅ Product added: " + p);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean updateProduct(int id, String name, double price, int stock) {
        Product p = getProductById(id);
        if (p != null) {
            p.setName(name);
            p.setPrice(price);
            p.setStock(stock);
            return true;
        }
        return false;
    }

    public void deleteProduct(int id) {
        Product p = getProductById(id);
        if (p != null) {
            products.remove(p);
        }

        System.out.println("✅ Product deleted: " + p);
    }

    public ArrayList<Product> searchProductsByName(String query) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
