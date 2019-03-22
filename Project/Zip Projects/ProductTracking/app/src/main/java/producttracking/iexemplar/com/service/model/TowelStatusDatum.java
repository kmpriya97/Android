
package producttracking.iexemplar.com.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TowelStatusDatum {

    @SerializedName("gradeA")
    @Expose
    private Integer gradeA;
    @SerializedName("gradeB")
    @Expose
    private Integer gradeB;
    @SerializedName("gradeC")
    @Expose
    private Integer gradeC;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getGradeA() {
        return gradeA;
    }

    public void setGradeA(Integer gradeA) {
        this.gradeA = gradeA;
    }

    public Integer getGradeB() {
        return gradeB;
    }

    public void setGradeB(Integer gradeB) {
        this.gradeB = gradeB;
    }

    public Integer getGradeC() {
        return gradeC;
    }

    public void setGradeC(Integer gradeC) {
        this.gradeC = gradeC;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
