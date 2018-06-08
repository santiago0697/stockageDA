package view;

import model.LocationsModel;
import tables.LocationsTableModel;

import javax.swing.*;

public class LocationsView {
    private JPanel mainLocationsPane;
    private JPanel controlArea;
    private JButton createLocation;
    private JButton deleteLocation;
    private JPanel tablePane;
    private JTable contentTable;

    public JButton getCreateLocation() {
        return createLocation;
    }

    public JButton getDeleteLocation() {
        return deleteLocation;
    }

    public JPanel getTablePane() {
        return tablePane;
    }

    public JPanel getMainLocationsPane() {
        return mainLocationsPane;
    }

    public JPanel getControlArea() {
        return controlArea;
    }

    public JTable getContentTable() {
        return contentTable;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        contentTable = new JTable(new LocationsTableModel(LocationsModel.getAllLocations(), this));

        createLocation = new JButton("Create");
        createLocation.setName("createLocation");

        deleteLocation = new JButton("Delete");
        deleteLocation.setName("deleteLocation");
    }

    public void updateContentTable() {
        this.contentTable.setModel(new LocationsTableModel(LocationsModel.getAllLocations(), this));
    }

    public JPanel getView() {
        return this.mainLocationsPane;
    }
}
