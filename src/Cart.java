import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void updateQuantity(int productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CartItem item : items) {
            sb.append(item).append("\n");
        }
        sb.append("Total: $").append(getTotal());
        return sb.toString();
    }
}
