package messages;

public class MovieSearchMessage extends Message {

    private final String query;

    public MovieSearchMessage(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
