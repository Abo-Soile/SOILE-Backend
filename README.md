# Soile2 README

Soile is a webplatform for building and conducting pshycological test.

Tech used:
- ANTLR4 for parsing and building the tests;
- StringTemplate for building the forms
- Vertx.io links everything together and serves the website
- Html templates by dustjs

- Some javascript library to handle the client side stuff.


### Dev history in gitlog

## TODO:
- Authentification - vertx-mod-auth?
- Modify the form builder to ommit useless extra html tags, like <html>
- Figure out how to store stuff  - Mongodb - vertx mongo persistor?
- Define how an experiment should work.
