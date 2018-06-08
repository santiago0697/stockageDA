package model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import utils.Constants;
import utils.Utils;

import java.util.Arrays;
import java.util.List;

public class ProvidersModel {

    private static final String BASE_PATH = Constants.API_SERVER_URL + "/providers";

    private Integer provider_id;
    private String provider_name;
    private String provider_rewrite;
    private String provider_email;
    private String provider_phone;
    private String provider_address;

    public Integer getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(Integer provider_id) {
        this.provider_id = provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_rewrite() {
        return provider_rewrite;
    }

    public void setProvider_rewrite(String provider_rewrite) {
        this.provider_rewrite = provider_rewrite;
    }

    public String getProvider_email() {
        return provider_email;
    }

    public void setProvider_email(String provider_email) {
        this.provider_email = provider_email;
    }

    public String getProvider_phone() {
        return provider_phone;
    }

    public void setProvider_phone(String provider_phone) {
        this.provider_phone = provider_phone;
    }

    public String getProvider_address() {
        return provider_address;
    }

    public void setProvider_address(String provider_address) {
        this.provider_address = provider_address;
    }

    public void getCustomInfo() {
        System.out.println(String.format("provider_id: %d\nprovider_name: %s\nprovider_rewrite: %s\nprovider_email: %s\nprovider_phone: %s\nprovider_address: %s",
                provider_id, provider_name, provider_rewrite, provider_email, provider_phone, provider_address));
    }

    public static List<ProvidersModel> getAllProviders() {
        HttpResponse<ProvidersModel[]> jsonResponse = null;

        try {
            jsonResponse = Unirest.get(BASE_PATH + "/all").asObject(ProvidersModel[].class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            return Arrays.asList(jsonResponse.getBody());
        } else {
            return null;
        }
    }

    public static void createProvider(ProvidersModel pm) {
        try {
            HttpResponse<JsonNode> postResponse = Unirest.post(BASE_PATH + "/insert")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(pm)
                    .asJson();
        } catch (UnirestException e) {
            Utils.log("ERROR", "Error a la hora de insertar el proveedor");
        }
    }

    public static void updateProvider(ProvidersModel pm, Integer provider_id) {
        try {
            Unirest.put(BASE_PATH + "/update/" + provider_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(pm)
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de actualizar el proveedor");
        }
    }

    public static void deleteProvider(Integer provider_id) {
        try {
            Unirest.delete(BASE_PATH + "/delete/" + provider_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de eliminar el proveedor");
        }
    }
}
