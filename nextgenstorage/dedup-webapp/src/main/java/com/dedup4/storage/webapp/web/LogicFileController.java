package com.dedup4.storage.webapp.web;

import com.dedup4.storage.common.domain.LogicFile;
import com.dedup4.storage.webapp.service.FileOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@RestController
@RequestMapping(value = "/file")
public class LogicFileController {

    @Autowired
    private FileOperationService fileOperationService;

    @RequestMapping(method = RequestMethod.GET)
    public LogicFile treeView(Principal principal) {
        return fileOperationService.getRootFolder(principal.getName());
    }

    @RequestMapping(value = "folder", method = RequestMethod.GET)
    public List<LogicFile> show(@RequestParam String path,
                                Principal principal) {
        LogicFile rootFolder = fileOperationService.getRootFolder(principal.getName());
        LogicFile folder = rootFolder.getFileByPath(path);
        if (folder.isFolder()) {
            return folder.getChildren();
        }
        return new ArrayList<>();
    }

    @RequestMapping(value = "delete")
    public Boolean delete(@RequestParam String path,
                          @RequestParam String name,
                          Principal principal) {
        LogicFile rootFolder = fileOperationService.getRootFolder(principal.getName());
        LogicFile folder = rootFolder.getFileByPath(path);
        folder.delete(name);
        fileOperationService.updateLogicFile(rootFolder);
        return true;
    }

    @RequestMapping(value = "mkdir")
    public Boolean addFolder(@RequestParam String path,
                             @RequestParam String name,
                             Principal principal) {
        LogicFile rootFolder = fileOperationService.getRootFolder(principal.getName());
        LogicFile folderAdded = rootFolder.addFolder(path, name);
        fileOperationService.updateLogicFile(rootFolder);
        return folderAdded != null;
    }

    @RequestMapping(value = "rename")
    public Boolean rename(@RequestParam String path,
                          @RequestParam String oldName,
                          @RequestParam String newName,
                          Principal principal) {
        LogicFile rootFolder = fileOperationService.getRootFolder(principal.getName());
        LogicFile file = rootFolder.getFileByPath(path + '/' + oldName);
        file.setName(newName);
        fileOperationService.updateLogicFile(rootFolder);
        return true;
    }

    @RequestMapping(value = "exist")
    public Boolean exist(@RequestParam String path,
                         @RequestParam String name,
                         Principal principal) {
        LogicFile rootFolder = fileOperationService.getRootFolder(principal.getName());
        LogicFile folder = rootFolder.getFileByPath(path);
        return folder.exist(name);
    }

    @RequestMapping(value = "hasSubDir")
    public Boolean hasSubDir(@RequestParam String path, Principal principal) {
        LogicFile rootFolder = fileOperationService.getRootFolder(principal.getName());
        LogicFile folder = rootFolder.getFileByPath(path);
        return folder.hasSubFolder();
    }

}
