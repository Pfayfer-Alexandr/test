import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {


    private static final String API_KEY = "e31f6ce0-8fa6-4c71-a2ab-4b8bb06dcd2a";
    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/forecast";

    public static void main(String[] args) throws IOException, InterruptedException {
        double lat = 55.75;
        double lon = 37.62;
        int limit = 7;

        String weatherData = getWeatherData(lat, lon, limit);

        if (weatherData != null) {
            System.out.println("Полные данные о погоде:");
            System.out.println(weatherData);

            Gson gson = new Gson();
            JsonObject jsonObject = JsonParser.parseString(weatherData).getAsJsonObject();

            String currentTemp = jsonObject.getAsJsonObject("fact").get("temp").getAsString();
            System.out.println("\nТекущая температура: " + currentTemp + " °C");

            double avgTemp = calculateAverageTemperature(jsonObject, limit);
            if (avgTemp != -1) {
                System.out.println("\nСредняя температура за " + limit + " дней: " + String.format("%.1f", avgTemp) + " °C");
            } else {
                System.out.println("\nНевозможно вычислить среднюю температуру.");
            }
        }
    }

    private static String getWeatherData(double lat, double lon, int limit) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?lat=" + lat + "&lon=" + lon + (limit > 0 ? "&limit=" + limit : "")))
                .header("X-Yandex-API-Key", API_KEY)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            System.err.println("Ошибка при запросе к API: Код ответа " + response.statusCode());
            return null;
        }
    }

    private static double calculateAverageTemperature(JsonObject jsonObject, int limit) {
        JsonArray forecasts = jsonObject.getAsJsonArray("forecasts");
        if (forecasts == null || forecasts.size() == 0) {
            System.err.println("Нет данных о прогнозе!");
            return -1;
        }

        List<Double> temperatures = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, forecasts.size()); i++) {
            JsonElement forecast = forecasts.get(i);
            if (forecast.isJsonObject() &&
                    forecast.getAsJsonObject().has("parts") &&
                    forecast.getAsJsonObject().getAsJsonObject("parts").has("day") &&
                    forecast.getAsJsonObject().getAsJsonObject("parts").getAsJsonObject("day").has("temp_avg")) {
                try {
                    double temp = forecast.getAsJsonObject().getAsJsonObject("parts").getAsJsonObject("day").get("temp_avg").getAsDouble();
                    temperatures.add(temp);
                } catch (NumberFormatException | IllegalStateException e) {
                    System.err.println("Ошибка при получении температуры для прогноза " + i + ": " + e.getMessage());
                }
            } else {
                System.err.println("Отсутствует поле 'parts.day.temp_avg' в прогнозе " + i);
            }
        }

        if (temperatures.isEmpty()) {
            System.err.println("Не удалось получить ни одной температуры!");
            return -1;
        }
        return temperatures.stream().mapToDouble(Double::doubleValue).average().orElse(-1);
    }
}