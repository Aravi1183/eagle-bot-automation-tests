# EagleBot API traffic conditions at a given location
## Introduction

This is a sample Rest API test solution for EagleBot API implemented on JSON Server fake/mock endpoints available in https://localhost:3000/. 
The published APIs represent a eagleBotAPI traffic conditions.

Tests are written using a combination of SerenityBDD, RestAssured, Junit & Maven.

## Framework & Design Considerations
- Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.
- API calls & validations are made using RestAssured and SerenityRest which is a wrapper on top of RestAssured.
- Tests are written in BDD Gherkin format, it is represented as a living documentation in the test report.
- These domain models are called from a step-definitions class which are in-turn called from BDD tests.Consist of steps class where API actions and assertions are defined/written.
- A test scenario to validate API response schema has been included and schema comparison is placed inside "apiRequestJson" folder in test resources. The specs are generated from https://www.liquid-technologies.com/online-json-to-schema-converter.

### The project directory structure

```Gherkin
src
  + test
    + java                          Test runners and supporting code
      + eagleRockHub                Domain model package consisting of all actions on eagleBotAPI traffic flow functionality
          +stepsDefinitions         Steps contain a group of resource manipulation operations. It can be an action, verification or a context related operation. The classic Given_When_Then format can be reflected in the steps.
          +steps                    Each test tells a simple user story, which is carried out using certain Step.
          +utils                    Common utility methods to provide the baseURI/endpoints.
      + EagleBotAPI                 This Class is to extend the framework when any environments will be available.
    + resources
      + schema                      Folder containing json schema for API schema validation
      Serenity.conf                 Configurations file

```
Pre-requisite:
1. Intellij/IDE
2. Java 1.8
3. JSON server 

## How to run this project

1. Clone the project
2. Traffic flow data has been created and stored in eaglebottrafficdata.json, Please refer this file under "src/test/resources/apiRequestJson/"
3. Install mock JSON server from https://www.npmjs.com/package/json-server.
4. Start the Json Server from command prompt "json-server --watch eaglebottrafficdata.json". 
5. Open the localhost at 3000 port "http://localhost:3000"
6. Hit following command to execute this project from command prompt:

``` 
mvn clean install 

The test results will be recorded here `target/site/serenity/index.html`.

open target/site/serenity/index.html 
```
Reports can be seen in  under the artifacts section `serenity/index.html`.


### Additional configurations

Additional command line parameters can be passed for switching the application environment.
```json
$ mvn clean verify -Denvironment=dev
```
Configurations to for different environments are set in the `test/resources/serenity.conf` file. In real time projects each environment can be configured with its baseurl to run the tests based on different environments.
```
environments {
  default {
    baseurl = "http://localhost:3000"
  }
  dev {
    baseurl = "http://localhost:3000"
  }
  staging {
    baseurl = "http://localhost:3000"
  }
}
```
