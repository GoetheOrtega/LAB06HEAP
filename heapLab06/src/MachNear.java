public class MachNear {
    private int[] heap;
    private int size;

    public MachNear(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public MachNear(int[] elements) {
        heap = elements;
        size = elements.length;
        buildHeap();
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int[] children(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left >= size && right >= size) {
            return null;
        } else if (right >= size) {
            return new int[]{left};
        } else {
            return new int[]{left, right};
        }
    }

    public void restoreHeapProperty(int i) {
        int largest = i;
        int[] children = children(i);
        if (children != null) {
            for (int child : children) {
                if (child < size && heap[child] > heap[largest]) {
                    largest = child;
                }
            }
            if (largest != i) {
                swap(i, largest);
                restoreHeapProperty(largest);
            }
        }
    }

    public void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) {
            restoreHeapProperty(i);
        }
    }

    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        } else {
            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            restoreHeapProperty(0);
            return max;
        }
    }

    public void add(int element) {
        if (size == heap.length) {
            throw new IllegalStateException("Heap is full");
        } else {
            heap[size] = element;
            size++;
            int i = size - 1;
            while (i > 0 && heap[i] > heap[parent(i)]) {
                swap(i, parent(i));
                i = parent(i);
            }
        }
    }

    public void heapSort() {
        buildHeap();
        for (int i = size - 1; i > 0; i--) {
            swap(0, i);
            size--;
            restoreHeapProperty(0);
        }
        size = heap.length;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
