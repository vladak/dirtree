package org.vladak;

public class TreeNodeException extends Exception {
    TreeNodeException(String path) {
        super(String.format("'%s' is not a path element", path));
    }
}
