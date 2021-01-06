package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test public void testConstructor() {
        Product testProduct = null;
        try {
            testProduct = new Product(123456, "This is a test", 1234);
        } catch (Exception e) {
        }
        assertEquals("testProduct's product ID should be: 123456", 123456, testProduct.getProductID());
        assertEquals("testProduct's description should be: This is a test", "This is a test", testProduct.getDescription());
        assertEquals("testProduct's Year should be: 1234", 1234, testProduct.getYear());
    }

    @Test public void testSetProductID() {
        Product testProduct = null;
        try {
            testProduct = new Product(123455, "123", 1234);
            testProduct.setProductID(123456);
        } catch (Exception e) {
        }
        assertEquals("testProduct's product ID should be: 123456", 123456, testProduct.getProductID());
    }

    @Test public void testSetDescription() {
        Product testProduct = null;
        try {
            testProduct = new Product(123455, "123", 1234);
            testProduct.setDescription("This is a test description");
        } catch (Exception e) {
        }
        assertEquals("testProduct's description should be: This is a test description", "This is a test description", testProduct.getDescription());
    }

    @Test public void testSetPrice() {
        Product testProduct = null;
        try {
            testProduct = new Product(123455, "123", 1234);
        } catch (Exception e) {
        }
        testProduct.setPrice(12.5);
        assertEquals("testProduct's Price should be: 12.5", 12.5, testProduct.getPrice(), 0.01);
    }

    @Test public void testSetYear() {
        Product testProduct = null;
        try {
            testProduct = new Product(123455, "123", 1233);
            testProduct.setYear(1234);
        } catch (Exception e) {
        }
        assertEquals("testProduct's Year should be: 1234", 1234, testProduct.getYear());
    }

    @Test public void testToString() {
        Product testProduct = null;
        try {
            testProduct = new Product(123456, "this is a test", 1234);
        } catch (Exception e) {
        }
        testProduct.setPrice(12.5);
        assertEquals("type = \"product\"\nProductID = \"123456\"\ndescription = \"this is a test\"\nprice = \"12.5\"\nyear = \"1234\"\n", testProduct.toString());
    }

    @Test public void testEquals() {
        Product testProduct = null;
        Product testProduct1 = null;
        try {
            testProduct = new Product(123456, "this is a test", 1234);
            testProduct1 = new Product(111111, "this is a test", 1234);
        } catch (Exception e) {
        }
        assertTrue(testProduct.equals(testProduct));
        assertFalse(testProduct.equals(testProduct1));
    }
}