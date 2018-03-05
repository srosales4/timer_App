# In class Excersise
## CS4330 Spring 2018

AM
/
SPDL

How to save the settings, so that rotating the screen is possible without interrupting the counter?

## Answer:

By using the properties of the Model class (TimerModel.java)

##  ToDo:

Make separate method to cut down on Redundant Code

Also the there is still a problem:  When you run the timer and stop it.  It shows you the elapsed time.  If you then rotate it, it erases the elapsed time.  This is because when you rotate it it stores the values for hour min sec.  But in the OnCreate method you initialize the number to zero, so the numbers should be initialized to ?  To whatever hour min sec is.  Meaning in the very beginning, (first run) it should set hour min sec to 0?  Or to Current time?  What is the default value for the textView?  If you ommit the default values for the textView, then the time will be shown correctly.
