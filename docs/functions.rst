==================
Function Reference
==================

Elang comes with a set of built in functions for performing common tasks. These functions are pretty much necessary when building tests so it's usefull to be familiar with them. 

#######
Stimuli
#######

Stimuli is a special list that defines how many times a 

**setstimuli(list)**
Sets the stimuli to the given list. Overwrites previously defined stimuli.

**shufflestimuli()**
Randomly shuffles the order of the stimuli.

**shufflestimuli(stimulicount)**
Creates a randomized suset of the stimuli containing the number specified in _stimulicount_.

#######
Display
#######

Display positions are defined as the number of pixels from the top-left corner of the display. Positions are defined as a json object with two variables; top and left, .e.g. the position {top:50 left:100} is located 100 pixels to the right and 50 pixels below the topleft corner. 

**show(displayobject position)**
Displays an object at the specified position.

**show(displayobject "center")**
Displays the object at the absolute center of the screen. 

**hide(displayobject)**
Hides the specified object.


DisplayObjects
==============

All displayobjects share the same functionality regarding showing and hiding them. And they must be defined and assigned to a variable before being used. 

**msgbox(message, fontsize=20)**
Displayobject for displaying text at any location. Fontsize is an optional argument and it defaults to 20

**imagefile(imageurl)**
Object containing a image specified by the url, so images can either be uploaded to the testeditor or fetched from the internet. Note that images on the net can disappear or change at any time.  The image is displayed without any scaling so make sure that the image is the right size. 

**rectangle(width height borderwidth=2)**
Displays a rectangle with the given width height and borderwidth. Borderwidth is an optional argument and defaults to 2. 


It's also possible to display simple text messages using showmsg(message) and hidemsg() without any further specifications. This just shows/hides a message at location {top:50 left:50}

**showmsg(message)**
Displays a message at the standard message location in the top right corner

**hidemsg()**
Hides the standard message.

###########
Mouse input
###########

**onmouseclick(imagefile, {action: function, inputid: number, resettimer: bool})**

Creates a mouseclick listener for the object/image, which will be triggered when the image is clicked. The function specified in "action" is executed on each click. 

**onmouseclick(imagefile, false)**

Removes all mouseclick functions bound to the specified object.

##############
Keyboard Input
##############

**onkeypress(key, func)**

Binds the specified key to a function so that the function is run every time when the key is pressed.

**onkeypress(key)**

Removes all actionss bound to the specified key.

::

    function leftclick()
        showmsg("left was clicked")
    end

    onkeypress("left", leftclick)

Example: the function leftclick is run each time when the left arrow is clicked on the keyboard.

**onanykey(func ignore=[])**

Runs the specified function on all keypresses.

**onanykey(func ignore="")**

Executes the specified function when any keyboeard key except keys specified ignore are pressed. Ignored keys should be sent as a list of individual keynames, for example ["a" "enter"] ignores the keys **a** and **enter**, see the keycode table for the correct key names. 


Ignore can also accept a special command "onlyletters" which ignores everything but a-z.
    onanykey(resume() "onlyletters").

**onanykey()**

Removes all actions bound with __onanykey__

**resumeonkey(keycode)**

Runs resume() once when the specified key is clicked. 

**resumeonkey()**

Runs resume() on any keypress once. 

**getlastkey()**

Returns the most recent keypress as a string.

KeyCodes
========
Keys are specified using keycodes where keycode corrsepsonds to a certain key, keycodes should be defined as strings e.g. ("a"). Alphanumerical keys(a-ö 0-9) simply uses the keys letter, so the keycode "a" corresponds to the button a key on the keyboard. All other keycodes are defined in the following table.

+-----------+------------------------+
|"backspace"| Backspace              |       
+-----------+------------------------+
| "tab"     |  Tab                   |   
+-----------+------------------------+
| "enter"   |  Enter/Return          |           
+-----------+------------------------+
| "shift"   |  Left and Right shift  |                   
+-----------+------------------------+
| "ctrl"    |  Left and right control|                   
+-----------+------------------------+
| "alt"     |  Alt                   |   
+-----------+------------------------+
| "capslock"|  Capslock              |       
+-----------+------------------------+
| "escape"  |  Esc                   |   
+-----------+------------------------+
| "pageup"  |  Page Up               |       
+-----------+------------------------+
| "pagedown"|  Page Down             |       
+-----------+------------------------+
| "end"     |  End                   |   
+-----------+------------------------+
| "home"    |  Home                  |   
+-----------+------------------------+
| "insert"  |  Insert                |   
+-----------+------------------------+
| "delete"  |  Delete                |   
+-----------+------------------------+
| "left"    |  Arrow Left            |       
+-----------+------------------------+
| "up"      |  Arrow Up              |       
+-----------+------------------------+
| "right"   |  Arrow Right           |           
+-----------+------------------------+
| "down"    |  Arrow Down            |       
+-----------+------------------------+

##########
Arithmetic
##########

Basic arithmetic operations that takes on two or more numbers as arguments, so plus(5 5 5 5) is equivalent with 5 + 5 + 5 + 5. Note that the inner function is evaluated before the outer one when performing mulitple nested operations so **multiply(2 plus(5 5)) = 2 * (5+5)** while **plus(2 multiply(5 5)) = 2 + (5*5)**.

**plus(number1 number2)** = number1 + number2

**minus(number1 number2)** = number1 - number2

**multiply(number1 number2)** = number1 * number2

**divide(number1 number2)** = number1 / number2

**modulo(number1 number2)** = number1 % number2 
Calculates the remainder when dividing number1 with number2 (number1/number2)

**round(number)** Rounds the number to the nearest whole number

**round(number mode)** Round a number down when mode = "floor" and up when mode = "ceil"

#####
Logic
#####

Basic logic functions that return a boolean value(true or false)

**not(boolean)**  Logic NOT

**and(bool1 bool2)** Logic AND

**or(bool1 bool2)** logic OR

**lessthan(number1 number2 _or_ lt(number1 number2))** number1 < number2

**greaterthan(number1 number2) _or_ gt(number1 number2)** number1 > number2

**equal(number1 number2) _or_ eq(number1 number2)** number1 == number2

#########################
Lists/String Manipulation
##########################

**append(string1 string2)**

Appends two strings, append("Hello " "World") = "Hello World"

**lenght(object)**

Returns the number of elements/letters in  list or string including whitespaces. 
**elementatindex(object, index)**

Returns the element at the specified index a list/string where the index starts from 0.

::

    elementatindex("Hello" 0) #= "H"
    elementatindex("Test" 3) #= t

##############
Random numbers
##############

**randominteger(min, max)**

returns a pseudorandom non decimal number within the range.

**randomnumber(min, max)**

Returns a pseudorandom number value within the range

**seedrandom(seed)**

Seeds the random generator with  a value. A certain seed will always produce the same sequence of random values. 

###############
Time and timers
###############

Time is measured in milliseconds with a precision of +-2 milliseconds in most cases. Time is measured in Epoch time, i.e. the number of milliseconds since 00:00:00 1.1.1970.

**recordts()**

Returns a timestamp with the current time with millisecond precision. 

**starttimer()**

Starts the timer.

**elapsedtime()**

Returns elapsed time, in ms, since the last call to starttimer. Returns 0 if no timer has been started. 

##############
Result storage
##############

Results are stored as .csv spredsheet files that can easily be imported into Excel or any other spreadsheet software. Data can be stored in two different files:
-   Aggregate data from the whole experiment, can only contain one row per user. 
-   "Raw data", test specific data, so each test (in the same experiment) writes to a separate file. Allows for multiple rows per user. It's possible to compute e.g. an average over all rows and store this value in the aggregate datatable. 

**storeSingle(field data)**

Stores a single value with the specified fieldname in the aggregated datatable.

**storeRow(string field data)**

Stores a value with the given fieldname in the current raw datarow. 

**newRow()**

Creates a new empty row to write raw data to.

The example produces the following result:

###############################
Data processing and aggregation
###############################

Raw data can be processed to something more usable. An aggregation function loops through the whole raw data table and performs the specified function on every field that it finds. Rows that don't have any value in the specific field are simply omitted. Results are stored in the processed data table in the fi

**count(field)**

Counts how many rows contain the specific field.

**count(field, value)**

Counts how many rows contain a specific field with a specific value.

**average(field)**

Computes the average value from all rows containing this field.

**median(field)**

Computes the median from all rows containing this field.

**standarddeviation(field)**

Computes the standard deviation from all rows containing ths field.

::

    #Example
    
    #Adding values to first row
    storeRow("Row number" 1)
    storeRow("TestValue" 5)
    storeRow("Message" "Hello")

    newRow()
    storeRow("Row number" 2)
    storeRow("TestValue" 5)
    storeRow("Message" "Hello")

    newRow()
    storeRow("Row number 3")
    storeRow("TestValue" 14)
    storeRow("Message" "Goodbye")
    storeRow("ExtraValue" "Extra")

    count("RowNumber")       # How many rows in coloumn "RowNumber" contain a value
    count("ExtraValue")      # How many rows in coloumn "ExtraValue" contain a value
    count("Message" "Hello") # How many rows in coloumn "Message" contain the value "Hello"
    average("TestValue")     # Avarage of all values in the TestValue coloumn

    storeSingle("Single Value" 1234567)

**Raw Data** 

+------------+-----------+----------+------------+
| Row Number | TestValue | Message  | ExtraValue |
+============+===========+==========+============+
|     1      |     5     | "Hello"  |            |
+------------+-----------+----------+------------+
|     2      |     5     | "Hello"  |            |
+------------+-----------+----------+------------+
|     3      |     14    | "Goodbye"|   "Extra"  |
+------------+-----------+----------+------------+

**Aggregate Data**

+-----------------+------------------+---------------------+------------------+--------------+
| count_RowNumber | count_ExtraValue | count_Message_Hello | average_TestValue| Single Value |
+=================+==================+=====================+==================+==============+
|       3         |        1         |          2          |       8          |   1234567    |
+-----------------+------------------+---------------------+------------------+--------------+


Not implemented yet
median(field)
sum(field)