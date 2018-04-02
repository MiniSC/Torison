package designModel.wather;

/**
 * 抽象观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，update()会被回调
 */
public interface Observer {
    /**
     * 观察者更新自己的信息
     * @param message
     */
    public void update(String message);
}
