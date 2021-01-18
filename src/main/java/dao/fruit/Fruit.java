package dao.fruit;

import com.fasterxml.jackson.annotation.JsonProperty;
import dao.Commodity;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fruit extends Commodity {
    @JsonProperty(value = "COMMODITY")
    private String commodity;
    @JsonProperty(value = "FIXED_OVERHEAD")
    private double fixedOverhead;
    @JsonProperty(value = "VARIABLE_OVERHEAD")
    private double variableOverhead;
}
