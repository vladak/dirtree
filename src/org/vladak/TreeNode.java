package org.vladak;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * add-only tree that holds file-system path elements
 */
public class TreeNode {
    private final String pathElem;
    private Set<TreeNode> children = new HashSet<>();

    TreeNode(String pathElem) throws TreeNodeException {
        if (!pathElem.equals("/") && pathElem.contains(File.separator)) {
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
        return Collections.unmodifiableSet(children);
    }

    /**
     * @param child new child node
     * @throws TreeNodeException if the child node contains invalid path element
     */
    public void addChild(TreeNode child) throws TreeNodeException {
        if (child.getPathElem().contains(File.separator)) {
            throw new TreeNodeException(child.getPathElem());
        }
        children.add(child);
    }

    /**
     * @param pathElem path element
     * @return child node that matches the path element or null
     */
    public TreeNode getChild(String pathElem) {
        for (TreeNode child : children) {
            if (child.getPathElem().equals(pathElem)) {
                return child;
            }
        }

        return null;
    }

    public String toString() {
        return pathElem;
    }
}
