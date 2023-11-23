package ServerFacade;

import Responses.ListResponse;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ServerFacade{
    public static Map registerHelper(String[] args) throws Exception{
        URI uri = new URI("http://localhost:8080/user");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("POST");

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");

        if(args.length != 4){
            Map respBody = new HashMap();
            respBody.put("message","Error: Wrong number of Arguments");
            return respBody;
        }

        // Write out the body
        var body = Map.of("username", args[1], "password", args[2],"email",args[3]);
        try (var outputStream = http.getOutputStream()) {
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());
        }

        // Make the request
        http.connect();

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                Map responseBody = gson.fromJson(inputStreamReader, Map.class);
                return responseBody;
            }
        }
        else{
            InputStream respBody = http.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            Gson gson = new Gson();
            Map responseBody = gson.fromJson(inputStreamReader, Map.class);
            return responseBody;
        }



    }

    public static Map loginHelper(String[] args) throws Exception{
        URI uri = new URI("http://localhost:8080/session");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("POST");

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");

        if(args.length != 3){
            Map respBody = new HashMap();
            respBody.put("message","Error: Wrong number of Arguments");
            return respBody;
        }

        // Write out the body
        var body = Map.of("username", args[1], "password", args[2]);
        try (var outputStream = http.getOutputStream()) {
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());
        }

        // Make the request
        http.connect();

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                Map responseBody = gson.fromJson(inputStreamReader, Map.class);
                return responseBody;
            }
        }
        else{
            InputStream respBody = http.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            Gson gson = new Gson();
            Map responseBody = gson.fromJson(inputStreamReader, Map.class);
            return responseBody;
        }
    }

    public static Map logoutHelper(String[] args, String auth) throws Exception{
        URI uri = new URI("http://localhost:8080/session");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("DELETE");

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");
        http.addRequestProperty("authorization",auth);

        // Make the request
        http.connect();

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                Map responseBody = gson.fromJson(inputStreamReader, Map.class);
                return responseBody;
            }
        }
        else{
            InputStream respBody = http.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            Gson gson = new Gson();
            Map responseBody = gson.fromJson(inputStreamReader, Map.class);
            return responseBody;
        }
    }

    public static Map createHelper(String[] args, String auth) throws Exception{
        URI uri = new URI("http://localhost:8080/game");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("POST");

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");
        http.addRequestProperty("authorization",auth);

        if(args.length != 2){
            Map respBody = new HashMap();
            respBody.put("message","Error: Wrong number of Arguments");
            return respBody;
        }


        // Write out the body
        var body = Map.of("gameName", args[1]);
        try (var outputStream = http.getOutputStream()) {
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());
        }

        // Make the request
        http.connect();

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                Map responseBody = gson.fromJson(inputStreamReader, Map.class);
                return responseBody;
            }
        }
        else{
            InputStream respBody = http.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            Gson gson = new Gson();
            Map responseBody = gson.fromJson(inputStreamReader, Map.class);
            return responseBody;
        }
    }

    public static ListResponse listHelper(String[] args, String auth) throws Exception{
        URI uri = new URI("http://localhost:8080/game");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("GET");

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");
        http.addRequestProperty("authorization",auth);

        // Make the request
        http.connect();


        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                ListResponse responseBody = gson.fromJson(inputStreamReader, ListResponse.class);
                return responseBody;
            }
        }
        else{
            InputStream respBody = http.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            Gson gson = new Gson();
            ListResponse responseBody = gson.fromJson(inputStreamReader, ListResponse.class);
            return responseBody;
        }
    }

    public static Map observeHelper(String[] args, String auth) throws Exception{
        URI uri = new URI("http://localhost:8080/game");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("PUT");

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");
        http.addRequestProperty("authorization",auth);

        if(args.length != 2){
            Map respBody = new HashMap();
            respBody.put("message","Error: Wrong number of Arguments");
            return respBody;
        }

        // Write out the body
        int id = Integer.parseInt(args[1]);
        var body = Map.of("gameID", id);
        try (var outputStream = http.getOutputStream()) {
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());
        }

        // Make the request
        http.connect();

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                Map responseBody = gson.fromJson(inputStreamReader, Map.class);
                return responseBody;
            }
        }
        else{
            InputStream respBody = http.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            Gson gson = new Gson();
            Map responseBody = gson.fromJson(inputStreamReader, Map.class);
            return responseBody;
        }
    }
    public static Map joinHelper(String[] args, String auth) throws Exception{
        URI uri = new URI("http://localhost:8080/game");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("PUT");

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");
        http.addRequestProperty("authorization",auth);

        if(args.length != 3){
            Map respBody = new HashMap();
            respBody.put("message","Error: Wrong number of Arguments");
            return respBody;
        }
        if(args[2] != null) {
            String color = args[2].toUpperCase();
            if (color != "WHITE" || color != "BLACK") {
                Map respBody = new HashMap();
                respBody.put("message", "Error: Not an acceptable color");
                return respBody;
            }
        }

        // Write out the body
        String color = args[2].toUpperCase();
        int id = Integer.parseInt(args[1]);
        var body = Map.of("playerColor",color,"gameID", id);
        try (var outputStream = http.getOutputStream()) {
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());
        }

        // Make the request
        http.connect();

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                Map responseBody = gson.fromJson(inputStreamReader, Map.class);
                return responseBody;
            }
        }
        else{
            InputStream respBody = http.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            Gson gson = new Gson();
            Map responseBody = gson.fromJson(inputStreamReader, Map.class);
            return responseBody;
        }
    }

}
