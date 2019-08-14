package com.mo.guard.service;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.exception.NotFoundAppNameException;
import com.mo.guard.model.Doc;
import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.model.entity.AuthEntity;
import com.mo.guard.model.entity.ResourceEntity;
import com.mo.guard.model.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DocService {

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    AuthServiceImpl authService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    AppServiceImpl appService;

    public void uploadDoc(Doc doc) {

        // == application ==
        String application = doc.getApplication();
        int applicationSequence;
        AppEntity app = appService.findByDisplayNameAndEnableFlag(application, (byte) EnableFlag.Y.getValue());
        if (app != null) {
            applicationSequence = app.getSequence();
        } else {
            // TODO specific
            throw new NotFoundAppNameException();
        }

        Map<String, Doc.DocResource> resources = doc.getResources();
        Map<String, List<String>> auths = doc.getAuths();
        Map<String, List<String>> roles = doc.getRoles();
        List<Map.Entry<String, Doc.DocResource>> listOfResource = new ArrayList<>(resources.entrySet());
        List<Map.Entry<String, List<String>>> listOfAuth = new ArrayList<>(auths.entrySet());
        List<Map.Entry<String, List<String>>> listOfRole = new ArrayList<>(roles.entrySet());

        // == resources ==
        List<ResourceEntity> entityResources = new ArrayList<>();;
        if (resources != null) {
            listOfResource.stream().forEach(entry -> {
                ResourceEntity entityResource = new ResourceEntity();
                // TODO duplicate check ...
                entityResource.setDisplayName(entry.getKey());
                entityResource.setHttpMethod(entry.getValue().getMethod());
                entityResource.setHttpPath(entry.getValue().getPath());
                entityResource.setAppSequence(applicationSequence);
                entityResources.add(entityResource);
            });

            // resource save
            resourceService.saveAll(entityResources);
        }

        // == auth ==
        List<AuthEntity> entityAuths = new ArrayList<>();
        if (auths != null) {
            listOfAuth.stream().forEach(entry -> {
                AuthEntity entityAuth = new AuthEntity();
                entityAuth.setDisplayName(entry.getKey());
                List<String> reqResNames = entry.getValue();
                for(String reqResName : reqResNames) {
                    ResourceEntity entityResource = entityResources.stream()
                            .filter(res -> reqResName.equals(res.getDisplayName()))
                            .findFirst()
                            .get();
                    if(entityResource != null) {
                        entityAuth.getResources().add(entityResource);
                    }
                }
                entityAuths.add(entityAuth);
            });

            authService.saveAll(entityAuths);
        }
        // auth save

        // == roles ==
        if (roles != null) {
            List<RoleEntity> entityRoles = new ArrayList<>();
            listOfRole.stream().forEach(entry -> {
                RoleEntity entityRole = new RoleEntity();
                entityRole.setDisplayName(entry.getKey());
                List<String> reqAuthNames = entry.getValue();
                for(String reqAuthName : reqAuthNames) {
                    AuthEntity entityAuth = entityAuths.stream()
                            .filter(res -> reqAuthName.equals(res.getDisplayName()))
                            .findFirst()
                            .get();
                    if(entityAuth != null) {
                        entityRole.getAuths().add(entityAuth);
                    }
                }
                entityRoles.add(entityRole);
            });
        }
        // role save

    }
}