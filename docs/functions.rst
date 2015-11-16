==================
Function Reference
==================

.. default-domain:: js

Elang comes with a set of built in functions for performing common tasks. These functions are pretty much necessary when building tests so it's usefull to be familiar with them. 

#######
Stimuli
#######

Stimuli is a special list that controls the interaction phase. The iteration in an interaction phase is run once for each element in the stimuli list. 

.. js:function:: setstimuli(list)
    
    Sets the stimuli to the given list. Overwrites previously defined stimuli.

    :param array list: List of stimuli objects to set as the stimuli. 

.. js:function:: shufflestimuli()
    
    Randomly shuffles the order of the stimuli.

.. js:function:: shufflestimuli(stimulicount)

    Creates a randomized subset of the stimuli containing the number specified in ``stimulicount``.

    :param int stimulicount: The number of stimuli to select.

.. js:function:: emptystimuli()
    
    Empties the stimuli list, and ends an interaction phase when the current iteration ends when called from inside an interaction phase.

#######
Display
#######

Display positions are defined as the number of pixels from the top-left corner of the display. Positions are defined as a json object with two variables; top and left, .e.g. the position {top:50 left:100} is located 100 pixels to the right and 50 pixels below the topleft corner. 

.. js:function:: show(item position)
    
    Displays an object at the specified position.

    :param displayobject item: The object that should be shown.
    :param position position: Where to displat the object, as an object e.g. {top:100 left:200}

.. js:function:: show(item "center")
    
    Displays the object at the absolute center of the screen.

    :param displayobject item: The object that should be shown.

.. js:function:: hide(item)
    
    Hides the specified object.

    :param displayobject item: The object that should be hidden.


DisplayObjects
==============

All displayobjects share the same functionality regarding showing and hiding them. And they must be defined and assigned to a variable before being used. 

.. js:function:: msgbox(message, fontsize=20)

    Displayobject for displaying text at any location. Fontsize is an optional argument and it defaults to 20

    :param string message: The text to display in the messagebox.
    :param int fontsize: Fontsize, defaults to 20.
    :returns: Displayobject

.. js:function:: imagefile(imageurl) 

    Object containing a image specified by the url, so images can either be uploaded to the testeditor or fetched from the internet. Note that images on the net can disappear or change at any time.  The image is displayed without any scaling so make sure that the image is the right size.

    :param string imageurl: Absoulute or relative url to the image. 
    :returns: Displayobject


**rectangle(width height borderwidth=2)**
Displays a rectangle with the given width height and borderwidth. Borderwidth is an optional argument and defaults to 2. 

.. js:function:: rectangle(width height borderwidth=2)

    Displays a rectangle with a white background and black border using the given width height and borderwidth.

    :param int width: Width of the rectangle.
    :param int height: Height if the rectangle.
    :param int borderwidth: Defines the borderwidth in pixels.
    :returns: Displayobject

.. js:function:: countdownbar(width time)
    
    Displays a fully filled countdown bar with the specified width and time(ms).The countdown animation is started by calling animate(countdownbar)

    :param int width: Width of the bar.
    :param int time: Define how long it takse for the bar to reach the end in milliseconds.
    :returns: Displayobject


It's also possible to display simple text messages using showmsg(message) and hidemsg() without any further specifications. This just shows/hides a message at location {top:50 left:50}

.. js:function:: showmsg(message)

    Displays a message at the standard message location in the top right corner, using the standard size and a standard margin.

    :param string message: The message to display.

.. js:function:: hidemsg()

    Hides the standard message.

###########
Mouse input
###########

.. js:function:: onmouseclick(object {action:function inputid: number})**

    Creates a mouseclick listener for the object/image, which will be triggered when the image is clicked. The function specified in "action" is executed on each click. 

    :param object displayobject: Displayobject that should respond to clicks.
    :param function action: Function to call when a click is made. Can be either a builtin function or one defined in the test.
    :param int inputid: Assign a number that will be passed to the function when the object is clicked.

    ::

        function boxClick(id)
            showmsg(append("Clicked box" id))
        end

        var box <- rectangle(50 50)
        show(box "center")
        onmouseclick(box, {action:boxClick inputid:55)
        #Clicking the box show a messag containing -Clicked box 55-

.. js:function:: onmouseclick(imagefile false)

    Removes all mouseclick functions bound to the specified object.

    :param object displayobject: Displayobject that should not respond to click any more.

##############
Keyboard Input
##############

.. function:: onkeypress(key, func)

    Binds the specified key to a function so that the function is run every time when the key is pressed.

    :param string key: Which keyboard key to use.
    :param function func: Function to call when a click is made

    Example: the function leftclick is run each time when the left arrow is clicked on the keyboard.

    ::

        function leftclick()
            showmsg("left was clicked")
        end

        onkeypress("left", leftclick)


.. function:: onkeypress(key)

    Removes all actionss bound to the specified key.

    :param string key: Which keyboard key to use.



.. function:: onanykey(func ignore=[])

    Executes the specified function when any keyboeard key except keys specified ignore are pressed. Ignored keys should be sent as a list of individual keynames, for example ["a" "enter"] ignores the keys **a** and **enter**, see the keycode table for the correct key names. 

    :param function func: Which function to call.
    :param list ignore: A list of keys to ignore
    :param string ignore: A specific ignore command

    Supported ignore commands:

    * "onlyletters"  - ignores everything but a-z.


.. function:: onanykey()

    Removes all actions bound with :js:func:`onanykey`

.. function:: resumeonkey(keycode)
    
    Runs resume() once when the specified key is pressed. 

    :param string keycode: Key to resume on 


.. function:: resumeonkey()

    Runs resume() on any keypress once. 

    :returns:  keycode

.. function:: getlastkey()

    Returns the most recent keypress, as long as there an active :js:func:`onkeypress` , :js:func:`onanykey` or :js:func:`resumeonkey`.

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

.. function:: plus(number1 number2 numbers*)
    
    Adds together all the given numbers number1 + number2 + ... numberX

    :param number number1: Number1
    :param number number2: Number2 
    :param number numbers: Number3, and so on...

    :returns: Result
    :rtype: number

.. function:: minus(number1 number2)
    
    Calculates number1 - number2

    :param number number1: Number to subtract from
    :param number number2: Number to subtract

    :returns: integer Result
    :rtype: number

.. function:: multiply(number1 number2 numbers*) 

    Calculates number1 * number2 ... numberX

    :param number number1: Number1
    :param number number2: Number2 
    :param number numbers: Number3, and so on...

    :returns: Result
    :rtype: number

.. function:: divide(number1 number2)

    Calculates number1 / number2

    :returns: Number
    :rtype: number

.. function:: modulo(number1 number2) = number1 % number2 
    
    Calculates the remainder when dividing number1 with number2 (number1/number2)

    :param number number1: First number
    :param number number2: Second number

    ::

        var a <- modulo(5 9)  # a = 4
        var b <- module(8 64) # b = 0
        var c <- module(8 45) # c = 4

    :returns: result
    :rtype: number

.. function:: round(number) 

    Rounds the number to the nearest whole number

    :param number number: Number to round

    :returns: Number

.. function:: round(number mode) 

    Round a number down when mode = "floor" and up when mode = "ceil"

    :param number number: Number to round
    :param string mode: "floor" to round down or "ceil" to round up 

    :returns: Number

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
#########################

.. function:: append(string1 string2)

    Appends two strings, append("Hello " "World") = "Hello World"

.. function:: lenght(object)

    Returns the number of elements/letters in  list or string including whitespaces. 

    :param object array/string: 

.. function:: elementatindex(object, index)

    Returns the element at the specified index a list/string where the index starts from 0. Equivalent to  object[index]

    :param object array: Object to select an element from.
    :param index number: Index

    ::

        elementatindex("Hello" 0) #= "H"
        elementatindex("Test" 3) #= t


.. function:: range(word start end) 

    Round a number down when mode = "floor" and up when mode = "ceil"

    :param word string/array: The string or array to select a range from
    :param start number: Index at which to begin selection. Start can be omitted in which case the selections begins from the first index i.e. 0.
    :param end number: Index at which to end the selection, values up to but not including end are selected. 

    :returns: Range


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

Raw data can be processed to something more usable. An aggregation function loops through the whole raw data table and performs the specified function on every field that it finds. Rows that don't have any value in the specific field are simply omitted. Aggregation functions can also be used to aggregate data in lists.

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

**outliers(field multiplier)**
Removes values that deviate more than multiplier*standarddefinition from the average value.

**outliers(field multiplier standarddeviation average)**
You can also provid your own standarddeviation and average for example when computing outliers of a subset of you data when you still want to use for example the whole dataset for average and standarddeviation.

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

    storesingle("countrows" count("RowNumber"))       # How many rows in coloumn "RowNumber" contain a value
    storesingle("countExtra" count("ExtraValue"))      # How many rows in coloumn "ExtraValue" contain a value
    storesingle("countHello" count("Message" "Hello")) # How many rows in coloumn "Message" contain the value "Hello"
    storesingle("average" average("TestValue"))     # Avarage of all values in the TestValue coloumn

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

+-----------+------------+------------+--------+--------------+
| countrows | countExtra | countHello | average| Single Value |
+===========+============+============+========+==============+
|     3     |     1      |      2     |   8    |   1234567    |
+-----------+------------+------------+--------+--------------+

