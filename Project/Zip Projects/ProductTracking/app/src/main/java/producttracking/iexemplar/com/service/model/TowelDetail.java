
package producttracking.iexemplar.com.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TowelDetail {

    @SerializedName("towelName")
    @Expose
    private String towelName;
    @SerializedName("colour")
    @Expose
    private String colour;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getTowelName() {
        return towelName;
    }

    public void setTowelName(String towelName) {
        this.towelName = towelName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
