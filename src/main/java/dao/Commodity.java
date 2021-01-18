package dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public abstract class Commodity {
    @JsonProperty(value = "COUNTRY")
    private String country;
}
