package data;

public interface UserRepo {
    String findPassword(String username);

    boolean addUser(String username, String password);
}
