package cn.edu.xmu.comm.action;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;

import com.opensymphony.xwork2.ActionSupport;
import cn.edu.xmu.comm.commons.utils.FilesUtil;
import org.apache.struts2.util.ServletContextAware;

/**
 * 上传文件
 *
 * @author Yaojie Lu
 * @version 12/28/2014
 */
public class UploadAction extends ActionSupport implements ServletContextAware{

    @Override
    public String execute(){

        FilesUtil filesUtil = new FilesUtil();
        System.out.println("File Name is:"+getFileFileName());
        System.out.println("File ContentType is:"+getFileContentType());
        System.out.println("Files Directory is:"+filesPath);
        try {
            filesUtil.saveFile(getFile(), getFileFileName(), context.getRealPath("") + File.separator + filesPath);
        } catch (IOException e) {
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;

    }

    private File file;
    private String fileContentType;
    private String fileFileName;
    private String filesPath;
    private ServletContext context;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    @Override
    public void setServletContext(ServletContext ctx) {
        this.context=ctx;
    }

}