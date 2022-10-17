package util

object Constants {
  def baseUrl = "http://localhost:8888"
  def adminUrl = baseUrl + "/admin"
  def banksUrl = baseUrl + "/banks"
  def crmUrl = baseUrl + "/crm"
  def casesUrl = crmUrl + "/cases"
  def charsetHeader = "*/*"
  def contentType = "Content-Type"
  def applicationJson = "application/json"
  def error = "ERROR"
  def notificationZeroMessage = "$.notifications[0].message"
  def notificationZeroPath = "$.notifications[0].path"
  def notificationZeroSeverity = "$.notifications[0].severity"
  def payloadZero = "$.payload[0]"
  def payloadOne = "$.payload[1]"
  def payloadTwo = "$.payload[2]"
  def xChannel = "X-CHANNEL"
  def xMipAuthenticatedUser = "X-MIP-Authenticated-User"
  def xRequestSource = "X-Request-Source"
  def test = "test"
  def OK = 200
  def INTERNAL_SERVER_ERROR = 500
}
