var console = require('vertx/console');


function requireAdmin(request, callback) {
  console.log("CHECKING FOR ADMIN MIDDLEWARE" + request)
  if (!request.session.isAdmin()) {
    request.unauthorized();
  }else {
    callback(request);
    return;
  }
}

function testMiddleware1(request, callback) {
  console.log("TEST MIDDLEWARE 1");
  request.test1 = "TEST1";
  callback(request);
}

function testMiddleware2(request, callback) {
  request.test2 = "TEST2"
  callback(request);
}

module.exports.requireAdmin = requireAdmin;
module.exports.m1 = testMiddleware1;
module.exports.m2 = testMiddleware2;