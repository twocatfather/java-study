package day8.데이터저장소시스템;

public class MongoClient {

    public void save(String data) {
        System.out.println("Data saved to MongoDB: " + data + "success");
    }

    public String findById(String id) {
        System.out.println("Data found in MongoDB: " + id);
        return "Data found in MongoDB: " + id + "success";
    }
}
