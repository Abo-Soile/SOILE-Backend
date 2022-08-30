===================================
Questionnaire Docs
===================================

##########
Plain text
##########

There are several commands available to format plain text. They are the subject of this section.

Next-line arguments

When we say that a command accepts a “next-line argument,” we mean that the argument to the command is on the next line of the markup.

To demonstrate, the following command could be used to produce the title of this section. (*/title* requires a next-line argument.)

::

    /title
    Next-line arguments

###############
Stack arguments
###############

Two commands - */ts* and */bs* - operate on **stacks**. They differ in their scope. The scope of */ts* is always a single paragraph, whereas the scope of */bs* may be several paragraphs. As it has a more restricted scope, */ts* has a higher precedence than */bs* when they conflict with each other.

There are three fundamental stack operations - *push*, *pop*, and *empty*. All of these operations involve :ref:`text styles. <text_style_ref>`

**push** places a style on top of the stack. *push* can be abbreviated as "+".

**pop** removes the style off of the stack, which was inserted (i.e. pushed) last. *pop* can be abbreviated as "-"

**empty** clears the whole stack; i.e., it basically calls *pop* repeatedly, until there is nothing more to *pop*. *empty* can be abbreviated as "!".

In the example to follow, '[]' denotes an empty stack. (Do not mistake it for an array, however.) The leftmost element is considered the top of the stack.

.. role:: blue
.. role:: blar
.. role:: blup
.. role:: bluu

.. raw:: html

    <style>
        .blue {
            color: blue;
        }
        .blar {
            color: blue;
            font-size:120%; 
        }
        .bluu {
            color: blue;
            text-decoration: underline;
        }
    </style>

+-------------------+----------------------------------+-------------+
|       Command     |               Stack              | Rendering   |
+===================+==================================+=============+
|                   |                                  |    Text     |
+-------------------+----------------------------------+-------------+
| push "blue"       |                ["blue"]          | :blue:`Text`|
+-------------------+----------------------------------+-------------+
| push "large"      |        ["large", "blue"]         | :blar:`Text`|
+-------------------+----------------------------------+-------------+
|      pop          |               ["blue"]           | :blue:`Text`|
+-------------------+----------------------------------+-------------+
| push "uppercase"  |       ["uppercase", "blue"]      | :blue:`TEXT`|
+-------------------+----------------------------------+-------------+
| push "underline"  |["underline", "uppercase", "blue"]| :bluu:`TEXT`|
+-------------------+----------------------------------+-------------+
| empty             |                []                |    Text     |
+-------------------+----------------------------------+-------------+

.. _text_style_ref:

###########
Text styles
###########

Below, the text style command name displays its formatting effect. For instance, ‘monospace’ has been formatted in a monospace font; ‘large’ has been displayed in a large font size; and so on. It is noteworthy, too, that text style names are always in lower case regardless how they have been rendered below.

.. raw:: html

    <style>
    .monospace   {font-style: monospace;}
    .sanserif    {font-style: sanserif;}
    .serif       {font-style: serif;}
    .underline   {text-decoration: underline;}
    .linethrough {text-decoration: line-through;}
    .overline    {text-decoration: overline;}
    .small       {font-size: 80%;}
    .large       {font-style: 120%;}
    .aqua        {color: aqua;}
    .black       {color: black;}
    .blue        {color: blue;}
    .fuchsia     {color: fuchsia;}
    .gray        {color: gray;}
    .green       {color: green;}
    .lime        {color: lime;}
    .maroon      {color: maroon;}
    .navy        {color: navy;}
    .olive       {color: olive;}
    .orange      {color: orange;}
    .purple      {color: purple;}
    .red         {color: red;}
    .silver      {color: silver;}
    .teal        {color: teal;}
    </style>

.. role:: monospace
.. role:: sanserif
.. role:: serif
.. role:: underline
.. role:: linethrough
.. role:: overline
.. role:: small
.. role:: large
.. role:: aqua
.. role:: black
.. role:: blue
.. role:: fuchsia
.. role:: gray
.. role:: green
.. role:: lime
.. role:: maroon
.. role:: navy
.. role:: olive
.. role:: orange
.. role:: purple
.. role:: red
.. role:: silver
.. role:: teal

+-----------------------------+----------------------+
|* :monospace:`monospace`     |* :blue:`blue`        |      
|* :sanserif:`sanserif`       |* :fuchsia:`fuchsia`  |
|* :serif:`serif`             |* :gray:`gray`        |
|* :underline:`underline`     |* :green:`green`      |          
|* :linethrough:`linethrough` |* :lime:`lime`        | 
|* :overline:`overline`       |* :maroon:`maroon`    |              
|* UPPERCASE                  |* :navy:`navy`        |
|* lowercase                  |* :olive:`olive`      |
|* Capitalize                 |* :orange:`orange`    |
|* :small:`small`             |* :purple:`purple`    |
|* :large:`large`             |* :red:`red`          |
|* :aqua:`aqua`               |* :silver:`silver`    |
|* :black:`black`             |* :teal:`teal`        |
+-----------------------------+----------------------+


/bs
---

This command accepts stack arguments.

Mnemonic: “bs” stands for block style.


/lb
---

Inserts a linebreak into a paragraph.

This command does not accept any arguments.


/p
--

Opens a new paragraph, and if necessary closes the previous paragraph.

This command does not accept any arguments.

This command implicitly calls the commands /ts empty, and /pb.


/pagetitle
----------

Sets the title of the questionnaire.

This command requires a “next-line argument”.


/title
------

Creates a title.

This command requires a “next-line argument”.


/subtitle
---------

Creates a subtitle.

Titles created with this command are logically on a lower level than those created with This command /title.

This command requires a “next-line argument”.


/pb
---

Closes the currently open paragraph explicitly.

Note that /p closes the currently open paragraph implicitly.

This command does not accept any arguments.

Mnemonic: “pb” stands for paragraph break.

/ts
---

This command accepts stack arguments.

Mnemonic: “ts” stands for text style.

/link
-----

This command creates a hyperlink.

This command is unique in that it requires two arguments: one argument given next to the command (the URL), and a “next-line argument” (the link text).

This is how a link to the Åbo Akademi homepage would be created.

  /link http://www.abo.fi/
  Åbo Akademi
          
This would produce 
Åbo Akademi.

#######
Widgets
#######

The purpose of widget elements is to accept and receive input from readers of a questionnaire.

Object arguments
----------------

Every “widget command” requires an object argument. It is fully deliberate that the object arguments resemble the JSON data interchange format. (The only difference, again fully deliberate, is that in our object argument notation fewer double quote characters are required. The purpose is to improve user-friendliness.)

To understand object arguments, or objects for short, one needs to understand simple value types first. (Simple value types can be called atomic value types, too.) There are three kinds of simple value types: strings, integers, and booleans.

Basically, a string consists of characters enclosed in double quote (") characters (e.g., "this is a string"). A boolean value is either true or false. Integers are just numbers (e.g., 1234, and 543).

Arrays and objects are compound values. An array is a collection of zero or more elements, which can be either simple values, or compound values. Objects are a collection of one or more ‘properties’, which are name-value pairs. Names are strings (with the possibility of omitting double quote characters, if the string does not contain any whitespace characters), and values are either simple values, or compound values.

An example of a basic array (by ‘basic’ we mean that the elements of the array are simple values):

::

  ["a string", true, 123, false, "another string"]
          
An example of a basic object which has three properties—‘title’, ‘published’, and ‘copyrighted’.

::

  {title: "Javascript", published: 2011, copyrighted: true}
          
An example of a more complicated object:

::

  {
    books: [
      {
        title: "Psychology vol. I",
        year: 1890
      },
      {
        title: "Psychology vol. II",
        year: 1895
      }
    ],
    author: {
      firstname: "John",
      lastname: "Doe",
      pennames: ["jdoe", "johnd"]
    },
    pages: 1500,
    bestseller: false
  }
          
Specifying object types

Suppose we specify an argument to a command as follows:
::

  {
      title: String,
      year: Integer,
      paperback: Boolean,
      reviews: 
        [
          {
            user: String, 
            rating: Integer,
            review: String
          }
        ]
  }
            
This is interpreted as follows.

* The argument itself is an object.
* The object has a property ‘title’, which must be a string.
* The object has a property ‘year’, which must be an integer.
* The object has a property ‘paperback’, which must be a boolean.
* The object has a property ‘reviews’, which must be an array.
* The elements of the ‘reviews’ array must be objects with three properties:  the property ‘user’ must be a string, the property ‘rating’ must be an integer, and the property ‘review’ must be a string.

The following object is valid with respect to the above specification.

::

  {
      title: "Cooking",
      year: 2013,
      paperback: true,
      reviews: 
        [
          {
            user: "reader1", 
            rating: 5,
            review: "Love this book!"
          },
          {
            user: "reader2", 
            rating: 1,
            review: "Hate this book!"
          }
        ]
  }
            

/dropdownmenu
-------------

::

  {
      dbcolumn: String,
      numeric: true,
      label: String,
      options: 
        [
          {
            dbvalue: String, 
            text: String
          }
        ]
  }
          
Example:

::

  /dropdownmenu {
   dbcolumn: "continent",
   label: "Select Continent",
   options: [{dbvalue: "EU", text: "Europe"},
             {dbvalue: "AM", text: "America"},
             {dbvalue: "AS", text: "Asia"},
             {dbvalue: "AU", text: "Australia"},
             {dbvalue: "AF", text: "Africa"}
             ]
  }

          
/multiselect
------------

::

  {
      numeric: true,
      default_value: String
      options: 
        [ 
          [ 
            {
              dbcolumn: String,
              dbvalue: String, 
              text: String, 
              checked: Boolean
            }
          ] 
        ]
  }
          
Example:

::

  /multiselect {
      default_value: "no",
      dbcolumn: "vehicle",
      options: [ [ 
            {
              dbcolumn: "Bike",
              dbvalue: "yes", 
              text: "Bike", 
              checked: true
            },
              {
              dbcolumn: "Car",
              dbvalue: "yes", 
              text: "Car", 
              checked: false
            },
            {
              dbcolumn: "Motorbike",
              dbvalue: "yes", 
              text: "Motorbike", 
              checked: false
            }] ]
  }

          
/numberfield
------------

::

  {
      dbcolumn: String,
      numeric: true,
      label: String,
      value: Integer,
      minimum: Integer,
      maximum: Integer,
      increment: Integer or "Decimal value"
  }

Increment can be either an integer(5) or a decimal value(3.6).Note that decimal values must be surrounded by quotes("5.5"). If not it's interpreted as an integer.

Example:

::

  /numberfield {
      dbcolumn: "age",
      label: "Enter your age",
      value: 0,
      minimum: 0,
      maximum: 100,
      increment: 1
  }

          
/singleselect
-------------

::

  {
      numeric: true,
      default_value: String
      options: 
        [ 
          [ 
            {
              dbcolumn: String,
              dbvalue: String, 
              text: String, 
              checked: Boolean
            }
          ] 
        ]
  }
          
Example:

::

  /singleselect {
     default_value: "-1",
     dbcolumn: "Yes/No/maybe",
     options: [[{dbvalue: "yes", text: "Yes", checked: true},
               {dbvalue: "no", text: "No"},
               {dbvalue: "maybe", text: "Maybe"}]]
  }

          
/slider
-------

::

  {
      dbcolumn: String,
      numeric: true,
      labels: 
        [
          Integer
        ],
      minimum: Integer,
      maximum: Integer,
      increment: Integer,
      select: Integer                                            
  }
          
Example:

::

  /slider {
     dbcolumn: "Slider 0-6",
     numeric: true,
     dbtype: "int",
     labels: [0,1,2,3,4,5,6],
     minimum: 0,
     maximum: 6,
     increment: 1,
     select: 0
  }

          
/textarea
---------

::

  {
      dbcolumn: String,
      rows: Integer,
      columns: Integer,
      label: String,
      text: String
      optional: Boolean (default=true)
  }
          
The Optional field is not required and can be left out, the default value for it is false

Example:

::

  /textarea {
     dbcolumn: "story",
     rows: 3,
     length: 150,
     label: "Tell us a story",
     text: "Enter your story here."
  }

          
/textbox
--------

::

  {
      dbcolumn: String,
      label: String,
      linebreak: Boolean,
      length: Integer,
      text: String
      optional: Boolean (default=true)
  }
       
The Optional field is not required and can be left out, the default value for it is false

Example:
::

  /textbox {
     dbcolumn: "name",
     label: "Enter you name",
     linebreak: true,
     length: 40,
     text: "Enter your name here"
  }

