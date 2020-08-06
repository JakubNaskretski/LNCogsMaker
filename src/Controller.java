import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Connect all classes together
public class Controller {

//    private Model model;
    private View view;
    private FormulationTable formulationTableClass;
    private PricesTable pricesTable;
    private CogsTable cogsTable;
    private Double subtotalMaterialsCosts = 0.0;
    private Double subtotalBottles = 0.0, subtotalCap = 0.0, subtotalLabbel = 0.0, subtotalMeasurer = 0.0, subtotalUnitBox = 0.0, subtotalLeaflet = 0.0, subtotalCollectiveBox = 0.0;
    private Double[] subtotalsList = {subtotalBottles, subtotalCap, subtotalLabbel, subtotalMeasurer, subtotalUnitBox, subtotalLeaflet, subtotalCollectiveBox};
    private Double subtotalRawCosts = 0.0;
    private Double totalCogsCosts = 0.0;

    public Controller(View v, FormulationTable ft, PricesTable pt, CogsTable ct) {
        view = v;
        formulationTableClass = ft;
        pricesTable = pt;
        cogsTable = ct;
        initView();

//        Init product details variables in Formulation table
        formulationTableClass.setProductName(view.getProductNameLabel().getText());
        formulationTableClass.setProductCapacity(view.getProductCapacityLabel().getText());
        formulationTableClass.setClientName(view.getClientNameLabel().getText());
        formulationTableClass.setFormulationDate(view.getDateOfTheFormulationLabel().getText());

    }

    private void initView() {

        initTable();
//        Add action listeners
        view.getLoadFormulationButton().addActionListener(e -> {
            formulationTableClass.loadAndSetLoadedFile();
//            model.setModelListData(formulationTableClass.getFormulationTable());
            //TODO: czy potrzebny zapis repaint
//            view.getFrame().repaint();
            calculateCogs();
//            view.createFormulationDataTable();
            initRawsTable();
        });

//        TODO: Check if  you can make loop over choosers
        view.getBottleChooser().addActionListener(e -> {
//            for (JComboBox chooser : view.getMaterialsChoosersList()){
//
//            }
           if (view.getBottleChooser().getSelectedIndex() > -1) {
               System.out.println(pricesTable.getRmpml().getRmpml().get(1).getMaxPrice()[view.getBottleChooser().getSelectedIndex()]);
               view.getCogsMaterialsData()[0][0] = 1;
               view.getCogsMaterialsData()[0][3] = 1;
               view.getCogsMaterialsData()[0][4] = "pcs";
               if (pricesTable.getRmpml().getRmpml().get(1).getMinPrice() != null){
                   view.getCogsMaterialsData()[0][5] = pricesTable.getRmpml().getRmpml().get(1).getMaxPrice()[view.getBottleChooser().getSelectedIndex()];
               } else if (pricesTable.getRmpml().getRmpml().get(1).getMaxPrice() != null) {
                   view.getCogsMaterialsData()[0][5] = pricesTable.getRmpml().getRmpml().get(1).getMaxPrice()[view.getBottleChooser().getSelectedIndex()];
               }
               view.getCogsMaterialsData()[0][6] = pricesTable.getRmpml().getRmpml().get(1).getCurrency()[view.getBottleChooser().getSelectedIndex()];
               calculateMaterials(0,view.getBottleChooser().getSelectedIndex(), 1);
               view.createFormulationDataTable();
           }
        });

        view.getCapChooser().addActionListener(e -> {
            if (view.getCapChooser().getSelectedIndex() > -1) {
                System.out.println(pricesTable.getRmpml().getRmpml().get(2).getMaxPrice()[view.getCapChooser().getSelectedIndex()]);
                view.getCogsMaterialsData()[1][0] = 2;
                view.getCogsMaterialsData()[1][3] = 1;
                view.getCogsMaterialsData()[1][4] = "pcs";
                if (pricesTable.getRmpml().getRmpml().get(2).getMinPrice() != null){
                    view.getCogsMaterialsData()[1][5] = pricesTable.getRmpml().getRmpml().get(2).getMaxPrice()[view.getCapChooser().getSelectedIndex()];
                } else if (pricesTable.getRmpml().getRmpml().get(2).getMaxPrice() != null) {
                    view.getCogsMaterialsData()[1][5] = pricesTable.getRmpml().getRmpml().get(2).getMaxPrice()[view.getCapChooser().getSelectedIndex()];
                }
                view.getCogsMaterialsData()[1][6] = pricesTable.getRmpml().getRmpml().get(2).getCurrency()[view.getCapChooser().getSelectedIndex()];
                calculateMaterials(1,view.getCapChooser().getSelectedIndex(), 2);
                view.createFormulationDataTable();
            }
        });

        view.getLabelChooser().addActionListener(e -> {
            if (view.getLabelChooser().getSelectedIndex() > -1) {
                System.out.println(pricesTable.getRmpml().getRmpml().get(3).getMaxPrice()[view.getLabelChooser().getSelectedIndex()]);
                view.getCogsMaterialsData()[2][0] = 3;
                view.getCogsMaterialsData()[2][3] = 1;
                view.getCogsMaterialsData()[2][4] = "pcs";
                if (pricesTable.getRmpml().getRmpml().get(3).getMinPrice() != null){
                    view.getCogsMaterialsData()[2][5] = pricesTable.getRmpml().getRmpml().get(3).getMaxPrice()[view.getLabelChooser().getSelectedIndex()];
                } else if (pricesTable.getRmpml().getRmpml().get(3).getMaxPrice() != null) {
                    view.getCogsMaterialsData()[2][5] = pricesTable.getRmpml().getRmpml().get(3).getMaxPrice()[view.getLabelChooser().getSelectedIndex()];
                }
                view.getCogsMaterialsData()[2][6] = pricesTable.getRmpml().getRmpml().get(3).getCurrency()[view.getLabelChooser().getSelectedIndex()];
                calculateMaterials(2,view.getLabelChooser().getSelectedIndex(), 3);
                view.createFormulationDataTable();
            }
        });

        view.getMeasurerChooser().addActionListener(e -> {
            if (view.getMeasurerChooser().getSelectedIndex() > -1) {
                System.out.println(pricesTable.getRmpml().getRmpml().get(4).getMaxPrice()[view.getMeasurerChooser().getSelectedIndex()]);
                view.getCogsMaterialsData()[3][0] = 4;
                view.getCogsMaterialsData()[3][3] = 1;
                view.getCogsMaterialsData()[3][4] = "pcs";
                if (pricesTable.getRmpml().getRmpml().get(4).getMinPrice() != null){
                    view.getCogsMaterialsData()[3][5] = pricesTable.getRmpml().getRmpml().get(4).getMaxPrice()[view.getMeasurerChooser().getSelectedIndex()];
                } else if (pricesTable.getRmpml().getRmpml().get(4).getMaxPrice() != null) {
                    view.getCogsMaterialsData()[3][5] = pricesTable.getRmpml().getRmpml().get(4).getMaxPrice()[view.getMeasurerChooser().getSelectedIndex()];
                }
                view.getCogsMaterialsData()[3][6] = pricesTable.getRmpml().getRmpml().get(4).getCurrency()[view.getMeasurerChooser().getSelectedIndex()];
                calculateMaterials(3,view.getMeasurerChooser().getSelectedIndex(), 4);
                view.createFormulationDataTable();
            }
        });

        view.getUnitBoxChooser().addActionListener(e -> {
            if (view.getUnitBoxChooser().getSelectedIndex() > -1) {
                System.out.println(pricesTable.getRmpml().getRmpml().get(5).getMaxPrice()[view.getUnitBoxChooser().getSelectedIndex()]);
                view.getCogsMaterialsData()[4][0] = 5;
                view.getCogsMaterialsData()[4][3] = 1;
                view.getCogsMaterialsData()[4][4] = "pcs";
                if (pricesTable.getRmpml().getRmpml().get(5).getMinPrice() != null){
                    view.getCogsMaterialsData()[4][5] = pricesTable.getRmpml().getRmpml().get(5).getMaxPrice()[view.getUnitBoxChooser().getSelectedIndex()];
                } else if (pricesTable.getRmpml().getRmpml().get(5).getMaxPrice() != null) {
                    view.getCogsMaterialsData()[4][5] = pricesTable.getRmpml().getRmpml().get(5).getMaxPrice()[view.getUnitBoxChooser().getSelectedIndex()];
                }
                view.getCogsMaterialsData()[4][6] = pricesTable.getRmpml().getRmpml().get(5).getCurrency()[view.getUnitBoxChooser().getSelectedIndex()];
                calculateMaterials(4,view.getUnitBoxChooser().getSelectedIndex(), 5);
                view.createFormulationDataTable();
            }
        });

        view.getLeafletChooser().addActionListener(e -> {
            if (view.getLeafletChooser().getSelectedIndex() > -1) {
                System.out.println(pricesTable.getRmpml().getRmpml().get(6).getMaxPrice()[view.getLeafletChooser().getSelectedIndex()]);
                view.getCogsMaterialsData()[5][0] = 6;
                view.getCogsMaterialsData()[5][3] = 1;
                view.getCogsMaterialsData()[5][4] = "pcs";
                if (pricesTable.getRmpml().getRmpml().get(6).getMinPrice() != null){
                    view.getCogsMaterialsData()[5][5] = pricesTable.getRmpml().getRmpml().get(6).getMaxPrice()[view.getLeafletChooser().getSelectedIndex()];
                } else if (pricesTable.getRmpml().getRmpml().get(6).getMaxPrice() != null) {
                    view.getCogsMaterialsData()[5][5] = pricesTable.getRmpml().getRmpml().get(6).getMaxPrice()[view.getLeafletChooser().getSelectedIndex()];
                }
                view.getCogsMaterialsData()[5][6] = pricesTable.getRmpml().getRmpml().get(6).getCurrency()[view.getLeafletChooser().getSelectedIndex()];
                calculateMaterials(5,view.getLeafletChooser().getSelectedIndex(), 6);
                view.createFormulationDataTable();
            }
        });

        view.getCollectiveBoxChooser().addActionListener(e -> {
            if (view.getCollectiveBoxChooser().getSelectedIndex() > -1) {
                System.out.println(pricesTable.getRmpml().getRmpml().get(7).getMaxPrice()[view.getCollectiveBoxChooser().getSelectedIndex()]);
                view.getCogsMaterialsData()[6][0] = 7;
                view.getCogsMaterialsData()[6][3] = 1;
                view.getCogsMaterialsData()[6][4] = "pcs";
                if (pricesTable.getRmpml().getRmpml().get(7).getMinPrice() != null){
                    view.getCogsMaterialsData()[6][5] = pricesTable.getRmpml().getRmpml().get(7).getMaxPrice()[view.getCollectiveBoxChooser().getSelectedIndex()];
                } else if (pricesTable.getRmpml().getRmpml().get(7).getMaxPrice() != null) {
                    view.getCogsMaterialsData()[6][5] = pricesTable.getRmpml().getRmpml().get(7).getMaxPrice()[view.getCollectiveBoxChooser().getSelectedIndex()];
                }
                view.getCogsMaterialsData()[6][6] = pricesTable.getRmpml().getRmpml().get(7).getCurrency()[view.getCollectiveBoxChooser().getSelectedIndex()];
                calculateMaterials(6,view.getCollectiveBoxChooser().getSelectedIndex(), 7);
                view.createFormulationDataTable();

            }
        });

//        TODO: add autocalculating prices - iterates over table fields if not null calculates them

        view.getMenuItemChangePricesSource().addActionListener(e -> {
            pricesTable.loadPricesTableDataSource(view.getFrame());
        });

    }

//    Initializing tables with data
    private void initTable(){

//        Loading data for JCombo boxes materials table
        for (int i=1;i<8;i++){

//            for (Enumeration e = pricesTable.getRmpml().getRmpml().get(i).getProductNumberDict().elements(); e.hasMoreElements();){
//                view.getMaterialsChoosersList().get(i-1).addItem(e.nextElement());

            // Get a set of the entries
            Set set = pricesTable.getRmpml().getRmpml().get(i).getProductNumberDict().entrySet();
            // Get an iterator
            Iterator iter = set.iterator();

            while(iter.hasNext()) {
                Map.Entry me = (Map.Entry)iter.next();
                view.getMaterialsChoosersList().get(i-1).addItem(me.getValue());
            }
        }



        view.createFormulationDataTable();

    }

//     {"pcs","pcs","pcs","pcs","pcs","pcs","pcs","pcs","pcs",
//            "","kg","kg","kg","kg","kg","kg","kg","kg","kg",
//            "","","pcs","pcs","pcs","pcs","pcs","pcs","pcs","pcs",""};

 public void calculateCogs(){
     try {

//         RAW MATERIALS ============
//         for (int j = 0; j<cogsTable.getCounter().length; j++){
//             if (j < 9 || (j > 20 && j <  29)){
//                 cogsTable.getMu()[j] = "pcs";
//             } else if (j > 9 & j < 19) {
//                 cogsTable.getMu()[j] = "kg";
//             }
//         }

         for (int i = 0; i < formulationTableClass.getCounter().length; i++) {
             cogsTable.getItemName1()[i] = formulationTableClass.getRawMaterialsNames()[i];
//             cogsTable.getItemName2()[i+10] = formulationTableClass.getRawMaterialsNames()[i];
             cogsTable.getMu()[i] = "kg";
             cogsTable.getQty()[i] = formulationTableClass.getAmountPerKG()[i];
             cogsTable.getSystemNumbers()[i] = formulationTableClass.getSystemNumbers()[i];
             for (int j = 0; j < pricesTable.getRmpml().getRmpml().get(0).getCounter().length; j++) {
                 if (formulationTableClass.getSystemNumbers()[i].equals(pricesTable.getRmpml().getRmpml().get(0).getSystemNumbers()[j])){
//                     System.out.println("Found price of " + formulationTableClass.getFormulationTable()[2][i]);
//                     TODO: FIx temporary solution
                     if (pricesTable.getRmpml().getRmpml().get(0).getMinPrice() != null){
                         cogsTable.getPurchasePrice()[i] = pricesTable.getRmpml().getRmpml().get(0).getMinPrice()[j];
                         cogsTable.getCurrency()[i] = pricesTable.getRmpml().getRmpml().get(0).getCurrency()[j];
                     }
                     cogsTable.getPurchasePrice()[i] = pricesTable.getRmpml().getRmpml().get(0).getMaxPrice()[j];
                     cogsTable.getCurrency()[i] = pricesTable.getRmpml().getRmpml().get(0).getCurrency()[j];
                 }
             }

             if (cogsTable.getPurchasePrice()[i] != null) {
                 if (cogsTable.getCurrency()[i].equals("EUR")) {
                 cogsTable.getPln()[i] = (cogsTable.getPurchasePrice()[i]*pricesTable.getEuroRate());
                 } else if (cogsTable.getCurrency()[i].equals("USD")) {
                     cogsTable.getPln()[i] = (cogsTable.getPurchasePrice()[i]*pricesTable.getUsdRate());
                 } else {
                     cogsTable.getPln()[i] = cogsTable.getPurchasePrice()[i];
                 }
             } else {
                 cogsTable.getPln()[i] = 0.0;
             }

             if (cogsTable.getPln()[i] != null) {
                cogsTable.getPlnQty()[i] = cogsTable.getPln()[i] * cogsTable.getQty()[i];
                subtotalRawCosts+=cogsTable.getPlnQty()[i];
             } else {
                 cogsTable.getPlnQty()[i] = 0.0;
             }

             view.getCogsRawSubtotalTextField().setText((subtotalRawCosts) + " PLN");
             view.getCogsTotalCostsTextField().setText((subtotalRawCosts + subtotalMaterialsCosts) + " PLN");


         }

         //         PRODUCTION ============




     } catch (NullPointerException e){
         System.out.println("Nie załadowano danych");
         e.printStackTrace();
     }
 }

 public void initRawsTable(){
     for (int i = 0; i < formulationTableClass.getCounter().length; i++){
         for (int j=0;j<6;j++) {
             view.getFormulationData()[i][j] = formulationTableClass.getFormulationTable()[j][i];
         }
//            cogsTable.getItemName1CogsTable()[i+10] = formulationTableClass.getRawMaterialsNamesFormulationTable()[i];
     }
     for (int i = 0; i< cogsTable.getCounter().length; i++){
         view.getCogsRawData()[i][0] = cogsTable.getCounter()[i];
         //        TODO: Temporary solution
         view.getCogsRawData()[i][1] = cogsTable.getItemName1()[i];
         view.getCogsRawData()[i][2] = cogsTable.getItemName2()[i];
         view.getCogsRawData()[i][3] = cogsTable.getQty()[i];
         view.getCogsRawData()[i][4] = cogsTable.getMu()[i];
         view.getCogsRawData()[i][5] = cogsTable.getPurchasePrice()[i];
         view.getCogsRawData()[i][6] = cogsTable.getCurrency()[i];
         view.getCogsRawData()[i][7] = cogsTable.getPln()[i];
         view.getCogsRawData()[i][8] = cogsTable.getPlnQty()[i];

     }
 }

 public void calculateMaterials(int matierlasTableRow, int selectedIndex, int sheetNumberFromPriceXls){
//        TODO: add also min price for calculations
     Double plnPrice = 0.0;
     if (view.getCogsMaterialsData()[matierlasTableRow][5] != null) {
         if (String.valueOf(view.getCogsMaterialsData()[matierlasTableRow][6]).equals("EUR")) {
             view.getCogsMaterialsData()[matierlasTableRow][7] = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getEuroRate(), 2);
             plnPrice = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getEuroRate(), 2);
         } else if (String.valueOf(view.getCogsMaterialsData()[matierlasTableRow][6]).equals("USD")){
             view.getCogsMaterialsData()[matierlasTableRow][7] = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getUsdRate(), 2);
             plnPrice = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getUsdRate(), 2);
         } else {
             view.getCogsMaterialsData()[matierlasTableRow][7] = pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex];
             plnPrice = (pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]);
         }
     } else {
         view.getCogsMaterialsData()[matierlasTableRow][7] = 0.0;
     }

     if (view.getCogsMaterialsData()[matierlasTableRow][7] != null) {
         view.getCogsMaterialsData()[matierlasTableRow][8] = plnPrice * Double.valueOf(1000);
         subtotalsList[sheetNumberFromPriceXls-1]=plnPrice * Double.valueOf(1000);
     } else {
         view.getCogsMaterialsData()[matierlasTableRow][8] = 0.0;
     }

     subtotalMaterialsCosts = 0.0;
     for (Double subtotalCost : subtotalsList){
         subtotalMaterialsCosts += subtotalCost;}
     view.getCogsMaterialsSubtotalTextField().setText((subtotalMaterialsCosts) + " PLN");
     view.getCogsTotalCostsTextField().setText((subtotalRawCosts + subtotalMaterialsCosts) + " PLN");

 }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
