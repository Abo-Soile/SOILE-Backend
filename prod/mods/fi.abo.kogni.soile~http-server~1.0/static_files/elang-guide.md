# Elang Documentation

This document contains documentation for the experiment language.

## Structure



Val and gvar definitons

function definitons

phase definitions

phase transitions


## Variables
**gvar** Global variable, useable everywhere

**val** static global value, cannot be modified after definition

**var** Normal variable, visible only in the current function/phase.

A variable definition contains: "variabletype" "variable name" <- "value". Also note that variable definitions must be done in the beginning of the program (gvar and val) or in the beginning of a function/phase definition (var). 
	
	# Creates a variable named a and assigns it's value to the string "asdf"
	var a <- "asdf"

	# Changes a's value from "asdf" to "qwerty"
	a <- "qwerty"

## Phases and transitions

intermezzo-phase
information-phase
intraction-phase

	interaction-phase MainPhase

	  enterphase
	    helptext("enterphase")
	    reacted <- 0
	    setstimuli([randomnumber(2 3)])
	  end
	  
	  leavephase
	    helptext("Leaving phase")
	  end
	  
	  beforeiteration
	    helptext("Before stimuli iteration")
	  end
	  
	  afteriteration
	    helptext("After stimuli iteration")
	  end
	  
	  iteration
	    var i <- 0
	    var s <- 2
	    
	    s <- stimulus()
	  end
	end


## Builtin functions

Builtin functions are functions that are called and executed from a experiment by the user. 

### Display

Display positions are defined as the number of pixels from the top-left corner of the display. Positions are defined as a json object with two variables; top and left, .e.g. he position {top:50 left:100} is located 100 pixels to the right and 50 pixels below the topleft corner. 

**show(displayobject position)**
Displays an object at the specified position.

**hide(displayobject)**
Hides the specified object.


#### DisplayObjects

All displayobjects share the same functionality regarding showing and hidning them. And they must be defined and assigned to a varriable before being used. 

**msgbox(message, fontsize=20)**
Displayobject for displaying text at any location. Fontsize is an optional argument and it defaults to 20

**imagefile(imageurl)**
Object contianing a image specified by the url, so images can either be uploaded to the testeditor or fetched from the internet. Note that images on the net can disappear or change at any time.  The image is displayed without any scaling so make sure that the image is the right size. 

**rectangle(width height borderwidth=2)**
Displays a rectangle with the given width height and borderwidth. Borderwidth is an optional argument and defaults to 2. 


It's also possible to display simple text messages using showmsg(message) and hidemsg() without any further specifications. This just shows/hides a message at location {top:50 left:50}

**showmsg(message)**
Displays a message at the standard message location in the top right corner

**hidemsg()**
Hides the standard message.

### Input

#### Mouse

**onmouseclick(imagefile, {action: function, inputid: number, resettimer: bool})**

Creates a mouseclick listener for the object/image, which will be triggered when the image is clicked. The function specified in "action" is executed on each click. 

**onmouseclick(imagefile, false)**

Removes all mouseclick functions bound to the specified object.

####Keyboard

**onkeypress(key, func)**
Binds the specified key to a function so that the function is run every time when the key is pressed.

**onkeypress(key)**
Removes all actionss bound to the specified key.

	function leftclick()
		showmsg("left was clicked")
	end

	onkeypress("left", leftclick)
Example: the function leftclick is run each time when the left arrow is clicked on the keyboard.

**onanykey(func)**
Executes the specified function when any keyboeard key is clicked. 

**getlastkey()**
Returns the last key clicked by the user. 

### Arithmetic

#### Basic
**plus(number1 number2)** number1 + number2

**minus(number1 number2)** number1 - number2

**multiply(number1 number2)** number1 * number2

**divide(number1 number2)** number1 / number2

#### Logic
**not(boolean)**

**and(bool1 bool2)**

**or(bool1 bool2)**

**lessthan(number1 number2 _or_ lt(number1 number2))** number1 < number2

**greaterthan(number1 number2) _or_ gt(number1 number2)** number1 > number2

**equal(number1 number2) _or_ eq(number1 number2)** number1 == number2


### Functions

**append(string1 string2)**
Appends two strings

**lenght(object)**
Returns the number of elements/letters in a array or string

**elementatindex(object, index)**
Returns the element at the specified index in the object/string, starting from zero.



#### Random numbers

**randominteger(min, max)**
returns a pseudorandom non decimal number within the range.

**randomnumber(min, max)**
Returns a pseudorandom number value within the range

**seedrandom(seed)**
Seeds the random generator with  a value. A certain seed will always produce the same sequence of random values. 

#### Time and timers

**recordts()**
Returns a timestamp with the current time with m,illisecond precision. Timestamps can easily be compared with basic arithmetic operations.

**starttimer()**
Starts the timer.

**elapsedtime()**
Returns time elapsed, in ms, since the last call top starttimer. Returns 0 if no timer has been started. 

#### Result storage

Results are stored in two tables, raw data and aggregated data. The aggregated data will only produce one row per user while any number of rows can be used for raw data. 

**storeSingle(field data)**

Stores a single value with the specified fieldname in the aggregated datatable. 

**storeRow(string field data)**

Stores a value with the given fieldname in the current raw datarow. 

**newRow()**
Creates a new empty row to write raw data to.


##### Data processing and aggregation

Raw data can be processed to something more usable. An aggregation function loops through the whole raw data table and performs the specified function on every field that it finds. Rows that don't have any value in the specific field are simply omitted. Results are stored in the processed data table in the fi

**count(field)**

Counts how many rows contain the specific field.

**count(field, value)**

Counts how many rows contain a specific field with a specific value.

**average(field)**

Computes the average value from all rows containing this field.

	Not implemented yet
	median(field)
	sum(field)



## RT - Runtime functions

Rt functions operate within the vm and should not be called by users. They are only used to manage and run the vm.