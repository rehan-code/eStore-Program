/**
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 */
package eStoreSearch;

public class Product {
    private int productID;
    private String description;
    private double price;
    private int year;

    /**
     * Constructor for the class. Assigns the parameters to the appropriate instance variables.
     * @param productID
     * @param description
     * @param year
     */
    public Product(int productID, String description, int year) throws Exception {
        if( productID < 1000000 && productID > 0) { 
            this.productID = productID;
        }else {
            throw new Exception("error: Product ID not in valid range of 6 digits");
        }

        if (description.isEmpty()) {
            throw new Exception("error: Description field is empty");
        }else {
            this.description = description;
        }

        if( year < 10000 && year > 999) { 
            this.year = year;
        }else {
            throw new Exception("error: Year not in valid range of 1000 to 9999");
        }
    }

    public void setProductID (int productID) throws Exception {
        if( productID < 1000000 && productID > 0) { 
            this.productID = productID;
        }else {
            throw new Exception("error: Product ID not in valid range of 6 digits");
        }
    }

    public void setDescription (String description) throws Exception {
        if (description.isEmpty()) {
            throw new Exception("error: Description field is empty");
        }else {
            this.description = description;
        }
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public void setYear (int year) throws Exception {
        if( year < 10000 && year > 999) { 
            this.year = year;
        }else {
            throw new Exception("error: Year not in valid range of 1000 to 9999");
        }
    }

    public int getProductID () {
        return this.productID;
    }

    public String getDescription () {
        return this.description;
    }

    public double getPrice () {
        return this.price;
    }

    public int getYear () {
        return this.year;
    }

    /**
     * Prints the object with all its data values.
     */
    public void print() {
        String formattedID = String.format("%06d", getProductID());
        System.out.println();
        System.out.println("Product ID: " + formattedID);
        System.out.println("Description: " + getDescription());
        System.out.println("Price: " + getPrice());
        System.out.println("Year: " + getYear());
    }

    public String toString () {
        String formattedID = String.format("%06d", getProductID());
        return ("type = \"product\"\n" + "ProductID = \"" + formattedID + "\"\n" + 
                "description = \"" + getDescription() + "\"\n" + "price = \"" + 
                getPrice() + "\"\n" + "year = \"" + getYear() + "\"\n");
    }

    public String toString2() {
        String formattedID = String.format("%06d", getProductID());
        return "Product ID: " + formattedID + "\nDescription: " + getDescription() + "\nPrice: " + getPrice() + "\nYear: " + getYear();
    }

    public boolean equals (Object tempObject) {
        if (tempObject == null) {
            return false;
        }else if(getClass() != tempObject.getClass()){
            return false;
        }else {
            Product tempProduct = (Product)tempObject;
            return (getProductID()==(tempProduct.getProductID()) && getDescription().equals(tempProduct.getDescription()) 
                && (getPrice()==(tempProduct.getPrice())) && (getYear()==(tempProduct.getYear())) );
        }
    }
}