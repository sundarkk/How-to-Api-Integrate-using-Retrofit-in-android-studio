package com.sundar.shopping.classes;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {
    public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder httpClientBuilder = null;

    /*For HTTP base URL*/
    public static Retrofit apiService(Context context) {
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                   .baseUrl(Constants.BASE_URL_API)
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit apiService(Context context, int connectionTimeOut, int readTimeOut, int writeTimeOut) {
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(connectionTimeOut, TimeUnit.MINUTES)
                .readTimeout(readTimeOut, TimeUnit.MINUTES)
                .writeTimeout(writeTimeOut, TimeUnit.MINUTES);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_API)
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
    /*For HTTP base URL*/
    /*For HTTPS base URL*/
//    public static Retrofit apiService(Context context) {
//        httpClientBuilder = new OkHttpClient.Builder()
//                .connectTimeout(1, TimeUnit.MINUTES)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .writeTimeout(15, TimeUnit.SECONDS);
//
//        initHttpLogging();
//        initSSL(context);
//
//
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL_API)
//                    .client(httpClientBuilder.build())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return retrofit;
//    }
//
//    public static Retrofit apiService(Context context, int connectionTimeOut, int readTimeOut, int writeTimeOut) {
//        httpClientBuilder = new OkHttpClient.Builder()
//                .connectTimeout(connectionTimeOut, TimeUnit.MINUTES)
//                .readTimeout(readTimeOut, TimeUnit.MINUTES)
//                .writeTimeout(writeTimeOut, TimeUnit.MINUTES);
//
//        initHttpLogging();
//        initSSL(context);
//
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL_API)
//                    .client(httpClientBuilder.build())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return retrofit;
//    }
    /*For HTTPS base URL*/

   /* private static void initHttpLogging() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) httpClientBuilder.addInterceptor(logging);
    }

    private static void initSSL(Context context) {

        SSLContext sslContext = null;
        try {
            sslContext = createCertificate(context.getResources().openRawResource(R.raw.sslcert));
        } catch (CertificateException | IOException | KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (sslContext != null) {
            httpClientBuilder.sslSocketFactory(sslContext.getSocketFactory(), systemDefaultTrustManager());
        }

    }

    //Creating ssl certificate
    private static SSLContext createCertificate(InputStream trustedCertificateIS) throws CertificateException, IOException, KeyStoreException, KeyManagementException, NoSuchAlgorithmException {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate ca;
        try {
            ca = cf.generateCertificate(trustedCertificateIS);
        } finally {
            trustedCertificateIS.close();
        }

        // creating a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // creating a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // creating an SSLSocketFactory that uses our TrustManager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        return sslContext;

    }

    private static X509TrustManager systemDefaultTrustManager() {

        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }

    }*/

}
