package client;

import dto.fruit.CommodityDTO;

import java.util.List;

public interface CommodityClient<T extends CommodityDTO> {
    void summarizeOutput(List<T> dto, double volume);
}
