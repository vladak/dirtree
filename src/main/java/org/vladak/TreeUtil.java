package org.vladak;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.vladak.TreeNode.isRoot;

public class TreeUtil {
    private TreeUtil() {
        // private to enforce static
    }

    /**
     * TODO: add another method with visitor pattern and implement this one as a wrapper ?
     * Get all leaves of a node by performing depth first traversal.
     * @param node initiating node
     * @return set of nodes
     */
    public static Set<TreeNode> getLeaves(TreeNode node) {
        if (node.getChildren().isEmpty()) {
            return Set.of(node);
        }

        Set<TreeNode> res = new HashSet<>();
        for (TreeNode child : node.getChildren()) {
            res.addAll(getLeaves(child));
        }

        return res;
    }

    /**
     * see {@link #addPath(String, TreeNode, boolean)}
     * @param path absolute file system path
     * @param root root node
     * @throws TreeException on bad input data
     */
    public static void addPath(String path, TreeNode root) throws TreeException {
        addPath(path, root, false);
    }

    /**
     * break down path into path elements and create node in given tree
     * @param path absolute file system path
     * @param root root node
     * @param stripBasename strip basename from the path prior to breaking it into the path elements
     * @throws TreeException on bad input data
     */
    public static void addPath(String path, TreeNode root, boolean stripBasename) throws TreeException {
        File file = new File(path);
        if (!file.isAbsolute()) {
            throw new TreeException("not absolute path");
        }

        if (!isRoot(root.getPathElem())) {
            throw new TreeException("not a root node");
        }

        Path filePath = Paths.get(path);
        if (!root.getPathElem().equals(filePath.getRoot().toString())) {
            throw new TreeException(String.format("root of '%s' is not equal to root node: %s",
                    filePath, root.getPathElem()));
        }

        if (stripBasename) {
            path = path.substring(0, path.lastIndexOf(File.separator));
        }

        TreeNode node = root;
        Path p = new File(path).toPath();
        Iterator<Path> elements = p.iterator();
        while (elements.hasNext()) {
            String pathElem = elements.next().toString();
            TreeNode childNode = node.getChild(pathElem);
            if (childNode == null) {
                TreeNode newnode = new TreeNode(pathElem);
                node.addChild(newnode);
                node = newnode;
            } else {
                node = childNode;
            }
        }
    }

    /**
     * @param node tree node
     * @return path from the node to the root node
     */
    public static String getPathToRoot(TreeNode node) {
        // TODO: what if the node is root

        StringBuilder sb = new StringBuilder();
        sb.append(node.getPathElem());
        node = node.getParent();
        while (node != null) {
            // TODO: Windows
            if (!node.getPathElem().equals("/")) {
                sb.insert(0, File.separator);
                sb.insert(0, node.getPathElem());
            } else {
                sb.insert(0, node.getPathElem());
            }
            node = node.getParent();
        }
        // TODO: Windows
        // path = "/" + path;

        return sb.toString();
    }
}
