package edu.iu.c322.trackingservice.model;

import java.util.Objects;

public class Tracking {

    private String status;
    private String date;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracking tracking = (Tracking) o;
        return status.equals(tracking.status) && date.equals(tracking.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, date);
    }
}
