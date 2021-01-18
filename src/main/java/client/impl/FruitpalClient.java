package client.impl;

import client.CommodityClient;
import dao.impl.FruitDAO;
import dto.fruit.FruitDTO;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import service.impl.FruitService;

import java.text.NumberFormat;
import java.util.List;
import java.util.concurrent.Callable;


@Command(name = "fruitpal", description = "Consumes a fruit, price, and quantity and " +
                                "returns a summary of acquisition costs by country")
public class FruitpalClient implements CommodityClient<FruitDTO>, Callable<Integer> {

    @Parameters(index = "0", description = "The fruit being queried for pricing information")
    private String fruit;

    @Parameters(index = "1", description = "The price per ton for the requested fruit")
    private double tonnagePrice;

    @Parameters(index = "2", description = "The quantity of the fruit being requested (in tons)")
    private double volume;

    private FruitService fruitService;

    public FruitpalClient() {
        this.fruitService = new FruitService(new FruitDAO());
    }

    @Override
    public Integer call() throws Exception {
        List<FruitDTO> pricing = this.fruitService.getPricingDetails(this.fruit, this.tonnagePrice, this.volume);
        summarizeOutput(pricing, this.volume);

        return 0;
    }

    public void summarizeOutput(List<FruitDTO> pricing, double volume) {
        StringBuilder sb = new StringBuilder();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        if(pricing.size() == 0) {
            System.out.println("No pricing data was found for your search");
        }
        for(FruitDTO fruit : pricing) {
            sb.append(fruit.getCountry().toUpperCase());
            sb.append("  ");
            sb.append(formatter.format(fruit.getTotalCost()));
            sb.append("  |  ");
            sb.append("(" + fruit.getTotalVariableCost()  + " * " + volume + ") + " + fruit.getTotalFixedCost());
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }

}
