import jxl.Cell;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AddItemController {

    private AddItemView addItemView;
    private PricesTable pricesTable;
    private WritableWorkbook copiedWorkbook;
    private Workbook existingPriceWorkbook;
    private JFrame cogsView;
    private Controller controller;


    private int itemSheetNumber, newCellNumber;
    private String lastCellValue, textForCell;
    private WritableCell writableCell;

    public AddItemController(JFrame cogsView, PricesTable pricesTable, String itemType, Controller controller) {
        this.controller = controller;
        this.cogsView = cogsView;
        this.pricesTable = pricesTable;

//        TODO: Fix this solution
        if (itemType.equals("Bottle")){
                itemSheetNumber = 1;
        } else if (itemType.equals("Cap")) {
            itemSheetNumber = 2;
        } else if (itemType.equals("Measurer")) {
            itemSheetNumber = 4;
        } else if (itemType.equals("Unit box")) {
            itemSheetNumber = 5;
        } else if (itemType.equals("Leaflet")) {
            itemSheetNumber = 6;
        } else if (itemType.equals("Collective box")) {
            itemSheetNumber = 7;
        } else if (itemType.equals("Pallete")) {

        }
        cogsView.setEnabled(false);
    }

    public void initAddItemView(){
        addItemView = new AddItemView();

        addItemView.getConfirmButton().addActionListener(e -> {
            addItem();
            cogsView.setEnabled(true);

//            TODO: Fix this temporary solution
            pricesTable.loadPriceListsFromExcel();
            controller.loadAndDisplayDataForMaterialsTable();

            addItemView.getFrame().dispose();
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
        for (int columnNo = 0; columnNo < 7; columnNo++) {
              switch (columnNo){
                  case 0:
                      lastCellValue = existingPriceWorkbook.getSheet(itemSheetNumber).getCell(columnNo, rowNo-1).getContents();
                      newCellNumber = (Integer.valueOf(lastCellValue))+1;
//                      writableCell = new Label(columnNo, rowNo, String.valueOf(newCellNumber));
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(new Number(columnNo,rowNo,newCellNumber));
                      break;
                  case 1: // Checks previous cell gets int number from string, adds number to the new cell
                      lastCellValue = existingPriceWorkbook.getSheet(itemSheetNumber).getCell(columnNo, rowNo-1).getContents();
                       newCellNumber = Integer.valueOf(lastCellValue.substring(lastCellValue.length()-3))+1;
                      if (String.valueOf(newCellNumber).length() == 1){
                          writableCell = new Label(columnNo, rowNo, (lastCellValue.substring(0, lastCellValue.length()-3)+"00"+newCellNumber));
                      }
                      else if (String.valueOf(newCellNumber).length() == 2){
                          writableCell = new Label(columnNo, rowNo, (lastCellValue.substring(0, lastCellValue.length()-3)+"0"+newCellNumber));
                      }
                      else if (String.valueOf(newCellNumber).length() == 3){
                          writableCell = new Label(columnNo, rowNo, (lastCellValue.substring(0, lastCellValue.length()-3)+newCellNumber));
                      }
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 2:
                      writableCell = new Label(columnNo, rowNo, addItemView.getProductNameTextField().getText());
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 3:
//                      TODO: Add verify method
                      if (addItemView.getPurchaseMinPriceTextField().getText().equals("Null")){
                          copiedWorkbook.getSheet(itemSheetNumber).addCell(new Number(columnNo,rowNo,0));
                      } else {
                          if (addItemView.getPurchaseMinPriceTextField().getText().contains(",")){
                              textForCell = addItemView.getPurchaseMinPriceTextField().getText().replace(",",".");
                              copiedWorkbook.getSheet(itemSheetNumber).addCell(new Number(columnNo,rowNo,Double.valueOf(textForCell)));
                          } else {
                              copiedWorkbook.getSheet(itemSheetNumber).addCell(new Number(columnNo,rowNo,Double.valueOf(addItemView.getPurchaseMinPriceTextField().getText())));
                          }
                      }
                      break;
                  case 4:
                      if (addItemView.getPurchaseMaxPriceTextField().getText().equals("Null")){
                          copiedWorkbook.getSheet(itemSheetNumber).addCell(new Number(columnNo,rowNo,0));
                      } else {
                          if (addItemView.getPurchaseMaxPriceTextField().getText().contains(",")){
                              textForCell = addItemView.getPurchaseMaxPriceTextField().getText().replace(",",".");
                              copiedWorkbook.getSheet(itemSheetNumber).addCell(new Number(columnNo,rowNo,Double.valueOf(textForCell)));
                          } else {
                              copiedWorkbook.getSheet(itemSheetNumber).addCell(new Number(columnNo,rowNo,Double.valueOf(addItemView.getPurchaseMaxPriceTextField().getText())));
                          }
                      }
                      break;
                  case 5:
//                      TODO: add currency chooser
                      writableCell = new Label(columnNo, rowNo, addItemView.getCurrencyChooserField().getSelectedItem().toString());
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
                  case 6:
                      writableCell = new Label(columnNo, rowNo, addItemView.getProductSupplierTextField().getText());
                      copiedWorkbook.getSheet(itemSheetNumber).addCell(writableCell);
                      break;
              }
          }
            new PopUpInfo("Dodano nowy przedmiot do cennika", cogsView);
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
