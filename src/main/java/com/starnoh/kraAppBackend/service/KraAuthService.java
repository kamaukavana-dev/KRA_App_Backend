package com.starnoh.kraAppBackend.service;

import com.starnoh.kraAppBackend.config.KraConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import org.json.JSONException;

import java.io.IOException;
import java.time.Instant;
import java.util.Base64;

@Service
public class KraAuthService {

    private final KraConfig kraConfig;

    public KraAuthService(KraConfig kraConfig){
        this.kraConfig = kraConfig;
    }

    private String accessToken;
    private Instant expiryTime;

    public String getAccessToken() {
        if (accessToken == null || Instant.now().isAfter(expiryTime)) {

            try {
                generateNewToken();
            }catch(IOException | JSONException e) {
                throw new RuntimeException("Failed to generate KRA access token" , e);
            }

        }
        return accessToken;
    }

    private void generateNewToken() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        String consumerKey = kraConfig.getConsumerKey();
        String consumerSecret = kraConfig.getConsumerSecret();

        if (consumerKey == null || consumerKey.isEmpty() ||
                consumerSecret == null || consumerSecret.isEmpty()) {
            throw new JSONException("Auth credentials not provided");
        }

        System.out.println("Here is the consumer key " + consumerKey);
        System.out.println("Here is the consumer secret " + consumerSecret);

        String credentials = consumerKey + ":" + consumerSecret;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());

        Request request = new Request.Builder()
                .url("https://sbx.kra.go.ke/v1/token/generate?grant_type=client_credentials")
                .header("Authorization", "Basic " + encoded)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()){

            String json = response.body().string();

            System.out.println("Here is the response " + json);

            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed: " + response.code());
            }

            System.out.println(response.code());
            System.out.println("Token response: " + json);

            JSONObject jsonObject = new JSONObject(json);
            accessToken = jsonObject.getString("access_token");

            long expiresIn = jsonObject.getLong("expires_in");
            expiryTime = Instant.now().plusSeconds(expiresIn - 60); // minus 60 for safety

        } catch(Exception e) {

            System.out.println("Something went wrong : " + e.getMessage());
            e.printStackTrace();

        }

    }
}
