package com.example.miwipet.models;

import java.util.ArrayList;

public class OfferModel {
    private String username;
    private String userImage;
    private int successTrade, failedTrade;
    private InventoryModel wantItem;
    private InventoryModel offererItem;
    private int wantItemSingle;
    private ArrayList<Integer> offererItemSeries;

    public OfferModel() {

    }

    public OfferModel(String username, String userImage, int successTrade, int failedTrade, InventoryModel wantItem) {
        this.username = username;
        this.userImage = userImage;
        this.successTrade = successTrade;
        this.failedTrade = failedTrade;
        this.wantItem = wantItem;
    }

    public OfferModel(String username, String userImage, int successTrade, int failedTrade, InventoryModel wantItem, InventoryModel offererItem) {
        this.username = username;
        this.userImage = userImage;
        this.successTrade = successTrade;
        this.failedTrade = failedTrade;
        this.wantItem = wantItem;
        this.offererItem = offererItem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getSuccessTrade() {
        return successTrade;
    }

    public void setSuccessTrade(int successTrade) {
        this.successTrade = successTrade;
    }

    public int getFailedTrade() {
        return failedTrade;
    }

    public void setFailedTrade(int failedTrade) {
        this.failedTrade = failedTrade;
    }

    public InventoryModel getWantItem() {
        return wantItem;
    }

    public void setWantItem(InventoryModel wantItem) {
        this.wantItem = wantItem;
    }

    public InventoryModel getOffererItem() {
        return offererItem;
    }

    public void setOffererItem(InventoryModel offererItem) {
        this.offererItem = offererItem;
    }

    public int getWantItemSingle() {
        return wantItemSingle;
    }

    public void setWantItemSingle(int wantItemSingle) {
        this.wantItemSingle = wantItemSingle;
    }

    public ArrayList<Integer> getOffererItemSeries() {
        return offererItemSeries;
    }

    public void setOffererItemSeries(ArrayList<Integer> offererItemSeries) {
        this.offererItemSeries = offererItemSeries;
    }
}
