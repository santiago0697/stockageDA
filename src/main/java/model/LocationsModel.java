package model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import utils.Constants;
import utils.Utils;

import java.util.Arrays;
import java.util.List;

public class LocationsModel {

    private static final String BASE_PATH = Constants.API_SERVER_URL + "/locations";

    private Integer location_id;
    private String location_name;
    private String location_rewrite;

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_rewrite() {
        return location_rewrite;
    }

    public void setLocation_rewrite(String location_rewrite) {
        this.location_rewrite = location_rewrite;
    }

    public static List<LocationsModel> getAllLocations() {
        HttpResponse<LocationsModel[]> jsonResponse = null;

        try {
            jsonResponse = Unirest.get(BASE_PATH + "/all").asObject(LocationsModel[].class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            return Arrays.asList(jsonResponse.getBody());
        } else {
            return null;
        }
    }

    public static void createLocation(LocationsModel lm) {
        try {
            Unirest.post(BASE_PATH + "/insert")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(lm)
                    .asJson();
        } catch (UnirestException e) {
            Utils.log("ERROR", "Error a la hora de insertar el proveedor");
        }
    }

    public static void updateLocation(LocationsModel lm, Integer location_id) {
        try {
            Unirest.put(BASE_PATH + "/update/" + location_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(lm)
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de actualizar el proveedor");
        }
    }

    public static void deleteLocation(Integer location_id) {
        try {
            Unirest.delete(BASE_PATH + "/delete/" + location_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de eliminar el proveedor");
        }
    }
}
