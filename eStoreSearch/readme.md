# ASSIGNMENT 1 README
    Student Name: Rehan Nagoor Mohideen
    Student ID: 1100592

## Description
    This program is an EStoreSearch. The user can add either a Book product or an Electronic
    Product and enter the ProductID, Description, Year and other fields and also search for 
    products on the store.

## Assumptions and Limitations
    My program accepts the product ID as long as it is in the range of 1-999999 (inclusive) 
        and prepends and leading zero when required to make it 6 digits when printing out.
    The program expects valid data types as input and may sometimes not work otherwise.
    The program checks whether the entered are within the acceptable ranges. StudentID 
        must be 6 characters and the year should be in the range of 1000-9999.
    Files are saved to and read from src/main/resources/ from the gradle folder.
    Assuming that all the values in the file have quotations around it to identify the values.
    Assuming that the empty elements in the file have "null" rather than left empty in the quotes.

## Building and testing
    The program can be built using: gradle build
    The program can be run using: gradle run <filename>
    The program can be tested with the junit test files using: gradle test
    The user can add junit testfiles if needed.
    There are a total of 4 files and 4 corresponding test files.
    Book.java and Electronic.java are the classes from the Product.java superclass.
    EStoreSearch.java contains the main function and the search methods.

## Testing Plan

### Product, Book, Electronic
1. Testing setters, getters and constructors for all instance variables by setting their 
    values and using assertEquals and getters to compare and check if right.
2. Testing if ToString prints the string correctly using assertEquals.
3. Testing if the equals functions compare the objects correctly using asserEquals.

### EStoreSearch
1. Testing isDuplicateID by creating an arraylist for Electronic and Book and add array 
    elements and testing with assertTrue and assertFalse with appropriate ProductID values.

2. Testing keywordIsMatch by creating a string and an array of keywords. Using assertTrue 
    and assertFalse to find a match when a keyword matchs the string and not otherwise.

3. Testing addHash and getMatchingHash by first checking if it return valid arrayList for case 
    insensitive comparisons. Then addHash another element with a common keyword and 
    check if getHash returns an arrayList with both of the integer values with the 
    common keyword using asserEquals.

4. Testing saveData and loadData by first making sure there is no file in src/test/resources 
    and assertFalse with loadData with invalid file. Then using saveData to save contents of 
    a created productList to a file and assertTrue and checking if the loadData can 
    successfully load the data with assertTrue. Then making sure that the loaded index is 
    correct by matching them to expected values with assertEquals. Also making sure that 
    getting there is no index for a value that does no exist.

## Possible improvements
    The program can be made to be more object oriented than it currently is, and in a way 
    that allows more junit tests to be performed on the EStoreSearch files.