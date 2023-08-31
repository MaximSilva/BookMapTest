import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            BookOrder orderBook = new BookOrder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String command = parts[0];
                if (command.equals("u")) {
                    String orderType = parts[3];
                    int price = Integer.parseInt(parts[1]);
                    int size = Integer.parseInt(parts[2]);
                    orderBook.update(orderType, price, size);
                } else if (command.equals("q")) {
                    if (parts[1].equals("best_bid")) {
                        String result = orderBook.getBestPriceAndSize("bid");
                        if (!result.isEmpty()) {
                            writer.write(result);
                            writer.newLine();
                        }
                    } else if (parts[1].equals("best_ask")) {
                        String result = orderBook.getBestPriceAndSize("ask");
                        if (!result.isEmpty()) {
                            writer.write(result);
                            writer.newLine();
                        }
                    } else if (parts[1].equals("size")) {
                        int price = Integer.parseInt(parts[2]);
                        orderBook.removeBids(price);
                        orderBook.removeAsks(price);
                    }
                } else if (command.equals("o")) {
                    String orderType = parts[1];
                    int size = Integer.parseInt(parts[2]);
                    if (orderType.equals("buy")) {
                        orderBook.removeAsks(size);
                    } else if (orderType.equals("sell")) {
                        orderBook.removeBids(size);
                    }
                }
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
