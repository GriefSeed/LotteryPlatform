package com.hyht.LotteryPlat.service.impl;


import com.hyht.LotteryPlat.dao.UsersDao;
import com.hyht.LotteryPlat.entity.Users;
import com.hyht.LotteryPlat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersDao usersDao;

    @Override
    public Users queryUserById(int userId) {
        return usersDao.queryUserById(userId);
    }
}
