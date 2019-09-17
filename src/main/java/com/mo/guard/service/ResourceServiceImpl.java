package com.mo.guard.service;


import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.ResourceEntity;
import com.mo.guard.repository.ResourceRepository;
import com.mo.guard.service.core.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    /**
     * findAllByEnableFlag
     *
     * @param enableFlag
     * */
    @Override
    public List<ResourceEntity> findAllByEnableFlag(EnableFlag enableFlag) {
        /*return appRepository.findAll();*/
        return resourceRepository.findAllByEnableFlag(enableFlag);
    }

    /**
     * findBySequence
     *
     * @param sequence
     * */
    @Override
    public ResourceEntity findBySequence(int sequence) {
        return resourceRepository.findBySequence(sequence);
    }

    /**
     * save
     *
     * @param resource
     * */
    @Override
    public ResourceEntity save(ResourceEntity resource)
            throws RuntimeException {
        return resourceRepository.save(resource);
    }

    /**
     * TODO
     * saveOrUpdate
     *
     * @param resource
     */
    public ResourceEntity saveAndUpdate(ResourceEntity resource)
            throws RuntimeException {
        return null;
    }

    /**
     * saveAll
     *
     * @param resources
     * */
    @Override
    public List<ResourceEntity> saveAll(List<ResourceEntity> resources)
            throws RuntimeException {
        return resourceRepository.saveAll(resources);
    }

    /**
     * updateBySequence
     *
     * @param sequence
     * @param targetResource
     * */
    @Override
    public ResourceEntity updateBySequence(int sequence,
                                           ResourceEntity targetResource) {
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

    /**
     * unenable
     *
     * @param sequence
     */
    @Override
    public ResourceEntity unenable(int sequence) {
        ResourceEntity resource = findBySequence(sequence);
        resource.setEnableFlag(EnableFlag.NO);
        resourceRepository.flush();
        return resource;
    }


}
