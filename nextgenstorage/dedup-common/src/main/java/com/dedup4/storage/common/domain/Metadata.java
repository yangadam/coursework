package com.dedup4.storage.common.domain;

import java.util.List;

/**
 * @author Yang Mengmeng Created on Apr 20, 2016.
 */
public class Metadata extends IdentityData {

    private List<MetadataItem> metadatas;

    public List<MetadataItem> getMetadatas() {
        return metadatas;
    }

    public void setMetadatas(List<MetadataItem> metadatas) {
        this.metadatas = metadatas;
    }
}
