package com.example.lostandfound;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LostItem {

    public enum REPORT_TYPE {
        REPORT_TYPE_LOST,
        REPORT_TYPE_FOUND
    }

    private int id;
    private REPORT_TYPE reportType;
    private String itemName;
    private String description;
    private String location;

    private LocalDate dateReported;
    private String postersName;
    private String mobile;

    public LostItem(int id, REPORT_TYPE reportType, String itemName, String description, String location, LocalDate dateReported, String postersName, String mobile) {
        this.id = id;
        this.reportType = reportType;
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.dateReported = dateReported;
        this.postersName = postersName;
        this.mobile = mobile;
    }

    public REPORT_TYPE getReportType() {
        return reportType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDateReported() {
        return dateReported;
    }

    public String getPostersName() {
        return postersName;
    }

    public String getMobile() {
        return mobile;
    }


    public String formatTimeAgo() {
        LocalDate date = this.dateReported;
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
            long years = ChronoUnit.YEARS.between(date, currentDate);
            long months = ChronoUnit.MONTHS.between(date, currentDate);
            long weeks = ChronoUnit.WEEKS.between(date, currentDate);
            long days = ChronoUnit.DAYS.between(date, currentDate);
            long hours = ChronoUnit.HOURS.between(date.atStartOfDay(), LocalDateTime.now());
            long minutes = ChronoUnit.MINUTES.between(date.atStartOfDay(), LocalDateTime.now());
            long seconds = ChronoUnit.SECONDS.between(date.atStartOfDay(), LocalDateTime.now());

            if (years > 0) {
                return years + " year" + (years > 1 ? "s" : "") + " ago";
            } else if (months > 0) {
                return months + " month" + (months > 1 ? "s" : "") + " ago";
            } else if (weeks > 0) {
                return weeks + " week" + (weeks > 1 ? "s" : "") + " ago";
            } else if (days > 0) {
                return days + " day" + (days > 1 ? "s" : "") + " ago";
            } else if (hours > 0) {
                return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
            } else if (minutes > 0) {
                return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
            } else if (seconds > 0) {
                return seconds + " second" + (seconds > 1 ? "s" : "") + " ago";
            } else {
                return "Just now";
            }
        }
        return "Not too long";
    }
}
