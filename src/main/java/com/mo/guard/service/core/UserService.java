package com.mo.guard.service.core;

import com.mo.guard.model.entity.UserEntity;

public interface UserService extends CommonService<UserEntity> {

    UserEntity findByUsernameAndEnableFlag(String username, byte enableFlag);

}
