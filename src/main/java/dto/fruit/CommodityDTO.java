package dto.fruit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public abstract class CommodityDTO {
    private String country;
}
