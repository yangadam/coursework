package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;

/**
 * 上传文件
 *
 * @author Yaojie Lu
 * @version 12/28/2014
 */
@Controller
public class UploadAction extends ActionSupport {

    @Resource
    private CarService carService;

    private String title;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String savePath;

    private String license;

    @Override
    @Required(name = "director,clerk")
    public String execute() {

        try {
            FileOutputStream fos = new FileOutputStream(getSavePath() + File.separator + getUploadFileName());
            FileInputStream fis = new FileInputStream(getUpload());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return INPUT;
        } catch (IOException e) {
            e.printStackTrace();
            return INPUT;
        }
        carService.confirmCarRentParkPlace(license);
        return SUCCESS;
    }

    public String getSavePath() {
        return ServletActionContext.getServletContext().getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}