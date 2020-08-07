public class Main {

    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
//        TODO: Add table and logi for production part
//        TODO: check if all forulations wiht decimal amounts will work
//        TODO: Add logic after "add new bottle"
//        Add manual price adding
//        TODO: Lock down table cells
//        TODO: Consider auto expanding tables
//        TODO: Write One function to read xls tables
//            Model m = new Model();
//        TODO: Add version for capsules also
        StartingView sv = new StartingView();
        View v = new View();
        FormulationTable ft = new FormulationTable();
        PricesTable pt = new PricesTable();
        CogsTable ct = new CogsTable(9);
        Controller c = new Controller(sv, v, ft, pt, ct);
    }
}
