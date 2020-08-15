import javax.swing.*;
import java.awt.*;

// Displays add item window
public class AddItemView {

    private JFrame frame;
    private JButton confirmButton, cancelButton;
    private JTextField productNameTextField, purchaseMinPriceTextField, purchaseMaxPriceTextField;
    private JComboBox currencyChooserField;
    private JPanel mainJPanelContainer, inputBoxesContainer;
    private GridLayout inputBoxesLayout;

    public AddItemView() {
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

        purchaseMinPriceTextField = new JTextField();
        purchaseMinPriceTextField.setPreferredSize(new Dimension(150,20));

        purchaseMaxPriceTextField = new JTextField();
        purchaseMaxPriceTextField.setPreferredSize(new Dimension(150,20));

        String[] currencyOptions = {"PLN", "EUR", "USD"};
        currencyChooserField = new JComboBox(currencyOptions);
        currencyChooserField.setPreferredSize(new Dimension(150,20));

        inputBoxesLayout = new GridLayout(2,4);
        inputBoxesContainer = new JPanel();
        inputBoxesContainer.setLayout(inputBoxesLayout);

        inputBoxesContainer.add(new JLabel("Product name"));
        inputBoxesContainer.add(new JLabel("Product price"));
        inputBoxesContainer.add(new JLabel("Product currency"));

        inputBoxesContainer.add(productNameTextField);
        inputBoxesContainer.add(purchaseMinPriceTextField);
        inputBoxesContainer.add(purchaseMaxPriceTextField);
        inputBoxesContainer.add(currencyChooserField);

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

        cancelButton = new JButton("Anuluj");
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        mainJPanelContainer.add(cancelButton, c);


        frame.getContentPane().add(mainJPanelContainer);
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getProductNameTextField() {
        return productNameTextField;
    }

    public JTextField getPurchaseMinPriceTextField() {
        return purchaseMinPriceTextField;
    }

    public JTextField getPurchaseMaxPriceTextField() {
        return purchaseMaxPriceTextField;
    }

    public JComboBox getCurrencyChooserField() {
        return currencyChooserField;
    }
}
