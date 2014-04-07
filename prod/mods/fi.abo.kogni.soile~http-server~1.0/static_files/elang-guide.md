# Elang Documentation

This document contains documentation for the experiment language.

## Phases



## Bin - builtin functions

Builtin functions are functions that are called and executed from a experiment by the user. 

### onmouseclick

**onmouseclick(imagefile, {action: function, inputid: number, resettimer: bool})**

Creates a mouseclick listener for the object/image, which will be triggered when the image is clicked. The function specified in "action" is executed on each click. 

**onmouseclick(imagefile, false)**

Removes and deletes the clicklistener from the specified object.

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

**greaterthan(number1 number2) _o_r gt(number1 number2)** number1 > number2

**equal(number1 number2) _or_ eq(number1 number2)** number1 == number2



## RT - Runtime functions

Rt functions operate within the vm and should not be called by users. They are only used to manage and run the vm.