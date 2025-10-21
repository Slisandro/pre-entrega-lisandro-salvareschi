import java.util.HashMap;
import java.util.Set;

public class CartService {

    private final HashMap<User, Cart> userCarts;

    public CartService() {
        userCarts = new HashMap<>();
    }

    public Set<User> getAllUsersWithCarts() {
        return userCarts.keySet();
    }

    public Cart getCart(User user) {
        Cart cart;

        if (userCarts.containsKey(user)) {
            cart = userCarts.get(user);
        } else {
            cart = new Cart();
            userCarts.put(user, cart);
        }

        return cart;
    }

    public void addProduct(User user, Product product, int quantity) {
        Cart cart = getCart(user);
        cart.addProduct(product, quantity);
        System.out.println("🛒 Product added to cart!");
    }
}
