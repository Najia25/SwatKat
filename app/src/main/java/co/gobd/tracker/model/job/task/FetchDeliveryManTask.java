package co.gobd.tracker.model.job.task;

import co.gobd.tracker.model.job.Location;

/**
 * Created by fahad on 4/25/16.
 */
public class FetchDeliveryManTask extends JobTask{

    private String JobTaskStateString;
    private String State;
    private String Id;
    private String Assigned;
    public FetchDeliveryManTask(String jobTaskStateString, String state, String Id,String assigned) {
        super(JobTaskTypes.FETCH_DELIVERYMAN, "Fetching Delivery Guy");
        JobTaskStateString = jobTaskStateString;
        State = state;
        this.Id = Id;
        this.Assigned=assigned;
        setType(JobTaskTypes.FETCH_DELIVERYMAN);
    }
    public String getAssetRefId() {
        return Assigned;
    }
    public String getJobTaskStateString() {
        return JobTaskStateString;
    }

    public String getState() {
        return State;
    }

    public String getId(){
        return Id;
    }

    public void setId(String Id){
        this.Id = Id;
    }

    public void setState(String State) {
        this.State = State;
    }

    public Location getLocation() {
        return null;
    }



    @Override
    public String toString() {
        return "FetchDeliveryManTask{" +
                "JobTaskStateString='" + JobTaskStateString + '\'' +
                ", State='" + State + '\'' +
                '}';
    }
}
