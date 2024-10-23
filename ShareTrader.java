public class ShareTrader {
    // Static variable to store the maximum profit
    private static int maxProfit;

    // Static method to calculate the maximum profit from stock prices
    public static int findMaxProfit(int[] prices) {
        maxProfit = 0;
        int n = prices.length;

        if (n < 2) {
            return maxProfit; // Not enough prices to make a transaction
        }

        // First transaction
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // Buy at prices[i] and sell at prices[j]
                int firstProfit = prices[j] - prices[i];
                if (firstProfit > 0) {
                    // Second transaction
                    for (int k = j + 1; k < n; k++) {
                        for (int l = k + 1; l < n; l++) {
                            // Buy at prices[k] and sell at prices[l]
                            int secondProfit = prices[l] - prices[k];
                            if (secondProfit > 0) {
                                maxProfit = Math.max(maxProfit, firstProfit + secondProfit);
                            }
                        }
                    }
                }
            }
        }
        return maxProfit;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = {10, 22, 5, 75, 65, 80};
        System.out.println("Input: " + java.util.Arrays.toString(prices1));
        System.out.println("Output: " + findMaxProfit(prices1)); // Expected Output: 87

        // Test case 2
        int[] prices2 = {2, 30, 15, 10, 8, 25, 80};
        System.out.println("Input: " + java.util.Arrays.toString(prices2));
        System.out.println("Output: " + findMaxProfit(prices2)); // Expected Output: 100
    }
}