package application;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.hdfs.FileStatus;
import util.commons.Unit;
import view.HdfsOverviewController;
import view.LeftPaneController;

public class MainApp extends Application {

	private final static int LEFT_PANE_INDEX = 0;
	private final static int MIDDLE_PANE_INDEX = 1;
	private final static int RIGHT_PANE_INDEX = 2;

	private final static int LEFT_ACCORDION_HDFS = 0;
	private final static int LEFT_ACCORDION_DEDUP4FS = 1;
	private final static int LEFT_ACCORDION_MONGODB = 2;

	private Stage primaryStage;
	private AnchorPane rootLayout;


	private SplitPane mainPaneLayout;
	private AnchorPane rightAnchorPane;
	private TabPane middleTabPane;

	private Accordion leftAccordion;
	private Accordion rightAccordion;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Dedup4 Client");
		this.primaryStage.getIcons().add(new Image("file:../../resources/images/logo.png"));

		initRootLayout();
		initSplitPaneLayout();
		initRightPane();
		initLeftPane();
		initMiddlePane();
		showHdfsOverview();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initSplitPaneLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/MainPaneLayout.fxml"));
			mainPaneLayout = (SplitPane) loader.load();

			rootLayout.getChildren().add(mainPaneLayout);
			AnchorPane.setTopAnchor(mainPaneLayout, Double.valueOf(25));
			AnchorPane.setBottomAnchor(mainPaneLayout, Double.valueOf(0));
			AnchorPane.setLeftAnchor(mainPaneLayout, Double.valueOf(0));
			AnchorPane.setRightAnchor(mainPaneLayout, Double.valueOf(0));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initRightPane() {
		rightAnchorPane = (AnchorPane)mainPaneLayout.getItems().get(RIGHT_PANE_INDEX);
		rightAccordion = (Accordion)rightAnchorPane.getChildren().get(0);
	}

	public void initMiddlePane() {
		middleTabPane = (TabPane)mainPaneLayout.getItems().get(MIDDLE_PANE_INDEX);
		middleTabPane.setRotateGraphic(false);
		middleTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		middleTabPane.setSide(Side.TOP);
		middleTabPane.setFocusTraversable(true);
	}

	public void initLeftPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/LeftPane.fxml"));
			leftAccordion = (Accordion) loader.load();

			AnchorPane anchorPane = (AnchorPane)mainPaneLayout.getItems().get(LEFT_PANE_INDEX);
			anchorPane.getChildren().add(leftAccordion);
			AnchorPane.setBottomAnchor(leftAccordion, Double.valueOf(0));
			AnchorPane.setLeftAnchor(leftAccordion, Double.valueOf(-4));
			AnchorPane.setTopAnchor(leftAccordion, Double.valueOf(-2));
			AnchorPane.setRightAnchor(leftAccordion, Double.valueOf(-4));

			LeftPaneController controller = loader.getController();
			controller.setMainApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showHdfsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/HdfsOverview.fxml"));
			AnchorPane treeTableView = (AnchorPane)loader.load();

			AnchorPane hdfsAnchorPane = (AnchorPane)leftAccordion.getPanes().get(0).getContent();
			hdfsAnchorPane.getChildren().add(treeTableView);
			AnchorPane.setBottomAnchor(treeTableView, Double.valueOf(-10));
			AnchorPane.setLeftAnchor(treeTableView, Double.valueOf(-10));
			AnchorPane.setTopAnchor(treeTableView, Double.valueOf(-10));
			AnchorPane.setRightAnchor(treeTableView, Double.valueOf(-10));

			HdfsOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showFileOverview(String text, FileStatus fileStatus) {
		try {
			AnchorPane textPane = new AnchorPane();

			// File
			TextArea textArea = new TextArea();
			textArea.setText(text);
			textArea.setEditable(false);
			textArea.setWrapText(true);
			textPane.getChildren().add(textArea);
			AnchorPane.setBottomAnchor(textArea, 0d);
			AnchorPane.setLeftAnchor(textArea, 0d);
			AnchorPane.setRightAnchor(textArea, 0d);
			AnchorPane.setTopAnchor(textArea, 0d);

			Tab textAreaTab = new Tab(fileStatus.getPathSuffix());
			textAreaTab.setContent(textPane);
			textAreaTab.setTooltip(new Tooltip(fileStatus.getAbsolutePath()));
			middleTabPane.getTabs().add(textAreaTab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Accordion getRightAccordion() {
		return rightAccordion;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
