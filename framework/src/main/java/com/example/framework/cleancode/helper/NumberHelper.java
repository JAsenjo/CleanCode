package com.example.framework.cleancode.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Clase de ayuda para hacer operaciones comunes con números.
 *
 * Created by marcos.regueras on 17/09/2016.
 */
public final class NumberHelper {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(NumberHelper.class);

    /**
     *
     */
    private NumberHelper() {
    }

    /**
     *
     * @param source
     * @return
     */
    public static BigInteger toBigInteger(final Long source) {

        BigInteger destination = null;

        if (source != null) {

            destination = new BigInteger(source.toString());

        }

        return destination;

    }

    /**
     *
     * @param source
     * @return
     */
    public static Double toDouble(final Float source) {

        Double destination = null;

        if (source != null) {

            destination = Double.valueOf(Float.toString(source));

        }

        return destination;

    }

    /**
     *
     * @param source
     * @return
     */
    public static Float toFloat(final Double source) {

        Float destination = null;

        if (source != null) {

            destination = Float.valueOf(Double.toString(source));

        }

        return destination;

    }

    /**
     *
     * @param source
     * @return
     */
    public static Integer toInteger(final Object source) {

        Integer destination = null;

        if (source instanceof String) {

            destination = Integer.parseInt((String) source);

        } else if (source instanceof Integer) {

            destination = (Integer) source;

        } else if (source instanceof Long) {

            destination = ((Long) source).intValue();

        } else if (source instanceof Double) {

            destination = ((Double) source).intValue();

        } else if (source instanceof Float) {

            destination = ((Float) source).intValue();

        } else {

            // la primera vez se verá el error y se actuará en consecuencia ampliando estos if
            destination = -1;

        }

        return destination;

    }

    /**
     *
     * @param pattern
     * @return
     */
    public static String toStringWithPattern(final Double source, final String pattern) {

        final DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(new Locale("es", "ES"));

        String destination = null;
        DecimalFormat decimalFormat = new DecimalFormat(pattern, dfs);

        if (source != null) {

            destination = decimalFormat.format(source);

        }

        return destination;

    }

    /**
     *
     * @param value
     * @param places
     * @return
     */
    public static double round(final double value, final int places) {

        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();

    }

}
