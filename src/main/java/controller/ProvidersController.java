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
    private ArrayList<Integer> providersEdidtedRows;

    public ProvidersController() {
        providersEdidtedRows = new ArrayList<>();
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

    public void saveData() {
        for (Integer i : providersEdidtedRows) {
            ProvidersModel pm = new ProvidersModel();
            ProvidersTableModel tableModel = (ProvidersTableModel) view.getContentTable().getModel();

            pm.setProvider_name((String) tableModel.getValueAt(i, view.getContentTable().getColumn("providers_name").getModelIndex()));
            pm.setProvider_rewrite((String) tableModel.getValueAt(i, view.getContentTable().getColumn("providers_rewrite").getModelIndex()));
            pm.setProvider_email((String) tableModel.getValueAt(i, view.getContentTable().getColumn("providers_email").getModelIndex()));
            pm.setProvider_phone((String) tableModel.getValueAt(i, view.getContentTable().getColumn("providers_phone").getModelIndex()));
            pm.setProvider_address((String) tableModel.getValueAt(i, view.getContentTable().getColumn("providers_address").getModelIndex()));

            ProvidersModel.createProvider(pm);
        }
    }

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
                    providersEdidtedRows.add(controller.view.getContentTable().getModel().getRowCount());
                    break;
                case "save":
                    Utils.log("INFO", "Save action");
                    saveData();
                    break;
                case "delete":
                    Utils.log("INFO", "Delete action");
                    break;
            }
        }
    }
}
