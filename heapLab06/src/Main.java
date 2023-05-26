import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(3);

        maxHeap.set(2);
        maxHeap.set(3);
        maxHeap.set(5);

        System.out.println(maxHeap.isEmpty());
        System.out.println(maxHeap.size());
        System.out.println(Arrays.toString(maxHeap.heap));


        String fileName = "src/input.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            int n = Integer.parseInt(reader.readLine());
            String [] elements = reader.readLine().split(" ");


            int[] heap = new int[n];
            for (int i = 0; i < n; i++) {
                heap[i] = Integer.parseInt(elements[i]);
            }
            if (isMaxHeap(heap, n)) {
                System.out.println("Yes!");
            } else {
                System.out.println("No!");
            }


            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(elements[i]);
            }
            convertToMaxHeap(array);
            System.out.println(Arrays.toString(array));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean isMaxHeap(int[] heap, int n) {
        for (int i = 0; i < n; i++) {
            if (2 * i + 1 < n && heap[i] < heap[2 * i + 1]) {
                return false;
            }

            if (2 * i + 2 < n && heap[i] < heap[2 * i + 2]) {
                return false;
            }
        }

        return true;
    }
    private static void convertToMaxHeap(int[] array) {
        int n = array.length;

        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(array, i, largest);
            heapify(array, n, largest);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}