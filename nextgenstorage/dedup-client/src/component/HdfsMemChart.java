package component;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;
import model.hdfs.HeapMemoryUsageType;
import model.hdfs.Memory;
import model.hdfs.NonHeapMemoryUsageType;
import util.http.hdfs.HdfsAccess;

public class HdfsMemChart {

	private final static int FRAME_UNIT = 5;
	private final static String X_NAME = "Time (s)";
	private final static String Y_NAME = "Memory (MB)";

	private final static String USED_DATA_NAME = "Used";
	private final static String COMITTED_DATA_NAME = "Comitted";

	private final static int X_START = 0;
	private final static int X_MAX = 60;
	private final static int X_UNIT = FRAME_UNIT;
	private final static int Y_START = 0;
	private final static int Y_MAX = 1024;
	private final static int Y_UNIT = 32;

	private boolean isNonHeap;

    private StackedAreaChart<Number, Number> chart;
    private Timeline animation;
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    private XYChart.Series<Number, Number> comitted;
    private XYChart.Series<Number, Number> used;
    private int second;

    public HdfsMemChart(boolean isNonHeap) {
    	this.isNonHeap = isNonHeap;
    	animation = new Timeline();
		animation.getKeyFrames()
				.add(new KeyFrame(Duration.millis(1000 * FRAME_UNIT), (ActionEvent actionEvent) -> {
						nextTime();
						plotTime();
				}));
		animation.setCycleCount(Animation.INDEFINITE);
    }

	public void play() {
		animation.play();
	}

	public void stop() {
		animation.pause();
	}

    public Node createContent() {
        xAxis = new NumberAxis(X_NAME, X_START, X_MAX, X_UNIT);
        yAxis = new NumberAxis(Y_NAME, Y_START, Y_MAX, Y_UNIT);

        used = new XYChart.Series<>();
        used.setName(USED_DATA_NAME);
        used.getData().add(new XYChart.Data<Number, Number>(0, 0));

		comitted = new XYChart.Series<>();
		comitted.setName(COMITTED_DATA_NAME);
		comitted.getData().add(new XYChart.Data<Number, Number>(0, 0));

		chart = new StackedAreaChart<Number, Number>(xAxis, yAxis);
		chart.getData().add(used);
		chart.getData().add(comitted);
        return chart;
    }

    private void nextTime() {
		second += FRAME_UNIT;
	}

	private void plotTime() {
		if (second > 60) {
            xAxis.setLowerBound(xAxis.getLowerBound() + FRAME_UNIT);
            xAxis.setUpperBound(xAxis.getUpperBound() + FRAME_UNIT);
        }

		Memory memory = null;
		try {
			memory = HdfsAccess.getInstance().getMemory();
			long comittedData = 0;
			long usedData = 0;
			long maxData = 0;

			if(!isNonHeap) {
				HeapMemoryUsageType heapMemoryUsageType = memory.getHeapMemoryUsage();
				maxData = heapMemoryUsageType.getMax();
				comittedData = heapMemoryUsageType.getCommitted();
				usedData = heapMemoryUsageType.getUsed();
			} else {
				NonHeapMemoryUsageType nonHeapMemoryUsageType = memory.getNonHeapMemoryUsage();
				maxData = nonHeapMemoryUsageType.getMax();

				if(maxData <= 0) {
					maxData = 2 * nonHeapMemoryUsageType.getCommitted();
				}

				comittedData = nonHeapMemoryUsageType.getCommitted();
				usedData = nonHeapMemoryUsageType.getUsed();
			}

			yAxis.setUpperBound(maxData);
			comitted.getData().add(new XYChart.Data<Number, Number>(second, comittedData - usedData));
			used.getData().add(new XYChart.Data<Number, Number>(second, usedData));
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (second > 60) {
			comitted.getData().remove(0);
			used.getData().remove(0);
		}
	}
}
