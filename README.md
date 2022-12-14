
# Project POO: POOTV / OOPTV
* Drugea Diana Ioana - 323CA

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1)

## File Structure

* src/
    * inputclasses/ - cointains all the classes that are used to parse the input from the json
        * ActionInput -> retains the information for an action
        * ContainsInput -> retains the information of a Cointains field from the Filter section of an
                            action
        * CredentialsInput -> retains all the information about the credentials of the user
        * FiltersInput -> saves the information that's stored in the Filter field of an action
        * InputData -> class that parses the entire input
        * MovieInput -> saves the information about each film
        * SortingInput -> retains the information of a Sort field from the Filter section of an action
        * UserInput -> saves the information given at input about an User
    * movieclasses/ - contains all the classes that are used for Movies
        * Movies -> the class used to save the information about each movie and modify where is the case
    * tvclasses/ - classes that are used for the flow of the program
        * TV -> the core class of the program, which controls every other class and executes the main loop
                of the program
        * actionclasses/ - classes that are used to do and parse actions
            * ActionHandler -> parses the action from the ActionInput given and calls the specific visitor based
                                on its type.
            * ChangePageVisitor -> does every "change page" action on a given page
            * OnPageVisitor -> does every "on page" action on a given page
            * PageVisitor -> the interface used by ChangePageVisitor and OnPageVisitor
            * Visitable -> interface used by every class in the Page class family
        * databaseclasses/ - classes that save all the users and movies
            * MovieCompDecrIncr -> comparator created for sorting movies in a decresing order rating wise and
                                    increasing order duration wise
            * MovieCompIncrDecr -> comparator created for sorting movies in an increasing order rating wise and
                                    decreasing order duration wise
            * MovieDatabase -> saves all the movies in the program while also giving specific Movies/List of movies
                                depending on the method used (Similar to a mySQL database)
            * UsersDatabase -> saves all the users in the program and gives specific users depending
                                on the method used (Similar to a mySQL database)
        * pageclasses/ - classes that are representing each page of the program
            * Page -> the parent class of the family of classes
            * HomepageAuth -> the homepage when the user is logged in
            * HomepageNon -> the homepage when there's no active user logged in
            * LoginPage -> the Login page
            * MoviesPage -> the page that displays all the movies
            * RegisterPage -> the Register page
            * SeeDetailsPage -> the page that displays information about a specific movie
            * UpgradesPage -> the page that lets the user buy upgrades
    * userclasses/ - contains classes that deal with the users
        * User -> class that represents a user in the program
        * Credentials -> class that saves the information about an user's name, password, country and other
                            stuff.
  * Main


## The program logic

Once the program starts, it saves all the input information from the given json file into the
specific input classes. Afterwards, tv.Initialize() is called to save all the data from input
into the databases. Then, the main loop of the program (ie tv.mainloop()) starts, which goes
through every action given at input and calls the ActionHandler to them. This class then decides
which visitor to call for the specific action and, based on the activePage and activeUser variables
in the tv class, either executes the action or not.
For example, if we're at the start of the program, we would be on HomepageNon aka the homepage for when
there's no user logged in. Then, let's say we tell the program to change the page to login. ActionHandler
would call the ChangePage visitor for the HomepageNon and see wether or not we can actually move to the
login page. For this case, we can and it will change the activePage variable to the LoginPage saved in
the TV class. If we told the program, for example, to go from HomepageNon to MoviesPage, the
ChangePageVisitor would call the createOutputError method for the HomepageNon to display the specific
error in the output file.
The program basically just follows certain steps:
    -> Get the type of action
    -> Call the specific PageVisitor class
    -> Either do the action or create the output for the error
    ->

## Flow scheme

Main -> TV -> ActionHandler -> ChangePageVisitor -> every child of Page class
                                                 -> MovieDatabase (when moving to a MoviePage/SeeDetails page)
                            -> OnPageVisitor -> every child of Page class
                                            -> UsersDatabase
                                            -> MovieDatabase
"->" = x calls y class

## Problems encountered

Honestly, trying to find some details about the program implementation that we're NOT found
on OCW, such as the rating having to be lower than 6.
Had to basically go through each reff file and compare it with my output just to SEE these small
details that we're omited.
