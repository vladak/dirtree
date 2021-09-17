package org.vladak;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TreeNodeTest {
    @Test
    void testInvalidTreeNodeParameter() throws IOException {
        Path path = Files.createTempFile("foo", "bar");
        assertThrows(TreeNodeException.class, () -> {
            new TreeNode(path.toString());
        });
    }

    @Test
    void testEmptyTreeNodeParameter() {
        assertThrows(TreeNodeException.class, () -> {
            new TreeNode("");
        });
    }

    @Test
    void testRoot() throws IOException, TreeNodeException {
        Path path = Files.createTempFile("foo", "bar");
        System.out.println("root: " + path.getRoot().toString());
        new TreeNode(path.getRoot().toString());
    }

    @Test
    void testGetElem() throws TreeNodeException {
        String elem = "foo";
        TreeNode node = new TreeNode(elem);
        assertEquals(elem, node.getPathElem());
    }

    @Test
    void testAddChild() throws IOException, TreeNodeException {
        Path path = Files.createTempFile("foo", "bar");
        TreeNode root = new TreeNode(path.getRoot().toString());
        TreeNode node = new TreeNode("foo");
        assertThrows(TreeNodeException.class, () -> node.addChild(root));
    }
}
