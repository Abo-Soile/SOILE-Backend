===================
Elang Documentation
===================

This document contains documentation for the experiment language.

#########
Structure
#########

The following structure/order is enforced on programs:

1.  Val and gvar definitons
2.  Function definitons
3.  Phase definitions
4.  Transitions
    
**Example** ::

    #Val and gvar definitions"
    gvar g1 <- "global var"
    gvar g2 <- 56
    val a <- "a"
    .
    .
    .
    #end of definitions
 
    #Function definitions 
    function test()
      helptext("test")
    end

    function helloworld()
      showmsg("Hello world")
     end
     .
     .
     .
    #end of function definitions

    #Phase definitions
    intermezzo-phase phase1
      showmsg("In phase1")
      wait(5000)
    end

    information-phase phase2
      showmsg("In phase2")
      wait(5000)
    end

    intermezzo-phase phase3
      showmsg("In phase3")
      wait(5000)
    end
    #End of phase definitions


    #Transitions
    transition
      start(phase1)
      phase1 -> phase2
      phase2 -> phase3
      final(phase3)
    end

    ##End of program

#########
Variables
#########

Variables are containers that can store a value. A variable is created by using the approperiate keyword and assigning a value to it. "val a <- "abc" creates a variable named a that contains the value "abc". The value can then be used by using the variables name. Variable definitions should be in the beginning of a function.

::

    var a <- "abc"
    showmsg(a)  # Displays abc on the screen

A existing variable can be given a new value by using the assignment operator <-. 

::

    var a <- 41  # a contains the number 41
    a <- 66      # 66 is assign to a, the old value(41) is forgotten

There are three different types of variables; 

-  **gvar** Global variable, useable everywhere
-  **val** static global value, cannot be modified after definition
-  **var** Normal variable, usable only inside the function/phase where it's defined.

::

    gvar a <- 55

    information-phase Phase1
      var b <- "Variable b" 
      #Both  a and b are visible here
    end

    information-phse Phase2
      # A is visible but b isn't
    end
    
######
Values
######

Elang supports 5 different values or data types; boolean values, numbers, strings, lists and objects.

Boolean
=======
A boolean value can be either true or false. 

Numbers
=======
Numbers can be any whole or decimal number where . is used as the decimal. 

Strings
=======
A string is a sequence of characters defined by being surrounded by " ". For example "This is a string".

Lists
=====
A list is a list like structure that contains a set of values in a certain order. A list is defined with space separated values surrounded by square brackets, eg. ** var list <- [1 5 10 15 20]. Accessing of a individual value in the list is done with list[index] where index is a number corresponding to the values position in the list starting from 0.´So list[0] corresponds to the value 1 while list[3] corresponds to 15. The lists values and indexes is shown in the table below. 

+-------+---+---+----+----+----+
| list  | 1 | 5 | 10 | 15 | 20 |
+-------+---+---+----+----+----+
| index | 0 | 1 | 2  | 3  | 4  |   
+-------+---+---+----+----+----+


::    

    var aList <- ["oh" "hi" "there"]
    # index    [  0    1      2 ]
    # aList contains 3 elemts "oh", "hi" and "there".
    #
    helptext(aList[1])
    # Writes "hi" ti the 

Objects
=======

An object is a value that can contain mulitple values using a key value structure. An object is defined using curly brackets { }, and an simple object definition would look like this:  **var obj <- {name:"Jon Doe"}**, where *name* is the key and "Jon Doe" is the value. Value is then accessed with *obj.name* . Values in objects cannot be changed after it's been defined, but a new value can ofcourse be assigned to the variable

::
  
  #Object example
  var person <- {firstname:"Jon" lastname:"Doe"}
  helptext(obj.firstname) # Logs "Jon"
  helptext(obj.lastname)  # Logs "Doe"

  # This is illegal since values can't be changes
  person.firstname <- "Kat" #Doesn't work

  # This is fine
  person <- {firstname:"Kat" lastname:person.lastname}



######################
Phases and transitions
######################

Phases
======

Program flow is contolled with phases and phase transitions. There are two types of phases; intermezzo-phases and interaction phases. The intermezzo phase is more simple and is just run from the beginning to the end while the interaction phase has more support for repeating actions. Phases are defined with the correscponding keyword followed a name, and the phases is ended with the **end** command.

An simple intermezzophase is shown below. This phase, named helloPhase, displays a simple greeting to the user and quites after that. 
::

    #Simple intermezzo phase definition
    intermezzo-phase helloPhase
      showmsg("Hello")
      wait(seconds(2))
      showmsg("How are you today?")
      wait(seconds(4))
      hidemsg()
    end

    Result:
    Displays Hello for two seconds -> displays "How are you" for 4 seconds -> hides the message.
    

The interaction phase should be used when the same task is repeated with slighly different input, typically when building a test where the test person repeats a task multiple times. Theses inputs are defined using the *setstimuli(_list of stimuli_)* function, which is a functions that takes a list([]) of values and stores them until an interaction phase is reached. 

A minimal interaction-phase definition would look like this:
::

    interaction-phase simpleInteraction
      iteration
        showmsg(stimulus())
        wait(seconds(2))
      end
    end

There's two things that separate this from the intermezzo-phase. The *iteration* block and the *stimulus()* function. The code inside the iteration block is run once for every stimuli stored using setstimuli(). The current stimulus value can be accessed using the stimulus() function inside the iteration block. So in the example above we're displaying each stimuli as a message to the user. So for example if we set the stimuli (using setstimuli()) to for example ["Hello" "How are you today?"] (two string) the iteration will run two times, displaying "Hello" at the first iteration and "How are you" at the second, and then we're out of stimuli so the phase will end. 

We could expand the interactionphase a little by storing objects in the list instead of strings, for example [{message:"Hello" time:2} {message:"How are you today" time:4}, and changing the iteration block a little:
::

    interaction-phase simpleInteraction
      iteration
        showmsg(stimulus().message)
        wait(seconds(stimulus().time))
      end
    end

Our phase will now do the same thing as the intermezzo-phase we looked at above. It's also very easy to add more stimuli by just adding more values to the list when setting the stimuli. 



The interaction phase as some extra control structures, **enterphase**, **leavephase**, **beforeiteration**, **afteriteration** and **iteration**. Commands inside enterphase are run once when the phase starts and leavephase is run once at the end of the whole iteration phase. Before and after iteration is run before/after each time the iteration step is run.
::

    interaction-phase MainPhase
        enterphase
            showmsg("Entering phase")
            # Setting 3 stimuli, 1, 2 and 3
            setstimuli([1 2)])  
        end
        
        leavephase
            showmsg("Leaving phase")
        end
        
        beforeiteration
            showmsg("Before iteration")
        end
        
        afteriteration
            showmsg("After iteration")
        end
        
        iteration
            # Stimulus returns the current stimuli, 1 in the first iteration
            # and 2 in the second one.
            showmsg(append("In iteration, at stimulus number " stimulus()))
        end
    end
    
    # Output:
    # Entering phase
    # Before iteration
    # In iteration, at stimulus number 1
    # After iteration
    # Before iteration
    # In iteration, at stimulus number 2
    # After iteration
    # Before iteration
    # In iteration, at stimulus number 3
    # After iteration
    # Leaving phase

Transitions
===========

The order in which phases are run is defined in the transition block that should be placed after all phase definitions in the code (at the buttom of the program). A very simple transitions definition could look like this:

::

    transition
      start(firstPhase),
      firstPhase -> secondPhase,
      secondPhase -> lastPhase,
      final(lastPhase)
    end

The transition definition starts with the keyword **transition**, followed by a list of phase tranistion separated by a commas (,). The starting phase is defined first using the **start(_phaseName_)** command and the last phase is lastly defined in the same way with the **final(phaseName)** command. These phases (start, final) should **only** be reachable once, ie at the beginning end of the program.

Phases transition are  defined in any order using the *fromPhase* -> *toPhase* command, until all phases have been visited atleast once.

It's also possible to include a conditional transition by adding **if** *boolean expression* after the transition, e.g. phase1 -> phase2 if(*boolean expression*). This transition will only occure if the boolean expression returns true, making it possible to choose how a program should progress. 

In the example below the phase *add_a* is repeated as long as a is less than 5, and add a transitions into lastPhase when a is greater than 4, i.e. when it reaches 5.

::

        gvar a <- 0

        intermezzo-phase first
          a <- 0
        end

        intermezzo-phase add_a
            helptext(a)
            a <- plus(a 1)
        end

        intermezzo-phase lastPhase
            helptext("Reached lastphase")
        end

        transition
          start(first),
          first -> increment_a,
          add_a -> add_a if(lt(a 5)),
          add_a -> lastPhase if(gt(a 4)),
          final(lastPhase)
        end

        Result:
        0
        1
        2
        3
        4
        Reached lastphae

        Tranisitions
        first->add_a->add_a->add_a->add_a->add_a->lastPhase

**Warning:** It's up to the user to make sure that test don't end up in a so calle infinite loop, where it never reaches the final tranisiton and just repeats something *ad infinum*.

#########    
Functions
#########

A function is a named sequence of a program that performs a specific task, packaged as a unit. This unit can the be used, or called, whenever this task need to be performed. Elang comes with a set of predefined functions for common task (documented below) and users can also define their own functions. 

Using functions
===============

A function is called or excecuted using it's name, followed by function arguments surrounded by parntheses, e.g. **add(1 2)**. Functions can also return a result which can be used in the same way as a value. The **add(a b)** accepts two arguments, a and b, and returns the addition of theses valuse (a + b).

::

    var a <- 5
    var b <- 10

    var c <- add(a b)
    c will contain 15. 

    var d <- add(10 10)
    d contains 20

Defining functions
==================
Functions should be defined in the function definitn block, after variables and before phases. A example function could look like this:

::    

    # Functions that computes number^2
    function powerOfTwo(number)
      var result <- multiply(number number)
      return result
    end

    # usage
    var b <- powerOfTwo(5)
    # b = 25

So a function is defined with the keyword function followed by it's name. A function can accept any number of parameters or no parameters at all, contained inside the bracket separated by a space. The function body should start with variable definitions, same way as in phases, and can then perform any number of commands. The **return** keyword specifies that a value is returned from the function and the function stops executing.

::

    function returnZero()
        return 0

        # The function stops executing after the return statement
        # so return 10 is never run
        return 10
    end

    var a <- returnZero()
    # a = 0


###############
Getting Started 
###############


We'll start by creating a minimal experiment that displays "Hello Test" for 3 seconds and exits.

::
  
  intermezzo-phase hello
    showmsg("Hello")
    wait(3000)
  end

  interezzo-phase test
    showmsg("Test!")
    wait(3000)
  end

  transition
    start(hello),
    hello -> test,
    final(test) 
  end