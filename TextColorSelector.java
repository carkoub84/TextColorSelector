import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TextColorSelector extends Application {

    private Slider redSlider, greenSlider, blueSlider, opacitySlider;
    private Label textLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Color Selector");

        redSlider = createSlider("Red", 0, 255);
        greenSlider = createSlider("Green", 0, 255);
        blueSlider = createSlider("Blue", 0, 255);
        opacitySlider = createSlider("Opacity (%)", 0, 100);

        textLabel = new Label("Selected Color: #000000, Opacity: 1.00");
        textLabel.setStyle("-fx-font-size: 16px;");

        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20px;");
        root.getChildren().addAll(redSlider, greenSlider, blueSlider, opacitySlider, textLabel);

        redSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());
        greenSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());
        blueSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());
        opacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor());

        Scene scene = new Scene(root, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createSlider(String label, double min, double max) {
        Slider slider = new Slider(min, max, 0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setBlockIncrement(1);
        slider.setPrefWidth(300);
        slider.setStyle("-fx-font-size: 14px;");
        slider.setLabelFormatter(new SliderLabelFormatter(label));
        return slider;
    }

    private void updateTextColor() {
        int red = (int) redSlider.getValue();
        int green = (int) greenSlider.getValue();
        int blue = (int) blueSlider.getValue();
        double opacity = opacitySlider.getValue() / 100.0;

        Color textColor = Color.rgb(red, green, blue, opacity);
        String hexColor = String.format("#%02X%02X%02X", red, green, blue);

        textLabel.setText("Selected Color: " + hexColor + ", Opacity: " + String.format("%.2f", opacity));
        textLabel.setTextFill(textColor);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class SliderLabelFormatter extends javafx.util.StringConverter<Double> {
        private String label;

        public SliderLabelFormatter(String label) {
            this.label = label;
        }

        @Override
        public String toString(Double object) {
            return label + ": " + String.format("%.0f", object);
        }

        @Override
        public Double fromString(String string) {
            return null;
        }
    }
}
