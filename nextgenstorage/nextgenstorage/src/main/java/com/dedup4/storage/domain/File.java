package com.dedup4.storage.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Apr 04, 2016.
 */
@Document
public class File extends UpdateInfo {

    private String name;
    private Boolean isFolder;
    private String absolutePath;
    private Long size;
    private List<File> children;
    private String owner;
    private String tempFileName;
    private Location location;//server/hdfs/container

    private File(String name, String owner) {
        this.setName(name);
        this.setOwner(owner);
        this.setIsFolder(true);
        this.setChildren(new ArrayList<>());
    }

    private File(String name, long size, String owner) {
        this.setName(name);
        this.setSize(size);
        this.setOwner(owner);
        this.setIsFolder(false);
        this.setLocation(Location.SERVER);
    }

    public static File createRootFolder(String username) {
        File file = new File("", username);
        file.setAbsolutePath("/");
        return file;
    }

    private static String childAbsPath(String parentPath, String name) {
        String absPath = parentPath;
        if (!absPath.endsWith("/")) {
            absPath = absPath + "/";
        }
        return absPath + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<File> getChildren() {
        return children;
    }

    public void setChildren(List<File> children) {
        this.children = children;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTempFileName() {
        return tempFileName;
    }

    public void setTempFileName(String tempFileName) {
        this.tempFileName = tempFileName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public File getFileByName(String name) {
        for (File file : children) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    public File getFileByPath(String path) {
        String temp = path.trim();
        if (temp.startsWith("/")) {
            temp = temp.substring(1);
        }
        if (StringUtils.isBlank(temp)) {
            return this;
        }
        int pos = temp.indexOf('/');
        File child = getFileByName(temp.substring(0, pos));
        if (child != null) {
            return child.getFileByPath(temp.substring(pos + 1));
        }
        return null;
    }

    public File addFolder(String name) {
        File file = new File(name, this.owner);
        file.setAbsolutePath(childAbsPath(this.absolutePath, name));
        children.add(file);
        return file;
    }

    public File addFolder(String path, String name) {
        File parentFolder = this.getFileByPath(path);
        if (parentFolder != null && parentFolder.isFolder()) {
            return parentFolder.addFolder(name);
        }
        return null;
    }

    public File addFile(String name, long size) {
        File file = new File(name, size, this.owner);
        file.setAbsolutePath(childAbsPath(this.absolutePath, name));
        children.add(file);
        return file;
    }

    public File addFile(String path, String name, long size) {
        File parentFolder = this.getFileByPath(path);
        if (parentFolder != null && parentFolder.isFolder()) {
            return parentFolder.addFile(name, size);
        }
        return null;
    }

    public boolean delete(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(name)) {
                children.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean exist(String name) {
        for (File file : children) {
            if (file.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubFolder() {
        for (File file : children) {
            if (file.isFolder()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", isFolder=" + isFolder +
                ", absolutePath='" + absolutePath + '\'' +
                ", size=" + size +
                ", children=" + children +
                ", owner='" + owner + '\'' +
                '}';
    }

    public enum Location {
        SERVER, HDFS, CONTAINER
    }
}
