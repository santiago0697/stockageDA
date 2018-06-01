import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.providers;
import utils.Constants;

import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {

    public Main() {
        frameSetUp();
    }

    private void frameSetUp() {
        setTitle("Stockage Desktop Application");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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

        /*HttpResponse<providers> jsonResponse = null;

        try {
            jsonResponse = Unirest.get(Constants.API_SERVER_URL + "/providers/getById/1").asObject(providers.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            providers p = jsonResponse.getBody();
            p.getCustomInfo();
        }
         */
    }

}
