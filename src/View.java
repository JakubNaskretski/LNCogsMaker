import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.ArrayList;


// This class provides view for the application
public class View {


    private int formulationSize;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menuFile, menuAddItems, changeCurrencies;
    private JMenuItem menuItemNew, menuItemExport, menuItemChangePricesSource, menuItemChangeProductionPricesSource, saveCogsMenuItem, loadCogsMenuItem,
            menuItemAddNewBottle, menuItemAddNewCap, menuItemAddNewLabel, menuItemAddNewMeasurer, menuItemAddNewUnitBox, menuItemAddNewLeaflet, menuItemAddNewCollectiveBox, menuItemAddNewPallete,
            menuItemAddNewTests,
            menuItemChangeCurrencies;
    private JTable formulationTable, cogsMaterialsTable, cogsRawTable, cogsProductionTable;
    private JComboBox bottleChooser, capChooser, labelChooser, measurerChooser, unitBoxChooser, leafletChooser, collectiveBoxChooser, palleteChooser, MFCostChooser, OHChooser, TestsChooser;
    private DefaultTableCellRenderer cellRenderer;
    private JScrollPane mainScrollPane, formulationTableScrollPane, cogsMaterialsTableScrollPane, cogsRawTableScrollPane, cogsProductionScrollPane;
    private JPanel subtotalMaterialsPanel, subtotalRawsPanel, subtotal,ProductionPanel ,formulationTablePane, cogsTablePane;
    private JButton calculateCostsButton, loadFormulationButton;
    private JPanel mainJPanelContainer, middleInformationJPanel, cogsPriceJPanel;
    private Object[][] formulationData, cogsMaterialsData, cogsRawData, cogsProductionData;
    private JLabel cogsDate, productNameLabel, clientNameLabel, productCapacityLabel, dateOfTheFormulationLabel;
    private JTextField cogsMaterialsSubtotalTextField, cogsRawSubtotalTextField, cogsProductionSubtotalTextField, cogsTotalCostsTextField,
            cogsEurTextField, cogsPlnTextField, cogsEurPriceTextField, cogsPlnPriceTextField, cogsEurMarginTextField, cogsPlnMarginTexField, cogsMarginPercantageTextField;
    private GridLayout subtotalMaterialsLayout, subtotalRawsLayout, subtotalProductionLayout, cogsPriceLayout;

    private ArrayList<TableCellEditor> materialsEditorsList = new ArrayList<TableCellEditor>();
    private ArrayList<JComboBox> materialsChoosersList = new ArrayList<>();

    private ArrayList<TableCellEditor> productionEditorsList = new ArrayList<TableCellEditor>();
    private ArrayList<JComboBox> productionChoosersList = new ArrayList<>();

    private Dimension screenSize;

    public View(int formulationSize){
        this.formulationSize = formulationSize;
        frame = new JFrame();
        frame.getContentPane();
//        frame.getContentPane().setMaximumSize(new Dimension(1500, 1200));
//        frame.setResizable(false);
        addComponentsToPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        changeCurrencies = new JMenu("Currency");
        menuBar.add(changeCurrencies);

        menuItemNew = new JMenuItem("New");
//        menuFile.add(menuItemNew);
        menuItemExport = new JMenuItem("Export cogs");
        menuFile.add(menuItemExport);
        menuItemChangePricesSource = new JMenuItem("Change materials price source");
        menuFile.add(menuItemChangePricesSource);
        menuItemChangeProductionPricesSource = new JMenuItem("Change production price source");
        menuFile.add(menuItemChangeProductionPricesSource);
        saveCogsMenuItem = new JMenuItem("Zapisz cogs");
//        menuFile.add(saveCogsMenuItem);
        loadCogsMenuItem = new JMenuItem("Wczytaj cogs");
//        menuFile.add(loadCogsMenuItem);



        menuItemAddNewBottle = new JMenuItem("Dodaj nową butelkę");
        menuAddItems.add(menuItemAddNewBottle);
        menuItemAddNewCap = new JMenuItem("Dodaj nową nakrętkę");
        menuAddItems.add(menuItemAddNewCap);
        menuItemAddNewLabel = new JMenuItem("Dodaj nową etykietę");
        menuAddItems.add(menuItemAddNewLabel);
        menuItemAddNewMeasurer = new JMenuItem("Dodaj nową miarkę");
        menuAddItems.add(menuItemAddNewMeasurer);
        menuItemAddNewUnitBox = new JMenuItem("Dodaj nowy kartonik ");
        menuAddItems.add(menuItemAddNewUnitBox);
        menuItemAddNewLeaflet = new JMenuItem("Dodaj nową ulotkę");
        menuAddItems.add(menuItemAddNewLeaflet);
        menuItemAddNewCollectiveBox = new JMenuItem("Dodaj nowy karton zbiorczy");
        menuAddItems.add(menuItemAddNewCollectiveBox);
        menuItemAddNewPallete = new JMenuItem("Dodaj nową  paletę");
        menuAddItems.add(menuItemAddNewPallete);

        menuItemAddNewTests = new JMenuItem("Add new test");
        menuAddItems.add(menuItemAddNewTests);

        menuItemChangeCurrencies = new JMenuItem("Change currencies rate");
        changeCurrencies.add(menuItemChangeCurrencies);
//        menuItemchangeUsdCurrency = new JMenuItem("Change Usd currency rate");
//        changeCurrencies.add(menuItemchangeUsdCurrency);

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

//        cogsMaterialsTable.setMinimumSize(new Dimension());
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

        cogsPriceLayout = new GridLayout(4, 3, 5, 5);
        cogsPriceJPanel = new JPanel();
//        cogsPriceJPanel.setPreferredSize(new Dimension(200, 150));
        cogsPriceJPanel.setLayout(cogsPriceLayout);

        cogsEurTextField = new JTextField();
        cogsPlnTextField = new JTextField();
        cogsEurPriceTextField = new JTextField();
        cogsPlnPriceTextField = new JTextField();
        cogsEurMarginTextField = new JTextField();
        cogsPlnMarginTexField = new JTextField();
        cogsMarginPercantageTextField = new JTextField();

        cogsPriceJPanel.add(new JLabel("Cogs", SwingConstants.RIGHT));
        cogsPriceJPanel.add(cogsEurTextField);
        cogsPriceJPanel.add(cogsPlnTextField);
        cogsPriceJPanel.add(new JLabel("Price", SwingConstants.RIGHT));
        cogsPriceJPanel.add(cogsEurPriceTextField);
        cogsPriceJPanel.add(cogsPlnPriceTextField);
        cogsPriceJPanel.add(new JLabel("Margin", SwingConstants.RIGHT));
        cogsPriceJPanel.add(cogsEurMarginTextField);
        cogsPriceJPanel.add(cogsPlnMarginTexField);
        cogsPriceJPanel.add(new JLabel("Margin %", SwingConstants.RIGHT));
        cogsPriceJPanel.add(cogsMarginPercantageTextField);
        calculateCostsButton = new JButton("Calculate costs");
        cogsPriceJPanel.add(calculateCostsButton);




        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.ipady = 0;
        c.ipadx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        mainJPanelContainer.add(cogsMaterialsTableScrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Subtotal "), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsMaterialsSubtotalTextField, c);

        // -

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 4;
        mainJPanelContainer.add(cogsRawTableScrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,0);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Subtotal "), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsRawSubtotalTextField, c);

        // -

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        mainJPanelContainer.add(cogsProductionScrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,0);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Subtotal "), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsProductionSubtotalTextField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        mainJPanelContainer.add(Box.createVerticalStrut(5), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,0);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        mainJPanelContainer.add(new JLabel("Total cogs "), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        mainJPanelContainer.add(cogsTotalCostsTextField, c);

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

        c2.gridwidth = 4;
        c2.gridy = 4;
        middleInformationJPanel.add(cogsDate, c2);

        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 8;
        c.ipady = 20;
        c.ipadx = 20;
        mainJPanelContainer.add(middleInformationJPanel, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,5,10);
        c.gridx = 2;
        c.gridy = 8;
        c.gridwidth = 2;
        mainJPanelContainer.add(cogsPriceJPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 4;
        mainJPanelContainer.add(formulationTableScrollPane,c);

        loadFormulationButton = new JButton("Wczytaj formulacje");
//        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 11;
        mainJPanelContainer.add(loadFormulationButton, c);

        cogsMaterialsTableScrollPane.setMinimumSize(new Dimension(768,151));
        cogsRawTableScrollPane.setMinimumSize(new Dimension(768, 151));
        cogsProductionScrollPane.setMinimumSize(new Dimension(768, 71));
        formulationTableScrollPane.setMinimumSize(new Dimension(768,171));

        cogsMaterialsTableScrollPane = new JScrollPane(mainJPanelContainer);
        cogsMaterialsTableScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        cogsMaterialsTableScrollPane.setMinimumSize(new Dimension(768, 832));

        frame.getContentPane().add(cogsMaterialsTableScrollPane);
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

    public JTextField getCogsEurTextField() {
        return cogsEurTextField;
    }

    public JTextField getCogsPlnTextField() {
        return cogsPlnTextField;
    }

    public JTextField getCogsEurPriceTextField() {
        return cogsEurPriceTextField;
    }

    public JTextField getCogsPlnPriceTextField() {
        return cogsPlnPriceTextField;
    }

    public JTextField getCogsEurMarginTextField() {
        return cogsEurMarginTextField;
    }

    public JTextField getCogsPlnMarginTexField() {
        return cogsPlnMarginTexField;
    }

    public JTextField getCogsMarginPercantageTextField() {
        return cogsMarginPercantageTextField;
    }

    public JButton getCalculateCostsButton() {
        return calculateCostsButton;
    }

    public JPanel getFormulationTablePane() {
        return formulationTablePane;
    }

    public JPanel getCogsTablePane() {
        return cogsTablePane;
    }

    public JPanel getMainJPanelContainer() {
        return mainJPanelContainer;
    }

    public JPanel getMiddleInformationJPanel() {
        return middleInformationJPanel;
    }

    public JPanel getCogsPriceJPanel() {
        return cogsPriceJPanel;
    }

    public JScrollPane getFormulationTableScrollPane() {
        return formulationTableScrollPane;
    }

    public JScrollPane getCogsMaterialsTableScrollPane() {
        return cogsMaterialsTableScrollPane;
    }

    public JScrollPane getCogsRawTableScrollPane() {
        return cogsRawTableScrollPane;
    }

    public JScrollPane getCogsProductionScrollPane() {
        return cogsProductionScrollPane;
    }

    public JMenuItem getMenuItemChangeCurrencies() {
        return menuItemChangeCurrencies;
    }

    public JMenuItem getMenuItemExport() {
        return menuItemExport;
    }

    //    TODO: ADD ACION LISTENER FOR TABLE

}

