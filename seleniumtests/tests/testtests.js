module.exports = {
  "Test test creation": function(browser) {
    browser
      .login(browser)
      .setValue("form  .input-group input", browser.globals.testname)
      .click("form .input-group .input-group-btn .btn")
      .waitForElementVisible(".ace_editor", 5000)
      .assert.elementPresent(".ace_editor")
      .assert.elementPresent("#uploader")
  },

  "Test test compilation":function(browser) {
    var fs = require('fs');
    var file = fs.readFileSync("resources/test.elang", 'utf8');

    browser
      .click("#compileButton_label")
      .waitForElementVisible("#errorbox", 3000)
//      .assert.containsText("#errorbox:nth-child(2)", "Some transition rules contain undefined phase names.")
      .setValue("#editor textarea", file)
      .click("#compileButton_label")
      .waitForElementNotVisible("#errorbox", 2000 )

      .click("#runButton")
      .waitForElementVisible("#message", 3000)
      .assert.containsText("#message", "END")
      .assert.elementPresent("#rawTable tbody tr", "Raw data visible")
      .assert.elementPresent("#dataTable tbody tr", "Stored data visible")
      .end()
  }
};