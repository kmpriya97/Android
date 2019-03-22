
package producttracking.iexemplar.com.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusDatum {

    @SerializedName("process")
    @Expose
    private String process;
    @SerializedName("processType")
    @Expose
    private String processType;
    @SerializedName("fabric")
    @Expose
    private String fabric;

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }


    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

}
