import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PricesTable {

    private RawMaterialsPricesModel rmpm;
    private Object[] priceModelsList = new Object[1];

    private JFileChooser jfc;
//    private JFrame currentFrame;
    private String rawMaterialsTablePricesPath = "C:\\Users\\kuba2\\IdeaProjects\\LnCogsV3\\TestExcelFiles\\TestPrices.xls";
    private double euroRate, usdRate;

    private String[] bottleChooserList;
    private String[] capChooserList;
    private String[] labelChooserList;
    private String[] measurerChooserList;
    private String[] unitBoxChooserList;
    private String[] leafletChooserList;
    private String[] collectiveBoxChooserList;


    public PricesTable() {
        this.jfc = new JFileChooser();
        this.euroRate = 4.3;
        this.usdRate = 3.8;
        try {
            getPricesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New path have been set "+rawMaterialsTablePricesPath);
    }

    //    Choose file with file chooser java set chosen file address and read data from file calling read func
    public void loadpricesTableDataSource(JFrame currentFrame) {
        jfc.setDialogTitle("Open file");
        int returnValue = jfc.showOpenDialog(currentFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String extension = PricesTable.Utils.getExtension(selectedFile);
            if (extension != null) {
                if (extension.equals(PricesTable.Utils.xls)) {
                    rawMaterialsTablePricesPath = selectedFile.getAbsolutePath();
                    try {
                        getPricesList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("New path have been set "+rawMaterialsTablePricesPath);
                }
            }
        }
    }

    private void initiatePriceModels(){
        File inputWorkbook = new File(rawMaterialsTablePricesPath);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);

            this.rmpm = new RawMaterialsPricesModel(new Integer[sheet.getRows()], new String[sheet.getRows()],new String[sheet.getRows()],
                    new Double[sheet.getRows()], new Double[sheet.getRows()], new String[sheet.getRows()], new String[sheet.getRows()]);

            priceModelsList[0] = rmpm;

    } catch (Exception e){
            System.out.println("Couldn't load Models");}
    }

    private void getPricesList() throws IOException {

            initiatePriceModels();

//            TODO: Remove double sheet
            File inputWorkbook = new File(rawMaterialsTablePricesPath);
            Workbook w;
            try {
                w = Workbook.getWorkbook(inputWorkbook);
                // Get the first sheet
                Sheet sheet = w.getSheet(0);

//                        TODO: FIX temporary solution of creating table
//                this.pricesTable = new Object[][]{
//                        counter, systemNumbers, rawMaterialsNames,
//                        minPrice, maxPrice, currency, supplier};

                for (int j = 0; j < 7; j++) {
                    for (int i = 0; i < sheet.getRows(); i++) {
                        Cell cell = sheet.getCell(j, i);
                        CellType type = cell.getType();
//  TODO: add verify data type
//                    model.getModelListData()[j][i] = cell.getContents();
                        if (type == CellType.LABEL) {
//                            priceModelsList[0].get [j][i] = cell.getContents();

                            rmpm.getPricesTable()[j][i] = cell.getContents();

                                System.out.println("I got a label "
                                        + cell.getContents());
                            }

                        if (type == CellType.NUMBER) {
                            System.out.println("I got a number "
                                    + cell.getContents());
                            if (j == 3) {
                                rmpm.getPricesTable()[j][i] = Double.valueOf(cell.getContents().replace(",","."));
                            } else if(j == 4){
                                rmpm.getPricesTable()[j][i] = Double.valueOf(cell.getContents().replace(",","."));
                            } else {
                                rmpm.getPricesTable()[j][i] = Integer.valueOf(cell.getContents());
                            }
                        }
                    }
                }
            } catch (BiffException e) {
                e.printStackTrace();
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
}
