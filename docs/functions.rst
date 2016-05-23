==================
Function Reference
==================

.. default-domain:: js

Elang comes with a set of built in functions for performing common tasks. These functions are pretty much necessary when building tests so it's usefull to be familiar with them. 

.. js:function:: helptext(data*)

    Writes every parameter given to the debug log. 

    :param data anything: Things(s) to write to the debug log
    

#######
Stimuli
#######

Stimuli is a special list that controls the interaction phase. The iteration in an interaction phase is run once for each element in the stimuli list. 

.. js:function:: setstimuli(stimuli)
    
    Sets the stimuli to the given list. Overwrites previously defined stimuli.

    :param list stimuli: List of stimuli objects to set as the stimuli. 

.. js:function:: shufflestimuli()
    
    Randomly shuffles the order of the stimuli.

.. js:function:: shufflestimuli(stimulicount)

    Creates a randomized subset of the stimuli containing the number specified in ``stimulicount``.

    :param number stimulicount: The number of stimuli to select.

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

.. js:function:: hide(item*)
    
    Hides the specified object(s).

    :param displayobject item*: The object(s) that should be hidden.

.. js:function:: hideall()

    Hides every visible element on the screen, including text shown with showmsg().


DisplayObjects
==============

All displayobjects share the same functionality regarding showing and hiding them. And they must be defined and assigned to a variable before being used. 

.. js:function:: msgbox(message, fontsize=20)

    Displayobject for displaying text at any location. Fontsize is an optional argument and it defaults to 20

    :param string message: The text to display in the messagebox.
    :param int fontsize: Fontsize, defaults to 20.
    :returns: Displayobject

.. js:function:: textbox(width)
    
    Displays a one line textbox with the specified width.

    :param int width: Width of the textbox

.. js:function:: textarea(width, height)
    
    Displays a  multilined text area, for reading longer responses as well as enabling the user to press enter. 

    :param int width: Width of the textarea
    :param int width: Height of the textarea

.. js:function:: imagefile(imageurl) 

    Object containing a image specified by the url, so images can either be uploaded to the testeditor or fetched from the internet. Note that images on the net can disappear or change at any time.  The image is displayed without any scaling so make sure that the image is the right size.

    :param string imageurl: Absoulute or relative url to the image. 
    :returns: Displayobject


.. js:function:: rectangle(width height borderwidth=2)

    Displays a rectangle with a white background and black border using the given width height and borderwidth.

    :param int width: Width of the rectangle.
    :param int height: Height if the rectangle.
    :param int borderwidth: Defines the borderwidth in pixels.
    :returns: Displayobject


.. function:: button(text)
    
    Displays a button with the specified text. The button animates when a user clicks or hovers the mouse above it. 

    :param string text: Text to show on the button
    :returns: Displayobject


.. js:function:: countdownbar(width time)
    
    Displays a fully filled countdown bar with the specified width and time(ms).The countdown animation is started by calling animate(countdownbar)

    ::

        var countdown <- countdownbar(200 5000) #
        show(countdown "center")                # Create and show a countdownbar, with a 5 second countdown
        wait(2000)                              # Wait two seconds
        animate(countdown)                      # Countdownbar starts counting down
        wait(5000)
        hideall()                               #Hide it after 5 seconds -> when it's empty

    :param int width: Width of the bar.
    :param int time: Define how long it takse for the bar to reach the end in milliseconds.
    :returns: Displayobject


It's also possible to display simple text messages using showmsg(message) and hidemsg() without any further specifications. This just shows/hides a message at location {top:50 left:50}

.. js:function:: showmsg(message)

    Displays a message at the standard message location in the top right corner, using the standard size and a standard margin.

    :param string message: The message to display.

.. js:function:: hidemsg()

    Hides the standard message.

.. js:function:: emptymsg()

    Empties the standard message. The same thing can be done by calling :js:func:`showmsg` with an empty string.

##########
Text input
##########

.. js:function:: settext(object text)
    
    Changes the text inside a textbox or textarea. The textbox/area can also be emptied by giving it an empty string (""). Settext can also be used to change the text in a msgbox, thus avoiding recreating the a box at every change. 

    :param int object: Which object to change
    :param string text: What to chagne the text to

.. js:function:: readtext(object)

    Returns what is currently written in the specified :js:func:`textbox` or :js:func:`textarea`.

    :param int width: Width of the textarea
    :returns: Current text in the :js:func:`textbox` or :js:func:`textbox`
    :rtype: text

.. js:function:: focus(object)
    
    Makes the selected textbox/area focused, meaning that the specified box will be highlighted and that the user can start typing without having to select it first. 

    :param id object: The textbox/area to focus on 


###########
Mouse input
###########

.. js:function:: onmouseclick(displayobject {action:function inputid: number})

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

.. js:function:: onmouseclick(displayobject false)

    Removes all mouseclick functions bound to the specified object.

    :param object displayobject: Displayobject that should not respond to click any more.


#############
Drag and drop
#############

Drag&drop works by setting objects to draggable, and defining dropzones that the draggable objects can interact with (can be dropped on). Any displayobject can be designated as draggable or as a dropzone. 

.. function:: draggable(object data)

    :param displayobject object: Displayobject to make draggable.
    :param object data: The data that is passed to the dropfunction when the object is dropped.


.. function:: dropzone(object ondrop dropdata)

    :param displayobject object: Displayobject to designate as a dropzone
    :param function ondrop: Function to call when an object is dropped on the dropzone
    :param object dropdata: The second parameter to sen to the ondrop function



::

    var box1 = rectangle(10 10 2 "gray")
    var box2 = rectangle(10 10 2 "gray")

    var text1 = msgbox("Text1")
    var text2 = msgbox("Text2")

    function dropbox(text box)
        showmsg(text "was dropped on" box)
    end

    dropzone(box1 dropbox "Box1")
    dropzone(box2 dropbox "Box2")

    draggable(text1 "Text1")
    draggable(text2 "Text2")

    # Dropping text1 box1 prints "text1 was dropped on box1"
    # Dropping text2 box1 prints "text2 was dropped on box1"
    # Dropping text1 box2 prints "text1 was dropped on box2"
    # Dropping text2 box2 prints "text2 was dropped on box2"

#####
Audio
#####

Audio works the same way as images. You upload an audio file(.mp3 or .ogg), or find one on the Internet. It can then be used after being loaded with the :js:func:`audiofile` command. Note that there's no guarantee that the user will hear what is being played, as he might have turned of/down his speakers/headphones or his device might not even have any audio output. So it's a good idea to check that the users hears the audio, for example asking the user to type in a word that is played, before starting the actual task.

.. function:: audiofile(url)
    
    Loads an audiofile, from soile or from the Internet and stores it in the specified variable. 

    :param string url: Url to the audiofile

.. function:: play(audiofile):
    
    Plays the specified audiofile from the last location.

    :param audiofile audiofile: Audiofile to use.

.. function:: pause(audiofile)

    Pauses the specified audiofile.

    :param audiofile audiofile: Audiofile to use.

.. function:: jumpto(audiofile seconds)

    Skips to the specified location in the audiofile. Can be called for both playing and paused audiofiles.

    :param audiofile audiofile: Audiofile to use.
    :param number seconds: Which location to skip to.

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
    
    Runs :js:func:`resume` once when the specified key is pressed. 

    :param string keycode: Key to resume on 


.. function:: resumeonkey()

    Runs :js:func:`resume` on any keypress once.

.. function:: getlastkey(active=true)

    Returns the most recent keypress, as long as there an active :js:func:`onkeypress` , :js:func:`onanykey` or :js:func:`resumeonkey`.

KeyCodes
========
Keys are specified using keycodes where keycode corrsepsonds to a certain key, keycodes should be defined as strings e.g. ("a"). Alphanumerical keys(a-ö 0-9) simply uses the keys letter, so the keycode "a" corresponds to the button a key on the keyboard. All other keycodes are defined in the following table. 

+-------------+-------------------------+
| "backspace" | Backspace               |
+-------------+-------------------------+
| "tab"       |  Tab                    |
+-------------+-------------------------+
| "enter"     |  Enter/Return           |
+-------------+-------------------------+
| "shift"     |  Left and Right shift   |
+-------------+-------------------------+
| "ctrl"      |  Left and right control |
+-------------+-------------------------+
| "alt"       |  Alt                    |
+-------------+-------------------------+
| "capslock"  |  Capslock               |
+-------------+-------------------------+
| "escape"    |  Esc                    |
+-------------+-------------------------+
| "pageup"    |  Page Up                |
+-------------+-------------------------+
| "pagedown"  |  Page Down              |
+-------------+-------------------------+
| "end"       |  End                    |
+-------------+-------------------------+
| "home"      |  Home                   |
+-------------+-------------------------+
| "insert"    |  Insert                 |
+-------------+-------------------------+
| "delete"    |  Delete                 |
+-------------+-------------------------+
| "left"      |  Arrow Left             |
+-------------+-------------------------+
| "up"        |  Arrow Up               |
+-------------+-------------------------+
| "right"     |  Arrow Right            |
+-------------+-------------------------+
| "down"      |  Arrow Down             |
+-------------+-------------------------+

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

.. function:: not(boolean)
    
    Logic NOT

.. function:: and(bool1 bool2)
    
    Logic AND

.. function:: or(bool1 bool2)
    
    Logic OR

.. function:: lessthan(number1 number2)
    
    number1 < number2

.. function:: lt(number1 number2))
    
    Short version of :js:func:`lessthan`

.. function:: greaterthan(number1 number2)
    
    number1 > number2

.. function:: gt(number1 number2)
    
    Short version of :js:func:`greaterthan`

.. function:: equals(number1 number2)
    
    number1 == number2

.. function:: eq(number1 number2)

    Short version of :js:func:`equals`

.. function:: fuzzyequal(string1 string2 ignoreCases)
    
    Computes how different two strings are from each other. The difference is defined by how many single character edits are required to change string1 into string2.

    :param string1 string: String to compare
    :param string2 string: String to compare with
    :param ignoreCases boolean: Specify if upper/lower case letters should be taken into account, defaults to true.

    For example, the difference between "kitten" and "sitting" is 3, since the following three edits change one into the other, and there is no way to do it with fewer than three edits:

    ::

        kitten → sitten (substitution of "s" for "k")
        sitten → sittin (substitution of "i" for "e")
        sittin → sitting (insertion of "g" at the end).


    :returns: 0 if the strings are equal
    :returns: A number greater than 1 specifying how different the strings are



#########################
Lists/String Manipulation
#########################

.. js:function::  append(string1 string2..stringn)

    Appends two or more strings

    :param string1 string: String to append to
    :param string2 string: String to append to ´string1´
    :param stringn string: String to append to ´string1´


    ::

        append("Hello " "World" "!") #= "Hello World!"



.. js:function::  length(object)

    Returns the number of elements/letters in list or string including whitespaces. 

    :param array/string object: Var to get length of
    :returns number: Length of variable

.. js:function::  elementatindex(object, index)

    Returns the element at the specified index a list/string where the index starts from 0. Equivalent to  object[index]

    :param array object: Object to select an element from.
    :param number index: Index

    ::

        elementatindex("Hello" 0) #= "H"
        elementatindex("Test" 3) #= t


.. js:function::  range(word start end) 

    Round a number down when mode = "floor" and up when mode = "ceil"

    :param string/list word: The string or array to select a range from
    :param number start: Index at which to begin selection. Start can be omitted in which case the selections begins from the first index i.e. 0.
    :param end number: Index at which to end the selection, values up to but not including end are selected. 

    :returns: Range


.. js:function:: split(text separator)
    
    Splits a string into a list of string at every occurance of the separator in the string. 

    :param string text: The string to be split
    :param string separator: Specifies the character to use when splitting the string
    :returns: List of strings

    ::

        split(["1..2..3..4..5], "..") # = [1 2 3 4 5]


.. js:function:: join(list separator)
    
    Joins all elements of a list into a string, separated by the sperator.

    :param list list: The array to be joined.
    :param string separator: Specifies a string to separate each element in the list


    ::

        join([1 2 3 4 5], "..") # = 1..2..3..4..5

.. function:: shuffle(list)

    Returns a new shuffled/randomized version of the list.

    :param list list: The list tp be shuffled.
    :returns: Shuffled list 

##############
Random numbers
##############

.. js:function:: randominteger(min max not)

    Generates a pseudorandom non decimal number within the specified range, and which is not equal to any of the numbers specified in *not*.

    :param number min: Range min number
    :param number max: Range max number
    :param number/list not: Number(s) that are not allowed

.. js:function:: randomnumber(min max)

    Returns a pseudorandom number value within the range

    :param number min: Range min number
    :param number max: Range max number


.. js:function:: seedrandom(seed)

    Seeds the random generator with  a value. A certain seed will always produce the same sequence of random values. 

###############
Time and timers
###############


Time is measured in milliseconds with a precision of +-2 milliseconds in most cases. Time is measured in Epoch time, i.e. the number of milliseconds since 00:00:00 1.1.1970.

.. js:function::  recordts()

    Returns a timestamp with the current time with millisecond precision. 

    :returns: Current timestamp
    :rtype: number

.. js:function::  starttimer()

    Starts the timer.

.. js:function::  elapsedtime()

    Returns elapsed time, in ms, since the last call to :js:func:`starttimer`. Reuturns 0 if the timer hasn't been started. 

    :returns: Elapsed time, in milliseconds.

.. js:function::  wait(time=undefined) 

    Halts the program for a certain amount of time, or until :js:func:`resume` is called. Waits forever if no time is specified.

    :param number time: Number of milliseconds to wait. 

.. js:function::  resume() 

    Resumes program execution if the program is currently halted. Does nothing if the program is running. 


##############
Result storage
##############

Results are stored as .csv spredsheet files that can easily be imported into Excel or any other spreadsheet software. Data can be stored in two different files:
-   Aggregate data from the whole experiment, can only contain one row per user. 
-   "Raw data", test specific data, so each test (in the same experiment) writes to a separate file. Allows for multiple rows per user. It's possible to compute e.g. an average over all rows and store this value in the aggregate datatable. 

.. js:function:: storeSingle(field data)

    Stores a single value with the specified fieldname in the aggregated datatable.

    :param string field: The name of the field
    :param anything data:  Data to store.


.. js:function:: storeRow(field data)

    Stores a value with the given fieldname in the current raw datarow. 

    :param string field: The name of the field
    :param anything data:  Data to store.


.. js:function:: newRow()

    Creates a new empty row to write raw data to.


The example produces the following result:

###############################
Data processing and aggregation
###############################

Raw data can be processed to something more usable. An aggregation function loops through the whole raw data table and performs the specified function on every field that it finds. Rows that don't have any value in the specific field are simply omitted. Aggregation functions can also be used to aggregate data in lists.


.. js:function:: count(field)

    Counts how many rows contain the specific field.


.. js:function:: count(field, value)

    Counts how many rows contain a specific field with a specific value.


.. js:function:: average(field)

    Computes the average value from all rows containing this field.


.. js:function:: median(field)

    Computes the median from all rows containing this field.


.. js:function:: standarddeviation(field)

    Computes the standard deviation from all rows containing ths field. 


.. js:function:: outliers(field multiplier)

    Removes values that deviate more than multiplier*standarddefinition from the average value.


.. js:function:: outliers(field multiplier standarddeviation average)

    You can also provid your own standarddeviation and average for example when computing outliers of   a subset of you data when you still want to use for example the whole dataset for average and standarddeviation.

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


##########################
Traning specific functions
##########################

Tests that are run as part of a trainingstudy have access to a few functions for storing variables bwtween trainingrounds and for displaying a score to the test person.

.. js:function:: savevariable(variablename value)

    Saves a variable with the specified name and value. This value can then be accessed using the :js:func:`loadvariable` function in the next training round.

    :param string variablename: The name with which to store the variable
    :param value value:  Value to store in the variable

.. js:function:: loadvariable(variablename defaultValue)

    Returns the previously stored variable. The default value is returned if the variable doesnt exist yet.

    :param string variablename: The name of the variable to be loaded
    :param value defaultValue:


::

    #This codesnippet prints 1 the first time the test is run, 2 the second time,
    #3 the third time, and so on.
    var round <- loadvariable("persistantRound" 1)
    .
    .
    .
    helptext(round)
    .
    .
    .
    savevariable("persistantRound" plus(round 1))


.. js:function:: savescore(score)

    Save a score that is displayed to the user at the end of a training session, and summed score for a whole trainingsession is also shown to the test person in the form of a graph, so that he can view how he has improved during the training. Its a good idea to make sure that all test in a trainingexperiment produce a score with the same order of magnitude. 

    :param number score: The value of the score to be saved.
