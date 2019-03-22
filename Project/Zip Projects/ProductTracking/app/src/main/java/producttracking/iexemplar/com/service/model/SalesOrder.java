
package producttracking.iexemplar.com.service.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesOrder {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("towelDetails")
    @Expose
    private List<TowelDetail> towelDetails = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<TowelDetail> getTowelDetails() {
        return towelDetails;
    }

    public void setTowelDetails(List<TowelDetail> towelDetails) {
        this.towelDetails = towelDetails;
    }

}
