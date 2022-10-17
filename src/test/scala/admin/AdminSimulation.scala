package admin

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import util.Constants.{adminUrl, applicationJson, charsetHeader, contentType}

import scala.util.Random

class AdminSimulation extends Simulation {

  val httpProdocol = http.baseUrl(adminUrl)
    .acceptCharsetHeader(charsetHeader)
    .header(contentType, applicationJson)

  val createBank = scenario("createBank")
    .exec(http("createBank")
      .post("")
      .body(RawFileBody("banks/bank.json")).asJson
      .check(status.is(201))
      .check(jsonPath("$.id").saveAs("id")))
  val createdBank = scenario("createdBank")
    .exec(http("allAdminOperations")
      .get("/${id}")
      .check(status.is(200)))

  val allAdmin: ScenarioBuilder = scenario("allAdmin").group("allAdmin") {
    exec(createBank)
      .exec(createdBank)
  }

  setUp(allAdmin.inject(atOnceUsers(1)).protocols(httpProdocol))

}
