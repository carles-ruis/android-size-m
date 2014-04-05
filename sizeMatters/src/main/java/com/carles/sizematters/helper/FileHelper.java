package com.carles.sizematters.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.carles.sizematters.C;
import com.carles.sizematters.model.MenSizesConversion;
import com.carles.sizematters.model.SizeCountriesConversion;
import com.carles.sizematters.model.WomenSizesConversion;
import com.carles.sizematters.util.CalcUtil;

public class FileHelper {

    public static List<SizeCountriesConversion> readCountriesConversionResource(InputStream is) {
        List<SizeCountriesConversion> ret = new ArrayList<SizeCountriesConversion>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            String[] splitted;
            while ((line = br.readLine()) != null) {

                if (line.charAt(0) != C.FILE_COMMENT_MARKER) {
                    splitted = Pattern.compile("\\s").split(line);

                    String size = parseCountriesConversion(splitted[0]);
                    String eu = parseCountriesConversion(splitted[1]);
                    String us = parseCountriesConversion(splitted[2]);
                    String uk = parseCountriesConversion(splitted[3]);
                    String jp = parseCountriesConversion(splitted[4]);

                    ret.add(new SizeCountriesConversion(size, eu, us, uk, jp));

                }
            }
        } catch (IOException e) {
            ret = Collections.emptyList();
        }

        return ret;
    }

    private static String parseCountriesConversion(String src) {
        /*- check if is an unknown value*/
        if (C.UNKNOWN_VALUE.equals(src)) {
            return "";
        }
        return src;
    }

    public static Map<String, MenSizesConversion> readMenSizeConversionResource(InputStream is, boolean showInInches) {
        /*- LinkedHashMap is ordered by insertion order*/
        Map<String, MenSizesConversion> ret = new LinkedHashMap<String, MenSizesConversion>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            String splitted[];
            while ((line = br.readLine()) != null) {

                if (line.charAt(0) != C.FILE_COMMENT_MARKER) {

                    /*- format is: letters neck chest sleeve waist */
                    splitted = Pattern.compile("\\s").split(line);

                    String size = splitted[0];
                    String neck = parseGenderConversion(splitted[1], showInInches);
                    String chest = parseGenderConversion(splitted[2], showInInches);
                    String sleeve = parseGenderConversion(splitted[3], showInInches);
                    String waist = parseGenderConversion(splitted[4], showInInches);

                    ret.put(size, new MenSizesConversion(size, neck, chest, sleeve, waist));
                }
            }
        } catch (IOException e) {
            ret = Collections.emptyMap();
        }
        return ret;
    }

    public static Map<String, WomenSizesConversion> readWomenSizesConversionResource(InputStream is,
            boolean showInInches) {
        /*- LinkedHasMap is ordered by insertion order*/
        Map<String, WomenSizesConversion> ret = new LinkedHashMap<String, WomenSizesConversion>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            String[] splitted;
            while ((line = br.readLine()) != null) {

                if (line.charAt(0) != C.FILE_COMMENT_MARKER) {

                    /*- format is: letters bust waist hips */
                    splitted = Pattern.compile("\\s").split(line);

                    String size = splitted[0];
                    String bust = parseGenderConversion(splitted[1], showInInches);
                    String waist = parseGenderConversion(splitted[2], showInInches);
                    String hip = parseGenderConversion(splitted[3], showInInches);

                    ret.put(size, new WomenSizesConversion(size, bust, waist, hip));

                }
            }
        } catch (IOException e) {
            ret = Collections.emptyMap();
        }
        return ret;
    }

    private static String parseGenderConversion(String src, boolean showInInches) {

        /*- check if is an unknown value*/
        if (C.UNKNOWN_VALUE.equals(src)) {
            return "";
        }

        /*- show as centimetres, don't have to parse the value*/
        if (!showInInches) {
            return src;
        }

        /*- show as inches, we have to convert it from centimeters  */
        if (src.contains(C.RANGE_SEPARATOR)) {

            /*- convert a range of values to inches */
            String[] splitted = src.split(C.RANGE_SEPARATOR);
            return new StringBuilder().append(CalcUtil.cmsToInches(splitted[0])).append(C.RANGE_SEPARATOR)
                    .append(CalcUtil.cmsToInches(splitted[1])).toString();

        } else {
            /*- convert a single value to inches */
            return CalcUtil.cmsToInches(src);
        }

    }
}
