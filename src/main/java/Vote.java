public class Vote {

    private String voter;
    private Boolean vote;

    public Vote(String voter, Boolean vote) {
        this.voter = voter;
        this.vote = vote;
    }

    public String getVoter() {
        return voter;
    }

    public Boolean getVote() {
        return vote;
    }
}
