import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.*;
public class Predicates {

    public static <T> void remove(Collection<T> coll, Predicate<T> pred) {
        for(T x : coll) {
            if(pred.test(x))
                coll.remove(x);
        }
    }
    public static <T> void retain(Collection<T> coll, Predicate<T> pred) {
        for(T x : coll) {
            if(!pred.test(x))
                coll.remove(x);
        }
    }
    public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
        List<T> temp = new ArrayList<>();
        for(T x : coll) {
            if(pred.test(x))
                temp.add(x);
        }
        return temp;
    }
    public static <T> int find(ArrayList<T> list, Predicate<T> pred) {
        int count = 0;
        for(T x : list) {
            if(pred.test(x))
                return count;
            count++;
        }
        //At this point, pred is false
        return -1;
    }
}
