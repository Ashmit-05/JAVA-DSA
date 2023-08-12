public class UnionFind {
	private int size;
	private int[] sz; // used to track the size of each component
	private int[] id; // id[i] points to the parent of i, if id[i] = i, then i is root node
	private int numOfComponents;

	public UnionFind(int size) {
		if(size <= 0) {
			System.out.println("Size must be more than 0");
			return;
		}

		this.size = numOfComponents = size;
		sz = new int[size];
		id = new int[size];

		for(int i = 0; i < size; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	public int find(int p) {
		int root = p;
		while(root != id[root]) {
			root = id[root];
		}

		// Path compression
		while(p != root) {
			int next = id[p];
			id[p] = root;
			p = next;
		}
		return root;
	}
	
	public void unify(int p, int q) {
		int root1 = find(p);
		int root2 = find(q);

		if(root1 == root2) return;

		if(sz[root1] < sz[root2]) {
			sz[root2] += sz[root1];
			id[root1] = root2;
		} else {
			sz[root1] += sz[root2];
			id[root2] = root1;
		}
		numOfComponents--;
	}
	
	// some common operations
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	public int componentSize(int p) {
		return sz[find(p)];
	}
	public int size() {
		return size;
	}
	public int components() {
		return numOfComponents;
	}


}
