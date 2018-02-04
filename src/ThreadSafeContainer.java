import com.sun.nio.sctp.ShutdownNotification;

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

    public synchronized void add(E element) throws InterruptedException {
        // If there is no space to put element, wait
        while(isFull()) {
            wait();
        }
        // If an element has been removed and no longer full, notify all threads
        if(!isFull()) {
            notifyAll();
        }

        // If array's current size is 0, set front, end and element to the 0th location
        if(mCurrentSize == 0) {
            mElementArray[mFront] = element;
            mFront = 0;
            mEnd = 0;
            mCurrentSize++;
        }
        // Else keep going with circular array implementation
        else {
            mEnd = (mEnd + 1) % mElementArray.length;
            mElementArray[mEnd] = element;
            mCurrentSize++;
        }

    }

    public synchronized E remove() throws InterruptedException {
        // If array is empty, there is nothing to remove, wait.
        while(isEmpty()) {
            wait();
        }
        // If array is not empty, notify all threads
        if(!isEmpty()) {
            notifyAll();
        }

        // Remove element from the front, set mFront to next element, lower mCurrentSize
        E tempElement = mElementArray[mFront];
        mElementArray[mFront] = null;
        if(mCurrentSize != 1) {
            mFront = (mFront + 1) % mElementArray.length;
        }
        mCurrentSize--;
        return tempElement;
    }

    // Clean this up so that only goes through filled elements
    public synchronized void clear() {

        // Iterate through each element and set array to null
        for(int i = 0; i < mCapacity; i++) {
            if(mElementArray[i] != null) {
                mElementArray[i] = null;
            }
        }

        // Reset Front and End trackers
        mFront = 0;
        mEnd = 0;
    }

    public synchronized void shutdown() throws Exception {
        
    }

    public void printAll() {
        for(int i = 0; i < mCapacity; i++) {
            System.out.println(mElementArray[i]);
        }
        System.out.println("Front: " + mFront);
        System.out.println("End: " + mEnd);
        System.out.println("Length: " + mElementArray.length);
    }

    private boolean isFull() {
        return mCurrentSize == mElementArray.length;
    }

    private boolean isEmpty() {
        return mCurrentSize == 0;
    }

}
