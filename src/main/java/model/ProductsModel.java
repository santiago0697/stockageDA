package model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import utils.Constants;
import utils.Utils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProductsModel {

    private static final String BASE_PATH = Constants.API_SERVER_URL + "/products";

    private Integer products_id;
    private String products_name;
    private String products_description;
    private Date products_date_added;
    private Integer products_status;
    private String products_image;
    private Integer products_qty;
    private ProvidersModel provider;
    private LocationsModel location;

    public Integer getProducts_id() {
        return products_id;
    }

    public void setProducts_id(Integer products_id) {
        this.products_id = products_id;
    }

    public String getProducts_name() {
        return products_name;
    }

    public void setProducts_name(String products_name) {
        this.products_name = products_name;
    }

    public String getProducts_description() {
        return products_description;
    }

    public void setProducts_description(String products_description) {
        this.products_description = products_description;
    }

    public Integer getProducts_status() {
        return products_status;
    }

    public void setProducts_status(Integer products_status) {
        this.products_status = products_status;
    }

    public String getProducts_image() {
        return products_image;
    }

    public void setProducts_image(String products_image) {
        this.products_image = products_image;
    }

    public Integer getProducts_qty() {
        return products_qty;
    }

    public void setProducts_qty(Integer products_qty) {
        this.products_qty = products_qty;
    }

    public ProvidersModel getProvider() {
        return provider;
    }

    public void setProvider(ProvidersModel provider) {
        this.provider = provider;
    }

    public LocationsModel getLocation() {
        return location;
    }

    public void setLocation(LocationsModel location) {
        this.location = location;
    }

    public Date getProducts_date_added() {
        return products_date_added;
    }

    public void setProducts_date_added(Date products_date_added) {
        this.products_date_added = products_date_added;
    }

    public static List<ProductsModel> getAllProducts() {
        HttpResponse<ProductsModel[]> jsonResponse = null;

        try {
            jsonResponse = Unirest.get(BASE_PATH + "/all").asObject(ProductsModel[].class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse != null) {
            return Arrays.asList(jsonResponse.getBody());
        } else {
            return null;
        }
    }

    public static void createProduct(ProductsModel pm, Integer provider_id, Integer location_id) {
        try {
            HttpResponse<JsonNode> postResponse = Unirest.post(BASE_PATH + "/insert/provider/" + provider_id + "/location/" + location_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(pm)
                    .asJson();
        } catch (UnirestException e) {
            Utils.log("ERROR", "Error a la hora de insertar el producto");
        }
    }

    public static void updateProduct(ProductsModel pm, Integer products_id) {
        try {
            Unirest.put(BASE_PATH + "/update/" + products_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(pm)
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de actualizar el producto");
        }
    }

    public static void deleteProduct(Integer products_id) {
        try {
            Unirest.delete(BASE_PATH + "/delete/" + products_id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
        } catch (Exception e) {
            Utils.log("ERROR", "Error a la hora de eliminar el product");
        }
    }
}
