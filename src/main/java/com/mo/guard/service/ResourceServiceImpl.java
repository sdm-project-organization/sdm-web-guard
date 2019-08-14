package com.mo.guard.service;


import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.ResourceEntity;
import com.mo.guard.repository.ResourceRepository;
import com.mo.guard.service.core.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public List<ResourceEntity> findAllByEnableFlag(byte enableFlag) {
        /*return appRepository.findAll();*/
        return resourceRepository.findAllByEnableFlag(enableFlag);
    }

    @Override
    public ResourceEntity findBySequence(int sequence) {
        return resourceRepository.findBySequence(sequence);
    }

    @Override
    public ResourceEntity save(ResourceEntity resource) throws RuntimeException {
        return resourceRepository.save(resource);
    }

    @Override
    public List<ResourceEntity> saveAll(List<ResourceEntity> resources) throws RuntimeException {
        return resourceRepository.saveAll(resources);
    }

    @Override
    public ResourceEntity updateBySequence(int sequence, ResourceEntity targetResource) {
        ResourceEntity originResource = findBySequence(sequence);
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
    public ResourceEntity unenable(int sequence) {
        ResourceEntity resource = findBySequence(sequence);
        resource.setEnableFlag(EnableFlag.N.getValue());
        resourceRepository.flush();
        return resource;
    }


}
