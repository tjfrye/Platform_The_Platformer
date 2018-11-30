package scenes;

import java.util.Arrays;

import database.Queries;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HighscoresMenu extends Menu{

	public HighscoresMenu(int screenW, int screenH, Stage primaryStage) {
		super(screenW, screenH, primaryStage);
		
		Label label_title = new Label("Highscore Leaderboard");
		label_title.setFont(new Font("Arial", 20));
		
		TableView table = new TableView();
		table.setEditable(true);
		
		TableColumn col_place = new TableColumn("Place");
		col_place.setCellValueFactory(new PropertyValueFactory<Queries.Entry, String>("place"));
		TableColumn col_username = new TableColumn("Username");
		col_username.setCellValueFactory(new PropertyValueFactory<Queries.Entry, String>("username"));
		TableColumn col_highscore = new TableColumn("Highscore");
		col_highscore.setCellValueFactory(new PropertyValueFactory<Queries.Entry, String>("highscore"));

		table.setItems(Queries.listHighscores());
		table.getColumns().addAll(col_place, col_username, col_highscore);
		
		
		
		Button button_back = createButton("Back");
		button_back.setOnAction(e -> {window.setScene(new MainMenu(width, height, window).getScene());});
		
		GridPane GP = new GridPane();
		VBox VB = new VBox(10);
		VB.getChildren().addAll(label_title, table, button_back);
		GP.getChildren().add(VB);
		GP.setAlignment(Pos.CENTER);
				
		//create and set background image for the main menu
		Image mm_bg_img = new Image("file:resources/backgrounds/mm_background.png");
		GP.setBackground(new Background(new BackgroundImage (mm_bg_img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		s = new Scene(GP, screenW, screenH);
	}

}
