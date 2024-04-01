import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;

public class ImmutableMap<T1, T2> implements Serializable {
    private final HashMap<T1, T2> map;

    public ImmutableMap(ArrayList<Pair<T1, T2>> pairs){
        HashMap<T1, T2> temp = new HashMap<T1, T2>();
        for(Pair<T1, T2> pair : pairs){
            temp.put(pair.first(), pair.second());
        }
        map = temp;
    }

    public T2 get(T1 key){
        return map.get(key);
    }
}
