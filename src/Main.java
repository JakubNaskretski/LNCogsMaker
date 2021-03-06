import controllers.Controller;
import tablesModels.FormulationTable;
import tablesModels.PricesTable;
import views.PopUpInfoView;
import views.StartingView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
//        TODO: Consider making virtual table with all subtotal etc prices
//        TODO: Subtotal costs don't reset after new formulation
//        TODO: set final rows number for production table
//        TODO: Add table and logi for production part
//        TODO: check if all forulations wiht decimal amounts will work
//        TODO: Add logic after "add new bottle"
//        Add manual price adding
//        TODO: Lock down table cells
//        TODO: Consider auto expanding tables
//        TODO: Write One function to read xls tables
//            Model m = new Model();
//        TODO: Add version for capsules also
//        TODO: replace all Rounds in controller
//        TODO: pass parent window

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            new PopUpInfoView("Cannot find look class");
        }
        catch (InstantiationException e) {
            new PopUpInfoView("Instation exception with look class");
        }
        catch (IllegalAccessException e) {
            new PopUpInfoView("No acces for look class");
        }

        StartingView sv = new StartingView();
        FormulationTable ft = new FormulationTable();
        PricesTable pt = new PricesTable();
        Controller c = new Controller(sv, ft, pt);
    }



}
