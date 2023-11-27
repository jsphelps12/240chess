package ServerFacade;

import Responses.ListResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

import static ServerFacade.ServerFacade.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServerFacadeTest {
    //@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

    private ServerFacade serverFacade = new ServerFacade();
    private static String auth;

    @BeforeAll
    public static void setUp(){
        auth = null;
    }

    @Test
    @Order(1)
    public void clearAll() throws Exception{
        URI uri = new URI("http://localhost:8080/db");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("DELETE");
        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");


        // Make the request
        http.connect();

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Output the response body
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                Gson gson = new Gson();
                Map responseBody = gson.fromJson(inputStreamReader, Map.class);
            }
        }
    }

    @Test
    @Order(3)
    public void registerHelperTest() throws Exception {
        String[] args = {"register","user","pass","email"};
        Map response = registerHelper(args);
        auth = response.get("authToken").toString();
        boolean success = false;
        if(response.containsKey("username")){
            success = true;
        }
        Assertions.assertTrue(success, "Didn't work as expected");
    }

    @Test
    @Order(7)
    public void loginHelperTest() throws Exception {
        String[] args1 = {"login","user","pass"};
        Map response = loginHelper(args1);
        auth = response.get("authToken").toString();
        boolean success = false;

        if(response.containsKey("authToken")){
            success = true;
        }
        Assertions.assertTrue(success, "Didn't work as expected");
    }

    @Test
    @Order(5)
    public void logoutHelperTest() throws Exception {
        String[] args = {"logout"};
        Map response = logoutHelper(args,auth);
        boolean success = false;
        if(response.containsKey("message")){
            success = true;
        }
        Assertions.assertFalse(success, "Didn't work as expected");
    }

    @Test
    @Order(8)
    public void createHelperTest() throws Exception {
        String[] args = {"create","testGame"};
        Map response = createHelper(args, auth);
        boolean success = false;
        if(response.containsKey("gameID")){
            success = true;
        }
        Assertions.assertTrue(success, "Didn't work");
    }

    @Test
    @Order(10)
    public void listHelperTest() throws Exception{
        String[] args = {"list"};
        ListResponse response = listHelper(args, auth);
        boolean success = false;
        if(response.getMessage() == null){
            success = true;
        }
        Assertions.assertTrue(success,"didn't work");
    }

    @Test
    @Order(14)
    public void observeHelperTest() throws Exception{
        String[] args = {"join","1"};
        Map response = joinHelper(args, auth);
        boolean success = false;
        if(!response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success,"didn't work");
    }

    @Test
    @Order(15)
    public void joinHelperTest() throws Exception{
        String[] args = {"join","1","white"};
        Map response = joinHelper(args, auth);
        boolean success = false;
        if(!response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success,"didn't work");
    }

    @Test
    @Order(2)
    public void registerHelperFail() throws Exception {
        String[] args = {"register","user","pass",};
        Map response = registerHelper(args);
        boolean success = false;
        if(response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success, "Didn't work as expected");
    }

    @Test
    @Order(6)
    public void loginHelperFail() throws Exception {
        String[] args1 = {"login","user1"};
        Map response = loginHelper(args1);
        boolean success = false;

        if(response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success, "Didn't work as expected");
    }

    @Test
    @Order(4)
    public void logoutHelperFail() throws Exception {
        String[] args = {"logout"};
        Map response = logoutHelper(args,"NotAGoodAuth");
        boolean success = false;
        if(response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success, "Didn't work as expected");
    }


    @Test
    @Order(9)
    public void createHelperFail() throws Exception{
        String[] args = {"create","testGame"};
        Map response = createHelper(args, "NotGoodAuth");
        boolean success = false;
        if(response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success, "Didn't work");
    }

    @Test
    @Order(11)
    public void listHelperFail() throws Exception{
        String[] args = {"list"};
        ListResponse response = listHelper(args, "NotAnAuth");
        boolean success = false;
        if(response.getMessage() != null){
            success = true;
        }
        Assertions.assertTrue(success,"didn't work");
    }

    @Test
    @Order(12)
    public void observeHelperFail() throws Exception{
        String[] args = {"join","1"};
        Map response = joinHelper(args, "NotAnAuth");
        boolean success = false;
        if(response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success,"didn't work");
    }

    @Test
    @Order(13)
    public void joinHelperFail() throws Exception{
        String[] args = {"join","1","white"};
        Map response = joinHelper(args, "BadAuth");
        boolean success = false;
        if(response.containsKey("message")){
            success = true;
        }
        Assertions.assertTrue(success,"didn't work");
    }
}