package view;

import controller.ProvidersController;

import javax.swing.*;

public class ProvidersView {
    private ProvidersController controller;
    private JPanel mainProvidersPane;
    private JPanel controlArea;
    private JButton bCreate;
    private JButton bUpdate;
    private JButton bDelete;
    private JTable contentTable;

    public ProvidersView(ProvidersController controller){
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
}
