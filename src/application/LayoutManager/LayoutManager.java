package application.LayoutManager;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LayoutManager {
    private static Map<String, String > dostNiedost = Stream.of(new Object[][] {
            { "T", "Dostępn" },
            { "F", "Niedostępn" },
            { "N", "Niedostępn" },
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

    private static Map<String, String > genderTypeToScreen = Stream.of(new Object[][] {
            { "M", "Mężczyzna" },
            { "K", "Kobieta" },
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

    private static Map<String, String> genderTypeFromScreen = Stream.of(new Object[][]{
            {"Mężczyzna", "M"},
            {"Kobieta", "K"},
            {"mężczyzna", "M"},
            {"kobieta", "K"},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

    private static Map<String, String > serviceType = Stream.of(new Object[][] {
            { "ABONAMENTY_KOMORKOWE", "Abonament komorkowy" },
            { "ABONAMENTY_SPRZETOWE", "Sprzęt" },
            { "TELEWIZJE", "Telewizja"},
            { "INTERNETY_STACJONARNE", "Internet stacjonarny"},
            { "INTERNETY_MOBILNE", "Internet mobilny"},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));


    public static String TF(String txt, String lastLetter) {
        return dostNiedost.get(txt) + lastLetter;
    }

    public static String TFfromScreen(String text) {
        if(text.contains("Dostępn")) {
            return "T";
        } else if(text.contains("Niedostępn")) {
            return "F";
        }
        return "";
    }

    public static String genderToScreen(String gender) {
        return genderTypeToScreen.get(gender);
    }

    public static String genderFromScreen(String gender) {
        return genderTypeFromScreen.get(gender);
    }

    public static String serviceType(String service) {
        return serviceType.get(service);
    }

    public static String phoneNumberToScreen(String phone) {
        StringBuffer buffer = new StringBuffer(phone);
        buffer.insert(6," ");
        buffer.insert(3," ");
        return buffer.toString();
    }

    public static String phoneNumberFromScreen(String phone) {
        String[] buffer = phone.split(" ");
        String out = "";
        for(String str: buffer) {
            out+=str;
        }
        return  out;
    }

    public static String downlinkToScreen(String downlink) {
        return downlink + " mb/s";
    }
    public static String downlinkFromScreen(String downlink) {
        String[] tmp = downlink.split(" ");
        return tmp[0];
    }

    public static String uplinkToScreen(String uplink) {
        int tmp = (int) (Double.parseDouble(uplink) / (double) 10);
        return tmp + " mb/s";
    }
    public static String upLinkFromScreen(String uplink) {
        String[] tmp = uplink.split(" ");
        return tmp[0];
    }

    public static String minutesToScreen(String minutes) {
        if(minutes.contains("INF")) {
            return "nielimitowane";
        } else {
            return minutes;
        }
    }
    public static String minutesFromScreen(String minutes) {
        if(minutes.contains("nielimitowane")) {
            return "INF";
        } else {
            return minutes;
        }
    }
}
