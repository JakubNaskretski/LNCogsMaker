package models;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedHashMap;

// This class is Model of a raw material for price dataa
public class RawMaterialsPricesModel {

    private Object[][] pricesTable;
    private LinkedHashMap productNumberDict;
    private Integer[] counter;
    private String[] systemNumbers;
    private String[] rawMaterialsNames;
    private Double[] minPrice;
    private Double[] maxPrice;
    private String[] currency;
    private String[] supplier;

    public RawMaterialsPricesModel(Integer[] counter, String[] systemNumbers,
                                   String[] rawMaterialsNames, Double[] minPrice, Double[] maxPrice,
                                   String[] currency, String[] supplier)
    {
        this.productNumberDict = new LinkedHashMap();
        this.counter = counter;
        this.systemNumbers = systemNumbers;
        this.rawMaterialsNames = rawMaterialsNames;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.currency = currency;
        this.supplier = supplier;
        this.pricesTable = new Object[][]{
                counter, systemNumbers, rawMaterialsNames,
                minPrice, maxPrice, currency, supplier};
//        super.getRmpml().add(this);
    }

    public Object[][] getPricesTable() {
        return pricesTable;
    }

    public Integer[] getCounter() {
        return counter;
    }

    public String[] getSystemNumbers() {
        return systemNumbers;
    }

    public String[] getRawMaterialsNames() {
        return rawMaterialsNames;
    }

    public Double[] getMinPrice() {
        return minPrice;
    }

    public Double[] getMaxPrice() {
        return maxPrice;
    }

    public String[] getCurrency() {
        return currency;
    }

    public String[] getSupplier() {
        return supplier;
    }

    public LinkedHashMap getProductNumberDict() {
        return productNumberDict;
    }
}
