package ie.dq.batch.api;

import ie.dq.batch.api.vm.JobSummary;
import ie.dq.batch.service.BatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private BatchService batchService;

    @RequestMapping("/")
    public String index() {
        return "Spring Boot 2 Batch Service!";
    }

    @RequestMapping("jobs")
    public Set<String> getJobNames() {
        logger.info("Get registered jobs");
        return batchService.getRegisteredJobNames();
    }

    //2DO change to POST
    @RequestMapping("jobs/{jobname}")
    public String launchJob(@PathVariable(name = "jobname") String jobName) {
        logger.info("Launching job "+jobName);
        return batchService.startJob(jobName);
    }

    @RequestMapping("jobs/{id}/summary")
    public JobSummary getSummary(@PathVariable(name = "id") Long id) {
        return batchService.getJobSummary(id);
    }

}
