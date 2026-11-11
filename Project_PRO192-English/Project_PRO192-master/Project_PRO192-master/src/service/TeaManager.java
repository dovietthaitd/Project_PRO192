package service;

import model.Tea;
import java.util.ArrayList;
import java.util.List;

public class TeaManager {
    private List<Tea> teaList = new ArrayList<>();

    public void add(Tea t) {
        for (Tea x : teaList) {
            if (x.getId().equalsIgnoreCase(t.getId())) {
                System.out.println("❌ Tea ID already exists!");
                return;
            }
        }
        teaList.add(t);
        System.out.println("✅ Tea added successfully!");
    }

    public boolean remove(String id) {
        return teaList.removeIf(t -> t.getId().equalsIgnoreCase(id));
    }

    public Tea findById(String id) {
        for (Tea t : teaList) {
            if (t.getId().equalsIgnoreCase(id)) return t;
        }
        return null;
    }

    public void display() {
        if (teaList.isEmpty()) {
            System.out.println("📭 Tea list is empty.");
            return;
        }
        System.out.println("\n===== TEA LIST =====");
        for (Tea t : teaList) System.out.println(t);
    }
}