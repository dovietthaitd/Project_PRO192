package model;

public class TeaPot {
    private String id;
    private String name;
    private double rentalPrice;   // giá thuê / giờ
    private int quantity;
    private boolean isRented; // đang được thuê hay không

    public TeaPot(String id, String name, double rentalPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.rentalPrice = rentalPrice;
        this.quantity = quantity;
        this.isRented = false;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getRentalPrice() { return rentalPrice; }
    public int getQuantity() { return quantity; }
    public boolean isRented() { return isRented; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setRented(boolean isRented) { this.isRented = isRented; }

    @Override
    public String toString() {
        return String.format("| %-6s | %-20s | %8.0f đ/h | SL: %d | %s |",
                id, name, rentalPrice, quantity, isRented ? "Đang thuê" : "Sẵn sàng");
    }
}