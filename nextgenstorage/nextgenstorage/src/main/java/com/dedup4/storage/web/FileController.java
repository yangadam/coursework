package com.dedup4.storage.web;

import com.dedup4.storage.domain.File;
import com.dedup4.storage.repository.FileRepository;
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
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @RequestMapping(method = RequestMethod.GET)
    public File treeView(Principal principal) {
        return fileRepository.findByOwner(principal.getName());
    }

    @RequestMapping(value = "folder", method = RequestMethod.GET)
    public List<File> show(@RequestParam String path,
                           Principal principal) {
        File rootFolder = fileRepository.findByOwner(principal.getName());
        File folder = rootFolder.getFileByPath(path);
        if (folder.isFolder()) {
            return folder.getChildren();
        }
        return new ArrayList<>();
    }

    @RequestMapping(value = "delete")
    public Boolean delete(@RequestParam String path,
                          @RequestParam String name,
                          Principal principal) {
        File rootFolder = fileRepository.findByOwner(principal.getName());
        File folder = rootFolder.getFileByPath(path);
        return folder.delete(name);
    }

    @RequestMapping(value = "mkdir")
    public Boolean addFolder(@RequestParam String path,
                             @RequestParam String name,
                             Principal principal) {
        File rootFolder = fileRepository.findByOwner(principal.getName());
        File folderAdded = rootFolder.addFolder(path, name);
        fileRepository.save(rootFolder);
        return folderAdded != null;
    }

    @RequestMapping(value = "exist")
    public Boolean exist(@RequestParam String path,
                         @RequestParam String name,
                         Principal principal) {
        File rootFolder = fileRepository.findByOwner(principal.getName());
        File folder = rootFolder.getFileByPath(path);
        return folder.exist(name);
    }

    @RequestMapping(value = "hasSubDir")
    public Boolean hasSubDir(@RequestParam String path, Principal principal) {
        File rootFolder = fileRepository.findByOwner(principal.getName());
        File folder = rootFolder.getFileByPath(path);
        return folder.hasSubFolder();
    }

}
