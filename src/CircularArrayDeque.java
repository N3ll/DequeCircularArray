public class CircularArrayDeque<T> implements DequeI<T> {

	private T[] deque;
	private int front, rear, count;
	private static int DEFAULT_CAPACITY = 25;

	@SuppressWarnings("unchecked")
	public CircularArrayDeque(int capacity) {
		deque = (T[]) new Object[capacity];
		front = count = 0;
		rear = 1;
	}

	public CircularArrayDeque() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void addToFront(T newEntry) {
		ensureCapacity();
		deque[front] = newEntry;
		front = (front - 1 + deque.length) % deque.length;
		System.out.println("front " + front);
		count++;

	}

	@Override
	public void addToBack(T newEntry) {
		ensureCapacity();
		deque[rear] = newEntry;
		rear = (rear + 1) % deque.length;
		System.out.println("back " + rear);
		count++;
	}

	@Override
	public T removeFront() {
		T result = null;
		if (isEmpty())
			return result;
		result = deque[front];
		deque[front] = null;
		front = (front + 1 + deque.length) % deque.length;
		count--;
		return result;
	}

	@Override
	public T removeBack() {
		T result = null;
		if (isEmpty())
			return result;
		result = deque[rear];
		deque[rear] = null;
		rear = (rear - 1) % deque.length;
		count--;
		return result;
	}

	@Override
	public T getFront() {
		T result = null;
		if (isEmpty())
			return result;

		result = deque[(front + 1 + deque.length) % deque.length];
		return result;
	}

	@Override
	public T getBack() {
		T result = null;
		if (isEmpty())
			return result;
		result = deque[(rear - 1) % deque.length];
		return result;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < count; i++) {
			deque[front] = null;
			front = (front + 1) % deque.length;
		}
		front = rear = count = 0;
	}

	private void ensureCapacity() {
		if (size() == deque.length) {
			@SuppressWarnings("unchecked")
			T[] larger = (T[]) (new Object[deque.length * 2]);

			for (int i = 0; i < count; i++) {
				larger[i] = deque[front];
				front = (front + 1) % deque.length;
			}
			front = 0;
			rear = count;
			deque = larger;
		}
	}

	private int size() {
		return count;
	}

}
