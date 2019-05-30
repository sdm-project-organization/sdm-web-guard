package com.sdm.login.services.core;

import com.sdm.login.models.tables.User;

public interface UserService extends CommonService<User> {

    User findByUserIdAndEnableFlag(String userId, byte enableFlag);

}
