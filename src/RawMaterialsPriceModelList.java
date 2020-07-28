import java.util.ArrayList;

// Class for storing RawMaterialsPricesModels
public class RawMaterialsPriceModelList {

    private static ArrayList<RawMaterialsPricesModel> rmpml;

    public RawMaterialsPriceModelList() {
        this.rmpml = new ArrayList<RawMaterialsPricesModel>();
    }

    public ArrayList<RawMaterialsPricesModel> getRmpml() {
        return rmpml;
    }

    public void setRmpml( ArrayList<RawMaterialsPricesModel> rmpml ) {
        this.rmpml = rmpml;
    }
}
