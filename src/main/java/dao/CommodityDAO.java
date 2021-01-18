package dao;

import dao.fruit.Fruit;

import java.util.List;

public interface CommodityDAO<T extends Commodity> {
    List<T> fetchPricingData();
}
