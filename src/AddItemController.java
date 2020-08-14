import javax.swing.*;

public class AddItemController {

    private AddItemView addItemView;
    private JFrame cogsView;

    public AddItemController(JFrame cogsView) {
        this.cogsView = cogsView;
        cogsView.setEnabled(false);
    }

    public void initAddItemView(){
        addItemView = new AddItemView();

        addItemView.getConfirmButton().addActionListener(e -> {

            cogsView.setEnabled(true);
        });

        addItemView.getCancelButton().addActionListener(e -> {
            addItemView.getFrame().dispose();
            cogsView.setEnabled(true);
        });
    }

}
