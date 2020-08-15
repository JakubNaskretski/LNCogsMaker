import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;


// This class provides view for the application
public class View implements TableModelListener {


    private int formulationSize;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menuFile, menuAddItems;
    private JMenuItem menuItemNew, menuItemSave, menuItemChangePricesSource, menuItemChangeProductionPricesSource,
            menuItemAddNewBottle, menuItemAddNewCap, menuItemAddNewLabel, menuItemAddNewMeasurer, menuItemAddNewUnitBox, menuItemAddNewLeaflet, menuItemAddNewCollectiveBox, menuItemAddNewPallete,
            menuItemAddNewTests;
    private JTable formulationTable, cogsMaterialsTable, cogsRawTable, cogsProductionTable;
    private JComboBox bottleChooser, capChooser, labelChooser, measurerChooser, unitBoxChooser, leafletChooser, collectiveBoxChooser, palleteChooser, MFCostChooser, OHChooser, TestsChooser;
    private DefaultTableCellRenderer cellRenderer;
    private JScrollPane formulationTableScrollPane, cogsMaterialsTableScrollPane, cogsRawTableScrollPane, cogsProductionScrollPane;
    private JPanel formulationTablePane, cogsTablePane;
    private JButton loadFormulationButton;
    private JPanel mainJPanelContainer, middleInformationJPanel;
    private Object[][] formulationData, cogsMaterialsData, cogsRawData, cogsProductionData;
    private JLabel cogsDate, productNameLabel, clientNameLabel, productCapacityLabel, dateOfTheFormulationLabel;
    private JTextField cogsMaterialsSubtotalTextField, cogsRawSubtotalTextField, cogsProductionSubtotalTextField, cogsTotalCostsTextField;

    private ArrayList<TableCellEditor> materialsEditorsList = new ArrayList<TableCellEditor>();
    private ArrayList<JComboBox> materialsChoosersList = new ArrayList<>();

    private ArrayList<TableCellEditor> productionEditorsList = new ArrayList<TableCellEditor>();
    private ArrayList<JComboBox> productionChoosersList = new ArrayList<>();


    public View(int formulationSize){
        this.formulationSize = formulationSize;
        frame = new JFrame();
        frame.getContentPane();
        frame.getContentPane().setMaximumSize(new Dimension(1500, 1200));
        addComponentsToPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Auto cogs counter");
        frame.setLocationRelativeTo(null);
        frame.setLocation(0,0);

        // Display frame
        frame.pack();
        frame.setVisible(true);
    }

    protected void addComponentsToPane() {

//    Menu
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        menuFile = new JMenu("Plik");
        menuBar.add(menuFile);
        menuAddItems = new JMenu("Dodaj przedmioty");
        menuBar.add(menuAddItems);

        menuItemNew = new JMenuItem("New");
        menuFile.add(menuItemNew);
        menuItemSave = new JMenuItem("Save");
        menuFile.add(menuItemSave);
        menuItemChangePricesSource = new JMenuItem("Change materials price source");
        menuFile.add(menuItemChangePricesSource);
        menuItemChangeProductionPricesSource = new JMenuItem("Change production price source");
        menuFile.add(menuItemChangeProductionPricesSource);



        menuItemAddNewBottle = new JMenuItem("Add new bottle");
        menuAddItems.add(menuItemAddNewBottle);
        menuItemAddNewCap = new JMenuItem("Add new cap");
        menuAddItems.add(menuItemAddNewCap);
        menuItemAddNewLabel = new JMenuItem("Add new Label");
        menuAddItems.add(menuItemAddNewLabel);
        menuItemAddNewMeasurer = new JMenuItem("Add new measurer");
        menuAddItems.add(menuItemAddNewMeasurer);
        menuItemAddNewUnitBox = new JMenuItem("Add new unit box");
        menuAddItems.add(menuItemAddNewUnitBox);
        menuItemAddNewLeaflet = new JMenuItem("Add new leaflet");
        menuAddItems.add(menuItemAddNewLeaflet);
        menuItemAddNewCollectiveBox = new JMenuItem("Add new collective box");
        menuAddItems.add(menuItemAddNewCollectiveBox);
        menuItemAddNewPallete = new JMenuItem("Add new pallete");
        menuAddItems.add(menuItemAddNewPallete);

        menuItemAddNewTests = new JMenuItem("Add new test");
        menuAddItems.add(menuItemAddNewTests);

        bottleChooser = new JComboBox();
        materialsChoosersList.add(bottleChooser);
        capChooser  = new JComboBox();
        materialsChoosersList.add(capChooser);
        labelChooser  = new JComboBox();
        materialsChoosersList.add(labelChooser);
        measurerChooser  = new JComboBox();
        materialsChoosersList.add(measurerChooser);
        unitBoxChooser  = new JComboBox();
        materialsChoosersList.add(unitBoxChooser);
        leafletChooser  = new JComboBox();
        materialsChoosersList.add(leafletChooser);
        collectiveBoxChooser  = new JComboBox();
        materialsChoosersList.add(collectiveBoxChooser);
        palleteChooser = new JComboBox();
        materialsChoosersList.add(palleteChooser);

        cogsTotalCostsTextField = new JTextField();
        cogsTotalCostsTextField.setEditable(false);
        cogsTotalCostsTextField.setBackground(Color.WHITE);

        cogsDate = new JLabel("Example Cogs date");
        productNameLabel = new JLabel("Example product name");
        clientNameLabel = new JLabel("Example client name");
        productCapacityLabel = new JLabel("Example capacity of the product");
        dateOfTheFormulationLabel = new JLabel("Example date");

        MFCostChooser = new JComboBox();
        productionChoosersList.add(MFCostChooser);
        OHChooser = new JComboBox();
        productionChoosersList.add(OHChooser);
        TestsChooser = new JComboBox();
        productionChoosersList.add(TestsChooser);

//Making main content panel
        mainJPanelContainer = new JPanel();
        mainJPanelContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

//Making middle content panel
        middleInformationJPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();

        cogsTablePane = new JPanel();

//        Making editorsList with JComboBoxesChoosers for cogsMaterialsTable
        materialsEditorsList.add(new DefaultCellEditor(bottleChooser));
        materialsEditorsList.add(new DefaultCellEditor(capChooser));
        materialsEditorsList.add(new DefaultCellEditor(labelChooser));
        materialsEditorsList.add(new DefaultCellEditor(measurerChooser));
        materialsEditorsList.add(new DefaultCellEditor(unitBoxChooser));
        materialsEditorsList.add(new DefaultCellEditor(leafletChooser));
        materialsEditorsList.add(new DefaultCellEditor(collectiveBoxChooser));
        materialsEditorsList.add(new DefaultCellEditor(palleteChooser));

        //Make new table with materials
        cogsMaterialsData = new Object[8][9];
        String[] cogsTableColumnNames1 = {"No.","Item", "Item 2",
                "QTY","m.u.", "Purchase price", "Currency", "PLN", "PLN * QTY"};
//        Creating table, adding JComboBoxChoosers to each row from materialsEditorList
        cogsMaterialsTable = new JTable(cogsMaterialsData, cogsTableColumnNames1){
            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column)
            {
                int modelColumn = convertColumnIndexToModel(column);

                if (modelColumn == 1 && row < 8)
                    return  materialsEditorsList.get(row);
                else
                    return super.getCellEditor(row, column);
            }
        };
        cogsMaterialsTable.setPreferredScrollableViewportSize(cogsMaterialsTable.getPreferredSize());
        cogsMaterialsTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        cogsMaterialsTableScrollPane = new JScrollPane(cogsMaterialsTable);
        cogsMaterialsSubtotalTextField = new JTextField();
        cogsMaterialsSubtotalTextField.setEditable(false);
        cogsMaterialsSubtotalTextField.setBackground(Color.WHITE);

        //        Make new table with COGS
        cogsRawData = new Object[formulationSize][9];
        String[] cogsTableColumnNames2 = {"No.","Item", "Item 2",
                "QTY","m.u.", "Purchase price", "Currency", "PLN", "PLN * QTY"};
        cogsRawTable = new JTable(cogsRawData, cogsTableColumnNames2);
        cogsRawTable.setPreferredScrollableViewportSize(cogsRawTable.getPreferredSize());
        cogsRawTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        cogsRawTable.getModel().addTableModelListener(this);


        cogsRawTableScrollPane = new JScrollPane(cogsRawTable);
        cogsRawSubtotalTextField = new JTextField();
        cogsRawSubtotalTextField.setEditable(false);
        cogsRawSubtotalTextField.setBackground(Color.WHITE);

        productionEditorsList.add(new DefaultCellEditor(MFCostChooser));
        productionEditorsList.add(new DefaultCellEditor(OHChooser));
        productionEditorsList.add(new DefaultCellEditor(TestsChooser));

        //        Make new table with production
        cogsProductionData = new Object[3][9];
        String[] cogsTableColumnNames3 = {"No.","Item", "Item 2",
                "QTY","m.u.", "Purchase price", "Currency", "PLN", "PLN * QTY"};
        //        Creating table, adding JComboBoxChoosers to each row from materialsEditorList
        cogsProductionTable = new JTable(cogsProductionData, cogsTableColumnNames3){
            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column)
            {
                int productionModelColumn = convertColumnIndexToModel(column);

                if (productionModelColumn == 5 && row < 3)
                    return  productionEditorsList.get(row);
                else
                    return super.getCellEditor(row, column);
            }
        };
//        cogsProductionTable.ed
        cogsProductionTable.setPreferredScrollableViewportSize(cogsProductionTable.getPreferredSize());
        cogsProductionTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        cogsProductionScrollPane = new JScrollPane(cogsProductionTable);
        cogsProductionSubtotalTextField = new JTextField();
        cogsProductionSubtotalTextField.setEditable(false);
        cogsProductionSubtotalTextField.setBackground(Color.WHITE);

//Make new table with formulation
        formulationData = new Object[formulationSize][6];
        formulationTablePane = new JPanel();
        String[] formulationTableColumnNames = {"No.","Surowiec", "Numer",
                "mg/5ml","Forma chemiczna", "g/150ml"};
        formulationTable = new JTable(formulationData, formulationTableColumnNames);
        formulationTable.getColumnModel().getColumn(1).setPreferredWidth(240);
        formulationTable.getColumnModel().getColumn(2).setPreferredWidth(160);
        formulationTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        formulationTable.setPreferredScrollableViewportSize(formulationTable.getPreferredSize());
        formulationTableScrollPane = new JScrollPane(formulationTable);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        mainJPanelContainer.add(cogsDate, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        mainJPanelContainer.add(cogsMaterialsTableScrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Subtotal"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsMaterialsSubtotalTextField, c);

        // -

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        mainJPanelContainer.add(cogsRawTableScrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Subtotal"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsRawSubtotalTextField, c);

        // -

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 4;
        mainJPanelContainer.add(cogsProductionScrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Subtotal"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsProductionSubtotalTextField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        mainJPanelContainer.add(Box.createVerticalStrut(5), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Total cogs"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsTotalCostsTextField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 1;
        mainJPanelContainer.add(Box.createVerticalStrut(10), c);

        // -

            c2.fill = GridBagConstraints.HORIZONTAL;
            c2.gridwidth = 4;
            c2.gridy = 0;
            middleInformationJPanel.add(dateOfTheFormulationLabel, c2);

            c2.gridwidth = 4;
            c2.gridy = 1;
            middleInformationJPanel.add(clientNameLabel, c2);

            c2.gridwidth = 4;
            c2.gridy = 2;
            middleInformationJPanel.add(productNameLabel, c2);

            c2.gridwidth = 4;
            c2.gridy = 3;
            middleInformationJPanel.add(productCapacityLabel, c2);


        c.fill = GridBagConstraints.NONE;
//        c.gridwidth = 1;
//        c.gridheight = 4;
        c.gridx = 1;
        c.gridy = 10;
        mainJPanelContainer.add(middleInformationJPanel, c);


        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 11;
        mainJPanelContainer.add(new JButton("Button 2"), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 4;
        mainJPanelContainer.add(formulationTableScrollPane,c);

        loadFormulationButton = new JButton("Wczytaj formulacje");
//        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 13;
        mainJPanelContainer.add(loadFormulationButton, c);


        frame.getContentPane().add(mainJPanelContainer);
    }

    public void repaintTables(){
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public JButton getLoadFormulationButton() {
        return loadFormulationButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Object[][] getFormulationData() {
        return formulationData;
    }

    public Object[][] getCogsRawData() {
        return cogsRawData;
    }

    public JMenuItem getMenuItemChangePricesSource() {
        return menuItemChangePricesSource;
    }

    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    public JLabel getClientNameLabel() {
        return clientNameLabel;
    }

    public JLabel getProductCapacityLabel() {
        return productCapacityLabel;
    }

    public JLabel getDateOfTheFormulationLabel() {
        return dateOfTheFormulationLabel;
    }

    public JTextField getCogsMaterialsSubtotalTextField() {
        return cogsMaterialsSubtotalTextField;
    }

    public JTextField getCogsRawSubtotalTextField() {
        return cogsRawSubtotalTextField;
    }

    public JTextField getCogsProductionSubtotalTextField() {
        return cogsProductionSubtotalTextField;
    }

    public JTable getCogsMaterialsTable() {
        return cogsMaterialsTable;
    }

    public JTable getCogsRawTable() {
        return cogsRawTable;
    }

    public JTable getCogsProductionTable() {
        return cogsProductionTable;
    }

    public JComboBox getBottleChooser() {
        return bottleChooser;
    }

    public JComboBox getCapChooser() {
        return capChooser;
    }

    public JComboBox getLabelChooser() {
        return labelChooser;
    }

    public JComboBox getMeasurerChooser() {
        return measurerChooser;
    }

    public JComboBox getUnitBoxChooser() {
        return unitBoxChooser;
    }

    public JComboBox getLeafletChooser() {
        return leafletChooser;
    }

    public JComboBox getCollectiveBoxChooser() {
        return collectiveBoxChooser;
    }

    public JComboBox getPalleteChooser() {
        return palleteChooser;
    }

    public JLabel getCogsDate() {
        return cogsDate;
    }

    public ArrayList<JComboBox> getMaterialsChoosersList() {
        return materialsChoosersList;
    }

    public Object[][] getCogsMaterialsData() {
        return cogsMaterialsData;
    }

    public JTextField getCogsTotalCostsTextField() {
        return cogsTotalCostsTextField;
    }

    public Object[][] getCogsProductionData() {
        return cogsProductionData;
    }

    public JComboBox getMFCostChooser() {
        return MFCostChooser;
    }

    public JComboBox getOHChooser() {
        return OHChooser;
    }

    public JComboBox getTestsChooser() {
        return TestsChooser;
    }

    public JMenuItem getMenuItemAddNewBottle() {
        return menuItemAddNewBottle;
    }

    public JMenuItem getMenuItemChangeProductionPricesSource() {
        return menuItemChangeProductionPricesSource;
    }

    public ArrayList<JComboBox> getProductionChoosersList() {
        return productionChoosersList;
    }

    public JMenuItem getMenuItemAddNewCap() {
        return menuItemAddNewCap;
    }

    public JMenuItem getMenuItemAddNewLabel() {
        return menuItemAddNewLabel;
    }

    public JMenuItem getMenuItemAddNewMeasurer() {
        return menuItemAddNewMeasurer;
    }

    public JMenuItem getMenuItemAddNewUnitBox() {
        return menuItemAddNewUnitBox;
    }

    public JMenuItem getMenuItemAddNewLeaflet() {
        return menuItemAddNewLeaflet;
    }

    public JMenuItem getMenuItemAddNewCollectiveBox() {
        return menuItemAddNewCollectiveBox;
    }

    public JMenuItem getMenuItemAddNewPallete() {
        return menuItemAddNewPallete;
    }

    //    TODO: ADD ACION LISTENER FOR TABLE
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        System.out.println(data);
    }
}

