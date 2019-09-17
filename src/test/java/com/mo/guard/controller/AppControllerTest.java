package com.mo.guard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mo.guard.GuardApplication;
import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.service.AppServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
public class AppControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppServiceImpl appService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getApps() throws Exception {

    }

    @Test
    public void createApp() {
    }

    @Test
    public void getApp() throws Exception {
        AppEntity targetApp = AppEntity.getSample();
        given(appService.findBySequenceAndEnableFlag(1, EnableFlag.YES)).willReturn(targetApp);

        // TODO prefix update
        final ResultActions actions = mockMvc
                .perform(get("/apps/{appId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("displayName").value(targetApp.getDisplayName()));
    }

    @Test
    public void updateApp() throws Exception {

        AppEntity originApp = AppEntity.getSample();
        AppEntity targetApp = AppEntity.getSample();
        targetApp.setSequence(9999);
        targetApp.setOrganSequence(9999);
        targetApp.setDisplayName("newName");
        targetApp.setDesc("newDesc");
        targetApp.setDisplayOrder(9999);
        targetApp.setEnableFlag(EnableFlag.NO);
        targetApp.setActiveFlag(ActiveFlag.NO);
        given(appService.updateBySequence(1, targetApp)).willReturn(targetApp);

        // TODO updated_dt, editor 수정 확인

        final ResultActions actions = mockMvc
                .perform(put("/apps/{appId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(targetApp)))
                .andDo(print());

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("displayName").value(targetApp.getDisplayName()));
    }

    @Test
    public void deleteApp() {
    }
}