package com.ManageSystem.service;

import com.ManageSystem.mapper.LoginMapper;
import com.ManageSystem.pojo.DetailData;
import com.ManageSystem.pojo.User;
import com.ManageSystem.util.NewRandomString;
import com.ManageSystem.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 修改账号信息
     */
    public void upUserData(DetailData data) {
        SqlSession sqlSession = factory.openSession();
        LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
        mapper.updataUser(data);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 查询所有
     */
    public User loginUserPass(User user) {
        SqlSession sqlSession = factory.openSession();
        LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
        User user1 = mapper.loginUserPass(user);
        sqlSession.close();
        return user1;
    }

    /**
     * 查询所有
     */
    public User loginUser(User user) {
        SqlSession sqlSession = factory.openSession();
        LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
        User user1 = mapper.loginUser(user);
        sqlSession.close();
        return user1;
    }

    /**
     * 注册账号
     */
    public void addUser(User user) {
        SqlSession sqlSession = factory.openSession();
        LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
        Integer maxUser = mapper.selectMaxUser();
        maxUser++;
        user.setDetaildata(maxUser);
        mapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
        addData(maxUser);
    }

    /*
     * 注册账号添加默认信息
     * */
    public void addData(int x) {
        SqlSession sqlSession = factory.openSession();
        LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
        String tacitlyImg = "img/User_PorImg/Tacitly.png";
        String filename = NewRandomString.getRandomString2(10);
        DetailData data = new DetailData(x, tacitlyImg, filename, 0, 0);
        mapper.addData(data);
        sqlSession.commit();
        sqlSession.close();
    }

    /*
     * 查询账号详细信息
     * */
    public DetailData inquireUserData(User user) {
        if (user.getUsername() != null) {
            User user1 = loginUser(user);
            SqlSession sqlSession = factory.openSession();
            LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
            DetailData detailData = mapper.selectData(user1.getDetaildata());
            sqlSession.close();
            return detailData;
        }
        return null;
    }
}
