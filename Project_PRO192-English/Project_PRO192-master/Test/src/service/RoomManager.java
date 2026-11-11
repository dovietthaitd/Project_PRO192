/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.Room;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nguye
 */
public class RoomManager {
    private List<Room> danhSach = new ArrayList<>();

    public void addRoom(Room p) {
        if (findById(p.getId()) != null) {
            System.out.println("❌ Mã phòng đã tồn tại!");
            return;
        }
        danhSach.add(p);
        System.out.println("✅ Thêm phòng thành công!");
    }

    public boolean deleteRoom(String ma) {
        Room p = findById(ma);
        return p != null && danhSach.remove(p);
    }

    public Room findById(String ma) {
        for (Room p : danhSach)
            if (p.getId().equalsIgnoreCase(ma)) return p;
        return null;
    }

    public boolean returnRoom(String ma) {
        Room p = findById(ma);
        if (p == null || !p.isStatus()) return false;
        p.setStatus(false);
        return true;
    }

    public void display() {
        if (danhSach.isEmpty()) { System.out.println("📭 Chưa có phòng."); return; }
        System.out.println("\n===== DANH SÁCH PHÒNG TRÀ =====");
        for (Room p : danhSach) System.out.println(p);
    }
}
