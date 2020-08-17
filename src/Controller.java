import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Connect all classes together
public class Controller {

//    private Model model;
    private StartingView startingView;
    private View view;
    private FormulationTable formulationTableClass;
    private PricesTable pricesTable;
    private CogsTable cogsTable;
    private Double subtotalMaterialsCosts = 0.0;
    private Double subtotalBottles = 0.0, subtotalCap = 0.0, subtotalLabbel = 0.0, subtotalMeasurer = 0.0,
            subtotalUnitBox = 0.0, subtotalLeaflet = 0.0, subtotalCollectiveBox = 0.0, subtotalPallete = 0.0, subtotalMF = 0.0, subtotalOH = 0.0, subtotalTests = 0.0;
    private Double[] subtotalMaterialssList = {subtotalBottles, subtotalCap, subtotalLabbel, subtotalMeasurer,
            subtotalUnitBox, subtotalLeaflet, subtotalCollectiveBox, subtotalPallete};
    private Double[] subtotalProductionList = {subtotalMF, subtotalOH, subtotalTests};
    private Double subtotalRawCosts = 0.0;
    private Double subtotalProductionCosts = 0.0;;
    private Double totalCogsCosts = 0.0;
    private LocalDate cogsDate;

    public Controller(StartingView sv, FormulationTable ft, PricesTable pt) {
        startingView = sv;
        formulationTableClass = ft;
        pricesTable = pt;
        initView();
    }

    private void initView() {

        startingView.getLoadFormulationButton().addActionListener(e -> {
            formulationTableClass.loadAndSetLoadedFile();
            this.view = new View(formulationTableClass.getCounter().length);
            this.cogsTable = new CogsTable(formulationTableClass.getCounter().length);

            startingView.getFrame().dispose();
    try{

            loadAndDisplayDataForMaterialsTable();
            calculateIngredients();
            loadAndDisplayRawsTable();
            loadAndDisplayProductionTable();

        } catch (Exception e1) {
        new PopUpInfo("Nie udało się załadować danych", startingView.getFrame());}

        initiateTablesView();

        });

    }

    private void initiateTablesView(){
        view.getLoadFormulationButton().addActionListener(e -> {

            subtotalRawCosts = 0.0;
            subtotalMaterialsCosts = 0.0;
            subtotalProductionCosts = 0.0;

            view.getFrame().dispose();
            formulationTableClass.loadAndSetLoadedFile();
            this.view = new View(formulationTableClass.getCounter().length);
            this.cogsTable = new CogsTable(formulationTableClass.getCounter().length);

                    try{
            loadAndDisplayDataForMaterialsTable();
            calculateIngredients();
            loadAndDisplayRawsTable();
            loadAndDisplayProductionTable();
                    } catch (Exception e1) {
                        new PopUpInfo("Nie udało się załadować danych", view.getFrame());
                    }

            initiateTablesView();

        });

//        =================================== Add items listeners
        view.getMenuItemAddNewBottle().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Bottle", this);

            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewBottle().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Bottle", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewCap().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Cap", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewLabel().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Label", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewMeasurer().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Bottle", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewUnitBox().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Unit box", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewLeaflet().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Leaflet", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewCollectiveBox().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Collective box", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });

        view.getMenuItemAddNewPallete().addActionListener(e -> {
            try {
                new AddItemController(view.getFrame(), pricesTable, "Pallete", this).initAddItemView();
            } catch (Exception exceptionAddBottle){
                exceptionAddBottle.printStackTrace();
                new PopUpInfo("Nie udało się otworzyć widoku dodawania butelki");
            }
        });
//        TODO: Check if  you can make loop over choosers

        //        Init product details variables in Formulation table

        view.getCogsDate().setText("Cogs utworzony "+ String.valueOf(cogsDate = LocalDate.now()));
        view.getProductNameLabel().setText("Nazwa produktu "+formulationTableClass.getProductName());
        view.getProductCapacityLabel().setText("Pojemność produktu "+formulationTableClass.getProductCapacity());
        view.getClientNameLabel().setText("Klient "+formulationTableClass.getClientName());
        view.getDateOfTheFormulationLabel().setText("Formulacja z "+formulationTableClass.getFormulationDate());

//                =================================== Item choosers listeners

        view.getBottleChooser().addActionListener(e -> {
            getDataFromChooser(0, view.getBottleChooser().getSelectedIndex(), 1);
        });

        view.getCapChooser().addActionListener(e -> {
            getDataFromChooser(1, view.getCapChooser().getSelectedIndex(), 2);
        });

        view.getLabelChooser().addActionListener(e -> {
            getDataFromChooser(2, view.getLabelChooser().getSelectedIndex(), 3);
        });

        view.getMeasurerChooser().addActionListener(e -> {
            getDataFromChooser(3, view.getMeasurerChooser().getSelectedIndex(), 4);
        });

        view.getUnitBoxChooser().addActionListener(e -> {
            getDataFromChooser(4, view.getUnitBoxChooser().getSelectedIndex(), 5);
        });

        view.getLeafletChooser().addActionListener(e -> {
            getDataFromChooser(5, view.getLeafletChooser().getSelectedIndex(), 6);
        });

        view.getCollectiveBoxChooser().addActionListener(e -> {
            getDataFromChooser(6, view.getCollectiveBoxChooser().getSelectedIndex(), 7);
        });

        view.getPalleteChooser().addActionListener(e -> {
            getDataFromChooser(7, view.getPalleteChooser().getSelectedIndex(), 8);
        });

//        TODO: add autocalculating prices - iterates over table fields if not null calculates them

//                        =================================== Production costs choosers listeners

        view.getMFCostChooser().addActionListener(e -> {getDataFromProductionChooser(0, view.getMFCostChooser().getSelectedIndex(), 0);});

        view.getOHChooser().addActionListener(e -> {getDataFromProductionChooser(1, view.getOHChooser().getSelectedIndex(), 1);});

        view.getTestsChooser().addActionListener(e -> {getDataFromProductionChooser(2, view.getTestsChooser().getSelectedIndex(), 2);});

//                               =================================== Menu items listeners

        view.getMenuItemChangePricesSource().addActionListener(e -> {
            pricesTable.loadMaterialsPricesTableDataSource(view.getFrame());
        });

        view.getMenuItemChangeProductionPricesSource().addActionListener(e -> {
            pricesTable.loadProductionTableDataSource(view.getFrame());
        });

//                                       =================================== Cogs price listeners
        view.getCogsEurPriceTextField().addActionListener(e -> {
            if (view.getCogsEurPriceTextField().getText().length() > 0){
                view.getCogsPlnPriceTextField().setText(String.valueOf(round(Double.valueOf(view.getCogsEurPriceTextField().getText())*pricesTable.getEuroRate(),2)));
                view.getCogsEurMarginTextField().setText(String.valueOf(round((Double.valueOf(view.getCogsEurPriceTextField().getText())-((subtotalRawCosts + subtotalMaterialsCosts + subtotalProductionCosts)/1000)/pricesTable.getEuroRate()),2) + " Eur"));
            }
        });

        view.getCalculateCostsButton().addActionListener(e -> {
            if (view.getCogsEurPriceTextField().getText().length() > 0){
                double cogsPlnPrice = round(Double.valueOf(view.getCogsEurPriceTextField().getText())*pricesTable.getEuroRate(), 2);
                double cogsValue = ((subtotalRawCosts + subtotalMaterialsCosts + subtotalProductionCosts)/1000);
                
                view.getCogsPlnPriceTextField().setText(String.valueOf(cogsPlnPrice));
                view.getCogsEurMarginTextField().setText(round((Double.valueOf(view.getCogsEurPriceTextField().getText())-cogsValue/pricesTable.getEuroRate()),2) + " Eur");
                view.getCogsPlnMarginTexField().setText(round((cogsPlnPrice-cogsValue),2) + " Pln");
                view.getCogsMarginPercantageTextField().setText(String.valueOf((int)(round(((Double.valueOf(view.getCogsEurPriceTextField().getText())-cogsValue/pricesTable.getEuroRate())/Double.valueOf(view.getCogsEurPriceTextField().getText()))*100,0))));

            } else if (view.getCogsPlnPriceTextField().getText().length() > 0) {
                view.getCogsEurPriceTextField().setText(String.valueOf(round(Double.valueOf(view.getCogsPlnPriceTextField().getText())/pricesTable.getEuroRate(),2)));
                view.getCogsEurMarginTextField().setText(round((Double.valueOf(view.getCogsPlnPriceTextField().getText())/pricesTable.getEuroRate()-((subtotalRawCosts + subtotalMaterialsCosts + subtotalProductionCosts)/1000)/pricesTable.getEuroRate()),2) + " Eur");
            }
        });

    }

//    Initializing tables with data
    protected void loadAndDisplayDataForMaterialsTable(){
//        Loading data for JCombo boxes materials table

        for (int i=1;i<9;i++){

            if (view.getMaterialsChoosersList().get(i-1).getItemCount() > 0){ // Clears choosers before re uploading them in the 2nd ad next calls
                view.getMaterialsChoosersList().get(i-1).removeAllItems();
            }

            // Get a set of the entries
            Set set = pricesTable.getRmpml().getRmpml().get(i).getProductNumberDict().entrySet();
            // Get an iterator
            Iterator iter = set.iterator();

            while(iter.hasNext()) {
                Map.Entry me = (Map.Entry)iter.next();
                view.getMaterialsChoosersList().get(i-1).addItem(me.getValue());
            }
        }
        view.repaintTables();
    }


    public void loadAndDisplayRawsTable(){
        for (int i = 0; i < formulationTableClass.getCounter().length; i++){
            for (int j=0;j<6;j++) {
                view.getFormulationData()[i][j] = formulationTableClass.getFormulationTable()[j][i];
            }
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


 public void calculateIngredients(){
     try {
         for (int i = 0; i < formulationTableClass.getCounter().length; i++) {
             cogsTable.getItemName1()[i] = formulationTableClass.getRawMaterialsNames()[i];
             cogsTable.getMu()[i] = "kg";
             cogsTable.getQty()[i] = round(formulationTableClass.getAmountPerKG()[i], 2);
             cogsTable.getSystemNumbers()[i] = formulationTableClass.getSystemNumbers()[i];
             for (int j = 0; j < pricesTable.getRmpml().getRmpml().get(0).getCounter().length; j++) {
                 if (formulationTableClass.getSystemNumbers()[i].equals(pricesTable.getRmpml().getRmpml().get(0).getSystemNumbers()[j])){
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
                 cogsTable.getPln()[i] = round((cogsTable.getPurchasePrice()[i]*pricesTable.getEuroRate()),2);
                 } else if (cogsTable.getCurrency()[i].equals("USD")) {
                     cogsTable.getPln()[i] = round((cogsTable.getPurchasePrice()[i]*pricesTable.getUsdRate()),2);
                 } else {
                     cogsTable.getPln()[i] = cogsTable.getPurchasePrice()[i];
                 }
             } else {
                 cogsTable.getPln()[i] = 0.0;
             }
             if (cogsTable.getPln()[i] != null) {
                cogsTable.getPlnQty()[i] = round(cogsTable.getPln()[i] * cogsTable.getQty()[i],2);
                subtotalRawCosts+=cogsTable.getPlnQty()[i];
             } else {
                 cogsTable.getPlnQty()[i] = 0.0;
             }
             view.getCogsRawSubtotalTextField().setText(round((subtotalRawCosts),2) + " PLN");
             view.getCogsTotalCostsTextField().setText(round((subtotalRawCosts + subtotalMaterialsCosts)/1000,2) + " PLN");
         }

         //         PRODUCTION ============




     } catch (NullPointerException e){
         new PopUpInfo("Nie udało się załadować danych", view.getFrame());
         e.printStackTrace();
     }
 }

 public void loadAndDisplayProductionTable(){

     for (int i=0;i<3;i++){
         // Get a set of the entries
         Set productionSet = pricesTable.getPmpml().getPmpml().get(i).getProductNumberDict().entrySet();
         // Get an iterator
         Iterator productionIter = productionSet.iterator();

         while(productionIter.hasNext()) {
             Map.Entry me = (Map.Entry)productionIter.next();
             view.getProductionChoosersList().get(i).addItem(me.getValue());
         }
     }
     view.repaintTables();

 }


    public void getDataFromChooser(int materialsTableRow, int selectedIndex, int sheetNumberFromPriceXls) {
        if (selectedIndex > -1) {
            System.out.println(pricesTable.getRmpml().getRmpml().get(7).getMaxPrice()[selectedIndex]);
            view.getCogsMaterialsData()[materialsTableRow][0] = sheetNumberFromPriceXls;
            view.getCogsMaterialsData()[materialsTableRow][3] = 1;
            view.getCogsMaterialsData()[materialsTableRow][4] = "pcs";
            if (pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMinPrice() != null) {
                view.getCogsMaterialsData()[materialsTableRow][5] = pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex];
            } else if (pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice() != null) {
                view.getCogsMaterialsData()[materialsTableRow][5] = pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex];
            }
            view.getCogsMaterialsData()[materialsTableRow][6] = pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getCurrency()[selectedIndex];
            calculateMaterials(materialsTableRow, selectedIndex, sheetNumberFromPriceXls);
            view.repaintTables();
        }
    }

    public void getDataFromProductionChooser(int materialsTableRow, int selectedIndex, int sheetNumberFromPriceXls) {
        if (selectedIndex > -1) {
            view.getCogsProductionData()[materialsTableRow][0] = sheetNumberFromPriceXls+1;
            view.getCogsProductionData()[materialsTableRow][1] = pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getRawMaterialsNames()[selectedIndex];
            view.getCogsProductionData()[materialsTableRow][3] = 1;
            view.getCogsProductionData()[materialsTableRow][4] = "pcs";
            view.getCogsProductionData()[materialsTableRow][6] = pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getCurrency()[selectedIndex];
//            TODO: Solve issue with 1+ need
            calculateProduction(materialsTableRow, selectedIndex, sheetNumberFromPriceXls);
            view.repaintTables();
        }
    }

    public void calculateProduction(int materialsTableRow, int selectedIndex, int sheetNumberFromPriceXls){
//        TODO: add also min price for calculations
        Double plnPrice = 0.0;
        if (selectedIndex > -1) {
            if (String.valueOf(view.getCogsProductionData()[materialsTableRow][6]).equals("EUR")) {
                view.getCogsProductionData()[materialsTableRow][7] = round(pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getPrice()[selectedIndex]*pricesTable.getEuroRate(), 2);
                plnPrice = round(pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getPrice()[selectedIndex]*pricesTable.getEuroRate(), 2);
            } else if (String.valueOf(view.getCogsProductionData()[materialsTableRow][6]).equals("USD")){
                view.getCogsProductionData()[materialsTableRow][7] = round(pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getPrice()[selectedIndex]*pricesTable.getUsdRate(), 2);
                plnPrice = round(pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getPrice()[selectedIndex]*pricesTable.getUsdRate(), 2);
            } else {
                view.getCogsProductionData()[materialsTableRow][7] = pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getPrice()[selectedIndex];
                plnPrice = (pricesTable.getPmpml().getPmpml().get(sheetNumberFromPriceXls).getPrice()[selectedIndex]);
            }
        } else {
            view.getCogsProductionData()[materialsTableRow][7] = 0.0;
        }

        if (view.getCogsProductionData()[materialsTableRow][7] != null) {
            view.getCogsProductionData()[materialsTableRow][8] = plnPrice * Double.valueOf(1);
            subtotalProductionList[sheetNumberFromPriceXls]=plnPrice * Double.valueOf(1);
        } else {
            view.getCogsProductionData()[materialsTableRow][8] = 0.0;
        }

        subtotalProductionCosts = 0.0;
        for (Double subtotalCost : subtotalProductionList){
            subtotalProductionCosts += subtotalCost;}
        view.getCogsProductionSubtotalTextField().setText(round((subtotalProductionCosts),2) + " PLN");
        view.getCogsTotalCostsTextField().setText(round((subtotalRawCosts + subtotalMaterialsCosts + subtotalProductionCosts)/1000,2) + " PLN");

    }


 public void calculateMaterials(int materialsTableRow, int selectedIndex, int sheetNumberFromPriceXls){
//        TODO: add also min price for calculations
     Double plnPrice = 0.0;
     if (view.getCogsMaterialsData()[materialsTableRow][5] != null) {
         if (String.valueOf(view.getCogsMaterialsData()[materialsTableRow][6]).equals("EUR")) {
             view.getCogsMaterialsData()[materialsTableRow][7] = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getEuroRate(), 2);
             plnPrice = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getEuroRate(), 2);
         } else if (String.valueOf(view.getCogsMaterialsData()[materialsTableRow][6]).equals("USD")){
             view.getCogsMaterialsData()[materialsTableRow][7] = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getUsdRate(), 2);
             plnPrice = round(pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]*pricesTable.getUsdRate(), 2);
         } else {
             view.getCogsMaterialsData()[materialsTableRow][7] = pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex];
             plnPrice = (pricesTable.getRmpml().getRmpml().get(sheetNumberFromPriceXls).getMaxPrice()[selectedIndex]);
         }
     } else {
         view.getCogsMaterialsData()[materialsTableRow][7] = 0.0;
     }

     if (view.getCogsMaterialsData()[materialsTableRow][7] != null) {
         view.getCogsMaterialsData()[materialsTableRow][8] = plnPrice * Double.valueOf(1000);
         subtotalMaterialssList[sheetNumberFromPriceXls-1]=plnPrice * Double.valueOf(1000);
     } else {
         view.getCogsMaterialsData()[materialsTableRow][8] = 0.0;
     }

     subtotalMaterialsCosts = 0.0;
     for (Double subtotalCost : subtotalMaterialssList){
         subtotalMaterialsCosts += subtotalCost;}
     view.getCogsMaterialsSubtotalTextField().setText(round((subtotalMaterialsCosts),2) + " PLN");
     view.getCogsTotalCostsTextField().setText(round((subtotalRawCosts + subtotalMaterialsCosts + subtotalProductionCosts)/1000,2) + " PLN");

     view.getCogsEurTextField().setText(round((subtotalRawCosts + subtotalMaterialsCosts + subtotalProductionCosts)/1000/pricesTable.getEuroRate(),2) + " EUR");
     view.getCogsPlnTextField().setText(round((subtotalRawCosts + subtotalMaterialsCosts + subtotalProductionCosts)/1000,2) + " PLN");

    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
