package data;

public interface ReviewRepo {
    void addReview(String loggedInUser, int movieId, String review, int score);
}
