package designModel.wather;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体被观察者
 */
public class WechatServer implements Observerable{

    private List<Observer> list;
    private String message;

    public WechatServer(){
        list = new ArrayList<Observer>();
    }


    @Override
    public void registerObserver(Observer o) {
         list.add(o);

    }

    @Override
    public void removeObserver(Observer o) {
        if (!list.isEmpty()){
            list.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i <list.size() ; i++) {
            Observer observer = list.get(i);
            observer.update(message);
        }

    }

    public void setInfomation(String s){
        this.message = s;
        System.out.println("更新信息："+s);
        notifyObserver();
    }
}
