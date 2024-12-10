interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}
abstract class RainySeasonConservation implements WaterConservationSystem {

}


class CityBlockConservation extends RainySeasonConservation {
    public int calculateTrappedWater(int[] blockHeights) {
        if (blockHeights==null||blockHeights.length == 0) {
            return 0;
        }

        int n = blockHeights.length;
        int[] maxHeightLeft = new int[n];
        int[] maxHeightRight = new int[n];
        int trappedWater = 0;
        maxHeightLeft[0] = blockHeights[0];
        for (int i = 1; i < n; i++) {
            maxHeightLeft[i] = Math.max(maxHeightLeft[i - 1], blockHeights[i]);
        }
        maxHeightRight[n - 1] = blockHeights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxHeightRight[i] = Math.max(maxHeightRight[i + 1], blockHeights[i]);
        }
        for (int i = 0; i < n; i++) {
            trappedWater += Math.min(maxHeightLeft[i], maxHeightRight[i]) - blockHeights[i];
        }
        return trappedWater;
    }
}


public class Cityscape {
    public static void main(String[] args) {
        WaterConservationSystem conservationSystem = new CityBlockConservation();
        int[] blockHeights = {3,0,2,0,4};
        int trappedWater = conservationSystem.calculateTrappedWater(blockHeights);
        System.out.println("Trapped Water: " + trappedWater);
    }
}