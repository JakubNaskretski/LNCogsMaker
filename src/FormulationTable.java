import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

// Reads formulation file, creates virtual table with formulation

public class FormulationTable {

    private Object[][] formulationTable;
    private Integer[] counter;
    private String[] rawMaterialsNames;
    private String[] systemNumbers;
    private Double[] amountPerDosage;
    private String[] chemicalForm;
    private Integer[] amountPerKG;

    private String excelAddress;
    private JFileChooser jfc;
    private JFrame currentFrame;
    private String selectedFilePath;

    private String productName, clientName, productCapacity, formulationDate;

    // Creates virtual table for formulation
    // TODO: Make dynamicly created table
    public FormulationTable() {
        this.jfc = new JFileChooser();
    }

    //    Reads file with formulations and fills virtual formulation table with readed data
    public void readFormulationData() throws IOException {
        File inputWorkbook = new File(selectedFilePath);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);

//            Rows -1 because 1st line is meta data for cogs
            this.counter = new Integer[sheet.getRows()-1];
            this.rawMaterialsNames = new String[sheet.getRows()-1];
            this.systemNumbers = new String[sheet.getRows()-1];
            this.amountPerDosage = new Double[sheet.getRows()-1];
            this.chemicalForm = new String[sheet.getRows()-1];
            this.amountPerKG = new Integer[sheet.getRows()-1];
            Object[][] formulationListData = {
                counter,
                rawMaterialsNames,
                systemNumbers,
                amountPerDosage,
                chemicalForm,
                amountPerKG};
            this.formulationTable = formulationListData;

            formulationDate = sheet.getCell(0, 0).getContents();
            clientName = sheet.getCell(0, 1).getContents();
            productName = sheet.getCell(0, 2).getContents();
            productCapacity = sheet.getCell(0, 3).getContents();



            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 1; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
//  TODO: add verify data type
//                    model.getModelListData()[j][i] = cell.getContents();
                    if (type == CellType.LABEL) {
                        formulationTable[j][i-1] = cell.getContents();
//                        System.out.println("I got a label "
//                                + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
//                        System.out.println("I got a number "
//                                + cell.getContents());
                        if (j == 3) {
                            formulationTable[j][i-1] = Double.valueOf(cell.getContents());
                        } else {
                            formulationTable[j][i-1] = Integer.valueOf(cell.getContents());
                        }
                    }
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    //    Choose file with file chooser java set chosen file address and read data from file calling read func
    public void loadAndSetLoadedFile() {
        jfc.setDialogTitle("Open file");
        int returnValue = jfc.showOpenDialog(currentFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String extension = FormulationTable.Utils.getExtension(selectedFile);
            if (extension != null) {
                if (extension.equals(FormulationTable.Utils.xls)) {
                    selectedFilePath = selectedFile.getAbsolutePath();
                    try {
                        readFormulationData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
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


    public Object[][] getFormulationTable() {
        return formulationTable;
    }

    public void setFormulationTable(Object[][] formulationTable) {
        this.formulationTable = formulationTable;
    }

    public Integer[] getCounter() {
        return counter;
    }

    public void setCounter(Integer[] counter) {
        this.counter = counter;
    }

    public String[] getRawMaterialsNames() {
        return rawMaterialsNames;
    }

    public void setRawMaterialsNames(String[] rawMaterialsNames) {
        this.rawMaterialsNames = rawMaterialsNames;
    }

    public String[] getSystemNumbers() {
        return systemNumbers;
    }

    public void setSystemNumbers(String[] systemNumbers) {
        this.systemNumbers = systemNumbers;
    }

    public Double[] getAmountPerDosage() {
        return amountPerDosage;
    }

    public void setAmountPerDosage(Double[] amountPerDosage) {
        this.amountPerDosage = amountPerDosage;
    }

    public String[] getChemicalForm() {
        return chemicalForm;
    }

    public void setChemicalForm(String[] chemicalForm) {
        this.chemicalForm = chemicalForm;
    }

    public Integer[] getAmountPerKG() {
        return amountPerKG;
    }

    public void setAmountPerKG(Integer[] amountPerKG) {
        this.amountPerKG = amountPerKG;
    }

    public String getExcelAddress() {
        return excelAddress;
    }

    public void setExcelAddress(String excelAddress) {
        this.excelAddress = excelAddress;
    }

    public JFileChooser getJfc() {
        return jfc;
    }

    public void setJfc(JFileChooser jfc) {
        this.jfc = jfc;
    }

    public JFrame getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public String getSelectedFilePath() {
        return selectedFilePath;
    }

    public void setSelectedFilePath(String selectedFilePath) {
        this.selectedFilePath = selectedFilePath;
    }

    public String getProductName() {
        return productName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getProductCapacity() {
        return productCapacity;
    }

    public String getFormulationDate() {
        return formulationDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setProductCapacity(String productCapacity) {
        this.productCapacity = productCapacity;
    }

    public void setFormulationDate(String formulationDate) {
        this.formulationDate = formulationDate;
    }
}
