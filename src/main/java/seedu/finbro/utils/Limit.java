package seedu.finbro.utils;

public class Limit {
    private static double limit = 0;
    private static double spent = 0;

    public static double getLimit() {
        return limit;
    }

    public static double getSpent() {
        return spent;
    }

    public static void setSpent(double spent) {
        Limit.spent = spent;
    }

    public static String toFileFormat() {
        return String.format("%.2f", Limit.getLimit()) + "\n";
    }

    public static void setLimit(double limit) {
        if (limit >= 0) {
            Limit.limit = limit;
        }
    }
}
