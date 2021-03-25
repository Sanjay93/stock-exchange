package com.sanjay.stock.services;

import com.sanjay.stock.dto.StockDto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class StockMain {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the file path!");
            System.exit(0);
        }
        StockMain stockMain = new StockMain();
        try {
            stockMain.findStockOrder(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findStockOrder(String filePath) throws Exception {
        StockBuySell stockBuySell = new StockBuySell();
        HashMap<String, StockDto> map = parseFile(filePath);
        stockBuySell.stockBuySell(map);
    }

    public HashMap<String, StockDto> parseFile(String filePath) throws Exception {
        InputStream in = new FileInputStream(filePath);
        HashMap<String, StockDto> inputMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] arr = line.split("\\s+");
                StockDto stockDto = new StockDto();
                ;
                String orderId = "";
                for (int i = 0; i < arr.length; i++) {
//                    #1 09:45 BAC sell 240.12 100
                    orderId = arr[0];
                    String time = arr[1];
                    boolean isBuy = arr[3].equalsIgnoreCase("buy") ? true : false;
                    double price = Double.parseDouble(arr[4]);
                    //double quantity  = Double.parseDouble(arr[5]);
                    stockDto.setOrderId(orderId);
                    //    stockDto.setQuantity(quantity);
                    stockDto.setTime(time);
                    stockDto.setBuy(isBuy);
                    stockDto.setPrice(price);
                }
                inputMap.put(orderId, stockDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputMap;
    }
}
