package day8.데이터저장소시스템;

import javax.sql.DataSource;

public class MySQLUserRepository implements UserRepository{
    private final DataSource dataSource;

    public MySQLUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public String findById(String id) {
        System.out.println("Mysql DB에서 id" + id + "의 사용자를 찾습니다.");
        return null;
    }

    @Override
    public void save(User user) {
        System.out.println("Mysql DB에 사용자를 저장합니다.");
    }
}
