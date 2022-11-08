package gui;

import java.beans.Expression;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Calculator extends VBox implements EventHandler<ActionEvent> {

	String number1 = "";
	String number2 = "";
	String operator;
	Text displayText;
	int op = 0;

	public Calculator() {
		super(10);
		this.displayText = new Text();

		Rectangle rt = new Rectangle(250, 50, Color.TRANSPARENT);

		rt.setStroke(Color.GRAY);

		StackPane sp = new StackPane(rt, this.displayText);

		sp.setPadding(new Insets(10, 10, 10, 10));

		GridPane gd = new GridPane();

		gd.setPadding(new Insets(10, 10, 10, 10));
		gd.setVgap(5);
		gd.setHgap(4);

		gd.setAlignment(Pos.CENTER);

		Button b7 = new Button("7");
		gd.add(b7, 0, 0);
		b7.setPrefWidth(50);
		b7.setOnAction(this);

		Button b8 = new Button("8");
		gd.add(b8, 1, 0);
		b8.setPrefWidth(50);
		b8.setOnAction(this);

		Button b9 = new Button("9");
		gd.add(b9, 2, 0);
		b9.setPrefWidth(50);
		b9.setOnAction(this);

		Button div = new Button("/");
		gd.add(div, 3, 0);
		div.setPrefWidth(50);
		div.setOnAction(this);

		Button b4 = new Button("4");
		gd.add(b4, 0, 1);
		b4.setPrefWidth(50);
		b4.setOnAction(this);

		Button b5 = new Button("5");
		gd.add(b5, 1, 1);
		b5.setPrefWidth(50);
		b5.setOnAction(this);

		Button b6 = new Button("6");
		gd.add(b6, 2, 1);
		b6.setPrefWidth(50);
		b6.setOnAction(this);

		Button mul = new Button("*");
		gd.add(mul, 3, 1);
		mul.setPrefWidth(50);
		mul.setOnAction(this);

		Button b1 = new Button("1");
		gd.add(b1, 0, 2);
		b1.setPrefWidth(50);
		b1.setOnAction(this);

		Button b2 = new Button("2");
		gd.add(b2, 1, 2);
		b2.setPrefWidth(50);
		b2.setOnAction(this);

		Button b3 = new Button("3");
		gd.add(b3, 2, 2);
		b3.setPrefWidth(50);
		b3.setOnAction(this);

		Button minus = new Button("-");
		gd.add(minus, 3, 2);
		minus.setPrefWidth(50);
		minus.setOnAction(this);

		Button b0 = new Button("0");
		gd.add(b0, 0, 3, 2, 1);
		b0.setPrefWidth(105);
		b0.setOnAction(this);

		Button plus = new Button("+");
		gd.add(plus, 2, 3);
		plus.setPrefWidth(50);
		plus.setOnAction(this);

		Button equals = new Button("=");
		gd.add(equals, 3, 3);
		equals.setPrefWidth(50);
		equals.setOnAction(this);

		Button reset = new Button("C");
		gd.add(reset, 0, 4, 4, 1);
		reset.setPrefWidth(215);
		reset.setOnAction(this);

		this.getChildren().addAll(sp, gd);
	}

	@Override
	public void handle(ActionEvent event) {

		Button b = (Button) event.getSource();
		String value = b.getText();
		String expression = displayText.getText();
		String[] operadores = new String[] { "+", "-", "*", "/" };

		try {
			if (!Arrays.asList(operadores).contains(value) && value != "C" && value != "=") {
				if (op == 0) {
					number1 += value;
					displayText.setText(number1);

				} else {
					number2 += value;
					displayText.setText(number1 + operator + number2);

				}
			} else if (Arrays.asList(operadores).contains(value) && op<1)  {
				operator = value;
				if (value == "+") {
					op = 1;
				} else if (value == "-") {
					op = 2;
				} else if (value == "*") {
					op = 3;
				} else {
					op = 4;
				}
				displayText.setText(number1 + operator);
			} else if (value == "C") {
				Limpiar();
				displayText.setText("");

			} else if (value == "="){
				displayText.setText(Evaluar(number1, number2, op).toString());
			}else {
				number1=Evaluar(number1, number2, op).toString();
				number2="";
				operator=value;
				if (value == "+") {
					op = 1;
				} else if (value == "-") {
					op = 2;
				} else if (value == "*") {
					op = 3;
				} else {
					op = 4;
				}			
				displayText.setText(number1 + operator);

			}
	
		}
		catch(Exception e){
			Limpiar();
			displayText.setText("Error");

		}
		
	}

	private Integer Evaluar(String n1, String n2, int o) {
		Integer f1 = Integer.valueOf(n1);
		Integer f2 = Integer.valueOf(n2);
		Integer ans = 0;

		if (o == 1) { // Addition
			ans = f1 + f2;
		} else if (o == 2) {
			ans = f1 - f2;
		} else if (o == 3) {
			ans = f1 * f2;
		} else if (o == 4) {
			ans = f1 / f2;
		}
		Limpiar();
		return ans;
	}

	public void Limpiar() {
		number1 = "";
		number2 = "";
		operator = "";
		op = 0;
	}
}
