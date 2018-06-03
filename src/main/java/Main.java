import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import controller.ProvidersController;
import utils.Constants;

import javax.swing.*;
import java.io.IOException;

import static utils.Constants.PROVIDERS_ICON_RESOURCE;
import static utils.Utils.createImageIcon;

public class Main extends JFrame {

    private JTabbedPane mainTabbedPane;
    private ProvidersController providersController;

    public Main() {
        initComponents();
        frameSetUp();
    }

    private void frameSetUp() {
        setTitle("Stockage Desktop Application");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        mainTabbedPane = new JTabbedPane();

        providersController = new ProvidersController();
        mainTabbedPane.addTab("Providers", null, providersController.getView(), "Providers CRUD");

        this.add(mainTabbedPane);
    }

    private static void initSetUp() {
        /*
            Unirest serialize objects setup
         */
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper =
                    new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String s, Class<T> aClass) {
                try {
                    return jacksonObjectMapper.readValue(s, aClass);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object o) {
                try {
                    return jacksonObjectMapper.writeValueAsString(o);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public static void main(String[] args) {
        System.out.println("[INFO] Inicio de la aplicacion");
        initSetUp();

        new Main().setVisible(true);

        /*HttpResponse<ProvidersModel> jsonResponse = null;

        try {
            jsonResponse = Unirest.get(Constants.API_SERVER_URL + "/ProvidersModel/getById/1").asObject(ProvidersModel.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            ProvidersModel p = jsonResponse.getBody();
            p.getCustomInfo();
        }
         */
    }

}
