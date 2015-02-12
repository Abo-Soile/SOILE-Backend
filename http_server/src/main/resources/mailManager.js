var vertx = require("vertx");
var console = require('vertx/console');

var verticleAddress = "soile.my_mailer";

var defaultFrom = "noreply@kogni.abo.fi"

var mailManager = {
    sendMail:function(to, subject, body, response) {
        var mail = {
            "to": to,
            "from": defaultFrom,
            "subject": subject,
            "body": body
        };

        vertx.eventBus.send(verticleAddress, mail, response);
    },

    passwordReset:function(to, resetUrl,response) {
        var body = "\
Hello \n  \
You or someone specifying your email account, has requested to \
have their password reset for Soile.\n\n \
To reset the password, click the link below:: \n " + 
resetUrl + 
"\n\n Soile Team";

        var subject = "Soile Password reset";

        this.sendMail(to, subject, body, response);
    }
}

module.exports = mailManager;