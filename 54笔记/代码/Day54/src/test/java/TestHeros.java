import com.shine.dao.HeroDao;
import com.shine.dao.HerosDao;
import com.shine.entity.Hero;
import com.shine.entity.Heros;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

public class TestHeros {
    @Test
    public void getHeroGenId02() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取Mapper
        HerosDao heroDao = sqlSession.getMapper(HerosDao.class);

        Heros hero = new Heros();
        hero.setUsername("张顺");
        hero.setAge(23);
        hero.setGender("male");
        hero.setAddr("水泊梁山");
        hero.setInfo("浪里白条张顺");

        // 返回值是受影响的行数
        int genId01 = heroDao.getHeroGenId03(hero);

        // 回填的id
        System.out.println(hero.getId());
        System.out.println(hero);
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }
}
