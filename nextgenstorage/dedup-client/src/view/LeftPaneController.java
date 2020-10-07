package view;

import application.MainApp;
import component.HdfsMemChart;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LeftPaneController {

	private MainApp mainApp;

	private ObservableList<TitledPane> panes;

	@FXML
	private void initialize() {

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		panes = this.mainApp.getRightAccordion().getPanes();
	}

	public LeftPaneController() {}

	private TitledPane heapMemPane = new TitledPane("Heap Memory Usage", new AnchorPane());
	private boolean isHeapMemPaneOpened = false;

	TitledPane nonHeapMemPane = new TitledPane("NonHeap Memory Usage", new AnchorPane());
	private boolean isNonHeapMemPanOpened = false;

	@FXML
	private void handleExpandHdfs() {
		heapMemPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!isHeapMemPaneOpened) {
					AnchorPane contentPane = (AnchorPane)heapMemPane.getContent();
					HdfsMemChart hdfsHeapMemChart = new HdfsMemChart(false);
					addMemChart(contentPane, hdfsHeapMemChart);
					isHeapMemPaneOpened = true;
				}
			}
		});

		heapMemPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!isNonHeapMemPanOpened) {
					HdfsMemChart hdfsNonHeapMemChart = new HdfsMemChart(true);
					AnchorPane contentPane = (AnchorPane)nonHeapMemPane.getContent();
					addMemChart(contentPane, hdfsNonHeapMemChart);
					isNonHeapMemPanOpened = true;
				}
			}
		});

		panes.clear();
		panes.add(heapMemPane);
		panes.add(nonHeapMemPane);
	}

	private void addMemChart(AnchorPane pane, HdfsMemChart chart) {
		Node node = chart.createContent();
		pane.getChildren().add(node);
		chart.play();
	}

	@FXML
	private void handleExpandDedup4FS() {
		TitledPane storagePane = new TitledPane();
		storagePane.setText("Storage Usage");
		panes.clear();
		panes.add(storagePane);
	}

	@FXML
	private void handleExpandMongoDB() {
		TitledPane storagePane = new TitledPane();
		storagePane.setText("Storage Usage");

		panes.clear();
		panes.add(storagePane);
	}


}
