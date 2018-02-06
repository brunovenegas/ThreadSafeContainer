public class Main {

    public static void main(String args[]) {
        ThreadSafeContainer<String> test = new ThreadSafeContainer<>(5);
        Runnable producer = new Runnable() {
            public void run() {
                try {
                    test.add("A");
                    test.add("B");
                    test.add("C");
                    test.add("D");
                    test.add("E");
                    test.add("F");
                    test.add("G");
                    test.add("H");
                    test.shutdown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ShutdownException e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        Runnable consumer = new Runnable() {
            public void run() {
                try {
                    System.out.println("Removed item: " + test.remove());
                    System.out.println("Removed item: " + test.remove());

                    System.out.println("Removed item: " + test.remove());
                    System.out.println("Removed item: " + test.remove());
                    System.out.println("Removed item: " + test.remove());
                    System.out.println("Removed item: " + test.remove());
                    System.out.println("Removed item: " + test.remove());
                    System.out.println("Removed item: " + test.remove());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ShutdownException e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
