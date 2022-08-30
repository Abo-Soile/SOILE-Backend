module.exports = {
  "Test Admin Login" : function (browser) {
    browser
      .url("http://soile.fi:8080/login")
      .waitForElementVisible('body', 1000)
      //.setValue('input#login-username', 'admin')
      .setValue('input#login-username', browser.globals.adminuser)
      .setValue('input#login-password', browser.globals.adminpass)
      .click('#btn-login')
      .waitForElementVisible('.navbar-text', 10000)
      .assert.containsText('nav .navbar-text', 'Signed in as admin')
  },
    "Test Admin Logout" : function (browser) {
    browser
      .useXpath()
      .click("//button[contains(text(),'Logout')]")
      .useCss()
      .waitForElementVisible('body', 1000)
      .assert.containsText('h4', "Welcome to soile")
      .end()
  }
};