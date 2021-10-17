package com.example.roomsbotadmin;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuthToken {

    public static String getAuthToken() {
        try
        {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("username","admin");
            params.put("password", "1");
            params.put("grant_type", "password");
            params.put("client_id", "bot-client");
            params.put("client_secret", "c0ab8651-1821-4f77-94cd-690e31545c5c");
            params.put("scope", "openid");

            StringBuilder postData = new StringBuilder();
            for(Map.Entry<String,Object> param : params.entrySet())
            {
                if(postData.length() != 0)
                {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()),StandardCharsets.UTF_8));
            }
            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

            URL url = new URL("http://localhost:8484/auth/realms/bot/protocol/openid-connect/token/");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.getOutputStream().write(postDataBytes);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            for (String line = reader.readLine(); line != null; line = reader.readLine())
            {
                buffer.append(line);
            }

            JSONObject json = new JSONObject(buffer.toString());
            String accessToken = json.getString("access_token");
            return accessToken;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  null;
    }

}
