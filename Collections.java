import java.util.List;
import java.util.Comparator;

public class Collections {

    public static <T extends Comparable<T>> int binarySearch(List<T> list, T key) {
        if (list == null || list.isEmpty()) {
            return -1;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midValue = list.get(mid);

            if (midValue.equals(key)) {
                return mid;
            } else if (midValue.compareTo(key) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static <T> int binarySearch(List<T> list, T key, Comparator<T> c) {
        if (list == null || list.isEmpty() || c == null) {
            return -1;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midValue = list.get(mid);

            int comparison = c.compare(midValue, key);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}