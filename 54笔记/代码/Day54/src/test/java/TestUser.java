import com.shine.dao.UserDao;
import com.shine.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestUser {
    @Test
    public void getUserById01() throws IOException {
        // 加载配置文件获取SQL会话工厂
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SQL会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行会话
        User user = sqlSession.selectOne("com.shine.dao.UserDao.findById", 1);

        // 提交事务
        System.out.println(user);
        sqlSession.commit();

    }

    @Test
    public void getUserById02() throws IOException {
        // 加载配置文件获取SQL会话工厂
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建--构造--构造器模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SQL会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取UserDao的代理
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findById(2);

        // 提交事务
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void insertUser() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 执行sql
        User user = new User();
        user.setUsername("qianyi");
        user.setPassword("yiqian");

        int i = userDao.insertUser(user);
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void findUserByNameAndPwd() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 执行sql
        User user = userDao.findUserByNameAndPwd1("lisi", "sili");
        System.out.println(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findUserByNameAndPwd2() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 执行sql
        User user = userDao.findUserByNameAndPwd2("wangwu", "wuwang");
        System.out.println(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findUserByNameAndPwd3() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 执行sql
        User user = userDao.findUserByNameAndPwd3("zhaoliu", "liuzhao");
        System.out.println(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findUserByNameAndPwd4() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        Map<String,String> map = new HashMap();
        map.put("username","tianqi");
        map.put("password","qitian");
        // 执行sql
        User user = userDao.findUserByNameAndPwd4(map);
        System.out.println(user);

        sqlSession.commit();
        sqlSession.close();
    }
}
