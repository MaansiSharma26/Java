// Abstract class Robber
abstract class Robber {
    // Abstract method to be implemented by subclasses
    public abstract void RobbingClass();

    // Default method in the abstract class
    public void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }

    // Abstract methods to be implemented by subclasses
    public abstract int RowHouses(int[] rowHouses);
    public abstract int RoundHouses(int[] roundHouses);
    public abstract int SquareHouse(int[] squareHouses);
    public abstract int MultiHouseBuilding(int[]... houseTypes);
}

// Class JAVAProfessionalRobber inherits from Robber
class JAVAProfessionalRobber extends Robber {
    @Override
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    // Helper method to calculate maximum loot using the dynamic programming approach
    private int robHelper(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev1 = 0, prev2 = 0;
        for (int num : nums) {
            int temp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = temp;
        }
        return prev1;
    }

    @Override
    public int RowHouses(int[] rowHouses) {
        return robHelper(rowHouses);
    }

    @Override
    public int RoundHouses(int[] roundHouses) {
        if (roundHouses == null || roundHouses.length == 0) return 0;
        if (roundHouses.length == 1) return roundHouses[0];
        // Maximize loot by excluding either the first or the last house
        return Math.max(robHelper(java.util.Arrays.copyOfRange(roundHouses, 0, roundHouses.length - 1)),
                        robHelper(java.util.Arrays.copyOfRange(roundHouses, 1, roundHouses.length)));
    }

    @Override
    public int SquareHouse(int[] squareHouses) {
        return robHelper(squareHouses);
    }

    @Override
    public int MultiHouseBuilding(int[]... houseTypes) {
        int totalMax = 0;
        for (int[] houseType : houseTypes) {
            totalMax += robHelper(houseType);
        }
        return totalMax;
    }
}

// Main class to test the implementation
public class RobberGame {
    public static void main(String[] args) {
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();

        // Call the default and overridden methods
        robber.MachineLearning();
        robber.RobbingClass();

        // Test cases
        int[] rowHouses = {1, 2, 3, 0};
        System.out.println("RowHouses: " + robber.RowHouses(rowHouses)); // Expected: 4

        int[] roundHouses = {1, 2, 3, 4};
        System.out.println("RoundHouses: " + robber.RoundHouses(roundHouses)); // Expected: 6

        int[] squareHouses = {5, 10, 2, 7};
        System.out.println("SquareHouse: " + robber.SquareHouse(squareHouses)); // Expected: 17

        int[] type1 = {5, 3, 8, 2};
        int[] type2 = {10, 12, 7, 6};
        int[] type3 = {4, 9, 11, 5};
        int[] type4 = {8, 6, 3, 7};
        System.out.println("MultiHouseBuilding: " + robber.MultiHouseBuilding(type1, type2, type3, type4)); // Expected: 59
    }
}