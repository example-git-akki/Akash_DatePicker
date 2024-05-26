package utilities;

import java.util.Date;

public class unique {
    public static String getTimeStamp() {
        Date date = new Date();
        String partialName = date.toString().replaceAll(":", "_").replaceAll(" ", "_");
        return partialName;
    }
}
