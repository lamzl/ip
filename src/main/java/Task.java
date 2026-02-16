public class Task {
    private boolean isDone;
    private String description;

    public Task(String description){
        this.isDone = false;
        this.description = description;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
