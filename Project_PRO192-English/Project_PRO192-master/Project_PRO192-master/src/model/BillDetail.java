package model;

public class BillDetail {
    private String type;       
    private String name;
    private String unit;       // <--- Phải có dòng này
    private double unitPrice;  
    private int quantity;      
    private double totalPrice; 

    public BillDetail(String type, String name, String unit, double unitPrice, int quantity) {
        this.type = type;
        this.name = name;
        this.unit = unit;      // <--- Phải gán giá trị
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = unitPrice * quantity;
    }

    public String getType()      { return type; }
    public String getName()      { return name; }
    public String getUnit()      { return unit; }       // <--- Phải có hàm này
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity()     { return quantity; }
    public double getTotalPrice(){ return totalPrice; }
}