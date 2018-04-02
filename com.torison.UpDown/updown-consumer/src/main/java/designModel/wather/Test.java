package designModel.wather;

public class Test {

    public static void main(String[] args) {
        WechatServer server = new WechatServer();

        Observer userzhang = new User("zhang");
        Observer userlisi = new User("lisi");
        Observer userwang = new User("wang");

        server.registerObserver(userzhang);
        server.registerObserver(userlisi);
        server.registerObserver(userwang);
        server.setInfomation(".....");

        System.out.println("---------------");
        server.removeObserver(userzhang);
        server.setInfomation("1111111111");

    }
}
