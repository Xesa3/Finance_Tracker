package com.example.finance_tracker.data;

import com.example.finance_tracker.data.CheckDataParser;
import com.example.finance_tracker.data.CheckFetcher;
import java.util.List;
public class CheckRepository {
    public interface OnDataFetchedListener {
        void onDataFetched(List<CheckDataParser.Item> items, double totalSum);
        void onError(String errorMessage);
    }

    public void fetchDataFromQr(String qrData, OnDataFetchedListener listener) {
        new Thread(() -> {
            try {
                // Получаем JSON-строку от сервера
                String jsonResponse = CheckFetcher.fetchData(qrData);

                if (jsonResponse != null) {
                    // Парсим ответ
                    CheckDataParser.CheckResult result = CheckDataParser.parse(jsonResponse);

                    // Передаем данные на UI
                    if (result != null) {
                        listener.onDataFetched(result.items, result.totalSum);
                    } else {
                        listener.onError("Ошибка парсинга данных.");
                    }
                } else {
                    listener.onError("Ошибка при получении данных.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                listener.onError("Произошла ошибка." + e.getMessage());
            }
        }).start();
    }
}
