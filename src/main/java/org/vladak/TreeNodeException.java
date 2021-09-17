package org.vladak;

public class TreeNodeException extends TreeException {
    TreeNodeException(String path) {
        super(String.format("'%s' is not a path element", path));
    }
}
