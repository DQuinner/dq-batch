package ie.dq.batch.api.vm;

public class StepExecution {

    private String id;
    private String version;
    private String name;
    private String status;
    private String exitStatus;
    private String readCount;
    private String filterCount;
    private String writeCount;
    private String readSkipCount;
    private String writeSkipCount;
    private String processSkipCount;
    private String commitCount;
    private String rollbackCount;
    private String exitDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(String writeCount) {
        this.writeCount = writeCount;
    }

    public String getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(String filterCount) {
        this.filterCount = filterCount;
    }

    public String getReadSkipCount() {
        return readSkipCount;
    }

    public void setReadSkipCount(String readSkipCount) {
        this.readSkipCount = readSkipCount;
    }

    public String getWriteSkipCount() {
        return writeSkipCount;
    }

    public void setWriteSkipCount(String writeSkipCount) {
        this.writeSkipCount = writeSkipCount;
    }

    public String getProcessSkipCount() {
        return processSkipCount;
    }

    public void setProcessSkipCount(String processSkipCount) {
        this.processSkipCount = processSkipCount;
    }

    public String getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(String commitCount) {
        this.commitCount = commitCount;
    }

    public String getRollbackCount() {
        return rollbackCount;
    }

    public void setRollbackCount(String rollbackCount) {
        this.rollbackCount = rollbackCount;
    }

    public String getExitDescription() {
        return exitDescription;
    }

    public void setExitDescription(String exitDescription) {
        this.exitDescription = exitDescription;
    }
}
