package com.dedup4.storage.core.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Yang Mengmeng Created on Apr 20, 2016.
 */
@Component
public class FileServiceImpl implements FileService {


    @Autowired
    private FsShell fsShell;

    @Override
    public void dedupFile(File file) {

    }

}
