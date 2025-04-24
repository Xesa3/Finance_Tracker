package com.example.finance_tracker.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class CheckDataParser {

    public static class Item { //этот класс пердназначен для типа предмет
        //в нем будет записываться купленное что то
        public String name;
        public double price;

        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public static class CheckResult { //Массив который хранит все продукты
        public List<Item> items;
        public double totalSum;

        public CheckResult(List<Item> items, double totalSum) {
            this.items = items;
            this.totalSum = totalSum;
        }
    }

    public static CheckResult parse(String json) throws Exception { //Ну и сам парсер
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode data = root.get("data");

        if (data == null || data.isNull()) {
            return null;
        }

        JsonNode jsonItems = data.get("json").get("items");
        double totalSum = data.get("json").get("totalSum").asInt() / 100.0;

        List<Item> items = new ArrayList<>(); //Где все будет хранится
        for (JsonNode itemNode : jsonItems) {
            String name = itemNode.get("name").asText();
            double priceInRubles = itemNode.get("price").asInt() / 100.0;
            items.add(new Item(name, priceInRubles));
        }

        return new CheckResult(items, totalSum);
    }

}
