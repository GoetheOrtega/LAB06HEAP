public class MaxHeap {
    Integer [] heap;
    int n;

    public MaxHeap(int capacity) {
        heap = new Integer[capacity + 1];
        n = 0;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public int size() {
        return n;
    }
    public void set(int x) {
        if (n == heap.length - 1) {
            resize(2*heap.length);
        }
        n++;
        heap[n] = x;
        swim(n);
    }

    private void swim(int k) {
        while (k > 1 && heap[k / 2] < heap[k]) {
            int temp = heap[k];
            heap[k] = heap[k/2];
            heap[k/2] = temp;
            k = k/2;
        }
    }
    private void resize(int x) {
        Integer [] NewHeap = new Integer[x];
        System.arraycopy(heap, 0, NewHeap, 0, heap.length);
        heap = NewHeap;
    }
}