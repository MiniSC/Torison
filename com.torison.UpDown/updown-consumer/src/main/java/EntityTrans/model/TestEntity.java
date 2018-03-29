package EntityTrans.model;

public class TestEntity<T> extends BaseEntity {

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String trans(){
      return super.transToBytesString(this);
    }
}
