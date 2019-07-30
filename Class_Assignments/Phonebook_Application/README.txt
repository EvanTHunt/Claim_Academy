-----------------------------------------------------------------------------

The "PhoneBookApplication" is a basic Java record management software that interacts with a user via the console. The application stores a list of entries, each composed of a name, address, and phone number. The user may choose to perform the following actions when interacting with the application:

-add a new entry
-search for entries by various criteria and display the search results
-update an entry
-delete an entry
-display all entries

-----------------------------------------------------------------------------

The functionality of the application is structured with several classes: 

The primary classes are "Person", "Address", and "PhoneBook". The "Person" and "Address" classes are dummy classes used to hold information only. "Person" objects contain a name, phone number, and an "Address" object. The "PhoneBook" class contains an array of "Person" objects and all methods that operate on that array, for example, deleting a "Person" object from the array. 

In addition to the primary classes, there are two custom exception classes ("NumberEntriesInvalidException" and "PhoneNumberInvalidException"), each extending the "RuntimeException" class. These two classes are used in the "PhoneBook" class to be thrown when the user inputs information with an incorrect format.

The "PhoneBookApplication" class houses the MAIN function in which the entire application runs. Before any operations are performed, an instance of the "PhoneBook" class is instantiated. The application then enters an operations loop until terminated by the user. Each cycle of the application will query the user for the operation to be performed. The subsequent operation will query the user for any required data as well as output any data requested. The application finally queries the user whether to continue or terminate the program. 