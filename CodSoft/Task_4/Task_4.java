package Task_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class Task_4 {
    public static void main(String[] args) {
        // JSON data with exchange rates
        String jsonData = """
        {
            "disclaimer": "https://openexchangerates.org/terms/",
            "license": "https://openexchangerates.org/license/",
            "timestamp": 1449877801,
            "base": "USD",
            "rates": {
                "AED": 3.672538,
                "AFN": 66.809999,
                "ALL": 125.716501,
                "AMD": 484.902502,
                "ANG": 1.788575,
                "AOA": 135.295998,
                "ARS": 9.750101,
                "AUD": 1.390866
                // Add more rates as needed
            }
        }
        """;

        try {
            // Parse the JSON data
            JSONObject exchangeRates = new JSONObject(jsonData);

            // Display supported currencies
            System.out.println("Supported currencies:");
            for (String currency : exchangeRates.getJSONObject("rates").keySet()) {
                System.out.print(currency + " ");
            }
            System.out.println();

            // User input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the amount to convert: ");
            double amount = Double.parseDouble(reader.readLine());

            System.out.print("Enter the base currency (e.g., USD, EUR): ");
            String baseCurrency = reader.readLine().toUpperCase();

            System.out.print("Enter the target currency (e.g., USD, EUR): ");
            String targetCurrency = reader.readLine().toUpperCase();

            // Convert currency
            double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency, exchangeRates);
            System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static double convertCurrency(double amount, String baseCurrency, String targetCurrency, JSONObject exchangeRates) {
        JSONObject rates = exchangeRates.getJSONObject("rates");

        // Validate if base and target currencies exist in the rates
        if (!rates.has(baseCurrency)) {
            throw new IllegalArgumentException("Base currency not supported: " + baseCurrency);
        }
        if (!rates.has(targetCurrency)) {
            throw new IllegalArgumentException("Target currency not supported: " + targetCurrency);
        }

        double baseRate = rates.getDouble(baseCurrency);
        double targetRate = rates.getDouble(targetCurrency);

        // Convert amount from base currency to target currency
        return amount * (targetRate / baseRate);
    }
}
