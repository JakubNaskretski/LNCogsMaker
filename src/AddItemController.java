import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AddItemController {

    private AddItemView addItemView;
    private PricesTable pricesTable;
    private WritableWorkbook copiedWorkbook;
    private Workbook existingPriceWorkbook;
    private JFrame cogsView;
    private int itemSheetNumber;

    private Label l;
    private WritableCell writableCell;

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
            addItem();
            addItemView.getFrame().dispose();
            cogsView.setEnabled(true);
        });

        addItemView.getCancelButton().addActionListener(e -> {
            addItemView.getFrame().dispose();
            cogsView.setEnabled(true);
        });
    }

    private void addItem(){
//        TODO: Is this many trycatch needed
        try {
            existingPriceWorkbook = Workbook.getWorkbook(new File(pricesTable.getRawMaterialsTablePricesPath()));
            copiedWorkbook = Workbook.createWorkbook(new File(pricesTable.getRawMaterialsPricePathFolderName()+"\\copiedPrices.xls"), existingPriceWorkbook);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        int rowNo = (existingPriceWorkbook.getSheet(itemSheetNumber).getRows());
        try {
        for (int columnNo = 1; columnNo < 7; columnNo++) {
              switch (columnNo){
                  case 1: // Checks previous cell gets int number from string, adds number to the new cell
                      String lastCellValue = existingPriceWorkbook.getSheet(itemSheetNumber).getCell(columnNo, rowNo-1).getContents();
                      int newCellNumber = Integer.valueOf(lastCellValue.substring(lastCellValue.length()-3))+1;
                      if (String.valueOf(newCellNumber).length() == 1){
                          l = new Label(columnNo, rowNo, (lastCellValue.substring(0, lastCellValue.length()-3)+"00"+newCellNumber));
                      }
                      else if (String.valueOf(newCellNumber).length() == 2){
                          l = new Label(columnNo, rowNo, (lastCellValue.substring(0, lastCellValue.length()-3)+"0"+newCellNumber));
                      }
                      else if (String.valueOf(newCellNumber).length() == 3){
                          l = new Label(columnNo, rowNo, (lastCellValue.substring(0, lastCellValue.length()-3)+newCellNumber));
                      }
                      writableCell = l;
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 2:
                      l = new Label(columnNo, rowNo, addItemView.getProductNameTextField().getText());
                      writableCell = l;
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 3:
//                      TODO: Add verify method
                      l = new Label(columnNo, rowNo, addItemView.getPurchaseMinPriceTextField().getText());
                      writableCell = l;
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 4:
                      l = new Label(columnNo, rowNo, addItemView.getPurchaseMaxPriceTextField().getText());
                      writableCell = l;
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 5:
//                      TODO: add currency chooser
                      l = new Label(columnNo, rowNo, "PLN");
                      writableCell = l;
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 6:
                      break;
              }
          }
        } catch (WriteException e) {
            System.out.println("Nie udało się dodać komórki");
            new PopUpInfo("Nie udało się dodać komórki");
        }

        try {
            copiedWorkbook.write();
        copiedWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        existingPriceWorkbook.close();

        File originalSheet = new File(pricesTable.getRawMaterialsTablePricesPath());
        originalSheet.delete();

        File copiedSheet = new File(pricesTable.getRawMaterialsPricePathFolderName()+"\\copiedPrices.xls");
        copiedSheet.renameTo(new File(pricesTable.getRawMaterialsTablePricesPath()));

    }


}
