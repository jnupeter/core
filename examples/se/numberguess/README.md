Weld SE Numberguess example (Swing)
===================================

Running the Example
-------------------
As with all Weld SE applications, this example is executed
by starting Java with `org.jboss.weld.environment.se.StartMain`
as the main class. Of course you will need all of the relevant jar dependencies
on your classpath, which is most easily done by loading the project into your
favourite Maven-capable IDE and running it from there.

If you are using m2eclipse, and the application won't start, make sure you uncheck 
"Resolve dependencies from Workspace projects" in the Maven properties panel. Then
run a full build to ensure all classes are in the right place. 

To run this example using Maven directly:

 - Ensure that Maven 3 is installed and in your `PATH`
 - Ensure that the `JAVA_HOME` environment variable is pointing to your JDK installation
 - Open a command line or terminal window in the `examples/se/numberguess` directory
 - Execute the following command

        mvn -Drun

Swing Example: Number Guess
---------------------------
Here's an example of a Swing application, Number Guess, similar to the example in chapter 7.
This example shows how to use the Weld SE extension in a Java SE based Swing application
with no EJB or servlet dependencies.

In the Number Guess application you are given 10 attempts to guess a number between 1 and 100. After each attempt, you will be told whether you are too high, or too low. 

The game's main logic is located in `Game.java`. In this example, it differs from the web application version in several ways:

* the bean is application scoped rather than session scoped, since an instance
    of a Swing application typically represents a single 'session'.

* Notice that the bean is not named, since it doesn't need to be accessed via EL.

* In Java SE there is no JSF `FacesContext` to which messages can be added. Instead
    the Game class provides additional information about the state of the current game
    including:

    * if the game has been won or lost,

    * if the most recent guess was invalid.

    This allows the Swing UI to query the state of the game, which it does indirectly
    via a class called `MessageGenerator`, in order to determine the appropriate messages
    to display to the user during the game.

* Since there is no dedicated validation phase, validation of user input is performed
    during the `check()` method.

* The `reset()` method makes a call to the injected `rndGenerator` in order to get
    the random number at the start of each game. Note that it cannot use
    `manager.getInstanceByType(Integer.class, new AnnotationLiteral<Random>(){})`
    as the JSF example does because there will not be any active contexts like there
    is during a JSF request.

For a deeper look into the SE Number Guess example, please refer to chapter 7.2 of the reference documentation.