package model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import utils.Constants;
import utils.Utils;

import java.util.Arrays;
import java.util.List;

public class DeliveryStatusModel {

    private static final String BASE_PATH = Constants.API_SERVER_URL + "/delivery_status";

    private Integer delivery_status_id;
    private String delivery_rewrite;
    private String delivery_description;

    public Integer getDelivery_status_id() {
        return delivery_status_id;
    }

    public void setDelivery_status_id(Integer delivery_status_id) {
        this.delivery_status_id = delivery_status_id;
    }

    public String getDelivery_rewrite() {
        return delivery_rewrite;
    }

    public void setDelivery_rewrite(String delivery_rewrite) {
        this.delivery_rewrite = delivery_rewrite;
    }

    public String getDelivery_description() {
        return delivery_description;
    }

    public void setDelivery_description(String delivery_description) {
        this.delivery_description = delivery_description;
    }

    public static List<DeliveryStatusModel> getAllDeliveryStatus() {
        HttpResponse<DeliveryStatusModel[]> jsonResponse = null;

        try {
            jsonResponse = Unirest.get(BASE_PATH + "/all").asObject(DeliveryStatusModel[].class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            return Arrays.asList(jsonResponse.getBody());
        } else {
            return null;
        }
    }

    public static void createDeliveryStatus(DeliveryStatusModel dsm) {
        try {
            Unirest.post(BASE_PATH + "/insert")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(dsm)
                    .asJson();
        } catch (UnirestException e) {
            Utils.log("ERROR", "Error a la hora de insertar el estado");
        }
    }

    public static void updateDeliveryStatus(DeliveryStatusModel dsm, Integer delivery_status_id) {
        try {
            Unirest.put(BASE_PATH + "/update/" + delivery_status_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(dsm)
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de actualizar el estado");
        }
    }

    public static void deleteDeliveryStatus(Integer delivery_status_id) {
        try {
            Unirest.delete(BASE_PATH + "/delete/" + delivery_status_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de eliminar el estado");
        }
    }
}
