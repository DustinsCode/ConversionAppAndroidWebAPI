package com.conversionappandroid.dummy;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class HistoryContent {
    public static final List<HistoryItem> ITEMS = new ArrayList<HistoryItem>();

//    static {
//        DateTime now = DateTime.now();
//        addItem(new HistoryItem(2.0, 1.829, "Length", "Yards", "Meters", now.minusDays(1)));
//        addItem(new HistoryItem(1.0, 3.785, "Volume", "Gallons", "Liters", now.minusDays(1)));
//        addItem(new HistoryItem(2.0, 1.829, "Length", "Yards", "Meters", now.plusDays(1)));
//        addItem(new HistoryItem(1.0, 3.785, "Volume", "Gallons", "Liters", now.plusDays(1)));
//    }

    public static void addItem(HistoryItem item) {
        ITEMS.add(item);
    }

    public static class HistoryItem {
        public Double fromVal;
        public Double toVal;
        public String mode;
        public String fromUnits;
        public String toUnits;
        public String _key;


        public String timestamp;

        public HistoryItem(){}

        public HistoryItem(Double fromVal, Double toVal, String mode,
                           String fromUnits, String toUnits, DateTime timestamp) {
            this.fromVal = fromVal;
            this.toVal = toVal;
            this.mode = mode;
            this.fromUnits = fromUnits;
            this.toUnits = toUnits;
            this.timestamp = timestamp.toString();
        }

        @Override
        public String toString() {
            return this.fromVal + " " + this.fromUnits + " = " + this.toVal + " " + this.toUnits;
        }
    }
}

