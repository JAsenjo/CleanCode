package com.example.framework.cleancode.helper;

import android.text.TextUtils;

import com.example.framework.cleancode.config.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase de ayuda para crear un builder gson.
 *
 */
public final class GsonHelper {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(GsonHelper.class);

    /**
     *
     */
    private static Gson sGson = null;

    /**
     *
     */
    private GsonHelper() {
    }

    /**
     *
     * @return gson
     */
    public static Gson getInstance() {

        synchronized (GsonHelper.class) {

            if (sGson == null) {

                GsonBuilder gsonBuilder = new GsonBuilder();

                if (!TextUtils.isEmpty(Constants.GSON_DATE_FORMAT)) {

                    final DateFormat dataFormat = new SimpleDateFormat(Constants.GSON_DATE_FORMAT, Locale.getDefault());

                    // se especifica para formatear las fechas
                    JsonDeserializer<Date> dateJsonDeserializer = new JsonDeserializer<Date>() {

                        @Override
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            try {
                                return json != null ? dataFormat.parse(json.getAsString()) : null;
                            } catch (ParseException e) {
                                LOG.error("error parseando fecha {}", e);
                                return null;
                            }
                        }

                    };
                    gsonBuilder.registerTypeAdapter(Date.class, dateJsonDeserializer);

                    // se especifica para formatear las fechas
                    JsonSerializer<Date> dateJsonSerializer = new JsonSerializer<Date>() {

                        @Override
                        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                            return src != null ? new JsonPrimitive(dataFormat.format(src)) : null;
                        }

                    };
                    gsonBuilder.registerTypeAdapter(Date.class, dateJsonSerializer);

                }

                sGson = gsonBuilder.create();

            }
        }

        return sGson;

    }

}
