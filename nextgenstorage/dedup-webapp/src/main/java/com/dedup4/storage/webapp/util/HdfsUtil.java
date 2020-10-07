package com.dedup4.storage.webapp.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HdfsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HdfsUtil.class);

    private String hdfsPath = "hdfs://139.129.17.212:9000";

    private Configuration configuration = new Configuration();

    public HdfsUtil(String basePath) {
        hdfsPath += basePath;
    }

    public void uploadFileFromPath(String sourcePath, String hdfsDstPath) throws Exception {
        FileSystem hdfs = FileSystem.get(configuration);
        Path src = new Path(sourcePath);
        Path dst = new Path(hdfsPath + hdfsDstPath);
        hdfs.copyFromLocalFile(src, dst);
        hdfs.close();
    }

    public void uploadFile(File file, String hdfsDstPath) throws IOException {
        byte[] buff = new byte[1024 * 1024];
        FileSystem hdfs = FileSystem.get(configuration);
        Path dst = new Path(hdfsPath + hdfsDstPath);
        FSDataOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = hdfs.create(dst);
            while (inputStream.read(buff) != -1) {
                outputStream.write(buff, 0, buff.length);
            }
        } catch (Exception e) {
            LOGGER.warn(e.toString());
        } finally {
            if (outputStream != null) {
                inputStream.close();
                outputStream.close();
            }
        }
    }

    public void createFile(String content, String hdfsLocation) throws Exception {
        byte[] buff = content.getBytes();
        FileSystem hdfs = FileSystem.get(configuration);
        Path dst = new Path(hdfsPath + hdfsLocation);
        FSDataOutputStream outputStream = null;

        try {
            outputStream = hdfs.create(dst);
            outputStream.write(buff, 0, buff.length);
        } catch (Exception e) {
            LOGGER.warn(e.toString());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    public void renameFile(String hdfsSrcPath, String hdfsDstPath) throws Exception {
        FileSystem hdfs = FileSystem.get(configuration);
        Path dst = new Path(hdfsPath);

        Path frpath = new Path(hdfsPath + hdfsSrcPath);
        Path topath = new Path(hdfsPath + hdfsDstPath);

        hdfs.rename(frpath, topath);

        FileStatus files[] = hdfs.listStatus(dst);
        for (FileStatus file : files) {
            LOGGER.info(file.getPath().toString());
        }
    }

    public void deleteFile(String delHdfsPath, String fileName) throws Exception {
        FileSystem hdfs = FileSystem.get(configuration);

        String path = "/users/" + delHdfsPath + "/" + fileName;
        Path topath = new Path(path.trim());

        boolean ok = hdfs.delete(topath, true);
        LOGGER.info(ok ? "deletion succeed" : "deletion failed");

        // 输出当前目录
        // FileStatus files[] = hdfs.listStatus(dst);
        // for (FileStatus file : files) {
        // LOGGER.info(file.getPath().toString());
        // }
    }

    public void getModifyTime(String hdfsLocation) throws Exception {
        FileSystem hdfs = FileSystem.get(configuration);
        Path dst = new Path(hdfsPath + hdfsLocation);

        FileStatus files[] = hdfs.listStatus(dst);
        for (FileStatus file : files) {
            LOGGER.info(file.getPath() + "\t" + file.getModificationTime());
            LOGGER.info(file.getPath() + "\t" + new Date(file.getModificationTime()));
        }
    }

    public boolean exists(String hdfsLocation) throws Exception {
        FileSystem hdfs = FileSystem.get(configuration);
        Path dst = new Path(hdfsPath + hdfsLocation);

        return hdfs.exists(dst);
    }

    public void mkdir(String dirPath, String dirName) throws IOException {
        FileSystem hdfs = FileSystem.get(configuration);
        Path dstPath = new Path(hdfsPath + dirPath + "/" + dirName);
        hdfs.mkdirs(dstPath);
    }

    public ArrayList<FileInfo> getDirectoryFromHdfs(String hdfsLocation) throws Exception {
        ArrayList<FileInfo> list = new ArrayList<>();
        FileInfo fileInfo;

        DistributedFileSystem hdfs = (DistributedFileSystem) FileSystem.get(configuration);
        FileStatus fileList[] = hdfs.listStatus(new Path(hdfsPath + hdfsLocation));
        int size = fileList.length;
        for (FileStatus aFileList : fileList) {
            fileInfo = new FileInfo();
            fileInfo.setFileName(aFileList.getPath().getName());
            if (aFileList.isDirectory())
                fileInfo.setIsDirectory(1);
            else {
                fileInfo.setIsDirectory(0);
            }
            fileInfo.setFileSize(aFileList.getLen());

            list.add(fileInfo);
        }

        return list;
    }

    public InputStream readFile(String filePath) throws IOException {
        FileSystem hdfs = FileSystem.get(configuration);
        Path srcPath = new Path(hdfsPath + filePath);
        InputStream in = null;
        try {
            in = hdfs.open(srcPath);
        } catch (Exception e) {
            LOGGER.warn(e.toString());
        }
        return in;
    }

    public boolean deleteDir(String hdfsDir) throws IOException {
        FileSystem hdfs = FileSystem.get(configuration);
        Path path = new Path(hdfsPath + hdfsDir);
        return hdfs.delete(path, true);
    }

    public String calculateHdfsMD5(String filePath) throws IOException, NoSuchAlgorithmException {

        FileSystem hdfs = FileSystem.get(configuration);
        Path path = new Path(hdfsPath + filePath);
        FSDataInputStream os = hdfs.open(path);

        byte[] buffer = new byte[1024 * 1024 * 2];
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        while (true) {
            int bytesRead = os.read(buffer);
            if (bytesRead <= -1)
                break;
            else if (bytesRead > 0) {
                md5.update(buffer, 0, bytesRead);
            }
        }
        byte[] result = md5.digest();

        return DatatypeConverter.printHexBinary(result).toLowerCase();
    }

    public boolean hasSubDir(String hdfsDir) throws Exception {
        List<FileInfo> list = getDirectoryFromHdfs(hdfsDir);

        for (FileInfo aList : list) {
            if (aList.getIsDirectory() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean replaceWithEmpty(String dirPath, String fileName) throws IOException {
        FileSystem hdfs = FileSystem.get(configuration);
        Path path = new Path(hdfsPath + dirPath + "/" + fileName);

        boolean ok = hdfs.delete(path, true);
        hdfs.create(path);
        return ok;
    }

}