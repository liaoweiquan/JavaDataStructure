package queue;

public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
    }
    public LoopQueue(){
        data = (E[]) new Object[10];
    }


    @Override
    public void enqueue(E e) {

        // queue is full.
        if((tail + 1) % data.length == front){
            resize(getCapacity() << 1);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        ++ size;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity + 1];
        for(int i = 0; i < size; ++ i){
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        -- size;
        if(size == getCapacity() >> 2 && getCapacity() >> 1 != 0){
            resize(getCapacity() >> 1);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("queue.Queue: size = %d, capacity = %d\n", size, getCapacity()));
        sb.append("[");
        for(int i = front; i != tail; i = (i + 1) % data.length){
            sb.append(data[i]);
            if((i + 1) % data.length != tail){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}