package banks

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import util.Constants._

class BanksSimulation extends Simulation {

  val httpProtocol = http.baseUrl(banksUrl)
    .acceptCharsetHeader(charsetHeader)
    .header(contentType, applicationJson)

  val allBanks = scenario("Get All Banks")
    .exec(http("Get All Banks")
      .get("")
      .check(status.is(200))
      .check(jsonPath("$[0].id").saveAs("id")))

  val bankById = scenario("Get All Banks")
    .exec(http("Get All Banks")
      .get("/${id}")
      .check(status.is(200)))

  val getAllAddresses: ScenarioBuilder = scenario("allBanks").group("allBanks") {
    exec(allBanks)
      .exec(bankById)
  }

  setUp(getAllAddresses.inject(atOnceUsers(1)).protocols(httpProtocol))

  private def createContractAddressScenario(requestName: String, contractId: String, path: String, userName: String): ScenarioBuilder = {
    val finalPath = "/addresses/contractId/".replace("contractId", contractId).concat("/").concat(path)
    scenario(requestName)
      .exec(http(requestName)
        .get(finalPath)
        .header(xMipAuthenticatedUser, userName)
        .check(status.is(200))
        .check(bodyString.saveAs("response")))
  }

}
