package service;

import model.Tea;
import java.util.ArrayList;
import java.util.List;

public class TeaManager {
    private List<Tea> items = new ArrayList<>();

    public void add(Tea tea) {
        // kiểm tra trùng mã
        for (Tea item : items) {
            if (item.getId().equalsIgnoreCase(tea.getId())) {
                System.out.println("❌ Mã trà đã tồn tại!");
                return;
            }
        }
        items.add(tea);
        System.out.println("✅ Thêm trà thành công!");
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
    public Tea findById(String id) {
        for (Tea tea : items) {
            if (tea.getId().equalsIgnoreCase(id)) return tea;
        }
        return null;
    }

    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("📭 Danh sách trà trống.");
            return;
        }
        System.out.println("\n===== DANH SÁCH TRÀ =====");
        for (Tea tea : items) System.out.println(tea);
    }

    public List<Tea> getAll() { return items; }
}