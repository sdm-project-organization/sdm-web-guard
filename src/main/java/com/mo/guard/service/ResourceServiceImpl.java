package com.mo.guard.service;


import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Resource;
import com.mo.guard.repository.ResourceRepository;
import com.mo.guard.repository.ResourceRepository;
import com.mo.guard.service.core.ResourceService;
import com.mo.guard.service.core.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public List<Resource> findAllByEnableFlag(byte enableFlag) {
        /*return appRepository.findAll();*/
        return resourceRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public Resource findBySequence(int sequence) {
        return resourceRepository.findBySequence(sequence);
    }

    @Override
    public Resource save(Resource resource) throws Exception {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateBySequence(int sequence, Resource targetResource) {
        Resource originResource = findBySequence(sequence);
        originResource.setHttpPath(targetResource.getHttpPath());
        originResource.setHttpMethod(targetResource.getHttpMethod());
        originResource.setDisplayName(targetResource.getDisplayName());
        originResource.setDisplayOrder(targetResource.getDisplayOrder());
        originResource.setDesc(targetResource.getDesc());
        originResource.setMeta(targetResource.getMeta());
        resourceRepository.flush();
        return originResource;
    }
    @Override
    public Resource unenable(int sequence) {
        Resource resource = findBySequence(sequence);
        resource.setEnableFlag(EnableFlag.N.getValue());
        resourceRepository.flush();
        return resource;
    }


}
