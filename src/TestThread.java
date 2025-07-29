// TestThread.java
public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("--- Kịch bản 1: Chạy đồng thời (không có join()) ---");
        OddThread oddThread1 = new OddThread();
        EvenThread evenThread1 = new EvenThread();

        oddThread1.start(); // Bắt đầu luồng lẻ
        evenThread1.start(); // Bắt đầu luồng chẵn

        // Kịch bản này, các số lẻ và chẵn sẽ được in ra xen kẽ,
        // thứ tự phụ thuộc vào bộ lập lịch của CPU.

        try {
            // Chờ các luồng ở kịch bản 1 hoàn thành để đầu ra không bị lẫn lộn
            oddThread1.join();
            evenThread1.join();
            System.out.println("\n--- Kịch bản 1 đã hoàn thành ---");
            Thread.sleep(100); // Khoảng dừng nhỏ để phân biệt các kịch bản
        } catch (InterruptedException e) {
            System.err.println("Main thread bị ngắt.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- Kịch bản 2: Sử dụng join() để số lẻ xuất trước ---");
        OddThread oddThread2 = new OddThread();
        EvenThread evenThread2 = new EvenThread();

        oddThread2.start(); // Bắt đầu luồng lẻ

        try {
            // Sử dụng join() để main thread chờ oddThread2 hoàn thành
            // trước khi bắt đầu evenThread2
            oddThread2.join(); // main thread sẽ bị block tại đây cho đến khi oddThread2 kết thúc
            System.out.println("OddThread đã kết thúc. Bắt đầu EvenThread.");
            evenThread2.start(); // Chỉ khi oddThread2 xong thì EvenThread mới bắt đầu
            evenThread2.join(); // Chờ evenThread2 kết thúc
        } catch (InterruptedException e) {
            System.err.println("Main thread bị ngắt khi chờ luồng.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- Kịch bản 2 đã hoàn thành ---");
        System.out.println("Chương trình TestThread kết thúc.");
    }
}