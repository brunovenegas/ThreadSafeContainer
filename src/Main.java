public class Main {

    public static void main(String args[]) {
        ThreadSafeContainer<String> test = new ThreadSafeContainer<>(5);
        test.printAll();
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
        test.printAll();
    }
}
