package model;

public class BillDetail {
    private String type;       // "Trà", "Ấm thuê", "Đồ ăn kèm"
    private String name;
    private double unitPrice;
    private int quantity;       // số lượng (hoặc số giờ nếu là ấm)
    private double totalPrice;  // = donGia * soLuong

    public BillDetail(String type, String name, double unitPrice, int quantity) {
        this.type = type;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = unitPrice * quantity;
    }

    public String getType()      { return type; }
    public String getName()       { return name; }
    public double getUnitPrice()    { return unitPrice; }
    public int getQuantity()      { return quantity; }
    public double getTotalPrice() { return totalPrice; }
}