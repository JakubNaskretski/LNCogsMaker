package views;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class StartingView {

        private JFrame frame;
        private JMenuBar menuBar;
        private JMenu menuFile, changeCurrencies;
        private JMenuItem menuItemNew, menuItemSave, menuItemChangePricesSource, menuItemChangeProductionPriceSource, menuItemChangeCurrencies;
        private JButton loadCogsButton ,loadFormulationButton;
        private JPanel mainJPanelContainer;

        public StartingView(){

            frame = new JFrame();

            Image icon = new ImageIcon("images\\LNIcon.png").getImage();
            frame.setIconImage(icon);

            frame.getContentPane();
            addComponentsToPane();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setTitle("Auto cogs counter");
            frame.setLocationRelativeTo(null);
            frame.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2),
                    (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2);

            // Display frame
//            frame.pack();
            frame.setSize(new Dimension(200, 150));
            frame.setVisible(true);
        }

        protected void addComponentsToPane() {

//    Menu
            menuBar = new JMenuBar();
            frame.setJMenuBar(menuBar);

            menuFile = new JMenu("Plik");
            menuBar.add(menuFile);
//            changeCurrencies = new JMenu("Currencies");
//            menuBar.add(changeCurrencies);

            menuItemNew = new JMenuItem("New");
//            menuFile.add(menuItemNew);
            menuItemSave = new JMenuItem("Save");
//            menuFile.add(menuItemSave);
            menuItemChangePricesSource = new JMenuItem("Change raw materials prices source");
            menuFile.add(menuItemChangePricesSource);
            menuItemChangeProductionPriceSource = new JMenuItem("Change production price source");
            menuFile.add(menuItemChangeProductionPriceSource);

//            menuItemChangeCurrencies = new JMenuItem("Change currencies rate");
//            changeCurrencies.add(menuItemChangeCurrencies);



//Making main content panel
            mainJPanelContainer = new JPanel();
            mainJPanelContainer.setLayout(new GridLayout(2,1));

            loadCogsButton = new JButton("Load COGs");
            mainJPanelContainer.add(loadCogsButton);

            loadFormulationButton = new JButton("Load formulation");
            mainJPanelContainer.add(loadFormulationButton);


            frame.getContentPane().add(mainJPanelContainer);
        }

        public void createFormulationDataTable(){
            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
        }

        public JButton getLoadFormulationButton() {
            return loadFormulationButton;
        }

        public JFrame getFrame() {
            return frame;
        }

        public JMenuItem getMenuItemChangePricesSource() {
            return menuItemChangePricesSource;
        }

    public JMenuItem getMenuItemChangeProductionPriceSource() {
        return menuItemChangeProductionPriceSource;
    }

    public JMenuItem getMenuItemChangeCurrencies() {
        return menuItemChangeCurrencies;
    }
}

