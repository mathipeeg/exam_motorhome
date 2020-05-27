package com.example.demo.Service;
//import com.example.demo.DBManager.OrderException;
import com.example.demo.Model.Customer;
import com.example.demo.Model.CustomerOrder;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderExtras;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.example.demo.DBManager.*;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM-yyy");
    FleetRepository fleetRepository = new FleetRepository();

    public void submitOrder(CustomerOrder co) throws CustomException {
        Order order = new Order();
        System.out.println(co.getStartDate());
        System.out.println(co.getEndDate());
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

    public void addExtra(int extraId) throws CustomException {
        OrderExtras orderExtras = new OrderExtras();
        orderExtras.setExtraId(extraId);
        orderExtras.setOrderId(orderRepository.getLastOrderId());
        orderRepository.addExtra(orderExtras);
    }

    public double totalPrice(Order co, String string) throws CustomException {
        getNights(dateFormat.format(co.getStartDate()), dateFormat.format(co.getEndDate()));

        int nights = (int)getNights(dateFormat.format(co.getStartDate()), dateFormat.format(co.getEndDate()));
        String season = getSeason(dateFormat.format(co.getStartDate()));
        double priceNightly = getSeasonalPrice(season, orderRepository.getSize(fleetRepository.getMotorhome(co.getMotorhomeId()).getSizeId()).getPrice());
        double nightsTotalPrice = (nights * priceNightly);
        double allExtraPrice = 0;

        for (int i = 0; i < orderRepository.getExtraInfo(co.getId()).size() ; i++) {
            allExtraPrice+=orderRepository.getExtraInfo(co.getId()).get(i).getPrice();
        }

        double totalPriceAll = nightsTotalPrice + allExtraPrice + co.getDeposit();

        if (string.equalsIgnoreCase("totalPriceAll")) return totalPriceAll;
        else return  nightsTotalPrice;
    }

    public Order getOrder(int lastOrderId) throws CustomException {
        Order order = orderRepository.getOrder(lastOrderId);
        order.setStartDate(order.getStartDate());
        return null;
    }
}
