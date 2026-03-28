package seedu.finbro.utils;

import java.util.HashMap;
import java.util.Map;

public class CurrencyRateTable {
    private static final String BASE_CURRENCY = "SGD";
    private static final Map<String, Double> sgdToRate = new HashMap<>();

    static {
        sgdToRate.put("USD", 0.74);
        sgdToRate.put("EUR", 0.68);
        sgdToRate.put("MYR", 3.47);
        sgdToRate.put("GBP", 0.58);
        sgdToRate.put("JPY", 111.20);
        sgdToRate.put("CNY", 5.36);
    }

    public static boolean isSupportedCurrency(String currency) {
        String code = currency.toUpperCase();
        return !code.equals(BASE_CURRENCY) && !sgdToRate.containsKey(code);
    }

    public static double convert(double amount, String fromCurrency, String toCurrency) {
        String from = fromCurrency.toUpperCase();
        String to = toCurrency.toUpperCase();

        if (isSupportedCurrency(from) || isSupportedCurrency(to)) {
            throw new IllegalArgumentException("Unsupported currency.");
        }

        if (from.equals(to)) {
            return amount;
        }

        if (from.equals(BASE_CURRENCY)) {
            return amount * sgdToRate.get(to);
        }

        if (to.equals(BASE_CURRENCY)) {
            return amount / sgdToRate.get(from);
        }

        double amountInSgd = amount / sgdToRate.get(from);
        return amountInSgd * sgdToRate.get(to);
    }

    public static String getSupportedCurrencies() {
        return BASE_CURRENCY + ", " + String.join(", ", sgdToRate.keySet());
    }
}