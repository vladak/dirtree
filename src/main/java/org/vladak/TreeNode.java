package org.vladak;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * add-only tree that holds file-system path elements
 */
public class TreeNode {
    private final String pathElem;
    private final Map<String,TreeNode> children = new HashMap<>();
    private TreeNode parent = null;

    /**
     * Create tree node from a path element.
     * @param pathElem path element
     * @throws TreeNodeException if the pathElem is not a path component or is empty
     */
    TreeNode(String pathElem) throws TreeNodeException {
        if (pathElem.isEmpty()) {
            throw new TreeNodeException("empty path element");
        }

        Path path = Paths.get(pathElem);
        if (pathElem.contains(File.separator) && !path.toString().equals(path.getRoot().toString())) {
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
    public Set<TreeNode> getChildren() {
        return Set.copyOf(children.values());
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
     * @return child node that matches the path element or null
     */
    public TreeNode getChild(String pathElem) {
        return children.getOrDefault(children.get(pathElem), null);
    }

    public String toString() {
        return pathElem;
    }
}
