/**
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 */
package eStoreSearch;

import java.util.*;
import java.io.*;

public class EStoreSearch {
    
    static ArrayList<Product> productList = new ArrayList<Product>();
    static HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
    static String argument0 = null;
    
    /**
     * main function to run the EStoreSearch
     * @param args
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            loadData(productList, args[0], index);
            argument0 = args[0];
        }
        EStoreSearchGUI gui = new EStoreSearchGUI( );
        gui.setVisible(true);
    }

    /**
     * Prints out the menu
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("(1) Add a product.");
        System.out.println("(2) Search for a product");
        System.out.println("(3) Quit the program.");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * function creates a Book object that get added to the book arraylist
     * takes in valid instance variables from the user
     * @param productList
     * @return the Book structure
     */
    public static Book createBook(ArrayList<Product> productList, String inputPID, String tempdesc, String inputYear, String inputPrice, String tempAuthor, String tempPublisher) throws Exception {
        //Scanner scannerObj = new Scanner(System.in);
        int tempID = 0;

        if (inputPID.isEmpty()) {
            throw new Exception("error: Product ID must be supplied");
        }
        if (inputYear.isEmpty()) {
            throw new Exception("error: Year must be supplied");
        }

        try {
            tempID = Integer.parseInt(inputPID);
        } catch (Exception e) {
            throw new Exception("error: Product ID is invalid and cannot be converted to an integer");
        }
        if (isDuplicateID(productList, tempID)) {
            throw new Exception("error: Product ID enterred is a duplicate");
        }

        int tempYear = 0;
        try {
            tempYear = Integer.parseInt(inputYear);
        } catch (Exception e) {
            throw new Exception("error: Year is invalid and cannot be converted to an integer");

        }

        double tempPrice = 0;

        if (!inputPrice.isEmpty()) {
            try {
                tempPrice = Double.parseDouble(inputPrice);
            } catch (Exception e) {
                throw new Exception("error: price is invalid and cannot be converted to an double");
            }
        }

        Book tempBook;
        try {
            tempBook = new Book(tempID, tempdesc, tempYear);
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }

        if (tempPrice != 0.0) {
            tempBook.setPrice(tempPrice);
        }

        //tempAuthor = tempAuthor.replace('\n', '\0');
        if (!tempAuthor.isEmpty()) {
            tempBook.setAuthor(tempAuthor);
        }

        //tempPublisher = tempPublisher.replace('\n', '\0');
        if (!tempPublisher.isEmpty()) {
            tempBook.setPublisher(tempPublisher);
        }

        return tempBook;
    }

    /**
     * function creates an Electronic object that get added to the book arraylist
     * takes in valid instance variables from the user
     * @param productList
     * @return the Electronic structure
     */
    public static Electronic createElectronic(ArrayList<Product> productList, String inputPID, String tempdesc, String inputYear, String inputPrice, String tempMaker) throws Exception {
        int tempID = 0;

        if (inputPID.isEmpty()) {
            throw new Exception("error: Product ID must be supplied");
        }
        if (inputYear.isEmpty()) {
            throw new Exception("error: Year must be supplied");
        }

        try {
            tempID = Integer.parseInt(inputPID);
        } catch (Exception e) {
            throw new Exception("error: Product ID is invalid and cannot be converted to an integer");
        }
        if (isDuplicateID(productList, tempID)) {
            throw new Exception("error: Product ID enterred is a duplicate");
        }

        int tempYear = 0;
        try {
            tempYear = Integer.parseInt(inputYear);
        } catch (Exception e) {
            throw new Exception("error: Year is invalid and cannot be converted to an integer");
        }

        double tempPrice = 0;

        if (!inputPrice.isEmpty()) {
            try {
                tempPrice = Double.parseDouble(inputPrice);
            } catch (Exception e) {
                throw new Exception("error: price is invalid and cannot be converted to an double");
            }
        }

        Electronic tempElectronic;
        try {
            tempElectronic = new Electronic(tempID, tempdesc, tempYear);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        if (tempPrice != 0.0) {
            tempElectronic.setPrice(tempPrice);
        }
        
        //tempMaker = tempMaker.replace('\n', '\0');
        if (!tempMaker.isEmpty()) {
            tempElectronic.setMaker(tempMaker);
        }

        return tempElectronic;
    }

    /**
     * gets a valid productID from the user
     * @param productList
     * @return the valid ProductID integer
     */
    public static int getUserProductID(ArrayList<Product> productList) {
        Scanner scannerObj = new Scanner(System.in);
        int tempInput = -1;
        String junk;

        while (!(tempInput > -1 && tempInput < 1000000) || isDuplicateID(productList, tempInput)) {
            if (isDuplicateID(productList, tempInput)) {
                System.out.println("The entered Product ID already exists.");
            }
            System.out.print("Enter the unique 6 digit product ID (required): ");
            try {
                junk = scannerObj.nextLine();
                tempInput = Integer.parseInt(junk);
            } catch (Exception e) {
                tempInput = -1;
            }
        }

        return tempInput;
    }

    /**
     * gets a string from the user
     * @return the string obtained from the user
     */
    public static String getUserStringInput() {
        Scanner scannerObj = new Scanner(System.in);
        String descInput= "";

        descInput = scannerObj.nextLine();
        descInput = descInput.replace('\n', '\0');
        
        return descInput;
    }

    /**
     * get a valid year from the user
     * @return the valid year int
     */
    public static int getUserYear() {
        Scanner scannerObj = new Scanner(System.in);
        int tempInput = -1;
        String junk;

        while (!(tempInput >= 1000 && tempInput <= 9999)) {
            System.out.print("Enter the 4 digit Year (required): ");
            try {
                junk = scannerObj.nextLine();
                tempInput = Integer.parseInt(junk);
            } catch (Exception e) {
                tempInput = -1;
            }
        }

        return tempInput;
    }

    /**
     * gets a valid prive from the user
     * @return the valid price double
     */
    public static double getUserPrice() {
        Scanner scannerObj = new Scanner(System.in);
        String tempInput;
        boolean isEmpty;
        double tmpPrice = 0.0;

        System.out.print("Enter the price: ");
        tempInput = scannerObj.nextLine();
        tempInput = tempInput.replace('\n', '\0');
        isEmpty = tempInput.isEmpty();
        if (isEmpty == false) {
            tmpPrice = Double.parseDouble(tempInput);
        }

        return tmpPrice;
    }

    /**
     * This method takes in the search values entered by the user and executes the appropriate 
     * searches and prints the appropriate product to match.
     * @param bookList
     * @param electronicList
     * @param searchID
     * @param searchKeywords
     * @param searchTimePeriod
     */
    public static ArrayList<Integer> printSearchValues(ArrayList<Product> productList, HashMap<String, ArrayList<Integer>> index, int searchID, String [] searchKeywords, String searchTimePeriod) {
        int i;
        int yearStatus = -1;
        int year = -1;
        int year2 = -1;
        String tempString[];
        ArrayList<Integer> searchIndexes = new ArrayList<Integer>();
        //status of -1 means invalid year, status of 0 means to check year and earlier (-year / <=), status of 1 means only that year (==),
        //status of 2 means to check that year and later (year- / >=), status of 3 means check between 2 year (year-year)
        
        if (!searchTimePeriod.isEmpty()) {
            if (searchTimePeriod.length() == 9 && Character.compare(searchTimePeriod.charAt(4), '-') == 0) {
                tempString = searchTimePeriod.split("[- ]+");
                year = Integer.parseInt(tempString[0]);
                year2 = Integer.parseInt(tempString[1]);
                if (year >= 1000 && year <= 9999) {
                    if (year2 >= 1000 && year2 <= 9999) {
                        yearStatus = 3;
                    } else {
                        yearStatus = 2;
                    }
                    
                }
            } else if (searchTimePeriod.length() == 5 && Character.compare(searchTimePeriod.charAt(0), '-') == 0) {
                tempString = searchTimePeriod.split("[- ]+");
                year = Integer.parseInt(tempString[1]);
                if (year >= 1000 && year <= 9999) {
                    yearStatus = 0;
                }
            } else if (searchTimePeriod.length() == 4) {
                year = Integer.parseInt(searchTimePeriod);
                if (year >= 1000 && year <= 9999) {
                    yearStatus = 1;
                }
            } else if (searchTimePeriod.length() == 5 && Character.compare(searchTimePeriod.charAt(4), '-') == 0) {
                tempString = searchTimePeriod.split("[- ]+");
                year = Integer.parseInt(tempString[0]);
                if (year >= 1000 && year <= 9999) {
                    yearStatus = 2;
                }
            }
        }

        //Call the appropriate function based on the entered searches.
        if (searchID != -1 && !(searchKeywords[0].isEmpty())) {//user enterred both keywords and search ID
            ArrayList<Integer> matchedHashList = getMatchingHash(index, searchKeywords);//getting the hash of the matching keywords

            if (yearStatus != -1) {//if the entered a valid Year to search.
                for (Integer indexNo : matchedHashList) {//getting the elements the index
                    i = indexNo;
                    if (yearStatus == 0 && (productList.get(i)).getYear() <= year && (productList.get(i)).getProductID() == searchID) {//if year status 0 and searchId and keywords match
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 1 && (productList.get(i)).getYear() == year && (productList.get(i)).getProductID() == searchID) {//if year status 1 and searchId matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 2 && (productList.get(i)).getYear() >= year && (productList.get(i)).getProductID() == searchID) {//if year status 2 and searchId matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 3 && (productList.get(i)).getYear() >= year && (productList.get(i)).getYear() <= year2 && (productList.get(i)).getProductID() == searchID) {//if year status 3 and searchId matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    }

                }
            } else {//if invalid year
                for (Integer indexNo : matchedHashList) {
                    if ((productList.get(indexNo)).getProductID() == searchID) {
                        //(productList.get(indexNo)).print();
                        searchIndexes.add(indexNo);
                    }
                }
            }

        } else if (searchID != -1 && (searchKeywords[0].isEmpty())) {//user search ID but no keywords
            
            if (yearStatus != -1) {//if the entered a valid Year to search.
                for (i=0; i < productList.size() && !productList.isEmpty(); i++) {//getting the elements from booklist
                    if (yearStatus == 0 && (productList.get(i)).getYear() <= year && (productList.get(i)).getProductID() == searchID) {//if year status 0 and searchId matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 1 && (productList.get(i)).getYear() == year && (productList.get(i)).getProductID() == searchID) {//if year status 1 and searchId matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 2 && (productList.get(i)).getYear() >= year && (productList.get(i)).getProductID() == searchID) {//if year status 2 and searchId matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 3 && (productList.get(i)).getYear() >= year && (productList.get(i)).getYear() <= year2 && (productList.get(i)).getProductID() == searchID) {//if year status 3 and searchId matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    }

                }
            } else {//if invalid year
                for (i=0; i < productList.size() && !productList.isEmpty(); i++) {
                    if ((productList.get(i)).getProductID() == searchID) {
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    }
                }
            }

        } else if (searchID == -1 && !(searchKeywords[0].isEmpty())) {//user entered keywords and no product ID
            ArrayList<Integer> matchedHashList = getMatchingHash(index, searchKeywords);//getting the hash of the matching keywords

            if (yearStatus != -1 && !matchedHashList.isEmpty()) {//if the entered a valid Year to search.
                for (Integer indexNo : matchedHashList) {//getting the elements from booklist
                    i = indexNo;
                    if (yearStatus == 0 && (productList.get(i)).getYear() <= year) {//if year status 0 and keyword matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 1 && (productList.get(i)).getYear() == year) {//if year status 1 and keyword matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 2 && (productList.get(i)).getYear() >= year) {//if year status 2 and keyword matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 3 && (productList.get(i)).getYear() >= year && (productList.get(i)).getYear() <= year2) {//if year status 3 and keyword matches
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    }

                }
            } else if(!matchedHashList.isEmpty()) {//if invalid year
                for (Integer indexNo : matchedHashList) {
                    //(productList.get(indexNo)).print();
                    searchIndexes.add(indexNo);
                }
                
            }
            
        } else {//if the user didnt enter either of the 2 previous searches

            if (yearStatus != -1) {//if the entered a valid Year to search.
                for (i=0; i < productList.size() && !productList.isEmpty(); i++) {//getting the elements from booklist
                    if (yearStatus == 0 && (productList.get(i)).getYear() <= year) {//if year status 0
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 1 && (productList.get(i)).getYear() == year) {//if year status 1
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 2 && (productList.get(i)).getYear() >= year) {//if year status 2
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    } else if (yearStatus == 3 && (productList.get(i)).getYear() >= year && (productList.get(i)).getYear() <= year2) {//if year status 3
                        //(productList.get(i)).print();
                        searchIndexes.add(i);
                    }

                }
            } else {//if they didnt enter any search values. print the entire book and electronic library.
                //printLists(productList);
                for (i=0; i < productList.size() && !productList.isEmpty(); i++) {
                    //(productList.get(i)).print();
                    searchIndexes.add(i);
                }
            }

        }
        return searchIndexes;
    }

    /**
     * this method prints all the objects in both the lists
     * @param bookList
     * @param electronicList
     */
    public static void printLists(ArrayList<Product> productList) {
        int i;

        for (i=0; i < productList.size() && !productList.isEmpty(); i++) {
            (productList.get(i)).print();
        }
    }

    /**
     * checks if the keyword(s) matches any values in the description
     * @param description
     * @param searchKeywords
     * @return true if there is a match and false if there isnt
     */
    public static boolean keywordIsMatch(String description, String [] searchKeywords) {
        int i, j;
        String descriptionWords[] = description.split("[ ,.]+");
        boolean match = false;
        int nel = 0;

        for (i = 0; i<(searchKeywords.length); i++) {
            match = false;
            for (j = 0; j<(descriptionWords.length) && match == false; j++) {
                
                match = searchKeywords[i].equalsIgnoreCase(descriptionWords[j]);
            }
            if (match == true) {
                nel++;
            }
        }
        match = false;
        if (nel == searchKeywords.length) {
            match = true;
        }

        return match;
    }

    /**
     * This functions checks if the product ID is a duplicate of one previously entered.
     * @param productList
     * @param ID
     * @return the boolean true if there is a duplicate and and false if there is not
     */
    public static boolean isDuplicateID(ArrayList<Product> productList, int ID) {
        boolean isDup = false;
        int i;

        for (i=0; i < productList.size() && isDup == false; i++) {
            isDup = ID == (productList.get(i)).getProductID();//checks if the value is a duplicate by comparing with other elements in the list
        }
        return isDup;
    }

    /**
     * This function loads the data from the given filename in src/main/resources to the product list and creates the hash map
     * @param productList
     * @param filename
     * @param index
     * @return  true if successfull, false if not
     */
    public static boolean loadData(ArrayList<Product> productList, String filename, HashMap<String, ArrayList<Integer>> index) {
        boolean success = true;
        Book temp1;
        Electronic temp2;
        String pid, desc, price, year;
        String[] arg = {""};
        try {
            File fp = new File("src/main/resources/" + filename);
            Scanner scanner = new Scanner(fp);
        
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                arg = temp.split("\"");
                if (arg.length > 1 && arg[1].equals("book")) {//if the data entry is of a book
                    arg = (scanner.nextLine()).split("[\"]");
                    pid = arg[1];
                    arg = (scanner.nextLine()).split("[\"]");
                    desc = arg[1];
                    if (arg.length > 2) {//to account for if the character " is in the description. we dont want to split it.
                        for (int i = 2; i < arg.length; i++) {
                            desc = desc + "\"" + arg[i];
                        }
                    }
                    addHash(index, desc, productList.size());//adding the description value to the hash table.
                    arg = (scanner.nextLine()).split("[\"]");
                    price = arg[1];
                    arg = (scanner.nextLine()).split("[\"]");
                    year = arg[1];
                    temp1 = new Book(Integer.parseInt(pid), desc, Integer.parseInt(year));

                    if (!price.equals("0.0")) {
                        temp1.setPrice(Double.parseDouble(price));
                    }
                    arg = (scanner.nextLine()).split("[\"]");
                    if (!arg[1].equals("null")) {
                        temp1.setAuthor(arg[1]);
                    }
                    arg = (scanner.nextLine()).split("[\"]");
                    if (!arg[1].equals("null")) {
                        temp1.setPublisher(arg[1]);
                    }
                    productList.add(temp1);

                }else if (arg.length > 1 && arg[1].equals("electronics")) {//if the data entry is an electronic
                    arg = (scanner.nextLine()).split("[\"]");
                    pid = arg[1];
                    arg = (scanner.nextLine()).split("[\"]");
                    desc = arg[1];
                    if (arg.length > 2) {//to account for it the character " is in the description. we dont want to split it.
                        for (int i = 2; i < arg.length; i++) {
                            desc = desc + "\"" + arg[i];
                        }
                    }
                    addHash(index, desc, productList.size());//adding the description value to the hash table.
                    arg = (scanner.nextLine()).split("[\"]");
                    price = arg[1];
                    arg = (scanner.nextLine()).split("[\"]");
                    year = arg[1];
                    temp2 = new Electronic(Integer.parseInt(pid), desc, Integer.parseInt(year));

                    if (!price.equals("0.0")) {
                        temp2.setPrice(Double.parseDouble(price));
                    }
                    arg = (scanner.nextLine()).split("[\"]");
                    if (!arg[1].equals("null")) {
                        temp2.setMaker(arg[1]);
                    }
                    productList.add(temp2);
                }
            }
        } catch (Exception e) {
            success = false; // if failed (file not found)
        }
    
        return success;
    }

    /**
     * saves the data from productList to the enterred filename in src/main/resources
     * @param productList
     * @param filename
     * @return true on success, false on failure
     */
    public static boolean saveData(ArrayList<Product> productList, String filename) {
        boolean success = true;
        try {
            PrintWriter fileWriter = new PrintWriter("src/main/resources/" + filename, "UTF-8");
            for (int j = 0; j < productList.size(); j++) {
                fileWriter.print(  (productList.get(j)).toString() );
            }
            fileWriter.close();
        } catch (Exception e) {
            success = false;
        }
    
        return success;
    }

    /**
     * creates a hash from the description and saves the indexNo to that hash in the table
     * @param index
     * @param description
     * @param indexNo
     */
    public static void addHash(HashMap<String, ArrayList<Integer>> index, String description, int indexNo) {
        description = description.toLowerCase();
        String words[] = description.split("[ ,.\"-]+");
        for (String word : words) {//for every word in the description
            if (index.containsKey(word)) {//if that hash is already there then add the indexno to the hashlist
                ArrayList<Integer> list = index.get(word);
                list.add(indexNo);
            } else {//otherwise create a new array list for that hashed word
                ArrayList<Integer> newlist = new ArrayList<Integer>();
                newlist.add(indexNo);
                index.put(word, newlist);
            }
        }
    }

    /**
     * searches the hash table to get values for each keyword and gets the intersection of all keywords to return
     * @param index
     * @param searchKeywords
     * @return an integer arraylist of the index values that have all the matching keywords
     */
    public static ArrayList<Integer> getMatchingHash(HashMap<String, ArrayList<Integer>> index, String[] searchKeywords) {
        ArrayList<Integer> matchingHash = new ArrayList<Integer>();

        if (!index.isEmpty()) {
            if (index.containsKey(searchKeywords[0].toLowerCase())) {
                matchingHash = index.get(searchKeywords[0].toLowerCase());
            }
            for (String keyword : searchKeywords) {
                if (index.containsKey(keyword.toLowerCase())) {
                    matchingHash.retainAll(index.get(keyword.toLowerCase()));
                } else {
                    matchingHash.clear();
                }
            }
        }
        return matchingHash;
    }
}

