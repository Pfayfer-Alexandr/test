public class Arrays {
    public static int binarySearch(byte[] a, byte key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(byte[] a, int fromIndex, int toIndex, byte key) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex > toIndex) {
            throw new IllegalArgumentException("Индексы вне диапазона");
        }

        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(char[] a, char key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(char[] a, int fromIndex, int toIndex, char key) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Индексы вне диапазона");
        }

        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(double[] a, double key) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(double[] a, int fromIndex, int toIndex, double key) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Индексы вне диапазона");
        }

        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }

            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(float[] a, float key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Проверка, найден ли ключ
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(float[] a, int fromIndex, int toIndex, float key) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Индексы вне диапазона");
        }

        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }

            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(int[] a, int key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }

            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Индексы вне диапазона");
        }

        int left = fromIndex;
        int right = toIndex - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(long[] a, long key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }

            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(long[] a, int fromIndex, int toIndex, long key) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Индексы вне диапазона");
        }

        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }

            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(short[] a, short key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }

            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int binarySearch(short[] a, int fromIndex, int toIndex, short key) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Индексы вне диапазона");
        }

        int left = fromIndex;
        int right = toIndex - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
