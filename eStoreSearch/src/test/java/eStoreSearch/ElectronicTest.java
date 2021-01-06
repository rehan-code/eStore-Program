package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class ElectronicTest {

    @Test public void testConstructor() {
        Electronic testElectronic = null;;
        try {
            testElectronic = new Electronic(123456, "This is a test", 1234);
        } catch (Exception e) {
        }
        assertEquals("testElectronic's product ID should be: 123456", 123456, testElectronic.getProductID());
        assertEquals("testElectronic's description should be: This is a test", "This is a test", testElectronic.getDescription());
        assertEquals("testElectronic's Year should be: 1234", 1234, testElectronic.getYear());
    }
    
    @Test public void testSetProductID() {
        Electronic testElectronic = null;
        try {
            testElectronic = new Electronic(123455, "123", 1233);
            testElectronic.setProductID(123456);
        } catch (Exception e) {
        }
        assertEquals("testElectronic's product ID should be: 123456", 123456, testElectronic.getProductID());
    }

    @Test public void testSetDescription() {
        Electronic testElectronic = null;
        try {
            testElectronic = new Electronic(123455, "123", 1233);
            testElectronic.setDescription("This is a test description");
        } catch (Exception e) {
        }
        assertEquals("testElectronic's description should be: This is a test description", "This is a test description", testElectronic.getDescription());
    }

    @Test public void testSetPrice() {
        Electronic testElectronic = null;
        try {
            testElectronic = new Electronic(123455, "123", 1233);
        } catch (Exception e) {
        }
        testElectronic.setPrice(12.5);
        assertEquals("testElectronic's Price should be: 12.5", 12.5, testElectronic.getPrice(), 0.01);
    }

    @Test public void testSetYear() {
        Electronic testElectronic = null;
        try {
            testElectronic = new Electronic(123455, "123", 1233);
            testElectronic.setYear(1234);
        } catch (Exception e) {
        }
        assertEquals("testElectronic's Year should be: 1234", 1234, testElectronic.getYear());
    }

    @Test public void testSetMaker() {
        Electronic testElectronic = null;
        try {
            testElectronic = new Electronic(123455, "123", 1233);
        } catch (Exception e) {
        }
        testElectronic.setMaker("this is a test");
        assertEquals("testElectronic's Maker should be: this is a test", "this is a test", testElectronic.getMaker());
    }

    @Test public void testToString() {
        Electronic testElectronic = null;
        try {
            testElectronic = new Electronic(123456, "this is a test", 1234);
        } catch (Exception e) {
        }
        testElectronic.setPrice(12.5);
        testElectronic.setMaker("user");
        assertEquals("type = \"electronics\"\nProductID = \"123456\"\ndescription = \"this is a test\"\nprice = \"12.5\"\nyear = \"1234\"\nmaker = \"user\"\n", testElectronic.toString());
    }

    @Test public void testEquals() {
        Electronic testElectronic = null;
        Electronic testElectronic1 = null;
        try {
            testElectronic = new Electronic(123456, "this is a test", 1234);
            testElectronic1 = new Electronic(111111, "this is a test", 1234);
        } catch (Exception e) {
        }
        assertTrue(testElectronic.equals(testElectronic));
        assertFalse(testElectronic.equals(testElectronic1));
    }
}