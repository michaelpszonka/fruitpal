package service.impl;

import compare.FruitComparators.TotalFruitCostComparator;
import dao.CommodityDAO;
import dao.fruit.Fruit;
import dao.impl.FruitDAO;
import dto.fruit.FruitDTO;
import service.CommodityService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FruitService implements CommodityService<FruitDTO, Fruit> {

    FruitDAO fruitDAO;

    public FruitService(FruitDAO dao) {
        this.fruitDAO = dao;
    }

    /**
     * Query the Fruit DAO for pricing of a particular fruit
     * Upon return utilize each fruit entity's fixed/variable costs
     * to calculate the total cost for a particular fruit in each country.
     * @param fruit - the fruit being queried for pricing info
     * @param tonnagePrice - cost per ton of fruit
     * @param volume - amount of fruit being purchased
     * @return - List of FruitDTOs containing metadata around country, total cost, etc
     */
    public List<FruitDTO> getPricingDetails(String fruit, double tonnagePrice, double volume) {
        List<FruitDTO> fruitPricing = new ArrayList<>();
        List<Fruit> fruits = fetchCommodityDetails(fruit);

        for(Fruit f : fruits) {
            FruitDTO fruitDTO = new FruitDTO();
            double variableCost = tonnagePrice + f.getVariableOverhead();
            fruitDTO.setCountry(f.getCountry().toString());
            fruitDTO.setCommodity(f.getCommodity());
            fruitDTO.setTotalVariableCost(variableCost);
            fruitDTO.setTotalFixedCost(f.getFixedOverhead());
            fruitDTO.setTotalCost((volume * variableCost) + f.getFixedOverhead());
            fruitPricing.add(fruitDTO);
        }

        Collections.sort(fruitPricing, new TotalFruitCostComparator());

        return fruitPricing;
    }

    public List<Fruit> fetchCommodityDetails(String fruit) {
        return fruitDAO.fetchPricingData()
                                        .stream()
                                        .filter(record ->
                                                record.getCommodity().toLowerCase().equals(fruit.toLowerCase()))
                                        .collect(Collectors.toList());
    }
}
