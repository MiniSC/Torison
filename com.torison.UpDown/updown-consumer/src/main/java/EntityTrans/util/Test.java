package EntityTrans.util;

import EntityTrans.model.EntitySerialize;
import EntityTrans.model.TestEntity;

public class Test {

    public static void main(String[] args) {
        String a = "jdlksaflk";
        TestEntity<String> testEntity = new TestEntity<>();
        testEntity.setData(a);
        System.out.println(testEntity.trans());
        String new1 = (String) EntitySerialize.toObject(EntitySerialize.toByteArray(a));
        System.out.println(new1);
    }
}
