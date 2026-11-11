package service;

import model.Dish;
import java.util.ArrayList;
import java.util.List;

public class DishManager {
    private List<Dish> items = new ArrayList<>();

    public void add(Dish dish) {
        for (Dish item : items) {
            if (item.getId().equalsIgnoreCase(dish.getId())) {
                System.out.println("❌ Mã món đã tồn tại!");
                return;
            }
        }
        items.add(dish);
        System.out.println("✅ Thêm món thành công!");
    }

    public boolean remove(String id) {
    for (int i = 0; i < items.size(); i++) {
        if (items.get(i).getId().equalsIgnoreCase(id)) {
            items.remove(i);
            return true;
        }
    }
    return false;
    }

    public Dish findById(String id) {
        for (Dish dish : items) {
            if (dish.getId().equalsIgnoreCase(id)) return dish;
        }
        return null;
    }

    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("📭 Danh sách món trống.");
            return;
        }
        System.out.println("\n===== DANH SÁCH MÓN ĂN KÈM =====");
        for (Dish dish : items) System.out.println(dish);
    }

    public List<Dish> getAll() { return items; }
}