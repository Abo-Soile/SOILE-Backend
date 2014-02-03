var vertx = require('vertx');

vertx.createHttpServer().requestHandler(function(req) {
  //var Blowfish = Packages.fi.abo.kogni.soile2.utils.cryptography.Blowfish;
  var Blowfish = Packages['fi.abo.kogni.soile2.utils.cryptography.Blowfish'];
  var key = "vr7DlZqAyY061Y9M";
  var msg = 'O7dqEg5/elH9hcCLKYTvO7u+bclfe5zzIClPTePbwlQ=';

  req.response.end(Blowfish.decrypt(msg, key));
}).listen(8080)
