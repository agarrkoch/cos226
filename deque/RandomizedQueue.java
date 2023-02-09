import java.util.Iterator;

public class RandomizedQueue<Item> {

    private Item[] a;
    private int s; //size of array
    private int n; //items in array

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        s = 2;
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        if (s == 0) {
            return true;
        } else {
            return false;
        }
    }

    // return the number of items on the randomized queue
    public int size() {
        return s;
    }

    // add the item
    public void enqueue(Item item) {
        a[n] = item;
        n++;

        if (n == s) {
            resize(s * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        int r = (int) (Math.random() * n);
        Item remove = a[r];
        n--;

        if (r != n - 1) {
            a[r] = a[n - 1];
        } else {
            a[r] = null;
        }

        if (n == s / 4) {
            resize(s / 2);
        }

        return remove;
    }

    private void resize(int ns) {
        Item[] na = (Item[]) new Object[ns];

        for (int i = 0; i < n; i++) {
            na[i] = a[i];
        }
        a = na;
        s = ns;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int r = (int) (Math.random() * n);
        return a[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = n;
        private Item[] b = a;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            int r = (int) (Math.random() * i);
            Item r_ = b[r];

            if (r != i - 1) {
                b[r] = b[i - 1];
                b[i - 1] = r_;
            }

            i--;
            return r_;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue test = new RandomizedQueue();
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);

        Iterator iterator = test.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }
    }
}
