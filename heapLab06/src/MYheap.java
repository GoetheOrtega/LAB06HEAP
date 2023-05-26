import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MYheap {
    public static void main(String[] args) {
        String inputFileName = "src/File";
        String outputFileName = "src/output";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            int numOperations = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
            String line;

            while ((line = br.readLine()) != null) {
                String[] operation = line.split(" ");
                char operationType = operation[0].charAt(0);

                switch (operationType) {
                    case 'A':
                        int elementToAdd = Integer.parseInt(operation[1]);
                        priorityQueue.add(elementToAdd);
                        break;
                    case 'X':
                        if (!priorityQueue.isEmpty()) {
                            int minElement = priorityQueue.poll();
                            bw.write(String.valueOf(minElement));
                            bw.newLine();
                        } else {
                            bw.write("*");
                            bw.newLine();
                        }
                        break;
                    case 'D':
                        int index = Integer.parseInt(operation[1]);
                        int newValue = Integer.parseInt(operation[2]);
                        Object[] queueArray = priorityQueue.toArray();
                        if (index >= 0 && index < queueArray.length) {
                            priorityQueue.remove(queueArray[index]);
                            priorityQueue.add(newValue);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


