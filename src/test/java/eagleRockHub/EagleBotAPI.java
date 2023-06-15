package eagleRockHub;

import eagleRockHub.utils.ReusableMethods;
import eagleRockHub.utils.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@SuppressWarnings("ALL")
@RunWith(SerenityRunner.class)
@Concurrent(threads = "4X")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EagleBotAPI extends TestBase {
    Response res;
    String eagleBotId;
    public EnvironmentVariables environmentVariables;
    @Title("Test1 This Test will display all the existing EagleBots")
    @Test
    public void getAllExistingEaglebots()  {
        // EnvironmentVariables environmentVariables = Injectors.getInjector()
        //                .getInstance(EnvironmentVariables.class);
        // String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
        //         .getProperty("my.webservice.endpoint");

        Response res = SerenityRest.rest()
                .given()
                .when()
                    .get("/eaglebots")
                .then()
                    .log()
                    .all()
                    .extract()
                    .response();
        JsonPath js= ReusableMethods.rawToJson(res);
        Assert.assertEquals(200,res.getStatusCode());
        Assert.assertEquals("HTTP/1.1 200 OK",res.statusLine());
        Assert.assertEquals("application/json; charset=utf-8",res.contentType());
        int Total_EagleBots =js.get("eaglebots.size()");
        System.out.println("Total EagleBots in EagleRockHub "+Total_EagleBots);
    }




}
