package com.example.finance_tracker.data;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ContentType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.finance_tracker.data.CheckDataParser;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CheckFetcher {
    private static final String API_KEY = "32378.mLSkJvfXBoH3ijkXD";

    public static String fetchData(String qrData) throws Exception {
        String url = "https://proverkacheka.com/api/v1/check/get";

        // Кодируем строку qrData для правильной передачи в URL
        String encodedQrData = URLEncoder.encode(qrData, StandardCharsets.UTF_8.toString());
        String postData = "qrraw=" + encodedQrData + "&token=" + API_KEY;

        System.out.println("Запрос: " + postData); //Вид нашего запроса

        // Создаем HTTP клиент
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(url);

            // Добавляем данные в тело запроса
            StringEntity entity = new StringEntity(postData, ContentType.APPLICATION_FORM_URLENCODED);
            request.setEntity(entity);

            // Выполняем запрос
            var response = client.execute(request);
            return EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
