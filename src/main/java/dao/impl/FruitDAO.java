package dao.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.PropertiesLoader;
import dao.CommodityDAO;
import dao.fruit.Fruit;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class FruitDAOImpl implements CommodityDAO<Fruit> {

    private String filePath;

    public FruitDAOImpl() {
        Properties config = PropertiesLoader.loadProperties("application.properties");
        this.filePath = config.getProperty("fruit-file-path");
    }

    public FruitDAOImpl(String filePath) {
        this.filePath = filePath;
    }

    public List<Fruit> fetchPricingData() {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Fruit> fruits = null;
        try {
            fruits = mapper.readValue(loader.getResourceAsStream(filePath), new TypeReference<List<Fruit>>() {});
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fruits;
    }
}
