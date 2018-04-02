package quartz.email.observe;



import java.util.List;

/**
 * 抽象观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，update()会被回调
 */
public interface Observer<T> {
    /**
     * 观察者更新自己的信息
     * @param data
     *
     */
    public void update(List<T> data);
}
