
/**
 * The AcmeMfgInventory class is an inventory system that keeps track 
 * of inventory lots. Lots are added when groups of items arrive at a 
 * warehouse. After lots are added to inventory, items can removed from 
 * the lots as they leave the warehouse. Once a lot is empty, the lot is 
 * deleted. The lots in inventory can be displayed along with the total 
 * cost. In addition, the inventory can be queried to find out the quantity
 * of a specific item in inventory. 
 * 
 * @author Franklin University
 * @version 2.0
 */
public class AcmeMfgInventory implements Inventory
{
    /**
     * The maximum number of lots allowed in inventory.
     */
    public static final int MAX_LOTS = 250;
    
    private int nextNumber;
    private int numLots;
    private Lot [] lots;
    
    /**
     * Constructor for objects of class AcmeMfgInventory.
     */
    public AcmeMfgInventory()
    {
        nextNumber = 1;
        numLots = 0;
        lots = new Lot[MAX_LOTS];
    }

    /**
     * Adds a lot to inventory.
     * 
     * @param  lot  the lot to be added to inventory.
     * @return      <code>true</code> if the lot was added successfully, 
     *              and <code>false</code> if the lot was not added.
     */
    public boolean addLot(Lot lot)
    {
        //    If inventory is full, can't add
        if (numLots == MAX_LOTS) {
            return false;
        }
        
        lot.setNumber(nextNumber++);
        lots[numLots++] = lot;
        return true;
    }
    
    /**
     * Deletes a lot from inventory.
     * 
     * @param  lotNumber  the number of the lot to be deleted.
     * @return      <code>true</code> if the lot was deleted successfully, 
     *              and <code> false</code> if the lot was not deleted.
     */
    public boolean deleteLot(int lotNumber)
    {
        for (int i = 0; i < numLots; i++) {
            Lot lot = lots[i];
            
            if (lot.getNumber() == lotNumber) {
                //    Move lot from last slot into slot of deleted lot 
                lots[i] = lots[numLots - 1];
                lots[numLots - 1] = null;
                numLots--;
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns the quantity on-hand in inventory for the given item id. 
     * The item id may be contained in more than one lot in the inventory.
     * The quantity on-hand is the total number of items with that item 
     * id across all lots in inventory.
     * 
     * @param  itemId  the id of the item to be searched for in the inventory.
     * @return      the quanity on-hand.
     */
    public int queryItemQty(String itemId)
    {
        int totalQty = 0;
        
        for (int i = 0; i < numLots; i++) {
            Lot lot = lots[i];
            
            if (lot.getItemId().equals(itemId)) {
                totalQty += lot.getQty();
            }
        }
        
        return totalQty;
    }

    /**
     * Removes a quantity of items from inventory. The items may be 
     * contained in more than one lot. If the total quantity is not 
     * available in inventory, no items are removed. Once 
     * the items have been removed from inventory, if the quantity
     * on-hand is zero, the lot is be deleted from inventory.
     * 
     * @param  itemId  the identifier of the items to be removed.
     * @param  qty     the number of items to be removed.
     * @return         <code>true</code> if the items were removed 
     *                 successfully, and <code>false</code> if all 
     *                 the items were not removed.
     */
    public boolean remove(String itemId, int qty)
    {
        //    If total quantity is too low, don't do anything
        if (queryItemQty(itemId) < qty) {
            return false;
        }
    
        int numToRemove = qty;
        
        int i = 0;
        while (true) {
            Lot lot = lots[i];
            
            if (lot.getItemId().equals(itemId)) {
                if (numToRemove < lot.getQty()) {
                    //    Only have to remove part of a lot
                    lot.setQty(lot.getQty() - numToRemove);
                    break;
                }
                
                //    We need to remove this whole lot
                numToRemove -= lot.getQty();
                deleteLot(lot.getNumber());
                i--;
                
                if (numToRemove == 0) {
                    break;
                }
            }
            
            i++;
        }
        
        return true;
    }

    /**
     * Returns the number of lots in inventory.
     * 
     * @return      the number of lots.
     */
    public int getLotCount()
    {
        return numLots;
    }

    /**
     * Returns the lot with a given lot number.
     * 
     * @param   lotNo   the number of the lot to be returned.
     * @return          the first lot found if it exists in inventory or 
     *                  <code>null</code> if the lot does not exist in 
     *                  inventory.
     */
    public Lot getLot(int lotNo)
    {
        for (int i = 0; i < numLots; i++) {
            Lot lot = lots[i];
            
            if (lot.getNumber() == lotNo) {
                return lot;
            }
        }
        
        return null;
    }

    /**
     * Recreates the inventory from a persisted object file.  The file should
     * have been created by serialization of a prior inventory object.
     * 
     * @param fileName the name of the file containing the inventory object
     * @return the AcmeMfgInventory object read from the file
     * @throws RuntimeException if any exception occurs during processing
     */
    public static AcmeMfgInventory readFromFile(String fileName)
    {
        AcmeMfgInventory inventory = null;
        
        // TODO: Your code here
        
        return inventory;
    }

    /**
     * Writes the current inventory to the given file name using
     * object serialization.
     * 
     * @param fileName the name of the file to write.
     * @throws RuntimeException if any errors occur during processing
     */
    public void writeToFile(String fileName)
    {
        // TODO: Your code here
    }
}
