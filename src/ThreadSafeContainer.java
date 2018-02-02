public class ThreadSafeContainer<T> {

    private T object;

    public  ThreadSafeContainer(){
        
    }

    public ThreadSafeContainer(T t) {
        object = t;
    }
    
}
