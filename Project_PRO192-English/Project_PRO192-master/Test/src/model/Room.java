/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Room {
    private String id;
    private String name;
    private String type;
    private int capacity;
    private double price;
    private boolean status;

    public Room(String id, String name, String type, int capacity, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
    }
    
    

    public Room(String id, String name, String type, int capacity, double price, boolean status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-14s | %-9s | %2d người | %9.0f đ/h | %s |",
                id, name, type, capacity, price , status ? "Có khách" : "Trống");
    }
}
