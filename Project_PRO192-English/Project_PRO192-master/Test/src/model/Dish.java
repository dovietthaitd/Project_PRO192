package model;

public class Dish {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Dish(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return String.format("| %-6s | %-20s | %10.0f đ | SL: %d |",
                id, name, price, quantity);
    }
}