package eagleRockHub.stepDefinitions;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.hamcrest.Matchers.equalTo;
@RunWith(SerenityRunner.class)
@Concurrent(threads = "4X")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class eagleBotDefinitions {

	private static final String URL = "http://localhost:3000";
	public Response response;

	@Step("Search an EagleBot by id {0}")
	public void sendEagleBotID(int id) {
		response = SerenityRest.rest()
				.given()
					.contentType("application/json")
					.header("Content-Type", "application/json")
				.when()
					.get(URL + "/eaglebots/" + id)
				.then()
					.extract().response();
		String responseString = response.asString();
		JsonPath js= new JsonPath(responseString);
		String EagleBotId=js.get("id");
		System.out.println(EagleBotId);
	}
	@Step("Create a new EagleBot ")
	public void CreateEagleBot() throws IOException {
		String payload =new String(Files.readAllBytes(Paths.get("src/test/resources/apiRequestJson/createeaglebot.json")));
		response = SerenityRest.rest()
				.given()
					.contentType(ContentType.JSON)
					.header("Content-Type", "application/json")
					.body(payload)
				.when()
					.post(URL + "/eaglebots/" )
				.then()
					.extract()
					.response();
		String responseString = response.asString();
		JsonPath js= new JsonPath(responseString);
		String NewEagleBotId=js.get("id");
		System.out.println(NewEagleBotId);
	}
	@Step("Update an existing EagleBot")
	public void UpdateExistingEagleBot() throws IOException {
		String payload =new String(Files.readAllBytes(Paths.get("src/test/resources/apiRequestJson/updateeaglebot.json")));

		response = SerenityRest.rest()
				.given()
					.contentType("application/json")
					.header("Content-Type", "application/json")
					.body(payload)
				.when()
					.patch(URL + "/eaglebots/397" )
				.then()
					.extract()
					.response();
	}

	@Step("Create a duplicate EagleBot")
	public void CreateDuplicateEaglebotId() throws IOException {
		String payload =new String(Files.readAllBytes(Paths.get("src/test/resources/apiRequestJson/duplicateeaglebot.json")));

		response = SerenityRest.rest()
				.given()
					.contentType("application/json").header("Content-Type", "application/json")
					.body(payload)
				.when()
					.post(URL + "/eaglebots" )
				.then()
					.extract().response();
			}
	@Step("Create a Invlaid EagleBot")
	public void InvalidEaglebotId() throws IOException {

		response = SerenityRest.rest()
				.given()
					.contentType("application/json").header("Content-Type", "application/json")
				.when()
					.get(URL +"/eaglebot/1&_limit=-1" )
				.then()
					.extract().response();
	}

	@Step("Verify the status code 200")
	public void verifyStatusCode200(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}
	@Step("Verify the status code 201")
	public void verifyStatusCode201(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}
	@Step("Verify the status code 404")
	public void verifyStatusCode404(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}
	@Step("Verify the status code 400")
	public void verifyStatusCode400(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}
	@Step("Verify the status code 500")
	public void verifyStatusCode500(int expectedStatusCode) {
		SerenityRest.restAssuredThat(response -> response.statusCode(expectedStatusCode));
	}
	@Step("Verify the Content Type  ")
	public void verifyContentType(String expectedContentType) {
		SerenityRest.restAssuredThat(response -> response.contentType(expectedContentType));
	}
	@Step("Verify the eagleBot id ")
	public void verifyId(String EagleBotId) {
		SerenityRest.restAssuredThat(response -> response.body("id", equalTo(EagleBotId)));	}
//
	@Step("To verify the road name ")
	public void verifyRoadUnderInvestigation(String expectedRoadName) {
		SerenityRest.restAssuredThat(response -> response.body("road_name_under_investigation", equalTo(expectedRoadName)));
	}

	@Step("To verify the current address ")
	public void verifyCurrentAddress(String expectedCurrentAddress) {
		SerenityRest.restAssuredThat(response -> response.body("traffic_volume_current_location_address", equalTo(expectedCurrentAddress)));
	}
	@Step("To verify the rate of passing vehicle flow ")
	public void verifyVehicleFlow(String expectedVehicleFlow) {
		SerenityRest.restAssuredThat(response -> response.body("rate_of_passing_vehicle_flow", equalTo(expectedVehicleFlow)));
	}
	@Step("To verify vehicle volume by each direction of traffic ")
	public void verifyVehicleVolumedirection(String expectedVehicleVolume) {
		SerenityRest.restAssuredThat(response -> response.body("vehicle_volume_by_each_direction_of_traffic", equalTo(expectedVehicleVolume)));
	}
	@Step("To verify the message ")
	public void verifyMessage(String expectedMessage) {
		SerenityRest.restAssuredThat(response -> response.body("message", equalTo(expectedMessage)));
	}

}
