    public static class TreeNode
    {
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        private int value;
        
        public TreeNode(int val) {
            this.value = val;
        }

        public TreeNode add(int value) {
            if (value > this.value) {
                if (right == null) {
                    right = new TreeNode(value);
                    right.parent = this;
                    return right;
                }
                else {
                    return right.add(value);
                }
            }
            else {
                if (left == null) {
                    left = new TreeNode(value);
                    left.parent = this;
                    return left;
                }
                else {
                    return left.add(value);
                }
            }
        }

        public int getMax() {
            int max_value = value;
            
            TreeNode root = this;
            
            while(root != null) {
                if (root.value > max_value) 
                    max_value = root.value;

                root = root.right;
            }

            return max_value;
        }
        
        public TreeNode getLeftMost(TreeNode root) {

            while (root != null && root.left != null) {
                root = root.left;
            }

            return root;
        }

        public TreeNode remove(TreeNode n) {
            TreeNode parent = n.parent;

            if (n.right != null && n.left != null) {
                TreeNode p = getLeftMost(n.right);
                remove(p);
                
                if (parent != null) {
                    p.left = n.left;
                    p.right = n.right;

                    _updateNode(n, p);

                    return null;
                }
                else {
                    p.left = this.left;
                    p.right = this.right;
                    p.parent = null;
                    return p;
                }
            }
            else if (n.right != null) {
                _updateNode(n, n.right);
            }
            else if (n.left != null) {
                _updateNode(n, n.left);
            }
            else {
                _updateNode(n, null);
            }

            return null;
        }

        protected void _updateNode(TreeNode removed, TreeNode newNode) {
            TreeNode parent = removed.parent;
            if (parent.left == removed) {
                parent.left = newNode;
            }
            else {
                parent.right = newNode;
            }
            
            if (newNode != null)
                newNode.parent = parent;
        }
    }
