==============
Elang Examples
==============

.. _link to reaction test: http://http://soile.braintrain.fi/test/d4039e00-e452-4303-bc13-689318167d18
.. _link to Letter Memory: http://http://soile.braintrain.fi/test/ad088132-438f-4a4f-a65a-dafc6b6c4bea
.. _link to boxes: http://http://soile.braintrain.fi/test/a578c12d-4951-488d-90a6-e230b10950fa
.. _link to number letter: http://http://soile.braintrain.fi/test/edd1a4bd-fb05-46c9-aa0d-d282bf761c0a

#############
Reaction Test
#############


Description

`link to reaction test`_

.. code-block:: none
   :linenos:

    # This test tests the reaction time of a user. The user is asked to press
    # the key "h" as fast as possible when a yellow box appears on the screen.

    # The users reactiontime is logged and stored and an average is also calculated.

    # Loading a image from the internet. 
    val imgurl <- "http://placehold.it/350x150/fffff&text=x&c"

    # And creating a displayable image.
    gvar reactimg <- imagefile(imgurl)

    # Number of games played thus far, starting from 0
    gvar games <- 0

    # Users is done after 15 games.
    gvar maxgames <- 15

    # Did the user react or not 0 - No,   1 - Yes
    gvar reacted <- 0


    # Function that sets reacted ti 1 and resumes execution
    function userReacted()
      reacted <- 1
      resume()
    end

    # Funtion that checks if the user is done
    function gamedone()
      # Showing number of games played
      helptext("games played")
      helptext(games)
      
      # Adding 1 to games played
      games <- plus(games 1)
      
      # Check if games played is greater than maxgames(15), return true or false
      return gt(games maxgames)
    end

    # First phase, writes stuff to the log and wait 2000ms
    intermezzo-phase Alpha
      helptext("Starting Test")
      wait(2000)
    end

    # Second phase, shows some text for the user
    intermezzo-phase Info
      # Show some instructions to the user
      showmsg("Press h when the yellow box appears")
      helptext("Displaying instructions")
      
      wait(seconds(7))
      # Hide the text after waiting
      hidemsg()
      
      helptext("Hiding instructions")
    end

    # This is the last phase, calculating and storing results
    intermezzo-phase FinalPhase
      helptext("Final phase, storing results")
      
      count("reacted" 1)
      count("reacted" 0)
      average("rt")
      
    end


    # Gamephase, is run 
    interaction-phase MainPhase

      enterphase
        helptext("Starting Mainphase")
        # Setting reacted to "no",
        reacted <- 0
        
        # Computing a random number between 2 and 3 and setting it as stimuli
        setstimuli([randomnumber(2 3)])
      end
      
      # Is run at the end of the phase
      leavephase
        helptext("")
      end

      # Is run for each stimuli, only once in this case since there's only one stimuli
      beforeiteration
        helptext("")
      end
      
      # Is run after each stimuli, 
      afteriteration
        helptext("")
        # Starting a new datarow
        newrow()
      end
      
      # Is run once for each stimuli, 
      iteration
        #Declaring variables
        var i <- 0
        var s <- 0
        
        # Storing the current stimuli in s
        s <- stimulus()
        
        # Wait s seconds, is the random number that was stored in row 81
        wait(seconds(s))
        
        # Run the function userReacted when "h" is pressed
        onkeypress("h" userReacted)
        
        # Starting timer
        starttimer()
        
        # Showing the red box to the user
        show(reactimg {top:100 left:300})
        
        # Wait and allow the user to reactr
        wait(seconds(3))
        
        helptext("Hiding image")
        hide(reactimg)
        
        # Store time since starttimer in i
        i <- elapsedtime()
        
        # Stop listening for keypresses
        onkeypress("h")
        
        # Store reaction as rt, stimuli as waittime and if the user reacted in the results
        storerow("rt" i)
        storerow("waittime" s)
        storerow("reacted" reacted)
        
        helptext("Enditeration")
      end
    end

    # Tranition table
    transition
      start(Alpha),
      Alpha -> Info,
      Info -> MainPhase if (not(gamedone())),
      MainPhase -> FinalPhase if (gamedone()),
      final(FinalPhase)
    end



#############
Letter memory
#############

`link to Letter Memory`_

.. code-block:: none
   :linenos: 

    # Loading images
    gvar crosshair <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/crosshair.jpg") 
    gvar fortsatt <- imagefile("http://ocean.danielwarna.com:8081/testimages/ad088132-438f-4a4f-a65a-dafc6b6c4bea/fortsatt.png") 

    #gvar rounds <- 4
    gvar clicked <- false

    # Defining position to avoid repetition later
    val center <- {top:200 left:500}
    val leftpos <- {top:200 left:200}
    val rightpos <- {top:200 left:600}

    val upPos <- {top:100 left:500}
    val downPos <- {top:300 left:500}

    val keypadpos <- {top:350 left:400}
    val rectpos <- {top:345 left:395}

    # Defining globals
    gvar userinput <- "a"
    gvar in <- ""

    # How long should a letter be shown
    gvar delay <- 1500

    # The box that goes around the input
    gvar inputBox <- rectangle(210 60 2)

    # The roccect answer is shown only on the first letterserie so we should track this
    gvar firstSerie <- true

    function click(a)
      helptext(a)
      clicked <- true
    end

    # First phase, showing instructions to user
    intermezzo-phase Start
      wait(seconds(2))
      
      # Showing instructions
      showmsg("I denna uppgift ser du serier med bokstäver som visas ett i taget på datorskärmen. Din uppgift är att hela tiden hålla i minnet de fyra senaste siffrorna. Bokstavsserierna kan vara mycket långa, så försök att hela tiden hålla i minnet endast de fyra sista bokstäverna som presenterats i exakt den ordning de visats. Då serien tar slut bör du svara vilka de sista fyra bokstäverna var med att trycka på bokstavsknapparna på ditt tangentbord. Du vet inte hur lång serien är, så du måste hela tiden vara uppmärksam. Tryck på Fortsätt för att se en demonstration av uppgiften.")
      
      # Showing the "Fortsätt" button
      show(fortsatt keypadpos)
      
      # Activiting mouseclick on the button -> waiting until user clicks it
      onmouseclick(fortsatt {action:resume})
      wait(45656456465)
      
      # Hiding message and button and disabling clickaction
      hidemsg()
      hide(fortsatt)
      onmouseclick(fortsatt)
      
    end

    # Second phase, showing what the user should do throught examples
    interaction-phase Guide
      enterphase
        # The stimuli consists of the letter serie and the correct answer
        setstimuli([{serie:"GLRBKS" ans:"rbks"}
                  ])
      end
        
      leavephase
        helptext("Leaving practise")
      end
      
      beforeiteration
        # Filler , iteration blocks can't be empty
        plus(1 1)
      end
      
      afteriteration
        plus(1 1)
        #helptext("After stimuli iteration")
      end
      
      iteration
        # Defining variables
        var textbox <- 0
        var s <- 0
        var letters <- 0
        var currentletter <- 0
        var answer <- 0
        var ind <- 0
        
        var counter <- 0
        
        # Showing message -> wait -> hide
        showmsg("En ny serie börjar....")
        wait(seconds(3))
        hidemsg()
        
        # Storeing stimuli data in variables
        s <- stimulus()
        letters <- s.serie
        answer <- s.ans
      
        # currentLetter is set to the first letter in the serie
        currentletter <- elementatindex(letters 0)
        
        # While loop is run until all letters have been shown
        while currentletter
        do
          # Logging
          helptext(currentletter)
          
          # Showing and hiding crosshair
          show(crosshair center)
          wait(500)
          hide(crosshair)
          
          # Creating a messagebox containing the current letter, showing and hiding it
          textbox <- msgbox(currentletter 40)
          show(textbox center)
          wait(delay)
          hide(textbox)
          
          # Incrementing ind with 1
          ind <- plus(ind 1)
          
          # And loading the next letter, elementatindex returns false when ind is bigger than the number of letters
          # thus ending the while-loop
          currentletter <- elementatindex(letters ind)
          
          wait(50)
          
        end
        
        showmsg("Då denna fråga ställs, vet du att det är dags att svara. Bokstavsserien var G-L-R-B-K-S, de sista fyra bokstäverna var alltså R-B-K-S. Svara med att trycka på bokstavsknapparna på ditt tangentbord. Tryck först på R, sedan på B, därefter på K och slutligen på S. Kom ihåg att ordningsföljden är viktig! Du kan inte ändra på ditt svar då du tryckt på en knapp. Tryck på de angivna bokstavsknapparna och tryck sedan Här visas på Fortsätt för personens att börja övningsuppgiften.")
        
        # User hasn't entered enything yet, make sure that it's empty
        userinput <- ""
        # Showing the border around the answer
        show(inputBox rectpos)
        
        # Counter starts at 0, while loop is run until count is equal to or bigger than 4
        while lt(counter 4)
        do
          # Resume when the user enters the correct letter, incorrect ones are ignored
          resumeonkey(elementatindex(answer counter))
          
          # Wait until user enters something
          wait(500052352)
          hide(textbox)
          
          # storing the pressed key in the variable "in"
          in <- getlastkey()
          
          # Logging user input
          helptext(append("Testing input: " in))
          
          # adding the recently pressed letter to the userinput variable, 
          userinput <- append(userinput in)
          userinput <- append(userinput " - ")
          
          # Adding the letter to shown message and showing it
          textbox <- msgbox(userinput 40)
          show(textbox keypadpos)
          
          helptext(userinput)
          
          # Adding +1 to the counter, loop won't repeat when counter equels 4
          counter <- plus(counter 1)
        end
        
        # Showing the continue button and waiting for the uset to click it
        show(fortsatt {top:350 left:780})
        wait(500)

        onmouseclick(fortsatt {action: resume})
        resumeonkey()
        wait(3456334635)
        
        
        # Hiding everything
        hide(fortsatt)
        onmouseclick(fortsatt)

        hidemsg()
        hide(inputBox)
        hide(textbox)
        
        helptext("Guide over")
      end
    end

    # The actual test phase
    interaction-phase Test

      enterphase
        # Setting the stimuli, all users are giventhe same serie
        setstimuli([ 
                  {serie:"GSIEKZSI" ans:"kzsi"}
                  {serie:"MNIOPE" ans:"iope"}
                  {serie:"SJNEUAOA" ans:"uaoa"}
                  ])
      end
        
      leavephase
        helptext("Leaving practise")
      end
      
      beforeiteration
        plus(1 1)
      end
      
      afteriteration
        plus(1 1)
        # Creating a new resultrow after each round
        newrow()
      end
      
      iteration
        # Initiating variables
        var tb <- 0
        var s <- 0
        var currentletter <- 0
        var ind <- 0
        var counter <- 0
        var userans <- 0
        
        var ansbox <- 0
        
        # Preparing the user
        showmsg("En ny serie börjar....")
        wait(seconds(3))
        hidemsg()

        # Setting variables
        s <- stimulus()
        ind <- 0
        userans <- ""
        
        helptext(s.serie)
        
        # Current letter is set to the first letter in the serie
        currentletter <- elementatindex(s.serie 0)
        
        # Displaying all letters on by one until all letters have been displayed. 
        while currentletter
        do
          helptext(currentletter)
          show(crosshair center)
          wait(500)
          hide(crosshair)
          
          tb <- msgbox(currentletter 40)
          show(tb center)
          wait(delay)
          hide(tb)
            
          ind <- plus(ind 1)
          currentletter <- elementatindex(s.serie ind)
          
        end
        
        # All letters have been shown, preparing to accept user input
        showmsg("Vilka var de fyra sista bokstäverna? Svara med att trycka på bokstavsknapparna på tangentbordet. Gissa även ifall du inte är helt säker, gissande avdrar inte poäng och du kanske svarar rätt. Ge ett fullständigt svar (fyra bokstäver) för att fortsätta..")
        wait(50)
        
        # User input is empty at first, and the counter starts at 0
        userinput <- ""
        counter <- 0
        
        show(inputBox rectpos)

        # Starting to accept input from user, one letter at the time
        while lt(counter 4)
        do
          # Waiting for a keypress, 
          onanykey(resume "onlyletters")
          wait(500052352)
          
          # Stop listening for a input
          onanykey()
          hide(tb)
          
          # Storing the keypress and appening it to the user input variable
          in <- getlastkey()
          userinput <- append(userinput in)
          userinput <- append(userinput " - ")
          
          # We're also storing the user input without any "-" inbetween for easier comparison
          userans <- append(userans in)
          
          # Showing the updated inputstring to the uset
          tb <- msgbox(userinput 40)
          show(tb keypadpos)
          
          helptext(userinput)
          
          # +1 to counter
          counter <- plus(counter 1)
          
          wait(50)
        end
        
        # Showing the correct answer on the first round
        if firstSerie
        then
          ansbox <- msgbox(append("Rätt svar: " s.ans) 36)
          show(ansbox {top: 280 left:350})
        end
        
        # Storing the serie, correct answer adn user provided answer in results
        storerow("serie" s.serie)
        storerow("svar" s.ans)
        storerow("anvandarens svar" userans)
        
        # Comparing the users' answer to the correct one
        if eq(userans s.ans)
        then
          # User answered correctly
          storerow("ratt_svar" true)
        else
          # User anwered incorrectly
          storerow("ratt_svar" false)
        end
        
        # Showing the continuebutton
        show(fortsatt {top:350 left:780})
        
        wait(500)
        
        # Waiting for a mouseclick on it
        onmouseclick(fortsatt {action: resume})
        wait(3456334635)
        
        # Button clicked, disabling button and hidning it
        hide(fortsatt)
        onmouseclick(fortsatt)
        
        #resumeonkey()
        #wait(2345345345)
        
        # Setting firstserie to false and hiding the correct answer box. 
        if firstSerie
        then 
          hide(ansbox)
          firstSerie <- false
        end
        
        # Hiding everything else in preparation for the next round
        hide(inputBox)
        hide(tb)
        wait(500)

      end
    end

    # Final phase, aggregating results
    intermezzo-phase FinalPhase
        plus(1 1)
        count("ratt_svar" true)
        count("ratt_svar" false)
    end

    transition
      start(Start),
      Start -> Guide,
      Guide -> Test,
      Test -> FinalPhase,
      final(FinalPhase)
    end


#####
Boxes
#####

`link to boxes`_

.. code-block:: none
   :linenos:

    # Loading images
    gvar bluebox <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/bluebox.jpg") 
    gvar redbox <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/redbox.jpg") 
    gvar crosshair <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/crosshair.jpg") 
    gvar keydown <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/keydown.png") 
    gvar keyright <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/keyright.png") 
    gvar fortsatt <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/fortsatt.png") 


    gvar practiseerrors <- 0

    gvar rounds <- 4
    gvar clicked <- false
    gvar answered <- false

    # Defining positions
    val center <- {top:240 left:470}
    val leftpos <- {top:200 left:200}
    val rightpos <- {top:200 left:600}

    val keypadpos <- {top:350 left:400}

    function click()
        clicked <- true
    end

    function answer()
      answered <- true
      resume()
    end

    intermezzo-phase Start
        plus(1 1)
        #showmsg("I denna uppgift kommer du att se en röd eller blå fyrkant som uppenbarar sig på högra eller vänstra kanten av datorskärmen. Tryck på nedåtpilen ↓ ifall fyrkanten är blå eller på högerpilen → ifall fyrkanten är röd, oberoende av var fyrkanten uppenbarar sig. Mellan varje ny fyrkant visas ett fixeringskors som du bör fixera din blick på. Tryck på Fortsätt för att se en demonstration av uppgiften")
        #show(fortsatt keypadpos)
        
        #onmouseclick(fortsatt {action:resume})
        #wait(45656456465)
        
        #hidemsg()
        #hide(fortsatt)
        #onmouseclick(fortsatt)
    end

    # Telling the user what to do
    intermezzo-phase Guide

        # Blue box - Left
        #-----------------------
        
        # Showing and hiding crosshair
        show(crosshair center)
        wait(800)
        hide(crosshair)
        wait(250)
        
        # Showing instructions and the box
        showmsg("Fyrkanten är blå, alltså bör du trycka på nedpil, Tryck på nedpil för att fortsätta")
        show(bluebox leftpos)
        show(keydown keypadpos)

        # Waiting until the user clicks arrow down
        resumeonkey("down")
        wait(56465489)
        
        # Clearing screen
        hidemsg()
        hide(bluebox)
        hide(keydown)
        
        # Red box right
        #-----------------------
        show(crosshair center)
        wait(800)
        hide(crosshair)    
        wait(250)
        
        showmsg("Fyrkanten är röd, alltså bör du trycka på högerpil, Tryck på högerpil för att fortsätta")
        show(redbox leftpos)
        show(keyright keypadpos)

        resumeonkey("right")
        wait(8859789)
        
        hidemsg()
        hide(redbox)
        hide(keyright)
        
        # Blue box right
        #-----------------------
        show(crosshair center)
        wait(800)
        hide(crosshair)    
        wait(250)
        
        showmsg("Fyrkanten är blå, alltså bör du trycka på nedpil, Tryck på nedpil för att fortsätta")
        show(bluebox rightpos)
        show(keydown keypadpos)

        resumeonkey("down")
        wait(8859789)
        
        hidemsg()
        hide(bluebox)
        hide(keydown)
        
        # Red box left
        #-----------------------
        show(crosshair center)
        wait(800)
        hide(crosshair)    
        wait(250)
        
        showmsg("Fyrkanten är röd, alltså bör du trycka på högerpil, Tryck på högerpil för att fortsätta")
        show(redbox rightpos)
        show(keyright keypadpos)

        resumeonkey("right")
        wait(8859789)
        
        hidemsg()
        hide(redbox)
        hide(keyright)
        
        #User finished the instruction round done
        #-----------------------
        showmsg("Nu får du göra några exempeluppgifter.Tryck på nedåtpilen ifall fyrkanten är blå eller på högerpilen ifall fyrkanten är röd. Endast fyrkanterna och fixeringskorset kommer att vara synliga på skärmen. Svara så snabbt du kan, men undvik fel. Varje fyrkant är synlig på rutan tills du tryckt på en av svarsknapparna, men högst i 1,5 sekunder. Tryck på nedåtpilen för att börja övningsuppgiften.")

        show(keydown keypadpos)
        resumeonkey("down")
        wait(353453453453)
        
        hide(keydown)
        hidemsg()
        wait(seconds(1))
    end

    # Practise round
    interaction-phase Practise

      enterphase
        helptext("Enter practise")
        
        # Repeat instructions if the user made any errors, ig practiseerrors > 0
        if gt(practiseerrors 0)
        then
          showmsg("Vi tar det en gång till. Tryck på nedåtpilen ↓ ifall fyrkanten är blå eller på högerpilen → ifall fyrkanten är röd. Svara så snabbt du kan, men undvik fel. Varje fyrkant är synlig på rutan tills du tryckt på en av svarsknapparna, men högst i 1,5 sekunder. Tryck på nedåtpilen för att börja ↑ övningsuppgiften")
          show(keydown keypadpos)
          resumeonkey()
          wait(353453453453)
          
          hidemsg()
          hide(keydown)
        end
        
        # Setting errors to 0 before starting a new practisround
        practiseerrors <- 0
        
        # Defining practise stimuli,
        # Box: b = blue box  pos l = display box to the left
        # Box: r = red box   pos r = display box to the right
        setstimuli([{box:"b" pos:"l"}
                    {box:"r" pos:"r"}
                    {box:"b" pos:"r"}
                    {box:"r" pos:"l"}
                    ])
      end
      
      leavephase
        helptext("Leaving practise")
      end
      
      beforeiteration
        plus(1 1)
        #helptext("Before stimuli iteration")
      end
      
      afteriteration
        #helptext("After stimuli iteration")
        plus(1 1)
      end
      
      iteration
        # Defining variables
        var s <- 0
        var box <- redbox
        var pos <- leftpos

        clicked <- false
        
        s <- stimulus()
        
        # Figuring out where the bix should be placed by comparing stimuli.pos
        if eq(s.pos "r") 
        then
          pos <- rightpos
        else
          pos <- leftpos
        end
        
        # Showing and hidning crosshair
        show(crosshair center)
        wait(800)
        hide(crosshair)
        wait(250)
        
        # The function "click" is called if the users anwers correctly
        if eq(s.box "b")
        then
          box <- bluebox
          onkeypress("down" click)
        else
          box <- redbox
          onkeypress("right" click)
        end
        
        # Whowing box
        show(box pos)
        
        # Box is shown for 1.5 seconds, answering right or wrong makes it disappear
        resumeonkey()
        wait(1500)
        
        # Hiding the box and and disabling keypresses
        hide(box)
        onkeypress("right")
        onkeypress("down")
        
        # clicked is set to true in the click function, i.e. the user ansered correctly
        if clicked
        then 
          helptext("user answered correctly")
        else
          # Wrong answer add 1 to errors
          practiseerrors <- plus(practiseerrors 1)
        end
     
      end
    end

    # Repeating instructions
    intermezzo-phase Repeatinstructions
        showmsg("Bra jobbat! Nu börjar uppgiften. Tryck på nedåtpilen ↓ ifall fyrkanten är blå eller på högerpilen → ifall fyrkanten är röd. Svara så snabbt du kan, men undvik fel. Tryck på nedåtpilen för att börja uppgiften.")
        show(keydown keypadpos)
        resumeonkey("down")
        wait(353453453453)

        hidemsg()
        hide(keydown)

    end

    # The actual test
    interaction-phase Test

      enterphase
        helptext("Entering test")
        
        setstimuli([{box:"r" pos:"r"}
                    {box:"b" pos:"l"}
                    {box:"b" pos:"r"}
                    {box:"r" pos:"r"}
                    {box:"r" pos:"l"}
                    ])
      end
      
      leavephase
        helptext("Leaving test")
      end
      
      beforeiteration
        plus(1 1)
        #helptext("Before stimuli iteration")
      end
      
      afteriteration
        # Starting a new resultrow
        newrow()

      end
      
      iteration
        var s <- 0
        var i <- 0
        var box <- redbox
        var pos <- leftpos

        # Clicked = user answered correctly
        # Answered = did the user press any button at all
        clicked <- false
        answered <- false
        
        s <- stimulus()
        
        # Where to show the box
        if eq(s.pos "r") 
        then
          pos <- rightpos
        else
          pos <- leftpos
        end
        
        show(crosshair center)
        wait(800)
        hide(crosshair)
        wait(250)
        
        # Starting timer
        starttimer()
        
        # Deciding 2hich box should be shown and which key is the right one
        if eq(s.box "b")
        then
          box <- bluebox
          onkeypress("down" click)
        else
          box <- redbox
          onkeypress("right" click)
        end
        
        show(box pos)
        
        onanykey(answer)
        wait(1500)
        
        # Store reaction time
        i <- elapsedtime()
        
        # Disable keypresses
        onkeypress("right")
        onkeypress("down")
        onanykey()
        hide(box)

        # Computing results
        if clicked
        then 
          # User answered correctly
          helptext("user answered correctly")
          storerow("correct" true)
          storerow("rt" i)
        else
          if answered
          then
            # User answered incorrecly
            storerow("correct" false)
            storerow("rt" i)
          else
            # User didn't answer
            storerow("correct" "ommitted")
            storerow("rt" "nil")
          end
        end
        
        # Storing stimuli data in results
        storerow("stimuli" s)
     
      end
    end

    # The end, calculating result aggragations
    intermezzo-phase FinalPhase
        plus(1 1)
        
        #Storing data
        count("correct" true)
        count("correct" false)
        count("correct" "ommitted")
        average("rt")
    end

    transition
      start(Start),
      Start -> Guide,
      Guide -> Practise,
      Practise -> Repeatinstructions if(lt(practiseerrors 2)), # User passed the practise round,
                                                               # moving on to the actual test
      Practise -> Practise if(gt(practiseerrors 1)),           # Too many errors in practisround -> repeating it
      Repeatinstructions -> Test,
      Test -> FinalPhase,
      final(FinalPhase)
    end



#############
Number-Letter
#############

NumberLetter

`link to number letter`_

.. code-block:: none
   :linenos: 

    # Loading images
    gvar crosshair <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/crosshair.jpg") 
    gvar keydown <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/keydown.png") 
    gvar keyright <- imagefile("http://ocean.danielwarna.com:8081/testimages/a578c12d-4951-488d-90a6-e230b10950fa/keyright.png") 
    gvar fortsatt <- imagefile("http://ocean.danielwarna.com:8081/testimages/edd1a4bd-fb05-46c9-aa0d-d282bf761c0a/fortsatt.png") 

    gvar practiseerrors <- 0

    gvar rounds <- 4

    gvar clicked <- false
    gvar answered <- false

    gvar mess <- ""

    gvar upRect <- rectangle(65 65 2)
    gvar downRect <- rectangle(65 65 2)

    val center <- {top:220 left:500}
    val leftpos <- {top:200 left:200}
    val rightpos <- {top:200 left:600}

    val upPos <- {top:100 left:500}
    val downPos <- {top:300 left:500}

    val keypadpos <- {top:350 left:370}

    #Functions
    #------------
    function click()
        clicked <- true
    end

    function answer()
      answered <- true
      resume()
    end


    intermezzo-phase Start
        plus(1 1)
        wait(seconds(2))
        #showmsg("I denna uppgift ser du ett bokstavs-sifferpar som uppenbarar sig i en av två rutor. Ena rutan befinner sig ovanför den andra. Ifall bokstavs-sifferparet uppenbarar sig i den övre rutan, bör du trycka på nedåtpilen ↓ ifall siffran är jämn eller på högerpilen → ifall siffran är udda. Ifalll bokstavs-sifferparet uppenbarar sig i den nedre rutan, bör du trycka på nedåtpilen ↓ ifall bokstaven är en vokal eller på högerpilen → ifall bokstaven är en konsonant. Tryck på Fortsätt för att se en demonstration av uppgiften.")
        #show(fortsatt keypadpos)
        
        #onmouseclick(fortsatt {action:resume})
        #wait(45656456465)
        
        #hidemsg()
        #hide(fortsatt)
        #onmouseclick(fortsatt)
    end

    intermezzo-phase Guide

        # Up - Even
        #-----------------------
        
        #SHowing and hiding crosshair
        wait(150)
        show(crosshair center)
        wait(300)
        hide(crosshair)
        
        # Creating a msgbox containing A8
        mess <- msgbox("A8" 40)

        # Showing instructions
        showmsg("Bokstavs-sifferparet uppenbarade sig i den övre rutan, alltså bör siffran kategoriseras. Siffran är jämn,               alltså tryck på nedåtpilen.")
        show(keydown keypadpos)
        show(mess upPos)
        show(upRect upPos)
        show(downRect downPos)

        # Waiting for the right answer
        resumeonkey("down")
        wait(56465489)
        
        # Clearing screen for the next example
        hidemsg()
        hide(mess)
        hide(keydown)
        hide(upRect)
        hide(downRect)
        
        # Down - Consonant
        #-----------------------
        wait(150)
        show(crosshair center)
        wait(300)
        hide(crosshair)
        
        mess <- msgbox("G5" 40)
        
        showmsg("Bokstavs-sifferparet uppenbarade sig i den nedre rutan, alltså bör bokstaven kategoriseras. Bokstaven är en konsonant, alltså tryck på högerpilen.")
        show(keyright keypadpos)
        show(mess downPos)
        show(upRect {top:120 left:upPos.left})
        show(downRect downPos)

        resumeonkey("right")
        wait(8859789)
        
        hidemsg()
        hide(mess)
        hide(keyright)
        hide(upRect)
        hide(downRect)
        
        wait(150)
        show(crosshair center)
        wait(300)
        hide(crosshair)
        
        
        #Instructions done
        #-----------------------
        showmsg("Nu får du göra några exempeluppgifter. Ifall bokstavs-sifferparet uppenbarar sig i den övre rutan, bör du trycka på nedåtpilen ↓ ifall siffran är jämn eller på högerpilen → ifall siffran är udda. Ifalll bokstavs-sifferparet uppenbarar sig i den nedre rutan, bör du trycka på nedåtpilen ↓ ifall bokstaven är en vokal eller på högerpilen → ifall bokstaven är en konsonant. Endast rutorna och fixeringskorset kommer att vara synliga på skärmen. Svara så snabbt du kan, men undvik fel. Varje bokstavs- sifferpar är synlig på rutan tills du tryckt på en av svarsknapparna, men högst i 3 sekunder. Tryck på ↑ nedåtpilen för att börja övningsuppgiften.")

        # Asking the user to press arrow down and waiting until he does
        show(keydown keypadpos)
        resumeonkey("down")
        wait(353453453453)
        
        # Hiding everything
        hide(keydown)
        hidemsg()
        wait(seconds(3))
    end


    interaction-phase Practise

      enterphase
        helptext("Enter practise")
        
        # Show instructions again if user failed the last practiseround
        if gt(practiseerrors 1)
        then
          showmsg("Vi tar det en gång till. Ifall bokstavs-sifferparet uppenbarar sig i den övre rutan, bör du trycka på nedåtpilen ↓ ifall siffran är jämn eller på högerpilen → ifall siffran är udda. Ifalll bokstavs-sifferparet uppenbarar sig i den nedre rutan, bör du trycka på nedåtpilen ↓ ifall bokstaven är en vokal eller på högerpilen → ifall bokstaven är en konsonant. Svara så snabbt du kan, men undvik fel. Varje bokstavs-sifferpar är synlig på rutan tills du tryckt på en av svarsknapparna, men högst i 3 sekunder. Tryck på nedåtpilen för att börja övningsuppgiften")
          show(keydown keypadpos)
          resumeonkey()
          wait(353453453453)
          
          hidemsg()
          hide(keydown)
        end
        
        # Setting errors to 0
        practiseerrors <- 0
        
        # Setting stimuli
        # text: what to show, ans = correct answer, pos = where to show it
        setstimuli([{text:"A5" ans:"r" pos: upPos}
                    {text:"U9" ans:"d" pos: downPos}
                    {text:"C6" ans:"r" pos: downPos}
                    {text:"R4" ans:"d" pos: upPos}
        ])
      end
      
      leavephase
        helptext("Leaving practise")
      end
      
      beforeiteration
        plus(1 1)
        #helptext("Before stimuli iteration")
      end
      
      afteriteration
        #helptext("After stimuli iteration")
        plus(1 1)
      end
      
      iteration
        var s <- 0
        var box <- ""
        var pos <- leftpos

        clicked <- false

        s <- stimulus()
        #helptext(s)
        
        # Shwoing and hiding crosshair
        wait(150)
        show(crosshair center)
        wait(300)
        hide(crosshair)
        
        # Choosing which key corresponds to the correct answer
        # Calling click if user answers correctly
        if eq(s.ans "d")
        then
          onkeypress("down" click)
        else
          onkeypress("right" click)
        end
        
        # Showing everything
        box <- msgbox(s.text 40)
        show(box s.pos)
        show(upRect upPos)
        show(downRect downPos)
        
        # Waiting for a keypress
        resumeonkey()    
        wait(3000)
        
        # Hiding everything
        hide(box)
        hide(upRect)
        hide(downRect)
        onkeypress("right")
        onkeypress("down")

        # If user pressed the correct key -> user answerd correcty, else increase error count
        if clicked
        then 
          helptext("user answered correctly")
        else
          practiseerrors <- plus(practiseerrors 1)
        end
        
        wait(2000)
      end
    end

    # Repeating instructions
    intermezzo-phase Repeatinstructions

        showmsg("Bra jobbat! Nu börjar uppgiften. Ifall bokstavs-sifferparet uppenbarar sig i den övre rutan, bör du trycka på nedåtpilen ↓ ifall siffran är jämn eller på högerpilen → ifall siffran är udda. Ifalll bokstavs- sifferparet uppenbarar sig i den nedre rutan, bör du trycka på nedåtpilen ↓ ifall bokstaven är en vokal eller på högerpilen → ifall bokstaven är en konsonant. Svara så snabbt du kan, men undvik fel. Tryck på nedåtpilen för att börja uppgiften.")
        show(keydown keypadpos)
        resumeonkey("down")
        wait(353453453453)
        
        hidemsg()
        hide(keydown)

    end

    interaction-phase Test

      enterphase
        helptext("Enter test")
        
        # Defining stimuli for the actual test
        setstimuli([{text:"A5" ans:"r" pos: upPos}
                    {text:"C6" ans:"r" pos: downPos}
                    {text:"A9" ans:"d" pos: downPos}
                    {text:"U4" ans:"d" pos: upPos}
            ])
      end
      
      leavephase
        helptext("Leaving Test")
      end
      
      beforeiteration
        plus(1 1)
        #helptext("Before stimuli iteration")
      end
      
      # Creating a new storage row after each iteration
      afteriteration
        #helptext("After stimuli iteration")
        newrow()

      end
      
      iteration
        var s <- ""
        var i <- 0
        var box <- 0

        # User hasn't answered yet, so both are set to false
        answered <- false
        clicked <- false
        s <- stimulus()
        
        # Showing and hining crosshair
        wait(150)
        show(crosshair center)
        wait(300)
        hide(crosshair)
        
        box <- msgbox(s.text 40)
        
        # Starting timer
        starttimer()
        
        # Defining whihc button represents a correct answer
        if eq(s.ans "d")
        then
          onkeypress("down" click)
        else
          onkeypress("right" click)
        end
        
        # Showing stuff
        show(box s.pos)
        show(upRect upPos)
        show(downRect downPos)
        
        # Wait for 3 seconds or until a key is pressed
        onanykey(answer)
        wait(3000)
        
        # Time taken for user to answer
        i <- elapsedtime()
        
        # Disable keypresses
        onanykey()
        onkeypress("right")
        onkeypress("down")
        
        # Hide stuff
        hide(box)
        hide(upRect)
        hide(downRect)
        
        # clicked is set to true when the user answers correctly, 
       
        if clicked
        then 
          # So the answer is logged and the result is stored
          helptext("user answered correctly")
          storerow("correct" true)
        else
          # Else user didnt answer or answered incorrectly, answered is set to true
          # if the user pressed any button
          if answered
          then
            # User answerd, but incorrectly
            storerow("correct" false)
          else
            # User didn't answer, storing result
            storerow("correct" "ommitted")
          end
        end
        
        # Storing reactiontime
        if answered
        then
          storerow("rt" i)
        else
          storerow("rt" "nil")
        end
        
        storerow("stimuli" s)
     
      end
    end

    # Last phase, calculating results
    intermezzo-phase FinalPhase
        plus(1 1)
        count("correct" true)
        count("correct" false)
        count("correct" "ommitted")
        average("rt")
    end

    transition
      start(Start),
      Start -> Guide,
      Guide -> Practise,
      # continue to real test if there was 1 or less incorrect answers
      Practise -> Repeatinstructions if(lt(practiseerrors 2)),
      # Repeat practise if there was more than one incorrect answer
      Practise -> Practise if(gt(practiseerrors 1)),
      Repeatinstructions -> Test,
      Test -> FinalPhase,
      final(FinalPhase)
    end
