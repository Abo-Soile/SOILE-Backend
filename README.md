# Soile2 README

Soile is a webplatform for building and conducting pshycological test.

Tech used:
- ANTLR4 for parsing and building the tests;
- StringTemplate for building the forms
- Vertx.io links everything together and serves the website
- Html templates by dustjs
- Mongodb

- Some javascript library to handle the client side stuff, dojo, underscore.


### Install instructions
Dependencies:
- Java version 7 or greater.
- Vertx 2.0, should be added top path.
- Mongodb, newest version.

## Mongo Collections and Fields

#### User:
	- username
	- password (hashed)
	- name
	- address1
	- city
	- postalcode
	- country

#### Experiment: 
	- Name
	- Start Date
	- Stop Date
	- Description
	- Components [{_id, name, type}]
	- loginrequired

#### Tests:
	- _id
	- code
	- compiled (true/false)
	- js 		#Compiled js
	- name 

#### Form: 
	- _id
	- form 		# Compiled form
	- markup	# Markup code

####Test/Form Data:
	- _id
	- phase
	- expId
	- userid
	- confirmed
	- data {collection}
	- type



### Dev history in gitlog

