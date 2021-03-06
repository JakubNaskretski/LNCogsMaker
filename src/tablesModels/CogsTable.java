package tablesModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CogsTable {

    private Object[][] cogsTable;
    private Integer[] counter;
    private String[] itemName1;
    private String[] itemName2;
    private Double[] qty;
    private String[] mu;
    private Double[] purchasePrice;
    private String[] currency;
    private Double[] pln;
    private Double[] plnQty;
    private String[] systemNumbers;

    private String[] bottleChooserList;
    private String[] capChooserList;
    private String[] labelChooserList;
    private String[] measurerChooserList;
    private String[] unitBoxChooserList;
    private String[] leafletChooserList;
    private String[] collectiveBoxChooserList;

    private String cogsDate;

    private int percentageMargin;
    private double[] cogsPriceElements = new double[6];
    private String[] cogsPriceElementsNames = {"Cogs value", "Cogs price", "Cogs margin", "Cogs margin %"};



//    cogsTableColumnNames = {"No.","Item", "Item 2",
//            "QTY","m.u.", "Purchase price", "Currency", "PLN", "PLN * QTY"};

    public CogsTable(int numberOfItems) {
        this.percentageMargin = 0;

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
       cogsDate = dateFormat.format(date);

        counter = new Integer[numberOfItems];
        for (int i=0;i<numberOfItems;i++){
            counter[i] = i+1;
        }
        itemName1 = new String[numberOfItems];
        itemName2 = new String[numberOfItems];
        qty = new Double[numberOfItems];
        mu = new String[numberOfItems];
        purchasePrice = new Double[numberOfItems];
        currency = new String[numberOfItems];
        pln = new Double[numberOfItems];
        plnQty = new Double[numberOfItems];
        systemNumbers = new String[numberOfItems];

        Object[][] cogsListData = {
                counter,
                itemName1,
                itemName2,
                qty,
                mu,
                purchasePrice,
                currency,
                pln,
                plnQty};
        this.cogsTable = cogsListData;
    }

    // ============== Getters n Setters ==============

    public Integer[] getCounter() {
        return counter;
    }

    public void setCounter(Integer[] counter) {
        this.counter = counter;
    }

    public String[] getItemName1() {
        return itemName1;
    }

    public void setItemName1(String[] itemName1) {
        this.itemName1 = itemName1;
    }

    public Object[][] getCogsTable() {
        return cogsTable;
    }


    public String[] getSystemNumbers() {
        return systemNumbers;
    }

    public Double[] getQty() {
        return qty;
    }

    public String[] getItemName2() {
        return itemName2;
    }

    public String[] getMu() {
        return mu;
    }

    public Double[] getPurchasePrice() {
        return purchasePrice;
    }

    public String[] getCurrency() {
        return currency;
    }

    public Double[] getPln() {
        return pln;
    }

    public Double[] getPlnQty() {
        return plnQty;
    }

    public String getCogsDate() {
        return cogsDate;
    }

    public int getPercentageMargin() {
        return percentageMargin;
    }

    public double[] getCogsPriceElements() {
        return cogsPriceElements;
    }

    public void setPercentageMargin(int percentageMargin) {
        this.percentageMargin = percentageMargin;
    }

    public String[] getCogsPriceElementsNames() {
        return cogsPriceElementsNames;
    }
}
