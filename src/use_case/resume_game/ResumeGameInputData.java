package use_case.resume_game;

public class ResumeGameInputData {
    final private String userName;

    public ResumeGameInputData(String userName) {
        this.userName = userName;
    }

    String getUsername() {
        return userName;
    }
}
