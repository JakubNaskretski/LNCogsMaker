import javax.swing.*;
import java.awt.*;

public class addItemView {

    private JFrame frame;
    private JButton confirmButton;
    private JTextField productNameTextField, purchasePriceTextField, currencyTextField;
    private JPanel mainJPanelContainer, inputBoxesContainer;
    private GridLayout inputBoxesLayout;

    public addItemView() {
        frame = new JFrame();
        frame.getContentPane();
        addComponentsToPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Auto cogs counter");
        frame.setLocationRelativeTo(null);
        frame.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2);

        // Display frame
            frame.pack();
//        frame.setSize(new Dimension(200, 150));
        frame.setVisible(true);
    }

    protected void addComponentsToPane() {


        productNameTextField = new JTextField();
        productNameTextField.setPreferredSize(new Dimension(150,20));

        purchasePriceTextField = new JTextField();
        purchasePriceTextField.setPreferredSize(new Dimension(150,20));

        currencyTextField = new JTextField();
        currencyTextField.setPreferredSize(new Dimension(150,20));

        inputBoxesLayout = new GridLayout(2,3);
        inputBoxesContainer = new JPanel();
        inputBoxesContainer.setLayout(inputBoxesLayout);

        inputBoxesContainer.add(new JLabel("Product name"));
        inputBoxesContainer.add(new JLabel("Product price"));
        inputBoxesContainer.add(new JLabel("Product currency"));

        inputBoxesContainer.add(productNameTextField);
        inputBoxesContainer.add(purchasePriceTextField);
        inputBoxesContainer.add(currencyTextField);

//Making main content panel
        mainJPanelContainer = new JPanel();
        mainJPanelContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        mainJPanelContainer.add(inputBoxesContainer, c);

        confirmButton = new JButton("Dodaj");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        mainJPanelContainer.add(confirmButton, c);


        frame.getContentPane().add(mainJPanelContainer);
    }

    public void createFormulationDataTable() {
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

}
