import javax.naming.ldap.Control;
import javax.swing.*;

public class ChangeCurrencyController {

    private ChangeCurrencyView changeCurrencyView;
    private PricesTable pricesTable;
    private JFrame lockedFrame;
    private Controller controller;

    public ChangeCurrencyController(JFrame lockedFrame, PricesTable pricesTable, Controller controller) {
        this.controller = controller;
        this.lockedFrame = lockedFrame;
        this.pricesTable = pricesTable;
        this.changeCurrencyView = new ChangeCurrencyView(lockedFrame);
        lockedFrame.setEnabled(false);

        changeCurrencyView.getConfirmButton().addActionListener(e -> {

            if (changeCurrencyView.getCurrencyChooserField().getSelectedIndex() == 0){
                try{
                    if (changeCurrencyView.getPlnPrice().getText().contains(",")){
                        pricesTable.setEuroRate(Double.valueOf(changeCurrencyView.getPlnPrice().getText().replace(",",".")));
                } else {
                        pricesTable.setEuroRate(Double.valueOf(changeCurrencyView.getPlnPrice().getText()));
                    }
                } catch (Exception exc){
                    exc.printStackTrace();
                }
            } else if (changeCurrencyView.getCurrencyChooserField().getSelectedIndex() == 1){
                try{
                    if (changeCurrencyView.getPlnPrice().getText().contains(",")){
                        pricesTable.setUsdRate(Double.valueOf(changeCurrencyView.getPlnPrice().getText().replace(",",".")));
                    } else {
                        pricesTable.setUsdRate(Double.valueOf(changeCurrencyView.getPlnPrice().getText()));
                    }
                } catch (Exception exc){
                    exc.printStackTrace();
                    }
                }

            try {
            controller.calculateIngredients();
            controller.loadAndDisplayRawsTable();
            controller.loadAndDisplayProductionTable();
        } catch (Exception e1) {
            new PopUpInfo("Nie udało się załadować danych", changeCurrencyView.getMainFrame());}

            changeCurrencyView.getMainFrame().dispose();
            new PopUpInfo("New currencies ratio have been set - Eur: "+pricesTable.getEuroRate()+" USD: "+pricesTable.getUsdRate());
            controller.calculateCogsPrices();
            lockedFrame.setEnabled(true);
            });

        changeCurrencyView.getCancelButton().addActionListener(e -> {
                changeCurrencyView.getMainFrame().dispose();
                lockedFrame.setEnabled(true);
            });

       }


}
