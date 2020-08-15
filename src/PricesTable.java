import jxl.Cell;
import jxl.CellType;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


// Initiates price models and copy excel data into virtual price-model table
public class PricesTable {

    private RawMaterialsPriceModelList rmpml;
    private ProductionMaterialsPriceModelList pmpml;

    private RawMaterialsPricesModel rmpm;
    private RawMaterialsPricesModel pmpm;
//    private Object[] priceModelsList = new Object[1];

    private JFileChooser jfc;
//    private JFrame currentFrame;
    private String rawMaterialsTablePricesPath = "TestExcelFiles\\TestPrices.xls";
    private String productionMaterialsTablePricePath = "TestExcelFiles\\TestProductionPrices.xls";
    private String rawMaterialsPricePathFolderName = "TestExcelFiles";
    private String ProductionPricePathFolderName = "TestExcelFiles";
    private double euroRate, usdRate;

//    private String[] bottleChooserList;
//    private String[] capChooserList;
//    private String[] labelChooserList;
//    private String[] measurerChooserList;
//    private String[] unitBoxChooserList;
//    private String[] leafletChooserList;
//    private String[] collectiveBoxChooserList;


    public PricesTable() {
        this.rmpml = new RawMaterialsPriceModelList();
        this.pmpml = new ProductionMaterialsPriceModelList();
        this.jfc = new JFileChooser();
        this.euroRate = 4.3;
        this.usdRate = 3.8;
        loadPriceListsFromExcel();
        System.out.println("New path have been set "+rawMaterialsTablePricesPath+" and "+productionMaterialsTablePricePath);
    }

    public void loadPriceListsFromExcel(){
        try {
            getMaterialsPricesList();
            getProductionPricesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Choose file with file chooser java set chosen file address and read data from file calling read func
    public void loadMaterialsPricesTableDataSource(JFrame currentFrame) {
        jfc.setDialogTitle("Open file");
        int returnValue = jfc.showOpenDialog(currentFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String extension = PricesTable.Utils.getExtension(selectedFile);
            if (extension != null) {
                if (extension.equals(PricesTable.Utils.xls)) {
                    rawMaterialsTablePricesPath = selectedFile.getAbsolutePath();
                    rawMaterialsPricePathFolderName = selectedFile.getAbsolutePath();
                    try {
                        getMaterialsPricesList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new PopUpInfo("Ustawiono nowe źródło cen surowców");
                    System.out.println("New path have been set "+rawMaterialsTablePricesPath);
                }
            }
        }
    }

    //    Choose file with file chooser java set chosen file address and read data from file calling read func
    public void loadProductionTableDataSource(JFrame currentFrame) {
        jfc.setDialogTitle("Open file");
        int returnValue = jfc.showOpenDialog(currentFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String extension = PricesTable.Utils.getExtension(selectedFile);
            if (extension != null) {
                if (extension.equals(PricesTable.Utils.xls)) {
                    productionMaterialsTablePricePath = selectedFile.getAbsolutePath();
                    ProductionPricePathFolderName = selectedFile.getAbsolutePath();
                    try {
                        getProductionPricesList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new PopUpInfo("Ustawiono nowe źródło cen produkcji");
                    System.out.println("New path have been set "+productionMaterialsTablePricePath);
                }
            }
        }
    }

//    Opens raw materials price list, reads it and creates price models based on number of rows in the price offer
    private void initiateMaterialsPriceModels(){
        File materialsInputWorkbook = new File(rawMaterialsTablePricesPath);
        Workbook mw;
        try {
            mw = Workbook.getWorkbook(materialsInputWorkbook);

//            Read number of sheet, creates instance of RMPM for each sheet with number of rows in sheet
//            Each sheet by class is automaticcly added to RMPML
            for (int i=0;i<mw.getNumberOfSheets();i++){
                rmpml.getRmpml().add(new RawMaterialsPricesModel(new Integer[mw.getSheet(i).getRows()], new String[mw.getSheet(i).getRows()],new String[mw.getSheet(i).getRows()],
                        new Double[mw.getSheet(i).getRows()], new Double[mw.getSheet(i).getRows()], new String[mw.getSheet(i).getRows()], new String[mw.getSheet(i).getRows()]));
            }

    } catch (Exception e){
            new PopUpInfo("Nie udało się załadować modeli surowców");
        }
    }


    private void initiateProductionPriceModels() {

        //        Loading data for production part
        File productionInputWorkbook = new File(productionMaterialsTablePricePath);
        Workbook pw;
        try{
            pw = Workbook.getWorkbook(productionInputWorkbook);

            for (int i=0;i<pw.getNumberOfSheets();i++){
                pmpml.getPmpml().add(new ProductionMaterialsPriceModel(new Integer[pw.getSheet(i).getRows()], new String[pw.getSheet(i).getRows()],
                        new Double[pw.getSheet(i).getRows()], new String[pw.getSheet(i).getRows()]));
            }
        } catch (Exception e){
            System.out.println("Couldn't load production materials models");}

    }

// Calls initiatePriceList(), reads price list and fill model with prices from table
    private void getMaterialsPricesList() throws IOException {

            initiateMaterialsPriceModels();

//            TODO: Add dictionary for material choosers

//            TODO: Remove double sheet
            File inputWorkbook = new File(rawMaterialsTablePricesPath);
            Workbook w;
            try {
                w = Workbook.getWorkbook(inputWorkbook);
                // Get the first sheet

//                Sheet sheet = w.getSheet(0);

//                        TODO: FIX temporary solution of creating table
//                this.pricesTable = new Object[][]{
//                        counter, systemNumbers, rawMaterialsNames,
//                        minPrice, maxPrice, currency, supplier};

                for (int sheetNo=0; sheetNo<w.getNumberOfSheets();sheetNo++) {
                    for (int columnNo = 0; columnNo < 7; columnNo++) {
                        for (int rowNo = 0; rowNo < w.getSheet(sheetNo).getRows(); rowNo++) {
                            Cell cell = w.getSheet(sheetNo).getCell(columnNo, rowNo);
                            CellType type = cell.getType();
//  TODO: add verify data type
//                    model.getModelListData()[j][i] = cell.getContents();
                            if (type == CellType.LABEL) {
//                            priceModelsList[0].get [j][i] = cell.getContents();

                                rmpml.getRmpml().get(sheetNo).getPricesTable()[columnNo][rowNo] = cell.getContents();

                                if (columnNo == 2 && sheetNo > 0){
                                    rmpml.getRmpml().get(sheetNo).getProductNumberDict().put(rmpml.getRmpml().get(sheetNo).getSystemNumbers()[rowNo],cell.getContents());
                                }

                                System.out.println("I got a label "
                                        + cell.getContents());
                            }

                            if (type == CellType.NUMBER) {
                                System.out.println("I got a number "
                                        + cell.getContents());
                                if (columnNo == 3 | columnNo == 4) {
//                                    rmpm.getPricesTable()[j][i] = Double.valueOf(cell.getContents().replace(",", "."));
                                    rmpml.getRmpml().get(sheetNo).getPricesTable()[columnNo][rowNo] = Double.valueOf(cell.getContents().replace(",", "."));
                                } else {
//                                    rmpm.getPricesTable()[j][i] = Integer.valueOf(cell.getContents());
                                    rmpml.getRmpml().get(sheetNo).getPricesTable()[columnNo][rowNo] = Integer.valueOf(cell.getContents());
                                }
                            }
                        }
                    }
                }
            } catch (BiffException e) {
                new PopUpInfo("Nie załadowano tablic cenowych");
            }
    }


    // Calls initiatePriceList(), reads price list and fill model with prices from table
    private void getProductionPricesList() throws IOException {

        initiateProductionPriceModels();

//            TODO: Add dictionary for material choosers

//            TODO: Remove double sheet
        File inputWorkbook = new File(productionMaterialsTablePricePath);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            for (int sheetNo=0; sheetNo<w.getNumberOfSheets();sheetNo++) {
                for (int columnNo = 0; columnNo < 4; columnNo++) {
                    for (int rowNo = 0; rowNo < w.getSheet(sheetNo).getRows(); rowNo++) {
                        Cell cell = w.getSheet(sheetNo).getCell(columnNo, rowNo);
                        CellType type = cell.getType();
//  TODO: add verify data type
//                    model.getModelListData()[j][i] = cell.getContents();
                        if (type == CellType.LABEL) {
                            pmpml.getPmpml().get(sheetNo).getPricesTable()[columnNo][rowNo] = cell.getContents();
                        }
                        if (type == CellType.NUMBER) {
                            System.out.println("I got a number "
                                    + cell.getContents());
                            if (columnNo == 2) {
                                pmpml.getPmpml().get(sheetNo).getPricesTable()[columnNo][rowNo] = Double.valueOf(cell.getContents().replace(",", "."));
                                pmpml.getPmpml().get(sheetNo).getProductNumberDict().put(pmpml.getPmpml().get(sheetNo).getRawMaterialsNames()[rowNo],cell.getContents());
                            } else {
                                pmpml.getPmpml().get(sheetNo).getPricesTable()[columnNo][rowNo] = Integer.valueOf(cell.getContents());
                            }
                        }
                    }
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
            new PopUpInfo("Nie udało się załadować cen produkcji w Prices Table");
        }
    }



    //Files extension for file chooser
    private static class Utils {
        public final static String txt = "txt";
        public final static String jpg = "jpg";
        public final static String gif = "gif";
        public final static String tiff = "tiff";
        public final static String tif = "tif";
        public final static String png = "png";
        public final static String xls = "xls";


        /*
         * Get the extension of a file.
         */
        public static String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
    }

    // ============== Getters n Setters ==============


    public String getRawMaterialsTablePricesPath() {
        return rawMaterialsTablePricesPath;
    }

    public void setRawMaterialsTablePricesPath(String rawMaterialsTablePricesPath) {
        this.rawMaterialsTablePricesPath = rawMaterialsTablePricesPath;
    }

    public double getEuroRate() {
        return euroRate;
    }

    public double getUsdRate() {
        return usdRate;
    }

    public RawMaterialsPricesModel getRmpm() {
        return rmpm;
    }

    public RawMaterialsPriceModelList getRmpml() {
        return rmpml;
    }

    public ProductionMaterialsPriceModelList getPmpml() {
        return pmpml;
    }

    public String getProductionMaterialsTablePricePath() {
        return productionMaterialsTablePricePath;
    }

    public String getRawMaterialsPricePathFolderName() {
        return rawMaterialsPricePathFolderName;
    }

    public String getProductionPricePathFolderName() {
        return ProductionPricePathFolderName;
    }
}
