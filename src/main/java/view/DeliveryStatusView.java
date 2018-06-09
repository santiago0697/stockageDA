package view;

import model.DeliveryStatusModel;
import tables.DeliveryStatusTableModel;

import javax.swing.*;

public class DeliveryStatusView {
    private JPanel mainDeliveryStatusPane;
    private JPanel controlArea;
    private JButton createDeliveryStatus;
    private JButton deleteDeliveryStatus;
    private JPanel tablePane;
    private JTable contentTable;

    public JPanel getMainDeliveryStatusPane() {
        return mainDeliveryStatusPane;
    }

    public void setMainDeliveryStatusPane(JPanel mainDeliveryStatusPane) {
        this.mainDeliveryStatusPane = mainDeliveryStatusPane;
    }

    public JPanel getControlArea() {
        return controlArea;
    }

    public void setControlArea(JPanel controlArea) {
        this.controlArea = controlArea;
    }

    public JButton getCreateDeliveryStatus() {
        return createDeliveryStatus;
    }

    public void setCreateDeliveryStatus(JButton createDeliveryStatus) {
        this.createDeliveryStatus = createDeliveryStatus;
    }

    public JButton getDeleteDeliveryStatus() {
        return deleteDeliveryStatus;
    }

    public void setDeleteDeliveryStatus(JButton deleteDeliveryStatus) {
        this.deleteDeliveryStatus = deleteDeliveryStatus;
    }

    public JPanel getTablePane() {
        return tablePane;
    }

    public void setTablePane(JPanel tablePane) {
        this.tablePane = tablePane;
    }

    public JTable getContentTable() {
        return contentTable;
    }

    public void setContentTable(JTable contentTable) {
        this.contentTable = contentTable;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        contentTable = new JTable(new DeliveryStatusTableModel(DeliveryStatusModel.getAllDeliveryStatus(), this));

        createDeliveryStatus = new JButton("Create");
        createDeliveryStatus.setName("createDeliveryStatus");

        deleteDeliveryStatus = new JButton("Delete");
        deleteDeliveryStatus.setName("deleteDeliveryStatus");
    }

    public void updateContentTable() {
        this.contentTable.setModel(new DeliveryStatusTableModel(DeliveryStatusModel.getAllDeliveryStatus(), this));
    }

    public JPanel getView() {
        return this.mainDeliveryStatusPane;
    }
}
