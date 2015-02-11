module.exports = {
  "Test Signup": function (browser) {
    browser
      .url("http://soile.fi:8080/login")
      .waitForElementVisible('body', 1000)
      .click("#showRegister")
      .waitForElementVisible('#username', 1000)
      .setValue('#username', 'test@user.test')
      .setValue('#register-password', 'testpass')
      .setValue('#register-password-again', 'testpass')
      .click('#btn-register')
      .waitForElementVisible('#userheader', 1000)
      .assert.containsText("#userheader h4", 'Welcome back test@user.test')
  },

  "Test Logout": function(browser) {
    browser
      .useXpath()
      .click("//button[contains(text(),'Logout')]")
      .useCss()
      .waitForElementVisible('body', 1000)
      .assert.containsText('h4', "Welcome to soile")
  },

  "Test user exists": function(browser) {
    browser
      .url("http://soile.fi:8080/login")
      .waitForElementVisible('body', 1000)
      .click("#showRegister")
      .waitForElementVisible('#username', 1000)
      .setValue('#username', 'test@user.test')
      .setValue('#register-password', 'testpass')
      .setValue('#register-password-again', 'testpass')
      .click('#btn-register')
      .assert.elementPresent("#login-alert")
      .end()
  },

  "Test Soile Landing": function (browser) {
    browser
      .url("http://soile.fi:8080/")
      .waitForElementVisible('body', 1000)
      .assert.containsText('h4', "Welcome to soile")
      .end()
  }
};