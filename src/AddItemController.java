import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AddItemController {

    private AddItemView addItemView;
    private PricesTable pricesTable;
    private JFrame cogsView;
    private int itemSheetNumber;

    public AddItemController(JFrame cogsView, PricesTable pricesTable, String itemType) {
        this.cogsView = cogsView;
        this.pricesTable = pricesTable;

        switch (itemType){
            case "Bottle":
                itemSheetNumber = 1;
                break;
            case "Cap":
                itemSheetNumber = 2;
                break;
            case "Label":
                itemSheetNumber = 3;
                break;
            case "Measurer":
                itemSheetNumber = 4;
                break;
            case "Unit box":
                itemSheetNumber = 5;
                break;
            case "Leaflet":
                itemSheetNumber = 6;
                break;
            case "Collective box":
                itemSheetNumber = 7;
                break;
            case "Pallete":
                itemSheetNumber = 8;
                break;
        }

        cogsView.setEnabled(false);
    }

    public void initAddItemView(){
        addItemView = new AddItemView();
        addItemView.getConfirmButton().addActionListener(e -> {

            cogsView.setEnabled(true);
        });

        addItemView.getCancelButton().addActionListener(e -> {
            addItemView.getFrame().dispose();
            cogsView.setEnabled(true);
        });
    }

    private void readSheet(){
        File materialsInputWorkbook = new File(pricesTable.getRawMaterialsTablePricesPath());
        try {
          Workbook w = Workbook.getWorkbook(materialsInputWorkbook);
          int rowNo = (w.getSheet(itemSheetNumber).getRows())+1;
          for (int columnNo=1;columnNo<7;columnNo++){
              Cell cell = w.getSheet(itemSheetNumber).getCell(columnNo, rowNo);

          }

        }  catch (BiffException | IOException e) {
            new PopUpInfo("Nie udało się wczytać arkusza do dodania ceny");
        }

    }


}
