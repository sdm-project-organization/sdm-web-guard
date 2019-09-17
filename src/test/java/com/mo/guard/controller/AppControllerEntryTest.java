package com.mo.guard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.service.AppServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AppControllerEntryTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AppServiceImpl appService;

    @Test
    public void getApps() throws Exception {

    }

    @Test
    public void getApp() throws Exception {

        // NOT MOCK
        AppEntity targetApp = AppEntity.getSample();
        /*given(appService.findBySequenceAndEnableFlag(1, EnableFlag.YES)).willReturn(targetApp);*/

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
    public void createApp() {

    }

    @Test
    public void updateApp() throws Exception {

        AppEntity targetApp = AppEntity.getSample();
        targetApp.setSequence(9999);
        targetApp.setOrganSequence(9999);
        targetApp.setDisplayName("newName");
        targetApp.setDesc("newDesc");
        targetApp.setDisplayOrder(9999);
        targetApp.setEnableFlag(EnableFlag.NO);
        targetApp.setActiveFlag(ActiveFlag.NO);

        final int SEQUENCE = 1;
        final ResultActions actions = mockMvc
                .perform(put("/apps/{appId}", SEQUENCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(targetApp)))
                .andDo(print());

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("sequence").value(SEQUENCE))
                .andExpect(jsonPath("organSequence").value(targetApp.getOrganSequence()))
                .andExpect(jsonPath("displayOrder").value(targetApp.getDisplayOrder()))
                .andExpect(jsonPath("displayName").value(targetApp.getDisplayName()))
                .andExpect(jsonPath("desc").value(targetApp.getDesc()))
                // * Enum -> String when Response
                .andExpect(jsonPath("activeFlag").value(targetApp.getActiveFlag().toString()))
                .andExpect(jsonPath("enableFlag").value(EnableFlag.YES.toString()));

                // TODO updated_dt, editor 수정 확인
                /*.andExpect(jsonPath("editor").value(targetApp.getDisplayName()));
                .andExpect(jsonPath("updatedDate").value(targetApp.getDisplayName()));*/
    }

    @Test
    public void deleteApp() throws Exception {
        final int SEQUENCE = 1;
        final ResultActions actions = mockMvc
                .perform(delete("/apps/{appId}", SEQUENCE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        actions
                .andExpect(status().isOk());
    }
}