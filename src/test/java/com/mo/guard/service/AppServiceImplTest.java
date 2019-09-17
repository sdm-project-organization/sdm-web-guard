package com.mo.guard.service;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.repository.AppRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        AppServiceImpl.class
})
public class AppServiceImplTest {

    @Autowired
    AppServiceImpl appService;

    @MockBean
    AppRepository appRepository;

    @Test
    public void findBySequence() {
    }

    @Test
    public void findBySequenceAndEnableFlag() {

    }

    @Test
    public void findByDisplayNameAndEnableFlag() {
        AppEntity targetApp = new AppEntity(1, 1, "guard", "desc");
        given(appRepository.findByDisplayNameAndEnableFlag("guard", EnableFlag.YES)).willReturn(targetApp);

        // [CASE1]
        AppEntity targetAppFromRepositoryA = appService.findByDisplayNameAndEnableFlag("", EnableFlag.YES);
        assertNull(targetAppFromRepositoryA);

        // [CASE2]
        AppEntity targetAppFromRepositoryB = appService.findByDisplayNameAndEnableFlag(null, EnableFlag.YES);
        assertNull(targetAppFromRepositoryB);

        // [CASE3]
        AppEntity targetAppFromRepositoryC = appService.findByDisplayNameAndEnableFlag("guard", EnableFlag.YES);
        testEquals(targetAppFromRepositoryC, targetApp);
    }

    @Test
    public void findAllByEnableFlag() {
        List<AppEntity> expectedApp = Arrays.asList(
                new AppEntity(1, 1, "guard", "desc"),
                new AppEntity(1, 2, "dict", "desc")
        );
        given(appRepository.findAllByEnableFlag(EnableFlag.YES)).willReturn(expectedApp);

        List<AppEntity>  resultApp = appService.findAllByEnableFlag(EnableFlag.YES);
        testArrayEquals(expectedApp, resultApp);
    }

    @Test
    public void save() {

    }

    @Test
    public void updateBySequence() {
        AppEntity targetApp = new AppEntity(1, 1, "guard", "desc");
        given(appRepository.findBySequenceAndEnableFlag(1, EnableFlag.YES)).willReturn(targetApp);
        given(appRepository.findBySequenceAndEnableFlag(2, EnableFlag.YES)).willReturn(targetApp);

        // [CASE1]
        AppEntity updateApp =  new AppEntity(2, 2, "dict", "meta", ActiveFlag.NO, EnableFlag.NO);
        AppEntity targetAppFromRepositoryA = appService.updateBySequence(1, updateApp);
        AppEntity answerApp =  new AppEntity(2, 2, "dict", "meta", ActiveFlag.NO, EnableFlag.YES);
        testEquals(targetAppFromRepositoryA, answerApp);

        // [CASE2]
        AppEntity targetAppFromRepositoryB = appService.updateBySequence(2, updateApp);
        assertNull(targetAppFromRepositoryB);
    }

    @Test
    public void unenable() {
        // [CASE1]
        AppEntity expectedApp = new AppEntity(1, 1, "guard", "desc");
        given(appRepository.findBySequenceAndEnableFlag(1, EnableFlag.YES)).willReturn(expectedApp);
        AppEntity resultAppA = appService.unenable(1);
        assertEquals(resultAppA.getEnableFlag(), EnableFlag.NO);

        // [CASE2]
        given(appRepository.findBySequenceAndEnableFlag(2, EnableFlag.YES)).willReturn(null);
        AppEntity resultAppB = appService.unenable(2);
        assertNull(resultAppB);
    }

    public void testEquals(AppEntity origin, AppEntity target) {
        System.out.println("origin : " + origin.toString());
        System.out.println("target : " + target.toString());
        assertEquals(origin.getSequence(), target.getSequence());
        assertEquals(origin.getOrganSequence(), target.getOrganSequence());
        assertEquals(origin.getOrganSequence(), target.getOrganSequence());
        assertEquals(origin.getDisplayOrder(), target.getDisplayOrder());
        assertEquals(origin.getDisplayName(), target.getDisplayName());
        assertEquals(origin.getDesc(), target.getDesc());
        assertEquals(origin.getActiveFlag(), target.getActiveFlag());
        assertEquals(origin.getEnableFlag(), target.getEnableFlag());
        assertEquals(origin.getCreatedDate(), target.getCreatedDate());
        assertEquals(origin.getUpdatedDate(), target.getUpdatedDate());
    }

    public void testArrayEquals(List<AppEntity> origin, List<AppEntity> target) {
        assertEquals(origin.size(), target.size());
        for(int i=0; i<origin.size(); i++) {
            testEquals(origin.get(i), target.get(i));
        }
    }

}