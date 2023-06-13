package com.stempien.kantor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloController {
    public Label lblEuroBuy;
    public Label lblEuroSell;
    public Label lblDolarBuy;
    public Label lblDolarSell;
    public Label lblKoronaDunskaSell;
    public Label lblKoronaDunskaBuy;
    public Label lblJenSell;
    public Label lblJenBuy;

    @FXML
    private void initialize() {
        double kursEuro = metoda("http://api.nbp.pl/api/exchangerates/rates/a/eur/?format=json");
        lblEuroSell.setText(""+kursEuro);
        lblEuroBuy.setText(""+(kursEuro+0.2));
        double kursDolar = metoda("http://api.nbp.pl/api/exchangerates/rates/a/usd/?format=json");
        lblDolarSell.setText(""+kursDolar);
        lblDolarBuy.setText(""+(kursDolar+0.2));
        double kursKoronaDunska = metoda("http://api.nbp.pl/api/exchangerates/rates/a/dkk/?format=json");
        lblKoronaDunskaSell.setText(""+kursKoronaDunska);
        lblKoronaDunskaBuy.setText(""+(kursKoronaDunska+0.2));
        double kursJen = metoda("http://api.nbp.pl/api/exchangerates/rates/a/jpy/?format=json");
        lblJenSell.setText(""+kursJen);
        lblJenBuy.setText(""+(kursJen+0.2));
    }
    private double metoda(String urlString){
        try {
            String apiUrl = urlString;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String jsonResponse = response.toString();
                Double odpowiedz = Double.parseDouble(jsonResponse.split(",")[jsonResponse.split(",").length - 1].replace('"',' ').replace('m',' ').replace('i',' ').replace('d',' ').replace(':',' ').replace('}',' ').replace(']',' ').strip());
                System.out.println(odpowiedz);
                return odpowiedz;
            } else {
                System.out.println("Błąd podczas pobierania danych. Kod odpowiedzi: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}