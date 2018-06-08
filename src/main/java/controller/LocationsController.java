package controller;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import model.LocationsModel;
import tables.LocationsTableModel;
import utils.Utils;
import view.LocationsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationsController {

    private LocationsModel model;
    private LocationsView view;
    private ButtonsActionListener buttonsActionListener;

    public LocationsController() {
        model = new LocationsModel();
        view = new LocationsView();
        buttonsActionListener = new ButtonsActionListener(this);

        view.getCreateLocation().addActionListener(buttonsActionListener);
        view.getDeleteLocation().addActionListener(buttonsActionListener);
    }

    public JPanel getView() {
        return view.getView();
    }

    private class ButtonsActionListener implements ActionListener {
        private LocationsController controller;

        public ButtonsActionListener(LocationsController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            switch (buttonPressed.getName()) {
                case "createLocation":
                    Utils.log("INFO", "Create action");
                    ((LocationsTableModel) controller.view.getContentTable().getModel()).addRow(new LocationsModel());
                    break;
                case "deleteLocation":
                    Integer selectedRow = view.getContentTable().getSelectedRow();
                    Integer location_id = Integer.parseInt(view.getContentTable().getModel().getValueAt(selectedRow, 0).toString());
                    LocationsModel.deleteLocation(location_id);
                    view.updateContentTable();
                    Utils.log("INFO", "Delete action");
                    break;
            }
        }

    }
}
