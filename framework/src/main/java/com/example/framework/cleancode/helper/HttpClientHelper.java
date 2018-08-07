package com.example.framework.cleancode.helper;

import com.example.framework.cleancode.application.Application;
import com.example.framework.cleancode.config.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Clase de ayuda para crear un cliente http.
 *
 * Created by marcos.regueras on 17/09/2016.
 */
public final class HttpClientHelper {

    /**
     * Log de la aplicación.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientHelper.class);

    /**
     * Separador de los hash para un servidor.
     */
    private static final String TOKEN_HASH = "#hash#";

    /**
     * Separador de los servidores.
     */
    private static final String TOKEN_SERVER = "#server#";

    /**
     *
     */
    private static OkHttpClient sOkHttpClient = null;

    /**
     *
     */
    private HttpClientHelper() {
    }

    /**
     * Método para obtener el cliento http, después de configurarse.
     *
     * @return el cliente http.
     */
    public static OkHttpClient getInstance() {

        synchronized (HttpClientHelper.class) {
            if (sOkHttpClient == null) {
                sOkHttpClient = getOkHttpClient();
            }
        }

        return sOkHttpClient;

    }

    /**
     * @param closeable
     */
    public static void closeQuietly(final Closeable closeable) {
        IOHelper.closeQuietly(closeable);
    }

    /**
     * Método para obtener el cliento http, después de configurarse.
     *
     * @return el cliente http.
     */
    private static OkHttpClient getOkHttpClient() {

        final int connectTimeout = Application.getInstance().getConfig().getInteger(Constants.CONFIG_CONNECT_TIMEOUT);
        final int socketTimeout = Application.getInstance().getConfig().getInteger(Constants.CONFIG_SOCKET_TIMEOUT);

        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(socketTimeout, TimeUnit.SECONDS)
                .writeTimeout(socketTimeout, TimeUnit.SECONDS).build();

        // Para generar los datos, se utiliza la siguiente orden:
        // # openssl x509 -in velocity.pem -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha1 -binary | openssl enc -base64
        // o, siempre codificado en base64
        // # openssl x509 -in my-certificate.cer -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
        //
        // https://developer.mozilla.org/en-US/docs/Web/Security/Public_Key_Pinning
        /*
        String certificatePinner = Config.getString(Constants.CONFIG_CERTIFICATE_PINNER);

        if (!TextUtils.isEmpty(certificatePinner)) {

            CertificatePinner.Builder builder = new CertificatePinner.Builder();

            String[] servers = certificatePinner.split(TOKEN_SERVER);

            for (String server: servers) {

                String[] hash = server.split(TOKEN_HASH);

                // La primera posición es el servidor, el resto los hash

                for (int j = 1; j < hash.length; j++) {

                    builder.add(hash[0], hash[j]);

                }

            }

            client.setCertificatePinner(builder.build());

        }
        */

        return client;

    }

}
