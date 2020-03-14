public class ArrayDictionary implements Dictionary {
    private int capacity;
    private int count;
    private KVEntry[] entries;

    public ArrayDictionary(int capacity) {
        this.capacity = capacity;
        entries = new KVEntry[capacity];
    }

    private int hashFunction(int key) {
        return key % capacity;
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean add(int key, int value) {

        int hashedKey = hashFunction(key);

        // when there's no entry yet
        if (entries[hashedKey] == null) {
            if (count == capacity) {
                return false;
            }
            entries[hashedKey] = new KVEntry(key, value);
            count++;
            return true;
        }

        KVEntry ptr = entries[hashedKey];
        KVEntry pNewNode = null;
        while (ptr != null) {
            // update value if key already exists
            if (ptr.key == key) {
                ptr.value = value;
                return true;
            }
            pNewNode = ptr;
            ptr = ptr.next;
        }

        // add an entry to the end of the chain
        pNewNode.next = new KVEntry(key, value);
        return true;
    }

    //HOMEWORK SOLUTION
    // PS: This method only removes the first recorded instance of the key, if there are more, they will remain in the dictionary
    //	   I am making the assumption that this "oversight" was intentional on your part professor.
    @Override
    public boolean remove(int key) {
    	if(capacity == 0) {return false;}
    	int hashKey = hashFunction(key);
        if(entries[hashKey] != null) {
        	KVEntry ptr = entries[hashKey];
        	while(ptr.next != null) {
        		if(ptr.next.key == key) {
        			ptr.next = ptr.next.next;
        			return true;
        		}
        		ptr = ptr.next;
        	}
        	//Check for the first element being the key
        	if(entries[hashKey].next == null && entries[hashKey].key == key) {entries[hashKey] = null; return true;}
        	if(entries[hashKey].key == key) {entries[hashKey] = entries[hashKey].next; return true;}
        }
        return false;
    }

    //HOMEWORK SOLUTION
    @Override
    public boolean contains(int key) {
    	if(capacity == 0) {return false;}
    	int hashKey = hashFunction(key);
        KVEntry ptr = entries[hashKey];
        while(ptr != null) {
        	if(ptr.key == key) {
        		return true;
        	}
        	ptr = ptr.next;
        }
        return false;
    }

    @Override
    public Integer get(int key) {
        // NOT IMPLEMENTED
    	/*
    	 * Implementation was not neccessary for required funciton tasks.
    	 * 
    	 */
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (entries[i] == null) {
                builder.append("dictionary["+ i + "] = null\n");
                continue;
            }
            KVEntry ptr = entries[i];
            builder.append("dictionary["+i+"] = {");
            while (ptr != null) {
                builder.append("(" + ptr.key + ", " + ptr.value + ")");
                ptr = ptr.next;
            }
            builder.append("}\n");
        }
        return builder.toString();
    }
}
