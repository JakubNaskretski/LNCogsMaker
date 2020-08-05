import java.util.Enumeration;

// Connect all classes together
public class Controller {

//    private Model model;
    private View view;
    private FormulationTable formulationTableClass;
    private PricesTable pricesTable;
    private CogsTable cogsTable;


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
            view.getFrame().repaint();
            calculateCogs();
            initTable();
        });
        view.getBottleChooser().addActionListener(e -> {

           if (view.getBottleChooser().getSelectedIndex() > -1) {
               System.out.println(pricesTable.getRmpml().getRmpml().get(1).getMaxPrice()[view.getBottleChooser().getSelectedIndex()]);
               view.getCogsMaterialsData()[0][0] = pricesTable.getRmpml().getRmpml().get(1).getCounter()[view.getBottleChooser().getSelectedIndex()];
               view.getCogsMaterialsData()[0][3] = 1;
               view.getCogsMaterialsData()[0][4] = "pcs";
//               TODO: set filter if min and max
               view.getCogsMaterialsData()[0][5] = pricesTable.getRmpml().getRmpml().get(1).getMaxPrice()[view.getBottleChooser().getSelectedIndex()];
               view.getCogsMaterialsData()[0][6] = pricesTable.getRmpml().getRmpml().get(1).getCurrency()[view.getBottleChooser().getSelectedIndex()];
           }

        });
        view.getMenuItemChangePricesSource().addActionListener(e -> {
            pricesTable.loadPricesTableDataSource(view.getFrame());
        });

    }

//    Initializing tables with data
    private void initTable(){

//        Loading data for JCombo boxes materials table
        for (int i=1;i<8;i++){
            for (Enumeration e = pricesTable.getRmpml().getRmpml().get(i).getProductNumberDict().elements(); e.hasMoreElements();){
                view.getMaterialsChoosersList().get(i-1).addItem(e.nextElement());
            }
        }

        view.createFormulationDataTable();
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
         double subtotalRawCosts = 0;

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
                 if (cogsTable.getCurrency()[i] == "EUR") {
                 cogsTable.getPln()[i] = (cogsTable.getPurchasePrice()[i]*pricesTable.getEuroRate());
                 } else if (cogsTable.getCurrency()[i] == "USD") {
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

             view.getCogsRawSubtotalTextField().setText(String.valueOf(subtotalRawCosts) + " PLN");



         }

         //         PRODUCTION ============




     } catch (NullPointerException e){
         System.out.println("Nie załadowano danych");
         e.printStackTrace();
     }
 }


}
