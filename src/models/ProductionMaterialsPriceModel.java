package models;

import java.util.LinkedHashMap;

public class ProductionMaterialsPriceModel {

    private Object[][] pricesTable;
    private LinkedHashMap productNumberDict;
    private Integer[] counter;
    private String[] rawMaterialsNames;
    private Double[] Price;
    private String[] currency;

    public ProductionMaterialsPriceModel(Integer[] counter, String[] rawMaterialsNames, Double[] Price,
                                         String[] currency)
    {
        this.productNumberDict = new LinkedHashMap();
        this.counter = counter;
        this.rawMaterialsNames = rawMaterialsNames;
        this.Price = Price;
        this.currency = currency;
        this.pricesTable = new Object[][]{
                counter, rawMaterialsNames,
                Price, currency};
    }

    public Object[][] getPricesTable() {
        return pricesTable;
    }

    public Integer[] getCounter() {
        return counter;
    }


    public String[] getRawMaterialsNames() {
        return rawMaterialsNames;
    }

    public Double[] getPrice() {
        return Price;
    }

    public String[] getCurrency() {
        return currency;
    }

    public LinkedHashMap getProductNumberDict() {
        return productNumberDict;
    }
}
