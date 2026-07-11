package com.th.Utility;

public class DeliveryTimeUtil {

    public static String calculateDeliveryTime(double distance){

        int minTime;
        int maxTime;

        if(distance <= 2){

            minTime = 10;
            maxTime = 15;

        }
        else if(distance <= 5){

            minTime = 15;
            maxTime = 25;

        }
        else if(distance <= 8){

            minTime = 25;
            maxTime = 35;

        }
        else if(distance <= 12){

            minTime = 35;
            maxTime = 45;

        }
        else{

            minTime = 45;
            maxTime = 60;

        }

        return minTime + "-" + maxTime + " mins";
    }

}