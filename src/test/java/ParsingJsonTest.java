import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

public class ParsingJsonTest {
    @Test
    public void getJsonTest(){

        JsonPath response = RestAssured
                .given()
                .when()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        System.out.println("All Timestamps: " + response.get("messages.timestamp"));
        System.out.println("Second Timestamp :" + response.get("messages.timestamp[1]"));
    }
}