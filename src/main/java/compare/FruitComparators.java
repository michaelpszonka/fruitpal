package compare;

import dto.fruit.FruitDTO;

import java.util.Comparator;

public class FruitComparators {
    public static class TotalFruitCostComparator implements Comparator<FruitDTO> {
        @Override
        public int compare(FruitDTO f1, FruitDTO f2) {
            if(f1.getTotalCost() <= f2.getTotalCost()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
