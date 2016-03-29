package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.HdfsOverviewController;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	private SplitPane mainPaneLayout;
	private Accordion leftPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Dedup4 Client");
		this.primaryStage.getIcons().add(new Image("file:../../resources/images/logo.png"));

		initRootLayout();

		initSplitPaneLayout();

		initLeftPane();

		showHdfsOverview();
	}

	/**
	 * Initializes the root layout.
	 */
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

	public void initLeftPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/LeftPane.fxml"));
			leftPane = (Accordion) loader.load();
			AnchorPane anchorPane = (AnchorPane)mainPaneLayout.getItems().get(0);
			anchorPane.getChildren().add(leftPane);
			AnchorPane.setBottomAnchor(leftPane, Double.valueOf(0));
			AnchorPane.setLeftAnchor(leftPane, Double.valueOf(-4));
			AnchorPane.setTopAnchor(leftPane, Double.valueOf(-2));
			AnchorPane.setRightAnchor(leftPane, Double.valueOf(-4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showHdfsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/HdfsOverview.fxml"));
			AnchorPane treeTableView = (AnchorPane)loader.load();

			AnchorPane hdfsAnchorPane = (AnchorPane)leftPane.getPanes().get(0).getContent();
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

	public void showFileOverview(String text) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/FileOverview.fxml"));
			AnchorPane fileOverviewPane = (AnchorPane)loader.load();
			TextArea fileTextArea = (TextArea)fileOverviewPane.getChildren().get(0);
			fileTextArea.setText(text);
			fileTextArea.setWrapText(true);

			AnchorPane rightPane = (AnchorPane)mainPaneLayout.getItems().get(1);

			rightPane.getChildren().add(fileTextArea);
			AnchorPane.setBottomAnchor(fileTextArea, Double.valueOf(0));
			AnchorPane.setLeftAnchor(fileTextArea, Double.valueOf(0));
			AnchorPane.setTopAnchor(fileTextArea, Double.valueOf(0));
			AnchorPane.setRightAnchor(fileTextArea, Double.valueOf(0));
				// Set Controller
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
