public class ThreadSafeContainer<E> {

    // Capacity of array
    private int mCapacity;
    // Queue/Array
    private E[] mElementArray;
    // Front of array
    private int mFront;
    // End of array
    private int mEnd;
    // Current size of array
    private int mCurrentSize;

    public  ThreadSafeContainer(){
        
    }

    public ThreadSafeContainer(int capacity) {
        mCapacity = capacity;
        mElementArray = (E[]) new Object[mCapacity];
        mFront = 0;
        mEnd = 0;
        mCurrentSize = 0;
    }

    public void add(E element) {
        if(mCurrentSize == 0) {
            mElementArray[mFront] = element;
            mCurrentSize++;
        }
        else if(isFull()) {
            System.out.println("Sorry, we are full!");
        }
        else {
            mEnd++;
            mElementArray[mEnd] = element;
            mCurrentSize++;
        }

    }

    public synchronized E remove() {
        return null;
    }

    // Clean this up so that only goes through filled elements
    public synchronized void clear() {
        for(int i = 0; i < mCapacity; i++) {
            if(mElementArray[i] != null) {
                mElementArray[i] = null;
            }
        }
    }

    public void shutdown() throws Exception {
        
    }

    public void printAll() {
        for(int i = 0; i < mCapacity; i++) {
            System.out.println(mElementArray[i]);
        }
        System.out.println("Front: " + mFront);
        System.out.println("End: " + mEnd);
    }

    private boolean isFull() {
        return mCurrentSize == mCapacity;
    }

}
