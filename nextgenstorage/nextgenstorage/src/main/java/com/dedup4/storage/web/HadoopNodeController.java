package com.dedup4.storage.web;

import com.dedup4.storage.domain.HadoopNode;
import com.dedup4.storage.repository.HadoopNodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
@RestController
@RequestMapping("/hadoopNode")
public class HadoopNodeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HadoopNodeController.class);

    @Autowired
    private HadoopNodeRepository hadoopNodeRepository;

    /**
     * @param hadoopNode hadoop node
     * @return name of hadoop node if success, otherwise null
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody HadoopNode hadoopNode) {
        try {
            hadoopNodeRepository.insert(hadoopNode);
        } catch (Exception e) {
            LOGGER.error("Error when adding hadoop node into database", e);
            return null;
        }
        return hadoopNode.getName();
    }

    /**
     * @return hadoop node as list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<HadoopNode> list() {
        return hadoopNodeRepository.findAll();
    }

    @RequestMapping(value = "restart", method = RequestMethod.GET)
    public Boolean restart(@RequestParam String host) {
        // TODO
        return true;
    }

    @RequestMapping(value = "stop", method = RequestMethod.GET)
    public Boolean stop(@RequestParam String host) {
        // TODO
        return true;
    }

}