package eagleRockHub.utils;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost:3000";
	}

}