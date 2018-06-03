package controller;

import model.ProvidersModel;
import view.ProvidersView;

import javax.swing.*;

public class ProvidersController {

    private ProvidersModel model;
    private ProvidersView view;

    public ProvidersController() {
        model = new ProvidersModel();
        view = new ProvidersView(this);
    }

    public JPanel getView() {
        return view.getView();
    }
}
