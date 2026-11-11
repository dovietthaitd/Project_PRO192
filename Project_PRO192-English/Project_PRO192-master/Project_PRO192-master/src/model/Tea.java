package model;

public class Tea {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String unit; // Thêm thuộc tính Đơn vị

    // Sửa lại hàm khởi tạo để nhận thêm tham số unit
    public Tea(String id, String name, double price, int quantity, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getUnit() { return unit; } // Thêm hàm getUnit()

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        // Hiển thị đơn vị ra ngoài danh sách (Ví dụ: 80000 VND / 100g)
        return String.format("| %-6s | %-22s | %10.0f đ / %-5s | SL: %d |",
                id, name, price, unit, quantity);
    }
}