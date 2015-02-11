exports.command = function(browser) {
  browser
    .url("http://soile.fi:8080/login")
    .waitForElementVisible('body', 1000)
    //.setValue('input#login-username', 'admin')
    .setValue('input#login-username', browser.globals.adminuser)
    .setValue('input#login-password', browser.globals.adminpass)
    .click('#btn-login')
    .waitForElementVisible('.navbar-text', 10000)
    .assert.containsText('nav .navbar-text', 'Signed in as admin')
  return browser
  //return this; // allows the command to be chained.
};
