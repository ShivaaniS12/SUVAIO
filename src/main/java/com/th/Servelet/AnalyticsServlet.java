package com.th.Servelet;

import java.io.IOException;
import java.util.List;

import com.th.dao.OrderDAO;
import com.th.daoimpl.OrderDAOImpl;
import com.th.model.AnalyticsData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/analytics")
public class AnalyticsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDAO orderDao;

    @Override
    public void init() {

        orderDao =
                new OrderDAOImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {   
    	
    	
    	String topRestaurant =
                orderDao.getTopRestaurant();

        request.setAttribute(
                "topRestaurant",
                topRestaurant);
        
        List<AnalyticsData> ordersPerRestaurant =
                orderDao.getOrdersPerRestaurant();

        request.setAttribute(
                "ordersPerRestaurant",
                ordersPerRestaurant);
        
        List<AnalyticsData> revenueReport =
                orderDao.getRestaurantWiseRevenue();

        request.setAttribute(
                "revenueReport",
                revenueReport);
        
        double previousMonthRevenue =
                orderDao.getPreviousMonthRevenue();

        double currentMonthRevenue =
                orderDao.getCurrentMonthRevenue();

        request.setAttribute(
                "previousMonthRevenue",
                previousMonthRevenue);

        request.setAttribute(
                "currentMonthRevenue",
                currentMonthRevenue);
        
        double growthPercentage = 0;

        if(previousMonthRevenue > 0) {

            growthPercentage =
                    ((currentMonthRevenue
                    - previousMonthRevenue)
                    / previousMonthRevenue)
                    * 100;
        }
        
        request.setAttribute(
                "growthPercentage",
                growthPercentage);


        request.getRequestDispatcher(
                "analytics.jsp")
                .forward(
                        request,
                        response);
        
        double weeklyRevenue =
                orderDao.getWeeklyRevenue();

        double monthlyRevenue =
                orderDao.getMonthlyRevenue();

        double weeklyProfit =
                weeklyRevenue * 0.10;
        
        request.setAttribute(
                "weeklyRevenue",
                weeklyRevenue);

        request.setAttribute(
                "monthlyRevenue",
                monthlyRevenue);

        request.setAttribute(
                "weeklyProfit",
                weeklyProfit);
        
     

    }
}