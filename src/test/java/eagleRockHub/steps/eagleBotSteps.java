package eagleRockHub.steps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(SerenityRunner.class)
public class eagleBotSteps {

	@Steps
	eagleRockHub.stepDefinitions.eagleBotDefinitions eagleBotDefinitions;

	@Test
	@Title("Test2 This Test will create a new EagleBot with Payload file")
	public void CreateEagleBot() throws IOException {
		eagleBotDefinitions.CreateEagleBot();
		eagleBotDefinitions.verifyStatusCode201(201);
		eagleBotDefinitions.verifyContentType("application/json");
		eagleBotDefinitions.verifyId("1335");
		eagleBotDefinitions.verifyCurrentAddress("355 West");
	}

	@Test
	@Title("Test3 This Test will validate traffic condition of an Eaglebot at a given location")
	public void verifyValidEagleBot() {
		eagleBotDefinitions.sendEagleBotID(316);
		eagleBotDefinitions.verifyStatusCode200(200);
		eagleBotDefinitions.verifyContentType("application/json");
		eagleBotDefinitions.verifyId("316");
		eagleBotDefinitions.verifyCurrentAddress("125 East");
		eagleBotDefinitions.verifyRoadUnderInvestigation("24th St");
		eagleBotDefinitions.verifyVehicleFlow("700");
		eagleBotDefinitions.verifyVehicleVolumedirection("East Bound: 400 / West Bound: 300");
		eagleBotDefinitions.verifyMessage("Successfully! Record has been added.");
	}

	@Title("Test5 This Test will update/patch existing EagleBot data")
	@Test
	public void UpdateExistingEaglebotLocation() throws IOException {
		eagleBotDefinitions.UpdateExistingEagleBot();
		eagleBotDefinitions.verifyStatusCode200(200);
		eagleBotDefinitions.verifyContentType("application/json");
	}


	@Title("Test6 This Test will display invalid EagleBots-HTTP/1.1 with a 404 Not Found")
	@Test
	public void InvalidEagleBotId() throws IOException {
		eagleBotDefinitions.verifyStatusCode404(404);
	}
	@Title("Test7 This Test will display the bad request 400 Bad Request")
	@Test
	public void BadRequestEaglebotId() throws IOException {
		eagleBotDefinitions.InvalidEaglebotId();
		eagleBotDefinitions.verifyStatusCode404(404);
	}
	@Title("Test8 This Test to verify duplicate EagleBot")
	@Test
	public void CreateDuplicateEaglebotId() throws IOException {
		eagleBotDefinitions.CreateDuplicateEaglebotId();
		eagleBotDefinitions.verifyStatusCode500(500);
	}
}
