import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class StartingView {

        private JFrame frame;
        private JMenuBar menuBar;
        private JMenu menuFile, menuEdit;
        private JMenuItem menuItemNew, menuItemSave, menuItemChangePricesSource, menuItemAddNewBottle, menuItemAddNewLabel;
        private JButton loadFormulationButton;
        private JPanel mainJPanelContainer;

        public StartingView(){
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
            menuEdit = new JMenu("Edytuj");
            menuBar.add(menuEdit);

            menuItemNew = new JMenuItem("New");
            menuFile.add(menuItemNew);
            menuItemSave = new JMenuItem("Save");
            menuFile.add(menuItemSave);
            menuItemChangePricesSource = new JMenuItem("Change prices source");
            menuFile.add(menuItemChangePricesSource);
            menuItemAddNewBottle = new JMenuItem("Add new bottle");
            menuFile.add(menuItemAddNewBottle);
            menuItemAddNewLabel = new JMenuItem("Add new Label");
            menuFile.add(menuItemAddNewLabel);

//Making main content panel
            mainJPanelContainer = new JPanel();
            mainJPanelContainer.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            loadFormulationButton = new JButton("Wczytaj formulacje");
//        c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            mainJPanelContainer.add(loadFormulationButton, c);


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

    }

