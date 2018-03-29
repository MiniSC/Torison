package EntityTrans.util;

import EntityTrans.model.TestEntity;

public class Test {

    public static void main(String[] args) {
        String a = "jdlksaflk";
        TestEntity<String> testEntity = new TestEntity<>();
        testEntity.setData(a);
        System.out.println(testEntity.trans());
    }
}
