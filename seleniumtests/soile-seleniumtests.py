import unittest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

import selenium.webdriver.support.ui as ui

from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC

class SoileBaseTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        #driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());

        self.wait = ui.WebDriverWait(self.driver, 10) 

    def tearDown(self):
        self.driver.close()

    def waitFor(self, command, element):
        comm = lambda x: command(element)
        elem = self.wait.until(comm)

        return elem

    def loginAdmin(self):
        self.driver.get("http://soile.fi:8080/login")
        loginName = self.driver.find_element_by_id("login-username")
        loginPass = self.driver.find_element_by_id("login-password")

        loginName.send_keys("admin")
        loginPass.send_keys("admin")
        loginPass.send_keys(Keys.RETURN)

class SoileAdminTest(SoileBaseTest):

#    def setUp(self):
#        self.driver = webdriver.Firefox()
        #driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());

#        self.wait = ui.WebDriverWait(self.driver, 5)

    def atestAdminLogin(self):
        self.driver.get("http://soile.fi:8080")

        loginButton = self.driver.find_element_by_link_text("Login")
        loginButton.click()


        loginName = self.driver.find_element_by_id("login-username")
        loginPass = self.driver.find_element_by_id("login-password")

        loginName.send_keys("admin")
        loginPass.send_keys("admin")
        loginPass.send_keys(Keys.RETURN)

        #self.wait.until(lambda driver: self.driver.find_element_by_id("toolButtons"))
        a = self.waitFor(self.driver.find_element_by_id, "toolButtons")
        
        print "-------------"
        print a.text
        print "\n"
        #try:
        #    self.driver.find_element_by_id("toolButtons")
        #except:
        #    self.fail("Login Failed")
        #tools = self.driver.find_element_by_id("toolButtons")
        #print tools.text

    def atestWrongLogin(self):
        errorMessage = "Wrong username or password"

        self.driver.get("http://soile.fi:8080/login")

        loginName = self.driver.find_element_by_id("login-username")
        loginPass = self.driver.find_element_by_id("login-password")

        loginName.send_keys("admin")
        loginPass.send_keys("sdfs")
        loginPass.send_keys(Keys.RETURN)


        #loginAlert = self.driver.find_element_by_id("login-alert")
        #loginAlert = self.wait.until(lambda driver: self.driver.find_element_by_id("login-alert"))
        loginAlert = self.waitFor(self.driver.find_element_by_id, "login-alert")

        self.assertEqual(loginAlert.text, errorMessage)

    def testTestCreation(self):
        from random import randint

        self.loginAdmin()

        testName = self.waitFor(self.driver.find_element_by_name, "name")
        testName.send_keys("Selenium" + str(randint(0,99999999)))
        testName.send_keys(Keys.RETURN)

        #At testView
        compileButton = self.waitFor(self.driver.find_element_by_id, "compileButton_label")
        compileButton.click()

        errorBox = self.waitFor(self.driver.find_element_by_id, "errorbox")
        self.assertNotEqual(errorBox.text, "")

        editor = self.waitFor(self.driver.find_element_by_css_selector, "#editor textarea")
        editor.send_keys("#This is a comment")

        editor.send_keys("""
intermezzo-phase Alpha
  helptext("Starting Test")
  wait(150)
end

# Second phase, shows some text for the user
intermezzo-phase Info
  helptext("Hiding instructions")
end

# This is the last phase, calculating and storing results
intermezzo-phase FinalPhase
  helptext("Final phase, storing results")
  count("reacted" 1)
  count("reacted" 0)
  average("rt")
end

# Tranition table
transition
  start(Alpha),
  Alpha -> Info,
  Info -> FinalPhase,
  final(FinalPhase)
end""")

        compileButton = self.waitFor(self.driver.find_element_by_id, "compileButton_label")
        compileButton.click()

        self.assertEqual(errorBox.text, "")        

        runButton = self.waitFor(self.driver.find_element_by_id, "runButton_label")
        runButton.click()

        ended = self.wait.until(
                EC.text_to_be_present_in_element((By.ID, 'message'), "END")
            )
        print ended
        self.assertTrue(ended)

        logElement = self.driver.find_element_by_id("log")
        print logElement.text


#    def tearDown(self):
#        self.driver.close()

if __name__ == "__main__":
    unittest.main()