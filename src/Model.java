//package com.company;
//
//public class Model {
//
//    private int rawMaterialsFormulationTableCounter = 9;
//    private double euroRate, usdRate;
//    private String inputFile;
//    private Object[][] formulationTable, cogsTable;
//    private Integer[] counterFormulationTable = {1,2,3,4,5,6,7,8,9};
//    private String[] rawMaterialsNamesFormulationTable;
//    private String[] systemNumbersFormulationTable;
//    private Double[] amountPerDosageFormulationTable;
//    private String[] chemicalFormFormulationTable;
//    private Integer[] amountPerKGFormulationTable;
//
////    COGS ITEMS
//    private Integer[] counterCogsTable = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
//    private String[] itemNameCogsTable = new String[30];
//
////    Setting model for formulation
//    public Model() {
//        rawMaterialsNamesFormulationTable = new String[9];
//        systemNumbersFormulationTable = new String[9];
//        amountPerDosageFormulationTable = new Double[9];
//        chemicalFormFormulationTable = new String[9];
//        amountPerKGFormulationTable = new Integer[9];
//        Object[][] modelListData = {
//                counterFormulationTable,
//                rawMaterialsNamesFormulationTable,
//                systemNumbersFormulationTable,
//                amountPerDosageFormulationTable,
//                chemicalFormFormulationTable,
//                amountPerKGFormulationTable};
//        this.formulationTable = modelListData;
////        TODO: add auto updating exchange rate
//        this.euroRate = 4.3;
//        this.usdRate = 3.8;
//
//        for (int i=0;i<30;i++){
//            switch (i){
//                case 9:
//                    itemNameCogsTable[i] = "Subtotal of packaging";
//                    break;
//                case 20:
//                    itemNameCogsTable[i] = "Subtotal of ingredients";
//                    break;
//                case 29:
//                    itemNameCogsTable[i] = "Subtotal of manufacturing";
//                    break;
//            }
//        }
//
//
//    }
//
//    // ============== Getters n Setters ==============
//
//    public void setInputFile(String inputFile) {
//        this.inputFile = inputFile;
//    }
//
//    public Object[][] getModelListData() {
//        return formulationTable;
//    }
//
//    public void setModelListData(Object[][] modelListData) {
//        this.formulationTable = modelListData;
//    }
//
//    public Integer[] getRawMaterialsListsNumber() {
//        return counterFormulationTable;
//    }
//
//    public Integer[] getCounterCogsTable() {
//        return counterCogsTable;
//    }
//
//    public String[] getItemNameCogsTable() {
//        return itemNameCogsTable;
//    }
//
//    public Object[][] getFormulationTable() {
//        return formulationTable;
//    }
//
//}
