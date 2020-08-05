public class Main {

    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
//        TODO: Write One function to read xls tables
//            Model m = new Model();
        View v = new View();
        FormulationTable ft = new FormulationTable();
        PricesTable pt = new PricesTable();
        CogsTable ct = new CogsTable(9);
        Controller c = new Controller(v, ft, pt, ct);
    }
}
