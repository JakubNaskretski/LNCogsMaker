import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LockedTableModel extends AbstractTableModel {

    private final List<Object> itemsList;

    private final String[] columnNames = new String[] {"No.","Item", "Item 2",
                    "QTY","m.u.", "Purchase price", "Currency", "PLN", "PLN * QTY"
    };
    private final Class[] columnClass = new Class[] {
            Integer.class, String.class, String.class, Integer.class,
            String.class, Double.class, String.class, Double.class, Double.class
    };

    public LockedTableModel(List<Object> itemsList)
    {
        this.itemsList = itemsList;
    }

    @Override
    public int getRowCount() {
        return itemsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public boolean isCellEditable(int row, int column){
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

}
