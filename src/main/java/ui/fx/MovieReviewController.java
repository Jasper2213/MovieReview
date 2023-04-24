package ui.fx;

import domain.Movie;
import javafx.scene.image.Image;
import service.MovieService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MovieReviewController {

    @FXML
    private TextField txtSearch;
    @FXML
    private ListView<Movie> lstResults;
    @FXML
    private ImageView imgCover;
    @FXML
    private TextField txtReviewText;
    @FXML
    private Spinner<Integer> spnScore;

    private MovieService service;

    public void setService(MovieService service) {
        this.service = service;
    }

    public void onSearch(ActionEvent actionEvent) {
        lstResults.setItems(FXCollections.observableArrayList(
                service.getMovies(txtSearch.getText())
        ));
    }

    public void onDisplayMovie(ActionEvent actionEvent) {
        Movie movie = lstResults.getSelectionModel().getSelectedItem();
        imgCover.setImage(new Image(movie.getCoverUrl()));
    }

    public void onAddReview(ActionEvent actionEvent) {
        Movie movie = lstResults.getSelectionModel().getSelectedItem();
        String review = txtReviewText.getText();
        int score = spnScore.getValue();

        service.addReview(movie.getId(), review, score);
    }
}
