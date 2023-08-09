package com.bergeskar.currencyexchange.model;

public class Exchange {
    private Query query;
    private Info info;
    private double result;

    public Exchange() {
    }

    public Query getQuery() {
        return query;
    }
    public void setQuery(Query query) {
        this.query = query;
    }

    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }

    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }
}