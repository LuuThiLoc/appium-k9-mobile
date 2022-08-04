package tests.gson;

import com.google.gson.Gson;
import test_data.models.LoginCreds;

public class TestGSON {

    public static void main(String[] args) {
        LoginCreds loginCreds = new LoginCreds("teo@sth.com", "12345678");

        // Convert from ObjectData to Json
        Gson gson = new Gson();
        System.out.println(gson.toJson(loginCreds));

        // Convert from Json to ObjectData
        String loginCredJSONData = "{\"email\":\"teo@sth.com\",\"password\":\"12345678\"}";
        LoginCreds convertedFromJSON = gson.fromJson(loginCredJSONData, LoginCreds.class);
        System.out.println(convertedFromJSON);
    }
}
