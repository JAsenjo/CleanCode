package com.example.framework.cleancode.okhttp;

import com.example.framework.cleancode.exceptions.HttpException;
import com.example.framework.cleancode.exceptions.UnauthorizedException;
import com.example.framework.cleancode.helper.GsonHelper;
import com.example.framework.cleancode.helper.HttpClientHelper;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @param <T>
 * @param <S>
 */
public class HttpClient<T, S> {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=UTF-8");

    private static final String HTTP_METHOD_GET = "get";
    private static final String HTTP_METHOD_POST = "post";
    private static final String HTTP_METHOD_PUT = "put";

    private Map<String, String> requestHeaders;

    /**
     *
     * @param url url servicio
     * @param clazz clase para serializar
     * @return S
     */
    public S get(final String url, final Class<S> clazz) {
        return request(HTTP_METHOD_GET, null, url, clazz);
    }

    /**
     *
     * @param request request
     * @param url url
     * @param clazz clazz
     * @return S
     */
    public S post(final T request, final String url, final Class<S> clazz) {
        return request(HTTP_METHOD_POST, request, url, clazz);
    }

    /**
     *
     * @param request request
     * @param url url
     * @param clazz clazz
     * @return S
     */
    public S put(final T request, final String url, final Class<S> clazz) {
        return request(HTTP_METHOD_PUT, request, url, clazz);
    }

    /**
     *
     * @param method method
     * @param request request
     * @param url url
     * @param clazz clazz
     * @return S
     */
    protected S request(final String method, final T request, final String url, final Class<S> clazz) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("metodo = {}", method);
            LOG.debug("peticion = {}", request);
            LOG.debug("url = {}", url);
            LOG.debug("class = {}", clazz);
        }

        S response = null;

        Response responseHttp = null;

        try {

            final Gson gson = GsonHelper.getInstance();
            final OkHttpClient httpClient = HttpClientHelper.getInstance();

            Request.Builder requestHttpBuilder = null;

            switch (method) {
                case HTTP_METHOD_GET:
                    requestHttpBuilder = new Request.Builder().url(url);
                    break;
                case HTTP_METHOD_POST:
                    requestHttpBuilder = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(request)));
                    break;
                case HTTP_METHOD_PUT:
                    requestHttpBuilder = new Request.Builder().url(url).put(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(request)));
                    break;
            }

            Request requestHttp = null;

            if (requestHttpBuilder != null) {

                if (this.requestHeaders != null) {

                    if (LOG.isDebugEnabled()){
                        LOG.debug("requestHeaders: {}", this.requestHeaders);
                    }

                    for (String key : this.requestHeaders.keySet()) {
                        requestHttpBuilder.addHeader(key, this.requestHeaders.get(key));
                    }

                }

                requestHttp = requestHttpBuilder.build();

            }

            assert requestHttp != null;
            responseHttp = httpClient.newCall(requestHttp).execute();

            if (!responseHttp.isSuccessful()) {

                if (responseHttp.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    throw new UnauthorizedException("no autorizado " + responseHttp);
                } else {
                    throw new IOException("codigo inesperado " + responseHttp);
                }

            }

            response = gson.fromJson(responseHttp.body().charStream(), clazz);

            if (LOG.isDebugEnabled()){
                LOG.debug("respuesta: {}", response);
            }

        } catch (UnauthorizedException ua) {
            LOG.warn(ua.getMessage(), ua);
            throw ua;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new HttpException(e);
        } finally {
            HttpClientHelper.closeQuietly((responseHttp != null) ? responseHttp.body() : null);
        }

        return response;

    }

    /**
     *
     * @param requestHeaders cabeceras
     */
    public void setRequestHeaders(final Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

}
