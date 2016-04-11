package com.dedup4.storage.common.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Apr 04, 2016.
 */
public class LogicFile extends UpdateInfo {

    private String name;
    private Boolean isFolder;
    private String absolutePath;
    private long size;
    private List<LogicFile> children;
    private String owner;
    private String md5;

    private LogicFile(String name, String owner) {
        this.setName(name);
        this.setOwner(owner);
        this.setIsFolder(true);
        this.setChildren(new ArrayList<>());
    }

    private LogicFile(String name, long size, String owner) {
        this.setName(name);
        this.setSize(size);
        this.setOwner(owner);
        this.setIsFolder(false);
    }

    public static LogicFile createRootFolder(String username) {
        LogicFile file = new LogicFile("", username);
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<LogicFile> getChildren() {
        return children;
    }

    public void setChildren(List<LogicFile> children) {
        this.children = children;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public LogicFile getFileByName(String name) {
        for (LogicFile file : children) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    public LogicFile getFileByPath(String path) {
        String temp = path.trim();
        if (temp.startsWith("/")) {
            temp = temp.substring(1);
        }
        if (StringUtils.isBlank(temp)) {
            return this;
        }
        int pos = temp.indexOf('/');
        pos = (pos == -1 ? temp.length() - 1 : pos);
        LogicFile child = getFileByName(temp.substring(0, pos));
        if (child != null) {
            return child.getFileByPath(temp.substring(pos + 1));
        }
        return null;
    }

    public LogicFile addFolder(String name) {
        LogicFile file = new LogicFile(name, this.owner);
        file.setAbsolutePath(childAbsPath(this.absolutePath, name));
        children.add(file);
        return file;
    }

    public LogicFile addFolder(String path, String name) {
        LogicFile parentFolder = this.getFileByPath(path);
        if (parentFolder != null && parentFolder.isFolder()) {
            return parentFolder.addFolder(name);
        }
        return null;
    }

    public LogicFile addFile(String name, long size) {
        LogicFile file = new LogicFile(name, size, this.owner);
        file.setAbsolutePath(childAbsPath(this.absolutePath, name));
        children.add(file);
        return file;
    }

    public LogicFile addFile(String path, String name, long size) {
        LogicFile parentFolder = this.getFileByPath(path);
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
        for (LogicFile file : children) {
            if (file.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubFolder() {
        for (LogicFile file : children) {
            if (file.isFolder()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "LogicFile{" +
                "name='" + name + '\'' +
                ", isFolder=" + isFolder +
                ", absolutePath='" + absolutePath + '\'' +
                ", size=" + size +
                ", children=" + children +
                ", owner='" + owner + '\'' +
                '}';
    }

}
