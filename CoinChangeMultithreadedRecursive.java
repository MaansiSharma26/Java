import java.util.concurrent.atomic.AtomicInteger;

public class CoinChangeMultithreadedRecursive {
    static class CoinWorker extends Thread {
        private int[] coins;
        private int startIndex;
        private int sum;
        private AtomicInteger resultCounter;

        public CoinWorker(int[] coins, int startIndex, int sum, AtomicInteger resultCounter) {
            this.coins = coins;
            this.startIndex = startIndex;
            this.sum = sum;
            this.resultCounter = resultCounter;
        }

        private void countWays(int index, int currentSum) {
            if (currentSum == 0) {
                resultCounter.incrementAndGet(); // Found a valid combination
                return;
            }
            if (currentSum < 0 || index >= coins.length) {
                return; // No valid combination
            }

            // Include the current coin
            countWays(index, currentSum - coins[index]);

            // Exclude the current coin and move to the next
            countWays(index + 1, currentSum);
        }

        @Override
        public void run() {
            countWays(startIndex, sum);
        }
    }

    public static int countWays(int[] coins, int sum, int numThreads) throws InterruptedException {
        AtomicInteger resultCounter = new AtomicInteger(0);

        // Create threads for computation
        Thread[] threads = new Thread[numThreads];
        for (int t = 0; t < numThreads; t++) {
            int startIndex = t * (coins.length / numThreads); // Divide coins among threads
            int endIndex = (t + 1) * (coins.length / numThreads);
            if (t == numThreads - 1) {
                endIndex = coins.length; // Ensure the last thread processes all remaining coins
            }

            threads[t] = new CoinWorker(coins, startIndex, sum, resultCounter);
            threads[t].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            if (thread != null) {
                thread.join();
            }
        }

        return resultCounter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 3, sum = 4;
        int[] coins = {1, 2, 3};

        int numThreads = 2; // Number of threads to use
        int result = countWays(coins, sum, numThreads);

        System.out.println("Number of ways to make sum " + sum + ": " + result);
    }
}