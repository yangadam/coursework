package component;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.FileStatus;
import util.http.hdfs.HdfsAccess;
import view.HdfsOverviewController;

public class TextFieldContextMenuTreeCell extends TreeTableCell<FileStatus, String>{

	private TextField textField;
	private final ContextMenu contextMenu = new ContextMenu();

	private MainApp mainApp;

	public TextFieldContextMenuTreeCell(MainApp mainApp) {
		this.mainApp = mainApp;

		MenuItem viewItem = new MenuItem("View");
		MenuItem refreshItem = new MenuItem("Refresh");
		MenuItem propertiesItem = new MenuItem("Properties");
		contextMenu.getItems().addAll(viewItem, refreshItem, propertiesItem);

		// Add refresh action.
		refreshItem.setOnAction((ActionEvent t) -> {
			TreeItem<FileStatus> currentTreeItem = getTreeTableRow().getTreeItem();
			FileStatus currentFileStatus = currentTreeItem.getValue();

			if(currentFileStatus.isDirectory()) {
				currentTreeItem.getChildren().remove(0, currentTreeItem.getChildren().size());
				HdfsOverviewController.openFolder(currentTreeItem, currentFileStatus.getAbsolutePath());
				currentTreeItem.setExpanded(true);
			} else {
				FileStatus newFileStatus = HdfsOverviewController.getFileStatus(currentFileStatus.getAbsolutePath());
				newFileStatus.setPathSuffix(currentFileStatus.getPathSuffix());
				newFileStatus.setAbsolutePath(currentFileStatus.getAbsolutePath());
				currentTreeItem.setValue(newFileStatus);
			}
		});

		// Add view action
		viewItem.setOnAction((ActionEvent t) -> {
			try {
				FileStatus fileStatus = getTreeTableRow().getTreeItem().getValue();
				String text;
				if(fileStatus.isDirectory()) {
					text = HdfsAccess.getInstance().getDirectoryContentSummary(fileStatus.getAbsolutePath()).getDisplayText();
				} else {
					text = HdfsAccess.getInstance().open(fileStatus.getAbsolutePath(), null);
				}

				mainApp.showFileOverview(text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem());
        setGraphic(getTreeTableRow().getGraphic());
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased((KeyEvent t) -> {
        	if(t.getCode() == KeyCode.ENTER) {
        		commitEdit(textField.getText());
        	} else if (t.getCode() == KeyCode.ESCAPE) {
        		cancelEdit();
			}
        });
    }

	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeTableRow().getGraphic());
                setContextMenu(contextMenu);
            }
        }
	}

	@Override
	public void commitEdit(String newValue) {
		super.commitEdit(newValue);

		TreeItem<FileStatus> currentTreeItem = getTreeTableRow().getTreeItem();
		FileStatus fileStatus = currentTreeItem.getValue();

		if(!canRename(fileStatus, newValue)) {
			return;
		}

		TreeItem<FileStatus> parentTreeItem = currentTreeItem.getParent();
		FileStatus parentFileStatus = parentTreeItem.getValue();

		try {
			String newAbsolutePath = getNewAbsoultePath(parentFileStatus, newValue);
			HdfsAccess.getInstance().rename(fileStatus.getAbsolutePath(), newAbsolutePath);
			fileStatus.setAbsolutePath(newAbsolutePath);

			parentTreeItem.getChildren().remove(0, parentTreeItem.getChildren().size());
			HdfsOverviewController.openFolder(parentTreeItem, parentFileStatus.getAbsolutePath());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getNewAbsoultePath(FileStatus parentFileStatus, String newValue) {
		String parentAbsolutePath = parentFileStatus.getAbsolutePath();
		parentAbsolutePath = parentAbsolutePath.equals("/") ? "" : parentAbsolutePath;
		return parentAbsolutePath + "/" + newValue;
	}

	private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

	private boolean canRename(FileStatus fileStatus, String newValue) {
		if(fileStatus.getPathSuffix().equals(newValue)) {
			return false;
		}
		if(fileStatus.getAbsolutePath().equals("/")) {
			setText("/");
			return false;
		}
		return true;
	}
}
