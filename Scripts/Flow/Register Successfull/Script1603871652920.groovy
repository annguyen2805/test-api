import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

response1 = WS.sendRequest(findTestObject('Request/REGISTER - SUCCESSFL', [('email') : email, ('password') : password]))

WS.verifyResponseStatusCode(response1, GlobalVariable.successCode)

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(response1.getResponseBodyContent())

def token = result.token

def id = result.id

println(token)

response2 = WS.sendRequest(findTestObject('Request/LOGIN - SUCCESSFULL', [('email') : email, ('pass') : password]))

WS.verifyResponseStatusCode(response2, GlobalVariable.successCode)

WS.verifyElementPropertyValue(response2, 'token', token)

response3 = WS.sendRequest(findTestObject('Request/SINGLE USER', [('id') : id]))

WS.verifyResponseStatusCode(response3, 200)

WS.verifyElementPropertyValue(response3, 'data.email', email)

response4 = WS.sendRequest(findTestObject('Request/DELETE', [('id') : id]))

WS.verifyResponseStatusCode(response4, 204)

