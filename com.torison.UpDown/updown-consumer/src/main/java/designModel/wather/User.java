package designModel.wather;

/**
 * 具体的观察者
 */
public class User implements Observer {

    private String name;
    private String message;

    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message  = message;
        read();
    }

    public void read(){
        System.out.println(name+"推送消息"+message);
    }
}
