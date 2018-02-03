public class Main {

    public static void main(String args[]) {
        ThreadSafeContainer<String> test = new ThreadSafeContainer<>(5);
        test.add("fuck");
        test.add("you");
        test.add("bruno");
        test.add("you");
        test.add("suck");
        test.printAll();
    }
}
