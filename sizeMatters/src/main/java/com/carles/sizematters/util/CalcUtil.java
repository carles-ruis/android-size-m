package com.carles.sizematters.util;

import java.util.Locale;

public class CalcUtil {

    private static final Float CMS_PER_INCH = 2.54f;

    public static String cmsToInches(String cmsAsString) {
        try {
            float cms = Float.parseFloat(cmsAsString);
            float inches = cms / CMS_PER_INCH;
            return String.format(Locale.US, "%.1f \"", inches);
        } catch (NumberFormatException e) {
            /*- not a number, text is shown without parsing it */
            return cmsAsString;
        }
    }

}
