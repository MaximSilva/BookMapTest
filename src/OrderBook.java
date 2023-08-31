import java.util.Collections;
import java.util.TreeMap;

public class OrderBook {
    private TreeMap<Integer, Integer> bids; // purchase order book
    private TreeMap<Integer, Integer> asks; //book of sales leads

    public OrderBook(){
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


}
