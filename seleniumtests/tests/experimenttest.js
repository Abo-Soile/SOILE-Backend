module.exports = {
  "Test experiment creation": function(browser) {
    browser
      .login(browser)
      .useXpath()
      .click("//a[contains(text(),'New Experiment')]")
      .useCss()
      .waitForElementVisible("#widget_testSelector", 10000)
      .setValue("#name", browser.globals.expname)
      .setValue("#description", "Nightwatch experiment description")
      .click("#submit_label")
      .pause(1000)
      .waitForElementVisible("#widget_testSelector", 3000)
      .assert.value("#name", browser.globals.expname)
  },
  "Test add experiment": function(browser) {
    browser
      //.waitForElementVisible("#testSelector", 3000)
      .waitForElementVisible("#widget_testSelector", 3000)
      .setValue("#testSelector", browser.globals.testname)
      .click("#newtest")
      .waitForElementVisible("#componentlist li", 3000)
      .assert.value("#componentlist .dijitTextBox input", browser.globals.testname)
      
  },
    "Test add-delete form": function(browser) {
    browser
      .click("#newform")
      .waitForElementVisible("#componentlist li:nth-child(2)", 3000)
      .assert.elementPresent("#componentlist li:nth-child(2) div")
      .click("#componentlist li:nth-child(2) .btn-danger")
      .waitForElementNotPresent('#componentlist li:nth-child(2)', 1000);
  },
    "Test edit form": function(browser) {
    browser
      .click("#newform")
      .waitForElementVisible("#componentlist li:nth-child(2)", 3000)
      .click("#componentlist li:nth-child(2) .dijitButton:nth-child(2) ")
      .pause(1000)
      .frame(2)
      .waitForElementPresent("#editor", 6000)
      .setValue("#editor textarea", "/title\nTesttitle")
      .waitForElementPresent("#renderform_label", 3000)
      .assert.visible("#renderform")
      .click("#renderform")
      .pause(2000)
      .assert.elementPresent("#formcol div h2", "H2 present")
      .assert.containsText("#formcol div h2", "Testtitle")
      .end()
  }

};