import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CogsTable {

    private Object[][] cogsTable;
    private Integer[] counter = {1,2,3,4,5,6,7,8,9};
    private String[] itemName1 = new String[9];
    private String[] itemName2;
    private Integer[] qty;
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



//    cogsTableColumnNames = {"No.","Item", "Item 2",
//            "QTY","m.u.", "Purchase price", "Currency", "PLN", "PLN * QTY"};

    public CogsTable() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
       cogsDate = dateFormat.format(date);


        itemName2 = new String[9];
        qty = new Integer[9];
        mu = new String[9];
        purchasePrice = new Double[9];
        currency = new String[9];
        pln = new Double[9];
        plnQty = new Double[9];
        systemNumbers = new String[9];

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

    public Integer[] getQty() {
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
}
