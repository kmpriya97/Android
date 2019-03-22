package producttracking.iexemplar.com.service.model;

import java.util.List;

public class SalesOrderPost {

    private String number;
    private String date;
    private String customerName;
    private String country;
    private List<TowelDetail> towelDetails;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<TowelDetail> getTowelDetailsList() {
        return towelDetails;
    }

    public void setTowelDetailsList(List<TowelDetail> towelDetailsList) {
        this.towelDetails = towelDetailsList;
    }


}
