package co.gobd.tracker.model.job;

import java.util.List;

import co.gobd.tracker.model.job.task.JobTask;

/**
 * Created by fahad on 4/25/16.
 */
public class JobModel {
    private String Name;
    private String State;
    private List<JobTask> Tasks;

    public JobModel(String name, String state, List<JobTask> tasks) {
        Name = name;
        State = state;
        Tasks = tasks;
    }

}
