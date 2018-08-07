package com.example.framework.cleancode.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase de ayuda para hacer operaciones comunes con fechas.
 *
 * Created by marcos.regueras on 17/09/2016.
 */
public final class DateHelper {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DateHelper.class);

    /**
     *
     */
    private DateHelper() {
    }

    /**
     *
     * @param date
     * @return
     */
    public static Calendar toCalendar(final Date date) {

        Calendar calendar = null;

        if (date != null) {

            calendar = Calendar.getInstance();
            calendar.setTime(date);

        }

        return calendar;

    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(final Date date, final String pattern) {

        if (date != null) {

            return new SimpleDateFormat(pattern).format(date);

        }

        return null;

    }

    /**
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutesToDate(final Date date, final int minutes) {

        if (date != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            calendar.add(Calendar.MINUTE, minutes);

            return calendar.getTime();

        }

        return null;

    }

}
