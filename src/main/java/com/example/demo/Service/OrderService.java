package com.example.demo.Service;
//import com.example.demo.DBManager.OrderException;
import com.example.demo.DBManager.DatabaseException;
import com.example.demo.Model.Customer;
import com.example.demo.Model.CustomerOrder;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderExtras;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();
    FleetRepository fleetRepository = new FleetRepository();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");

    public void submitOrder(CustomerOrder co) {
        Order order = new Order();
        int customerId;
        if(!co.getExistingEmail().contains("@")){
            orderRepository.newCustomer(co);
            customerId = orderRepository.getLastCustomerId();
        } else {
            customerId = orderRepository.getCustomer(co.getExistingEmail()).getId();
        }
        String season = getSeason(dateFormat.format(co.getStartDate()));
        double priceNightly = getSeasonalPrice(season, orderRepository.getSize(fleetRepository.getMotorhome(co.getMotorhomeId()).getSizeId()).getPrice());
        order.setMotorhomeId(fleetRepository.getMotorhome(co.getMotorhomeId()).getId());
        order.setCustomerId(customerId);
        order.setPickup(co.getPickup());
        order.setDropoff(co.getDropoff());
        order.setStartDate(co.getStartDate());
        order.setEndDate(co.getEndDate());
        order.setNights((int)getNights(dateFormat.format(co.getStartDate()), dateFormat.format(co.getEndDate())));
        order.setDeposit(priceNightly * 2);

        System.out.println(order.getStartDate() + " " + order.getEndDate() + " " + order.getPickup() + order.getDropoff() + " and " + order.getCustomerId());

        orderRepository.newOrder(order);

    }

    public double getNights(String startDate, String endDate){
        long nights = -1;
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            nights = getDateDiff(start, end, TimeUnit.DAYS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nights;
    }

    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public String getSeason(String startDate){
        String monthAndYear = startDate.split("/")[1];
        int month = Integer.parseInt(monthAndYear.split("-")[0]);

        if(month > 9 && month <12 || month >= 1 && month < 4) return "low";
        else if(month > 3 && month < 6 || month == 9) return "middle";
        else return "high";
    }

    public double getSeasonalPrice(String season, double priceNightly){
        if (season.equalsIgnoreCase("low"))return priceNightly;
        else if (season.equalsIgnoreCase("middle")) return ((priceNightly/100) * 30) + priceNightly;
        else return ((priceNightly/100) * 60) + priceNightly;
    }

    public void addExtra(int extraId) {
        OrderExtras orderExtras = new OrderExtras();
        orderExtras.setExtraId(extraId);
        orderExtras.setOrderId(orderRepository.getLastOrderId());
        orderRepository.addExtra(orderExtras);
    }


    public double totalPrice(Order co, String string){
        getNights(dateFormat.format(co.getStartDate()), dateFormat.format(co.getEndDate()));

        int nights = (int)getNights(dateFormat.format(co.getStartDate()), dateFormat.format(co.getEndDate()));
        String season = getSeason(dateFormat.format(co.getStartDate()));
        double priceNightly = getSeasonalPrice(season, orderRepository.getSize(fleetRepository.getMotorhome(co.getMotorhomeId()).getSizeId()).getPrice());
        double nightsTotalPrice = (nights * priceNightly);
        double allExtraPrice = 0;
        for (OrderExtras extra : orderRepository.getOrderExtra(co.getId())) {
            allExtraPrice+=extra.getPrice();
        }
        double totalPriceAll = nightsTotalPrice + allExtraPrice + co.getDeposit();
        BigDecimal bd = new BigDecimal(nightsTotalPrice + totalPriceAll).setScale(2, RoundingMode.HALF_UP);
        double newInput = bd.doubleValue();


        if (string.equalsIgnoreCase("totalPrice")) return totalPriceAll + newInput;
        else return nightsTotalPrice + newInput;
    }

    public Order getOrder() {
        return orderRepository.getOrder(orderRepository.getLastOrderId());
    }

    public ArrayList<Extras> getAllExtras() throws DatabaseException {
        return orderRepository.getAllExtras();
    }

    public Customer getCustomer(Order order) {
        return orderRepository.getCustomer(order.getCustomerId());

    }

    public ArrayList<OrderExtras> getOrderExtra() throws DatabaseException {
        return orderRepository.getOrderExtra(orderRepository.getLastOrderId());
    }
}
