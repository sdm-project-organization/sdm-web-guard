package com.mo.guard.services.core;

import com.mo.guard.models.tables.User;

public interface UserService extends CommonService<User> {

    User findByUserIdAndEnableFlag(String userId, byte enableFlag);

}
