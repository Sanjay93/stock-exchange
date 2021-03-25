package com.sanjay.stock.services;

import com.sanjay.stock.dto.StockDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Interval {
    int buy, sell;
}

class StockBuySell {

    public static void main(String args[]) {
        StockBuySell stock = new StockBuySell();
        double price[] = {240.12, 237.45, 238.10, 237.80, 237.80, 236.00};
        int n = price.length;
        stock.stockBuySellByPrice(price, n);
    }

    void stockBuySell(HashMap<String, StockDto> map) {
        double[] price = new double[map.size()];
        int n = map.size();
        int i = 0;
        for (Map.Entry<String, StockDto> entry : map.entrySet()) {
            String orderId = entry.getKey();
            StockDto stockDto = entry.getValue();
            price[i] = stockDto.getPrice();
            i++;
        }
        stockBuySellByPrice(price, n);
    }

    void stockBuySellByPrice(double price[], int n) {
        if (n == 1)
            return;

        int count = 0;
        ArrayList<Interval> sol = new ArrayList<Interval>();
        int i = 0;
        while (i < n - 1) {
            while ((i < n - 1) && (price[i + 1] >= price[i]))
                i++;
            if (i == n - 1)
                break;

            Interval e = new Interval();
            e.buy = i++;
            while ((i < n) && (price[i] <= price[i - 1]))
                i++;
            e.sell = i - 1;
            sol.add(e);
            count++;
        }
        if (count == 0)
            System.out.println("There is no day when buying the stock "
                    + "will make profit");
        else
            for (int j = 0; j < count; j++)
                System.out.println("Buy on day: " + sol.get(j).buy
                        + "        "
                        + "Sell on day : " + sol.get(j).sell);

        return;
    }
//        #3 237.45 90 #2
//        #3 236.00 20 #6
//        #4 236.00 10 #6
//        #5 236.00 20 #6
}