import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BookOrder {
    private TreeMap<Integer, Integer> bids; // purchase order book
    private TreeMap<Integer, Integer> asks; //book of sales leads

    public BookOrder(){
        bids = new TreeMap<>(Collections.reverseOrder());
        asks = new TreeMap<>();
    }


    //Update of the book of orders
    public void update(String orderType, int price, int size)
    {
        if(orderType.equals("bid")){
            bids.put(price, size);
        }
        else if(orderType.equals("ask")){
            asks.put(price, size);
        }
    }

    // Delete shares from the buy order book
    public void removeBids(int size){
        Iterator<Map.Entry<Integer, Integer>> iterator = bids.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            int price = entry.getKey();
            int currentSize = entry.getValue();
            if(currentSize <= size){
                iterator.remove();
                size -= currentSize;
            }
            else {
                bids.put(price, currentSize - size);
                break;
            }
        }

    }
    // Delete shares from the sales order book
    public void removeAsks(int size) {
        Iterator<Map.Entry<Integer, Integer>> iterator = asks.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            int price = entry.getKey();
            int currentSize = entry.getValue();
            if (currentSize <= size) {
                iterator.remove();
                size -= currentSize;
            } else {
                asks.put(price, currentSize - size);
                break;
            }
        }
    }

    //Get the best price and size
    public String getBestPriceAndSize(String type) {
        TreeMap<Integer, Integer> book = type.equals("bid") ? bids : asks;
        if (book.isEmpty()) {
            return "";
        }
        Map.Entry<Integer, Integer> bestEntry = book.firstEntry();
        int price = bestEntry.getKey();
        int size = bestEntry.getValue();
        return price + "," + size;
    }
}
