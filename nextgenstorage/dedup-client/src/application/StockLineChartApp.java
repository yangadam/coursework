package application;

import java.util.Random;

/* ....Show License.... */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * A simulated stock line chart.
 *
 */

public class StockLineChartApp extends Application {
	private LineChart<Number, Number> chart;
	private XYChart.Series<Number, Number> dataSeries;
	private NumberAxis xAxis;
	private Timeline animation;
	int second = 0;

	public StockLineChartApp() {
		animation = new Timeline();
		animation.getKeyFrames()
				.add(new KeyFrame(Duration.millis(1000 * 2), (ActionEvent actionEvent) -> {
					for (int count = 0; count < 5; count++) {
						nextTime();
						plotTime();
					}
				}));
		animation.setCycleCount(Animation.INDEFINITE);
	}

	public Parent createContent() {
		xAxis = new NumberAxis(0, 60, 1);
		final NumberAxis yAxis = new NumberAxis(0, 11, 1);

		chart = new LineChart<>(xAxis, yAxis);

		// setup chart
		chart.getStylesheets().add(StockLineChartApp.class.getResource("StockLineChart.css").toExternalForm());
		chart.setCreateSymbols(false);
		chart.setAnimated(false);
		chart.setLegendVisible(false);
		chart.setTitle("HeapMemoryUsage");
		xAxis.setLabel("Time");
		xAxis.setForceZeroInRange(false);
		xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis, null, "s"));
		yAxis.setLabel("Memory Usage");
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, " KB"));

		dataSeries = new XYChart.Series<>();
		dataSeries.setName("Second Data");
		dataSeries.getData()
				.add(new XYChart.Data<Number, Number>(0, 0));

		chart.getData().add(dataSeries);
		return chart;
	}

	private void nextTime() {
		second++;
	}

	private void plotTime() {
		if (second > 60) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);
        }

		/**
		 * ...
		 */
		Random random = new Random();
		double y = random.nextInt(10);
		dataSeries.getData().add(new XYChart.Data<Number, Number>(second, y));

		// after 60 seconds delete old data
		if (second > 60) {
			dataSeries.getData().remove(0);
		}
	}

	public void play() {
		animation.play();
	}

	@Override
	public void stop() {
		animation.pause();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		play();
	}

	/**
	 *
	 * Java main for when running without JavaFX launcher
	 *
	 */
	public static void main(String[] args) {
		launch(args);
	}

}