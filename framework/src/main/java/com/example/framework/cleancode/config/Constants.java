package com.example.framework.cleancode.config;

/**
 *
 */
public final class Constants {

    /**
     * Charset por defecto.
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * Constante con el token de separación utilizado en el fichero de config JSON.
     */
    public static final String CONFIG_TOKEN_SEPARATION = "_@token@_";

    /**
     * Clave del JSON de configuración que contiene el entorno.
     */
    public static final String CONFIG_ENVIRONMENT = "environment";

    /**
     * Clave del JSON de configuración que contiene el tipo de dispositivo.
     */
    public static final String CONFIG_DEVICE = "device";

    /**
     * Clave del JSON de configuración que contiene los certificados de servidor para comprobar.
     */
    public static final String CONFIG_CERTIFICATE_PINNER = "certificate_pinner";

    /**
     * Clave del JSON de configuración que contiene el tiempo de timeout.
     */
    public static final String CONFIG_CONNECT_TIMEOUT = "connect_timeout";

    /**
     * Clave del JSON de configuración que contiene el tiempo de timeout de lectura/escritura del socket.
     */
    public static final String CONFIG_SOCKET_TIMEOUT = "socket_timeout";

    /**
     * Clave del JSON de configuración que contiene el tiempo de timeout para una conexión larga.
     */
    public static final String CONFIG_LONG_CONNECT_TIMEOUT = "long_connect_timeout";

    /**
     * Clave del JSON de configuración que contiene la clase para el acceso a la base de datos.
     */
    public static final String CONFIG_DATABASE_HELPER = "database_helper";

    /**
     * Clave del JSON de configuración que contiene un imei de ejemplo para las llamadas.
     */
    public static final String CONFIG_MOBILE_IMEI = "mobile_imei";

    /**
     * Clave de la preferencia que contiene el valor de la session.
     */
    public static final String PREFERENCE_SESSION = "session";

    /**
     * Tipos de alertas que nos podemos encontrar en la aplicación.
     */
    public static final int TYPE_ALERT_DIALOG_INFO = 1;
    public static final int TYPE_ALERT_DIALOG_WARNING = 2;
    public static final int TYPE_ALERT_DIALOG_ERROR = 3;

    /**
     * Formato para las fechas para parsear los json (puede ser nul)
     * Normalmente viene marcado por el formato usado en las respuestas del servidor
     */
    public static final String GSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

}
