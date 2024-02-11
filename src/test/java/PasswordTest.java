import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PasswordTest {

    @Test
    public void checkPasswordTest() throws InterruptedException{

        String successResult = "You are authorized";

        String[] passwordcheck = {"password", "123456", "123456789", "12345678", "12345", "qwerty", "abc123",
                "football", "1234567", "monkey", "111111", "letmein", "1234", "1234567890", "12345", "dragon",
                "baseball", "sunshine", "iloveyou", "trustno1", "princess", "adobe123", "123123", "welcome", "login",
                "admin", "qwerty123", "solo", "1q2w3e4r", "master", "666666", "photoshop", "1qaz2wsx", "qwertyuiop",
                "ashley", "mustang", "121212", "starwars", "bailey", "access", "flower", "555555", "passw0rd",
                "shadow", "lovely", "654321", "7777777", "michael", "!@#$%^&*", "jesus", "password1", "superman",
                "hello", "charlie", "888888", "696969", "hottie", "freedom", "aa123456", "qazwsx", "ninja", "azerty",
                "123123", "solo", "loveme", "whatever", "donald", "trustno1", "batman", "passw0rd", "zaq1zaq1",
                "qazwsx", "password1", "password1", "000000", "123qwe", "center"};

        for (int i = 0; i < passwordcheck.length; i++) {

            String strpasswordcheck = passwordcheck[i];

            Map<String, String> data = new HashMap<>();
            data.put("login", "super_admin");
            data.put("password", strpasswordcheck);

            Response response = RestAssured
                    .given()
                    .body(data)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();

            Thread.sleep(30);

            String responseCookie = response.getCookie("auth_cookie");
            Map<String, String> cookies = new HashMap<>();
            cookies.put("auth_cookie", responseCookie);

            Response responseForCheck = RestAssured
                    .given()
                    .body(data)
                    .cookies(cookies)
                    .when()
                    .post("https://playground.learnqa.ru/api/check_auth_cookie")
                    .andReturn();

            String resultForCheck = responseForCheck.asString();

            if (resultForCheck.equals(successResult)) {
                System.out.println("Correct password is: " + strpasswordcheck);
                System.out.println(resultForCheck);

            }
        }
    }
}