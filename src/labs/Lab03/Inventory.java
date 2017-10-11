
/**
 * Defines the interface for an inventory system that keeps track of 
 * inventory lots. Lots are added when groups of items arrive at a 
 * warehouse. After lots are added to inventory, items can removed from 
 * the lots as they leave the warehouse. Once a lot is empty, the lot is 
 * deleted. The lots in inventory can be displayed along with the total 
 * cost. In addition, the inventory can be queried to find out the 
 * quantity of a specific item in inventory. 
 * 
 * @author Franklin University
 * @version 2.0
 */

public interface Inventory
{
    /**
     * Adds a lot to inventory.
     * 
     * @param  lot  the lot to be added to inventory.
     * @return      <code>true</code> if the lot was added successfully, 
     *              and <code>false</code> if the lot was not added.
     */
    boolean addLot(Lot lot);
    
    /**
     * Deletes a lot from inventory.
     * 
     * @param  lotNumber  the number of the lot to be deleted.
     * @return      <code>true</code> if the lot was deleted successfully, 
     *              and <code>false</code> if the lot was not deleted.
     */
    boolean deleteLot(int lotNumber);
    
    /**
     * Returns the quantity on-hand in inventory for the given item id. 
     * The item id may be contained in more than one lot in the inventory. 
     * The quantity on-hand is the total number of items with that item id 
     * across all lots in inventory.
     * 
     * @param  itemId  the id of the item to be searched for in the inventory.
     * @return      the quanity on-hand.
     */
    int queryItemQty(String itemId);
    
    /**
     * Removes a quantity of items from inventory. The items may be contained
     * in more than one lot. If the total number of items cannot be removed, 
     * no items are removed from inventory.
     * 
     * @param  itemId  the identifier of the items to be removed.
     * @param  qty     the number of items to be removed.
     * @return         <code>true</code> if the items removed successfully, 
     *                 and <code>false</code> if all the items not removed.
     */
    boolean remove(String itemId, int qty);
    
    /**
     * Returns the number of lots in inventory.
     * 
     * @return      the number of lots.
     */
    int getLotCount();

    /**
     * Returns the lot with a given lot number.
     * 
     * @param   lotNo   the number of the lot to be returned.
     * @return          the first lot found if it exists in inventory or 
     *                  <code>null</code> if the lot does not exist
     *                  in inventory.
     */
    Lot getLot(int lotNo);
}
