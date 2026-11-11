package service;

import model.TeaPot;
import java.util.ArrayList;
import java.util.List;

public class TeaPotManager {
    private List<TeaPot> items = new ArrayList<>();

    public void add(TeaPot teaPot) {
        for (TeaPot item : items) {
            if (item.getId().equalsIgnoreCase(teaPot.getId())) {
                System.out.println("❌ Mã ấm đã tồn tại!");
                return;
            }
        }
        items.add(teaPot);
        System.out.println("✅ Thêm ấm thành công!");
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

    public TeaPot findById(String id) {
        for (TeaPot teaPot : items) {
            if (teaPot.getId().equalsIgnoreCase(id)) return teaPot;
        }
        return null;
    }

    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("📭 Danh sách ấm trống.");
            return;
        }
        System.out.println("\n===== DANH SÁCH ẤM TRÀ =====");
        for (TeaPot teaPot : items) System.out.println(teaPot);
    }

    public List<TeaPot> getAll() { return items; }
}