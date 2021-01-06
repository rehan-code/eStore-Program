package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class EStoreSearchTest {

    @Test public void testIsDuplicateID() {
        ArrayList<Product> productList = new ArrayList<Product>();
        Book testBook0 = null;
        Book testBook1 = null;
        try {
            testBook0 = new Book(123455, "123", 2134);
            testBook1 = new Book(111111, "123", 1234);
        } catch (Exception e) {
        }

        productList.add(testBook0);
        productList.add(testBook1);

        Electronic testElectronic0 = null;
        Electronic testElectronic1 = null;
        try {
            testElectronic0 = new Electronic(555555, "123", 1234);
            testElectronic1 = new Electronic(666666, "123", 1234);
        } catch (Exception e) {
        }

        productList.add(testElectronic0);
        productList.add(testElectronic1);

        assertTrue(EStoreSearch.isDuplicateID(productList, 111111));
        assertTrue(EStoreSearch.isDuplicateID(productList, 555555));
        assertFalse(EStoreSearch.isDuplicateID(productList, 123456));
    } 

    @Test public void testKeywordIsMatch() {
        String description = "This is a test";
        String[] searchKeywords = {"is", "Test"};

        assertTrue(EStoreSearch.keywordIsMatch(description, searchKeywords));
        searchKeywords[1] = "not";
        assertFalse(EStoreSearch.keywordIsMatch(description, searchKeywords));
    }
    
    @Test public void testAddandGetHash() {
        HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
        EStoreSearch.addHash(index, "this is a Test", 0);

        ArrayList<Integer> t1 = new ArrayList<Integer>();
        t1.add(0);
        String[] testString = {"test", "This"};
        
        assertEquals(t1, EStoreSearch.getMatchingHash(index, testString));//testing if it ignores cases

        EStoreSearch.addHash(index, "not Test", 2);
        String[] testString2 = {"test"};
        t1.add(2);
        assertEquals(t1, EStoreSearch.getMatchingHash(index, testString2));//testing it returns both values

        String[] testString3 = {"null"};
        t1.clear();
        assertEquals(t1, EStoreSearch.getMatchingHash(index, testString3));//testing if it returns nothing
    }

    @Test public void testSaveDataandLoadData() {
        ArrayList<Product> productList = new ArrayList<Product>();
        Book testBook0 = null;
        Book testBook1 = null;
        
        try {
            testBook0 = new Book(123455, "test1", 1233);
            testBook1 = new Book(111111, "test2", 1233);
        } catch (Exception e) {
        }

        productList.add(testBook0);
        productList.add(testBook1);

        Electronic testElectronic0 = null;
        Electronic testElectronic1 = null; 
        try {
            testElectronic0 = new Electronic(555555, "test1", 1233);
            testElectronic1 = new Electronic(666666, "test4", 1233);
        } catch (Exception e) {
        }

        productList.add(testElectronic0);
        productList.add(testElectronic1);

        HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();

        assertFalse(EStoreSearch.loadData(productList, "../../test/resources/nonExistentFile.txt", index));
        assertTrue(EStoreSearch.saveData(productList, "../../test/resources/testSaveandLoadDataTestfile.txt"));
        ArrayList<Product> productList1 = new ArrayList<Product>();
        assertTrue(EStoreSearch.loadData(productList1, "../../test/resources/testSaveandLoadDataTestfile.txt", index));
        ArrayList<Integer> t1 = new ArrayList<Integer>();
        t1.add(0);
        t1.add(2);
        assertEquals(t1, index.get("test1"));
        t1.clear();
        assertFalse(index.containsKey("test"));
    }
}