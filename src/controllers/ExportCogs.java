package controllers;

import controllers.Controller;
import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class ExportCogs {

    private JFileChooser jfc;
    private Controller controller;
    private JFrame currentFrame;
    private File fileToSave;
    private String savePath = FileSystems.getDefault().getPath("").toAbsolutePath().toString();

    private WritableWorkbook wbToSave;
    private WritableSheet sheetToSave;
    private WritableCell cellToSave;

    private String[] cogsTableColumnNames = {"No.","Item", "Item 2",
            "QTY","m.u.", "Purchase price", "Currency", "PLN", "PLN * QTY"};
    private int rowNoVar = 0, columnNoVar = 0;

    public ExportCogs(Controller controller) {
        this.controller = controller;
        this.jfc = new JFileChooser();
        jfc.setDialogTitle("Export cogs");
        jfc.setCurrentDirectory(new java.io.File("."));

        chooseSavePlace();
        exportCogs();
    }

//    Opens file chooser and selects place to save cogs
    private void chooseSavePlace() {
        int returnValue = jfc.showSaveDialog(currentFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fileToSave = jfc.getSelectedFile();
            savePath = fileToSave.getAbsolutePath();
        } else {
            System.out.println("No Selection ");
        }
    }

        private void exportCogs(){
            try {
                if (savePath.endsWith(".xls")){
                wbToSave = Workbook.createWorkbook(new File(savePath));
                } else {
                    wbToSave = Workbook.createWorkbook(new File(savePath+".xls"));}
                sheetToSave = wbToSave.createSheet(controller.formulationTableClass.getProductName(),0);

//                ------------------

                for (int columnFirstRow=0;columnFirstRow<cogsTableColumnNames.length;columnFirstRow++){
                    cellToSave = new Label(columnFirstRow, rowNoVar, cogsTableColumnNames[columnFirstRow], createCellFormat(true, 1));
                    sheetToSave.addCell(cellToSave);
                }

                for (int rowNo=0; rowNo<controller.view.getCogsMaterialsData().length;rowNo++){
                    for (int columnNo=0; columnNo<controller.view.getCogsMaterialsData()[rowNo].length;columnNo++){
                        cellToSave = new Label(columnNo, rowNo+1, String.valueOf(controller.view.getCogsMaterialsData()[rowNo][columnNo]), createCellFormat(true, 2));
                        sheetToSave.addCell(cellToSave);
                        System.out.println("i "+rowNo);
                        System.out.println("j "+columnNo);
                    }
                    this.rowNoVar = rowNo+1;
                }

                cellToSave = new Label(controller.view.getCogsMaterialsData().length, rowNoVar+1, String.valueOf(controller.getSubtotalMaterialsCosts()), createCellFormat(true, 1));
                sheetToSave.addCell(cellToSave);

//                ------------------

                this.columnNoVar = 0;
                this.rowNoVar = controller.view.getCogsMaterialsData().length+2;

                for (int columnFirstRow=columnNoVar;columnFirstRow<cogsTableColumnNames.length;columnFirstRow++){
                    cellToSave = new Label(columnFirstRow, rowNoVar, cogsTableColumnNames[columnFirstRow], createCellFormat(true, 1));
                    sheetToSave.addCell(cellToSave);
                }

                for (int rowNo=0; rowNo<controller.view.getCogsRawData().length;rowNo++){
                    for (int columnNo=0; columnNo<controller.view.getCogsRawData()[rowNo].length;columnNo++){
                        cellToSave = new Label(columnNo, rowNoVar, String.valueOf(controller.view.getCogsRawData()[rowNo][columnNo]), createCellFormat(true, 2));
                        sheetToSave.addCell(cellToSave);
                        System.out.println("i "+rowNo);
                        System.out.println("j "+columnNo);
                        this.columnNoVar++;
                    }
                    this.rowNoVar++;
                }

                cellToSave = new Label(controller.view.getCogsMaterialsData().length, rowNoVar, String.valueOf(controller.getSubtotalRawCosts()), createCellFormat(true, 1));
                sheetToSave.addCell(cellToSave);

//                                ------------------

                this.columnNoVar = 0;
                this.rowNoVar ++;

                for (int columnFirstRow=columnNoVar;columnFirstRow<cogsTableColumnNames.length;columnFirstRow++){
                    cellToSave = new Label(columnFirstRow, rowNoVar, cogsTableColumnNames[columnFirstRow], createCellFormat(true, 1));
                    sheetToSave.addCell(cellToSave);
                }

                for (int rowNo=0; rowNo<controller.view.getCogsProductionData().length;rowNo++){
                    for (int columnNo=0; columnNo<controller.view.getCogsProductionData()[rowNo].length;columnNo++){
                        cellToSave = new Label(columnNo, rowNoVar, String.valueOf(controller.view.getCogsProductionData()[rowNo][columnNo]), createCellFormat(true, 2));
                        sheetToSave.addCell(cellToSave);
                        System.out.println("i "+rowNo);
                        System.out.println("j "+columnNo);
                        this.columnNoVar++;
                    }
                    this.rowNoVar++;
                }

                cellToSave = new Label(controller.view.getCogsMaterialsData().length, rowNoVar, String.valueOf(controller.getSubtotalProductionCosts()), createCellFormat(true, 1));
                sheetToSave.addCell(cellToSave);

                this.rowNoVar++;

//                TODO: move round to controller
//                cellToSave = new Label(controller.view.getCogsMaterialsData().length, rowNoVar, String.valueOf(controller.round((controller.getSubtotalRawCosts()
//                        +controller.getSubtotalMaterialsCosts()
//                        +controller.getSubtotalProductionCosts())/1000, 2))+" PLN");
//                sheetToSave.addCell(cellToSave);

                int columnNo = controller.view.getCogsMaterialsData().length -2;
                int cogsNumbersCounter = 0;
                int cogsNamesCounter = 0;
                    for (int k=0; k<10;k++){
                        if (columnNo == controller.view.getCogsMaterialsData().length-2){
                            cellToSave = new Label(columnNo,rowNoVar,String.valueOf(controller.cogsTable.getCogsPriceElementsNames()[cogsNamesCounter]), createCellFormat(true, 2));
                            columnNo = controller.view.getCogsMaterialsData().length -1;
                            cogsNamesCounter++;
                            sheetToSave.addCell(cellToSave);
                        } else if (columnNo == controller.view.getCogsMaterialsData().length-1){
                        cellToSave = new Label(columnNo,rowNoVar,String.valueOf(controller.cogsTable.getCogsPriceElements()[cogsNumbersCounter])+ " Eur", createCellFormat(true, 2));
                        columnNo = controller.view.getCogsMaterialsData().length;
                        cogsNumbersCounter++;
                        sheetToSave.addCell(cellToSave);
                    } else if (columnNo == controller.view.getCogsMaterialsData().length){
                            cellToSave = new Label(columnNo,rowNoVar,String.valueOf(controller.cogsTable.getCogsPriceElements()[cogsNumbersCounter])+ " Pln", createCellFormat(true, 2));
                            rowNoVar++;
                            cogsNumbersCounter++;
                            columnNo = controller.view.getCogsMaterialsData().length-2;
                            sheetToSave.addCell(cellToSave);
                        }
                    }

                cellToSave = new Label(columnNo,rowNoVar,String.valueOf(controller.cogsTable.getPercentageMargin())+" %", createCellFormat(true, 2));
                sheetToSave.addCell(cellToSave);

//                Changing cells look


                try {
                    wbToSave.write();
                    wbToSave.close();
                } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

//            System.out.println(savePath);
        }

    public WritableCellFormat createCellFormat(boolean b, int formatType) throws WriteException {
        WritableFont wfontStatus;
        WritableCellFormat fCellstatus;
        if (formatType == 1) {
            wfontStatus = new WritableFont(WritableFont.createFont("Arial"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            fCellstatus = new WritableCellFormat(wfontStatus);


            fCellstatus.setWrap(true);
            fCellstatus.setAlignment(jxl.format.Alignment.CENTRE);
            fCellstatus.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            fCellstatus.setBorder(jxl.format.Border.ALL, BorderLineStyle.THICK, Colour.BLACK);
            fCellstatus.setBackground(Colour.LIGHT_TURQUOISE2);
            return fCellstatus;
        } else {
            wfontStatus = new WritableFont(WritableFont.createFont("Arial"), WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            fCellstatus = new WritableCellFormat(wfontStatus);


            fCellstatus.setWrap(true);
            fCellstatus.setAlignment(jxl.format.Alignment.CENTRE);
            fCellstatus.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            fCellstatus.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM, Colour.BLACK);
            return fCellstatus;
    }

    }



}
