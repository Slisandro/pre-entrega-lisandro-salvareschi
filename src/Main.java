import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    // Services
    static UserService userService = new UserService();
    static ProductService productService = new ProductService();
    static CartService cartService = new CartService();

    // Variable that determines whether a user/admin is logged in
    static User userLogged;

    // Variable to close the app
    static boolean breakApp = false; //

    public static void main(String[] args) {
        int option;
        while (!breakApp) {
            showMenu(userLogged);
            option = input.nextInt();
            input.nextLine();
            showOption(option);
        }
    }

    // --------- AUTHENTICATION METHODS --------- //

    // Method register user
    public static void registerUser() {
        System.out.println("Insert your name");
        String name = input.nextLine();

        System.out.println("Insert your username:");
        String username = input.nextLine();

        System.out.println("Insert your password:");
        String password = input.nextLine();

        userService.registerUser(name, username, password);

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    // Method Login user
    public static void login() {
        while (true) {

            System.out.println("Insert your username:");
            String username = input.nextLine();

            System.out.println("Insert your password:");
            String password = input.nextLine();

            User user = userService.loginUser(username, password);

            if (user != null) {
                userLogged = user;
                break;
            } else {
                System.out.println("Try again or type 'exit' as username to cancel.");
                String exitCheck = input.nextLine();
                if (exitCheck.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        }
    }

    // ------------ PRODUCTS METHODS ----------- //

    // Method to obtain a list of products
    public static void seeProducts() {
        ArrayList<Product> products = productService.getProducts();

        if (productService.getProducts().isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("======== Products ========");

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }

        System.out.println("===========================");

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    // Method for create product
    private static void addProduct() {
        System.out.println("Insert product name:");
        String name = input.nextLine();

        System.out.println("Insert product price:");
        double price = input.nextDouble();

        System.out.println("Insert product stock:");
        int stock = input.nextInt();

        input.nextLine();

        productService.addProduct(name, price, stock);

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    // Method for update product
    private static void updateProduct() {
        System.out.println("Insert product ID to update:");
        int id = input.nextInt();
        input.nextLine();

        Product product = productService.getProductById(id);

        if (product == null) {
            System.out.println("❌ Product not found.");
            return;
        }

        System.out.println("\n=== Current product data ===");
        System.out.println("Name: " + product.getName());
        System.out.println("Price: $" + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        System.out.println("============================");

        System.out.println("\nInsert new product name:");
        String newName = input.nextLine();

        System.out.println("Insert new product price:");
        double newPrice = input.nextDouble();

        System.out.println("Insert new product stock:");
        int newStock = input.nextInt();
        input.nextLine();

        boolean success = productService.updateProduct(id, newName, newPrice, newStock);

        if (success) {
            System.out.println("✅ Product updated successfully!");
        } else {
            System.out.println("❌ Failed to update product.");
        }
    }

    // Method for delete product
    private static void deleteProduct() {
        System.out.println("Insert product id:");
        int id = input.nextInt();

        input.nextLine();

        productService.deleteProduct(id);

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    // Method for search products by name
    private static void getProductsByName() {
        System.out.println("Insert product name to search:");
        String query = input.nextLine();

        ArrayList<Product> results = productService.searchProductsByName(query);

        if (results.isEmpty()) {
            System.out.println("❌ No products found matching '" + query + "'.");
            return;
        }

        System.out.println("======== Search Results ========");

        for (Product p : results) {
            System.out.println(p);
        }

        System.out.println("================================");

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    // ------------- USERS METHODS ------------- //

    // Method to obtain a list of users
    private static void seeUsers() {
        ArrayList<User> users = userService.getUsers();

        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        System.out.println("=== Users ===");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            System.out.println((i + 1) + ". " + u.name + " (" + u.username + ") - " + u.role);
        }

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    // -------------- CART METHODS ------------- //

    private static void viewAllCarts() {
        System.out.println("======== All User Carts ========");

        for (User user : cartService.getAllUsersWithCarts()) {
            Cart cart = cartService.getCart(user);
            System.out.println("🧑 " + user);

            if (cart.getItems().isEmpty()) {
                System.out.println("  🛒 Cart is empty.");
            } else {
                for (CartItem item : cart.getItems()) {
                    System.out.println("  " + item);
                }
                System.out.println("  Total: $" + cart.getTotal());
            }
            System.out.println("-------------------------------");
        }

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    private static void addProductToCart() {
        seeProducts();

        System.out.println("Insert product ID to add to cart:");
        int productId = input.nextInt();
        input.nextLine();

        Product product = productService.getProductById(productId);

        if (product == null) {
            System.out.println("❌ Product not found.");
            System.out.println("Press Enter to continue...");
            input.nextLine();
            return;
        }

        System.out.println("Insert quantity:");
        int quantity = input.nextInt();
        input.nextLine();

        if (quantity <= 0) {
            System.out.println("❌ Quantity must be greater than 0.");
            System.out.println("Press Enter to continue...");
            input.nextLine();
            return;
        }

        if (quantity > product.getStock()) {
            System.out.println("❌ Not enough stock. Available: " + product.getStock());
            System.out.println("Press Enter to continue...");
            input.nextLine();
            return;
        }

        product.setStock(product.getStock() - quantity);

        cartService.addProduct(userLogged, product, quantity);
        System.out.println("✅ Added " + quantity + " x " + product.getName() + " to your cart.");

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }

    private static void viewCart() {
        Cart cart = cartService.getCart(userLogged);

        if (cart.getItems().isEmpty()) {
            System.out.println("🛒 Your cart is empty.");
            return;
        }

        System.out.println("======== Your Cart ========");
        for (CartItem item : cart.getItems()) {
            System.out.println(item); // usa el toString() de CartItem
        }
        System.out.println("Total: $" + cart.getTotal());
        System.out.println("===========================");
    }

    // --------------- UTILITIES --------------- //

    /*

        The showMenu method receives as a parameter the variable userLogged.

        * If userLogged is null, shows the main menu
        * If userLogged.role is admin, shows the admin menu
        * If userLogged.role is user, shows the user menu

    */

    public static void showMenu(User userLogged) {
        if (userLogged == null) {
            System.out.println(menuWithoutUser);
            return;
        }

        if ("admin".equals(userLogged.role)) {
            System.out.println("===================================");
            System.out.println("Hello " + userLogged.name + "!");
            System.out.println("===================================");
            System.out.println(menuAsAdmin);
        } else {
            System.out.println("===================================");
            System.out.println("Hello " + userLogged.name + "!");
            System.out.println("===================================");
            System.out.println(menuAsUser);
        }
    }

    /*

        The showOption method receives as a parameter the variable option.
        Depending on the menu shown, it will perform one action or another.

        * If userLogged is null, shows the main options
        * If userLogged.role is admin, shows the admin options
        * If userLogged.role is user, shows the user options

    */

    public static void showOption(int option) {
        if (userLogged == null) {
            switch (option) {
                case 1 -> registerUser();
                case 2 -> login();
                case 3 -> seeProducts();
                case 4 -> getProductsByName();
                case 9 -> {
                    System.out.println("👋 Thank you for visiting the store.");
                    breakApp = true;
                }
                default -> System.out.println("❌ Invalid option.\n");
            }
        } else {
            if (userLogged.role.equals("user")) {
                switch (option) {
                    case 1 -> seeProducts();
                    case 2 -> addProductToCart();
                    case 3 -> getProductsByName();
                    case 4 -> viewCart();
                    case 8 -> {
                        userLogged = null;
                        System.out.println("👋 Session closed!");
                    }
                    case 9 -> {
                        System.out.println("👋 Thank you for visiting the store.");
                        breakApp = true;
                    }
                    default -> System.out.println("❌ Invalid option.\n");
                }

            } else if (userLogged.role.equals("admin")) {
                switch (option) {
                    case 1 -> viewAllCarts();
                    case 2 -> seeUsers();
                    case 3 -> seeProducts();
                    case 4 -> addProduct();
                    case 5 -> updateProduct();
                    case 6 -> deleteProduct();
                    case 7 -> getProductsByName();

                    case 8 -> {
                        userLogged = null;
                        System.out.println("👋 Session closed!");
                    }
                    case 9 -> {
                        System.out.println("👋 Thank you for visiting the store.");
                        breakApp = true;
                    }
                    default -> System.out.println("❌ Invalid option.\n");
                }
            }
        }
    }

    // --------------- CONSTANTS --------------- //

    static String menuWithoutUser = """
            ===================================
            MANAGEMENT SYSTEM - TECH LAB
            ===================================
            
            Welcome to our e-commerce!
            
            Select an option to continue
            
            1 - Sign Up
            2 - Sign in
            3 - See our products
            4 - Search product by name
            
            9 - Close app
            """;

    static String menuAsUser = """
            Select an option to continue
            
            1 - See our products
            2 - Add product to cart by ID
            3 - Search products by name
            4 - View cart
            
            8 - Log out
            9 - Close app
            """;

    static String menuAsAdmin = """
             Select an option to continue
            
             1 - See carts
             2 - See users
             3 - See products
             
             4 - Add product
             5 - Update product
             6 - Delete product
             7 - Search products by name
            
             8 - Log out
             9 - Close app
            """;
}


