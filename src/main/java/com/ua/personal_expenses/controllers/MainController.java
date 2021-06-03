package com.ua.personal_expenses.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ua.personal_expenses.modules.Product;
import com.ua.personal_expenses.services.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/expenses")
    public Product addExpense(@RequestBody Product product) {
        userService.save(product);
        return product;
    }

    @GetMapping("/expenses")
    public Map<LocalDate, List<Product>> getAllExpenses() {
        return userService.findAllGruppingByDate();
    }

    @DeleteMapping("/expenses")
    public List<Product> deleteForDate(@RequestParam String date) {
        LocalDate deleteDate = LocalDate.parse(date);
        return userService.deleteByDate(deleteDate);
    }

    @GetMapping("/total")
    public Map<String, Object> total(@RequestParam String base) throws IOException {
        String url_str = "https://api.exchangerate.host/latest";
//      String url_str = "https://api.exchangerate.host/convert?from=USD&to=EUR";

        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("GET");
        InputStream inputStream = request.getInputStream();

        int read;
        StringBuilder response = new StringBuilder();
        while ((read = inputStream.read()) != -1) {
            response.append((char) read);
        }
        JSONArray jsonArray = new JSONArray("[" + response + "]");
        JSONObject dateFromNet = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++) {
            dateFromNet = (JSONObject) jsonArray.get(i);
        }

        double euro = 0;
        List<Product> products = userService.findAll();
        JSONObject finalDateFromNet = dateFromNet;
        for (Product product : products) {
            try {
                double step = Math.pow(10, 30);
                euro += Math.ceil(product.getAmount() / Double.parseDouble(finalDateFromNet.getJSONObject("rates").get(product.getCurrency()).toString()) * step) / step;
            } catch (JSONException json) {
                System.out.println(json.getMessage());
            }
        }

        double total = 0;
        try {
            total = Math.ceil(euro * Double.parseDouble(finalDateFromNet.getJSONObject("rates").get(base).toString()) * 100) / 100;
        } catch (JSONException json) {
            System.out.println(json.getMessage());
        }
//        JsonParser jp = new JsonParser();
//        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//        JsonObject jsonobj = root.getAsJsonObject();
//        String req_result = jsonobj.get("result").getAsString();
        Map<String, Object> ress = new HashMap<>();
        ress.put("total", total);
        if (total != 0) {
            ress.put("currency", base);
        }
        return ress;
    }
}
