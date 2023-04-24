package data;

public class Repos {

    public static final ReviewRepo REVIEWS = new MySQLRepoImpl();
    public static final UserRepo USERS = (UserRepo) REVIEWS;
    public static final MovieRepo MOVIES = new MovieRepoImpl();

    private Repos() {
        /* config class*/
    }
}
