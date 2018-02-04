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
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();

/*        test.printAll();
        test.remove();
        test.add("fuck");
        test.add("you");
        test.add("bruno");
        test.add("you");
        test.add("suck");
        test.add("bitch");
        test.printAll();
        System.out.println(test.remove());
        test.add("bitch");
        test.printAll();
        test.clear();
        test.printAll();*/
    }
}
