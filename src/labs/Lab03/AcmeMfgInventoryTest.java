import org.junit.*;
import static org.junit.Assert.*;

/**
 * The test class AcmeMfgInventoryTest.
 *
 * @author  Franklin University
 * @version 2.0
 */
public class AcmeMfgInventoryTest
{
    private static final String PART = "PART";
    private static final String PROD = "PROD";
    private AcmeMfgInventory inventory;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        inventory = new AcmeMfgInventory();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        // Tear down the test fixture
    }
    
    /**
     * Test initial the lot count.
     */
    @Test
    public void regressionTestGetLotCount()
    {
        assertEquals("Hint: The getLotCount method should return 0"
                    + " if no lots have been added to inventory. ",
                    0, inventory.getLotCount());
    }    
    /**
     * Test adding lots to inventory.
     */
    @Test
    public void regressionTestAddLotMethod()
    {
        // Test adding a single default part lot to inventory
        PartLot partLot = new PartLot();
        assertTrue("Hint: The addLot method should return true "
                  + "when a PartLot object is added to inventory. ",
                    inventory.addLot(partLot));
        assertEquals("Hint: The getLotCount method should return 1 "
                    + "after the first lot is added to inventory. ",
                    1, inventory.getLotCount());
        assertEquals("Hint: The addLot method should set the lot number "
                    + "of the first lot added to 1. ",
                      1, partLot.getNumber());
        
        // Test adding a single default product lot to inventory
        ProductLot productLot = new ProductLot();
        assertTrue("Hint: The addLot method should return true "
                  + "when a ProductLot object is added to inventory. ",
                    inventory.addLot(productLot));
        assertEquals("Hint: The getLotCount method should return 2 "
                    + "after a second lot is added to inventory. ",
                    2, inventory.getLotCount());
        assertEquals("Hint: The addLot method should set the lot number "
                    + "of the second lot added to 2. ",
                    2, productLot.getNumber());
        
        // Test adding a part over the limit to inventory
        for (int i = 0; i < AcmeMfgInventory.MAX_LOTS - 2; i++)
        {
            assertTrue("Hint: The addLot method should return true "
                     + "when a PartLot object is added to inventory. ",
                     inventory.addLot(new PartLot()));
        }
        assertFalse("Hint: The addLot method should return false "
                   + "when you try to add too many lots to inventory.",
                    inventory.addLot(partLot));
        assertEquals("Hint: Lot count should not change when you try to "
                    + "add more lots than allowed in inventory. ",
                    AcmeMfgInventory.MAX_LOTS, inventory.getLotCount());
    }
    
    /**
     * Test deleting lots from inventory.
     */
    @Test
    public void regressionTestDeleteLotMethod()
    {
        // Test deleting a part lot from inventory
        PartLot partLot = new PartLot();
        inventory.addLot(partLot);
        assertTrue("Hint: the deletLot method could not delete "
                  + "an existing PartLot object from inventory. ",
                    inventory.deleteLot(partLot.getNumber()));
        assertEquals("Hint: The deleteLot method should decrease "
                    + "the lot count when a lot is deleted from inventory. ",
                    0, inventory.getLotCount());
                    
        // Test deleting a product lot from inventory
        ProductLot productLot = new ProductLot();
        inventory.addLot(productLot);
        assertTrue("Hint: the deletLot method could not delete "
                  + "an existing ProductLot object from inventory. ",
                    inventory.deleteLot(productLot.getNumber()));
        assertEquals("Hint: The deleteLot method should decrease "
                    + "the lot count when a lot is deleted from inventory. ",
                    0, inventory.getLotCount());
    }
    
    /**
     * Test multiple adds and deletes of lots in inventory.
     */
    @Test
    public void regressionTestAddsAndDeletes()
    {
        for (int i = 0; i < 5; i++)
        {
            inventory.addLot(new PartLot());     // Add lots 1 through 5
        }
        // Test deleting the last lot added
        assertTrue("Hint: The deleteLot method could not delete the last "
                  + "lot added to inventory. ",
                    inventory.deleteLot(5));
        assertEquals("Hint: Lot count should decrease when a lot is deleted. ",
                    4, inventory.getLotCount());
        
        // Test deleting a lot that does not exist
        assertFalse("Hint: The deleteLot method should return false if you "
                   + "try to delete a lot that does not exist in inventory. ",
                    inventory.deleteLot(5));
        assertEquals("Hint: The lot count should not change if lot was not "
                    + "deleted by the deleteLot method. ",
                    4, inventory.getLotCount());
                    
        // Test deleting the first lot added                    
        assertTrue("Hint: The deleteLot method should return true when "
                   + "deleting the first lot in inventory. ",
                    inventory.deleteLot(1));
        assertEquals("Hint: The lot count should decrease when a lot is "
                    + "deleted from inventory. ",
                    3, inventory.getLotCount());
                    
        // Test deleting one of the other lots added                    
        assertTrue("Hint: The deleteLot method should return true when "
                   + "deleting an existing lot from inventory. ",
                    inventory.deleteLot(3));
        assertEquals("Hint: The lot count should decrease when a lot is"
                    + "deleted from inventory. ",
                    2, inventory.getLotCount());
                    
        // Test adding another lot after the deletions
        PartLot partLot = new PartLot();
        assertTrue("Hint: The addLot method could not add PartLot "
                  + " to inventory. ",
                  inventory.addLot(partLot));
        assertEquals("Hint: The lot count should increase when a lot is "
                    + "added to inventory. ",
                    3, inventory.getLotCount());
        assertEquals("Hint: Lot numbers should not be reused. If 5 lots "
                    + "are added to inventory and 3 are deleted, when "
                    + "another lot is added, the lot number of the new "
                    + "should be set to 6. ",
                    6, partLot.getNumber());        
    }
    
    /**
     * Test getting lots from inventory.
     */
    @Test
    public void regressionTestGetLotMethod()
    {
        PartLot partLot;
        ProductLot productLot;
        // Test when there are no lots in inventory
        assertNull("Hint: The getLot method should return null "
                  + "if the requested lot does not exist in inventory. ",
                    inventory.getLot(99));
        
        // Test getting the first and only part lot in inventory
        partLot = new PartLot();
        inventory.addLot(partLot);
        assertEquals("Hint: The getLot method did not return "
                    + "the requested lot from inventory. ",
                    partLot, inventory.getLot(1));
        
        // Test getting the last and only product lot in inventory
        productLot = new ProductLot();
        inventory.addLot(productLot);
        assertEquals("Hint: The getLot method did not return "
                    + "the requested lot from inventory. ",
                    productLot, inventory.getLot(2));
        
        // Test that a deleted lot cannot be retrieved
        inventory.deleteLot(1);
        assertNull("Hint: The getLot method should return null if "
                  + " the requested lot was deleted from inventory. ",
                    inventory.getLot(1));
        
        // Test adding more lots and getting one of them from the middle
        for (int i = 0; i < 3; i++)
        {
            productLot = new ProductLot();
            inventory.addLot(productLot);
        }
        assertEquals("Hint: The getLot method did not return "
                    + "the requested lot from inventory. ",        
                    4, inventory.getLot(4).getNumber());
    }

    /**
     * Test queryItemQty method.
     */
    @Test
    public void regressionTestQueryItemQtyMethod()
    {
        PartLot partLot = new PartLot();
        ProductLot productLot = new ProductLot();
        
        // Test querying for an item when inventory is empty
        assertEquals("Hint: The queryItemQty method should return 0 if no "
                    + "lots have been added to inventory. ", 0,
                     inventory.queryItemQty(PROD));
        
        // Test querying for an item that does not exist in inventory
        partLot.setItemId(PART);
        partLot.setQty(5);
        inventory.addLot(partLot);
        assertEquals("Hint: The queryItemQty method should return 0 if no "
                    + "matching items are  found in inventory. ",
                    0, inventory.queryItemQty(PROD));
                    
        // Test querying for an item that exists in only one lot
        assertEquals("Hint: The queryItemQty method should return the "
                    + "number of items found in inventory. ", 5,
                     inventory.queryItemQty(PART));
        
        // Test querying for an item when more than one item in inventory
        productLot.setItemId(PROD);
        productLot.setQty(2);
        inventory.addLot(productLot);
        assertEquals("Hint: The queryItemQty method should return the "
                    + "number of items in inventory for only those lots "
                    + "where the item id matches. ", 5,
                    inventory.queryItemQty(PART));
        assertEquals("Hint: The queryItemQty method should return the "
                    + "number of items in inventory for only those lots "
                    + "where the item id matches. ", 2,
                    inventory.queryItemQty(PROD));

        // Test querying for an item that exists in more than one lot
        partLot = new PartLot();
        partLot.setItemId(PART);
        partLot.setQty(6);
        inventory.addLot(partLot);
        productLot = new ProductLot();
        productLot.setItemId(PROD);
        productLot.setQty(4);
        inventory.addLot(productLot);
        assertEquals("Hint: The queryItemQty method should return the total "
                    + "number of items in inventory when the item id is "
                    + "found in multiple lots. ",
                    11, inventory.queryItemQty(PART));
        assertEquals("Hint: The queryItemQty method should return the total "
                    + "number of items in inventory when the item id is "
                    + "found in multiple lots. ",
                    6, inventory.queryItemQty(PROD));
    }
    
    /**
     * Test removing parts from inventory.
     */
    @Test
    public void regressionTestRemoveMethodSingleLot()
    {
        PartLot partLot = new PartLot();

        // Test removing an item when there are no items in inventory
        assertFalse("Hint: The remove method should return false if there "
                   + "are no lots in inventory. ",
                    inventory.remove(PART, 1));
        // Test removing an item when the item does not exist in inventory
        partLot.setItemId(PART);
        partLot.setQty(5);
        inventory.addLot(partLot);
        assertFalse("Hint: The remove method should return false if the item "
                   + "does not exist in inventory. ",
                    inventory.remove(PROD, 1));
                    
        // Test removing a quantity of 0 of an item from inventory
        assertTrue("Hint: The remove method should return true if the "
                  + "number of items being removed is 0. ",
                    inventory.remove(PART, 0));
        assertEquals("Hint: The remove method should not change the quantity "
                    + "if the number of items being removed is 0. ",
                    5, inventory.queryItemQty(PART));
                    
        // Test removing a single item from inventory
        assertTrue("Hint: The remove method should return true when removing "
                  + "1 item from an existing lot in inventory. ",
                    inventory.remove(PART, 1));
        assertEquals("Hint: The item qty should be decreased when an item is "
                    + "removed from a lot in inventory. ", 4,
                    inventory.queryItemQty(PART));
        
        // Test removing two items from inventory
        assertTrue("Hint: The remove method should return true when removing "
                  + "2 items that exist in inventory. ",
                  inventory.remove(PART, 2));
        assertEquals("Hint: The item qty should be decrease by 2 when "
                    + "2 items  are removed from a lot in inventory. ",
                    2, inventory.queryItemQty(PART));
        
        // Test removing the entire qty of items in a single lot from inventory
        assertTrue("Hint: The remove method should be able to remove all items "
                  + "from a single lot in inventory. ",
                    inventory.remove(PART, 2));
        assertFalse("Hint: The remove method should return false when trying "
                    + "to remove items when all items have already been "
                    + "removed from inventory. ",
                    inventory.remove(PART, 1));
        assertEquals("Hint: The queryItemQty method should return 0 "
                    + "after all items removed from inventory. ",
                    0, inventory.queryItemQty(PART));
        assertNull("Hint: The lot should not exist in inventory after all "
                   + "the items have been removed from the lot. ",
                   inventory.getLot(1));
    }
    
    /**
     * Test removing producst and parts from inventory.
     */
    @Test
    public void regressionTestRemoveMethodMultiLot()
    {
        ProductLot productLot = new ProductLot();
        PartLot partLot = new PartLot();
                   
        // Test removing all the items from one lot and some from another lot
        productLot.setItemId(PROD);
        productLot.setQty(5);
        inventory.addLot(productLot);
        partLot.setItemId(PART);
        partLot.setQty(2);
        inventory.addLot(partLot);
        productLot = new ProductLot();
        productLot.setItemId(PROD);
        productLot.setQty(5);
        inventory.addLot(productLot);
        assertTrue("Hint: The remove method should return true when removing "
                  + "items from multiple lots. ",
                  inventory.remove(PROD, 7));
        assertEquals("Hint: The queryItemQty method returned the wrong "
                    + "quantity after items were removed from multiple lots. ",
                    3, inventory.queryItemQty(PROD));
        assertEquals("Hint: The lot count was incorrect after items were "
                    + "removed from multiple lots. If the remove method "
                    + "removed all the items from a lot, the lot should no "
                    + "longer exist in inventory. ",
                    2, inventory.getLotCount());        

        // Test removing all the items from more than one lot
        partLot = new PartLot();
        partLot.setItemId(PART);
        partLot.setQty(2);
        inventory.addLot(partLot);
        assertTrue("Hint: The remove method should return true when removing "
                  + "items from multiple lots. ",
                  inventory.remove(PART, 4));
        assertEquals("Hint: The queryItemQty method returned the wrong "
                    + "quantity after items were removed from multiple lots. ",
                    0, inventory.queryItemQty(PART));
        assertEquals("Hint: The lot count was incorrect after items were "
                    + "removed from multiple lots. If the remove method "
                    + "removed all the items from a lot, the lot should no "
                    + "longer exist in inventory. ",                    1, 
                    inventory.getLotCount());        
       
        // Test removing a qty of items when the entire qty not in inventory
        assertFalse("Hint: The remove method should return false if the entire "
                   + "quantity of items cannot be removed from inventory. ",
                    inventory.remove(PROD, 10));
        assertEquals("Hint: The quantity of items in inventory should not "
                    + "change when you try to remove more items than are "
                    + "available in inventory. ",
                    3, inventory.queryItemQty(PROD));
        assertEquals("Hint: The lot count should not change "
                    + "when you try to remove more items than are available in "
                    + "inventory. ",
                    1, inventory.getLotCount());
    }

    /**
     * Run regression tests.
     */
    @Test
    public void testRegression()
    {
        // Run regression tests but don't count them in Web-CAt
        assertNotNull(inventory); // added to keep Web-CAT happy
        regressionTestGetLotCount();
        setUp();
        regressionTestAddLotMethod();
        setUp();
        regressionTestDeleteLotMethod();
        setUp();
        regressionTestAddsAndDeletes();
        setUp();
        regressionTestGetLotMethod();
        setUp();
        regressionTestQueryItemQtyMethod();
        setUp();
        regressionTestRemoveMethodSingleLot();
        setUp();
        regressionTestRemoveMethodMultiLot(); 
    }
    
    // TODO: Your code here
}
