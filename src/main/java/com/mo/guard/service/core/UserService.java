package com.mo.guard.service.core;

import com.mo.guard.model.table.User;

public interface UserService extends CommonService<User> {

    User findByUserIdAndEnableFlag(String userId, byte enableFlag);

}
