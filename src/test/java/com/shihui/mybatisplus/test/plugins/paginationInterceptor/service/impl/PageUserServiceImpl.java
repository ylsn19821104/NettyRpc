package com.shihui.mybatisplus.test.plugins.paginationInterceptor.service.impl;

import com.shihui.mybatisplus.test.plugins.paginationInterceptor.entity.PageUser;
import com.shihui.mybatisplus.test.plugins.paginationInterceptor.service.PageUserService;
import org.springframework.stereotype.Service;

import com.shihui.mybatisplus.service.impl.ServiceImpl;
import com.shihui.mybatisplus.test.plugins.paginationInterceptor.mapper.PageUserMapper;

@Service
public class PageUserServiceImpl extends ServiceImpl<PageUserMapper, PageUser> implements PageUserService {

}
