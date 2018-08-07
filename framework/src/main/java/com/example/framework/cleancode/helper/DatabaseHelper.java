package com.example.framework.cleancode.helper;

import android.database.Cursor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase de ayuda para hacer operaciones comunes con la base de datos.
 *
 * Created by marcos.regueras on 17/09/2016.
 */
public final class DatabaseHelper {

    /**
     * Formateador para las fechas almacenadas en base de datos
     */
    private final static SimpleDateFormat mDateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     *
     */
    private DatabaseHelper() {
    }

    /**
     *
     * @param cursor
     */
    public static void close(final Cursor cursor) {
        IOHelper.closeQuietly(cursor);
    }

    /**
     *
     * @param value
     * @return
     */
    public static Boolean toBoolean(final int value) {
        return (value == 1);
    }

    /**
     *
     * @param value
     * @return
     */
    public static int toNumber(final Boolean value) {
        return ((value != null) && value) ? 1 : 0;
    }

    /**
     *
     * @param value
     */
    public static Date toDate(final Long value) {

        return new Date(value);

    }

    /**
     *
     * @param value
     * @return
     */
    public static long toNumber(final Date value) {

        return value.getTime();

    }

    /**
     *
     * @param date
     * @return string con la fecha en el formato adecuado para almacenarla en base de datos
     */
    public static String dateToText(Date date) {

        String result = null;

        if (date != null) {
            result = mDateFormater.format(date);
        }

        return result;

    }

    public static Date parseDate(String dateString){

        Date result = null;

        if (dateString != null && dateString.length()>0){
            try {
                result = mDateFormater.parse(dateString);
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return result;

    }

}
