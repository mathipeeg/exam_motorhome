package com.example.demo.Service;

import com.example.demo.DBManager.OrderException;
import com.example.demo.Model.*;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

    public void submitOrder(CustomerOrder co) throws OrderException {
        Order order = new Order();
        int customerId;
        if(!co.getExistingEmail().contains("@")){
            orderRepository.newCustomer(co);
            customerId = orderRepository.getLastCustomerId();
        } else {
            customerId = orderRepository.getCustomer(co.getExistingEmail()).getId();
        }
        int nights = (int)getNights(co.getStartDate(), co.getEndDate());
        String season = getSeason(co.getStartDate());
        double priceNightly = getSeasonalPrice(season, orderRepository.getSize(orderRepository.getMotorhome(co.getMotorhomeId()).getSizeId()).getPrice());
        order.setMotorhomeId(orderRepository.getMotorhome(co.getMotorhomeId()).getId());
        order.setCustomerId(customerId);
        order.setPickup(co.getPickup());
        order.setDropoff(co.getDropoff());
        order.setStartDate(co.getStartDate());
        order.setEndDate(co.getEndDate());
        order.setNights(nights);
        order.setDeposit(priceNightly * 2);
        orderRepository.newOrder(order);

        double price = getSeasonalPrice(season, priceNightly);
    }

    public long getNights(String startDate, String endDate){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM-yyyy");

        LocalDate date1 = LocalDate.parse(startDate, dtf);
        LocalDate date2 = LocalDate.parse(endDate, dtf);
        long nights = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("Nights: " + nights);
        return nights;
    }

    public String getSeason(String startDate){
        String monthAndYear = startDate.split("/")[1];
        int month = Integer.parseInt(monthAndYear.split("-")[0]);
        if(month == 10 || month == 11 || month == 12 || month == 1 || month == 2 || month == 3){
            return "low";
            // TODO: 18/05/2020 Hvorfor virker > < ikke? >.<'
        } else if(month == 4 || month == 5 || month == 9){
            return "middle";
        } else{
            return "high";
        }
    }

    public double getSeasonalPrice(String season, double priceNightly){
        if (season.equalsIgnoreCase("low")){
            return priceNightly;
        } else if (season.equalsIgnoreCase("middle")){
            double percent = (priceNightly/100) * 30;
            return percent + priceNightly;
        } else {
            // TODO: 18/05/2020 Forkort
            double percent = (priceNightly/100) * 60;
            return percent + priceNightly;
        }
    }
//    public double getDeposit(double seasonalPrice){
//
//    }
//
//    public double getExtras(){
//
//    }
//
//    public double getFullPrice(double extras, double deposit, double seasonalPrice){
//
//    }

    public void addExtra(int extraId) throws OrderException {
        OrderExtras orderExtras = new OrderExtras();
        orderExtras.setExtraId(extraId);
        System.out.println(orderRepository.getLastOrderId());
        orderExtras.setOrderId(orderRepository.getLastOrderId());
        orderRepository.addExtra(orderExtras);
    }

//    public void addReceipt(int receiptId) throws OrderException {
//        OrderReceipt orderReceipt = new OrderReceipt();
//        orderReceipt.setReceiptId(receiptId);
//        System.out.println(orderRepository.getReceipt());
//        orderReceipt.setReceiptId(orderRepository.getReceipt());
//    }
}
