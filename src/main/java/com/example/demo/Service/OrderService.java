package com.example.demo.Service;

import com.example.demo.DBManager.TestException;
import com.example.demo.Model.Customer;
import com.example.demo.Model.CustomerOrder;
import com.example.demo.Model.Order;
import com.example.demo.Repository.OrderRepository;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

    public void submitOrder(CustomerOrder co) throws TestException {
        Customer customer = new Customer();
        Order order = new Order();
        long nights = getNights(co.getStartDate(), co.getEndDate());
        String season = getSeason(co.getStartDate());
        int priceNightly = calculatePrice(season, orderRepository.getSize(orderRepository.getMotorhome(co.getMotorhomeId()).getSizeId()).getPrice());
        double price = calculatePrice(season, priceNightly, nights);
    }

    public long getNights(String startDate, String endDate){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM-yyyy");

        LocalDate date1 = LocalDate.parse(startDate, dtf);
        LocalDate date2 = LocalDate.parse(endDate, dtf);
        long nights = Duration.between(date1, date2).toDays();
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

    public double getSeasonalPrice(String season, double priceNightly, long nights){
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

    public double getDeposit(double seasonalPrice){

    }

    public double getExtras(){

    }

    public double getFullPrice(double extras, double deposit, double seasonalPrice){

    }
}
