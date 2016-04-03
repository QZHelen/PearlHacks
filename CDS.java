package cds_v2.cds_v1;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CDS extends Application {

	private VBox _screen;
	ArrayList<Food> _foods = Reading.filter("ramsfile.txt");
	ArrayList<Food> _vegie;
	ArrayList<Food> _vegan;
	ArrayList<Food> _glutenFree;
	String randomMenu;
	String randomMenuVegie;
	CheckBox _getVeggie = new CheckBox("I'm vegetarian!");
	CheckBox _getVegan = new CheckBox("I'm vegan!");

	public static void main(String[] args) {
		Application.launch();

	}

	public ArrayList<Food> vegieFilter() {
		ArrayList<Food> ahComeOn = new ArrayList<Food>();
		for (int i = 0; i < _foods.size(); i++) {
			if (_foods.get(i).isVegetarian()) {
				ahComeOn.add(_foods.get(i));
			}
		}
		_vegie = ahComeOn;
		return _vegie;
	}

	public ArrayList<Food> veganFilter() {
		ArrayList<Food> ahComeOn = new ArrayList<Food>();
		for (int i = 0; i < _foods.size(); i++) {
			if (_foods.get(i).isVegan()) {
				ahComeOn.add(_foods.get(i));
			}
		}
		_vegan = ahComeOn;
		return _vegan;
	}

	public ArrayList<Food> glutenFilter() {
		ArrayList<Food> ahComeOn = new ArrayList<Food>();
		for (int i = 0; i < _foods.size(); i++) {
			if (_foods.get(i).isGlutenFree()) {
				ahComeOn.add(_foods.get(i));
			}
		}
		_vegan = ahComeOn;
		return _glutenFree;
	}

	public void startPressed(ActionEvent a) {
		Random random = new Random();
		if (_getVegan.isSelected()) {
			int randomInt = random.nextInt(_vegan.size());
			Food randomVegan = _vegan.get(randomInt);
			randomMenu = randomVegan.getName();
			Alert alert = new Alert(AlertType.INFORMATION,
					randomMenu + "\n" + "Calories: " + randomVegan.getCalories());
			alert.show();
		} else if (_getVeggie.isSelected()) {
			int randomInt = random.nextInt(_vegie.size());
			Food randomVegie = _vegie.get(randomInt);
			randomMenu = randomVegie.getName();
			Alert alert = new Alert(AlertType.INFORMATION,
					randomMenu + "\n" + "Calories: " + randomVegie.getCalories());
			alert.show();
		} else {
			int randomInt = random.nextInt(_foods.size());
			Food randomFood = _foods.get(randomInt);
			randomMenu = randomFood.getName();
			Alert alert = new Alert(AlertType.INFORMATION, randomMenu + "\n" + "Calories: " + randomFood.getCalories());
			alert.show();
		}
	}

	public void start(Stage stage) {
		this.startUp(stage);
		stage.setTitle("CDS player");
		_screen.getChildren().add(this.heading());
		Button startHere = new Button("Anything!");
		startHere.setTranslateX(550.0);
		startHere.setTranslateY(-20);
		startHere.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18.0));
		startHere.setOnAction(this::startPressed);
		_screen.getChildren().add(startHere);

		this.vegieFilter();
		this.veganFilter();
		this.glutenFilter();

		_getVeggie.setTranslateX(45.0);
		_getVeggie.setTranslateY(-20);
		_getVeggie.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18.0));
		_screen.getChildren().add(_getVeggie);

		_getVegan.setTranslateX(45.0);
		_getVegan.setTranslateY(0);
		_getVegan.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18.0));
		_screen.getChildren().add(_getVegan);
		stage.show();
	}

	private void startUp(Stage stage) {
		double padding = 0.0;
		_screen = new VBox(padding);
		_screen.setPadding(new Insets(padding));
		Scene scene = new Scene(_screen);
		stage.setScene(scene);
	}

	public Group heading() {
		Group heading = new Group();

		Rectangle top = new Rectangle();
		top.setX(0.0);
		top.setY(-15.0);
		top.setWidth(800);
		top.setHeight(150);
		top.setFill(Color.LIGHTBLUE);
		heading.getChildren().add(top);

		Text topText = new Text("Let's begin our advanture to Rams...");
		topText.setFill(Color.WHITE);
		topText.setFont(Font.font("Apple Chancery", FontWeight.NORMAL, 35.0));
		topText.setTranslateX(50.0);
		topText.setTranslateY(30.0);
		heading.getChildren().add(topText);

		Text topText2 = new Text("Hmmm...what shall I eat today?");
		topText2.setFill(Color.WHITE);
		topText2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20.0));
		topText2.setTranslateX(50.0);
		topText2.setTranslateY(70.0);
		heading.getChildren().add(topText2);
		return heading;
	}

}
