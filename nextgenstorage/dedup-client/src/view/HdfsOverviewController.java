package view;

import java.io.IOException;

import application.MainApp;
import component.TextFieldContextMenuTreeCell;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeItem.TreeModificationEvent;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FileStatus;
import model.FileStatuses;
import util.http.hdfs.HdfsAccess;

public class HdfsOverviewController {

	@FXML
	private TreeTableView<FileStatus> hdfsTreeTableView;

	@FXML
	private TreeTableColumn<FileStatus, String> pathSuffixColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> permissionColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> lengthColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> accessTimeColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> blockSizeColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> groupColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> modificationTimeColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> ownerColumn;

	@FXML
	private TreeTableColumn<FileStatus, String> replicationColumn;

	private MainApp mainApp;

	public HdfsOverviewController() {}

	@FXML
	private void initialize() {
		hdfsTreeTableView.setEditable(true);

		pathSuffixColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().pathSuffixProperty());
		pathSuffixColumn.setCellFactory((TreeTableColumn<FileStatus, String> p) -> new TextFieldContextMenuTreeCell(mainApp));

		permissionColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().permissionProperty());

		lengthColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().lengthProperty());
		accessTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().accessTimeProperty());
		modificationTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().modificationTimeProperty());
		ownerColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().ownerProperty());
		groupColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().groupProperty());
		blockSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().blockSizeProperty());
		replicationColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().replicationProperty());
		hdfsTreeTableView.setTableMenuButtonVisible(true);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// First load.
		try {
			FileStatus rootFileStatus = HdfsAccess.getInstance().getStatus("/");
			rootFileStatus.setAbsolutePath("/");

			final TreeItem<FileStatus> root = new TreeItem<>(rootFileStatus, new ImageView(new Image("file:../../resources/images/folder.png")));
			root.setExpanded(true);
			FileStatuses statuses = HdfsAccess.getInstance().listDirectory("/");
			dealStatuses(statuses, root);
			hdfsTreeTableView.setRoot(root);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openFolder(TreeItem<FileStatus> parentTreeItem, String absolutePath) {
		try {
			FileStatuses statuses = HdfsAccess.getInstance().listDirectory(absolutePath);
			dealStatuses(statuses, parentTreeItem);
			parentTreeItem.getValue().setOpened(true);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FileStatus getFileStatus(String absolutePath) {
		try {
			return HdfsAccess.getInstance().getStatus(absolutePath);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void dealStatuses(FileStatuses statuses, TreeItem<FileStatus> parenTreeItem) {
		if(statuses != null) {
			statuses.getFileStatuses()
				.stream()
				.filter(i -> i.isDirectory())
				.forEach(
						(fileStatus) -> {
							String parentAbsolutePath = parenTreeItem.getValue().getAbsolutePath();
							parentAbsolutePath = parentAbsolutePath.equals("/") ? "" : parentAbsolutePath;
							fileStatus.setAbsolutePath(parentAbsolutePath + "/" + fileStatus.getPathSuffix());

							TreeItem<FileStatus> dirTreeItem = new TreeItem<>(fileStatus, new ImageView(new Image("file:../../resources/images/folder.png")));
							dirTreeItem.getChildren().add(new TreeItem<>()); // Set an empty tree item.
							parenTreeItem.getChildren().add(dirTreeItem);

							dirTreeItem.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler<TreeModificationEvent<FileStatus>>() {
								@Override
								public void handle(TreeModificationEvent<FileStatus> event) {
									if(!dirTreeItem.getValue().isOpened()) {
										dirTreeItem.getChildren().remove(0); // Remove empty tree item.
										openFolder(dirTreeItem, dirTreeItem.getValue().getAbsolutePath());
									}
								}
							});

							dirTreeItem.addEventHandler(TreeItem.branchCollapsedEvent(), new EventHandler<TreeModificationEvent<FileStatus>>() {
								@Override
								public void handle(TreeModificationEvent<FileStatus> event) {
									if(dirTreeItem.getValue().isOpened()) {
										dirTreeItem.getValue().setOpened(false);
									}
								}
							});

						}
				);

			statuses.getFileStatuses()
				.stream()
				.filter(i -> !i.isDirectory())
				.forEach(
						(fileStatus) -> {
							String parentAbsolutePath = parenTreeItem.getValue().getAbsolutePath();
							parentAbsolutePath = parentAbsolutePath.equals("/") ? "" : parentAbsolutePath;
							fileStatus.setAbsolutePath(parentAbsolutePath + "/" + fileStatus.getPathSuffix());
							parenTreeItem.getChildren().add(new TreeItem<>(fileStatus, new ImageView(new Image("file:../../resources/images/file.png"))));
						}
				);
		}
	}

}
