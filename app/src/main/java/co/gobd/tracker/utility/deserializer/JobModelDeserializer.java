package co.gobd.tracker.utility.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.gobd.tracker.model.job.JobModel;
import co.gobd.tracker.model.job.Location;
import co.gobd.tracker.model.job.Point;
import co.gobd.tracker.model.job.order.Order;
import co.gobd.tracker.model.job.order.OrderCart;
import co.gobd.tracker.model.job.order.PackageList;
import co.gobd.tracker.model.job.task.DeliveryTask;
import co.gobd.tracker.model.job.task.FetchDeliveryManTask;
import co.gobd.tracker.model.job.task.JobTask;
import co.gobd.tracker.model.job.task.JobTaskTypes;
import co.gobd.tracker.model.job.task.PackagePickupTask;

/**
 * Created by fahad on 4/25/16.
 */
public class JobModelDeserializer implements JsonDeserializer<JobModel> {

    private JobModel jobModel;

    //FIXME Write unit test
    @Override
    public JobModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<JobTask> jobTaskList = new ArrayList<>();

        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonArray tasks = jsonObject.getAsJsonArray("Tasks");

        for (int i = 0; i < tasks.size(); i++) {
            final JsonObject task = tasks.get(i).getAsJsonObject();
            final String type = task.get("Type").getAsString();

            String jobTaskStateString;
            String state;
            Location location;
            switch (type) {
                case JobTaskTypes.FETCH_DELIVERYMAN:
                    jobTaskStateString = task.get("JobTaskStateString").getAsString();
                    state = task.get("State").getAsString();

                    jobTaskList.add(new FetchDeliveryManTask(jobTaskStateString, state));
                    break;

                case JobTaskTypes.PACKAGE_PICKUP:
                    jobTaskStateString = task.get("JobTaskStateString").getAsString();
                    state = task.get("State").getAsString();

                    location = getLocation(task.getAsJsonObject("PickupLocation"));

                    jobTaskList.add(new PackagePickupTask(jobTaskStateString, state, location));
                    break;

                case JobTaskTypes.DELIVERY:
                    jobTaskStateString = task.get("JobTaskStateString").getAsString();
                    state = task.get("State").getAsString();

                    location = getLocation(task.getAsJsonObject("To"));

                    jobTaskList.add(new DeliveryTask(jobTaskStateString, state, location));
                    break;

                default:

                    break;
            }

        }

        String Name = jsonObject.get("Name").getAsString();
        String State = jsonObject.get("State").getAsString();
        String CreateTime = jsonObject.get("CreateTime").getAsString();
        String ModifiedTime = jsonObject.get("ModifiedTime").getAsString();
        String PreferredDeliveryTime = jsonObject.get("PreferredDeliveryTime").getAsString();
        String InvoiceId = jsonObject.get("PaymentMethod").getAsString();
        Boolean Deleted = jsonObject.get("Deleted").getAsBoolean();
        String PaymentStatus = jsonObject.get("PaymentStatus").getAsString();
        String HRID = jsonObject.get("HRID").getAsString();
        String Id = jsonObject.get("Id").getAsString();


        jobModel = new JobModel(Name, State, jobTaskList);

        return jobModel;

    }

    public Order getOrder(JsonObject jsonObject){

    }

    public OrderCart getOrderCart(JsonObject jsonObject){

    }

    public PackageList getPackageList(JsonObject jsonObject){

        String Item = jsonObject.get("Item").getAsString();
        Integer Quantity = jsonObject.get("Quantity").getAsInt();
        Double Price = jsonObject.get("Price").getAsDouble();
        Double VAT = jsonObject.get("VAT").getAsDouble();
        Double Total = jsonObject.get("Total").getAsDouble();
        Double VATAmount = jsonObject.get("VATAmount").getAsDouble();
        Double TotalPlusVAT = jsonObject.get("TotalPlusVAT").getAsDouble();
        Double Weight = jsonObject.get("Weight").getAsDouble();

        PackageList packageList = new PackageList(Item, Quantity, Price, VAT, Total, VATAmount,
                TotalPlusVAT, Weight);
        return packageList;
    }

    public Location getLocation(JsonObject jsonObject){

        String address = jsonObject.get("Address").getAsString();
        JsonObject jsonPoint = jsonObject.get("Point").getAsJsonObject();
        String type = jsonPoint.get("type").getAsString();
        JsonArray jsonCoord = jsonPoint.getAsJsonArray("coordinates");
        String[] coord = new String[2];
        for (int i = 0; i<jsonCoord.size(); i++){
            coord[i] = jsonCoord.get(i).getAsString();
        }

        Point point = new Point(type, coord);

        Location location = new Location(point, address);

        return location;

    }


}