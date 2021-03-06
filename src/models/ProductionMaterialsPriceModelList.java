package models;

import models.ProductionMaterialsPriceModel;

import java.util.ArrayList;

public class ProductionMaterialsPriceModelList {

    private ArrayList<ProductionMaterialsPriceModel> pmpml;

    public ProductionMaterialsPriceModelList() {
        this.pmpml = new ArrayList<ProductionMaterialsPriceModel>();
    }

    public ArrayList<ProductionMaterialsPriceModel> getPmpml() {
        return pmpml;
    }

    public void setPmpml(ArrayList<ProductionMaterialsPriceModel> pmpml) {
        this.pmpml = pmpml;
    }

    public void clearPmpml(){
        pmpml.clear();
    }

    public int getPmpmlSize(){
        return pmpml.size();
    }
}
