import javax.swing.*;
import java.awt.*;

// Displays add item window
public class AddItemView {

    private JFrame frame;
    private JButton confirmButton, cancelButton;
    private JTextField productNameTextField, purchaseMinPriceTextField, purchaseMaxPriceTextField, productSupplierTextField;
    private JComboBox currencyChooserField;
    private JPanel mainJPanelContainer, inputBoxContainer, buttonBoxContainer;
    private GridLayout inputBoxesLayout, buttonsBoxLayout;
    private String[] currencyOptions = {"PLN", "EUR", "USD"};

    public AddItemView() {
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

        productSupplierTextField = new JTextField();
        productSupplierTextField.setPreferredSize(new Dimension(150,20));

        currencyChooserField = new JComboBox(currencyOptions);
        currencyChooserField.setPreferredSize(new Dimension(150,20));
        currencyChooserField.setBackground(Color.WHITE);

        inputBoxesLayout = new GridLayout(2,5);
        inputBoxContainer = new JPanel();
        inputBoxContainer.setLayout(inputBoxesLayout);

        buttonsBoxLayout = new GridLayout(1,2);
        buttonBoxContainer = new JPanel();
        buttonBoxContainer.setLayout(buttonsBoxLayout);

        inputBoxContainer.add(new JLabel("Product name"));
        inputBoxContainer.add(new JLabel("Product min price"));
        inputBoxContainer.add(new JLabel("Product max price"));
        inputBoxContainer.add(new JLabel("Product currency"));
        inputBoxContainer.add(new JLabel("Product supplier"));

        inputBoxContainer.add(productNameTextField);
        inputBoxContainer.add(purchaseMinPriceTextField);
        inputBoxContainer.add(purchaseMaxPriceTextField);
        inputBoxContainer.add(currencyChooserField);
        inputBoxContainer.add(productSupplierTextField);

//Making main content panel
        mainJPanelContainer = new JPanel();
        mainJPanelContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        mainJPanelContainer.add(inputBoxContainer, c);

        buttonBoxContainer.add(confirmButton = new JButton("Dodaj"));
        buttonBoxContainer.add(cancelButton = new JButton("Anuluj"));

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        mainJPanelContainer.add(buttonBoxContainer, c);


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

    public String[] getCurrencyOptions() {
        return currencyOptions;
    }

    public JTextField getProductSupplierTextField() {
        return productSupplierTextField;
    }
}
