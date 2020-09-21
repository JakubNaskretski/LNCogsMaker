package models;

import java.util.ArrayList;

// Class for storing RawMaterialsPricesModels
public class RawMaterialsPriceModelList {

    private ArrayList<RawMaterialsPricesModel> rmpml;

    public RawMaterialsPriceModelList() {
        this.rmpml = new ArrayList<RawMaterialsPricesModel>();
    }

    public ArrayList<RawMaterialsPricesModel> getRmpml() {
        return rmpml;
    }

    public void clearRmpml(){
        rmpml.clear();
    }

    public void setRmpml( ArrayList<RawMaterialsPricesModel> rmpml ) {
        this.rmpml = rmpml;
    }

    public int getRmpmlSize(){
        return rmpml.size();
    }

}

