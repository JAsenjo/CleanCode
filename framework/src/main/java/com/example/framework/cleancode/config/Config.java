package com.example.framework.cleancode.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase de utilidad para cargar y acceder a la configuración del fichero JSON.
 *
 */
public final class Config {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    /**
     * Charset por defecto para la lectura del JSON.
     */
    private static final String DEFAULT_CHARSET = Constants.DEFAULT_CHARSET;

    /**
     * Valor de clave de configuración del JSON.
     */
    private static final String JSON_KEY_CONFIG = "config";

    /**
     * Valor de clave de configuración del JSON.
     */
    private static final String JSON_KEY_KEY = "key";

    /**
     * Valor de clave de configuración del JSON.
     */
    private static final String JSON_KEY_TYPE = "type";

    /**
     * Valor de clave de configuración del JSON.
     */
    private static final String JSON_KEY_VALUE = "value";

    /**
     * Valor de configuración del JSON.
     */
    private static final String JSON_VALUE_TYPE_STRING = "string";

    /**
     * Valor de configuración del JSON.
     */
    private static final String JSON_VALUE_TYPE_INTEGER = "int";

    /**
     * Valor de configuración del JSON.
     */
    private static final String JSON_VALUE_TYPE_BOOLEAN = "boolean";

    /**
     * Valor de configuración del JSON.
     */
    private static final String JSON_VALUE_TYPE_DOUBLE = "double";

    /**
     * Nombre del fichero de configuración.
     */
    public static final String CONFIG_FILE = "config.json";

    /**
     * Mapa con la configuración.
     */
    private Map<String, Object> mConfig = null;

    /**
     * Cargamos la configuración desde el stream en formato JSON.
     *
     * @param is el stream que contiene la configuración en formato JSON.
     *
     * @throws IOException al leer el fichero.
     * @throws JSONException al procesar el fichero.
     */
    public void loadJSON(final InputStream is) throws IOException, JSONException {

        mConfig = convertJSONToMap(convertStreamToString(is));

    }

    /**
     * Obtenemos desde la configuración en formato string el valor de la clave.
     *
     * @param key la clave de configuración.
     *
     * @return el valor de configuración.
     */
    public String getString(final String key) {

        checkConfigNull();

        return (String) mConfig.get(key);

    }

    /**
     * Fijamos en la configuración en formato string el valor de la clave.
     *
     * @param key la clave de configuración.
     * @param value el valor de configuración.
     */
    public void setString(final String key, final String value) {

        checkConfigNull();

        mConfig.put(key, value);

    }

    /**
     * Obtenemos desde la configuración en formato int el valor de la clave.
     *
     * @param key la clave de configuración.
     *
     * @return el valor de configuración.
     */
    public Integer getInteger(final String key) {

        checkConfigNull();

        return (Integer) mConfig.get(key);

    }

    /**
     * Fijamos en la configuración en formato integer el valor de la clave.
     *
     * @param key la clave de configuración.
     * @param value el valor de configuración.
     */
    public void setInteger(final String key, final Integer value) {

        checkConfigNull();

        mConfig.put(key, value);

    }

    /**
     * Obtenemos desde la configuración en formato double el valor de la clave.
     *
     * @param key la clave de configuración.
     *
     * @return el valor de configuración.
     */
    public Double getDouble(final String key) {

        checkConfigNull();

        return (Double) mConfig.get(key);

    }

    /**
     * Fijamos en la configuración en formato double el valor de la clave.
     *
     * @param key la clave de configuración.
     * @param value el valor de configuración.
     */
    public void setDouble(final String key, final Double value) {

        checkConfigNull();

        mConfig.put(key, value);

    }

    /**
     * Obtenemos desde la configuración en formato boolean el valor de la clave.
     *
     * @param key la clave de configuración.
     *
     * @return el valor de configuración.
     */
    public Boolean getBoolean(final String key) {

        checkConfigNull();

        return (Boolean) mConfig.get(key);

    }

    /**
     * Fijamos en la configuración en formato boolean el valor de la clave.
     *
     * @param key la clave de configuración.
     * @param value el valor de configuración.
     */
    public void setBoolean(final String key, final Boolean value) {

        checkConfigNull();

        mConfig.put(key, value);

    }

    /**
     * Método de conveniencia para convertir un stream en texto.
     *
     * @param is el stream a convertir.
     *
     * @return el stream en formato string.
     *
     * @throws IOException al leer el fichero.
     */
    private String convertStreamToString(final InputStream is) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(is, DEFAULT_CHARSET));

        final StringBuilder sb = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        return sb.toString();

    }

    /**
     * Método de conveniencia para procesar un JSON simple, en formato clave/valor, a un mapa.
     *
     * @param json el JSON a procesar.
     *
     * @return el mapa con la configuración.
     *
     * @throws JSONException al procesar el fichero.
     */
    private Map<String, Object> convertJSONToMap(final String json) throws JSONException {

        LOG.debug("json config: {}", json);

        final JSONArray array = (new JSONObject(json)).getJSONArray(JSON_KEY_CONFIG);

        JSONObject object;
        String key;
        String type;

        final Map<String, Object> config = new HashMap<String, Object>();

        for (int i = 0; i < array.length(); i++) {

            object = array.getJSONObject(i);

            key = object.getString(JSON_KEY_KEY);
            type = object.getString(JSON_KEY_TYPE);

            if (JSON_VALUE_TYPE_STRING.equalsIgnoreCase(type)) {

                config.put(key, object.getString(JSON_KEY_VALUE));

            } else if (JSON_VALUE_TYPE_INTEGER.equalsIgnoreCase(type)) {

                config.put(key, object.getInt(JSON_KEY_VALUE));

            } else if (JSON_VALUE_TYPE_BOOLEAN.equalsIgnoreCase(type)) {

                config.put(key, object.getBoolean(JSON_KEY_VALUE));

            } else if (JSON_VALUE_TYPE_DOUBLE.equalsIgnoreCase(type)) {

                config.put(key, object.getDouble(JSON_KEY_VALUE));

            }

        }

        return config;

    }

    /**
     *
     */
    private void checkConfigNull() {

        if (mConfig == null) {
            throw new IllegalStateException("configuracion no inicializada");
        }

    }

}
