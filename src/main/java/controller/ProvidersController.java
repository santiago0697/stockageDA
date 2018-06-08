package controller;

import model.ProvidersModel;
import tables.ProvidersTableModel;
import utils.Utils;
import view.ProvidersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProvidersController {

    private ProvidersModel model;
    private ProvidersView view;
    private ButtonsActionListener buttonsActionListener;

    public ProvidersController() {
        model = new ProvidersModel();
        view = new ProvidersView(this);
        buttonsActionListener = new ButtonsActionListener(this);

        view.getbCreate().addActionListener(buttonsActionListener);
        view.getbDelete().addActionListener(buttonsActionListener);
    }

    public JPanel getView() {
        return view.getView();
    }

    private class ButtonsActionListener implements ActionListener {

        private ProvidersController controller;

        public ButtonsActionListener(ProvidersController controller) {
            this.controller = controller;
        }

        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            switch (buttonPressed.getName()) {
                case "create":
                    Utils.log("INFO", "Create action");
                    ((ProvidersTableModel) controller.view.getContentTable().getModel()).addRow(new ProvidersModel());
                    break;
                case "delete":
                    Integer selectedRow = view.getContentTable().getSelectedRow();
                    Integer provider_id = Integer.parseInt(view.getContentTable().getModel().getValueAt(selectedRow, 0).toString());
                    ProvidersModel.deleteProvider(provider_id);
                    view.updateContentTable();
                    Utils.log("INFO", "Delete action");
                    break;
            }
        }
    }
}
