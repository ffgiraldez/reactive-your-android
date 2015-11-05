package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.AdapteeCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.ffgiraldez.reactiveyourandroid.operators.Sms;

/**
 * @author Fernando Franco Giráldez
 */
public class TelephonyEventCollection implements AdapteeCollection<Sms> {
    private final List<Sms> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Sms get(int index) {
        return list.get(index);
    }

    @Override
    public void add(Sms element) {
        list.add(element);
    }

    @Override
    public void remove(Sms element) {
        list.remove(element);
    }

    @Override
    public void addAll(Collection<Sms> elements) {
        list.addAll(elements);
    }

    @Override
    public void removeAll(Collection<Sms> elements) {
        list.removeAll(elements);
    }

    @Override
    public void clear() {
        list.clear();
    }
}
