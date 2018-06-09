package controller;

import model.DeliveryStatusModel;
import model.LocationsModel;
import tables.DeliveryStatusTableModel;
import utils.Utils;
import view.DeliveryStatusView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeliveryStatusController {

    private DeliveryStatusView view;
    private DeliveryStatusModel model;
    private ButtonsActionListener buttonsActionListener;

    public DeliveryStatusController() {
        view = new DeliveryStatusView();
        model = new DeliveryStatusModel();
        buttonsActionListener = new ButtonsActionListener(this);

        view.getCreateDeliveryStatus().addActionListener(buttonsActionListener);
        view.getDeleteDeliveryStatus().addActionListener(buttonsActionListener);
    }

    public JPanel getView() {
        return view.getView();
    }

    private class ButtonsActionListener implements ActionListener {
        private DeliveryStatusController controller;

        public ButtonsActionListener(DeliveryStatusController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            switch (buttonPressed.getName()) {
                case "createDeliveryStatus":
                    Utils.log("INFO", "Create action");
                    ((DeliveryStatusTableModel) controller.view.getContentTable().getModel()).addRow(new DeliveryStatusModel());
                    break;
                case "deleteDeliveryStatus":
                    Integer selectedRow = view.getContentTable().getSelectedRow();
                    Integer delivery_status_id = Integer.parseInt(view.getContentTable().getModel().getValueAt(selectedRow, 0).toString());
                    DeliveryStatusModel.deleteDeliveryStatus(delivery_status_id);
                    view.updateContentTable();
                    Utils.log("INFO", "Delete action");
                    break;
            }
        }

    }
}
