import model.TeaPot;
import model.Dish;
import model.Tea;
import model.Room;
import service.TeaPotManager;
import service.BillManager;
import service.DishManager;
import service.TeaManager;

import java.util.Scanner;
import service.RoomManager;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static TeaManager teaManager = new TeaManager();
    static TeaPotManager teaPotManager = new TeaPotManager();
    static DishManager dishManager = new DishManager();
    static RoomManager roomManager = new RoomManager();
    static BillManager billManager = new BillManager(teaManager, teaPotManager, dishManager, roomManager);  

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║   🍵 QUẢN LÝ PHÒNG TRÀ 🍵 ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Quản lý trà           ║");
            System.out.println("║ 2. Quản lý ấm trà        ║");
            System.out.println("║ 3. Quản lý món ăn kèm    ║");
            System.out.println("║ 4. Tạo hóa đơn           ║");
            System.out.println("║ 0. Thoát                 ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Chọn: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": teaMenu(); break;
                case "2": teaPotMenu(); break;
                case "3": dishMenu(); break;
                case "4": billManager.createBill(sc); break;
                case "0":
                    System.out.println("👋 Tạm biệt!");
                    return;
                default:
                    System.out.println("⚠️ Lựa chọn không hợp lệ.");
            }
        }
    }

    // ================== MENU TRÀ ==================
    static void teaMenu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ TRÀ ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm trà");
            System.out.println("3. Xóa trà");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": teaManager.displayAll(); break;
                case "2":
                    System.out.print("Mã: "); String id = sc.nextLine();
                    System.out.print("Tên: "); String name = sc.nextLine();
                    System.out.print("Giá: "); double price = Double.parseDouble(sc.nextLine());
                    System.out.print("Số lượng: "); int quantity = Integer.parseInt(sc.nextLine());
                    teaManager.add(new Tea(id, name, price, quantity));
                    break;
                case "3":
                    System.out.print("Nhập mã cần xóa: ");
                    String idToRemove = sc.nextLine();
                    System.out.println(teaManager.remove(idToRemove) ? "✅ Đã xóa!" : "❌ Không tìm thấy!");
                    break;
                case "0": return;
            }
        }
    }

    // ================== MENU ẤM ==================
    static void teaPotMenu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ẤM TRÀ ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm ấm");
            System.out.println("3. Xóa ấm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": teaPotManager.displayAll(); break;
                case "2":
                    System.out.print("Mã: "); String id = sc.nextLine();
                    System.out.print("Tên: "); String name = sc.nextLine();
                    System.out.print("Giá thuê/giờ: "); double price = Double.parseDouble(sc.nextLine());
                    System.out.print("Số lượng: "); int quantity = Integer.parseInt(sc.nextLine());
                    teaPotManager.add(new TeaPot(id, name, price, quantity));
                    break;
                case "3":
                    System.out.print("Nhập mã cần xóa: ");
                    String idToRemove = sc.nextLine();
                    System.out.println(teaPotManager.remove(idToRemove) ? "✅ Đã xóa!" : "❌ Không tìm thấy!");
                    break;
                case "0": return;
            }
        }
    }

    // ================== MENU MÓN ==================
    static void dishMenu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ MÓN ĂN KÈM ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm món");
            System.out.println("3. Xóa món");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": dishManager.displayAll(); break;
                case "2":
                    System.out.print("Mã: "); String id = sc.nextLine();
                    System.out.print("Tên: "); String name = sc.nextLine();
                    System.out.print("Giá: "); double price = Double.parseDouble(sc.nextLine());
                    System.out.print("Số lượng: "); int quantity = Integer.parseInt(sc.nextLine());
                    dishManager.add(new Dish(id, name, price, quantity));
                    break;
                case "3":
                    System.out.print("Nhập mã cần xóa: ");
                    String idToRemove = sc.nextLine();
                    System.out.println(dishManager.remove(idToRemove) ? "✅ Đã xóa!" : "❌ Không tìm thấy!");
                    break;
                case "0": return;
            }
        }
    }

    // ================== DỮ LIỆU MẪU ==================
    static void initializeSampleData() {
        // Trà phổ biến
        teaManager.add(new Tea("T01", "Phổ nhĩ(10g/phần)", 350000, 50));
        teaManager.add(new Tea("T02", "Phổ nhĩ quýt(1 viên)", 50000, 30));
        teaManager.add(new Tea("T03", "Đại Hồng Bào", 45000, 40));
        teaManager.add(new Tea("T04", "Bạch Hào Ngân Châm", 40000, 60));
        teaManager.add(new Tea("T05", "Mông Đỉnh Hoàng Nha", 30000, 25));

        // Ấm trà
        teaPotManager.add(new TeaPot("A01", "Ấm tử sa nhỏ", 200000, 10));
        teaPotManager.add(new TeaPot("A02", "Ấm Sứ", 250000, 8));
        teaPotManager.add(new TeaPot("A03", "Ấm Tử Đào", 150000, 12));
        teaPotManager.add(new TeaPot("A04", "Ấm Nê Hưng", 200000, 10));
        teaPotManager.add(new TeaPot("A05", "Ấm Vinh Xương", 200000, 10));

        // Món ăn kèm
        dishManager.add(new Dish("M01", "Bánh đậu xanh", 15000, 100));
        dishManager.add(new Dish("M02", "Bánh trung thu", 30000, 50));
        dishManager.add(new Dish("M03", "Hạt hướng dương", 10000, 200));
        dishManager.add(new Dish("M04", "Mứt sen", 20000, 80));
        
        roomManager.addRoom(new Room("P01", "Phòng Trúc", "Thường", 4, 500000));
        roomManager.addRoom(new Room("P02", "Phòng Mai", "Thường", 6, 800000));
        roomManager.addRoom(new Room("P03", "Phòng Lan", "VIP", 8, 1200000));
        roomManager.addRoom(new Room("P04", "Sân vườn Tùng", "Sân vườn", 12, 2000000));
    }
}