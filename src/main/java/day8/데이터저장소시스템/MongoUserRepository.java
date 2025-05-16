package day8.데이터저장소시스템;

public class MongoUserRepository implements UserRepository{
    private final MongoClient mongoClient;

    public MongoUserRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public String findById(String id) {
        return mongoClient.findById(id);
    }

    @Override
    public void save(User user) {
        mongoClient.save(user.toString());
    }
}
