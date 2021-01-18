package dto.fruit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class FruitDTO extends CommodityDTO {
    private String commodity;
    private double totalVariableCost;
    private double totalFixedCost;
    private double totalCost;
}
