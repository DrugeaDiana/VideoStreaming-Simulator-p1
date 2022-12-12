package movieClasses;

import java.util.Comparator;

public class MovieComparatorIncr implements Comparator {
    /**
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Object o1, Object o2) {
        Movie movie1 = (Movie)o1;
        Movie movie2 = (Movie)o2;
        int result = Double.compare(movie1.getRating(), movie2.getRating());
        if (result == 0) {
            result = Integer.compare(movie1.getDuration(), movie2.getDuration());
        }
        return result;
    }
}
