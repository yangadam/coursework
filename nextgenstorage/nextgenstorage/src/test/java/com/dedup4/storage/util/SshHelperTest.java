package com.dedup4.storage.util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Yang Mengmeng
 *         Created on Mar 19, 2016
 */
public class SshHelperTest {

    private SshHelper helper;
    private File tempFile;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder() {

        @Override
        protected void after() {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
            this.delete();
        }

    };
    private String fileName = "test.txt";

    @Before
    public void setUp() throws Exception {
        helper = new SshHelper("139.129.10.110", "YangMengmeng930626");
        tempFile = new File(tempFolder.getRoot().getAbsolutePath() + File.separator + fileName);
        tempFile.createNewFile();
    }

    @Test
    public void testExecuteCommand() {
        assertTrue(helper.executeCommand("ls"));
    }

    @Test
    public void testUpload() {
        assertTrue(helper.upload(tempFile.getParentFile().toPath(), ".", fileName));
    }

}