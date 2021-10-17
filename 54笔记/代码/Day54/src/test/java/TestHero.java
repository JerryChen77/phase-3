import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestHero {
    /**
     * 查询单个
     * @throws IOException
     */
    @Test
    public void getHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行sql
        Hero hero = heroDao.selectHero(2);

        System.out.println(hero);

        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 查询所有
     * @throws IOException
     */
    @Test
    public void getAllHero01() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行sql
        List<Hero> heroes = heroDao.selectAllHero();

        for (Hero hero : heroes) {
            System.out.println(hero);
        }

        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 模糊查询
     * @throws IOException
     */
    @Test
    public void selectHeroByName() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行sql
        // List<Hero> heroes = heroDao.selectHeroByName("%wu%");
        List<Hero> heroes = heroDao.selectHeroByName("wu");


        for (Hero hero : heroes) {
            System.out.println(hero);
        }
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insertHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行SQL
        Hero hero =  new Hero();
        hero.setUsername("luzhishen");
        hero.setGender("male");
        hero.setAge(33);
        hero.setAddr("天龙寺");
        hero.setInfo("倒拔垂杨柳的好汉");

        int i = heroDao.insertHero(hero);
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        int i = heroDao.deleteHero(10);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void updateHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        Hero hero = new Hero();
        hero.setId(1);
        hero.setUsername("及时雨");
        hero.setAge(49);
        hero.setGender("male");

        int i = heroDao.updateHero(hero);
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getHeroGenId01() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        Hero hero = new Hero();
        hero.setUsername("张顺");
        hero.setAge(23);
        hero.setGender("male");
        hero.setAddr("水泊梁山");
        hero.setInfo("浪里白条张顺");

        // 返回值是受影响的行数
        int genId01 = heroDao.getHeroGenId01(hero);

        // 回填的id
        System.out.println(hero.getId());
        System.out.println(hero);
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getHeroGenId02() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        Hero hero = new Hero();
        hero.setUsername("张顺");
        hero.setAge(23);
        hero.setGender("male");
        hero.setAddr("水泊梁山");
        hero.setInfo("浪里白条张顺");

        // 返回值是受影响的行数
        int genId01 = heroDao.getHeroGenId02(hero);

        // 回填的id
        System.out.println(hero.getId());
        System.out.println(hero);
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }
}
