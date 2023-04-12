package edu.iu.c322.trackingservice.model;

import java.util.Objects;

public class UpdateRequest {

    private int itemId;
    private String status;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRequest that = (UpdateRequest) o;
        return itemId == that.itemId && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, status);
    }
}
