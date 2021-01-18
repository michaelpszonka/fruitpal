package service;

import dao.Commodity;
import dto.fruit.CommodityDTO;

import java.util.List;

public interface CommodityService<T extends CommodityDTO, V extends Commodity> {
    List<T> getPricingDetails(String commodity, double price, double volume);
    List<V> fetchCommodityDetails(String commodity);
}
