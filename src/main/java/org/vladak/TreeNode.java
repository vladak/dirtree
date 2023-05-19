package org.vladak;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * add-only tree that holds file-system path elements
 * TODO: use generics ?
 */
public class TreeNode {
    private final String pathElem;
    private final Map<String,TreeNode> children = new HashMap<>();
    private TreeNode parent = null;

    // TODO: move to some utility class ? (not TreeUtil)
    static boolean isRoot(String pathElem) {
        Path path = Paths.get(pathElem);
        return path.toString().equals(path.getRoot().toString());
    }

    /**
     * Create tree node from a path element.
     * @param pathElem path element
     * @throws TreeNodeException if the pathElem is not a path component or is empty
     */
    TreeNode(String pathElem) throws TreeNodeException {
        if (pathElem.isEmpty()) {
            throw new TreeNodeException("empty path element");
        }

        if (pathElem.contains(File.separator) && !isRoot(pathElem)) {
            throw new TreeNodeException(pathElem);
        }

        this.pathElem = pathElem;
    }

    public String getPathElem() {
        return pathElem;
    }

    /**
     * @return set of child nodes
     */
    public Collection<TreeNode> getChildren() {
        return children.values();
    }

    /**
     * @param child new child node
     * @throws TreeNodeException if the child node contains invalid path element
     */
    public void addChild(TreeNode child) throws TreeNodeException {
        if (child.getPathElem().contains(File.separator)) {
            throw new TreeNodeException(child.getPathElem());
        }
        children.put(child.getPathElem(), child);
        child.setParent(this);
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    /**
     * @param pathElem path element
     * @return child node that matches the path element or {@code null}
     */
    public TreeNode getChild(String pathElem) {
        return children.get(pathElem);
    }

    public String toString() {
        return pathElem;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof TreeNode)) {
            return false;
        }

        TreeNode other = (TreeNode) otherObject;

        // TODO: might want to compare parent (if any) and children (if any)
        return other.getPathElem().equals(this.getPathElem());
    }
}
