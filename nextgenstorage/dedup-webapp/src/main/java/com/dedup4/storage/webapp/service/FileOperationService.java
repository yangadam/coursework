package com.dedup4.storage.webapp.service;

import com.dedup4.storage.common.domain.FileRecipe;
import com.dedup4.storage.common.domain.LogicFile;
import com.dedup4.storage.webapp.repository.FileRecipeRepository;
import com.dedup4.storage.webapp.repository.LogicFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
@Service
public class FileOperationService {

    @Autowired
    private LogicFileRepository logicFileRepository;

    @Autowired
    private FileRecipeRepository fileRecipeRepository;

    public FileRecipe getByMd5(String md5) {
        return fileRecipeRepository.findOne(md5);
    }

    public LogicFile saveLogicFile(String currentUser, String path, String name, String md5, long size) {
        LogicFile rootFolder = logicFileRepository.findByOwner(currentUser);
        if (rootFolder == null) {
            rootFolder = LogicFile.createRootFolder(currentUser);
        }
        LogicFile logicFile = rootFolder.addFile(path, name, size);
        logicFile.setMd5(md5);
        return logicFileRepository.save(rootFolder);
    }

    public FileRecipe saveFileRecipe(LogicFile logicFile) {
        FileRecipe fileRecipe = new FileRecipe(logicFile.getMd5(), logicFile.getName(), logicFile.getSize());
        return fileRecipeRepository.save(fileRecipe);
    }

    public FileRecipe getFile(String currentUser, String path, String name) {
        LogicFile rootFolder = logicFileRepository.findByOwner(currentUser);
        LogicFile logicFile = rootFolder.getFileByPath(path + "/" + name);
        return this.getByMd5(logicFile.getMd5());
    }


}
