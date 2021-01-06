/**
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 */
package eStoreSearch;

public class Book extends Product{
    private String author;
    private String publisher;

    /**
     * Constructor for the class. Assigns the parameters to the appropriate instance variables.
     * @param productID
     * @param description
     * @param year
     */
    public Book (int productID, String description, int year) throws Exception {
        super(productID, description, year);
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public String getAuthor () {
        return this.author;
    }

    public void setPublisher (String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher () {
        return this.publisher;
    }

    /**
     * Prints the object with all its data values.
     */
    @Override
    public void print() {
        String formattedID = String.format("%06d", getProductID());
        System.out.println();
        System.out.println("Product ID: " + formattedID);
        System.out.println("Description: " + getDescription());
        System.out.println("Price: " + getPrice());
        System.out.println("Year: " + getYear());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publisher: " + getPublisher());
    }

    @Override
    public String toString () {
        String ogString = super.toString();
        ogString = ogString.replace("product", "book");
        return (ogString + "author(s) = \"" + getAuthor() + "\"\n" + "publisher = \"" + getPublisher() + "\"\n");
    }

    @Override
    public String toString2 () {
        String ogString = super.toString2();
        return (ogString + "\nAuthor: " + getAuthor() + "\nPublisher: " + getPublisher() + "\n\n");

    }

    @Override
    public boolean equals (Object tempObject) {
        if (tempObject == null) {
            return false;
        }else if(getClass() != tempObject.getClass()){
            return false;
        }else {
            Book tempBook = (Book)tempObject;
            return (getProductID()==(tempBook.getProductID()) && getDescription().equals(tempBook.getDescription()) 
                && (getPrice()==(tempBook.getPrice())) && (getYear()==(tempBook.getYear())));
                // && getAuthor().equals(tempBook.getAuthor()) && getPublisher().equals(tempBook.getPublisher()));
        }
    }
}