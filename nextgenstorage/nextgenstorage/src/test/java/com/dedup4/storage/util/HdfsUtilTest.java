package com.dedup4.storage.util;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Yang Mengmeng Created on Mar 13, 2016.
 */
@Ignore
public class HdfsUtilTest {

    private HdfsUtil hdfsUtil;

    @Before
    public void setUp() throws Exception {
        hdfsUtil = new HdfsUtil("/users/");
    }

    @Test
    public void testGetDirectoryFromHdfs() throws Exception {
        System.out.println(hdfsUtil.getDirectoryFromHdfs(""));
    }
}