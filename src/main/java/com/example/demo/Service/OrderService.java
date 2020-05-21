package com.example.demo.Service;
import com.example.demo.DBManager.CustomException;
import com.example.demo.Model.CustomerOrder;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderExtras;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM-yyy");

    public void submitOrder(CustomerOrder co) throws CustomException {
        Order order = new Order();
        int customerId;
        if(!co.getExistingEmail().contains("@")){
            orderRepository.newCustomer(co);
            customerId = orderRepository.getLastCustomerId();
        } else {
            customerId = orderRepository.getCustomer(co.getExistingEmail()).getId();
        }
        int nights = (int)getNights(dateFormat.format(co.getStartDate()), dateFormat.format(co.getEndDate()));
        String season = getSeason(dateFormat.format(co.getStartDate()));
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

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit)
    {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
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
            return ((priceNightly/100) * 30) + priceNightly;
        } else {
            return ((priceNightly/100) * 60) + priceNightly;
        }
    }

    public void addExtra(int extraId) throws CustomException {
        OrderExtras orderExtras = new OrderExtras();
        orderExtras.setExtraId(extraId);
        orderExtras.setOrderId(orderRepository.getLastOrderId());
        orderRepository.addExtra(orderExtras);
    }

    public Order getOrder(int lastOrderId) throws CustomException {
        Order order = orderRepository.getOrder(lastOrderId);
        order.setStartDate(order.getStartDate());


        return null;
    }
}
