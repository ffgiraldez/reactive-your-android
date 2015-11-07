package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.AdapteeCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.ffgiraldez.reactiveyourandroid.operators.TelephonyEvent;

/**
 * @author Fernando Franco Giráldez
 */
public class TelephonyEventCollection implements AdapteeCollection<TelephonyEvent> {
    private final List<TelephonyEvent> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public TelephonyEvent get(int index) {
        return list.get(index);
    }

    @Override
    public void add(TelephonyEvent element) {
        list.add(element);
    }

    @Override
    public void remove(TelephonyEvent element) {
        list.remove(element);
    }

    @Override
    public void addAll(Collection<TelephonyEvent> elements) {
        list.addAll(elements);
    }

    @Override
    public void removeAll(Collection<TelephonyEvent> elements) {
        list.removeAll(elements);
    }

    @Override
    public void clear() {
        list.clear();
    }
}
