package Week7;

/**
 * Problem 5: The Shopping Cart
 * Encapsulates internal item prices, providing computed read-only total and item count.
 */
public class Cart {
    private final String cartId;
    private double[] itemPrices;
    private int itemCount;

    public Cart(String cartId, int capacity) {
        this.cartId = cartId;
        this.itemPrices = new double[Math.max(0, capacity)];
        this.itemCount = 0;
    }

    public String getCartId() {
        return this.cartId;
    }

    public void addItem(double price) {
        if (this.itemCount < this.itemPrices.length && price >= 0) {
            this.itemPrices[this.itemCount++] = price;
        }
    }

    public double getTotal() {
        double total = 0.0;
        for (int i = 0; i < this.itemCount; i++) {
            total += this.itemPrices[i];
        }
        return total;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public static void main(String[] args) {
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);

        double total = cart.getTotal();
        if (total == (long) total) {
            System.out.println("cart.getTotal() -> " + (long) total);
        } else {
            System.out.println("cart.getTotal() -> " + total);
        }
        System.out.println("cart.getItemCount() -> " + cart.getItemCount());
    }
}
