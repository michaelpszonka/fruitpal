import dao.impl.FruitDAO;
import dto.fruit.FruitDTO;
import org.junit.Before;
import org.junit.Test;
import service.impl.FruitService;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FruitServiceTest {

    FruitService fruitService;

    @Before
    public void setup() {
        fruitService = new FruitService(new FruitDAO("json/testFruitPricing.json"));
    }

    @Test
    public void testFetchCorrectNumberOfFruits() {
        double volume = 40.0;
        double tonnagePrice = 51.0;
        String mango = "mango";
        String apple = "Apple";
        List<FruitDTO> mangoData = fruitService.getPricingDetails(mango, tonnagePrice, volume);
        List<FruitDTO> appleData = fruitService.getPricingDetails(apple, tonnagePrice, volume);

        assertEquals(4, mangoData.size());
        assertEquals(2, appleData.size());
    }

    @Test
    public void testCalculatingCorrectTotalCost () {
        String mango = "mango";
        double volume = 405;
        double tonnagePrice = 53;
        double mangoFixedCost = 20.00;
        double mangoVariableCost = 1.42;
        double expectedTotalCost = volume * (tonnagePrice + mangoVariableCost) + mangoFixedCost;

        Optional<FruitDTO> brazilianMangos = fruitService.getPricingDetails(mango, tonnagePrice, volume)
                                                .stream()
                                                .filter(record -> record.getCountry().equalsIgnoreCase("BR") &&
                                                        record.getCommodity().equalsIgnoreCase("mango"))
                                                .findFirst();

        assertEquals(brazilianMangos.isPresent(), true);
        assertEquals(expectedTotalCost, brazilianMangos.get().getTotalCost(),0);
    }

    @Test
    public void testTotalCostDescendingPricingOrder() {
        List<FruitDTO> mangos = fruitService.getPricingDetails("mango", 53, 405);
        Iterator<FruitDTO> iter = mangos.iterator();
        FruitDTO curr, prev = iter.next();

        while(iter.hasNext()) {
            curr = iter.next();
            assertTrue(prev.getTotalCost() > curr.getTotalCost());
            prev = curr;
        }
    }
}
