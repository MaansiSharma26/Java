import java.util.*;

public class FrequencyAnalyzer {
    
    private static int[] numbers;

    public static void findTopKNumbers(int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();


        for (int num : numbers) {
            
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }


        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());

        entryList.sort((a, b) -> {
            int frequencyComparison = b.getValue().compareTo(a.getValue());
            if (frequencyComparison != 0) {
                return frequencyComparison;
            }
            return b.getKey().compareTo(a.getKey()); 
        });

        for (int i = 0; i < k && i < entryList.size(); i++) {
            System.out.print(entryList.get(i).getKey() + " ");
        }
        System.out.println();
    }

    
    public static void main(String[] args) {
        numbers = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        int k1 = 2;
        System.out.println("Input: " + Arrays.toString(numbers) + ", K = " + k1);
        System.out.print("Output: ");
        findTopKNumbers(k1); 

        
        numbers = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        int k2 = 4;
        System.out.println("Input: " + Arrays.toString(numbers) + ", K = " + k2);
        System.out.print("Output: ");
        findTopKNumbers(k2); 
    }
}