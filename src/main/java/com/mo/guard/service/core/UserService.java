package com.mo.guard.service.core;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.UserEntity;

public interface UserService extends CommonService<UserEntity> {

    UserEntity findByUsernameAndEnableFlag(String username,
                                           EnableFlag enableFlag);

}
