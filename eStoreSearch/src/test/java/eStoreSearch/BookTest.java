package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test public void testConstructor() {
        Book testBook = null;
        try {
            testBook = new Book(123456, "This is a test", 1234);
        } catch (Exception e) {
            
        }
        assertEquals("testBook's product ID should be: 123456", 123456, testBook.getProductID());
        assertEquals("testBook's description should be: This is a test", "This is a test", testBook.getDescription());
        assertEquals("testBook's Year should be: 1234", 1234, testBook.getYear());
    }

    @Test public void testSetProductID() {
         Book testBook = null;
        try {
            testBook = new Book(123455, "123", 1234);
            testBook.setProductID(123456);
        } catch (Exception e) {
        }
        assertEquals("testBook's product ID should be: 123456", 123456, testBook.getProductID());
    }

    @Test public void testSetDescription() {
         Book testBook = null;
        try {
            testBook = new Book(123455, "123", 1234);
            testBook.setDescription("This is a test description");
        } catch (Exception e) {
        }
        assertEquals("testBook's description should be: This is a test description", "This is a test description", testBook.getDescription());
    }

    @Test public void testSetPrice() {
         Book testBook = null;
        try {
            testBook = new Book(123455, "123", 1234);
        } catch (Exception e) {
        }
        testBook.setPrice(12.5);
        assertEquals("testBook's Price should be: 12.5", 12.5, testBook.getPrice(), 0.01);
    }

    @Test public void testSetYear() {
         Book testBook = null;
        try {
            testBook = new Book(123455, "123", 1233);
            testBook.setYear(1234);
        } catch (Exception e) {
        }
        assertEquals("testBook's Year should be: 1234", 1234, testBook.getYear());
    }

    @Test public void testSetAuthor() {
         Book testBook = null;
        try {
            testBook = new Book(123455, "123", 1233);
        } catch (Exception e) {
        }
        testBook.setAuthor("this is a test");
        assertEquals("testBook's Author should be: this is a test", "this is a test", testBook.getAuthor());
    }

    @Test public void testSetPublisher() {
         Book testBook = null;
        try {
            testBook = new Book(123455, "123", 1233);
        } catch (Exception e) {
        }
        testBook.setPublisher("this is a test");
        assertEquals("testBook's Publisher should be: this is a test", "this is a test", testBook.getPublisher());
    }

    @Test public void testToString() {
         Book testBook = null;
        try {
            testBook = new Book(123456, "this is a test", 1234);
        } catch (Exception e) {
        }
        testBook.setPrice(12.5);
        testBook.setAuthor("user");
        testBook.setPublisher("publisher");
        assertEquals("type = \"book\"\nProductID = \"123456\"\ndescription = \"this is a test\"\nprice = \"12.5\"\n" + 
        "year = \"1234\"\nauthor(s) = \"user\"\npublisher = \"publisher\"\n", testBook.toString());
    }

    @Test public void testEquals() {
         Book testBook = null;
        Book testBook1 = null;
        try {
            testBook = new Book(123456, "this is a test", 1234);
            testBook1 = new Book(111111, "this is a test", 1234);
        } catch (Exception e) {
        }
        assertTrue(testBook.equals(testBook));
        assertFalse(testBook.equals(testBook1));
    }
}