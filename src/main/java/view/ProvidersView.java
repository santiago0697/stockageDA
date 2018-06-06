package view;

import controller.ProvidersController;
import model.ProvidersModel;
import tables.ProvidersTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProvidersView {
    private ProvidersController controller;
    private JPanel mainProvidersPane;
    private JPanel controlArea;
    private JButton bCreate;
    private JButton bUpdate;
    private JButton bDelete;
    private JTable contentTable;
    private JPanel tablePanel;

    public ProvidersView(ProvidersController controller) {
        this.controller = controller;
    }

    public JPanel getView() {
        return this.mainProvidersPane;
    }

    public JPanel getControlArea() {
        return controlArea;
    }

    public JButton getbCreate() {
        return bCreate;
    }

    public JButton getbUpdate() {
        return bUpdate;
    }

    public JButton getbDelete() {
        return bDelete;
    }

    public JTable getContentTable() {
        return contentTable;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        contentTable = new JTable(new ProvidersTableModel(ProvidersModel.getAllProviders()));

        bCreate = new JButton("Create");
        bCreate.setName("create");

        bUpdate = new JButton("Update");
        bUpdate.setName("update");

        bDelete = new JButton("Delete");
        bDelete.setName("delete");

    }
}
