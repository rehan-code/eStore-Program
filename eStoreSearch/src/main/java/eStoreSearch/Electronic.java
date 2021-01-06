/**
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 */
package eStoreSearch;

public class Electronic extends Product{
    private String maker;

    /**
     * Constructor for the class. Assigns the parameters to the appropriate instance variables.
     * @param productID
     * @param description
     * @param year
     */
    public Electronic (int productID, String description, int year) throws Exception {
        super(productID, description, year);
    }

    public void setMaker (String maker) {
        this.maker = maker;
    }

    public String getMaker () {
        return this.maker;
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
        System.out.println("maker: " + getMaker());
    }

    @Override
    public String toString () {
        String ogString = super.toString();
        ogString = ogString.replace("product", "electronics");
        return (ogString + "maker = \"" + getMaker() + "\"\n");

    }

    @Override
    public String toString2 () {
        String ogString = super.toString2();
        return (ogString + "\nmaker: " + getMaker() + "\n\n");

    }

    @Override
    public boolean equals (Object tempObject) {
        if (tempObject == null) {
            return false;
        }else if(getClass() != tempObject.getClass()){
            return false;
        }else {
            Electronic tempElectronic = (Electronic)tempObject;
            return (getProductID()==(tempElectronic.getProductID()) && getDescription().equals(tempElectronic.getDescription()) 
                && (getPrice()==(tempElectronic.getPrice())) && (getYear()==(tempElectronic.getYear())));// && getMaker().equals(tempElectronic.getMaker()));
        }
    }
}