package controller;

import model.ProvidersModel;
import tables.ProvidersTableModel;
import utils.Utils;
import view.ProvidersView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProvidersController {

    private ProvidersModel model;
    private ProvidersView view;
    private ButtonsActionListener buttonsActionListener;

    public ProvidersController() {
        model = new ProvidersModel();
        view = new ProvidersView(this);
        buttonsActionListener = new ButtonsActionListener(this);

        view.getbCreate().addActionListener(buttonsActionListener);
        view.getbUpdate().addActionListener(buttonsActionListener);
        view.getbDelete().addActionListener(buttonsActionListener);
    }

    public JPanel getView() {
        return view.getView();
    }

        /*bCreate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

        }
    });*/

    private class ButtonsActionListener implements ActionListener {

        private ProvidersController controller;

        public ButtonsActionListener(ProvidersController controller) {
            this.controller = controller;
        }

        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            switch (buttonPressed.getName().toString()) {
                case "create":
                    Utils.log("INFO", "Create action");
                    ((ProvidersTableModel) controller.view.getContentTable().getModel()).addRow(new ProvidersModel());
                    break;
                case "save":
                    Utils.log("INFO", "Save action");
                    break;
                case "delete":
                    Utils.log("INFO", "Delete action");
                    break;
            }
        }
    }
}
