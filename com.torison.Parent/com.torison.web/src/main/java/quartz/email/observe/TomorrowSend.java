package quartz.email.observe;



import java.util.ArrayList;
import java.util.List;

public class TomorrowSend<T> implements Observerable {

    private List<Observer> observers;
    private List<T> data;

    public TomorrowSend(){
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers!=null) {
            this.observers.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i <observers.size() ; i++) {
            observers.get(i).update(data);
        }
    }

    public void setData(List<T> data){
        this.data = data;
        notifyObserver();
    }
}
